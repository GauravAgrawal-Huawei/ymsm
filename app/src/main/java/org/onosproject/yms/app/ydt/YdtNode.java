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

import org.onosproject.yangutils.datamodel.YangList;
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

/**
 * Represents implementation of interfaces to build and obtain YANG data tree
 * which is data (sub)instance representation, abstract of protocol.
 */
public abstract class YdtNode<T> implements YdtExtendedContext, Cloneable {

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
     * Ydt map to keep the track of node added in YDT.
     */
    final Map<YangSchemaNodeIdentifier, List<YdtNode<T>>> ydtNodeMap =
            new HashMap<>();

    /*
     * Reference for data-model schema node.
     */
    private YangSchemaNode yangSchemaNode;

    /*
     * Reference for ydt node operation type.
     */
    private YdtContextOperationType ydtContextOperationType;

    /*
     * Key object for ydtNodeMap.
     */
    private YangSchemaNodeIdentifier id;

    /*
     * Ydt map to keep the track of application information object
     * with respective type.
     */
    private final Map<AppType, Object> ydtAppInfoMap = new HashMap<>();

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
        return id.getName();
    }

    @Override
    public String getNamespace() {
        return id.getNameSpace();
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
        // Setting the application info map.
        ydtAppInfoMap.put(appType, object);
    }

    @Override
    public YangSchemaNodeContextInfo getSchemaNodeContextInfo(
            YangSchemaNodeIdentifier id) {
        try {
            return getYangSchemaNode().getChildSchema(id);
        } catch (DataModelException e) {
            // Free resources
            freeRestResources();
            throw new YdtException(e.getLocalizedMessage());
        }
    }

    /**
     * Adds value is not valid for non single instance leaf node.
     *
     * @param value value in a single instance node
     */
    public void addValue(String value) {
        // Free resources
        freeRestResources();
        throw new YdtException("Value cannot be set in non leaf " +
                                       getYdtNodeIdentifier().getName() +
                                       " node.");
    }

    /**
     * Creates the list of key element's of multi instance node.
     * this will not be applicable on leaf and leaf-list node.
     */
    public void createKeyNodeList() {
        // Free resources
        freeRestResources();
        throw new YdtException("List of key cannot be created for leaf " +
                                       "and leaf-list " +
                                       getYdtNodeIdentifier().getName() +
                                       " node.");
    }

    /**
     * Adds value is not valid for non single instance leaf node.
     * this will be applicable in case of call from SBI so no need
     * to validate the value.
     *
     * @param value value in a single instance node
     */
    public void addValueWithoutValidation(String value) {
        // Free resources
        freeRestResources();
        throw new YdtException("Value cannot be set in non leaf " +
                                       getYdtNodeIdentifier().getName() +
                                       " node.");
    }

    /**
     * Add value set is not valid for non multi instance leaf node.
     *
     * @param valueSet value set in a multi instance node
     */
    public void addValueSet(Set valueSet) {
        // Free resources
        freeRestResources();
        throw new YdtException("Value cannot be set in non leaf list " +
                                       getYdtNodeIdentifier().getName() +
                                       " node.");
    }

    /**
     * Add value set is not valid for non multi instance leaf node.
     *
     * @param valueSet value set in a multi instance node
     */
    public void addValueSetWithoutValidation(Set valueSet) {
        // Free resources
        freeRestResources();
        throw new YdtException("Value cannot be set in non leaf list " +
                                       getYdtNodeIdentifier().getName() +
                                       " node.");
    }

    /**
     * Default implementation is for multi instance node
     * where duplicate entries are valid so processing is required.
     */
    void isDuplicateEntriesValid() {
    }

    /**
     * Returns already existing YdtNode in Ydt tree with same nodeIdentifier.
     *
     * @param id represents a identifier of YANG data tree node
     * @return YDT node
     */
    public YdtNode getCollidingChild(YangSchemaNodeIdentifier id) {

        // Find the key in YDT map for getting the colliding node.
        List<YdtNode<T>> collidingChild = ydtNodeMap.get(id);

        /*
         * if colliding child exist then process colliding node in respective
         * YDT node type.
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
     * @param type of YDT node
     * @param id   node identifier of the YDT node
     */
    YdtNode(YdtType type, YangSchemaNodeIdentifier id) {
        ydtType = type;
        setId(id);
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
        freeRestResources();
        throw new YdtException("Value cannot be invoke from non leaf " +
                                       getYdtNodeIdentifier().getName() +
                                       " node.");
    }

    @Override
    public Set<String> getValueSet() {
        freeRestResources();
        throw new YdtException("ValueSet cannot be invoke from non leaf-list " +
                                       getYdtNodeIdentifier().getName() +
                                       " node.");
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
     * Returns object node identifier.
     *
     * @return node identifier
     */
    public YangSchemaNodeIdentifier getYdtNodeIdentifier() {
        return id;
    }

    /**
     * Sets object node identifier.
     *
     * @param id node identifier
     */
    public void setId(YangSchemaNodeIdentifier id) {
        this.id = id;
    }

    /**
     * Adds a child node, the children sibling list will be sorted based on node
     * type.This will add single child or sub-tree based on isAtomic flag.
     *
     * @param newChild refers to a new child to be added
     * @param isAtomic boolean flag to maintain atomicity of the current node
     * @throws YdtException in case of violation of any YDT rule
     */
    public void addChild(YdtContext newChild, boolean isAtomic)
            throws YdtException {

        YdtNode node;

        if (!(newChild instanceof YdtNode)) {
            // Free resources
            freeRestResources();
            throw new YdtException("Requested node type not supported.");
        } else {
            node = (YdtNode) newChild;
        }

        if (node.getParent() == null) {
            node.setParent(this);
        } else if (!node.getParent().equals(this)) {
            // Free resources
            freeRestResources();
            throw new YdtException("Node is already part of a tree");
        }

        if (node.getFirstChild() != null && isAtomic) {
            // Free resources
            freeRestResources();
            throw new YdtException("Child to be added is not atomic, " +
                                           "it already has a child");
        }

        if (node.getNextSibling() != null) {
            // Free resources
            freeRestResources();
            throw new YdtException("Child to be added is not atomic, " +
                                           "it already has a next sibling");
        }

        if (node.getPreviousSibling() != null) {
            // Free resources
            freeRestResources();
            throw new YdtException("Child to be added is not atomic, " +
                                           "it already has a previous sibling");
        }

        // First child to be added.
        if (getFirstChild() == null) {
            setChild(node);
            // Update last child.
            setLastChild(node);
            return;
        }

        // If the new node needs to be add as last child.
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
     * Sets type of YANG data tree node operation.
     *
     * @param ydtContextOperationType type of YANG data tree node operation
     */
    public void setYdtContextOperationType(YdtContextOperationType ydtContextOperationType) {
        this.ydtContextOperationType = ydtContextOperationType;
    }

    /**
     * Updates YDT map of current context parent node.
     *
     * @param id        object node identifier
     * @param childNode ydt node for which map need to be updated
     */
    @SuppressWarnings("unchecked")
    public void updateYdtMap(YangSchemaNodeIdentifier id,
                             YdtNode childNode) {
        List<YdtNode<T>> collidingChildList = ydtNodeMap
                .get(id);
        if (collidingChildList == null) {
            collidingChildList = new ArrayList<>();
        }
        collidingChildList.add(childNode);
        ydtNodeMap.put(id, collidingChildList);
    }

    /**
     * Returns the flag for for node if context switch.
     *
     * @return isContextSwitch flag of a node
     */
    public boolean getContextSwitch() {
        return isContextSwitch;
    }

    /**
     * Sets the flag to keep the track of context switch.
     * If it is set then when YDT get traverToParent then
     * traverse back to parent in YDT application tree.
     */
    public void setContextSwitch() {
        isContextSwitch = true;
    }

    /**
     * Validates all multi Instance inside current context.
     * this is not valid for leaf and leaf-list node.
     */
    public void validateMultiInstanceNode() {

        // Set for checking whether input string is unique or not.
        Set<String> keyStringSet = new HashSet<>();

        // Iterating over values in map and find multi instance node list only.
        for (List<YdtNode<T>> ydtNodeList : ydtNodeMap.values()) {

            // Clearing the set.
            keyStringSet.clear();
            if (ydtNodeList.get(0) instanceof YdtMultiInstanceNode) {

                // Storing the number of multiInstance node for number
                // if instance validation.
                int instanceCount = ydtNodeList.size();

                YangList list = (YangList) ydtNodeList.get(0)
                        .getYangSchemaNode();
                int minElement;
                int maxElement;
                if (list.getMinElements() != null) {
                    minElement = list.getMinElements()
                            .getMinElement();
                    if (instanceCount < minElement) {
                        // Free resources
                        freeRestResources();
                        throw new YdtException(
                                "Too few instances of " + list.getName() +
                                        ". Expected minimum instance " +
                                        minElement + ".");
                    }
                }

                if (list.getMaxElements() != null) {
                    maxElement = list.getMaxElements()
                            .getMaxElement();
                    if (instanceCount > maxElement) {
                        // Free resources
                        freeRestResources();
                        throw new YdtException(
                                "Too many instances of " + list.getName() +
                                        ". Expected maximum instance " +
                                        maxElement + ".");
                    }
                }

                if (list.isConfig() && instanceCount > 1) {
                    // Iterating over values in ydtNodeList of
                    // multiInstanceNode and compare the key string.
                    for (YdtNode ydtNode : ydtNodeList) {
                        if (!keyStringSet
                                .add(((YdtMultiInstanceNode) ydtNode)
                                             .getCompositeKey())) {
                            // Free resources
                            freeRestResources();
                            throw new YdtException(
                                    "Some of the key elements are not unique " +
                                            "in " + ydtNode
                                            .getYdtNodeIdentifier().getName() +
                                            ".");
                        }
                    }
                }
            }
        }
    }

    /**
     * Walks in whole Ydt Tree and de-reference all the tree node.
     * This will be called only when any exception occurs while processing
     * the node in Ydt tree
     */
    public void freeRestResources() {
        // Traversing to to logical rootNode.

        YdtNode rootNode = this;
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
     * @param node node to be free
     */
    private static void free(YdtNode node) {
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
