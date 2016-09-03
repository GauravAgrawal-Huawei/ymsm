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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.onosproject.yangutils.datamodel.YangList;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeContextInfo;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yms.app.ydt.exceptions.YdtExceptions;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YdtType;
import org.onosproject.yms.ydt.YmsOperationType;


/**
 * Represents YANG request work bench which contains all parameters for
 * request handling and methods to build and obtain YANG data tree
 * which is data (sub)instance representation, abstract of protocol.
 */
public class YangRequestWorkBench
        implements YdtExtendedBuilder {

    /**
     * Current node in YANG data tree, kept to maintain the current context in
     * YDT.
     */
    private YdtNode curNode;

    /**
     * Root node in YANG data tree, kept to maintain the root context in
     * YDT.
     */
    private YdtNode rootNode;

    /**
     * Current node in YANG data tree, kept to maintain the current context
     * in ydt application tree.
     */
    private YdtAppContext curAppNode;

    /**
     * Root node in YANG data tree, kept to maintain the root context in ydt
     * application tree.
     */
    private YdtAppContext rootAppNode;

    /**
     * Root Node Tag attribute in YANG data tree, kept to maintain the root
     * tag attributes in
     * YDT.
     * First key param of map represent tagName  name of tag attribute.
     * Second param of map represent tagValue value of tag attribute
     */
    private Map<String, String> rootTagAttributeMap;

    /**
     * YANG schema registry reference.
     */
    private final YangSchemaRegistry yangSchemaRegistry;

    /**
     * YMS operation type.
     */
    private final YmsOperationType ymsOperationType;

    /**
     * YDT default operation type.
     */
    private YdtContextOperationType ydtContextDefaultOpType;


    /**
     * Creates an instance of YangRequestWorkBench which is use to initialize
     * logical rootNode and and schema registry.
     *
     * @param logicalRootName      name of logical container of a protocol
     *                             which is a
     *                             holder of the complete tree
     * @param rootNamespace        namespace of logical container
     * @param operationType        type of operation done by using YANG
     *                             interface.
     * @param schemaRegistry       YMS schema registry
     * @param enableDataValidation Flag to identify validation need to be
     *                             done by YDT or not
     */
    public YangRequestWorkBench(String logicalRootName, String rootNamespace,
                                YmsOperationType operationType,
                                YangSchemaRegistry schemaRegistry,
                                boolean enableDataValidation) {
        YdtNode newNode;
        YangSchemaNodeIdentifier nodeIdentifier =
                new YangSchemaNodeIdentifier();
        nodeIdentifier.setName(logicalRootName);
        nodeIdentifier.setNameSpace(rootNamespace);
        newNode = new YdtSingleInstanceNode(nodeIdentifier);
        setRootNode(newNode);
        yangSchemaRegistry = schemaRegistry;
        ymsOperationType = operationType;

        // Set the logical root node for yang data app tree.
        rootAppNode = new DefaultYdtAppContext();
        rootAppNode.setModuleNode(newNode);
        setAppRootNode(rootAppNode);
    }

    /**
     * Set the logical root context information available in YDT node.
     *
     * @param rootNode refers to root node
     */
    private void setRootNode(YdtNode rootNode) {
        // Setting the root node
        this.rootNode = rootNode;
        curNode = this.rootNode;
    }

    /**
     * Set the app context tree logical root node  for ydt application tree.
     *
     * @param rootAppNode refers to logical root node
     */
    private void setAppRootNode(YdtAppContext rootAppNode) {
        this.rootAppNode = rootAppNode;
        curAppNode = rootAppNode;
    }

    /**
     * Get the app context tree root node  for ydt application tree.
     *
     * @return YdtAppContext refers to root node of ydt application tree.
     */
    public YdtAppContext getAppRootNode() {
        return rootAppNode;
    }

    /**
     * Get the Data model tree for given node.
     *
     * @param nodeIdentifier Represents a identifier of YANG data tree node
     * @return YANG data tree node
     */
    private YdtNode moduleHandler(YangSchemaNodeIdentifier nodeIdentifier) {

        YangSchemaNode node = yangSchemaRegistry
                .getYangSchemaNodeUsingSchemaName(nodeIdentifier.getName());
        if (node == null) {
            // Free resources
            curNode.freeRestResources();
            String errorInfo =
                    "Schema node with name " + nodeIdentifier.getName() +
                            " doesn't exist.";
            throw new YdtExceptions(errorInfo);
        }
        YdtNode newNode = new YdtSingleInstanceNode(nodeIdentifier);
        newNode.setYangSchemaNode(node);
        return newNode;
    }

    @Override
    public void setRootTagAttributeMap(Map<String, String> attributeTag) {
        rootTagAttributeMap = attributeTag;
    }

    @Override
    public Map<String, String> getRootTagAttributeMap() {
        return rootTagAttributeMap;
    }

    @Override
    public void addChild(String name, String namespace) {
        addChild(name, namespace, RequestedCardinality.UNKNOWN, null, RequestedCallType.OTHER);
    }

    @Override
    public void addChild(String name, String namespace, YdtType ydtType) {
        // Checking the YdtType and processing it accordingly.
        if (ydtType == YdtType.MULTI_INSTANCE_NODE) {
            addChild(name, namespace, RequestedCardinality.MULTI_INSTANCE, null,
                     RequestedCallType.OTHER);
        } else {
            addChild(name, namespace, RequestedCardinality.SINGLE_INSTANCE,
                     null, RequestedCallType.OTHER);
        }
    }

    @Override
    public void addChild(String name, String namespace,
                         YdtContextOperationType opType) {
        addChild(name, namespace, RequestedCardinality.UNKNOWN, opType, RequestedCallType.OTHER);

    }

    @Override
    public void addChild(String name, String namespace, YdtType ydtType,
                         YdtContextOperationType opType) {
        // Checking the YdtType and processing it accordingly.
        if (ydtType == YdtType.MULTI_INSTANCE_NODE) {
            addChild(name, namespace, RequestedCardinality.MULTI_INSTANCE,
                     opType, RequestedCallType.OTHER);
        } else {
            addChild(name, namespace, RequestedCardinality.SINGLE_INSTANCE,
                     opType, RequestedCallType.OTHER);
        }
    }


    /**
     * Adds a last child to YANG data tree, this method is to be used by all
     * protocols internally which are aware or unaware of the nature
     * (single/multiple) of node.
     *
     * @param name                 name of child to be added
     * @param namespace            namespace of child to be added, if it's
     *                             null, parent's
     *                             namespace will be applied to child
     * @param requestedCardinality type of YANG data tree node operation
     * @param opType               type of requested operation over a node
     * @param callType             call type to identify the its a leaf node or other node
     */
    private void addChild(String name, String namespace,
                          RequestedCardinality requestedCardinality,
                          YdtContextOperationType opType, RequestedCallType callType) {

        YdtNode childNode;
        boolean isContextSwitch = false;
        YangSchemaNode yangSchemaNode = null;
        YangSchemaNodeContextInfo childSchemaNodeInfo;
        YangSchemaNode lastAugmentingModuleNode = null;
        Boolean isLeaf = true;

        YangSchemaNodeIdentifier nodeIdentifier =
                new YangSchemaNodeIdentifier();
        nodeIdentifier.setName(name);

        // Reference for parent node operation type.
        YdtContextOperationType parentOpType =
                curNode.getYdtContextOperationType();

        if (callType != RequestedCallType.LEAF) {
            if ((opType != null) && (parentOpType != null)) {
                // Get operation type validate
                getOperationTypeValidate(parentOpType, opType);
            } else if (opType == null) {
                /**
                 * Checking the default operation type
                 * If default operation type
                 * is not set, merge will be taken as default operation type.
                 */
                if (ydtContextDefaultOpType != null) {
                    opType = ydtContextDefaultOpType;
                } else {
                    opType = YdtContextOperationType.MERGE;
                }

            }
        }

        // Module/sub-module node handler.
        if (curNode == rootNode) {
            childNode = moduleHandler(nodeIdentifier);
            nodeIdentifier
                    .setNameSpace(childNode.getYangSchemaNode().getNameSpace());
            /**
             * Setting context switch flag for root node
             * So when another module comes then it will help ydt to to keep
             * track whether ydtApp tree also need to
             * be traversed back to parent or not.
             */

        } else {

            // If namespace given by user null, then take namespace from parent.
            if (namespace == null) {
                namespace = curNode.getYangSchemaNode()
                        .getYangSchemaNodeIdentifier().getNameSpace();
            }

            nodeIdentifier.setNameSpace(namespace);

            // Get the already exiting YDT node in YDT tree with same
            // nodeIdentifier.
            childNode = (YdtNode) curNode.getCollidingChild(nodeIdentifier);

            /*
             * If colliding child doesn't exist ,
             * then query yang data model for schema for given node.
             */
            if (childNode == null) {

                // Get Yang Schema node context info which is having
                // YangSchemaNode + ContextSwitchedNode.
                childSchemaNodeInfo =
                        curNode.getSchemaNodeContextInfo(nodeIdentifier);

                if (childSchemaNodeInfo.getContextSwitchedNode() != null) {
                    lastAugmentingModuleNode =
                            curAppNode.getAugmentingSchemaNode(nodeIdentifier,
                                                               childSchemaNodeInfo);
                    if (lastAugmentingModuleNode != null) {
                        if (curAppNode
                                .addYdtAppTreeSet(lastAugmentingModuleNode)) {
                            isContextSwitch = true;
                        }
                    }
                }
                yangSchemaNode = childSchemaNodeInfo.getSchemaNode();

            } else {
                /*
                * If colliding child exist , it will be leaf-list or list
                * then take yang data node information from colliding child.
                */


                if ((childNode.getYdtType() != YdtType.MULTI_INSTANCE_NODE)) {
                    curNode = childNode;
                    return;
                }

                yangSchemaNode = childNode.getYangSchemaNode();
            }

            // Get the ydtNode of respective type.
            childNode = YdtNodeFactory.getNode(nodeIdentifier, yangSchemaNode,
                                               requestedCardinality, callType);
        }

        // Update node identifier.
        childNode.setNodeIdentifier(nodeIdentifier);

        YdtType nodeType = childNode.getYdtType();
        if ((nodeType != YdtType.MULTI_INSTANCE_LEAF_VALUE_NODE)
                && (nodeType != YdtType.SINGLE_INSTANCE_LEAF_VALUE_NODE)) {
            // Update operation type.
            isLeaf = false;
            childNode.setYdtContextOperationType(opType);
        }

        // Add child.
        curNode.addChild(childNode, true);

        // Update parent ydt node map.
        curNode.updateYdtMap(nodeIdentifier, childNode);

        /**
         * Create entry of module node in ydt app tree.
         * And if context switch happened then add entry for same node in the
         * ydt application tree.
         */

        if (isLeaf) {
            opType = curNode.getYdtContextOperationType();
        }

        if ((curNode == rootNode) || (isContextSwitch)) {
            // Add application context switched child in ydt App tree.
            addChildInAppTree(childNode, lastAugmentingModuleNode, opType,
                              isContextSwitch);
        }

        // Update app tree module node operation.
        curAppNode.getApplicationParent().updateAppOperationType(opType);

        // Updating the delete operation list in app tree.
        updateDeleteOperationList(childNode);

        // Updating the curNode.
        curNode = childNode;

    }

    /**
     * Add the given ydt node (with operation type delete/remove)in delete list.
     *
     * @param childNode ydt node
     */
    private void updateDeleteOperationList(YdtNode childNode) {
        if ((childNode.getYdtContextOperationType() ==
                YdtContextOperationType.DELETE) ||
                (childNode.getYdtContextOperationType() ==
                        YdtContextOperationType.REMOVE)) {

            List<YdtContext> deleteNodeList =
                    curAppNode.getApplicationParent().getDeleteNodes();
            // Add childNode with delete operation in deleteList.
            deleteNodeList.add(childNode);
            rootAppNode.setDeleteNodes(deleteNodeList);
        }
    }

    /**
     * Adds a last child to YANG app data tree.this method is to be used
     * internally by
     * other yang tools components.
     *
     * @param childNode                node to be added in tree
     * @param lastAugmentingModuleNode last augmenting module node
     * @param childOpType              operation type of node
     * @param isContextSwitch          to distinguish the call whether it
     *                                 module node call all its subNode call
     */
    private void addChildInAppTree(YdtNode childNode,
                                   YangSchemaNode lastAugmentingModuleNode,
                                   YdtContextOperationType childOpType,
                                   boolean isContextSwitch) {

        YdtAppNodeOperationType ydtAppNodeOperationType;

        DefaultYdtAppContext defaultYdtAppContext = new DefaultYdtAppContext();
        // Add context switched child in ydt App tree.
        curAppNode.addChild(defaultYdtAppContext);
        //Updating the curNode.
        curAppNode = defaultYdtAppContext;

        // Get the app tree operation type from ydt operation type.
        ydtAppNodeOperationType =
                curAppNode.getAppOpTypeFromYdtOpType(childOpType);

        if (isContextSwitch) {
            curAppNode.setAugmentingModuleSchemaNode(lastAugmentingModuleNode);

        } else {

            // If it is application rootNode then set the ydt application
            // root node.
            curAppNode.setModuleNode(childNode);

            // Setting application root node to maintain the delete node list.
            curAppNode.setApplicationParent(curAppNode);

            // Set the app tree operation type.
            curAppNode.setOperationType(ydtAppNodeOperationType);
        }

        // Updating context switch flag for YdtNode
        childNode.setContextSwitch();
    }


    /**
     * Validate the various combination of operation type.
     * If the "operation" attribute is not specified,
     * then the operation applied to the parent data node of the configuration
     * is used.If no parent data node is available, then the
     * 'default-operation'value is used.
     *
     * @param parentOpType Reference for parent node operation type
     * @param childOpType  type of YANG data tree node operation
     */
    private void getOperationTypeValidate(YdtContextOperationType parentOpType,
                                          YdtContextOperationType childOpType) {

        switch (parentOpType) {
            case CREATE:
                // inside the create operation delete operation should not come.
                if (childOpType == YdtContextOperationType.DELETE) {
                    // Free resources
                    curNode.freeRestResources();
                    throw new YdtExceptions(
                            "Create request is not allowed under delete " +
                                    "operation.");
                }
            case DELETE:
                // inside the delete operation create operation should not come.
                if (childOpType == YdtContextOperationType.CREATE) {
                    // Free resources
                    curNode.freeRestResources();
                    throw new YdtExceptions(
                            "Delete request is not allowed under create " +
                                    "operation.");
                }

            default:

                //TODO check all possible scenario.
        }
    }


    @Override
    public void addLeaf(String name, String namespace, String value) {
        addLeaf(name, namespace, value, null, RequestedCardinality.UNKNOWN);
    }

    @Override
    public void addLeaf(String name, String namespace, Set<String> valueSet) {
        addLeaf(name, namespace, null, valueSet,
                RequestedCardinality.MULTI_INSTANCE_LEAF);
    }


    /**
     * Adds a last leaf with list of values to YANG data tree. This method is
     * used by all protocols which knows the nature (single/multiple) or not.
     * Value of leaf can be null which indicates selection node in get
     * operation.
     *
     * @param name                 name of child to be added
     * @param namespace            namespace of child to be added, if it's
     *                             null, parent's
     *                             namespace will be applied to child
     * @param value                value of the child
     * @param valueSet             list of value of the child
     * @param requestedCardinality type of YANG data tree node operation.
     */

    private void addLeaf(String name, String namespace, String value,
                         Set<String> valueSet,
                         RequestedCardinality requestedCardinality) {
        addChild(name, namespace, requestedCardinality, null, RequestedCallType.LEAF);

        // After successful addition of child node updating the values in same.
        if (value != null) {
            curNode.addValue(value);
        } else {
            curNode.addValueSet(valueSet);
        }

    }

    @Override
    public void traverseToParent() {
        // If node is of multiInstanceNode type then check key uniqueness.
        if (curNode.getYdtType() == YdtType.MULTI_INSTANCE_NODE) {
            curNode.createKeyNodeList();
        }

        /**
         * Check application switch for curNode
         * if set, then traverseToParent in YDT application tree.
         */
        if ((curNode.getParent() == rootNode) || (curNode.getContextSwitch())) {
            traverseToParentYdtAppTree();
        }

        /**
         * Validate all multi Instance inside current context.
         * this is not valid for leaf and leaf-list node.
         */
        if ((curNode instanceof YdtMultiInstanceNode)
                || (curNode instanceof YdtSingleInstanceNode)) {
            curNode.validateMultiInstanceNode();
        }

        curNode = curNode.getParent();
    }

    /**
     * Traverses up in YANG application tree to the parent node,
     * this will be used when Ydt current context switch flag is set.
     */
    private void traverseToParentYdtAppTree() {
        curAppNode = curAppNode.getParent();
    }

    @Override
    public YdtContext getCurNode() {
        return curNode;
    }

    @Override
    public void setDefaultEditOperationType(
            YdtContextOperationType ydtContextOperationType) {
        ydtContextDefaultOpType = ydtContextOperationType;
    }


    @Override
    public YdtExtendedContext getRootNode() {
        return rootNode;
    }

    @Override
    public YmsOperationType getYmsOperationType() {
        return ymsOperationType;
    }

    @Override
    public void addMultiInstanceChild(String name, String namespace,
                                      List<String> keysValueList) {
        addChild(name, namespace, RequestedCardinality.UNKNOWN, null, RequestedCallType.UNKNOWN);
        int userInputSize = keysValueList.size();
        int actualSize;
        if (curNode.getYdtType() == YdtType.MULTI_INSTANCE_LEAF_VALUE_NODE) {
            // After successful addition of child node updating the values in same.
            userInputSize = curNode.getValueSet().size() + userInputSize;
//            checkElementCount(actualSize, userInputSize);
//            TODO check the element count
            for (String value : keysValueList) {
                curNode.addValue(value);
            }
        } else {
            YangList yangListHolder = (YangList) curNode.getYangSchemaNode();
            List<String> schemaKeyList = yangListHolder.getKeyList();
            actualSize = schemaKeyList.size();
            checkElementCount(actualSize, userInputSize);

            // Iterator for user given key value.
            Iterator<String> it1 = keysValueList.iterator();
            // Iterator for schema key name list.
            Iterator<String> it2 = schemaKeyList.iterator();

            // This loop runs till user given key values are not finished.
            while (it1.hasNext() && it2.hasNext()) {
                String value = it1.next();
                name = it2.next();
                addLeaf(name, namespace, value);
                if (it1.hasNext()) {
                    traverseToParentExtended();
                }
            }
        }
    }

    private void checkElementCount(int actualSize, int userInputSize) {
        if (actualSize < userInputSize) {
            // Free resources
            curNode.freeRestResources();
            throw new YdtExceptions("Too many key parameter in " +
                                            curNode.getYdtNodeIdentifier()
                                                    .getName() +
                                            ". Expected fixed count " +
                                            actualSize + ".");
        } else if (actualSize < userInputSize) {
            // Free resources
            curNode.freeRestResources();
            throw new YdtExceptions("Too few key parameter in " +
                                            curNode.getYdtNodeIdentifier()
                                                    .getName() +
                                            ". Expected fixed count " +
                                            actualSize + ".");
        }
    }

    /**
     * Adds a last child to YANG data tree, this method is to be used by
     * YANG object builder sub-calls internally.
     *
     * @param opType type of requested operation over a node
     * @return returns added ydt node in YDT tree.
     */

    private YdtNode addExtendedChildNode(YdtContextOperationType opType,
                                         YangSchemaNode yangSchemaNode) {

        YdtNode childNode;
        YangSchemaNodeIdentifier nodeIdentifier =
                yangSchemaNode.getYangSchemaNodeIdentifier();

        // Get the ydtNode of respective type.
        childNode = YdtNodeFactory
                .getYangSchemaNodeTypeSpecificContext(nodeIdentifier,
                                                      yangSchemaNode
                                                              .getYangSchemaNodeType());

        // Update node identifier.
        childNode.setNodeIdentifier(nodeIdentifier);

        // Update yang schema node.
        childNode.setYangSchemaNode(yangSchemaNode);

        // Update yang schema node.
        childNode.setYdtContextOperationType(opType);

        curNode.addChild(childNode, true);

        //Updating the curNode.
        curNode = childNode;

        return childNode;
    }

    @Override
    public YdtExtendedContext addChild(YdtContextOperationType opType,
                                       YangSchemaNode yangSchemaNode) {
        YdtExtendedContext childNode;
        childNode = addExtendedChildNode(opType, yangSchemaNode);
        return childNode;
    }

    @Override
    public YdtExtendedContext addLeaf(Set<String> valueSet,
                                      YangSchemaNode yangSchemaNode) {
        YdtNode childNode;
        childNode = addExtendedChildNode(null, yangSchemaNode);

        // After successful addition of child node updating the values in same.
        childNode.addValueSetWithoutValidation(valueSet);
        return childNode;

    }

    @Override
    public YdtExtendedContext addLeaf(String value,
                                      YangSchemaNode yangSchemaNode) {
        YdtNode childNode;
        childNode = addExtendedChildNode(null, yangSchemaNode);

        // After successful addition of child node updating the values in same.
        childNode.addValueWithoutValidation(value);
        return childNode;
    }

    @Override
    public void traverseToParentExtended() {
        //if (curNode != null) {
        // FIXME: null pointer excpetion is coming at this point. at yab ut (testExecuteQueryOperation)
        curNode = curNode.getParent();
        //}
    }

}
