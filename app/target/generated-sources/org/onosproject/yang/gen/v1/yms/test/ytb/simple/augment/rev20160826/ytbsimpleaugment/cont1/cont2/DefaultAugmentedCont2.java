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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2;

import com.google.common.base.MoreObjects;
import java.util.BitSet;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2.Cont1s;

/**
 * Represents the implementation of augmentedCont2.
 */
public class DefaultAugmentedCont2 implements AugmentedCont2 {

    protected int leaf4;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Cont1s cont1s;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();

    @Override
    public int leaf4() {
        return leaf4;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public Cont1s cont1s() {
        return cont1s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaf4, onosYangNodeOperationType, cont1s);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultAugmentedCont2) {
            DefaultAugmentedCont2 other = (DefaultAugmentedCont2) obj;
            return
                 Objects.equals(leaf4, other.leaf4) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(cont1s, other.cont1s);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("leaf4", leaf4)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("cont1s", cont1s)
            .toString();
    }

    /**
     * Creates an instance of augmentedCont2.
     *
     * @param builderObject builder object of augmentedCont2
     */
    protected DefaultAugmentedCont2(AugmentedCont2Builder builderObject) {
        this.leaf4 = builderObject.leaf4();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.cont1s = builderObject.cont1s();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public AugmentedCont2 processSubtreeFiltering(AugmentedCont2 appInstance, boolean isSelectAllSchemaChild) {
        AugmentedCont2Builder subTreeFilteringResultBuilder = new AugmentedCont2Builder();
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
    private boolean processLeafSubtreeFiltering(AugmentedCont2 appInstance, AugmentedCont2Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.LEAF4.getLeafIndex())) {
            if (appInstance.leaf4() != leaf4()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.leaf4(appInstance.leaf4());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.LEAF4.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.leaf4(appInstance.leaf4());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(AugmentedCont2 appInstance, AugmentedCont2Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cont1s()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont1s() != null) {
                Cont1s result = cont1s.processSubtreeFiltering(appInstance.cont1s(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont1s(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultAugmentedCont2.
     */
    protected DefaultAugmentedCont2() {
    }

    /**
     * Returns the attribute AugmentedCont2Builder.
     *
     * @return value of AugmentedCont2Builder
     */
    public static AugmentedCont2Builder builder() {
        return new AugmentedCont2Builder();
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
     * Represents the builder implementation of augmentedCont2.
     */
    public static class AugmentedCont2Builder implements AugmentedCont2.AugmentedCont2Builder {

        protected int leaf4;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Cont1s cont1s;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();


        @Override
        public int leaf4() {
            return leaf4;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public Cont1s cont1s() {
            return cont1s;
        }

        @Override
        public AugmentedCont2Builder leaf4(int leaf4) {
            getValueLeafFlags().set(LeafIdentifier.LEAF4.getLeafIndex());
            this.leaf4 = leaf4;
            return this;
        }

        @Override
        public AugmentedCont2Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public AugmentedCont2Builder cont1s(Cont1s cont1s) {
            this.cont1s = cont1s;
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



        @Override
        public AugmentedCont2Builder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }
        @Override
        public AugmentedCont2 build() {
            return new DefaultAugmentedCont2(this);
        }

        /**
         * Creates an instance of augmentedCont2Builder.
         */
        public AugmentedCont2Builder() {
        }
    }
}
