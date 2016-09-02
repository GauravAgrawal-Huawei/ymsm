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

import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yms.app.ydt.exceptions.YdtExceptions;
import org.onosproject.yms.ydt.YdtType;

/**
 * Represents YDT single instance leaf node which is an atomic element and doesn't
 * have any child.
 */
class YdtSingleInstanceLeafNode extends YdtNode {

    /**
     * Value of the leaf.
     */
    private String value;

    /**
     * Creates a YANG single instance leaf node.
     *
     * @param nodeIdentifier node identifier of YANG data tree single instance leaf node .
     */
    public YdtSingleInstanceLeafNode(YangSchemaNodeIdentifier nodeIdentifier) {
        super(YdtType.SINGLE_INSTANCE_LEAF_VALUE_NODE, nodeIdentifier);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void addValue(String value) {
        // check the value against corresponding data-type.
        try {
            this.getYangSchemaNode().isValueValid(value);
        } catch (Exception e) {
            // Free resources
            freeRestResources();
            throw new YdtExceptions(e.getMessage());
        }

        // After validation is successful then add value to node.
        this.value = value;
    }


    @Override
    public void addValueWithoutValidation(String value) {
        this.value = value;
    }

    @Override
    public void isDuplicateEntriesValid() {
        // Free resources
        freeRestResources();
        String errorInfo = "Duplicate entry with name " + this.getYdtNodeIdentifier().getName() + ".";
        throw new YdtExceptions(errorInfo);
    }
}
