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

package org.onosproject.yms.app.ych;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onosproject.yms.app.ych.codecutils.YchException;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.yob.DefaultYobBuilder;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.app.ytb.DefaultYangTreeBuilder;
import org.onosproject.yms.app.ytb.YtbException;
import org.onosproject.yms.ych.YangCodecHandler;
import org.onosproject.yms.ych.YangCompositeEncoding;
import org.onosproject.yms.ych.YangDataTreeCodec;
import org.onosproject.yms.ych.YangProtocolEncodingFormat;
import org.onosproject.yms.ydt.YdtBuilder;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YmsOperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Represents implementation of YANG SBI broker interfaces.
 * YSB acts as a broker between YMS and driver/provider.
 */
public class DefaultYangCodecHandler
        implements YangCodecHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Schema registry for driver.
     */
    private YangSchemaRegistry yangSchemaRegistry;

    /**
     * Default codecs.
     */
    private Map<YangProtocolEncodingFormat, YangDataTreeCodec> defaultCodecs;

    /**
     * Override codec handler.
     */
    private Map<YangProtocolEncodingFormat, YangDataTreeCodec> overRideCodecs = new HashMap<>();

    /**
     * Returns schema registry for the driver.
     *
     * @return schema registry for the driver
     */
    private YangSchemaRegistry getYangSchemaRegistry() {
        return yangSchemaRegistry;
    }

    /**
     * Sets the schema registry for the driver.
     *
     * @param yangSchemaRegistry the schema registry for the driver
     */
    private void setYangSchemaRegistry(YangSchemaRegistry yangSchemaRegistry) {
        this.yangSchemaRegistry = yangSchemaRegistry;
    }

    /**
     * Returns the default codec handler.
     *
     * @return the default codec handler
     */
    public Map<YangProtocolEncodingFormat, YangDataTreeCodec> getDefaultCodecs() {
        return defaultCodecs;
    }

    /**
     * Sets the default codec handler.
     *
     * @param defaultCodecs the default codec handler
     */
    public void setDefaultCodecs(
            Map<YangProtocolEncodingFormat, YangDataTreeCodec> defaultCodecs) {
        this.defaultCodecs = defaultCodecs;
    }

    /**
     * Returns the override codec handler.
     *
     * @return the override codec handler
     */
    public Map<YangProtocolEncodingFormat, YangDataTreeCodec> getOverRideCodecs() {
        return overRideCodecs;
    }

    /**
     * Creates a new YANG application broker.
     *
     * @param schemaRegistry YANG schema registry
     * @param defaultCodecs   default codec handler
     */
    public DefaultYangCodecHandler(YangSchemaRegistry schemaRegistry,
                                   Map<YangProtocolEncodingFormat, YangDataTreeCodec> defaultCodecs) {
        setYangSchemaRegistry(schemaRegistry);
        setDefaultCodecs(defaultCodecs);
    }

    /**
     * Sets the override codec handler.
     *
     * @param overRideCodecs the override codec handler
     */
    public void setOverRideCodecs(Map<YangProtocolEncodingFormat, YangDataTreeCodec> overRideCodecs) {
        this.overRideCodecs = overRideCodecs;
    }


    @Override
    public void addDeviceSchema(Class yangModule) {
        getYangSchemaRegistry().registerApplication(null, yangModule, null);
    }

    @Override
    public String encodeOperation(String logicalRootName, String logicalRootNameSpace, Map<String,
            String> tagAttributeLinkedMap, List<Object> yangModuleList, YangProtocolEncodingFormat dataFormat,
            YmsOperationType protocolOperation) {

        if (yangModuleList == null || yangModuleList.isEmpty()) {
            throw new YchException("YCH Error: The input module/sub-module object list cannot be null.");
        }

        // Get yang data tree from YTB for the received objects.
        DefaultYangTreeBuilder defaultYangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtExtendedBuilder = defaultYangTreeBuilder.getYdtBuilderForYo(yangModuleList,
                                                                       logicalRootName,
                                                                       logicalRootNameSpace,
                                                                       protocolOperation,
                                                                       getYangSchemaRegistry());

        // Get the default codec handler.
        YangDataTreeCodec codec = null;
        YangDataTreeCodec defaultCodec = getDefaultCodecs().get(dataFormat);
        if (defaultCodec != null) {
            codec = defaultCodec;
        }

        // Check over ridden codec handler is exist or not.
        if (overRideCodecs != null) {
            YangDataTreeCodec overRidingCodec = overRideCodecs.get(dataFormat);
            if (overRidingCodec != null) {
                codec = overRidingCodec;
            }
        }

        // Get the xml string form codec handler.
        String encodedString = null;
        ydtExtendedBuilder.setRootTagAttributeMap(tagAttributeLinkedMap);
        if (codec != null) {
            encodedString = codec.encodeYdtToProtocolFormat(ydtExtendedBuilder, protocolOperation);
        }

        return encodedString;
    }

    @Override
    public YangCompositeEncoding encodeCompositeOperation(String rootName, String rootNamespace,
                                                          Object appModuleObject,
                                                          YangProtocolEncodingFormat dataFormat,
                                                          YmsOperationType protocolOperation) {

        if (appModuleObject == null) {
            throw new YtbException("YCH Error: The input module/sub-module object cannot be null.");
        }

        List<Object> yangModuleList = new ArrayList<>();
        yangModuleList.add(appModuleObject);

        // Get yang data tree from YTB for the received objects.
        DefaultYangTreeBuilder defaultYangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtExtendedBuilder = defaultYangTreeBuilder.getYdtBuilderForYo(yangModuleList,
                                                                                          rootName,
                                                                                          rootNamespace,
                                                                                          protocolOperation,
                                                                                          getYangSchemaRegistry());

        // Get the default codec handler.
        YangDataTreeCodec codec = null;
        YangDataTreeCodec defaultCodec = getDefaultCodecs().get(dataFormat);
        if (defaultCodec != null) {
            codec = defaultCodec;
        }

        // Check over ridden codec handler is exist or not.
        if (overRideCodecs != null) {
            YangDataTreeCodec overRidingCodec = overRideCodecs.get(dataFormat);
            if (overRidingCodec != null) {
                codec = overRidingCodec;
            }
        }

        // Get the composite response from codec handler.
        YangCompositeEncoding yangCompositeEncoding = null;
        if (codec != null) {
            yangCompositeEncoding = codec.encodeYdtToCompositeProtocolFormat(ydtExtendedBuilder, protocolOperation);
        }

        return yangCompositeEncoding;
    }

    @Override
    public List<Object> decode(String inputString, YangProtocolEncodingFormat dataFormat,
                               YmsOperationType protocolOperation) {

        YdtBuilder ydtBuilder = null;
        YangDataTreeCodec codec = null;

        // Get the default codec handler.
        YangDataTreeCodec defaultCodec = getDefaultCodecs().get(dataFormat);
        if (defaultCodec != null) {
            codec = defaultCodec;
        }

        // Check over ridden codec handler is exist or not.
        if (overRideCodecs != null) {
            YangDataTreeCodec overRidingCodec = overRideCodecs.get(dataFormat);
            if (overRidingCodec != null) {
                codec = overRidingCodec;
            }
        }

        // Get the YANG data tree
        if (codec != null) {
            ydtBuilder = codec.decodeProtocolDataToYdt(inputString, getYangSchemaRegistry(), protocolOperation);
        }

        if (ydtBuilder != null) {
            return getObjectList(ydtBuilder.getRootNode());
        }

        return null;
    }

    @Override
    public Object decode(YangCompositeEncoding protocolData,
                         YangProtocolEncodingFormat dataFormat,
                         YmsOperationType protocolOperation) {

        YdtBuilder ydtBuilder = null;
        YangDataTreeCodec codec = null;

        // Get the default codec handler.
        YangDataTreeCodec defaultCodec = getDefaultCodecs().get(dataFormat);
        if (defaultCodec != null) {
            codec = defaultCodec;
        }

        // Check over ridden codec handler is exist or not.
        if (overRideCodecs != null) {
            YangDataTreeCodec overRidingCodec = overRideCodecs.get(dataFormat);
            if (overRidingCodec != null) {
                codec = overRidingCodec;
            }
        }

        // Get the YANG data tree
        if (codec != null) {
            ydtBuilder = codec.decodeCompositeProtocolDataToYdt(protocolData, dataFormat, protocolOperation);
        }

        // Get the module object by using YANG data tree
        if (ydtBuilder != null) {
            return getObjectList(ydtBuilder.getRootNode());
        }

        return null;
    }

    @Override
    public void registerOverriddenCodec(YangDataTreeCodec overriddenCodec, YangProtocolEncodingFormat dataFormat) {
        overRideCodecs.put(dataFormat, overriddenCodec);
    }

    /**
     * Returns the list of objects from YDT data tree.
     *
     * @param rootNode YDT root node
     * @return returns list of objects
     */
    private List<Object> getObjectList(YdtContext rootNode) {

        List<Object> objectList = new ArrayList<>();
        Object receivedObject = null;
        if (rootNode == null) {
            // TODO
            return null;
        }

        if (rootNode.getFirstChild() == null) {
            // TODO
            return null;
        }

        YdtContext curNode = rootNode.getFirstChild();
        DefaultYobBuilder yobBuilder = new DefaultYobBuilder();
        receivedObject = yobBuilder.getYangObject((YdtExtendedContext) curNode, yangSchemaRegistry);
        objectList.add(receivedObject);

        // Check next module is exit or not. If exist get the object for that.
        while (curNode.getNextSibling() != null) {
            curNode = curNode.getNextSibling();
            receivedObject = yobBuilder.getYangObject((YdtExtendedContext) curNode, yangSchemaRegistry);
            objectList.add(receivedObject);
        }

        return objectList;
    }
}
