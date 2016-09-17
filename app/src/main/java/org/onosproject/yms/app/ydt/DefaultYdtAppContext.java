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

import org.onosproject.yangutils.datamodel.YangAugment;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeContextInfo;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yangutils.datamodel.exceptions.DataModelException;
import org.onosproject.yms.app.ydt.exceptions.YdtException;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents YANG request work bench which contains all parameters for
 * request handling and methods to build and obtain YANG application data tree
 * which is data (sub)instance representation, abstract of protocol.
 */
public class DefaultYdtAppContext implements YdtAppContext {

    /*
     * Application module(root) node reference.
     */
    private YdtAppContext appRoot;

    /*
     * Parent reference.
     */
    private YdtAppContext parent;

    /*
     * First child reference.
     */
    private YdtAppContext child;

    /*
     * Next sibling reference.
     */
    private YdtAppContext nextSibling;

    /*
     * Previous sibling reference.
     */
    private YdtAppContext previousSibling;

    /*
     * Last child reference.
     */
    private YdtAppContext lastChild;

    /*
     * Reference for application's root ydtContext.
     */
    private YdtContext moduleContext;

    /*
     * Reference for list of nodes with operation type delete.
     */
    private List<YdtContext> deleteNodes = new ArrayList<>();

    /*
     * Reference for schema node of augmenting application.
     */
    private YangSchemaNode augModSchema;

    /*
     * Reference for operation type for application root node.
     */
    private YdtAppNodeOperationType operationType;

    @Override
    public void updateAppOperationType(YdtContextOperationType ydtOpType) {
        YdtAppNodeOperationType opType = getAppOpTypeFromYdtOpType(ydtOpType);
        if (opType == null) {
            return;
        }
        if (operationType == null) {
            operationType = opType;
        } else if (operationType != YdtAppNodeOperationType.BOTH &&
                operationType != opType) {
            operationType = YdtAppNodeOperationType.BOTH;
        }
    }

    @Override
    public YdtAppContext getParent() {
        return parent;
    }

    @Override
    public void setParent(YdtAppContext parent) {
        this.parent = parent;
    }

    @Override
    public YdtAppContext getAppRoot() {
        return appRoot;
    }

    @Override
    public void setAppRoot() {
        appRoot = this;
    }

    @Override
    public YdtAppContext getFirstChild() {
        return child;
    }

    @Override
    public void setChild(YdtAppContext child) {
        this.child = child;
    }

    @Override
    public YdtAppContext getNextSibling() {
        return nextSibling;
    }

    @Override
    public void setNextSibling(YdtAppContext nextSibling) {
        this.nextSibling = nextSibling;
    }

    @Override
    public YdtAppContext getPreviousSibling() {
        return previousSibling;
    }

    @Override
    public void setPreviousSibling(YdtAppContext previousSibling) {
        this.previousSibling = previousSibling;
    }

    @Override
    public YdtAppNodeOperationType getOperationType() {
        return operationType;
    }

    @Override
    public YdtAppNodeOperationType getAppOpTypeFromYdtOpType(
            YdtContextOperationType opType) {
        // Get the app tree operation type.
        switch (opType) {
            case CREATE:
            case MERGE:
            case REPLACE:
                return YdtAppNodeOperationType.OTHER_EDIT;
            case DELETE:
            case REMOVE:
                return YdtAppNodeOperationType.DELETE_ONLY;
            default:
                return null;
            //TODO handle the default data type.
        }
    }

    @Override
    public void setOperationType(YdtAppNodeOperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public List<YdtContext> getDeleteNodes() {
        // This suppose to be mutable for YAB
        return deleteNodes;
    }

    @Override
    public void setDeleteNodes(List<YdtContext> deleteNodes) {
        this.deleteNodes = deleteNodes;
    }

    @Override
    public YdtContext getModuleContext() {
        return moduleContext;
    }

    @Override
    public void setModuleContext(YdtContext moduleContext) {
        this.moduleContext = moduleContext;
    }

    @Override
    public YangSchemaNode getAugmentingModuleSchemaNode() {
        return augModSchema;
    }

    @Override
    public void setAugmentingModuleSchemaNode(
            YangSchemaNode schemaNode) {
        augModSchema = schemaNode;
    }

    @Override
    public YangSchemaNode getAugmentingSchemaNode(
            YangSchemaNodeIdentifier id,
            YangSchemaNodeContextInfo contextInfo) {
        YangSchemaNode lastAugMod = null;
        YangSchemaNode switchedNode =
                contextInfo.getContextSwitchedNode();

        while (switchedNode != null) {
            if (switchedNode instanceof YangAugment) {
                lastAugMod = switchedNode;
            }
            try {
                switchedNode = switchedNode.getChildSchema(id)
                        .getContextSwitchedNode();
            } catch (DataModelException e) {
                throw new YdtException(e.getMessage());
            }
        }
        if (lastAugMod != null) {
            return ((YangNode) lastAugMod).getParent();
        }
        return null;
    }

    @Override
    public YdtAppContext getLastChild() {
        return lastChild;
    }

    @Override
    public void setLastChild(YdtAppContext lastChild) {
        this.lastChild = lastChild;
    }

    @Override
    public void addChild(YdtAppContext newChild) {

        if (newChild.getParent() == null) {
            newChild.setParent(this);
        }

        // First child to be added.
        if (getFirstChild() == null) {
            setChild(newChild);
            // Update last child.
            setLastChild(newChild);
            return;
        }

        // If the new node needs to be add as last child.
        YdtAppContext curNode = getLastChild();
        curNode.setNextSibling(newChild);
        newChild.setPreviousSibling(curNode);
        setLastChild(newChild);
    }
}
