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

package org.onosproject.yms.app.yab;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.onosproject.yangutils.datamodel.TraversalType;
import org.onosproject.yangutils.datamodel.YangAugment;
import org.onosproject.yangutils.datamodel.YangAugmentableNode;
import org.onosproject.yangutils.datamodel.YangInput;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangRpc;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yms.app.yab.exceptions.YabException;
import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ydt.YangResponseWorkBench;
import org.onosproject.yms.app.ydt.YdtAppContext;
import org.onosproject.yms.app.ydt.YdtAppNodeOperationType;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ydt.YdtMultiInstanceNode;
import org.onosproject.yms.app.ydt.YdtNode;
import org.onosproject.yms.app.yob.DefaultYobBuilder;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.app.ytb.DefaultYangTreeBuilder;
import org.onosproject.yms.ydt.YdtBuilder;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YdtResponse;
import org.onosproject.yms.ydt.YmsOperationExecutionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.onosproject.yangutils.datamodel.TraversalType.CHILD;
import static org.onosproject.yangutils.datamodel.TraversalType.PARENT;
import static org.onosproject.yangutils.datamodel.TraversalType.ROOT;
import static org.onosproject.yangutils.datamodel.TraversalType.SIBILING;
import static org.onosproject.yms.ydt.YdtContextOperationType.DELETE;

/**
 * Represents YANG application broker. It acts as a broker between Protocol and YANG
 * based application. NBI protocol translates the protocol specific operation request
 * into a protocol abstract YANG data tree using the YdtBuilder.
 * NBI protocol's uses YANG data tree as the abstract data tree with
 * operation request.
 */
public class YangApplicationBroker {

    private static final Logger log = LoggerFactory.getLogger(YangApplicationBroker.class);

    /**
     * Static attribute for get.
     */
    private static final String GET = "get";

    /**
     * Static attribute for set.
     */
    private static final String SET = "set";

    /**
     * Schema registry of application.
     */
    private YangSchemaRegistry schemaRegistry;

    /**
     * Creates a new YANG application broker.
     *
     * @param schemaRegistry YANG schema registry
     */
    public YangApplicationBroker(YangSchemaRegistry schemaRegistry) {
        this.schemaRegistry = schemaRegistry;
    }

    /**
     * Processes query request of a NBI protocol.
     *
     * @param ydtWorkBench YANG request work bench
     * @return YANG response data tree node context
     */
    public YdtResponse processQuery(YdtBuilder ydtWorkBench) throws YabException {
        List<Object> responseObjects = new LinkedList<>();
        YangRequestWorkBench workBench = ((YangRequestWorkBench) ydtWorkBench);
        YdtContext rootYdtContext = workBench.getRootNode();
        YdtAppContext appRootNode = workBench.getAppRootNode();
        for (YdtAppContext appContext = appRootNode.getFirstChild(); appContext != null;
             appContext = appContext.getNextSibling()) {
            Object responseObject = processQueryOfApplication(appContext);
            responseObjects.add(responseObject);
        }
        YdtBuilder responseYdt = buildResponseYdtTree(responseObjects, rootYdtContext.getName(),
                rootYdtContext.getNamespace());

        return new YangResponseWorkBench(responseYdt.getRootNode(),
                YmsOperationExecutionStatus.EXECUTION_SUCCESS,
                ydtWorkBench.getYmsOperationType());
    }

    /**
     * Processes edit request of a NBI protocol.
     *
     * @param ydtWorkBench YANG request work bench
     * @return YANG response data tree node context
     * @throws CloneNotSupportedException clone is not supported
     */

    public YdtResponse processEdit(YdtBuilder ydtWorkBench)
            throws CloneNotSupportedException, YabException {
        YangRequestWorkBench workBench = ((YangRequestWorkBench) ydtWorkBench);
        YdtAppContext appRootNode = workBench.getAppRootNode();
        for (YdtAppContext appContext = appRootNode.getFirstChild(); appContext != null;
             appContext = appContext.getNextSibling()) {
            processEditOfApplication(appContext);
        }
        return new YangResponseWorkBench(null,
                YmsOperationExecutionStatus.EXECUTION_SUCCESS,
                workBench.getYmsOperationType());
    }

    /**
     * Processes operation request of a NBI protocol.
     *
     * @param ydtWorkBench YANG request work bench
     * @return YANG response data tree node context
     */
    public YdtResponse processOperation(YdtBuilder ydtWorkBench) throws YabException {
        List<Object> responseObjects = new LinkedList<>();
        YangRequestWorkBench workBench = ((YangRequestWorkBench) ydtWorkBench);
        YdtContext rootYdtContext = workBench.getRootNode();
        YdtContext ydtNode = ydtWorkBench.getRootNode().getFirstChild();
        while (ydtNode != null) {
            YdtContext childNode = ydtNode.getFirstChild();
            YangSchemaNode yangNode = ((YdtNode) childNode).getYangSchemaNode();
            if (yangNode instanceof YangRpc) {
                YdtContext inputNode = getInputYdtNode(childNode);
                Object inputParamObject = getYangObject(inputNode);
                Object appObject = getApplicationObject(ydtNode);
                Object outputObject = invokeApplicationsMethod(appObject, inputParamObject,
                        yangNode.getName());
                responseObjects.add(outputObject);
                YdtBuilder responseYdt = buildResponseYdtTree(responseObjects, rootYdtContext.getName(),
                        rootYdtContext.getNamespace());
                return new YangResponseWorkBench(responseYdt.getRootNode(),
                        YmsOperationExecutionStatus.EXECUTION_SUCCESS,
                        ydtWorkBench.getYmsOperationType());
            }
            ydtNode = ydtNode.getNextSibling();
        }
        return null;
    }

    /**
     * Processes query request of an application.
     *
     * @param appContext application context
     * @return response object from application
     */
    private Object processQueryOfApplication(YdtAppContext appContext)
            throws YabException {

        // get YdtContext from appContext
        YdtContext ydtNode = appContext.getModuleNode();

        // get YangObject of YdtContext from YOB
        Object outputObject = getYangObject(ydtNode);

        TraversalType curTraversal = ROOT;
        do {
            if (curTraversal != PARENT) {

                // find application and get application's object using YSR
                Object appManagerObject = getApplicationObject(appContext);

                // find which method to invoke
                String methodName = getApplicationGetterMethodName(appContext);

                // invoke application's getter method
                outputObject = invokeApplicationsMethod(appManagerObject, outputObject, methodName);
            }

            // appContext may contain other nodes if it is augmented, so traverse the appContext tree
            curTraversal = traverseApplicationContext(curTraversal, appContext);
        } while (appContext.getParent().getParent() != null);
        return outputObject;
    }

    /**
     * Returns YANG object for YDT node.
     *
     * @param ydtNode YANG data node
     * @return YANG object for YDT node
     */
    private Object getYangObject(YdtContext ydtNode) {
        if (ydtNode != null) {
            DefaultYobBuilder yobBuilder = new DefaultYobBuilder();
            return yobBuilder.getYangObject(((YdtExtendedContext) ydtNode), schemaRegistry);
        }
        return null;
    }

    /**
     * Returns application manager object for YDT node.
     *
     * @param ydtNode YANG data node
     * @return application manager object
     */
    private Object getApplicationObject(YdtContext ydtNode) {
        YangSchemaNode yangSchemaNode = ((YdtNode) ydtNode).getYangSchemaNode();
        return schemaRegistry.getRegisteredApplication(yangSchemaNode);
    }

    /**
     * Returns application manager object of application.
     *
     * @param appContext application context
     * @return application manager object
     */
    private Object getApplicationObject(YdtAppContext appContext) {
        YangSchemaNode yangSchemaNode;
        if (appContext.getModuleNode() != null) {
            yangSchemaNode = ((YdtNode) appContext.getModuleNode()).getYangSchemaNode();
        } else {
            yangSchemaNode = appContext.getAugmentingModuleSchemaNode();
        }
        return schemaRegistry.getRegisteredApplication(yangSchemaNode);
    }

    /**
     * Convert name to capital case.
     *
     * @param yangIdentifier identifier
     * @return name to capital case
     */
    private String getCapitalCase(String yangIdentifier) {
        return yangIdentifier.substring(0, 1).toUpperCase() + yangIdentifier.substring(1);
    }

    /**
     * Returns get method name for application's query request.
     *
     * @param appContext application context
     * @return get method name for application's query request
     */
    private String getApplicationGetterMethodName(YdtAppContext appContext) {
        return GET + getJavaNameOfApp(appContext);
    }

    /**
     * Returns set method name for application's edit request.
     *
     * @param appContext application context
     * @return get method name for application's edit request
     */
    private String getApplicationSetterMethodName(YdtAppContext appContext) {
        return SET + getJavaNameOfApp(appContext);
    }

    /**
     * Invokes application method for RPC request.
     *
     * @param appManagerObject application manager object
     * @param inputParamObject input parameter object of method
     * @param methodName       method name which should be invoked
     * @return response object from application
     */
    private Object invokeApplicationsMethod(Object appManagerObject,
                                            Object inputParamObject, String methodName) throws YabException {
        Class<?> appClass = appManagerObject.getClass();
        Method methodObject;
        Object responseObject = null;
        try {
            methodObject = appClass.getDeclaredMethod(methodName, inputParamObject.getClass());
        } catch (NoSuchMethodException e) {
            log.error("YAB: failed to fetch method object from application object.");
            throw new YabException(e.getMessage());
        }

        try {
            if (methodObject != null) {
                responseObject = methodObject.invoke(appManagerObject, inputParamObject);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("YAB: failed to invoke method in application object.");
            throw new YabException(e.getMessage());
        }
        return responseObject;
    }

    /**
     * Traverse the application context tree.
     *
     * @param curTraversal current traversal type
     * @param appContext   application context
     * @return curTraversal current traversal type
     */
    private TraversalType traverseApplicationContext(TraversalType curTraversal, YdtAppContext appContext) {
        if (curTraversal != PARENT && appContext.getFirstChild() != null) {
            curTraversal = CHILD;
            appContext = appContext.getFirstChild();
        } else if (appContext.getNextSibling() != null) {
            curTraversal = SIBILING;
            appContext = appContext.getNextSibling();
        } else {
            curTraversal = PARENT;
            appContext = appContext.getParent();
        }
        return curTraversal;
    }

    /**
     * Returns rpc's input schema node.
     *
     * @param rpcNode rpc schema node
     * @return rpc's input YANG data tree
     */
    private YdtContext getInputYdtNode(YdtContext rpcNode) {
        YdtContext inputNode = rpcNode.getFirstChild();
        while (inputNode != null) {
            YangSchemaNode yangInputNode = ((YdtNode) inputNode).getYangSchemaNode();
            if (yangInputNode instanceof YangInput) {
                return inputNode;
            }
            inputNode = rpcNode.getNextSibling();
        }
        return null;
    }

    /**
     * Returns response YANG data tree using YTB.
     *
     * @param responseObjects list of application's response objects
     * @param name            application YANG name
     * @param namespace       application YANG namespace
     * @return response YANG data tree
     */
    private YdtBuilder buildResponseYdtTree(List<Object> responseObjects, String name, String namespace) {
        DefaultYangTreeBuilder treeBuilder = new DefaultYangTreeBuilder();
        return treeBuilder.getYdtBuilderForYo(responseObjects,
                name, namespace, null, schemaRegistry);
    }

    /**
     * Processes edit request of an application.
     *
     * @param appContext application context
     */
    private void processEditOfApplication(YdtAppContext appContext)
            throws CloneNotSupportedException, YabException {
        if (appContext.getOperationType() != YdtAppNodeOperationType.OTHER_EDIT) {
            processDeleteRequestOfApplication(appContext);
        }

        if (appContext.getOperationType() != YdtAppNodeOperationType.DELETE_ONLY) {
            YdtContext ydtNode = appContext.getModuleNode();

            // get YO from YOB
            Object outputObject = getYangObject(ydtNode);

            TraversalType curTraversal = ROOT;
            do {
                if (curTraversal != PARENT) {

                    // find application and get application's object using YSR
                    Object appManagerObject = getApplicationObject(appContext);

                    // find which method to invoke
                    String methodName = getApplicationSetterMethodName(appContext);

                    // invoke application's getter method
                    invokeApplicationsMethod(appManagerObject, outputObject, methodName);
                }

                // appContext may contain other nodes if it is augmented, so traverse the appContext tree
                curTraversal = traverseApplicationContext(curTraversal, appContext);
            } while (appContext.getParent().getParent() != null);
        }
    }

    /**
     * Processes delete request of an application.
     *
     * @param appContext application context
     */
    private void processDeleteRequestOfApplication(YdtAppContext appContext)
            throws CloneNotSupportedException, YabException {
        TraversalType curTraversal = ROOT;
        List<YdtContext> deleteNodes = appContext.getDeleteNodes();

        if (deleteNodes != null && !deleteNodes.isEmpty()) {
            YdtContext deleteTree = buildDeleteTree(deleteNodes);

//            deleteTree = updateDeleteTreeWithAugmentedNodes(deleteTree);
            Object deleteInputParamObject = getYangObject(deleteTree);

            do {
                if (curTraversal == ROOT) {
                    while (appContext.getLastChild() != null) {
                        appContext = appContext.getLastChild();
                    }
                } else if (curTraversal == SIBILING) {
                    while (appContext.getLastChild() != null) {
                        appContext = appContext.getLastChild();
                    }
                }

                // getAugmentApplication manager object
                Object appManagerObject = getApplicationObject(appContext);

                // find which method to invoke
                String methodName = getApplicationSetterMethodName(appContext);

                // invoke application's getter method
                invokeApplicationsMethod(appManagerObject, deleteInputParamObject, methodName);

                if (appContext.getPreviousSibling() != null) {
                    curTraversal = SIBILING;
                    appContext = appContext.getPreviousSibling();
                } else if (appContext.getParent() != null) {
                    curTraversal = PARENT;
                    appContext = appContext.getParent();
                }
            } while (appContext.getParent() != null);
        }
    }

    /**
     * Builds delete tree for list of delete nodes.
     *
     * @param deleteNodes list of delete nodes
     * @return deleteTree YANG data tree for delete operation
     * @throws CloneNotSupportedException clone is not supported
     */
    public YdtContext buildDeleteTree(List<YdtContext> deleteNodes) throws
            CloneNotSupportedException {
        Iterator<YdtContext> deleteNodeIterator = deleteNodes.iterator();
        YdtContext deleteTree = null;
        while (deleteNodeIterator.hasNext()) {
            YdtContext deleteNode = deleteNodeIterator.next();
            if (((YdtExtendedContext) deleteNode.getParent()).getYdtContextOperationType() != DELETE) {
                deleteTree = updateDeleteTree(deleteNode);
            }
        }

        while (deleteTree.getParent().getParent() != null) {
            deleteTree = deleteTree.getParent();
        }
        return deleteTree;
    }

    /**
     * Unlinks the delete node from YDT tree and clones ancestors of
     * delete node and updates delete tree.
     *
     * @param deleteNode node to be deleted
     * @return updated delete tree
     */
    private YdtContext updateDeleteTree(YdtContext deleteNode)
            throws CloneNotSupportedException {
        cloneAncestorsOfDeleteNode(deleteNode);
        deleteNode = unlinkDeleteNodeFromCurrentTree(((YdtNode) deleteNode));
        return deleteNode;
    }

    /**
     * Clones ancestor nodes of delete node.
     *
     * @param deleteNode node to be deleted
     */
    private void cloneAncestorsOfDeleteNode(YdtContext deleteNode)
            throws CloneNotSupportedException {
        YdtNode clonedNode;
        YdtNode previousClonedNode = null;

        // Clone the parents of delete node to form delete tree
        YdtNode nodeToClone = (YdtNode) deleteNode.getParent();
        while (nodeToClone != null) {
            // If node is not cloned yet
            if (nodeToClone.getClonedNode() == null) {
                clonedNode = (YdtNode) nodeToClone.clone();
                clonedNode.setParent(null);
                clonedNode.setNextSibling(null);
                clonedNode.setPreviousSibling(null);
                clonedNode.setChild(null);
                clonedNode.setLastChild(null);
                if (nodeToClone instanceof YdtMultiInstanceNode) {
                    addKeyLeavesToClonedNode(nodeToClone, clonedNode);
                }
                nodeToClone.setClonedNode(clonedNode);
            } else {
                // already node is cloned
                clonedNode = (YdtNode) nodeToClone.getClonedNode();
            }

            if (previousClonedNode != null) {
                /*
                 * add previous cloned node as child of current cloned node
                 * so that tree will be formed from delete node parent to logical
                 * root node.
                 */
                clonedNode.addChild(previousClonedNode, false);
            }
            previousClonedNode = clonedNode;
            nodeToClone = nodeToClone.getParent();
        }
    }

    /**
     * Unlinks delete node from current YANG data tree of application
     * and links it to cloned delete tree.
     *
     * @param deleteNode node to be unlinked
     * @return deleteNode delete node linked to cloned delete tree
     */
    private YdtNode unlinkDeleteNodeFromCurrentTree(YdtNode deleteNode) {
        YdtNode parentClonedNode = (YdtNode) deleteNode.getParent().getClonedNode();
        unlinkNodeFromParent(deleteNode);
        unlinkNodeFromSibling(deleteNode);

        /*
         * Set all the pointers of node to null before adding as child
         * to parent's cloned node.
         */
        deleteNode.setParent(null);
        deleteNode.setPreviousSibling(null);
        deleteNode.setNextSibling(null);

        parentClonedNode.addChild(deleteNode, false);
        return deleteNode;
    }

    /**
     * Returns java name of an application.
     *
     * @param appContext application context
     * @return java name of an application
     */
    private String getJavaNameOfApp(YdtAppContext appContext) {
        String javaName = null;
        if (appContext.getModuleNode() != null) {
            javaName = ((YdtNode) appContext.getModuleNode()).getYangSchemaNode().getJavaClassNameOrBuiltInType();
        } else if (appContext.getAugmentingModuleSchemaNode() != null) {
            javaName = appContext.getAugmentingModuleSchemaNode().getJavaClassNameOrBuiltInType();
        } else {
            log.error("YAB: failed to fetch yang nodes from application context.");
        }

        if (javaName != null) {
            return getCapitalCase(javaName);
        } else {
            // TODO : throw exception
            return null;
        }
    }

    /**
     * Unlinks the current node from its parent.
     *
     * @param deleteNode node which should be unlinked from YDT tree
     */
    private void unlinkNodeFromParent(YdtNode deleteNode) {
        YdtNode parentNode = deleteNode.getParent();
        if (parentNode.getFirstChild() == deleteNode
                && parentNode.getLastChild() == deleteNode) {
            parentNode.setChild(null);
            parentNode.setLastChild(null);
        } else if (parentNode.getFirstChild() == deleteNode) {
            parentNode.setChild(deleteNode.getNextSibling());
        } else if (parentNode.getLastChild() == deleteNode) {
            parentNode.setLastChild(deleteNode.getPreviousSibling());
        }
    }

    /**
     * Unlinks the current node from its sibling.
     *
     * @param deleteNode node which should be unlinked from YDT tree
     */
    private void unlinkNodeFromSibling(YdtNode deleteNode) {
        YdtNode previousSiblingNode = deleteNode.getPreviousSibling();
        YdtNode nextSiblingNode = deleteNode.getNextSibling();
        if (nextSiblingNode != null && previousSiblingNode != null) {
            previousSiblingNode.setNextSibling(nextSiblingNode);
            nextSiblingNode.setPreviousSibling(previousSiblingNode);
        } else if (nextSiblingNode != null && previousSiblingNode == null) {
            nextSiblingNode.setPreviousSibling(null);
        } else if (previousSiblingNode != null && nextSiblingNode == null) {
            previousSiblingNode.setNextSibling(nextSiblingNode);
        }
    }

    private YdtContext updateDeleteTreeWithAugmentedNodes(YdtContext deleteTree) {
        TraversalType curTraversal = ROOT;
        YdtContext deleteNode = deleteTree.getFirstChild();

        while (deleteNode != deleteTree) {
            if (curTraversal != PARENT) {
                YangSchemaNode yangNode = ((YdtNode) deleteNode).getYangSchemaNode();
                if (yangNode instanceof YangAugmentableNode) {
                    if (!((YangAugmentableNode) yangNode).getAugmentedInfoList().isEmpty()) {
                        List<YangAugment> augmentList = ((YangAugmentableNode) yangNode).getAugmentedInfoList();
                        updateYdtWithAugmentNodes(augmentList, deleteNode);
                    }
                }
            }

            if (curTraversal != PARENT && deleteNode.getFirstChild() != null) {
                curTraversal = CHILD;
                deleteNode = deleteNode.getFirstChild();
            } else if (deleteNode.getNextSibling() != null) {
                curTraversal = SIBILING;
                deleteNode = deleteNode.getNextSibling();
            } else {
                curTraversal = PARENT;
                deleteNode = deleteNode.getParent();
            }
        }
        return deleteNode;
    }

    private void updateYdtWithAugmentNodes(List<YangAugment> augmentList, YdtContext deleteNode) {
        if (augmentList != null && !augmentList.isEmpty()) {
            Iterator<YangAugment> augmentListIterator = augmentList.listIterator();
            while (augmentListIterator.hasNext()) {
                YangAugment augment = augmentListIterator.next();
                addContentsOfAugmentToYdt(augment, deleteNode);
            }
        }
    }

    private void addContentsOfAugmentToYdt(YangNode augment, YdtContext deleteNode) {
        TraversalType curTraversal;
        YangNode yangNode = augment;
        YdtContext curYdtNode = deleteNode;

        yangNode = yangNode.getChild();
        if (yangNode == null) {
            return;
        } else {
            // Yang augment node need not be added.
            curTraversal = CHILD;
        }

        while (yangNode != augment) {
            if (curTraversal == CHILD) {
                curYdtNode = ((YangRequestWorkBench) curYdtNode).addChild(YdtContextOperationType.DELETE,
                        yangNode);
            } else if (curTraversal == SIBILING) {
                curYdtNode = curYdtNode.getParent();
                curYdtNode = ((YangRequestWorkBench) curYdtNode).addChild(YdtContextOperationType.DELETE,
                        yangNode);
            } else {
                curYdtNode = curYdtNode.getParent();
            }

            if (curTraversal != PARENT && yangNode.getChild() != null) {
                curTraversal = CHILD;
                yangNode = yangNode.getChild();
            } else if (yangNode.getNextSibling() != null) {
                curTraversal = SIBILING;
                yangNode = yangNode.getNextSibling();
            } else {
                curTraversal = PARENT;
                yangNode = yangNode.getParent();
            }
        }
    }

    private void addKeyLeavesToClonedNode(YdtNode curNode, YdtNode clonedNode)
            throws CloneNotSupportedException {
        YdtNode keyLeaf;
        YdtNode keyClonedLeaf;
        Set<YdtContext> keyList = ((YdtMultiInstanceNode) curNode).getKeyNodeList();
        if (keyList != null && !keyList.isEmpty()) {
            Iterator<YdtContext> keyListIterator = keyList.iterator();
            while (keyListIterator.hasNext()) {
                keyLeaf = (YdtNode) keyListIterator.next();
                keyClonedLeaf = (YdtNode) keyLeaf.clone();
                keyClonedLeaf.setParent(null);
                keyClonedLeaf.setNextSibling(null);
                keyClonedLeaf.setPreviousSibling(null);
                keyClonedLeaf.setChild(null);
                keyClonedLeaf.setLastChild(null);
                clonedNode.addChild(keyClonedLeaf, true);
            }
        }
    }
}
