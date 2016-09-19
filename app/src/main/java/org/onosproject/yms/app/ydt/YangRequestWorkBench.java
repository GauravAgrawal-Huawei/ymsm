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

import com.google.common.collect.ImmutableMap;
import org.onosproject.yangutils.datamodel.YangList;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeContextInfo;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yms.app.ydt.exceptions.YdtException;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YdtType;
import org.onosproject.yms.ydt.YmsOperationType;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.onosproject.yms.app.ydt.RequestedCallType.LEAF;
import static org.onosproject.yms.app.ydt.RequestedCallType.OTHER;
import static org.onosproject.yms.app.ydt.RequestedCardinality.MULTI_INSTANCE;
import static org.onosproject.yms.app.ydt.RequestedCardinality.MULTI_INSTANCE_LEAF;
import static org.onosproject.yms.app.ydt.RequestedCardinality.SINGLE_INSTANCE;
import static org.onosproject.yms.app.ydt.RequestedCardinality.UNKNOWN;
import static org.onosproject.yms.ydt.YdtContextOperationType.CREATE;
import static org.onosproject.yms.ydt.YdtContextOperationType.DELETE;
import static org.onosproject.yms.ydt.YdtContextOperationType.MERGE;
import static org.onosproject.yms.ydt.YdtContextOperationType.REMOVE;
import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_LEAF_VALUE_NODE;
import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_NODE;
import static org.onosproject.yms.ydt.YdtType.SINGLE_INSTANCE_LEAF_VALUE_NODE;

/**
 * Represents YANG request work bench which contains all parameters for
 * request handling and methods to build and obtain YANG data tree
 * which is data (sub)instance representation, abstract of protocol.
 */
public class YangRequestWorkBench implements YdtExtendedBuilder {

    /*
     * YMS operation type.
     */
    private final YmsOperationType ymsOperationType;
    /*
     * Flag to identify data validation need to be done by YDT or not.
     */
    private boolean validate = false;
    /*
     * Current node in YANG data tree, kept to maintain the
     * current context in YDT.
     */
    private YdtNode curNode;
    /*
     * Root node in YANG data tree, kept to maintain the root context in
     * YDT.
     */
    private YdtNode rootNode;
    /*
     * Current node in YANG data tree, kept to maintain the current context
     * in ydt application tree.
     */
    private YdtAppContext appCurNode;
    /*
     * Root node in YANG data tree, kept to maintain the root context in ydt
     * application tree.
     */
    private YdtAppContext appRootNode;
    /**
     * Root Node Tag attribute in YANG data tree, kept to maintain the root
     * tag attributes in YDT.
     * <p/>
     * First key param of map represent tagName  name of tag attribute.
     * Second param of map represent tagValue value of tag attribute
     */
    private Map<String, String> rootTagAttributeMap;
    /*
     * YANG schema registry reference.
     */
    private YangSchemaRegistry registry = null;
    /*
     * YDT default operation type.
     */
    private YdtContextOperationType ydtDefaultOpType;
    // TODO validate need to be handle later with interaction type basis in
    // future when it will be supported

    /**
     * Creates an instance of YANG request work bench which is use to initialize
     * logical rootNode and and schema registry.
     *
     * @param name       name of logical container of a protocol
     *                   which is a holder of the complete tree
     * @param namespace  namespace of logical container
     * @param opType     type of operation done by using YANG
     *                   interface
     * @param registry   Yang schema registry
     * @param isValidate Flag to identify data validation need to be
     *                   done by YDT or not
     */
    public YangRequestWorkBench(String name, String namespace,
                                YmsOperationType opType,
                                YangSchemaRegistry registry,
                                boolean isValidate) {
        YdtNode newNode;
        YangSchemaNodeIdentifier nodeIdentifier =
                new YangSchemaNodeIdentifier();
        nodeIdentifier.setName(name);
        nodeIdentifier.setNameSpace(namespace);
        newNode = new YdtSingleInstanceNode(nodeIdentifier);
        setRootNode(newNode);
        this.registry = registry;
        ymsOperationType = opType;
        validate = isValidate;
        // Set the logical root node for yang data app tree.
        appRootNode = new DefaultYdtAppContext();
        appRootNode.setModuleContext(newNode);
        setAppRootNode(appRootNode);
    }

    /**
     * Creates an instance of YANG request work bench which is used to build YDT
     * tree in YAB.
     *
     * @param curNode       current YDT node
     * @param operationType YMS operation type
     */
    public YangRequestWorkBench(YdtNode curNode,
                                YmsOperationType operationType) {
        this.curNode = curNode;
        ymsOperationType = operationType;
    }

    /**
     * Returns the app context tree root node for ydt application tree.
     *
     * @return YdtAppContext refers to root node of ydt application tree
     */
    public YdtAppContext getAppRootNode() {
        return appRootNode;
    }

    /**
     * Sets the app context tree logical root node  for ydt application tree.
     *
     * @param node application tree's logical root node
     */
    private void setAppRootNode(YdtAppContext node) {
        appRootNode = node;
        appCurNode = node;
    }

    /**
     * Returns the YANG schema registry of the workbench.
     *
     * @return YANG schema registry
     */
    public YangSchemaRegistry getYangSchemaRegistry() {
        return registry;
    }

    /**
     * Returns the data tree for given node identifier.
     *
     * @param id Represents node identifier of YANG data tree node
     * @return YANG data tree node
     */
    private YdtNode moduleHandler(YangSchemaNodeIdentifier id) {

        YangSchemaNode node = registry
                .getYangSchemaNodeUsingSchemaName(id.getName());
        if (node == null) {
            // Free resources
            curNode.freeRestResources();
            throw new YdtException("Application with name \"" + id.getName() +
                                           "\" doesn't exist.");
        }
        YdtNode newNode = new YdtSingleInstanceNode(id);
        newNode.setYangSchemaNode(node);
        return newNode;
    }

    @Override
    public Map<String, String> getRootTagAttributeMap() {
        if (rootTagAttributeMap != null) {
            return ImmutableMap.copyOf(rootTagAttributeMap);
        }
        return null;
    }

    @Override
    public void setRootTagAttributeMap(Map<String, String> attributeTag) {
        rootTagAttributeMap = attributeTag;
    }

    @Override
    public void addChild(String name, String namespace) {
        addChild(name, namespace, UNKNOWN, null, OTHER);
    }

    @Override
    public void addChild(String name, String namespace, YdtType ydtType) {
        addChild(name, namespace, ydtType, null);
    }

    @Override
    public void addChild(String name, String namespace,
                         YdtContextOperationType opType) {
        addChild(name, namespace, UNKNOWN, opType, OTHER);
    }

    @Override
    public void addChild(String name, String namespace, YdtType ydtType,
                         YdtContextOperationType opType) {
        RequestedCardinality cardinality;
        switch (ydtType) {
            case MULTI_INSTANCE_NODE:
                cardinality = MULTI_INSTANCE;
                break;
            case SINGLE_INSTANCE_NODE:
                cardinality = SINGLE_INSTANCE;
                break;
            default:
                curNode.freeRestResources();
                throw new YdtException("Requested Node should be created " +
                                               "using addLeaf interface.");
        }
        addChild(name, namespace, cardinality, opType, OTHER);
    }

    /**
     * Adds a last child to YANG data tree, this method is to be used by all
     * protocols internally which are aware or unaware of the nature
     * (single/multiple) of node.
     *
     * @param name        name of child to be added
     * @param namespace   namespace of child to be added
     * @param cardinality type of YANG data tree node operation
     * @param opType      type of requested operation over a node
     * @param callType    call type to identify the its a leaf node or
     *                    other node
     */
    private void addChild(String name, String namespace,
                          RequestedCardinality cardinality,
                          YdtContextOperationType opType,
                          RequestedCallType callType) {

        YdtNode childNode;
        boolean isContextSwitch = false;
        YangSchemaNode schemaNode;
        YangSchemaNodeContextInfo contextInfo;
        YangSchemaNode lastAugment = null;
        boolean isLeaf = true;

        YangSchemaNodeIdentifier id = new YangSchemaNodeIdentifier();
        id.setName(name);

        // Reference for parent node operation type.
        YdtContextOperationType parentOpType = curNode
                .getYdtContextOperationType();

        // Leaf nodes doesn't have operation type.
        if (callType != LEAF) {
            if (opType != null && parentOpType != null) {
                // Get operation type validate
                validateOperationType(parentOpType, opType);
            } else if (opType == null) {
                /*
                 * If the "operation" attribute is not specified,
                 * then the operation applied to the parent data node of the
                 * configuration is used. If no parent data node is available,
                 * then the default-operation'value is used.
                 * If default operation type is not set,
                 * merge will be taken as default operation type.
                 */
                if (parentOpType != null) {
                    opType = parentOpType;
                } else if (ydtDefaultOpType != null) {
                    opType = ydtDefaultOpType;
                } else {
                    opType = MERGE;
                }
            }
        }

        // Module/sub-module node handler.
        if (curNode.equals(rootNode)) {
            childNode = moduleHandler(id);
            id.setNameSpace(childNode.getYangSchemaNode().getNameSpace());
            //TODO supplied data model via YSR having same nameSpace or not.
        } else {
            // If namespace given by user null, then take namespace from parent.
            if (namespace == null) {
                namespace = curNode.getYdtNodeIdentifier().getNameSpace();
            }

            id.setNameSpace(namespace);

            /*
             * Get the already exiting YDT node in YDT tree with same
             * nodeIdentifier
             */
            childNode = curNode.getCollidingChild(id);

            /*
             * If colliding child doesn't exist ,
             * then query yang data model for schema of given node.
             */
            if (childNode == null) {
                /*
                 * Get Yang Schema node context info which is having
                 * YangSchemaNode and ContextSwitchedNode.
                 */
                contextInfo = curNode.getSchemaNodeContextInfo(id);

                if (contextInfo.getContextSwitchedNode() != null) {
                    lastAugment = appCurNode.getAugmentingSchemaNode(
                            id, contextInfo);
                    if (lastAugment != null) {
                        /*
                         * As two tree(YDT and YDT Application Tree) are getting
                         * prepared in parallel, So  setting context switch
                         * flag it will help ydt to keep the track whether
                         * ydtApp tree also need to be traversed back to parent
                         * or not with YDT tree traverse to parent call.
                         */
                        isContextSwitch = true;
                    }
                }
                schemaNode = contextInfo.getSchemaNode();
            } else {
                /*
                 * If colliding child exist , then will be leaf-list or list
                 * If its leaf-list then return and add new requested
                 * value/valueSet in same node else take yang data model
                 * information from colliding child.
                 */
                if (childNode.getYdtType() != MULTI_INSTANCE_NODE) {
                    curNode = childNode;
                    return;
                }
                schemaNode = childNode.getYangSchemaNode();
            }

            // Get the ydtNode of respective type.
            childNode = YdtNodeFactory.getNode(id, schemaNode, cardinality,
                                               callType);
        }

        // Update node identifier.
        childNode.setId(id);

        YdtType nodeType = childNode.getYdtType();
        if (nodeType != MULTI_INSTANCE_LEAF_VALUE_NODE &&
                nodeType != SINGLE_INSTANCE_LEAF_VALUE_NODE) {
            // Update operation type.
            isLeaf = false;
            childNode.setYdtContextOperationType(opType);
        }

        // Add child.
        curNode.addChild(childNode, true);

        // Update parent ydt node map.
        curNode.updateYdtMap(id, childNode);

        if (isLeaf) {
            opType = curNode.getYdtContextOperationType();
        }

        /*
         * Create entry of module node in ydt app tree.
         * Or if context switch happened then also add entry for same ydt
         * node in the ydt application tree.
         */
        if (curNode.equals(rootNode) || isContextSwitch) {
            addChildInAppTree(childNode, lastAugment, opType,
                              isContextSwitch);
        }

        // Update app tree module node operation.
        appCurNode.getAppRoot().updateAppOperationType(opType);

        // Updating the delete operation list in app tree.
        updateDeleteOperationList(childNode);

        // Updating the curNode.
        curNode = childNode;
    }

    /**
     * Adds the given ydt node (with operation type delete/remove)in delete
     * list of ydt application tree module node.
     *
     * @param childNode ydt node
     */
    private void updateDeleteOperationList(YdtNode childNode) {
        if (childNode.getYdtContextOperationType() == DELETE ||
                childNode.getYdtContextOperationType() == REMOVE) {

            List<YdtContext> nodeList =
                    appCurNode.getAppRoot().getDeleteNodes();
            // Add childNode with delete operation in deleteList.
            nodeList.add(childNode);
            appRootNode.setDeleteNodes(nodeList);
        }
    }

    /**
     * Adds a last child to YANG app data tree.this method is to be used
     * internally by other ydt interfaces.
     *
     * @param childNode       node to be added in tree
     * @param schemaNode      last augmenting module node
     * @param childOpType     operation type of node
     * @param isContextSwitch to distinguish the call whether it
     *                        module node call all its subNode call
     */
    private void addChildInAppTree(YdtNode childNode,
                                   YangSchemaNode schemaNode,
                                   YdtContextOperationType childOpType,
                                   boolean isContextSwitch) {

        YdtAppNodeOperationType opType;

        DefaultYdtAppContext appContext = new DefaultYdtAppContext();
        // Add context switched child in ydt App tree.
        appCurNode.addChild(appContext);
        //Updating the curNode.
        appCurNode = appContext;

        // Get the app tree operation type from ydt operation type.
        opType = appCurNode.getAppOpTypeFromYdtOpType(childOpType);

        if (isContextSwitch) {
            appCurNode.setAugmentingSchemaNode(schemaNode);
        } else {

            // If it is application rootNode then set the ydt application
            // module(root) node.
            appCurNode.setModuleContext(childNode);

            // Updating application root node to maintain the delete node list.
            appCurNode.setAppRoot();

            // Set the app tree operation type.
            appCurNode.setOperationType(opType);
        }

        // Updating context switch flag for YdtNode.
        childNode.setContextSwitch();
    }

    /**
     * Validates the various combination of operation type.
     *
     * @param parentOpType Reference for parent node operation type
     * @param childOpType  type of YANG data tree node operation
     */
    private void validateOperationType(YdtContextOperationType parentOpType,
                                       YdtContextOperationType childOpType) {

        switch (parentOpType) {
            case CREATE:
                // Inside the create operation delete operation should not come.
                if (childOpType == DELETE) {
                    // Free resources
                    curNode.freeRestResources();
                    throw new YdtException("Create request is not allowed " +
                                                   "under delete operation.");
                }
                break;
            case DELETE:
                // Inside the delete operation create operation should not come.
                if (childOpType == CREATE) {
                    // Free resources
                    curNode.freeRestResources();
                    throw new YdtException("Delete request is not allowed " +
                                                   "under create operation.");
                }
                break;
            default:
                //TODO check all possible scenario.
        }
    }

    @Override
    public void addLeaf(String name, String namespace, String value) {
        addLeaf(name, namespace, value, null, UNKNOWN);
    }

    @Override
    public void addLeaf(String name, String namespace, Set<String> valueSet) {
        addLeaf(name, namespace, null, valueSet, MULTI_INSTANCE_LEAF);
    }

    /**
     * Adds a last leaf with list of values/single value to YANG data tree.
     * This method is used by all protocols which knows the
     * <p/>
     * nature (single/multiple) or not.
     * Value of leaf can be null which indicates selection node in get
     * operation.
     *
     * @param name        name of child to be added
     * @param namespace   namespace of child to be added, if it's
     *                    null, parent's
     *                    namespace will be applied to child
     * @param value       value of the child
     * @param valueSet    list of value of the child
     * @param cardinality type of YANG data tree node operation
     */
    private void addLeaf(String name, String namespace, String value,
                         Set<String> valueSet,
                         RequestedCardinality cardinality) {
        addChild(name, namespace, cardinality, null, LEAF);

        // After successful addition of child node updating the values in same.
        if (value != null) {
            curNode.addValue(value);
        } else if (valueSet != null) {
            curNode.addValueSet(valueSet);
        }
    }

    @Override
    public void traverseToParent() {
        // If traverse back to parent for logical root node comes
        if (curNode.equals(rootNode)) {
            throw new YdtException("Can't invoke get parent " +
                                           "at logical root node.");
        }

        // If node is of multiInstanceNode type then check key uniqueness.
        if (curNode.getYdtType() == MULTI_INSTANCE_NODE) {
            curNode.createKeyNodeList();
        }

        /*
         * Check application switch for curNode if set,
         * then traverseToParent in YDT application tree.
         */
        if (curNode.getParent().equals(rootNode) ||
                curNode.getContextSwitch()) {
            traverseToAppTreeParent();
        }

        /*
         * Validate all multi Instance inside current context,
         * This is not valid for leaf and leaf-list node.
         */
        if (curNode instanceof YdtMultiInstanceNode ||
                curNode instanceof YdtSingleInstanceNode) {
            curNode.validateMultiInstanceNode();
        }

        curNode = curNode.getParent();
    }

    /**
     * Traverses up in YANG application tree to the parent node,
     * This will be used when Ydt current context switch flag is set.
     */
    private void traverseToAppTreeParent() {
        appCurNode = appCurNode.getParent();
    }

    @Override
    public YdtContext getCurNode() {
        return curNode;
    }

    @Override
    public void setDefaultEditOperationType(
            YdtContextOperationType ydtContextOperationType) {
        ydtDefaultOpType = ydtContextOperationType;
    }

    @Override
    public YdtExtendedContext getRootNode() {
        return rootNode;
    }

    /**
     * Sets the logical root context information available in YDT node.
     *
     * @param node logical root node
     */
    private void setRootNode(YdtNode node) {
        // Setting the root node
        rootNode = node;
        curNode = node;
    }

    @Override
    public YmsOperationType getYmsOperationType() {
        return ymsOperationType;
    }

    @Override
    public void addMultiInstanceChild(String name, String namespace,
                                      List<String> keysValueList) {
        addChild(name, namespace, UNKNOWN, null, RequestedCallType.UNKNOWN);
        int inputCount = keysValueList.size();
        int actualCount;
        if (curNode.getYdtType() == MULTI_INSTANCE_LEAF_VALUE_NODE) {
            // After successful addition of child node updating
            // the values in same.
            // inputCount = curNode.getValueSet().size() + inputCount;
            // checkElementCount(actualCount, inputCount);
            // TODO check the element count
            for (String value : keysValueList) {
                curNode.addValue(value);
            }
        } else if (curNode.getYdtType() == MULTI_INSTANCE_NODE) {
            YangList yangListHolder = (YangList) curNode.getYangSchemaNode();
            List<String> schemaKeyList = yangListHolder.getKeyList();
            actualCount = schemaKeyList.size();
            checkElementCount(actualCount, inputCount);

            // Iterator for user given key value.
            Iterator<String> it1 = keysValueList.iterator();
            // Iterator for fixed schema key name list.
            Iterator<String> it2 = schemaKeyList.iterator();

            while (it1.hasNext()) {
                String value = it1.next();
                name = it2.next();
                addLeaf(name, namespace, value);
                if (it1.hasNext()) {
                    traverseToParentWithoutValidation();
                }
            }
            curNode = curNode.getParent();
        } else {
            throw new YdtException("Adds an instance of a child list node, " +
                                           "or adds a child leaf list with " +
                                           "multiple instance only.");
        }
    }

    /**
     * Checks the user supplied list of argument match's the expected value
     * or not.
     *
     * @param actualCount count suppose to be
     * @param inputCount  user supplied values count
     */
    private void checkElementCount(int actualCount, int inputCount) {
        if (actualCount < inputCount) {
            // Free resources
            curNode.freeRestResources();
            String errorInfo = "Too many key parameter in " +
                    curNode.getYdtNodeIdentifier().getName() +
                    ". Expected fixed count " + actualCount + ".";
            throw new YdtException(errorInfo);
        } else if (actualCount > inputCount) {
            // Free resources
            curNode.freeRestResources();
            String errorInfo = "Too few key parameter in " +
                    curNode.getYdtNodeIdentifier().getName() +
                    ". Expected fixed count " + actualCount + ".";
            throw new YdtException(errorInfo);
        }
    }

    /**
     * Adds a last child to YANG data tree, this method is to be used by
     * YANG object builder sub-calls internally.
     *
     * @param opType type of requested operation over a node
     * @return returns added ydt node in YDT tree
     */
    private YdtNode addExtendedChildNode(YdtContextOperationType opType,
                                         YangSchemaNode schemaNode) {

        YdtNode childNode;
        YangSchemaNodeIdentifier id =
                schemaNode.getYangSchemaNodeIdentifier();

        // Get the ydtNode of respective type.
        childNode = YdtNodeFactory
                .getYangSchemaNodeTypeSpecificContext(
                        id, schemaNode.getYangSchemaNodeType());

        // Update node identifier.
        childNode.setId(id);

        // Update yang schema node.
        childNode.setYangSchemaNode(schemaNode);

        childNode.setYdtContextOperationType(opType);

        curNode.addChild(childNode, true);

        //Updating the curNode.
        curNode = childNode;

        return childNode;
    }

    @Override
    public YdtExtendedContext addChild(YdtContextOperationType opType,
                                       YangSchemaNode schemaNode) {
        return addExtendedChildNode(opType, schemaNode);
    }

    @Override
    public YdtExtendedContext addLeaf(Set<String> valueSet,
                                      YangSchemaNode schemaNode) {
        YdtNode childNode = addExtendedChildNode(null, schemaNode);

        // After successful addition of child node updating the values in same.
        childNode.addValueSetWithoutValidation(valueSet);
        return childNode;
    }

    @Override
    public YdtExtendedContext addLeaf(String value,
                                      YangSchemaNode schemaNode) {
        YdtNode childNode = addExtendedChildNode(null, schemaNode);

        // After successful addition of child node updating the values in same.
        childNode.addValueWithoutValidation(value);
        return childNode;
    }

    @Override
    public void traverseToParentWithoutValidation() {
        // If traverse back to parent for logical root node comes
        if (curNode.equals(rootNode)) {
            throw new YdtException("Can't invoke get parent " +
                                           "at logical root node.");
        }
        curNode = curNode.getParent();
    }
}
