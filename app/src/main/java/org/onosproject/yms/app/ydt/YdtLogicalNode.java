/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.onosproject.yms.app.ydt;

import static org.onosproject.yms.app.ydt.YdtConstants.DUP_NAME;
import static org.onosproject.yms.app.ydt.YdtConstants.getErrorString;
import static org.onosproject.yms.ydt.YdtType.LOGICAL_ROOT_NODE;

/**
 * Represents a single instance YANG data tree node.
 */
class YdtLogicalNode extends YdtNode {

    String name;
    String namespace;

    /**
     * Creates a YANG single instance node object.
     */
    public YdtLogicalNode(String name, String namespace) {
        super(LOGICAL_ROOT_NODE);
        this.name = name;
        this.namespace = namespace;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public String getModuleNameAsNameSpace() {
        return namespace;
    }

    @Override
    public void validDuplicateEntryProcessing() {
        errorHandler(getErrorString(DUP_NAME, getName()), this);
    }
}
