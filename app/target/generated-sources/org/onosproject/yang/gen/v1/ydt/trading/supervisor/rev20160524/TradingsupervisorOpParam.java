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

package org.onosproject.yang.gen.v1.ydt.trading.supervisor.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.BitSet;
import java.util.Objects;

/**
 * Represents the implementation of tradingsupervisor.
 */
public class TradingsupervisorOpParam implements Tradingsupervisor {

    protected String supervisor;
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
    public String supervisor() {
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
        if (obj instanceof TradingsupervisorOpParam) {
            TradingsupervisorOpParam other = (TradingsupervisorOpParam) obj;
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
     * Creates an instance of tradingsupervisor.
     *
     * @param builderObject builder object of tradingsupervisor
     */
    protected TradingsupervisorOpParam(TradingsupervisorBuilder builderObject) {
        this.supervisor = builderObject.supervisor();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public Tradingsupervisor processSubtreeFiltering(Tradingsupervisor appInstance, boolean isSelectAllSchemaChild) {
        TradingsupervisorBuilder subTreeFilteringResultBuilder = new TradingsupervisorBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(Tradingsupervisor appInstance, TradingsupervisorBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.SUPERVISOR.getLeafIndex())) {
            if (appInstance.supervisor() == null || !(supervisor().equals(appInstance.supervisor()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.supervisor(appInstance.supervisor());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.SUPERVISOR.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.supervisor(appInstance.supervisor());
        }

        return true;
    }


    /**
     * Creates an instance of tradingsupervisorOpParam.
     */
    protected TradingsupervisorOpParam() {
    }

    /**
     * Returns the attribute TradingsupervisorBuilder.
     *
     * @return value of TradingsupervisorBuilder
     */
    public static TradingsupervisorBuilder builder() {
        return new TradingsupervisorBuilder();
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
     * Represents the builder implementation of tradingsupervisor.
     */
    public static class TradingsupervisorBuilder {

        protected String supervisor;
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

        public String supervisor() {
            return supervisor;
        }
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        /**
         * Returns the builder object of supervisor.
         *
         * @param supervisor value of supervisor
         * @return builder object of supervisor
         */
        public TradingsupervisorBuilder supervisor(String supervisor) {
            getValueLeafFlags().set(LeafIdentifier.SUPERVISOR.getLeafIndex());
            this.supervisor = supervisor;
            return this;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public TradingsupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
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



        public TradingsupervisorBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public Tradingsupervisor build() {
            return new TradingsupervisorOpParam(this);
        }

        /**
         * Creates an instance of tradingsupervisorBuilder.
         */
        public TradingsupervisorBuilder() {
        }
    }
}
