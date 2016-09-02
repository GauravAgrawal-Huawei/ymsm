package org.onosproject.yms.app.ydt;

import java.util.List;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeContextInfo;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;

public interface YdtAppContext {

    /**
     * Returns the context of parent node.
     *
     * @return context of parent node
     */
    YdtAppContext getParent();

    /**
     * Sets the context of parent node.
     *
     * @param parent node
     */
    void setParent(YdtAppContext parent);

    /**
     * Returns the context of application parent node.
     *
     * @return context of application parent node
     */
    YdtAppContext getApplicationParent();

    /**
     * Sets the context of application parent node.
     *
     * @param applicationParent application parent node.
     */
    void setApplicationParent(YdtAppContext applicationParent);

    /**
     * Returns the context of first child.
     *
     * @return context of first child
     */
    YdtAppContext getFirstChild();

    /**
     * Sets the context of first child.
     *
     * @param child node
     */
    void setChild(YdtAppContext child);

    /**
     * Returns the context of last child.
     *
     * @return context of last child
     */
    YdtAppContext getLastChild();

    /**
     * Sets the context of last child.
     *
     * @param child node
     */
    void setLastChild(YdtAppContext child);

    /**
     * Returns the context of next sibling.
     *
     * @return context of next sibling
     */
    YdtAppContext getNextSibling();

    /**
     * Sets the context of next sibling.
     *
     * @param nextSibling node
     */
    void setNextSibling(YdtAppContext nextSibling);

    /**
     * Returns the context of previous sibling.
     *
     * @return context of previous sibling
     */
    YdtAppContext getPreviousSibling();

    /**
     * Sets the context of previous sibling.
     *
     * @param previousSibling node
     */
    void setPreviousSibling(YdtAppContext previousSibling);

    /**
     * Returns the app tree operation type.
     *
     * @return app tree operation type.
     */
    YdtAppNodeOperationType getOperationType();


    /**
     * Returns the app tree operation type with the help of YdtOperation type.
     *
     * @param ydtOpType ydt operation type.
     * @return app tree operation type.
     */
    YdtAppNodeOperationType getAppOpTypeFromYdtOpType(YdtContextOperationType ydtOpType);

    /**
     * Set the app tree operation type.
     *
     * @param operationType app tree operation type.
     */
    void setOperationType(YdtAppNodeOperationType operationType);

    /**
     * Returns the list of nodes with operation type delete.
     *
     * @return list of nodes with operation type delete
     */
    List<YdtContext> getDeleteNodes();

    /**
     * Sets the list of nodes with operation type delete.
     *
     * @param deleteNodes list of nodes with operation type delete.
     */
    void setDeleteNodes(List<YdtContext> deleteNodes);

    /**
     * Returns application's root ydtContext.
     *
     * @return YdtContext of application root node.
     */
    YdtContext getModuleNode();

    /**
     * Sets the application's ydtContext.
     *
     * @param moduleNode application's ydtContext.
     */
    void setModuleNode(YdtContext moduleNode);

    /**
     * Returns the YangSchemaNode of augmenting application.
     *
     * @return YangSchemaNode of augmenting application.
     */
    YangSchemaNode getAugmentingModuleSchemaNode();

    /**
     * Sets the YangSchemaNode of augmenting application root node.
     *
     * @param yangSchemaNode YangSchemaNode of augmenting application module.
     */
    void setAugmentingModuleSchemaNode(YangSchemaNode yangSchemaNode);

    /**
     * Adds a last child to ydt application data tree.
     *
     * @param newChild name of child to be added
     */
    void addChild(YdtAppContext newChild);

    /**
     * Get augmenting node module yang schema node.
     *
     * @param yangSchemaNodeIdentifier schema node identifier.
     * @param childSchemaNodeInfo      Yang Schema node context info which is having
     *                                 YangSchemaNode + ContextSwitchedNode.
     * @return augmenting node module yang schema node.
     */
    YangSchemaNode getAugmentingSchemaNode(YangSchemaNodeIdentifier yangSchemaNodeIdentifier,
                                           YangSchemaNodeContextInfo childSchemaNodeInfo);

    /**
     * Add entry in ydt application tree set. this is to avoid the traversing in the tree.
     *
     * @param yangSchemaNode yang schema node which is to be added in Set.
     * @return true in case of success else false.
     */
    boolean addYdtAppTreeSet(YangSchemaNode yangSchemaNode);

    /**
     * Update the app tree operation type.
     * If earlier operation type was OTHER_EDIT and now operation type came as DELETE_ONLY or vice-versa,
     * then update operation type to BOTH.
     *
     * @param childOpType ydt current context operation type.
     */
    void updateAppOperationType(YdtContextOperationType childOpType);
}
