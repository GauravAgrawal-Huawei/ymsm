/*
 * Copyright 2016-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.yms.app.ymsm;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.onosproject.core.ApplicationId;
import org.onosproject.core.CoreService;
import org.onosproject.yms.app.yab.YangApplicationBroker;
import org.onosproject.yms.app.ydt.DefaultYdtWalker;
import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ysr.DefaultYangSchemaRegistry;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ych.YangCodecHandler;
import org.onosproject.yms.ych.YangDataTreeCodec;
import org.onosproject.yms.ych.YangProtocolEncodingFormat;
import org.onosproject.yms.ydt.YdtBuilder;
import org.onosproject.yms.ydt.YdtResponse;
import org.onosproject.yms.ydt.YdtWalker;
import org.onosproject.yms.ydt.YmsOperationType;
import org.onosproject.yms.ymsm.YmsService;
import org.onosproject.yms.ynh.YangNotificationService;
import org.onosproject.yms.ysr.YangModuleIdentifier;
import org.onosproject.yms.ysr.YangModuleLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.onlab.util.Tools.groupedThreads;

/**
 * Represents implementation of YANG application management system manager.
 */
@Service
@Component(immediate = true)
public class YmsManager
        implements YmsService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationId appId;
    private YangSchemaRegistry schemaRegistry;
    private ExecutorService schemaRegistryExecutor;
    private static final String APP_ID = "org.onosproject.app.yms";

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected CoreService coreService;

    @Activate
    public void activate() {
        appId = coreService.registerApplication(APP_ID);
        schemaRegistry = new DefaultYangSchemaRegistry();
        schemaRegistryExecutor = Executors.newSingleThreadExecutor(
                groupedThreads(
                        "onos/apps/yang-management-system/schema-registry",
                        "schema-registry-handler", log));
        // TODO implementation.
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        // TODO implementation.
        log.info("Stopped");
    }

    @Override
    public YdtBuilder getYdtBuilder(String logicalRootName,
                                    String rootNamespace,
                                    YmsOperationType operationType) {
        return new YangRequestWorkBench(logicalRootName, rootNamespace,
                                        operationType, schemaRegistry, true);
    }

    @Override
    public YdtBuilder getYdtBuilder(String logicalRootName,
                                    String rootNamespace,
                                    YmsOperationType operationType,
                                    Object schemaRegistryForYdt) {
        if (schemaRegistryForYdt != null) {
            return new YangRequestWorkBench(logicalRootName, rootNamespace,
                                            operationType,
                                            (YangSchemaRegistry)
                                                    schemaRegistryForYdt,
                                            false);
        } else {
            return new YangRequestWorkBench(logicalRootName, rootNamespace,
                                            operationType, this.schemaRegistry,
                                            true);
        }
    }

    @Override
    public YdtWalker getYdtWalker() {
        return new DefaultYdtWalker();
    }

    @Override
    public YdtResponse executeOperation(YdtBuilder operationRequest) {
        YangApplicationBroker requestBroker =
                new YangApplicationBroker(schemaRegistry);
        switch (operationRequest.getYmsOperationType()) {
            case EDIT_CONFIG_REQUEST:
                try {
                    return requestBroker.processEdit(operationRequest);
                } catch (CloneNotSupportedException e) {
                    log.error("YAB: failed to process edit request.");
                }
            case QUERY_CONFIG_REQUEST:
                // TODO : to be implemented
            case QUERY_REQUEST:
                return requestBroker.processQuery(operationRequest);
            case RPC_REQUEST:
                return requestBroker.processOperation(operationRequest);
            default:
                // TODO : throw exception
        }
        return null;
    }

    @Override
    public YangNotificationService getYangNotificationService() {
        return null;
    }

    @Override
    public YangModuleLibrary getYangModuleLibrary() {
        return null;
    }

    @Override
    public String getYangFile(YangModuleIdentifier moduleIdentifier) {
        return null;
    }

    @Override
    public void registerDefaultCodec(YangDataTreeCodec defaultCodec,
                                     YangProtocolEncodingFormat dataFormat) {

    }

    @Override
    public void registerService(Object yangManager, Class<?> yangService,
                                List<String> supportedFeatureList) {
        schemaRegistryExecutor.execute(() -> schemaRegistry
                .registerApplication(yangManager, yangService));

        //For notification handler.
        DefaultYangSchemaRegistry defaultYangSchemaRegistry =
                (DefaultYangSchemaRegistry) schemaRegistry;
        if (defaultYangSchemaRegistry.verifyNotificationObject(yangService)) {
            //TODO: register notification handler.
        }

    }

    @Override
    public void unRegisterService(Object appManager, Class<?> yangService) {
        schemaRegistry.unRegisterApplication(appManager, yangService);
    }

    @Override
    public YangCodecHandler getYangCodecHandler() {
        return null;
    }

    /**
     * Returns schema registry.
     *
     * @return schema registry
     */
    public YangSchemaRegistry getSchemaRegistry() {
        return schemaRegistry;
    }

}
