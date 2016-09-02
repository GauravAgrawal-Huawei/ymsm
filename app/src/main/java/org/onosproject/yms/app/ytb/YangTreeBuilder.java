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

import java.util.List;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YmsOperationType;

/**
 * Abstraction of an entity which provides interfaces to build YANG data tree from the object
 * received from YNH, YAB or YSB.
 */
public interface YangTreeBuilder {

    /**
     * Returns the YDT builder after building the tree corresponding to the response YANG object.
     * Input is received from YAB, YSB.
     *
     * @param moduleObject        response class object received from the application
     * @param logicalRootNodeName name of the logical root node
     * @param rootNamespace       namespace of the logical root node
     * @param operationType       operation type of the logical root node
     * @param appSchemaRegistry   YANG schema registry of the application
     * @return YDT builder from the tree
     */
    YdtExtendedBuilder getYdtBuilderForYo(List<Object> moduleObject, String logicalRootNodeName, String rootNamespace,
                                          YmsOperationType operationType, YangSchemaRegistry appSchemaRegistry);

    /**
     * Returns the YDT context after building the tree. Input from YNH is received and processed.
     *
     * @param notificationObject  object of the notification from application
     * @param logicalRootNodeName name of the logical root node
     * @param appSchemaRegistry   YANG schema registry of the application
     * @return YDT context from the tree
     */
    YdtContext getYdtForNotification(Object notificationObject, String logicalRootNodeName,
                                     YangSchemaRegistry appSchemaRegistry);
}
