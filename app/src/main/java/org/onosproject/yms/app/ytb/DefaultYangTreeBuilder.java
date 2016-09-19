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

package org.onosproject.yms.app.ytb;

import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YmsOperationType;

import java.util.Iterator;
import java.util.List;

import static org.onosproject.yms.ydt.YmsOperationType.NOTIFICATION;
import static org.onosproject.yms.ydt.YmsOperationType.RPC_REPLY;

/**
 * Representation of YANG tree builder which generates YANG data tree from the
 * class objects which are provided from the applications and return it to the
 * protocol(s).
 */
public class DefaultYangTreeBuilder implements YangTreeBuilder {

    /**
     * Creates the YANG tree builder.
     */
    public DefaultYangTreeBuilder() {
    }

    @Override
    public YdtExtendedBuilder getYdtBuilderForYo(
            List<Object> moduleObject, String rootNodeName,
            String rootNodeNameSpace, YmsOperationType operationType,
            YangSchemaRegistry appSchemaRegistry) {

        if (moduleObject == null || moduleObject.isEmpty()) {
            throw new YtbException("The input module/sub-module object list, " +
                                           "cannot be null.");
        }

        YdtExtendedBuilder ydtExtendedBuilder = new YangRequestWorkBench(
                rootNodeName, rootNodeNameSpace, operationType,
                appSchemaRegistry, false);

        Iterator<Object> moduleObjectIterator = moduleObject.listIterator();
        while (moduleObjectIterator.hasNext()) {
            Object yangObject = moduleObjectIterator.next();
            YdtBuilderFromYo moduleYdtBuilder = new YdtBuilderFromYo(
                    ydtExtendedBuilder, yangObject, appSchemaRegistry);

            moduleYdtBuilder.getModuleNodeFromYsr(yangObject);
            moduleYdtBuilder.createYdtFromRootObject();
        }
        return ydtExtendedBuilder;
    }

    @Override
    public YdtContext getYdtForNotification(
            Object notificationObject, String rootNodeName,
            YangSchemaRegistry appSchemaRegistry) {

        if (notificationObject == null) {
            throw new YtbException("The input module/sub-module object list, " +
                                           "cannot be null.");
        }

        YdtExtendedBuilder ydtExtendedBuilder = new YangRequestWorkBench(
                rootNodeName, null, NOTIFICATION, appSchemaRegistry, false);
        YdtBuilderFromYo moduleYdtBuilder = new YdtBuilderFromYo(
                ydtExtendedBuilder, notificationObject, appSchemaRegistry);

        moduleYdtBuilder.getRootYangNodeWithNotificationFromYsr(
                notificationObject);
        /*
        Forms YDT in module, so that notification can further enhance the tree.
         */
        moduleYdtBuilder.createModuleNodeInYdt();
        moduleYdtBuilder.createYdtFromRootObject();
        return ydtExtendedBuilder.getRootNode();
    }

    @Override
    public YdtExtendedBuilder getYdtForRpcResponse(
            Object rpcOutputObject, YangRequestWorkBench rpcRequestWorkBench) {

        if (rpcOutputObject == null) {
            throw new YtbException("The RPC response class object, cannot be " +
                                           "null.");
        }

        // Gets the logical root node from RPC request work bench.
        YdtExtendedContext logicalRootNode = rpcRequestWorkBench.getRootNode();

        /*
         Creates a new work bench for RPC reply from the contents from request
         work bench
         */
        YdtExtendedBuilder ydtExtendedBuilder = new YangRequestWorkBench(
                logicalRootNode.getName(), logicalRootNode.getNamespace(),
                RPC_REPLY, rpcRequestWorkBench.getYangSchemaRegistry(), false);
        YdtBuilderFromYo moduleYdtBuilder = new YdtBuilderFromYo(
                ydtExtendedBuilder, rpcOutputObject,
                rpcRequestWorkBench.getYangSchemaRegistry());

        // Forms YDT till RPC, so that output can further enhance the tree.
        moduleYdtBuilder.createModuleAndRpcNodeInYdt(logicalRootNode);
        moduleYdtBuilder.createYdtFromRootObject();
        return ydtExtendedBuilder;
    }
}
