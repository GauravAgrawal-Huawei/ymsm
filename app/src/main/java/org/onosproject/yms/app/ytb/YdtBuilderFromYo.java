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

import org.onosproject.yangutils.datamodel.YangAugment;
import org.onosproject.yangutils.datamodel.YangAugmentableNode;
import org.onosproject.yangutils.datamodel.YangCase;
import org.onosproject.yangutils.datamodel.YangChoice;
import org.onosproject.yangutils.datamodel.YangLeaf;
import org.onosproject.yangutils.datamodel.YangLeafList;
import org.onosproject.yangutils.datamodel.YangLeavesHolder;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yangutils.datamodel.exceptions.DataModelException;
import org.onosproject.yangutils.translator.tojava.JavaFileInfoContainer;
import org.onosproject.yangutils.translator.tojava.JavaFileInfoTranslator;
import org.onosproject.yangutils.translator.tojava.javamodel.JavaLeafInfoContainer;
import org.onosproject.yms.app.ydt.AppType;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContextOperationType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.onosproject.yms.app.ytb.TraversalType.CHILD;
import static org.onosproject.yms.app.ytb.TraversalType.PARENT;
import static org.onosproject.yms.app.ytb.TraversalType.ROOT;
import static org.onosproject.yms.app.ytb.TraversalType.SIBLING;
import static org.onosproject.yms.app.ytb.YtbUtil.PERIOD;
import static org.onosproject.yms.app.ytb.YtbUtil.STR_NULL;
import static org.onosproject.yms.app.ytb.YtbUtil.getAttributeFromInheritance;
import static org.onosproject.yms.app.ytb.YtbUtil.getAttributeOfObject;
import static org.onosproject.yms.app.ytb.YtbUtil.getCapitalCase;
import static org.onosproject.yms.app.ytb.YtbUtil.getClassLoaderForAugment;
import static org.onosproject.yms.app.ytb.YtbUtil.getInterfaceClassFromImplClass;
import static org.onosproject.yms.app.ytb.YtbUtil.getOperationTypeOfTheNode;
import static org.onosproject.yms.app.ytb.YtbUtil.getParentObjectForTheLeafOrLeafList;
import static org.onosproject.yms.app.ytb.YtbUtil.getParentObjectForTheNode;
import static org.onosproject.yms.app.ytb.YtbUtil.getStringFromDataType;
import static org.onosproject.yms.app.ytb.YtbUtil.isAugmentNode;
import static org.onosproject.yms.app.ytb.YtbUtil.isMultiInstanceNode;
import static org.onosproject.yms.app.ytb.YtbUtil.isProcessableNode;
import static org.onosproject.yms.app.ytb.YtbUtil.isTypePrimitive;
import static org.onosproject.yms.app.ytb.YtbUtil.isValueOrSelectLeafSet;
import static org.onosproject.yms.ydt.YdtContextOperationType.NONE;

/**
 * Representation of traversal through YANG node and its corresponding object,
 * resulting in building of the YDT tree.
 */
public class YdtBuilderFromYo {

    private static final String STR_TYPE = "type";
    private static final String STR_SUBJECT = "subject";
    private static final String TRUE = "true";
    private static final String IS_LEAF_VALUE_SET_METHOD = "isLeafValueSet";
    private static final String IS_SELECT_LEAF_SET_METHOD = "isSelectLeaf";
    private static final String OUTPUT = "output";
    private static final String YANG_AUGMENTED_INFO_MAP = "yangAugmentedInfoMap";
    private static final String DEFAULT = "Default";

    /**
     * YANG schema registry of the application.
     */
    private final YangSchemaRegistry appSchemaRegistry;

    /**
     * Current instance of the YDT builder where the tree is built.
     */
    private final YdtExtendedBuilder ydtExtendedBuilder;

    /**
     * YANG root object that is required for walking through the node,
     * object and building YDT simultaneously.
     */
    private Object rootObject;

    /**
     * YANG root node that is required for walking through the node,
     * object and building YDT simultaneously.
     */
    private YangSchemaNode schemaRoot;

    /**
     * Creates YDT builder from YANG object by assigning the mandatory values.
     *
     * @param logicalRootBuilder logical root node builder
     * @param rootObject         logical root node object
     * @param appSchemaRegistry  YANG schema registry of the application
     */
    public YdtBuilderFromYo(YdtExtendedBuilder logicalRootBuilder,
                            Object rootObject,
                            YangSchemaRegistry appSchemaRegistry) {
        ydtExtendedBuilder = logicalRootBuilder;
        this.rootObject = rootObject;
        this.appSchemaRegistry = appSchemaRegistry;
    }

    /**
     * Returns schema root node, received from YSR, which searches based on
     * the object received from YAB or YCH.
     *
     * @param yangObject root node object
     */
    public void getModuleNodeFromYsr(Object yangObject) {
        Class interfaceClass = getInterfaceClassFromImplClass(yangObject);
        schemaRoot = appSchemaRegistry
                .getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        interfaceClass.getName());
    }

    /**
     * Returns schema root node, received from YSR, which searches based on
     * the object received from YNH.
     *
     * @param notificationEventObject notification object
     */
    public void getRootYangNodeWithNotificationFromYsr(
            Object notificationEventObject) {
        schemaRoot = appSchemaRegistry.
                getRootYangSchemaNodeForNotification(
                        notificationEventObject.getClass().getName());
    }

    /**
     * Creates the module node for in YDT before beginning with notification
     * root node traversal. Collects sufficient information to fill YDT with
     * notification root node in the traversal.
     */
    public void createModuleNodeInYdt() {

        // Adds the module node to the logical root node.
        ydtExtendedBuilder.addChild(NONE, schemaRoot);
        /*
         Gets the schema node from root node of YSR and sets that as root
         schema node.
         */
        schemaRoot = getSchemaNodeOfNotification();
        /*
         Gets the object of notification and sets that as root YANG object.
         */
        rootObject = getYangObjectOfNotification();
    }

    /**
     * Creates the module and RPC node in the YDT tree from the logical root
     * node received from request workbench. The output node is taken from
     * the child schema of RPC node.
     *
     * @param logicalRootNode logical root node
     */
    public void createModuleAndRpcNodeInYdt(YdtExtendedContext logicalRootNode) {

        // Gets the module node from logical root node.
        YdtExtendedContext moduleNode =
                (YdtExtendedContext) logicalRootNode.getFirstChild();

        // Adds the module node to the YDT.
        ydtExtendedBuilder.addChild(NONE, moduleNode.getYangSchemaNode());

        // Gets the RPC node from the module node.
        YdtExtendedContext rpcNode =
                (YdtExtendedContext) moduleNode.getFirstChild();
        YangSchemaNode rpcSchemaNode = rpcNode.getYangSchemaNode();

        // Adds the RPC node to the YDT.
        ydtExtendedBuilder.addChild(NONE, rpcSchemaNode);

        // Defines a schema identifier for output node.
        YangSchemaNodeIdentifier schemaIdentifier =
                new YangSchemaNodeIdentifier();
        schemaIdentifier.setName(OUTPUT);
        schemaIdentifier.setNameSpace(rpcSchemaNode.getNameSpace());
        try {
            // Gets the schema node of output from RPC schema node.
            schemaRoot = rpcSchemaNode.getChildSchema(schemaIdentifier)
                    .getSchemaNode();
        } catch (DataModelException e) {
            throw new YtbException(e);
        }
    }

    /**
     * Creates YDT tree from the root object, by traversing through YANG data
     * model node,and simultaneously checking the object nodes presence and
     * walking the object also.
     */
    public void createYdtFromRootObject() {
        YangNode curSchemaNode = (YangNode) schemaRoot;
        TraversalType curTraversal = ROOT;
        YtbNodeInfo previousNodeInfoForList = null;
        YtbNodeInfo previousNodeInfoForAugmentNode = null;

        while (curSchemaNode != null) {
            /*
             * Process the node if it is being visited for the 1st time in
             * the schema.
             * If the schema node is being retraced to a multi instance node,
             * check if it has multi instance node.
             */
            if (curTraversal != PARENT || isMultiInstanceNode(curSchemaNode)) {

                if (curTraversal == PARENT &&
                        isMultiInstanceNode(curSchemaNode)) {
                    /*
                     If the schema is being retraced for a multi-instance node,
                     means, it has already entered for this multi-instance
                     node earlier.
                     Now we need to re process the same schema node for the
                     any additional list object.
                    */
                    previousNodeInfoForList =
                            getCurNodeYtbInfoAndTraverseToYtbParent();
                }

                if (curTraversal == ROOT && !isAugmentNode(curSchemaNode)) {
                    /*
                     In case of RPC output, the root node is augmentative, so
                     when the root traversal is coming for augment this flow
                     must be skipped. This is only to add the root node in
                     the YDT not for augment.
                     */
                    processApplicationRootNode();
                } else {
                    Object processedObjectNode;
                    /*
                     Get the data from the object corresponding to the
                     current schema node. If present add the corresponding
                     YDT node to the tree and return the object. Otherwise
                     return null;
                     */
                    processedObjectNode = processCurSchemaNodeAndAddToYdt(
                            curSchemaNode, previousNodeInfoForList);
                    if (processedObjectNode == null && !isAugmentNode(
                            curSchemaNode)) {
                        /*
                         The Yang object does not have the attribute
                         corresponding to current schema node. Otherwise, if
                         it was a list object, all the instance of the list
                         object is already added to the YDT. If it is an
                         augment node whose object is null, check if it has
                         another augment sibling.
                         */
                        if (isProcessableNode(curSchemaNode.getNextSibling())) {
                            /*
                             If there is any sibling, then change the current
                             schema to sibling since the YDT, is still the
                             parent, new node will be added under current YDT
                             parent.
                             */
                            curTraversal = SIBLING;
                            curSchemaNode = curSchemaNode.getNextSibling();
                        } else {
                            /*
                             Change the current schema to parent. Since the
                             current schema is traversing to parent.
                             */
                            curTraversal = PARENT;
                            curSchemaNode = curSchemaNode.getParent();
                        }
                        continue;
                        /*
                         If it is an augment node whose object is null,
                         check if it has another augment sibling, set its
                         traversal as parent, irrespective of root or parent,
                         so it can check its sibling augment.
                         */
                    } else if (processedObjectNode == null &&
                            isAugmentNode(curSchemaNode)) {
                        curTraversal = PARENT;
                        /*
                         The second content in the list will be having parent
                         traversal, in such case it cannot go to its child in
                         the flow, hence that is made as child traversal and
                         proceeded to continue.
                         */
                    } else if (curTraversal == PARENT &&
                            isMultiInstanceNode(curSchemaNode)) {
                        curTraversal = CHILD;
                    }
                }
            }
            /*
             When augment node reaches, its sibling augment has to be
             checked. From the augmented node the previous node info is taken
             for augment and the traversal is changed to root, so as to
             check for the presence of sibling augment.
             */
            if (curTraversal == PARENT && isAugmentNode(curSchemaNode)) {
                curSchemaNode = ((YangAugment) curSchemaNode)
                        .getAugmentedNode();
                previousNodeInfoForAugmentNode = getParentYtbInfo();
                curTraversal = ROOT;
            }
            /*
             Whenever an augmentative node comes, its augment iterator is
             created for the first time or taken for more than one time. If
             augment is present it goes back for processing. If its null, the
             augmentative nodes process is continued.
             */
            if (curTraversal != PARENT &&
                    curSchemaNode instanceof YangAugmentableNode) {
                YangNode augmentNode = getAugmentInsideSchemaNode(
                        curSchemaNode, previousNodeInfoForAugmentNode);
                if (augmentNode != null) {
                    curSchemaNode = augmentNode;
                    continue;
                }
            }

            /*
             After processing the node, its child is processed. If complete
             child depth is over, its sibling is taken up and processed. If
             child and sibling is over, its traversed to parent without
             processing.
             In multi instance case, before going to parent or schema sibling,
             its own list sibling has to be processed.
             RPC,notification and augment has different flow to be processed,
             hence in normal check those are skipped.
             */
            if (curTraversal != PARENT && isProcessableNode(
                    curSchemaNode.getChild())) {
                previousNodeInfoForList = null;
                curTraversal = CHILD;
                curSchemaNode = curSchemaNode.getChild();
            } else if (isProcessableNode(curSchemaNode.getNextSibling())) {
                if (isMultiInstanceNode(curSchemaNode)) {
                    previousNodeInfoForList =
                            getCurNodeYtbInfoAndTraverseToYtbParent();
                    previousNodeInfoForAugmentNode = null;
                    continue;
                }
                curTraversal = SIBLING;
                traverseToParent(curSchemaNode);
                curSchemaNode = curSchemaNode.getNextSibling();
            } else {
                if (isMultiInstanceNode(curSchemaNode)) {
                    previousNodeInfoForList =
                            getCurNodeYtbInfoAndTraverseToYtbParent();
                    previousNodeInfoForAugmentNode = null;
                    continue;
                }
                curTraversal = PARENT;
                traverseToParent(curSchemaNode);
                curSchemaNode = curSchemaNode.getParent();
            }
        }
    }

    /**
     * Processes root node and add as a child to the YDT extended builder
     * which is created earlier.
     */
    private void processApplicationRootNode() {

        YtbNodeInfo nodeInfo = new YtbNodeInfo();
        YangNode rootSchemaYangNode = (YangNode) schemaRoot;
        addChildNodeInYdt(rootObject, rootSchemaYangNode, nodeInfo);
        // If root node has leaf or leaf-list those will be processed.
        processLeafEntry(rootSchemaYangNode);
        processLeafListEntry(rootSchemaYangNode);
    }

    /**
     * YDT extended builder traversal to parent is done based on the schema
     * node that requires to be traversed. Choice and case nodes are not
     * added in YDT and hence when they come for traverse to parent, it is
     * skipped.
     *
     * @param curSchemaNode current schema node
     */
    private void traverseToParent(YangNode curSchemaNode) {
        if (curSchemaNode instanceof YangCase ||
                curSchemaNode instanceof YangChoice) {
            return;
        }
        ydtExtendedBuilder.traverseToParentWithoutValidation();
    }

    /**
     * Returns the current YTB info of the YDT builder, and then traverses back
     * to parent. In case of multi instance node the previous node info is
     * used for iterating through.
     *
     * @return current YTB app info
     */
    private YtbNodeInfo getCurNodeYtbInfoAndTraverseToYtbParent() {
        YtbNodeInfo appInfo = getParentYtbInfo();
        ydtExtendedBuilder.traverseToParentWithoutValidation();
        return appInfo;
    }

    /**
     * Returns augment node for a node which got augmented. From the list of
     * augment nodes it has, one of the nodes is taken and provided linearly.
     *
     * @param curSchemaNode   current schema node
     * @param augmentNodeInfo previous augment node info
     * @return YANG augment node
     */
    private YangNode getAugmentInsideSchemaNode(YangNode curSchemaNode, YtbNodeInfo augmentNodeInfo) {
        if (augmentNodeInfo == null) {
            List<YangAugment> augmentList = ((YangAugmentableNode) curSchemaNode).getAugmentedInfoList();
            if (augmentList != null && !augmentList.isEmpty()) {
                YtbNodeInfo parentNodeInfo = getParentYtbInfo();
                Iterator<YangAugment> augmentNodeListIterator = augmentList.listIterator();
                parentNodeInfo.setAugmentNodeIterator(augmentNodeListIterator);
                return augmentNodeListIterator.next();
            }
        } else {
            if (augmentNodeInfo.getAugmentNodeIterator() != null) {
                Iterator<YangAugment> augmentNodeListIterator = augmentNodeInfo.getAugmentNodeIterator();
                if (augmentNodeListIterator.hasNext()) {
                    return augmentNodeListIterator.next();
                }
            }
        }
        return null;
    }

    /**
     * Processes the current schema node and if necessary adds it to the YDT
     * builder tree by extracting the information from the corresponding
     * class object.
     *
     * @param currentSchemaNode       current schema node
     * @param previousNodeInfoForList previous node info for list
     * @return object of the schema node
     */
    private Object processCurSchemaNodeAndAddToYdt(
            YangNode currentSchemaNode, YtbNodeInfo previousNodeInfoForList) {
        YtbNodeInfo curNodeInfo = new YtbNodeInfo();
        Object nodeObject = null;
        YtbNodeInfo parentNodeInfo = getParentYtbInfo();

        switch (currentSchemaNode.getYangSchemaNodeType()) {
            case YANG_SINGLE_INSTANCE_NODE:
                nodeObject = processSingleInstanceNode(
                        currentSchemaNode, curNodeInfo, parentNodeInfo);
                break;
            case YANG_MULTI_INSTANCE_NODE:
                nodeObject = processMultiInstanceNode(
                        currentSchemaNode, curNodeInfo,
                        previousNodeInfoForList, parentNodeInfo);
                break;
            case YANG_CHOICE_NODE:
                nodeObject = processChoiceNode(currentSchemaNode,
                                               parentNodeInfo);
                break;
            case YANG_NON_DATA_NODE:
                if (currentSchemaNode instanceof YangCase) {
                    nodeObject = processCaseNode(currentSchemaNode,
                                                 parentNodeInfo);
                }
                break;
            case YANG_AUGMENT_NODE:
                nodeObject = processAugmentNode(currentSchemaNode,
                                                parentNodeInfo);
                break;
            default:
                throw new YtbException(
                        "Non processable schema node has arrived for adding " +
                                "it in YDT tree");
        }
        if (nodeObject != null) {
            processLeafEntry(currentSchemaNode);
            processLeafListEntry(currentSchemaNode);
        }
        return nodeObject;
    }

    /**
     * Processes single instance node which has to be added to the YDT tree.
     *
     * @param currentSchemaNode current schema node
     * @param curNodeInfo       current YDT node info
     * @param parentNodeInfo    parent YDT node info
     * @return object of the current node
     */
    private Object processSingleInstanceNode(YangNode currentSchemaNode,
                                             YtbNodeInfo curNodeInfo,
                                             YtbNodeInfo parentNodeInfo) {
        // Gets the child object from parent YDT info.
        Object childObject = getChildObject(currentSchemaNode, parentNodeInfo);
        if (childObject != null) {
            // Adds the current node as child to YDT tree.
            addChildNodeInYdt(childObject, currentSchemaNode, curNodeInfo);
        }
        return childObject;
    }

    /**
     * Processes multi instance node which has to be added to the YDT tree.
     * For the first instance in the list, iterator is created and added to
     * the list. For second instance or more the iterator from first instance
     * is taken and iterated through to get the object.
     *
     * @param currentSchemaNode current list schema node
     * @param curNodeInfo       current node info for list
     * @param previousNodeInfo  previous instance node info
     * @param parentNodeInfo    parent node info of list
     * @return object of the current instance
     */
    private Object processMultiInstanceNode(YangNode currentSchemaNode,
                                            YtbNodeInfo curNodeInfo,
                                            YtbNodeInfo previousNodeInfo,
                                            YtbNodeInfo parentNodeInfo) {
        Object childObject = null;
        /*
         When YANG list comes to this flow for first time, its YTB node will
         be null. When it comes for the second or more content then the list
         would have been already set for that node. According to set or not
         set this flow will be proceeded.
         */
        if (previousNodeInfo == null) {
            // Gets the child object from parent YDT info.
            List<Object> childObjectList = (List<Object>) getChildObject(
                    currentSchemaNode, parentNodeInfo);

            if (childObjectList != null) {
                Iterator<Object> listIterator = childObjectList.iterator();
                if (!listIterator.hasNext()) {
                    return null;
                    //TODO: Handle the subtree filtering with no list entries.
                }
                // First object in the list is taken.
                childObject = listIterator.next();
                /*
                 For that node the iterator is set. So the next time for the
                 list this iterator will be taken.
                 */
                curNodeInfo.setCurrentListIterator(listIterator);
            }
        } else {
            /*
             If the list value comes for second or more time, that list node
             will be having YTB node info, where iterator can be retrieved
             and check if any more contents are present. If present those
             will be processed.
             */
            curNodeInfo.setCurrentListIterator(previousNodeInfo
                                                       .getCurrentListIterator());
            if (previousNodeInfo.getCurrentListIterator().hasNext()) {
                childObject = previousNodeInfo
                        .getCurrentListIterator().next();
            }
        }
        if (childObject != null) {
            // Adds the current node as child to YDT tree.
            addChildNodeInYdt(childObject, currentSchemaNode, curNodeInfo);
        }
        return childObject;
    }

    /**
     * Processes choice node which has to add map to the parent node info of
     * choice name and the case object.
     *
     * @param currentSchemaNode current schema node of choice
     * @param parentNodeInfo    parent YTB node info
     * @return object of the choice node
     */
    private Object processChoiceNode(YangNode currentSchemaNode,
                                     YtbNodeInfo parentNodeInfo) {
        /*
         Retrieves the parent YTB info, mainly the YANG object of root node,
         so as to check the child attribute from the node.
         */
        Object childObject = getChildObject(currentSchemaNode, parentNodeInfo);
        if (childObject != null) {
            Map<String, Object> choiceAndCaseMap = parentNodeInfo.getChoiceAndCaseMap();
            if (choiceAndCaseMap == null) {
                choiceAndCaseMap = new HashMap<>();
                choiceAndCaseMap.put(currentSchemaNode.getName(), childObject);
            } else {
                choiceAndCaseMap.put(currentSchemaNode.getName(), childObject);
            }
            parentNodeInfo.setChoiceAndCaseMap(choiceAndCaseMap);
        }
        return childObject;
    }

    /**
     * Processes case node, whose object is nothing but the choice object.
     * From the map contents that is filled by choice nodes, case takes its
     * object with the choice key name.
     *
     * @param currentSchemaNode current case schema node
     * @param parentNodeInfo    parent node info of choice
     * @return object of the case node
     */
    private Object processCaseNode(YangNode currentSchemaNode,
                                   YtbNodeInfo parentNodeInfo) {
        Object childObject = null;
        if (parentNodeInfo.getChoiceAndCaseMap() != null) {
            // Gets the case object from the map that is filled by choice.
            childObject = getCaseObjectFromChoice(parentNodeInfo,
                                                  currentSchemaNode);
        }
        if (childObject != null) {
            /*
             If case object is present set it as case object in the parent
             YTB node so that case child can take it fill it in the YDT tree
             */
            parentNodeInfo.setCaseObject(childObject);
        }
        return childObject;
    }

    /**
     * Processes augment node, which need not to be added in the YDT but it
     * has to bind itself to the parent YTB info, so rest of its child nodes
     * can use for adding themselves to the YDT tree.
     *
     * @param currentSchemaNode current augment schema node
     * @param parentNodeInfo    parent node info of augment
     * @return object of the augment node
     */
    private Object processAugmentNode(YangNode currentSchemaNode,
                                      YtbNodeInfo parentNodeInfo) {
        Object childObject;
        String augmentClassName = currentSchemaNode
                .getJavaClassNameOrBuiltInType();
        String augmentPackageName = currentSchemaNode.getJavaPackage();
        // Gets the parent object of the augment node.
        Object objectOfTheParentNode = getParentObjectForTheNode(parentNodeInfo,
                                                                 currentSchemaNode);
        // Gets the augment map info from the parent object.
        Map childObjectMap = (Map) getAttributeOfObject(
                objectOfTheParentNode, YANG_AUGMENTED_INFO_MAP);

        // Gets the class loader of the augment node.
        ClassLoader classLoader = getClassLoaderForAugment(currentSchemaNode,
                                                           appSchemaRegistry);
        Class classOfAugment;

        try {
            classOfAugment = classLoader.loadClass(augmentPackageName + PERIOD +
                                                           augmentClassName);
        } catch (ClassNotFoundException e) {
            throw new YtbException(e);
        }
        // Gets the augment object from the map of augments.
        childObject = childObjectMap.get(classOfAugment);
        parentNodeInfo.setAugmentObject(childObject);
        return childObject;
    }

    /**
     * Returns the YTB info from the parent node, so that the object can be
     * taken out.
     *
     * @return YTB node info of the parent node
     */
    private YtbNodeInfo getParentYtbInfo() {
        YdtExtendedContext parentYdtExtendedContext =
                (YdtExtendedContext) ydtExtendedBuilder.getCurNode();
        return (YtbNodeInfo) parentYdtExtendedContext
                .getAppInfo(AppType.YTB);
    }

    /**
     * Returns the child object from the parent object.
     *
     * @param currentSchemaNode current schema node
     * @param parentNodeInfo    parent YTB node info
     * @return object of the child node
     */
    private Object getChildObject(YangNode currentSchemaNode, YtbNodeInfo parentNodeInfo) {
        String nodeJavaName = currentSchemaNode.getJavaAttributeName();
        Object objectOfTheParentNode = getParentObjectForTheNode(
                parentNodeInfo, currentSchemaNode);
        // From the parent object get the current schema node object
        return getAttributeOfObject(objectOfTheParentNode, nodeJavaName);
    }

    /**
     * Adds the child node to the YDT by taking operation type from the
     * object simultaneously binding the object to the YDT node through YTB
     * node info.
     *
     * @param childObject       object of the node
     * @param currentSchemaNode current schema node
     * @param curNodeInfo       current YTB info
     */
    private void addChildNodeInYdt(Object childObject, YangNode
            currentSchemaNode, YtbNodeInfo curNodeInfo) {
        // Gets the operation type from the child object.
        YdtContextOperationType operationType =
                getOperationTypeOfTheNode(childObject);

        // Adds the child to the YDT node.
        ydtExtendedBuilder.addChild(operationType, currentSchemaNode);
        YdtExtendedContext currentYdtNode = (YdtExtendedContext)
                ydtExtendedBuilder.getCurNode();
        /*
         Takes the added node and binds the current YTB node info by setting
         the YANG object as the child object.
         */
        curNodeInfo.setYangObject(childObject);
        currentYdtNode.addAppInfo(AppType.YTB, curNodeInfo);
    }

    /**
     * Processes leaf entry for YANG schema node.
     *
     * @param yangSchemaNode YANG schema node
     */
    private void processLeafEntry(YangNode yangSchemaNode) {
        if (yangSchemaNode instanceof YangLeavesHolder) {
            List<YangLeaf> listOfLeaves = ((YangLeavesHolder) yangSchemaNode)
                    .getListOfLeaf();
            if (listOfLeaves != null && !listOfLeaves.isEmpty()) {
                Iterator<YangLeaf> yangLeafIterator = listOfLeaves
                        .listIterator();
                while (yangLeafIterator.hasNext()) {

                    // Initially sets the leaf type object as false.
                    boolean leafTypeObject = false;

                    // Gets the leaf and its java name
                    YangLeaf yangLeaf = yangLeafIterator.next();
                    JavaLeafInfoContainer leafInfo =
                            (JavaLeafInfoContainer) yangLeaf;
                    String javaNameOfLeaf = leafInfo.getJavaName(null);

                    YtbNodeInfo ytbNodeInfo = getParentYtbInfo();

                    String fieldValue = null;
                    Object objectOfTheParentNode = getParentObjectForTheLeafOrLeafList(
                            ytbNodeInfo, yangSchemaNode);
                    Object typeOfLeaf = getAttributeOfObject(
                            objectOfTheParentNode, javaNameOfLeaf);

                    // Checks of leaf type is primitive.
                    if (isTypePrimitive(yangLeaf.getDataType())) {

                        // Checks if value leaf is set.
                        String valueOfLeaf = isValueOrSelectLeafSet(
                                objectOfTheParentNode, javaNameOfLeaf,
                                IS_LEAF_VALUE_SET_METHOD);
                        /*
                         If value leaf is set, then the object value is taken.
                         */
                        if (valueOfLeaf.equals(TRUE)) {
                            fieldValue = getStringFromDataType(
                                    typeOfLeaf, yangLeaf.getDataType());
                        }
                    } else if (typeOfLeaf != null) {
                        /*
                         If leaf type is not primitive, value is taken from
                         object
                         */
                        fieldValue = getStringFromDataType(
                                typeOfLeaf, yangLeaf.getDataType());
                    }
                    /*
                     The value is added only when the content in the value is
                     present.
                     */
                    if (fieldValue != null &&
                            !fieldValue.equals(STR_NULL) &&
                            !fieldValue.isEmpty()) {
                        ydtExtendedBuilder.addLeaf(fieldValue, yangLeaf);
                        leafTypeObject = true;
                    }
                    // Checks if select leaf is set.
                    String selectLeaf = isValueOrSelectLeafSet(
                            objectOfTheParentNode, javaNameOfLeaf,
                            IS_SELECT_LEAF_SET_METHOD);
                    if (selectLeaf.equals(TRUE)) {
                        /*
                         If select leaf is set then the value is added to YDT
                         with null as value
                         */
                        ydtExtendedBuilder.addLeaf("", yangLeaf);
                        leafTypeObject = true;
                    }
                    // Only when leaf child is added traverse back to parent.
                    if (leafTypeObject) {
                        ydtExtendedBuilder.traverseToParentWithoutValidation();
                    }
                }
            }
        }
    }

    /**
     * Processes leaf list entry for YANG schema node.
     *
     * @param yangSchemaNode YANG schema node
     */
    private void processLeafListEntry(YangNode yangSchemaNode) {
        if (yangSchemaNode instanceof YangLeavesHolder) {
            List<YangLeafList> listOfLeafList =
                    ((YangLeavesHolder) yangSchemaNode).getListOfLeafList();
            if (listOfLeafList != null && !listOfLeafList.isEmpty()) {
                Iterator<YangLeafList> leafListIterator =
                        listOfLeafList.listIterator();
                while (leafListIterator.hasNext()) {

                    YangLeafList yangLeafList = leafListIterator.next();
                    JavaLeafInfoContainer leafListInfo =
                            (JavaLeafInfoContainer) yangLeafList;
                    String javaNameOfLeafList = leafListInfo.getJavaName(null);

                    YtbNodeInfo ytbNodeInfo = getParentYtbInfo();
                    Object objectOfTheParentNode = getParentObjectForTheLeafOrLeafList(
                            ytbNodeInfo, yangSchemaNode);

                    //TODO: Let the received object list be generic collection.
                    List<Object> leafListObject =
                            (List<Object>) getAttributeOfObject(
                                    objectOfTheParentNode, javaNameOfLeafList);
                    Set<String> leafListValue = new HashSet<>();
                    /*
                     If list is present, then add as child to the parent,
                     consecutively traverse back to parent.
                     */
                    if (leafListObject != null &&
                            !leafListObject.isEmpty()) {
                        Iterator<Object> objectIterator = leafListObject
                                .iterator();
                        while (objectIterator.hasNext()) {
                            String strValue = getStringFromDataType(
                                    objectIterator.next(),
                                    yangLeafList.getDataType());
                            leafListValue.add(strValue);
                        }
                        ydtExtendedBuilder.addLeaf(leafListValue, yangLeafList);
                        ydtExtendedBuilder.traverseToParentWithoutValidation();
                    }
                }
            }
        }
    }

    /**
     * Returns the schema node of notification from the root node. Gets the
     * enum value from event object and gives it to the root schema node for
     * getting back the notification schema node.
     *
     * @return YANG schema node of notification
     */
    private YangSchemaNode getSchemaNodeOfNotification() {
        // Gets the abstract class.
        Class parentClass = rootObject.getClass().getSuperclass();

        // Gets the attribute object from abstract class and parent object.
        Object typeOfEventObject = getAttributeFromInheritance(
                parentClass, rootObject, STR_TYPE);
        String valueOfOpType = String.valueOf(typeOfEventObject);

        if (valueOfOpType.equals(STR_NULL) || valueOfOpType.isEmpty()) {
            throw new YtbException("There is no notification present for the " +
                                           "event. Invalid input for " +
                                           "notification.");
        }
        try {
            // From the module node gets the notification node.
            return schemaRoot.getNotificationSchemaNode(valueOfOpType);
        } catch (DataModelException e) {
            throw new YtbException(e);
        }
    }

    /**
     * Returns the object of the notification class by retrieving the
     * attributes from the event class object.
     *
     * @return notification YANG object
     */
    private Object getYangObjectOfNotification() {
        Class parentClass = rootObject.getClass().getSuperclass();
        Object eventSubjectObject = getAttributeFromInheritance(
                parentClass, rootObject, STR_SUBJECT);
        String notificationName = schemaRoot.getJavaAttributeName();
        return getAttributeOfObject(eventSubjectObject, notificationName);
    }

    /**
     * Returns case object from the map that is bound to the parent node
     * info. For any case node, only when the key and value is matched the
     * object of the case is provided.
     *
     * @param parentNodeInfo parent YTB node info
     * @param caseNode       case schema node
     * @return object of the case node
     */
    private Object getCaseObjectFromChoice(YtbNodeInfo parentNodeInfo,
                                           YangSchemaNode caseNode) {

        // TODO: Attribute name is not given for case in YANG utils. Needs to
        // be analysed and changed.

        JavaFileInfoContainer javaFileInfoContainer =
                (JavaFileInfoContainer) caseNode;
        JavaFileInfoTranslator fileInfo = javaFileInfoContainer
                .getJavaFileInfo();
        String caseJavaName = fileInfo.getJavaName();

        // Gets the map of choice and case in the holder.
        Map choiceAndCaseObject = parentNodeInfo.getChoiceAndCaseMap();
        Iterator choiceAndCaseObjectIterator = choiceAndCaseObject
                .entrySet().iterator();

        while (choiceAndCaseObjectIterator.hasNext()) {
            Map.Entry choiceCase = (Map.Entry) choiceAndCaseObjectIterator
                    .next();
            Object caseObject = choiceCase.getValue();
            Object choiceName = choiceCase.getKey();

            /*
             Compares the case class name from node and object. Also the
             choice name from case node parent and the key which is filled.
             */
            if (caseObject.getClass().getSimpleName().equals(
                    DEFAULT + getCapitalCase(caseJavaName)) && choiceName
                    .equals(((YangNode) caseNode).getParent().getName())) {
                return caseObject;
            }
        }
        return null;
    }
}
