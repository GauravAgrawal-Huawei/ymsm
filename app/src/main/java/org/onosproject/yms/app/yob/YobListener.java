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

package org.onosproject.yms.app.yob;


import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ydt.YdtExtendedListener;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContext;

/**
 * Represents implementation of YANG object builder listener.
 */
public class YobListener implements YdtExtendedListener {

    /**
     * reference to the ydt root node.
     */
    private YdtExtendedContext ydtRootNode;

    public YangSchemaRegistry getSchemaRegistry() {
        return schemaRegistry;
    }

    public void setSchemaRegistry(YangSchemaRegistry schemaRegistry) {
        this.schemaRegistry = schemaRegistry;
    }

    YangSchemaRegistry schemaRegistry;

    /**
     * Returns the ydtRootNode.
     *
     * @return the ydtRootNode
     */
    public YdtExtendedContext getYdtRootNode() {
        return ydtRootNode;
    }

    /**
     * Sets the ydtRootNode.
     *
     * @param ydtRootNode refers to ydt root node which is module node
     */
    public void setYdtRootNode(YdtExtendedContext ydtRootNode) {
        this.ydtRootNode = ydtRootNode;
    }

    /**
     * Creates an instance of YANG object builder listener.
     *
     * @param ydtExtendedContext ydtExtendedContext is used to get application related
     *                           information maintained in YDT
     * @param schemaRegistry
     */
    public YobListener(YdtExtendedContext ydtExtendedContext, YangSchemaRegistry schemaRegistry) {
        setYdtRootNode(ydtExtendedContext);
        setSchemaRegistry(schemaRegistry);
    }

    @Override
    public void enterYdtNode(YdtExtendedContext ydtExtendedContext) {
        YobHandler nodeHandler = YobHandlerFactory.getYobHandlerForContext(ydtExtendedContext);

        if (nodeHandler != null) {
            nodeHandler.createYangBuilderObject(ydtExtendedContext, getYdtRootNode(), getSchemaRegistry());
        }
    }

    @Override
    public void exitYdtNode(YdtExtendedContext ydtExtendedContext) {
        YobHandler nodeHandler = YobHandlerFactory.getYobHandlerForContext(ydtExtendedContext);
        if (nodeHandler != null) {
            nodeHandler.buildObjectFromBuilder(ydtExtendedContext);
            // The current ydt context node and root node are same then return.
            if (!ydtExtendedContext.equals(getYdtRootNode())) {
                nodeHandler.setObjectInParent(ydtExtendedContext);
            }
        }
    }

    @Override
    public void enterYdtNode(YdtContext ydtContext) {
        enterYdtNode((YdtExtendedContext) ydtContext);
    }

    @Override
    public void exitYdtNode(YdtContext ydtContext) {
        exitYdtNode((YdtExtendedContext) ydtContext);
    }
}
