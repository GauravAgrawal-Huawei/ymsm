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

package org.onosproject.yms.app.ydt;

import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeContextInfo;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;

import java.util.List;

public interface YdtAppContext {

    /**
     * Returns the context of parent node.
     *
     * @return context of parent node
     */
    YdtAppContext getParent();

    /**
     * Sets the context of parent node.
     *
     * @param parent node
     */
    void setParent(YdtAppContext parent);

    /**
     * Returns the context of application parent node.
     *
     * @return context of application parent node
     */
    YdtAppContext getAppRoot();

    /**
     * Sets the context of application parent node.
     */
    void setAppRoot();

    /**
     * Returns the context of first child.
     *
     * @return context of first child
     */
    YdtAppContext getFirstChild();

    /**
     * Sets the context of first child.
     *
     * @param child node
     */
    void setChild(YdtAppContext child);

    /**
     * Returns the context of last child.
     *
     * @return context of last child
     */
    YdtAppContext getLastChild();

    /**
     * Sets the context of last child.
     *
     * @param child node
     */
    void setLastChild(YdtAppContext child);

    /**
     * Returns the context of next sibling.
     *
     * @return context of next sibling
     */
    YdtAppContext getNextSibling();

    /**
     * Sets the context of next sibling.
     *
     * @param nextSibling node
     */
    void setNextSibling(YdtAppContext nextSibling);

    /**
     * Returns the context of previous sibling.
     *
     * @return context of previous sibling
     */
    YdtAppContext getPreviousSibling();

    /**
     * Sets the context of previous sibling.
     *
     * @param preSibling node
     */
    void setPreviousSibling(YdtAppContext preSibling);

    /**
     * Returns the app tree operation type.
     *
     * @return app tree operation type
     */
    YdtAppNodeOperationType getOperationType();

    /**
     * Returns the app tree operation type with the help of YdtOperation type.
     *
     * @param opType ydt operation type
     * @return app tree operation type
     */
    YdtAppNodeOperationType getAppOpTypeFromYdtOpType(
            YdtContextOperationType opType);

    /**
     * Set the app tree operation type.
     *
     * @param opType app tree operation type
     */
    void setOperationType(YdtAppNodeOperationType opType);

    /**
     * Returns the list of nodes with operation type delete.
     *
     * @return list of nodes with operation type delete
     */
    List<YdtContext> getDeleteNodes();

    /**
     * Sets the list of nodes with operation type delete.
     *
     * @param deleteNodes list of nodes with operation type delete
     */
    void setDeleteNodes(List<YdtContext> deleteNodes);

    /**
     * Returns application's root ydtContext.
     *
     * @return YdtContext of application root node
     */
    YdtContext getModuleContext();

    /**
     * Sets the application's ydtContext.
     *
     * @param moduleNode application's ydtContext
     */
    void setModuleContext(YdtContext moduleNode);

    /**
     * Returns the YangSchemaNode of augmenting application.
     *
     * @return YangSchemaNode of augmenting application
     */
    YangSchemaNode getAugmentingSchemaNode();

    /**
     * Sets the YangSchemaNode of augmenting application root node.
     *
     * @param schemaNode YangSchemaNode of augmenting application module
     */
    void setAugmentingSchemaNode(YangSchemaNode schemaNode);

    /**
     * Adds a last child to ydt application data tree.
     *
     * @param newChild name of child to be added
     */
    void addChild(YdtAppContext newChild);

    /**
     * Returns augmenting node module yang schema node.
     *
     * @param id          schema node identifier
     * @param contextInfo Yang Schema node context info
     *                    which is having YangSchemaNode and
     *                    ContextSwitchedNode
     * @return augmenting node module yang schema node
     */
    YangSchemaNode getAugmentingSchemaNode(
            YangSchemaNodeIdentifier id,
            YangSchemaNodeContextInfo contextInfo);

    /**
     * Update the app tree operation type.
     * <p>
     * If earlier operation type was OTHER_EDIT and now operation type came as
     * DELETE_ONLY or vice-versa, then update operation type to BOTH.
     *
     * @param opType ydt current context operation type
     */
    void updateAppOperationType(YdtContextOperationType opType);
}
