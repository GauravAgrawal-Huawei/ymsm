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
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YmsOperationType;

import java.util.List;

/**
 * Abstraction of an entity which provides interfaces to build YANG data tree
 * from the object received from YNH, YAB or YCH.
 */
public interface YangTreeBuilder {

    /**
     * Returns the YDT builder after building the tree corresponding to the
     * response YANG object received from any of the protocol such as YAB or
     * YCH.
     *
     * @param moduleObject      class object from application
     * @param rootNodeName      name of the logical root node
     * @param rootNodeNameSpace namespace of the logical root node
     * @param operationType     operation type of the logical root node
     * @param appSchemaRegistry YANG schema registry of the application
     * @return YDT builder from the tree
     */
    YdtExtendedBuilder getYdtBuilderForYo(List<Object> moduleObject,
                                          String rootNodeName,
                                          String rootNodeNameSpace,
                                          YmsOperationType operationType,
                                          YangSchemaRegistry appSchemaRegistry);

    /**
     * Returns the YDT context after building the tree received from the
     * protocol YNH.
     *
     * @param notificationObject  object of the notification from application
     * @param rootNodeName name of the logical root node
     * @param appSchemaRegistry   YANG schema registry of the application
     * @return YDT context from the tree
     */
    YdtContext getYdtForNotification(Object notificationObject,
                                     String rootNodeName,
                                     YangSchemaRegistry appSchemaRegistry);

    /**
     * Returns the YDT context after building the RPC response tree. The input
     * for building the tree is RPC request workbench, RPC output java object
     * and its logical root node operation type. These are received from the
     * YSB protocol.
     *
     * @param rpcOutputObject     RPC response class object from the application
     * @param rpcRequestWorkBench RPC request workbench from YDT
     * @return YDT builder where RPC response tree is created.
     */
    YdtExtendedBuilder getYdtForRpcResponse(
            Object rpcOutputObject, YangRequestWorkBench rpcRequestWorkBench);
}
