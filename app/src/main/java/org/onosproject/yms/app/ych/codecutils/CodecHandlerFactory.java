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

package org.onosproject.yms.app.ych.codecutils;


import org.onosproject.yms.ych.YangProtocolEncodingFormat;
import org.onosproject.yms.ydt.YdtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an ysb handle factory to create different types of YANG data tree
 * node.
 */
public final class CodecHandlerFactory {

    private static final Logger log = LoggerFactory.getLogger(CodecHandlerFactory.class);

    /**
     * Utility factory class, hence the object creation is forbidden.
     */
    private CodecHandlerFactory() {
    }

    /**
     * Creates single instance node handler.
     */
    private static XmlCodecSingleInstanceHandler singleInstanceNode = new XmlCodecSingleInstanceHandler();

    /**
     * Creates multi instance node handler.
     */
    private static XmlCodecMultiInstanceHandler multiInstanceNode = new XmlCodecMultiInstanceHandler();

    /**
     * Creates single instance leaf node handler.
     */
    private static XmlCodecSingleInstanceLeafHandler singleInstanceLeaf = new XmlCodecSingleInstanceLeafHandler();

    /**
     * Creates multi instance leaf node handler.
     */
    private static XmlCodecMultiInstanceLeafHandler multiInstanceLeaf = new XmlCodecMultiInstanceLeafHandler();

    /**
     * Returns YSB instance handler node instance.
     *
     * @param node                 YDT context node
     * @param recvedDataFormat     data format type expected from driver
     * @return                     returns YSB handler node instance
     */
    public static XmlCodecHandler getCodecHandlerForContext(YdtContext node,
                                                            YangProtocolEncodingFormat recvedDataFormat) {
        switch (node.getYdtType()) {
            case SINGLE_INSTANCE_NODE: {
                if (recvedDataFormat == YangProtocolEncodingFormat.XML_ENCODING) {
                    return singleInstanceNode;
                } else {
                    // TODO
                }
                break;
            }

            case MULTI_INSTANCE_NODE: {
                if (recvedDataFormat == YangProtocolEncodingFormat.XML_ENCODING) {
                    return multiInstanceNode;
                } else {
                    // TODO
                }
                break;
            }

            case SINGLE_INSTANCE_LEAF_VALUE_NODE: {
                if (recvedDataFormat == YangProtocolEncodingFormat.XML_ENCODING) {
                    return singleInstanceLeaf;
                } else {
                    // TODO
                }
                break;
            }

            case MULTI_INSTANCE_LEAF_VALUE_NODE: {
                if (recvedDataFormat == YangProtocolEncodingFormat.XML_ENCODING) {
                    return multiInstanceLeaf;
                } else {
                    // TODO
                }
                break;
            }
            default: {
                log.error("YCH: Given YDT type is not supported.");
            }
        }

        return null;
    }
}
