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

package org.onosproject.yang.gen.v1.ydt.warehouse.supervisor.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;

/**
 * Represents the implementation of warehousesupervisor.
 */
public class WarehousesupervisorOpParam implements Warehousesupervisor {

    protected List<String> supervisor;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    public List<String> supervisor() {
        return supervisor;
    }
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(supervisor, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WarehousesupervisorOpParam) {
            WarehousesupervisorOpParam other = (WarehousesupervisorOpParam) obj;
            return
                 Objects.equals(supervisor, other.supervisor) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("supervisor", supervisor)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .toString();
    }

    /**
     * Creates an instance of warehousesupervisor.
     *
     * @param builderObject builder object of warehousesupervisor
     */
    protected WarehousesupervisorOpParam(WarehousesupervisorBuilder builderObject) {
        this.supervisor = builderObject.supervisor();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public Warehousesupervisor processSubtreeFiltering(Warehousesupervisor appInstance, boolean
            isSelectAllSchemaChild) {
        WarehousesupervisorBuilder subTreeFilteringResultBuilder = new WarehousesupervisorBuilder();
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
    private boolean processLeafListSubTreeFiltering(Warehousesupervisor appInstance, WarehousesupervisorBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (String supervisor : appInstance.supervisor()) {
                subTreeFilteringResultBuilder.addToSupervisor(supervisor);
            }
        } else if (supervisor() != null) {
            if (!supervisor().isEmpty()) {
                if (appInstance.supervisor() == null || appInstance.supervisor().isEmpty()) {
                    return false;
                }
                for (String supervisor : supervisor()) {
                    boolean flag = false;
                    for (String supervisor2 : appInstance.supervisor()) {
                        if (supervisor.equals(supervisor2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToSupervisor(supervisor2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.supervisor() != null && !appInstance.supervisor().isEmpty()) {
                    for (String supervisor : appInstance.supervisor()) {
                        subTreeFilteringResultBuilder.addToSupervisor(supervisor);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of warehousesupervisorOpParam.
     */
    protected WarehousesupervisorOpParam() {
    }

    /**
     * Returns the attribute WarehousesupervisorBuilder.
     *
     * @return value of WarehousesupervisorBuilder
     */
    public static WarehousesupervisorBuilder builder() {
        return new WarehousesupervisorBuilder();
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
     * Represents the builder implementation of warehousesupervisor.
     */
    public static class WarehousesupervisorBuilder {

        protected List<String> supervisor;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        public List<String> supervisor() {
            return supervisor;
        }
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        /**
         * Returns the builder object of supervisor.
         *
         * @param supervisor list of supervisor
         * @return builder object of supervisor
         */
        public WarehousesupervisorBuilder supervisor(List<String> supervisor) {
            this.supervisor = supervisor;
            return this;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public WarehousesupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }
        public WarehousesupervisorBuilder addToSupervisor(String value) {
            if (supervisor() == null) {
                supervisor(new ArrayList<>());
            }
            supervisor().add(value);
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



        public WarehousesupervisorBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public Warehousesupervisor build() {
            return new WarehousesupervisorOpParam(this);
        }

        /**
         * Creates an instance of warehousesupervisorBuilder.
         */
        public WarehousesupervisorBuilder() {
        }
    }
}
