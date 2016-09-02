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

import java.util.HashSet;
import java.util.Set;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yangutils.datamodel.exceptions.DataModelException;
import org.onosproject.yms.app.ydt.exceptions.YdtExceptions;
import org.onosproject.yms.ydt.YdtType;

/**
 * Represents YDT multi instance leaf node which can hold multiple values, it
 * is atomic element and doesn't have any child.
 */
class YdtMultiInstanceLeafNode extends YdtNode {

    /**
     * Set of values.
     */
    private Set<String> valueSet = new HashSet<>();

    /**
     * Creates a YANG multi instance leaf node.
     *
     * @param nodeIdentifier node identifier of YANG data tree multi instance node .
     */
    public YdtMultiInstanceLeafNode(YangSchemaNodeIdentifier nodeIdentifier) {
        super(YdtType.MULTI_INSTANCE_LEAF_VALUE_NODE, nodeIdentifier);
    }

    @Override
    public Set<String> getValueSet() {
        return valueSet;
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
        if (!this.valueSet.add(value)) {
            // Free resources
            freeRestResources();
            String errorInfo = "Duplicate entry found under " + this.getYdtNodeIdentifier().getName()
                    + " leaf-list node.";
            throw new YdtExceptions(errorInfo);
        }
    }

    @Override
    public void addValueSet(Set valueSet) {

        String value;

        // Check the value against corresponding data-type.
        for (Object aValueSet : valueSet) {

            try {
                value = String.valueOf(aValueSet);
                this.getYangSchemaNode().isValueValid(value);
            } catch (DataModelException e) {
                // Free resources
                freeRestResources();
                throw new YdtExceptions(e.getMessage());
            }
            // After validation is successful then add value to node.
            if (!this.valueSet.add(value)) {
                // Free resources
                freeRestResources();
                String errorInfo = "Duplicate entry found under " + this.getYdtNodeIdentifier().getName()
                        + " leaf-list node.";
                throw new YdtExceptions(errorInfo);
            }

        }

    }

    @Override
    public void addValueWithoutValidation(String value) {
        this.valueSet.add(value);
    }

    @Override
    public void addValueSetWithoutValidation(Set valueSet) {
        //noinspection unchecked
        this.valueSet = valueSet;
    }

}
