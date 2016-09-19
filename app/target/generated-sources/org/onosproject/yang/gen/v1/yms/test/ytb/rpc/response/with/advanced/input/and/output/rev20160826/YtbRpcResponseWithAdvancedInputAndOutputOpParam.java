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

package org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.BitSet;
import java.util.Objects;

/**
 * Represents the implementation of ytbRpcResponseWithAdvancedInputAndOutput.
 */
public class YtbRpcResponseWithAdvancedInputAndOutputOpParam implements YtbRpcResponseWithAdvancedInputAndOutput {

    protected byte[] refer;
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
    public byte[] refer() {
        return refer;
    }
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(refer, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbRpcResponseWithAdvancedInputAndOutputOpParam) {
            YtbRpcResponseWithAdvancedInputAndOutputOpParam other = (YtbRpcResponseWithAdvancedInputAndOutputOpParam)
            obj;
            return
                 Objects.equals(refer, other.refer) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("refer", refer)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .toString();
    }

    /**
     * Creates an instance of ytbRpcResponseWithAdvancedInputAndOutput.
     *
     * @param builderObject builder object of ytbRpcResponseWithAdvancedInputAndOutput
     */
    protected YtbRpcResponseWithAdvancedInputAndOutputOpParam(YtbRpcResponseWithAdvancedInputAndOutputBuilder
            builderObject) {
        this.refer = builderObject.refer();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public YtbRpcResponseWithAdvancedInputAndOutput processSubtreeFiltering(YtbRpcResponseWithAdvancedInputAndOutput
            appInstance, boolean
            isSelectAllSchemaChild) {
        YtbRpcResponseWithAdvancedInputAndOutputBuilder subTreeFilteringResultBuilder = new
            YtbRpcResponseWithAdvancedInputAndOutputBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(YtbRpcResponseWithAdvancedInputAndOutput appInstance,
            YtbRpcResponseWithAdvancedInputAndOutputBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.REFER.getLeafIndex())) {
            if (appInstance.refer() == null || !(refer().equals(appInstance.refer()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.refer(appInstance.refer());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.REFER.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.refer(appInstance.refer());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(YtbRpcResponseWithAdvancedInputAndOutput appInstance,
            YtbRpcResponseWithAdvancedInputAndOutputBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        return true;
    }


    /**
     * Creates an instance of ytbRpcResponseWithAdvancedInputAndOutputOpParam.
     */
    protected YtbRpcResponseWithAdvancedInputAndOutputOpParam() {
    }

    /**
     * Returns the attribute YtbRpcResponseWithAdvancedInputAndOutputBuilder.
     *
     * @return value of YtbRpcResponseWithAdvancedInputAndOutputBuilder
     */
    public static YtbRpcResponseWithAdvancedInputAndOutputBuilder builder() {
        return new YtbRpcResponseWithAdvancedInputAndOutputBuilder();
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
     * Represents the builder implementation of ytbRpcResponseWithAdvancedInputAndOutput.
     */
    public static class YtbRpcResponseWithAdvancedInputAndOutputBuilder {

        protected byte[] refer;
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

        public byte[] refer() {
            return refer;
        }
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        /**
         * Returns the builder object of refer.
         *
         * @param refer value of refer
         * @return builder object of refer
         */
        public YtbRpcResponseWithAdvancedInputAndOutputBuilder refer(byte[] refer) {
            getValueLeafFlags().set(LeafIdentifier.REFER.getLeafIndex());
            this.refer = refer;
            return this;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YtbRpcResponseWithAdvancedInputAndOutputBuilder onosYangNodeOperationType(OnosYangNodeOperationType
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



        public YtbRpcResponseWithAdvancedInputAndOutputBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public YtbRpcResponseWithAdvancedInputAndOutput build() {
            return new YtbRpcResponseWithAdvancedInputAndOutputOpParam(this);
        }

        /**
         * Creates an instance of ytbRpcResponseWithAdvancedInputAndOutputBuilder.
         */
        public YtbRpcResponseWithAdvancedInputAndOutputBuilder() {
        }
    }
}
