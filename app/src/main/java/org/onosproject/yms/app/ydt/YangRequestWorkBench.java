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

import static org.onosproject.yms.app.ydt.AppNodeFactory.getAppContext;
import static org.onosproject.yms.app.ydt.RequestedCallType.LEAF;
import static org.onosproject.yms.app.ydt.RequestedCallType.NON_LEAF;
import static org.onosproject.yms.app.ydt.RequestedCardinality.MULTI_INSTANCE;
import static org.onosproject.yms.app.ydt.RequestedCardinality.MULTI_INSTANCE_LEAF;
import static org.onosproject.yms.app.ydt.RequestedCardinality.SINGLE_INSTANCE;
import static org.onosproject.yms.app.ydt.RequestedCardinality.UNKNOWN;
import static org.onosproject.yms.app.ydt.YdtConstants.errorMsg;
import static org.onosproject.yms.app.ydt.YdtUtils.getAppOpTypeFromYdtOpType;
import static org.onosproject.yms.ydt.YdtContextOperationType.CREATE;
import static org.onosproject.yms.ydt.YdtContextOperationType.DELETE;
import static org.onosproject.yms.ydt.YdtContextOperationType.MERGE;
import static org.onosproject.yms.ydt.YdtContextOperationType.REMOVE;
import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_LEAF_VALUE_NODE;
import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_NODE;

/**
 * Represents YANG request work bench which contains all parameters for
 * request handling and methods to build and obtain YANG data tree
 * which is data (sub)instance representation, abstract of protocol.
 */
public class YangRequestWorkBench implements YdtExtendedBuilder {

    // Ydt formatted error string
    private static final String FMT_TOO_FEW =
            "Too few key parameters in %s. Expected %d; actual %d.";
    private static final String FMT_TOO_MANY =
            "Too many key parameters in %s. Expected %d; actual %d.";
    private static final String FMT_NOT_EXIST =
            "Application with name \"%s\" doesn't exist.";

    // Ydt error strings.
    private static final String E_USE_ADDLEAF =
            "Requested Node should be created using addLeaf interface.";
    private static final String E_CREATE =
            "Create request is not allowed under delete operation.";
    private static final String E_DEL =
            "Delete request is not allowed under create operation.";
    private static final String E_INVOKE_PARENT =
            "Can't invoke get parent at logical root node.";
    private static final String FMT_MANY_INS =
            "Too many instances of %s. Expected maximum instances %d.";
    /*
     * Reference for the current context node in YANG data tree.
     */
    private YdtNode curNode;

    /*
     * Reference for the logical root node in YANG data tree.
     */
    private YdtNode rootNode;

    /*
     * Reference for the current context in ydt application tree.
     */
    private YdtAppContext appCurNode;

    /*
     * Reference for the logical root node context in ydt application tree.
     */
    private YdtAppContext appRootNode;

    /**
     * Root Node Tag attribute in YANG data tree, kept to maintain the root
     * tag attributes in YDT.
     * <p>
     * First key param of map represent tagName  name of tag attribute.
     * Second param of map represent tagValue value of tag attribute
     */
    private Map<String, String> rootTagAttributeMap;

    /*
     * YANG schema registry reference.
     */
    private YangSchemaRegistry registry = null;

    /*
     * YMS operation type.
     */
    private final YmsOperationType ymsOperationType;

    /*
     * YDT default operation type.
     */
    private YdtContextOperationType ydtDefaultOpType;

    /*
     * Flag to identify data validation need to be done by YDT or not.
     */
    private boolean validate = false;
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
     * @param reg        Yang schema registry
     * @param isValidate Flag to identify data validation need to be
     *                   done by YDT or not
     */
    public YangRequestWorkBench(String name, String namespace,
                                YmsOperationType opType,
                                YangSchemaRegistry reg,
                                boolean isValidate) {

        setRootNode(new YdtLogicalNode(name, namespace));
        registry = reg;
        ymsOperationType = opType;
        validate = isValidate;

        setAppRootNode(getAppContext(true));
    }

    /**
     * Creates an instance of YANG request work bench which is used to build YDT
     * tree in YAB.
     *
     * @param node   current YDT node
     * @param opType YMS operation type
     */
    public YangRequestWorkBench(YdtNode node, YmsOperationType opType) {
        curNode = node;
        ymsOperationType = opType;
    }

    /**
     * Sets the logical root node for ydt.
     *
     * @param node ydt logical root node
     */
    private void setRootNode(YdtNode node) {
        rootNode = node;
        curNode = node;
    }

    /**
     * Sets the logical root node for ydt application tree.
     *
     * @param node ydt application context logical root node
     */
    private void setAppRootNode(YdtAppContext node) {
        appRootNode = node;
        appCurNode = node;
    }

    /**
     * Returns the YANG schema registry of Ydt.
     * This method will be used by ytb.
     *
     * @return YANG schema registry
     */
    public YangSchemaRegistry getYangSchemaRegistry() {
        return registry;
    }

    /**
     * Returns the ydt app context tree logical root node.
     * This method will be used by yab and ytb.
     *
     * @return YdtAppContext app tree logical root node
     */
    public YdtAppContext getAppRootNode() {
        return appRootNode;
    }

    /**
     * Returns the ydt module node with requested node identifier.
     *
     * @param id module/application node identifier
     * @return YANG data tree node
     */
    private YdtNode moduleHandler(YangSchemaNodeIdentifier id) {

        YangSchemaNode node =
                registry.getYangSchemaNodeUsingSchemaName(id.getName());

        String namespace = id.getNameSpace().getModuleNamespace();

        /*
         * Checking received schema node is having same namespace as
         * requested by user or not.
         */
        if (node == null || namespace != null &&
                !namespace.equals(node.getYangSchemaNodeIdentifier()
                                          .getNameSpace()
                                          .getModuleNamespace())) {
            throw new YdtException(errorMsg(FMT_NOT_EXIST, id.getName()));
        }

        /*
         * If yms operation is for query then no validation need to be
         * performed.
         */
        if (ymsOperationType == YmsOperationType.QUERY_REQUEST) {
            // Checking whether module node is already exits in YDT or not.
            curNode.getCollidingChild(id);
        }

        YdtNode newNode = new YdtSingleInstanceNode(node);
        newNode.setYangSchemaNode(node);
        return newNode;
    }

    @Override
    public void setRootTagAttributeMap(Map<String, String> attributeTag) {
        rootTagAttributeMap = attributeTag;
    }

    @Override
    public Map<String, String> getRootTagAttributeMap() {
        if (rootTagAttributeMap != null) {
            return ImmutableMap.copyOf(rootTagAttributeMap);
        }
        return null;
    }

    @Override
    public void addChild(String name, String namespace) {
        addChild(name, namespace, UNKNOWN, null, NON_LEAF);
    }

    @Override
    public void addChild(String name, String namespace, YdtType ydtType) {
        addChild(name, namespace, ydtType, null);
    }

    @Override
    public void addChild(String name, String namespace,
                         YdtContextOperationType opType) {
        addChild(name, namespace, UNKNOWN, opType, NON_LEAF);
    }

    @Override
    public void addChild(String name, String namespace, YdtType ydtType,
                         YdtContextOperationType opType) {
        RequestedCardinality cardinality = null;
        switch (ydtType) {
            case MULTI_INSTANCE_NODE:
                cardinality = MULTI_INSTANCE;
                break;
            case SINGLE_INSTANCE_NODE:
                cardinality = SINGLE_INSTANCE;
                break;
            default:
                curNode.errorHandler(E_USE_ADDLEAF, rootNode);
        }
        addChild(name, namespace, cardinality, opType, NON_LEAF);
    }

    /**
     * Adds a last child to YANG data tree; this method is to be used by all
     * protocols internally which are aware or unaware of the nature
     * (single/multiple) of node.
     *
     * @param name        name of child to be added
     * @param namespace   namespace of child to be added
     * @param cardinality type of YANG data tree node operation
     * @param opType      type of requested operation over a node
     * @param callType    to identify the whether its a leaf or other node
     */
    private void addChild(String name, String namespace,
                          RequestedCardinality cardinality,
                          YdtContextOperationType opType,
                          RequestedCallType callType) {

        YdtNode childNode;
        boolean contextSwitch = false;
        YangSchemaNode augmentingSchema = null;

        YangSchemaNodeIdentifier id = getNodeIdentifier(name, namespace);

        // Module/sub-module node handler.
        if (curNode.equals(rootNode)) {
            childNode = moduleHandler(id);
        } else {

            YangSchemaNode schemaNode;
            YangSchemaNodeContextInfo contextInfo;

            // If namespace given by user null, then take namespace from parent.
            if (namespace == null) {
                id.setNameSpace(curNode.getYangSchemaNode().getNameSpace());
            }

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
                    augmentingSchema = appCurNode.getAugmentingSchemaNode(
                            id, contextInfo);
                    if (augmentingSchema != null) {
                        /*
                         * As two tree(YDT and YDT Application Tree) are getting
                         * prepared in parallel, So  setting context switch
                         * flag it will help ydt to keep the track whether
                         * ydtApp tree also need to be traversed back to parent
                         * or not with YDT tree traverse to parent call.
                         */
                        contextSwitch = true;
                    }
                }
                schemaNode = contextInfo.getSchemaNode();
            } else {
                /*
                 * If colliding child exist , then it will be leaf-list or list.
                 * If its leaf-list then return and add new requested
                 * value/valueSet in same node else take yang data model
                 * information from colliding child.
                 */
                if (childNode.getYdtType() == MULTI_INSTANCE_LEAF_VALUE_NODE) {
                    curNode = childNode;
                    return;
                }
                schemaNode = childNode.getYangSchemaNode();
            }

            /*
             * For yms query request node specific validation are not
             * required as rest-conf can call addChild api for leaf/leaf-list
             * node addition also in ydt.
             */
            if (ymsOperationType == YmsOperationType.QUERY_REQUEST) {
                childNode = YdtNodeFactory.getYangSchemaNodeTypeSpecificContext(
                        schemaNode);
            } else {
                childNode = YdtNodeFactory.getNode(id, schemaNode, cardinality,
                                                   callType);
            }
        }

        opType = getValidOpType(opType, childNode);

        childNode.setYdtContextOperationType(opType);

        curNode.addChild(childNode, true);

        // Update parent ydt node map.
        curNode.updateYdtMap(childNode);

        processAppTree(opType, childNode, augmentingSchema, contextSwitch);

        curNode = childNode;
    }

    /**
     * Returns the yang node identifier with requested name and namespace.
     *
     * @param name      name of the node
     * @param namespace namespace of the node
     * @return yang node identifier
     */
    private YangSchemaNodeIdentifier getNodeIdentifier(String name,
                                                       String namespace) {
        YangSchemaNodeIdentifier id = new YangSchemaNodeIdentifier();
        id.setName(name);
        id.setNameSpace(new NameSpace(namespace));
        return id;
    }

    /**
     * Processes application tree on the bases of requested ydt node.
     *
     * @param opType           user requested operation type
     * @param childNode        requested ydt node
     * @param augmentingSchema schema of last augmenting node
     * @param contextSwitch    true, for module node call; false for modules
     *                         sub-node calls
     */
    private void processAppTree(
            YdtContextOperationType opType, YdtNode childNode,
            YangSchemaNode augmentingSchema, boolean contextSwitch) {

        /*
         * This is to avoid multiple entries of single augmented target.
         */
        if (augmentingSchema != null) {
            if (!appCurNode.addSchemaToAppSet(augmentingSchema)) {
                return;
            }
        }

        if (opType == null) {
            opType = curNode.getYdtContextOperationType();
        } else {
            // Updating operation type for parent nodes
            appCurNode.updateAppOperationType(opType);
        }

        /*
         * Create entry of module node in ydt app tree.
         * Or if context switch happened then also add entry for same ydt
         * node in the ydt application tree.
         */
        if (curNode.equals(rootNode) || contextSwitch) {
            addChildInAppTree(childNode, augmentingSchema, opType,
                              contextSwitch);

            // Setting app tree node operation.
            appCurNode.setOperationType(getAppOpTypeFromYdtOpType(opType));
        }

        // Updating the delete operation list in app tree.
        if (opType == DELETE || opType == REMOVE) {
            appCurNode.addDeleteNode(childNode);
        }
    }

    /**
     * Returns the valid operation type for requested ydt node after performing
     * validation.
     *
     * @param opType user requested operation type
     * @param node   new requested ydt node
     * @return operation type
     */
    private YdtContextOperationType getValidOpType(
            YdtContextOperationType opType, YdtNode node) {

        switch (node.getYdtType()) {

            case SINGLE_INSTANCE_NODE:
            case MULTI_INSTANCE_NODE:

                // Reference for parent node operation type.
                YdtContextOperationType parentOpType =
                        curNode.getYdtContextOperationType();

                if (opType == null) {
                    opType = getOperationType(parentOpType);
                } else if (parentOpType != null) {
                    validateOperationType(parentOpType, opType);
                }

                return opType;

            /*
             * Nodes other then single/multi instance node does not support
             * operation type so no need of validation for those.
             */
            default:
                return null;
        }
    }

    /**
     * Returns the operation type for non leaf node.
     * When "operation" attribute for current node is not specified or null,
     * then the operation applied to the parent data node of the
     * configuration is used. If no parent data node is available,
     * then the default-operation'value is used.
     * If default operation type is not set, merge will be taken as default
     * operation type.
     *
     * @param parentOpType operation type of parent node
     * @return operation type for current non leaf node
     */
    private YdtContextOperationType getOperationType(
            YdtContextOperationType parentOpType) {

        return parentOpType != null ? parentOpType :
                (ydtDefaultOpType != null ? ydtDefaultOpType : MERGE);
    }

    /**
     * Adds a last child to YANG app data tree.this method is to be used
     * internally by other ydt interfaces.
     *
     * @param childNode       node to be added in tree
     * @param schemaNode      last augmenting module node
     * @param childOpType     operation type of node
     * @param isContextSwitch true, for module node call; false for modules
     *                        sub-node calls
     */
    private void addChildInAppTree(YdtNode childNode,
                                   YangSchemaNode schemaNode,
                                   YdtContextOperationType childOpType,
                                   boolean isContextSwitch) {

        YdtAppNodeOperationType opType;

        DefaultYdtAppContext appContext = getAppContext(isContextSwitch);

        // Add context switched child in ydt App tree.
        appCurNode.addChild(appContext);

        appCurNode = appContext;

        opType = getAppOpTypeFromYdtOpType(childOpType);

        appCurNode.setAppData(childNode, schemaNode);

        appCurNode.setOperationType(opType);

        childNode.setAppContextSwitch();
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
                    curNode.errorHandler(E_CREATE, rootNode);
                }
                break;
            case DELETE:
                // Inside the delete operation create operation should not come.
                if (childOpType == CREATE) {
                    curNode.errorHandler(E_DEL, rootNode);
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
     * This method is used by all protocols which knows the nature
     * (single/multiple) or not.
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
            throw new YdtException(E_INVOKE_PARENT);
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
                curNode.getAppContextSwitch()) {
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
            YdtContextOperationType opType) {
        ydtDefaultOpType = opType;
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
                                      List<String> keysValueList,
                                      YdtContextOperationType opType) {
        addChild(name, namespace, UNKNOWN, opType,
                 RequestedCallType.MULTI_INSTANCE);
        int inputCount = keysValueList.size();

        if (curNode.getYdtType() == MULTI_INSTANCE_LEAF_VALUE_NODE) {

            /*
             * Calculating the current leaf-list node array size by adding
             * existing elements count and new supplied elements by user for
             * the same.
             */
            // TODO instance count for leaf list need to be handled.
//            if (curNode.getValueSet().size() + inputCount > expectedCount) {
//                curNode.errorHandler(
//                        errorMsg(FMT_MANY_INS, name, expectedCount), rootNode);
//            }

            /*
             * After successful addition of child node updating
             * the values in same.
             */
            for (String value : keysValueList) {
                curNode.addValue(value);
            }
        } else if (curNode.getYdtType() == MULTI_INSTANCE_NODE) {

            YangList yangListHolder = (YangList) curNode.getYangSchemaNode();
            List<String> schemaKeyList = yangListHolder.getKeyList();
            int expectedCount = schemaKeyList.size();
            checkElementCount(name, expectedCount, inputCount);

            //After validation adding the key nodes under the list node.
            Iterator<String> sklIter = schemaKeyList.iterator();
            Iterator<String> kvlIter = keysValueList.iterator();
            String keyEleName;

            while (kvlIter.hasNext()) {
                String value = kvlIter.next();
                keyEleName = sklIter.next();
                addLeaf(keyEleName, namespace, value);
                if (kvlIter.hasNext()) {
                    traverseToParentWithoutValidation();
                }
            }

            curNode = curNode.getParent();
        }
    }

    /**
     * Checks the user supplied list of argument match's the expected value
     * or not.
     *
     * @param name     name of the parent list/leaf-list node
     * @param expected count suppose to be
     * @param actual   user supplied values count
     */
    private void checkElementCount(String name, int expected,
                                   int actual) {
        if (expected < actual) {
            throw new YdtException(errorMsg(FMT_TOO_MANY, name, expected, actual));
        } else if (expected > actual) {
            throw new YdtException(errorMsg(FMT_TOO_FEW, name, expected, actual));
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

        childNode = YdtNodeFactory
                .getYangSchemaNodeTypeSpecificContext(schemaNode);

        childNode.setYangSchemaNode(schemaNode);

        childNode.setYdtContextOperationType(opType);

        curNode.addChild(childNode, true);

        curNode = childNode;

        return childNode;
    }

    @Override
    public YdtExtendedContext addChild(YdtContextOperationType opType,
                                       YangSchemaNode schemaNode) {
        return addExtendedChildNode(opType, schemaNode);
    }

    @Override
    public YdtExtendedContext addLeafList(Set<String> valueSet,
                                          YangSchemaNode schemaNode) {
        YdtNode childNode = addExtendedChildNode(null, schemaNode);

        /*
         * After successful addition of child node updating the values in
         * valueSet.
         */
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
        // If traverse back to parent for logical root node comes.
        if (curNode.equals(rootNode)) {
            throw new YdtException(E_INVOKE_PARENT);
        }
        curNode = curNode.getParent();
    }
}
