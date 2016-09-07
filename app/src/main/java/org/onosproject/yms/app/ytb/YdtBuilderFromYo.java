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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.onosproject.yangutils.datamodel.YangLeaf;
import org.onosproject.yangutils.datamodel.YangLeafList;
import org.onosproject.yangutils.datamodel.YangLeafRef;
import org.onosproject.yangutils.datamodel.YangLeavesHolder;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangNotification;
import org.onosproject.yangutils.datamodel.YangRpc;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangType;
import org.onosproject.yangutils.datamodel.exceptions.DataModelException;
import org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes;
import org.onosproject.yangutils.translator.tojava.javamodel.JavaLeafInfoContainer;
import org.onosproject.yms.app.ydt.AppType;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContextOperationType;

import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_MULTI_INSTANCE_NODE;
import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_SINGLE_INSTANCE_NODE;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.BOOLEAN;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.DECIMAL64;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.EMPTY;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.INT16;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.INT32;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.INT64;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.INT8;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.LEAFREF;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.UINT16;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.UINT32;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.UINT64;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.UINT8;
import static org.onosproject.yms.app.ytb.TraversalType.CHILD;
import static org.onosproject.yms.app.ytb.TraversalType.PARENT;
import static org.onosproject.yms.app.ytb.TraversalType.ROOT;
import static org.onosproject.yms.app.ytb.TraversalType.SIBLING;
import static org.onosproject.yms.ydt.YdtContextOperationType.NONE;

/**
 * Represents traversal through YANG node and its corresponding object,
 * resulting in building of the YDT tree.
 */
public class YdtBuilderFromYo {

    private static final String OPERATION_TYPE = "onosYangNodeOperationType";
    private static final String STR_NONE = "NONE";
    private static final String TRUE = "true";
    private static final String ENUM_LEAF_IDENTIFIER = "$LeafIdentifier";
    private static final String IS_LEAF_VALUE_SET_METHOD = "isLeafValueSet";
    private static final String EQUALS_TO = "=";
    private static final String STR_NULL = "null";
    private static final String STR_TYPE = "type";
    private static final String STR_SUBJECT = "subject";
    private static final String SCHEMA_NAME_IN_ENUM = "schemaName";

    /**
     * YANG schema registry of the application.
     */
    private YangSchemaRegistry appSchemaRegistry;

    /**
     * Current instance of the YDT builder.
     */
    private YdtExtendedBuilder ydtExtendedBuilder;

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
     * Sets YDT extended builder.
     *
     * @param ydtExtendedBuilder YDT extended builder
     */
    public void setYdtExtendedBuilder(YdtExtendedBuilder ydtExtendedBuilder) {
        this.ydtExtendedBuilder = ydtExtendedBuilder;
    }

    /**
     * Returns YDT extended builder.
     *
     * @return YDT extended builder
     */
    public YdtExtendedBuilder getYdtExtendedBuilder() {
        return ydtExtendedBuilder;
    }

    /**
     * Returns root node object.
     *
     * @return root node object
     */
    public Object getRootObject() {
        return rootObject;
    }

    /**
     * Sets root node object.
     *
     * @param rootObject root node object
     */
    public void setRootObject(Object rootObject) {
        this.rootObject = rootObject;
    }

    /**
     * Returns schema root node.
     *
     * @return schema root node
     */
    public YangSchemaNode getSchemaRoot() {
        return schemaRoot;
    }

    /**
     * Returns application's YANG schema registry.
     *
     * @return schema registry of application
     */
    public YangSchemaRegistry getAppSchemaRegistry() {
        return appSchemaRegistry;
    }

    /**
     * Sets application's YANG schema registry.
     *
     * @param appSchemaRegistry schema registry of application
     */
    public void setAppSchemaRegistry(YangSchemaRegistry appSchemaRegistry) {
        this.appSchemaRegistry = appSchemaRegistry;
    }

    /**
     * Sets schema root node.
     *
     * @param schemaRoot schema root node
     */
    public void setSchemaRoot(YangSchemaNode schemaRoot) {
        this.schemaRoot = schemaRoot;
    }

    /**
     * Creates object and gets the schema YANG node from YSR.
     *
     * @param logicalRootModuleBuilder logical root node builder
     * @param rootObject               logical root node object
     * @param appSchemaRegistry        YANG schema registry of the application
     */
    public YdtBuilderFromYo(YdtExtendedBuilder logicalRootModuleBuilder,
                            Object rootObject, YangSchemaRegistry
                                    appSchemaRegistry) {
        ydtExtendedBuilder = logicalRootModuleBuilder;
        this.rootObject = rootObject;
        this.appSchemaRegistry = appSchemaRegistry;
    }

    /**
     * Returns schema root node received from YSR from root object from YAB/YSB.
     *
     * @param yangObject root node object
     */
    public void getModuleNodeFromYsr(Object yangObject) {
        Class interfaceClass = getInterfaceClassFromImplClass(yangObject);
        YangSchemaNode schemaRootNode = getAppSchemaRegistry()
                .getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        interfaceClass.getName());
        setSchemaRoot(schemaRootNode);
    }

    /**
     * Returns schema notification node received from YSR from notification
     * object from YAB/YSB.
     *
     * @param notificationObject notification object
     */
    public void getRootYangNodeWithNotificationFromYsr(
            Object notificationObject) {
        YangSchemaNode schemaNotificationNode = getAppSchemaRegistry().
                getRootYangSchemaNodeForNotification(
                        notificationObject.getClass().getName());
        setSchemaRoot(schemaNotificationNode);
    }

    public void createModuleNodeInYdt() {

        // Adds the module node to the logical root node.
        ydtExtendedBuilder.addChild(NONE, getSchemaRoot());
        try {
            // Gets the schema node from root node of YSR and sets that as root schema node.
            setSchemaRoot(getSchemaNodeOfNotification());
            // Gets the YANG object class of notification and sets that as root YANG object.
            setRootObject(getYangObjectOfNotification());
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException |
                DataModelException e) {
            throw new YtbException(e.getMessage());
        }
    }

    /**
     * Creates YDT tree from the root object, by traversing through YANG data
     * model node, and simultaneously checking the object nodes presence and
     * walking the object also.
     */
    public void createYdtFromRootObject() {
        YangNode curSchemaNode = (YangNode) getSchemaRoot();
        TraversalType curTraversal = ROOT;
        YtbNodeInfo previousNodeInfo = null;

        while (curSchemaNode != null) {

            /*
             * Process the node if it is being visited for the 1st time in the schema.
             * If the schema node is being retraced to a multi instance node, check if it has multi instance node.
             */
            if (curTraversal != PARENT
                    || curSchemaNode.getYangSchemaNodeType() ==
                    YANG_MULTI_INSTANCE_NODE) {
                Object processedObjectNode;

                if (curTraversal == PARENT
                        && curSchemaNode.getYangSchemaNodeType() ==
                        YANG_MULTI_INSTANCE_NODE) {
                    /*
                     If the schema is being retraced for a multi-instance node,
                     means, it has already entered for this multi-instance node earlier.
                     Now we need to re process the same schema node for the any additional list object.
                    */
                    previousNodeInfo =
                            getCurNodeYtbInfoAndTraverseToYtbParent();
                }

                try {
                    if (curTraversal == ROOT) {
                    /*
                     * Add the application root node to YDT.
                     */
                        processApplicationRootNode();
                    } else {
                        /*
                         * Get the data from the object corresponding to the current schema node.
                         * If present add the corresponding YDT node to the tree and return the object.
                         * Otherwise return null;
                         */
                        processedObjectNode =
                                processCurSchemaNodeAndAddToYdt(curSchemaNode,
                                                                previousNodeInfo);

                        previousNodeInfo = null;

                        if (processedObjectNode == null) {
                        /*
                        The Yang object does not have the attribute corresponding to current schema node.
                        Otherwise, if it was a list object, all the instance of the list object is already
                        added to the YDT. None of the notification flow can be processed as sibling, because
                        notification can only be a root node.
                         */
                            if (curSchemaNode.getNextSibling() != null &&
                                    !(curSchemaNode.getNextSibling()
                                            instanceof YangNotification) &&
                                    !(curSchemaNode.getNextSibling() instanceof
                                            YangRpc)) {
                            /*If there is any sibling, then change the current schema to sibling
                            * since the YDT, is still the parent, new node will be added under current
                            * YDT parent
                            */
                                curTraversal = SIBLING;
                                curSchemaNode = curSchemaNode.getNextSibling();
                            } else {

                            /*change the current schema to parent.
                            * Since the current schema is traversing to parent.
                            */
                                curTraversal = PARENT;
                                curSchemaNode = curSchemaNode.getParent();
                            }
                            continue;
                            /*
                            If multi instance root node has come for greater than one in the list, the rest of the
                            children must be processed for the list as it is. So parent is made to go and check for
                            the child in the complete list.
                             */
                        } else if (curTraversal == PARENT) {
                            curTraversal = CHILD;
                        }
                    }
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                        NoSuchFieldException e) {
                    throw new YtbException(e.getMessage());
                }

            }

            /*
            None of the notification flow can be processed as sibling or child, because
            notification can only be a root node.
             */
            if (curTraversal != PARENT && curSchemaNode.getChild() != null &&
                    !(curSchemaNode.getChild()
                            instanceof YangNotification) &&
                    !(curSchemaNode.getChild() instanceof
                            YangRpc)) {
                previousNodeInfo = null;
                curTraversal = CHILD;
                curSchemaNode = curSchemaNode.getChild();
            } else if (curSchemaNode.getNextSibling() != null &&
                    !(curSchemaNode.getNextSibling() instanceof
                            YangNotification) &&
                    !(curSchemaNode.getNextSibling() instanceof
                            YangRpc)) {
                if (curSchemaNode.getYangSchemaNodeType() ==
                        YANG_MULTI_INSTANCE_NODE) {
                    previousNodeInfo =
                            getCurNodeYtbInfoAndTraverseToYtbParent();
                    continue;
                }

                curTraversal = SIBLING;
                getYdtExtendedBuilder().traverseToParentExtended();
                curSchemaNode = curSchemaNode.getNextSibling();
            } else {
                if (curSchemaNode.getYangSchemaNodeType() ==
                        YANG_MULTI_INSTANCE_NODE) {
                    previousNodeInfo =
                            getCurNodeYtbInfoAndTraverseToYtbParent();
                    continue;
                }
                curTraversal = PARENT;
                getYdtExtendedBuilder().traverseToParentExtended();
                curSchemaNode = curSchemaNode.getParent();
            }
        }
    }

    /**
     * Process root node and add as a child to the YDT extended builder obtained
     * from logical root node.
     */
    private void processApplicationRootNode()
            throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException, NoSuchFieldException {

        // Gets the operation type of the root node.
        YdtContextOperationType operationType =
                getOperationTypeOfTheNode(rootObject);
        // Adds root node as the child to the logical root node in YDT.
        ydtExtendedBuilder.addChild(operationType, getSchemaRoot());

        // Sets the YTB info of the root YDT node, such as root object, and application info.
        YdtExtendedContext currentYdtNode =
                (YdtExtendedContext) ydtExtendedBuilder.getCurNode();
        YtbNodeInfo nodeInfo = new YtbNodeInfo();
        nodeInfo.setYangObject(getRootObject());
        nodeInfo.setSchemaNodeType(YANG_SINGLE_INSTANCE_NODE);
        currentYdtNode.setAppInfo(AppType.YTB, nodeInfo);

        // If root node has leaf or leaf-list those will be processed.
        processLeafEntry((YangNode) getSchemaRoot());
        processLeafListEntry((YangNode) getSchemaRoot());
    }

    /**
     * Returns the current YTB info for corresponding YDT builder,
     * also makes YDT builder to switch back to parent.
     *
     * @return current YTB app info
     */
    private YtbNodeInfo getCurNodeYtbInfoAndTraverseToYtbParent() {
        YdtExtendedContext ydtExtendedContext
                = (YdtExtendedContext) ydtExtendedBuilder.getCurNode();
        YtbNodeInfo appInfo =
                (YtbNodeInfo) ydtExtendedContext.getAppInfo(AppType.YTB);
        getYdtExtendedBuilder().traverseToParentExtended();
        return appInfo;
    }

    /**
     * Processes the current schema node and if necessary adds it to the YDT
     * builder tree by extracting the information from the corresponding class
     * object.
     *
     * @param currentSchemaNode current schema node
     * @param previousNodeInfo  app info for the previous node has value when
     *                          object in the list is present
     * @return object of the schema node
     * @throws NoSuchFieldException      no field exception from reflection
     * @throws IllegalAccessException    illegal access exception from
     *                                   reflection
     * @throws NoSuchMethodException     no such method exception from
     *                                   reflection
     * @throws InvocationTargetException invocation target exception from
     *                                   reflection
     */
    private Object processCurSchemaNodeAndAddToYdt(YangNode currentSchemaNode,
                                                   YtbNodeInfo previousNodeInfo)
            throws
            InvocationTargetException, NoSuchMethodException,
            IllegalAccessException, NoSuchFieldException {
        YtbNodeInfo nodeInfo = new YtbNodeInfo();
        Object nodeObject = null;

        // Checks if it is a single instance node or multi instance node.
        if (currentSchemaNode.getYangSchemaNodeType() ==
                YANG_SINGLE_INSTANCE_NODE) {

            /*
            Retrieves the parent YTB info, mainly the YANG object of root node, so as to check the child attribute
            from the node.
             */
            YdtExtendedContext parentYdtExtendedContext =
                    (YdtExtendedContext) ydtExtendedBuilder.getCurNode();

            String nodeJavaName = currentSchemaNode.getJavaAttributeName();

            YtbNodeInfo parentNodeInfo = (YtbNodeInfo) parentYdtExtendedContext
                    .getAppInfo(AppType.YTB);

            // From the app info and the current java attribute name the object of the current schema node is retrieved.
            Object childObject =
                    getAttributeOfObject(parentNodeInfo.getYangObject(),
                                         nodeJavaName);

            if (childObject != null) {

                // When child object is present, it gets the operation type and adds the child to YDT.
                YdtContextOperationType operationType =
                        getOperationTypeOfTheNode(childObject);
                ydtExtendedBuilder.addChild(operationType, currentSchemaNode);
                YdtExtendedContext currentYdtNode =
                        (YdtExtendedContext) ydtExtendedBuilder.getCurNode();

                // Corresponding app info with the child object is set to that child node.
                nodeInfo.setSchemaNodeType(YANG_SINGLE_INSTANCE_NODE);
                nodeInfo.setYangObject(childObject);
                currentYdtNode.setAppInfo(AppType.YTB, nodeInfo);

                nodeObject = childObject;
            }
        } else if (currentSchemaNode.getYangSchemaNodeType() ==
                YANG_MULTI_INSTANCE_NODE) {
            Object childObject = null;

            /*
            When YANG list comes to this flow for first time, its YTB node will be null. When it comes for the second
             or more content then the list would have been already set for that node. According to set or not set
             this flow will be proceeded.
             */
            if (previousNodeInfo == null) {

                /*
                For the first time flow the parent node YTB node info is taken to get the attribute object of the list.
                 */
                YdtExtendedContext parentYdtExtendedContext =
                        (YdtExtendedContext) ydtExtendedBuilder.getCurNode();

                String nodeJavaName = currentSchemaNode.getJavaAttributeName();

                YtbNodeInfo parentNodeInfo =
                        (YtbNodeInfo) parentYdtExtendedContext
                                .getAppInfo(AppType.YTB);

                List<Object> childObjectList =
                        (List<Object>) getAttributeOfObject(
                                parentNodeInfo.getYangObject(),
                                nodeJavaName);
                if (childObjectList != null) {
                    Iterator<Object> listIterator = childObjectList.iterator();
                    if (!listIterator.hasNext()) {
                        return null;
                        //TODO: Handle the subtree filtering with no list entries.
                    }
                    // First object in the list is taken.
                    childObject = listIterator.next();
                    // For that node the current iterator is set, which would have completed the first content.
                    nodeInfo.setCurrentListIterator(listIterator);

                }

            } else {
                /*
                If the list value comes for second or more time, that list node will be having YTB node info, where
                iterator can be retrieved and check if any more contents are present. If present those will be
                processed.
                 */
                nodeInfo.setCurrentListIterator(
                        previousNodeInfo.getCurrentListIterator());
                if (previousNodeInfo.getCurrentListIterator().hasNext()) {
                    childObject =
                            previousNodeInfo.getCurrentListIterator().next();
                }
            }

            if (childObject != null) {
                // When the content object in the iteration is present, the child is added to YDT.
                YdtContextOperationType operationType =
                        getOperationTypeOfTheNode(childObject);
                ydtExtendedBuilder.addChild(operationType, currentSchemaNode);
                YdtExtendedContext currentYdtNode =
                        (YdtExtendedContext) ydtExtendedBuilder.getCurNode();

                // That node's YTB node info is filled with the object and is been set.
                nodeInfo.setSchemaNodeType(YANG_MULTI_INSTANCE_NODE);
                nodeInfo.setYangObject(childObject);
                currentYdtNode.setAppInfo(AppType.YTB, nodeInfo);

                nodeObject = childObject;
            }
        }
        // If the object of the current node is not null, the leaf and leaf-list are processed for that node.
        if (nodeObject != null) {
            processLeafEntry(currentSchemaNode);
            processLeafListEntry(currentSchemaNode);
        }
        return nodeObject;
    }

    /**
     * Processes leaf entry for YANG schema node.
     *
     * @param yangSchemaNode YANG schema node
     */
    private void processLeafEntry(YangNode yangSchemaNode) {
        if (yangSchemaNode instanceof YangLeavesHolder) {
            List<YangLeaf> listOfLeaves =
                    ((YangLeavesHolder) yangSchemaNode).getListOfLeaf();
            if (listOfLeaves != null && !listOfLeaves.isEmpty()) {
                Iterator<YangLeaf> yangLeafIterator =
                        listOfLeaves.listIterator();
                while (yangLeafIterator.hasNext()) {

                    // Initially sets the leaf type object as false.
                    boolean leafTypeObject = false;
                    // Gets the leaf and its java name
                    YangLeaf yangLeaf = yangLeafIterator.next();
                    JavaLeafInfoContainer leafInfo =
                            (JavaLeafInfoContainer) yangLeaf;
                    // TODO: Get java name from the schema node.
                    String javaNameOfLeaf = leafInfo.getJavaName(null);

                    // From the parent context get the object of the leaf.
                    YdtExtendedContext ydtExtendedContext =
                            (YdtExtendedContext) ydtExtendedBuilder
                                    .getCurNode();
                    YtbNodeInfo ytbNodeInfo = (YtbNodeInfo) ydtExtendedContext
                            .getAppInfo(AppType.YTB);
                    Object objectOfNode = ytbNodeInfo.getYangObject();
                    try {
                        String fieldValue = null;
                        Object typeOfLeaf = getAttributeOfObject(objectOfNode,
                                                                 javaNameOfLeaf);

                        // Checks of leaf type is primitive.
                        if (isTypePrimitive(yangLeaf.getDataType())) {
                            // If primitive check if value leaf set flag is present.
                            String valueOfLeaf =
                                    isValueLeafSetForLeaf(objectOfNode,
                                                          javaNameOfLeaf);
                            // If value set is done for that type then fetch the value of the type.
                            if (valueOfLeaf.equals(TRUE)) {
                                fieldValue = getStringFromDataType(typeOfLeaf,
                                                                   yangLeaf.getDataType());
                            }
                        } else if (typeOfLeaf != null) {
                            // If type is not primitive, check the object is not null.
                            fieldValue = getStringFromDataType(typeOfLeaf,
                                                               yangLeaf.getDataType());
                        }

                        // As we have to string helper, parsing of the value is required sometimes.
                        if (fieldValue != null &&
                                !fieldValue.equals(STR_NULL) &&
                                !fieldValue.isEmpty()) {
                            ydtExtendedBuilder.addLeaf(fieldValue, yangLeaf);
                            leafTypeObject = true;
                        }
                    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                            NoSuchFieldException | ClassNotFoundException e) {
                        throw new YtbException(e.getMessage());
                    }
                    // Only when child is added traverse back to parent.
                    if (leafTypeObject) {
                        ydtExtendedBuilder.traverseToParentExtended();
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
                    // TODO: Get java name from the schema node.
                    String javaNameOfLeafList = leafListInfo.getJavaName(null);

                    // From the parent context get the object of the leaf.
                    YdtExtendedContext ydtExtendedContext =
                            (YdtExtendedContext) ydtExtendedBuilder
                                    .getCurNode();
                    YtbNodeInfo ytbNodeInfo = (YtbNodeInfo) ydtExtendedContext
                            .getAppInfo(AppType.YTB);
                    Object objectOfNode = ytbNodeInfo.getYangObject();

                    try {
                        //TODO: Let the received object list be generic collection
                        List<Object> leafListObject =
                                (List<Object>) getAttributeOfObject(
                                        objectOfNode,
                                        javaNameOfLeafList);
                        Set<String> leafListValue = new HashSet<>();

                        // If list is present, then add as child to the parent, consecutively traverse back to parent.
                        if (leafListObject != null &&
                                !leafListObject.isEmpty()) {
                            Iterator<Object> objectIterator =
                                    leafListObject.iterator();
                            while (objectIterator.hasNext()) {
                                String strValue = getStringFromDataType(
                                        objectIterator.next(),
                                        yangLeafList.getDataType());
                                leafListValue.add(strValue);
                            }
                            ydtExtendedBuilder
                                    .addLeaf(leafListValue, yangLeafList);
                            ydtExtendedBuilder.traverseToParentExtended();
                        }
                    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                            NoSuchFieldException e) {
                        throw new YtbException(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Returns the value of the variable by extracting it from string value of
     * toStringHelper.
     *
     * @param strWithToStringHelper string retrieved from toStringHelper from
     *                              YANG utils generated class
     * @return value of the variable
     */
    private String getValueFromTheToStringHelper(String strWithToStringHelper) {
        if (strWithToStringHelper.contains(EQUALS_TO)) {
            int indexValue = strWithToStringHelper.lastIndexOf(EQUALS_TO);
            if (indexValue != -1) {
                return strWithToStringHelper.substring(indexValue + 1,
                                                       strWithToStringHelper
                                                               .length() - 1);
            }
        }
        return strWithToStringHelper;
    }

    /**
     * Returns the value of an attribute, in a class object.
     *
     * @param objectOfTheNode object of the root node
     * @param nameOfTheField  name of the attribute
     * @return object value received from the getter method of the field.
     * @throws NoSuchFieldException      no field exception from reflection
     * @throws IllegalAccessException    illegal access exception from
     *                                   reflection
     * @throws NoSuchMethodException     no such method exception from
     *                                   reflection
     * @throws InvocationTargetException invocation target exception from
     *                                   reflection
     */
    public Object getAttributeOfObject(Object objectOfTheNode,
                                       String nameOfTheField)
            throws NoSuchFieldException,
            IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        Class classOfNode = objectOfTheNode.getClass();
        Method getterMethodOfField =
                classOfNode.getDeclaredMethod(nameOfTheField);
        Object valueOfMethod = getterMethodOfField.invoke(objectOfTheNode);
        return valueOfMethod;
    }

    /**
     * Returns the operation type value for a class object.
     *
     * @param objectOfTheNode object of the class
     * @return operation type of the class
     * @throws NoSuchFieldException      no field exception from reflection
     * @throws IllegalAccessException    illegal access exception from
     *                                   reflection
     * @throws NoSuchMethodException     no such method exception from
     *                                   reflection
     * @throws InvocationTargetException invocation target exception from
     *                                   reflection
     */
    public YdtContextOperationType getOperationTypeOfTheNode(
            Object objectOfTheNode)
            throws NoSuchFieldException,
            IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        Object operationTypeObject =
                getAttributeOfObject(objectOfTheNode, OPERATION_TYPE);
        String valueOfOpType = String.valueOf(operationTypeObject);
        if (valueOfOpType.equals(STR_NULL) || valueOfOpType.isEmpty()) {
            valueOfOpType = STR_NONE;
        }
        return YdtContextOperationType.valueOf(valueOfOpType);
    }

    /**
     * Decides if the data type of the leaf is primitive data type from its
     * respective data type.
     *
     * @param yangType type of the leaf
     * @return status of the type to be primitive data type
     */
    private boolean isTypePrimitive(YangType yangType) {
        if (yangType.getDataType() == LEAFREF) {
            YangLeafRef leafref =
                    (YangLeafRef) yangType.getDataTypeExtendedInfo();
            return isPrimitiveDataType(
                    leafref.getEffectiveDataType().getDataType());
        }
        return isPrimitiveDataType(yangType.getDataType());
    }

    /**
     * Decides if the leaf data is actually set or not, by passing the enum
     * value through reflection for primitive data types.
     *
     * @param objectOfNode   object of the node where leaf is an attribute
     * @param javaNameOfLeaf java name of the leaf
     * @return status of the leaf data is actually set
     * @throws ClassNotFoundException    class not found exception from
     *                                   reflection
     * @throws IllegalAccessException    illegal access exception from
     *                                   reflection
     * @throws NoSuchMethodException     no such method exception from
     *                                   reflection
     * @throws InvocationTargetException invocation target exception from
     *                                   reflection
     */
    private String isValueLeafSetForLeaf(Object objectOfNode,
                                         String javaNameOfLeaf)
            throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {

        Class classOfNode = objectOfNode.getClass();
        Class interfaceClass = getInterfaceClassFromImplClass(objectOfNode);
        String enumPackage = interfaceClass.getName() + ENUM_LEAF_IDENTIFIER;
        ClassLoader classLoader = interfaceClass.getClassLoader();
        Class leafIdentifierEnumeration = classLoader.loadClass(enumPackage);
        Enum valueOfEnum = Enum.valueOf(leafIdentifierEnumeration,
                                        javaNameOfLeaf.toUpperCase());
        Method methodMyMethod = classOfNode
                .getMethod(IS_LEAF_VALUE_SET_METHOD, leafIdentifierEnumeration);
        return String.valueOf(methodMyMethod.invoke(objectOfNode, valueOfEnum));
    }

    /**
     * Returns whether the data type is of primitive data type.
     *
     * @param dataType data type to be checked
     * @return status of the data type as primitive
     */
    private boolean isPrimitiveDataType(YangDataTypes dataType) {
        return dataType == INT8
                || dataType == INT16
                || dataType == INT32
                || dataType == INT64
                || dataType == UINT8
                || dataType == UINT16
                || dataType == UINT32
                || dataType == UINT64
                || dataType == DECIMAL64
                || dataType == BOOLEAN
                || dataType == EMPTY;

    }

    /**
     * Returns interface class from an implementation class object.
     *
     * @param implClassObject implementation class object
     * @return respective interface class
     */
    private Class getInterfaceClassFromImplClass(Object implClassObject) {
        Class implClass = implClassObject.getClass();
        Class[] interfacesOfClass = implClass.getInterfaces();
        Class interfaceClass;
        if (interfacesOfClass.length == 1) {
            ListIterator<Class> rootClassInterfacesIterator =
                    Arrays.asList(interfacesOfClass).listIterator();
            interfaceClass = rootClassInterfacesIterator.next();
        } else {
            throw new YtbException(
                    "YTB Error: Two interfaces are present for the implementation class " +
                            implClass + ". Unable to handle it");
        }
        return interfaceClass;
    }

    /**
     * Returns the schema node of notification from the root node. Gets the enum
     * value from event object and gives it to the root schema node for getting
     * back the notification schema node.
     *
     * @return YANG schema node of notification
     * @throws InvocationTargetException invocation target exception from
     *                                   reflection
     * @throws NoSuchMethodException     no such method exception from
     *                                   reflection
     * @throws IllegalAccessException    illegal access exception from
     *                                   reflection
     * @throws NoSuchFieldException      no field exception from reflection
     * @throws DataModelException        data model exception from YANG utils
     */
    public YangSchemaNode getSchemaNodeOfNotification()
            throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException, NoSuchFieldException, DataModelException {
        Class parentClass = getRootObject().getClass().getSuperclass();
        Object typeOfEventObject =
                getAttributeFromInheritance(parentClass, getRootObject(),
                                            STR_TYPE);
        String valueOfOpType = String.valueOf(typeOfEventObject);
        if (valueOfOpType.equals(STR_NULL) || valueOfOpType.isEmpty()) {
            throw new YtbException(
                    "YTB error: There is no notification present for the event. Invalid input for " +
                            "notification.");
        }
        YangSchemaNode notificationNode =
                getSchemaRoot().getNotificationSchemaNode(valueOfOpType);
        return notificationNode;
    }

    /**
     * Returns the object of the notification class by retrieving the attributes
     * from the event class object.
     *
     * @return notification YANG object
     * @throws InvocationTargetException invocation target exception from
     *                                   reflection
     * @throws NoSuchMethodException     no such method exception from
     *                                   reflection
     * @throws IllegalAccessException    illegal access exception from
     *                                   reflection
     * @throws NoSuchFieldException      no field exception from reflection
     */
    public Object getYangObjectOfNotification()
            throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException, NoSuchFieldException {
        Class parentClass = getRootObject().getClass().getSuperclass();
        Object eventSubjectObject =
                getAttributeFromInheritance(parentClass, getRootObject(),
                                            STR_SUBJECT);
        String notificationName = getSchemaRoot().getJavaAttributeName();
        return getAttributeOfObject(eventSubjectObject, notificationName);
    }

    /**
     * Returns the object of the declared method in parent class by invoking
     * through the child class object.
     *
     * @param parentClass     parent class of the declared method
     * @param childClass      child class which inherits the parent class
     * @param nameOfTheMethod name of the declared method
     * @return value of the method
     * @throws NoSuchMethodException     no such method exception from
     *                                   reflection
     * @throws InvocationTargetException invocation target exception from
     *                                   reflection
     * @throws IllegalAccessException    illegal access exception from
     *                                   reflection
     */
    private Object getAttributeFromInheritance(Class parentClass,
                                               Object childClass,
                                               String nameOfTheMethod)
            throws
            NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Method getterMethodOfField =
                parentClass.getDeclaredMethod(nameOfTheMethod);
        Object valueOfMethod = getterMethodOfField.invoke(childClass);
        return valueOfMethod;
    }

    /**
     * Returns the string value from the respective data types of the
     * leaf/leaf-list.
     *
     * @param objectOfField object of the leaf/leaf-list field
     * @param dataType      type of the leaf/leaf-list
     * @return string value from the type
     */
    private String getStringFromDataType(Object objectOfField,
                                         YangType dataType) {
        YangDataTypes yangDataTypes = dataType.getDataType();
        switch (yangDataTypes) {
            case INT8:
            case INT16:
            case INT32:
            case INT64:
            case UINT8:
            case UINT16:
            case UINT32:
            case UINT64:
            case EMPTY:
            case IDENTITYREF:
            case STRING:
            case DECIMAL64:
            case INSTANCE_IDENTIFIER:
            case DERIVED:
            case UNION:
                //TODO: Generated code has to be changed, it must select the setting leaf and it must give back the
                // corresponding toString of that type.
            case BOOLEAN:
            case BITS:
                String valueOfTheField = String.valueOf(objectOfField);
                return getValueFromTheToStringHelper(valueOfTheField);
            case BINARY:
                return Base64.getEncoder()
                        .encodeToString((byte[]) objectOfField);
            case LEAFREF:
                YangLeafRef leafRef =
                        (YangLeafRef) dataType.getDataTypeExtendedInfo();
                YangType leafrefType = leafRef.getEffectiveDataType();
                return getStringFromDataType(objectOfField, leafrefType);
            case ENUMERATION:
                Object value = null;
                try {
                    value = getAttributeOfObject(objectOfField,
                                                 SCHEMA_NAME_IN_ENUM);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                String valueOfTheFieldInEnum = String.valueOf(value);
                return getValueFromTheToStringHelper(valueOfTheFieldInEnum);
            default:
                return null;
        }
    }
}
