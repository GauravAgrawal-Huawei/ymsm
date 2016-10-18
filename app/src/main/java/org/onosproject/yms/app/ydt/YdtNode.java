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
import org.onosproject.yangutils.datamodel.exceptions.DataModelException;
import org.onosproject.yms.app.ydt.exceptions.YdtException;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YdtExtendedInfoType;
import org.onosproject.yms.ydt.YdtType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.onosproject.yms.app.ydt.YdtConstants.errorMsg;

/**
 * Represents implementation of interfaces to build and obtain YANG data tree
 * which is data (sub)instance representation, abstract of protocol.
 */
public abstract class YdtNode<T> implements YdtExtendedContext, Cloneable {

    // ydt formatted error string
    private static final String FMT_UNI_KEY =
            "Some of the key elements are not unique in %s.";
    private static final String FMT_KLIST_STR =
            "List of key cannot be created for leaf and leaf-list %s node.";
    private static final String FMT_VAL_N =
            "Value cannot be set in non leaf %s node.";
    private static final String FMT_VAL_NS =
            "ValueSet cannot be set in non leaf-list %s node.";
    private static final String FMT_VAL_IN =
            "Value cannot be invoke from non leaf %s node.";
    private static final String FMT_VAL_INS =
            "ValueSet cannot be invoke from non leaf-list %s node";

    // ydt error string
    private static final String E_EXIST = "Node is already part of a tree";
    private static final String E_ATOMIC =
            "Child to be added is not atomic, it already has a child";
    private static final String E_SIB =
            "Child to be added is not atomic, it already has a next sibling";
    private static final String E_PRE =
            "Child to be added is not atomic, it already has a previous " +
                    "sibling";
    private static final String E_SUPPORT = "Requested node type not supported";

    /*
     * Parent reference.
     */
    private YdtNode parent;

    /*
     * First child reference.
     */
    private YdtNode child;

    /*
     * Next sibling reference.
     */
    private YdtNode nextSibling;

    /*
     * Previous sibling reference.
     */
    private YdtNode previousSibling;

    /*
     * Last child reference.
     */
    private YdtNode lastChild;

    /*
     * Type of node.
     */
    private YdtType ydtType;

    /*
     * Flag to keep the track of context switch,
     * if set then traverse back to parent in YDT app tree else no need.
     */
    private boolean isContextSwitch;

    /*
     * YDT extended information.
     */
    private T ydtExtendedInfo;

    /*
     * YDT extended information type.
     */
    private YdtExtendedInfoType ydtExtendedInfoType;

    /*
     * Ydt map to keep the track of node added under current parent node.
     */
    final Map<YangSchemaNodeIdentifier, YdtNode<T>> ydtNodeMap =
            new HashMap<>();

    /*
     * Ydt map to keep the track of multi instance node added under current
     * parent node.
     */
    final Map<YangSchemaNodeIdentifier, List<YdtNode<YdtMultiInstanceNode>>>
            ydtMultiInsMap = new HashMap<>();

    /*
     * Reference for data-model schema node.
     */
    private YangSchemaNode yangSchemaNode;

    /*
     * Reference for ydt node operation type.
     */
    private YdtContextOperationType ydtContextOperationType;

//    /*
//     * Key object for ydtNodeMap.
//     */
//    private YangSchemaNodeIdentifier id;

    /*
     * Ydt map to keep the track of application information object
     * with respective type.
     */
    private final Map<AppType, Object> ydtAppInfoMap = new HashMap<>();

    private YdtContext clonedNode;

    /**
     * Returns the cloned ydt node.
     *
     * @return clonedNode cloned ydt node
     */
    public YdtContext getClonedNode() {
        return clonedNode;
    }

    /**
     * Sets the cloned node.
     *
     * @param clonedNode cloned ydt node
     */
    public void setClonedNode(YdtContext clonedNode) {
        this.clonedNode = clonedNode;
    }

    @Override
    public String getName() {
        return yangSchemaNode.getName();
    }

    @Override
    public String getNamespace() {
        return yangSchemaNode.getNameSpace().getModuleNamespace();
    }

    @Override
    public String getModuleNameAsNameSpace() {
        return yangSchemaNode.getNameSpace().getModuleName();
    }

    @Override
    public <T> T getYdtContextExtendedInfo() {
        return (T) ydtExtendedInfo;
    }

    @Override
    public YdtExtendedInfoType getYdtExtendedInfoType() {
        return ydtExtendedInfoType;
    }

    @Override
    public YdtType getYdtType() {
        return ydtType;
    }

    @Override
    public YdtNode getParent() {
        return parent;
    }

    @Override
    public YdtNode getFirstChild() {
        return child;
    }

    @Override
    public YdtNode getNextSibling() {
        return nextSibling;
    }

    public YangSchemaNode getYangSchemaNode() {
        return yangSchemaNode;
    }

    @Override
    public YdtNode getLastChild() {
        return lastChild;
    }

    @Override
    public Object getAppInfo(AppType appType) {
        return ydtAppInfoMap.get(appType);
    }

    @Override
    public void addAppInfo(AppType appType, Object object) {
        ydtAppInfoMap.put(appType, object);
    }

    /**
     * Returns child schema node context information. It is used by YMS to
     * obtain the child schema corresponding to data node identifier.
     *
     * @param id represents a identifier of YANG data tree node
     * @return YANG data node context information
     */
    public YangSchemaNodeContextInfo getSchemaNodeContextInfo(
            YangSchemaNodeIdentifier id) {
        try {
            return getYangSchemaNode().getChildSchema(id);
        } catch (DataModelException e) {
            errorHandler(e.getLocalizedMessage(), this);
        }
        return null;
    }

    /**
     * Adds the given value to the non single instance leaf node.
     * <p>
     * This default implementation throws an exception stating that
     * the value cannot be added. Subclasses may override this method
     * to provide the correct behavior for their specific implementation.
     *
     * @param value value in a single instance node
     */
    public void addValue(String value) {
        errorHandler(
                errorMsg(FMT_VAL_N, getName()), this);
    }

    /**
     * Creates the list of key element's of multi instance node.
     * this will not be applicable on leaf and leaf-list node.
     */
    public void createKeyNodeList() {
        errorHandler(errorMsg(
                FMT_KLIST_STR, getName()), this);
    }

    /**
     * Adds the given value to the non single instance leaf node.
     * <p>
     * This default implementation throws an exception stating that
     * the value cannot be added. Subclasses may override this method
     * to provide the correct behavior for their specific implementation.
     * This will be applicable in case of call from SBI so no need
     * to validate the value.
     *
     * @param value value in a single instance leaf node
     */
    public void addValueWithoutValidation(String value) {
        errorHandler(
                errorMsg(FMT_VAL_N, getName()), this);
    }

    /**
     * Adds the given valueSet to the non multi instance leaf node.
     * <p>
     * This default implementation throws an exception stating that
     * the value cannot be added. Subclasses may override this method
     * to provide the correct behavior for their specific implementation.
     *
     * @param valueSet valueSet in a multi instance leaf node
     */
    public void addValueSet(Set<String> valueSet) {
        errorHandler(
                errorMsg(FMT_VAL_NS, getName()), this);
    }

    /**
     * Adds the given valueSet to the non multi instance leaf node.
     * <p>
     * This default implementation throws an exception stating that
     * the value cannot be added. Subclasses may override this method
     * to provide the correct behavior for their specific implementation.
     * This will be applicable in case of call from SBI so no need
     * to validate the value.
     *
     * @param valueSet valueSet in a multi instance leaf node
     */
    public void addValueSetWithoutValidation(Set<String> valueSet) {
        errorHandler(
                errorMsg(FMT_VAL_NS, getName()), this);
    }

    /**
     * Validates requested node allowed to have duplicate entry or not.
     * <p>
     * This default implementation throws an exception stating that
     * the duplicate entry found. Subclasses may override this method
     * to provide the correct behavior for their specific implementation.
     */
    public void validDuplicateEntryProcessing() {
    }

    /**
     * Returns already existing YdtNode in Ydt tree with same nodeIdentifier.
     *
     * @param id represents a identifier of YANG data tree node
     * @return YDT node
     */
    public YdtNode getCollidingChild(YangSchemaNodeIdentifier id) {

        // Find the key in YDT map for getting the colliding node.
        YdtNode collidingChild = ydtNodeMap.get(id);

        /*
         * If colliding child exist then process colliding node in respective
         * YDT node type.
         */
        if (collidingChild != null) {
            collidingChild.validDuplicateEntryProcessing();
            return collidingChild;
        }

        return null;
    }

    /**
     * Creates a specific type of node.
     *
     * @param type of YDT node
     * @param node schema node
     */
    YdtNode(YdtType type, YangSchemaNode node) {
        ydtType = type;
        yangSchemaNode = node;
    }

    /**
     * Creates a specific type of node.
     *
     * @param type of YDT node
     */
    YdtNode(YdtType type) {
        ydtType = type;
    }

    /**
     * Sets the parent of node.
     *
     * @param parent node
     */
    public void setParent(YdtNode parent) {
        this.parent = parent;
    }

    /**
     * Sets the first instance of a child node.
     *
     * @param child is only child to be set
     */
    public void setChild(YdtNode child) {
        this.child = child;
    }

    /**
     * Sets the next sibling of node.
     *
     * @param sibling YANG node
     */
    public void setNextSibling(YdtNode sibling) {
        nextSibling = sibling;
    }

    /**
     * Returns the previous sibling of a node.
     *
     * @return previous sibling of a node
     */
    public YdtNode getPreviousSibling() {
        return previousSibling;
    }

    /**
     * Sets the previous sibling.
     *
     * @param previousSibling points to predecessor sibling
     */
    public void setPreviousSibling(YdtNode previousSibling) {
        this.previousSibling = previousSibling;
    }

    @Override
    public String getValue() {
        errorHandler(
                errorMsg(FMT_VAL_IN, getName()), this);
        return null;
    }

    @Override
    public Set<String> getValueSet() {
        errorHandler(
                errorMsg(FMT_VAL_INS, getName()), this);
        return null;
    }

    /**
     * Sets the data-model node reference for of a given node.
     *
     * @param yangSchemaNode YANG data node
     */
    public void setYangSchemaNode(YangSchemaNode yangSchemaNode) {
        this.yangSchemaNode = yangSchemaNode;
    }

    /**
     * Sets the last instance of a child node.
     *
     * @param child is last child to be set
     */
    public void setLastChild(YdtNode child) {
        lastChild = child;
    }


    /**
     * Adds a child node.
     * The children sibling list will be sorted based on node
     * type. This will add single child or sub-tree based on isAtomic flag.
     *
     * @param newChild refers to a new child to be added
     * @param isAtomic boolean flag to maintain atomicity of the current node
     * @throws YdtException in case of violation of any YDT rule
     */
    public void addChild(YdtContext newChild, boolean isAtomic)
            throws YdtException {

        if (!(newChild instanceof YdtNode)) {
            errorHandler(errorMsg(E_SUPPORT), this);
        }

        YdtNode node = (YdtNode) newChild;

        if (node.getParent() == null) {
            node.setParent(this);
        } else if (!node.getParent().equals(this)) {
            errorHandler(errorMsg(E_EXIST), this);
        }

        if (node.getFirstChild() != null && isAtomic) {
            errorHandler(errorMsg(E_ATOMIC), this);
        }

        if (node.getNextSibling() != null) {
            errorHandler(errorMsg(E_SIB), this);
        }

        if (node.getPreviousSibling() != null) {
            errorHandler(errorMsg(E_PRE), this);
        }

        // If new node needs to be added as first child.
        if (getFirstChild() == null) {
            setChild(node);
            setLastChild(node);
            return;
        }

        // If new node needs to be added as last child.
        YdtNode curNode = getLastChild();
        curNode.setNextSibling(node);
        node.setPreviousSibling(curNode);
        setLastChild(node);
    }

    @Override
    public YdtContextOperationType getYdtContextOperationType() {
        return ydtContextOperationType;
    }

    /**
     * Sets type of yang data tree node operation.
     *
     * @param opType type of yang data tree node operation
     */
    public void setYdtContextOperationType(YdtContextOperationType opType) {
        ydtContextOperationType = opType;
    }

    /**
     * Updates ydt maps of current context parent node.
     *
     * @param node ydt node for which map need to be updated
     */
    void updateYdtMap(YdtNode node) {

        YangSchemaNodeIdentifier id = node.getYangSchemaNode()
                .getYangSchemaNodeIdentifier();
        /*
         * If node to be added is of type multi instance node(list) then multi
         * instance node to be updated
         */
        if (node.getYdtType() == YdtType.MULTI_INSTANCE_NODE) {
            updateMultiInsMap(id, node);
        }

        /*
         * If entry for multi instance node is already there with same id then
         * existing entry will be overwritten by the new entry.
         */
        ydtNodeMap.put(id, node);
    }

    /**
     * Updates ydt multi instance map of current context parent node.
     *
     * @param id   object node identifier
     * @param node ydt node for which map need to be updated
     */
    private void updateMultiInsMap(YangSchemaNodeIdentifier id, YdtNode node) {

        List<YdtNode<YdtMultiInstanceNode>> list = ydtMultiInsMap.get(id);
        if (list == null) {
            list = new ArrayList<>();
            ydtMultiInsMap.put(id, list);
        }
        list.add(node);
    }

    /**
     * Returns the flag for node if context switch.
     *
     * @return isContextSwitch flag of a node
     */
    public boolean getAppContextSwitch() {
        return isContextSwitch;
    }

    /**
     * Sets the flag to keep the track of context switch.
     * If it is set then when YDT get traverToParent then
     * traverse back to parent in YDT application tree.
     */
    public void setAppContextSwitch() {
        isContextSwitch = true;
    }

    /**
     * Validates all multi Instance nodes inside current context.
     */
    public void validateMultiInstanceNode() {

        // Set for checking whether input string is unique or not.
        Set<String> keyStringSet = new HashSet<>();

        if (ydtMultiInsMap.size() != 0) {
            /*
             * Iterating over values in map and find multi instance node list
             * only.
             */
            for (List<YdtNode<YdtMultiInstanceNode>> ydtNodeList :
                    ydtMultiInsMap.values()) {
                ydtNodeList.get(0).validateInstances(keyStringSet, ydtNodeList);
            }
        }
    }

    /**
     * Validates the given list of instances by verifying the allowed
     * instance count and key element uniqueness.
     * <p>
     * This default implementation do nothing if requested node is of type
     * other then multiInstanceNode. Subclasses may override this method
     * to provide the correct behavior for their specific implementation.
     *
     * @param keyStringSet set to validate the key element uniqueness
     * @param ydtNodeList  list of instance's of same list
     */
    protected void validateInstances(Set<String> keyStringSet,
                                     List<YdtNode<YdtMultiInstanceNode>>
                                             ydtNodeList) {

    }

    /**
     * Walks in whole Ydt Tree and de-reference all the tree node.
     * This will be called only when any exception occurs while processing
     * the node in Ydt tree.
     *
     * @param node ydt node
     */
    public void freeRestResources(YdtNode node) {
        // Traversing to logical rootNode.
        YdtNode rootNode = node;
        while (rootNode.getParent() != null) {
            rootNode = rootNode.getParent();
        }
        YdtNode currentNode = rootNode;
        while (currentNode != null) {

            // Move down to first child
            YdtNode nextNode = currentNode.getFirstChild();
            if (nextNode != null) {
                currentNode = nextNode;
                continue;
            }

            // No child nodes, so walk tree
            while (currentNode != null) {
                // To keep the track of last sibling.
                YdtNode lastSibling = currentNode;

                // Move to sibling if possible.
                nextNode = currentNode.getNextSibling();

                // free currentNode resources
                free(lastSibling);

                lastSibling.getNamespace();
                if (nextNode != null) {
                    currentNode = nextNode;
                    break;
                }

                // Move up
                if (currentNode.equals(rootNode)) {
                    currentNode = null;
                } else {
                    currentNode = currentNode.getParent();
                    lastSibling.setParent(null);
                }
            }
        }
    }

    /**
     * Free the give YDT node by de-referencing it to null.
     *
     * @param node node to be freed
     */
    private void free(YdtNode node) {
        if (node.getParent() != null) {
            YdtNode parent = node.getParent();
            parent.setChild(null);
            parent.setLastChild(null);
            if (node.getNextSibling() != null) {
                parent.setChild(node.getNextSibling());
            }
        }
        YdtNode parentRef = node.getParent();
        node = new YdtSingleInstanceNode(null);
        node.ydtType = null;
        node.setParent(parentRef);
    }

    /**
     * Clones the current node contents and create a new node.
     *
     * @return cloned node
     * @throws CloneNotSupportedException clone is not supported
     *                                    by the referred node
     */
    public YdtNode clone() throws CloneNotSupportedException {
        YdtNode clonedNode = (YdtNode) super.clone();
        clonedNode.setPreviousSibling(null);
        clonedNode.setNextSibling(null);
        clonedNode.setParent(null);
        clonedNode.setChild(null);
        clonedNode.setLastChild(null);
        return clonedNode;
    }

    /**
     * Handles an error scenario, freeing allocated resources for the given YTD
     * node before throwing an exception with the specified error message.
     *
     * @param error   error message
     * @param curNode ydt node
     * @throws YdtException with the specified error message
     */
    public void errorHandler(String error, YdtNode curNode) {
        //TODO free resources in exception scenario
//        curNode.freeRestResources(curNode);
        throw new YdtException(error);
    }
}
