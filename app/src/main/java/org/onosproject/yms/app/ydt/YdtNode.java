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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.onosproject.yangutils.datamodel.YangList;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeContextInfo;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yangutils.datamodel.exceptions.DataModelException;
import org.onosproject.yms.app.ydt.exceptions.YdtExceptions;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YdtExtendedInfoType;
import org.onosproject.yms.ydt.YdtType;

/**
 * Represents implementation of interfaces to build and obtain YANG data tree
 * which is data (sub)instance representation, abstract of protocol.
 */
public abstract class YdtNode<T> implements YdtExtendedContext, Cloneable {

    /**
     * Parent reference.
     */
    private YdtNode parent;

    /**
     * First child reference.
     */
    private YdtNode child;

    /**
     * Next sibling reference.
     */
    private YdtNode nextSibling;

    /**
     * Previous sibling reference.
     */
    private YdtNode previousSibling;

    /**
     * Last child reference.
     */
    private YdtNode lastChild;

    /**
     * Type of node.
     */
    private YdtType ydtType;

    /**
     * Flag to keep the track of context switch, It will help ydt to to keep track whether ydtApp tree also need to
     * be traversed back to parent or not.
     * if set traverse in YDT app tree.
     * else no need.
     */
    private boolean isContextSwitch;

    /**
     * YDT extended information.
     */
    private T ydtExtendedInfo;

    /**
     * YDT extended information type.
     */
    private YdtExtendedInfoType ydtExtendedInfoType;

    /**
     * Ydt map to keep the track of node added in YDT.
     */
    final Map<YangSchemaNodeIdentifier, List<YdtNode<T>>> ydtNodeMap = new HashMap<>();

    /**
     * Reference for data-model schema node.
     */
    private YangSchemaNode yangSchemaNode;

    /**
     * Reference for ydt node operation type.
     */
    private YdtContextOperationType ydtContextOperationType;

    /**
     * Key object for ydtNodeMap.
     */
    private YangSchemaNodeIdentifier nodeIdentifier;

    /**
     * Ydt map to keep the track of application information object with respective type.
     */
    private final Map<AppType, Object> ydtAppInfoMap = new HashMap<>();


    // TODO YANG schema mapping attribute

    private YdtContext clonedNode;

    /**
     * Prevents creation of YANG node object.
     */
    private YdtNode() {
    }

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
        return this.nodeIdentifier.getName();
    }

    @Override
    public String getNamespace() {
        return this.nodeIdentifier.getNameSpace();
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

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public Set<String> getValueSet() {
        return null;
    }

    @Override
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
    public void setAppInfo(AppType appType, Object object) {
        // Setting the application info map.
        ydtAppInfoMap.put(appType, object);
    }

    @Override
    public YangSchemaNodeContextInfo getSchemaNodeContextInfo(YangSchemaNodeIdentifier nodeIdentifier) {
        try {
            return getYangSchemaNode().getChildSchema(nodeIdentifier);
        } catch (DataModelException e) {
            // Free resources
            freeRestResources();
            throw new YdtExceptions(e.getMessage());
        }
    }

    /**
     * Add value is not valid for non single instance leaf node.
     *
     * @param value value in a single instance node.
     */
    public void addValue(String value) {
        // Free resources
        freeRestResources();
        String errorInfo = "Value cannot be set in non leaf "
                + this.getYdtNodeIdentifier().getName() + " node.";
        throw new YdtExceptions(errorInfo);
    }

    /**
     * Creates the list of key element's of multi instance node.
     * this will not be applicable on leaf and leaf-list node.
     */
    public void createKeyNodeList() {
        // Free resources
        freeRestResources();
        String errorInfo = "List of key cannot be created for leaf and leaf-list "
                + this.getYdtNodeIdentifier().getName() + " node.";
        throw new YdtExceptions(errorInfo);
    }

    /**
     * Add value is not valid for non single instance leaf node.
     * this will be applicable in case of call from SBI so no need to validate the value.
     *
     * @param value value in a single instance node.
     */
    public void addValueWithoutValidation(String value) {
        // Free resources
        freeRestResources();
        String errorInfo = "Value cannot be set in non leaf "
                + this.getYdtNodeIdentifier().getName() + " node.";
        throw new YdtExceptions(errorInfo);
    }

    /**
     * Add value set is not valid for non multi instance leaf node.
     *
     * @param valueSet value set in a multi instance node
     */
    public void addValueSet(Set valueSet) {
        // Free resources
        freeRestResources();
        String errorInfo = "Value cannot be set in non leaf list "
                + this.getYdtNodeIdentifier().getName() + " node.";
        throw new YdtExceptions(errorInfo);
    }

    /**
     * Add value set is not valid for non multi instance leaf node.
     *
     * @param valueSet value set in a multi instance node
     */
    public void addValueSetWithoutValidation(Set valueSet) {
        // Free resources
        freeRestResources();
        String errorInfo = "Value cannot be set in non leaf list "
                + this.getYdtNodeIdentifier().getName() + " node.";
        throw new YdtExceptions(errorInfo);
    }

    /**
     * Default implementation is for multi instance node
     * where duplicate entries are valid .
     */
    void isDuplicateEntriesValid() {
    }

    /**
     * Returns already existing YdtNode in Ydt tree with same nodeIdentifier.
     *
     * @param nodeIdentifier represents a identifier of YANG data tree node.
     * @return context of node.
     */
    public YdtExtendedContext getCollidingChild(YangSchemaNodeIdentifier nodeIdentifier) {

        // Find the key in YDT map for getting the colliding node.
        List<YdtNode<T>> collidingChild = ydtNodeMap.get(nodeIdentifier);

        /**
         * if colliding child exist then process colliding node in respective YDT node type.
         */
        if (collidingChild != null) {
            collidingChild.get(0).isDuplicateEntriesValid();
            return collidingChild.get(0);
        }

        return null;
    }

    /**
     * Creates a specific type of node.
     *
     * @param type           of YDT node
     * @param nodeIdentifier node identifier of the YDT node
     */
    YdtNode(YdtType type, YangSchemaNodeIdentifier nodeIdentifier) {
        setYdtType(type);
        setNodeIdentifier(nodeIdentifier);
    }

    /**
     * Sets the node type.
     *
     * @param ydtType type of YDT attribute
     */
    private void setYdtType(YdtType ydtType) {
        this.ydtType = ydtType;
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

    /**
     * Sets the data-model node reference for of a given node..
     *
     * @param yangSchemaNode YANG data node.
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
        this.lastChild = child;
    }

    /**
     * Get object node identifier.
     *
     * @return node identifier.
     */
    public YangSchemaNodeIdentifier getYdtNodeIdentifier() {
        return this.nodeIdentifier;
    }

    /**
     * Set object node identifier.
     *
     * @param nodeIdentifier node identifier.
     */
    public void setNodeIdentifier(YangSchemaNodeIdentifier nodeIdentifier) {
        this.nodeIdentifier = nodeIdentifier;
    }

    /**
     * Adds a child node, the children sibling list will be sorted based on node
     * type.
     *
     * @param newChild refers to a new child to be added
     * @param isAtomic boolean flag to check whether atomic child to be added or sub-tree
     * @throws YdtExceptions in case of violation of any YDT rule
     */
    public void addChild(YdtContext newChild, boolean isAtomic)
            throws YdtExceptions {

        YdtNode newChildImpl;

        if (!(newChild instanceof YdtNode)) {
            // Free resources
            freeRestResources();
            throw new YdtExceptions("Invalid object.");
        } else {
            newChildImpl = (YdtNode) newChild;
        }

        if (newChildImpl.getParent() == null) {
            newChildImpl.setParent(this);
        } else if (newChildImpl.getParent() != this) {
            // Free resources
            freeRestResources();
            throw new YdtExceptions("Node is already part of a tree");
        }

        if (newChildImpl.getFirstChild() != null && isAtomic) {
            // Free resources
            freeRestResources();
            throw new YdtExceptions("Child to be added is not atomic, it already has a child");
        }

        if (newChildImpl.getNextSibling() != null) {
            // Free resources
            freeRestResources();
            throw new YdtExceptions("Child to be added is not atomic, it already has a next sibling");
        }

        if (newChildImpl.getPreviousSibling() != null) {
            // Free resources
            freeRestResources();
            throw new YdtExceptions("Child to be added is not atomic, it already has a previous sibling");
        }

        // First child to be added.
        if (getFirstChild() == null) {
            setChild(newChildImpl);
            // Update last child.
            setLastChild(newChildImpl);
            return;
        }

        // If the new node needs to be add as last child.
        YdtNode curNode = getLastChild();
        curNode.setNextSibling(newChildImpl);
        newChildImpl.setPreviousSibling(curNode);
        setLastChild(newChildImpl);

    }

    @Override
    public YdtContextOperationType getYdtContextOperationType() {
        return ydtContextOperationType;
    }

    /**
     * Set type of YANG data tree node operation.
     *
     * @param opType type of YANG data tree node operation.
     */
    public void setYdtContextOperationType(YdtContextOperationType opType) {
        this.ydtContextOperationType = opType;
    }

    /**
     * Update YDT map of current context parent node.
     *
     * @param nodeIdentifier object node identifier.
     * @param childNode      ydt node for which map need to be updated.
     */
    @SuppressWarnings("unchecked")
    public void updateYdtMap(YangSchemaNodeIdentifier nodeIdentifier, YdtNode childNode) {
        List<YdtNode<T>> collidingChildList = this.ydtNodeMap.get(nodeIdentifier);
        if (collidingChildList == null) {
            collidingChildList = new ArrayList<>();
            collidingChildList.add(childNode);
        } else {
            collidingChildList.add(childNode);
        }
        this.ydtNodeMap.put(nodeIdentifier, collidingChildList);
    }

    /**
     * Returns the flag for for node if context switch.
     *
     * @return isContextSwitch flag of a node.
     */
    public boolean getContextSwitch() {
        return isContextSwitch;
    }

    /**
     * Sets the flag to keep the track of context switch.
     * If it is set then when YDT get traverToParent then traverse back to parent in YDT application tree.
     */
    public void setContextSwitch() {
        isContextSwitch = true;
    }

    /**
     * Validate all multi Instance inside current context.
     * this is not valid for leaf and leaf-list node.
     */
    public void validateMultiInstanceNode() {

        // Set for checking whether input string is unique or not.
        Set<String> keyStringSet = new HashSet<>();

        // Iterating over values in map and find multi instance node list only.
        for (List<YdtNode<T>> ydtNodeList : this.ydtNodeMap.values()) {

            // Clearing the set.
            keyStringSet.clear();
            if (ydtNodeList.get(0) instanceof YdtMultiInstanceNode) {

                // Storing the number of multiInstance node for number if instance validation.
                int totalInstance = ydtNodeList.size();

                YangList listSchemaNode = (YangList) ydtNodeList.get(0).getYangSchemaNode();
                int minElement;
                int maxElement;
                if (null != listSchemaNode.getMinElements()) {
                    minElement = listSchemaNode.getMinElements().getMinElement();
                    if (totalInstance < minElement) {
                        // Free resources
                        freeRestResources();
                        String errorInfo = "Too few instances of " +
                                listSchemaNode.getName() + ". Expected minimum instance " + minElement + ".";
                        throw new YdtExceptions(errorInfo);
                    }
                }

                if (null != listSchemaNode.getMaxElements()) {
                    maxElement = listSchemaNode.getMaxElements().getMaxElement();
                    if (totalInstance > maxElement) {
                        // Free resources
                        freeRestResources();
                        String errorInfo = "Too many instances of " +
                                listSchemaNode.getName() + ". Expected maximum instance " + maxElement + ".";
                        throw new YdtExceptions(errorInfo);
                    }
                }

                if ((listSchemaNode.isConfig()) && (totalInstance > 1)) {
                    // Iterating over values in ydtNodeList of multiInstanceNode and compare the key string.
                    for (YdtNode ydtNode : ydtNodeList) {
                        if (!keyStringSet.add(((YdtMultiInstanceNode) ydtNode).getCompositeKeyString())) {
                            // Free resources
                            freeRestResources();
                            String errorInfo = "Some of the key elements are not unique in " +
                                    ydtNode.getYdtNodeIdentifier().getName() + ".";
                            throw new YdtExceptions(errorInfo);
                        }
                    }
                }
            }
        }
    }

    /**
     * Walk in whole Ydt Tree and de-reference all the tree node.
     * This will be called only when any exception occurs while processing
     * the node in Ydt tree
     */

    public void freeRestResources() {
        // Traversing to to logical rootNode.

        YdtNode rootNode = this;
        while (null != rootNode.getParent()) {
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
                if (currentNode == rootNode) {
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
     * @param node node to be free
     */
    private static void free(YdtNode node) {
        if (null != node.getParent()) {
            YdtNode parent = node.getParent();
            parent.setChild(null);
            parent.setLastChild(null);
            if (node.getNextSibling() != null) {
                parent.setChild(node.getNextSibling());
            }
        }
        YdtNode parentRef = node.getParent();
        node = new YdtSingleInstanceNode(null);
        node.setYdtType(null);
        node.setParent(parentRef);
    }

    /**
     * Clones the current node contents and create a new node.
     *
     * @return cloned node
     * @throws CloneNotSupportedException clone is not supported by the referred node
     */
    public Object clone() throws CloneNotSupportedException {
        YdtNode clonedNode = (YdtNode) super.clone();
        clonedNode.setPreviousSibling(null);
        clonedNode.setNextSibling(null);
        clonedNode.setParent(null);
        clonedNode.setChild(null);
        clonedNode.setLastChild(null);
        return clonedNode;
    }
}
