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

package org.onosproject.yang.gen.v1.ydt.employee.id.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;

/**
 * Represents the implementation of employeeid.
 */
public class EmployeeidOpParam implements Employeeid {

    /**
     * Specify the node specific operation in protocols like NETCONF.
     * Applicable in protocol edit operation, not applicable in query operation
     */
    public enum OnosYangNodeOperationType {
        MERGE,
        REPLACE,
        CREATE,
        DELETE,
        REMOVE,
        NONE
    }

    /**
     * Specify the node specific operation in protocols like NETCONF.
     * Applicable in protocol edit operation, will be ignored in query operation
     */
    private OnosYangNodeOperationType onosYangNodeOperationType;


    protected List<String> employeeid = new ArrayList<>();
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    public List<String> employeeid() {
        return employeeid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmployeeidOpParam) {
            EmployeeidOpParam other = (EmployeeidOpParam) obj;
            return
                 Objects.equals(employeeid, other.employeeid);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("employeeid", employeeid)
            .toString();
    }

    /**
     * Creates an instance of employeeid.
     *
     * @param builderObject builder object of employeeid
     */
    protected EmployeeidOpParam(EmployeeidBuilder builderObject) {
        this.employeeid = builderObject.employeeid();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public Employeeid processSubtreeFiltering(Employeeid appInstance, boolean isSelectAllSchemaChild) {
        EmployeeidBuilder subTreeFilteringResultBuilder = new EmployeeidBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafListSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafListSubTreeFiltering(Employeeid appInstance, EmployeeidBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (String employeeid : appInstance.employeeid()) {
                subTreeFilteringResultBuilder.addToEmployeeid(employeeid);
            }
        } else if (employeeid() != null) {
            if (!employeeid().isEmpty()) {
                if (appInstance.employeeid() == null || appInstance.employeeid().isEmpty()) {
                    return false;
                }
                for (String employeeid : employeeid()) {
                    boolean flag = false;
                    for (String employeeid2 : appInstance.employeeid()) {
                        if (employeeid.equals(employeeid2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToEmployeeid(employeeid2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.employeeid() != null && !appInstance.employeeid().isEmpty()) {
                    for (String employeeid : appInstance.employeeid()) {
                        subTreeFilteringResultBuilder.addToEmployeeid(employeeid);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of employeeidOpParam.
     */
    protected EmployeeidOpParam() {
    }

    /**
     * Returns the attribute EmployeeidBuilder.
     *
     * @return value of EmployeeidBuilder
     */
    public static EmployeeidBuilder builder() {
        return new EmployeeidBuilder();
    }

    /**
     * Returns the valueLeafFlags.
     *
     * @return value of valueLeafFlags
     */
    public BitSet getValueLeafFlags() {
        return valueLeafFlags;
    }

    /**
     * Returns the selectLeafFlags.
     *
     * @return value of selectLeafFlags
     */
    public BitSet getSelectLeafFlags() {
        return selectLeafFlags;
    }


    @Override
    public boolean isLeafValueSet(LeafIdentifier leaf) {
        return getValueLeafFlags().get(leaf.getLeafIndex());
    }

    @Override
    public boolean isSelectLeaf(LeafIdentifier leaf) {
        return getSelectLeafFlags().get(leaf.getLeafIndex());
    }


    /**
     * Represents the builder implementation of employeeid.
     */
    public static class EmployeeidBuilder {

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected List<String> employeeid = new ArrayList<>();
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        /**
         * Returns the onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        /**
         * Set node operation type.
         *
         * @param onosYangNodeOperationType node operation type
         * @return builder object for node operation type
         */
        public EmployeeidBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }

        public List<String> employeeid() {
            return employeeid;
        }

        /**
         * Returns the builder object of employeeid.
         *
         * @param employeeid list of employeeid
         * @return builder object of employeeid
         */
        public EmployeeidBuilder employeeid(List<String> employeeid) {
            this.employeeid = employeeid;
            return this;
        }
        public EmployeeidBuilder addToEmployeeid(String value) {
            employeeid().add(value);
            return this;
        }

        /**
         * Returns the valueLeafFlags.
         *
         * @return value of valueLeafFlags
         */
        public BitSet getValueLeafFlags() {
            return valueLeafFlags;
        }

        /**
         * Returns the selectLeafFlags.
         *
         * @return value of selectLeafFlags
         */
        public BitSet getSelectLeafFlags() {
            return selectLeafFlags;
        }



        public EmployeeidBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public Employeeid build() {
            return new EmployeeidOpParam(this);
        }

        /**
         * Creates an instance of employeeidBuilder.
         */
        public EmployeeidBuilder() {
        }
    }
}
