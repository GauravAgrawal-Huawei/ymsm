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

package org.onosproject.yang.gen.v1.ydt.integer32.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.integer32.rev20160524.integer32.MultiRangeValidation;

/**
 * Represents the implementation of integer32.
 */
public class Integer32OpParam implements Integer32 {

    protected int negInt;
    protected int posInt;
    protected int minIntWithRange;
    protected int midIntWithRange;
    protected int maxIntWithRange;
    protected long minUint;
    protected long maxUint;
    protected long minUintWithRange;
    protected long midUintWithRange;
    protected long maxUintWithRange;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<MultiRangeValidation> multiRangeValidation;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    public int negInt() {
        return negInt;
    }
    public int posInt() {
        return posInt;
    }
    public int minIntWithRange() {
        return minIntWithRange;
    }
    public int midIntWithRange() {
        return midIntWithRange;
    }
    public int maxIntWithRange() {
        return maxIntWithRange;
    }
    public long minUint() {
        return minUint;
    }
    public long maxUint() {
        return maxUint;
    }
    public long minUintWithRange() {
        return minUintWithRange;
    }
    public long midUintWithRange() {
        return midUintWithRange;
    }
    public long maxUintWithRange() {
        return maxUintWithRange;
    }
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public List<MultiRangeValidation> multiRangeValidation() {
        return multiRangeValidation;
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(negInt, posInt, minIntWithRange, midIntWithRange, maxIntWithRange, minUint, maxUint,
            minUintWithRange, midUintWithRange, maxUintWithRange, onosYangNodeOperationType, multiRangeValidation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Integer32OpParam) {
            Integer32OpParam other = (Integer32OpParam) obj;
            return
                 Objects.equals(negInt, other.negInt) &&
                 Objects.equals(posInt, other.posInt) &&
                 Objects.equals(minIntWithRange, other.minIntWithRange) &&
                 Objects.equals(midIntWithRange, other.midIntWithRange) &&
                 Objects.equals(maxIntWithRange, other.maxIntWithRange) &&
                 Objects.equals(minUint, other.minUint) &&
                 Objects.equals(maxUint, other.maxUint) &&
                 Objects.equals(minUintWithRange, other.minUintWithRange) &&
                 Objects.equals(midUintWithRange, other.midUintWithRange) &&
                 Objects.equals(maxUintWithRange, other.maxUintWithRange) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(multiRangeValidation, other.multiRangeValidation);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("negInt", negInt)
            .add("posInt", posInt)
            .add("minIntWithRange", minIntWithRange)
            .add("midIntWithRange", midIntWithRange)
            .add("maxIntWithRange", maxIntWithRange)
            .add("minUint", minUint)
            .add("maxUint", maxUint)
            .add("minUintWithRange", minUintWithRange)
            .add("midUintWithRange", midUintWithRange)
            .add("maxUintWithRange", maxUintWithRange)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("multiRangeValidation", multiRangeValidation)
            .toString();
    }

    /**
     * Creates an instance of integer32.
     *
     * @param builderObject builder object of integer32
     */
    protected Integer32OpParam(Integer32Builder builderObject) {
        this.negInt = builderObject.negInt();
        this.posInt = builderObject.posInt();
        this.minIntWithRange = builderObject.minIntWithRange();
        this.midIntWithRange = builderObject.midIntWithRange();
        this.maxIntWithRange = builderObject.maxIntWithRange();
        this.minUint = builderObject.minUint();
        this.maxUint = builderObject.maxUint();
        this.minUintWithRange = builderObject.minUintWithRange();
        this.midUintWithRange = builderObject.midUintWithRange();
        this.maxUintWithRange = builderObject.maxUintWithRange();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.multiRangeValidation = builderObject.multiRangeValidation();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public Integer32 processSubtreeFiltering(Integer32 appInstance, boolean isSelectAllSchemaChild) {
        Integer32Builder subTreeFilteringResultBuilder = new Integer32Builder();
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
    private boolean processLeafSubtreeFiltering(Integer32 appInstance, Integer32Builder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.NEGINT.getLeafIndex())) {
            if (appInstance.negInt() != negInt()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.negInt(appInstance.negInt());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.NEGINT.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.negInt(appInstance.negInt());
        }

        if (getValueLeafFlags().get(LeafIdentifier.POSINT.getLeafIndex())) {
            if (appInstance.posInt() != posInt()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.posInt(appInstance.posInt());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.POSINT.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.posInt(appInstance.posInt());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MININTWITHRANGE.getLeafIndex())) {
            if (appInstance.minIntWithRange() != minIntWithRange()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.minIntWithRange(appInstance.minIntWithRange());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .MININTWITHRANGE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.minIntWithRange(appInstance.minIntWithRange());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MIDINTWITHRANGE.getLeafIndex())) {
            if (appInstance.midIntWithRange() != midIntWithRange()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.midIntWithRange(appInstance.midIntWithRange());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .MIDINTWITHRANGE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.midIntWithRange(appInstance.midIntWithRange());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MAXINTWITHRANGE.getLeafIndex())) {
            if (appInstance.maxIntWithRange() != maxIntWithRange()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.maxIntWithRange(appInstance.maxIntWithRange());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .MAXINTWITHRANGE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.maxIntWithRange(appInstance.maxIntWithRange());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MINUINT.getLeafIndex())) {
            if (appInstance.minUint() != minUint()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.minUint(appInstance.minUint());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.MINUINT.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.minUint(appInstance.minUint());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MAXUINT.getLeafIndex())) {
            if (appInstance.maxUint() != maxUint()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.maxUint(appInstance.maxUint());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.MAXUINT.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.maxUint(appInstance.maxUint());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MINUINTWITHRANGE.getLeafIndex())) {
            if (appInstance.minUintWithRange() != minUintWithRange()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.minUintWithRange(appInstance.minUintWithRange());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .MINUINTWITHRANGE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.minUintWithRange(appInstance.minUintWithRange());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MIDUINTWITHRANGE.getLeafIndex())) {
            if (appInstance.midUintWithRange() != midUintWithRange()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.midUintWithRange(appInstance.midUintWithRange());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .MIDUINTWITHRANGE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.midUintWithRange(appInstance.midUintWithRange());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MAXUINTWITHRANGE.getLeafIndex())) {
            if (appInstance.maxUintWithRange() != maxUintWithRange()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.maxUintWithRange(appInstance.maxUintWithRange());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .MAXUINTWITHRANGE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.maxUintWithRange(appInstance.maxUintWithRange());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(Integer32 appInstance, Integer32Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (MultiRangeValidation multiRangeValidation : appInstance.multiRangeValidation()) {
                subTreeFilteringResultBuilder.addToMultiRangeValidation(multiRangeValidation);
            }
        } else if (multiRangeValidation() != null) {
            isAnySelectOrContainmentNode = true;
            if (!multiRangeValidation().isEmpty()) {
                if (appInstance.multiRangeValidation() != null && !appInstance.multiRangeValidation().isEmpty()) {
                    for (MultiRangeValidation multiRangeValidation : multiRangeValidation()) {
                        for (MultiRangeValidation multiRangeValidation2 : appInstance.multiRangeValidation()) {
                            MultiRangeValidation result = multiRangeValidation
            .processSubtreeFiltering(multiRangeValidation2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToMultiRangeValidation(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.multiRangeValidation() != null && !appInstance.multiRangeValidation().isEmpty()) {
                    for (MultiRangeValidation multiRangeValidation : appInstance.multiRangeValidation()) {
                        subTreeFilteringResultBuilder.addToMultiRangeValidation(multiRangeValidation);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of integer32OpParam.
     */
    protected Integer32OpParam() {
    }

    /**
     * Returns the attribute Integer32Builder.
     *
     * @return value of Integer32Builder
     */
    public static Integer32Builder builder() {
        return new Integer32Builder();
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
     * Represents the builder implementation of integer32.
     */
    public static class Integer32Builder {

        protected int negInt;
        protected int posInt;
        protected int minIntWithRange;
        protected int midIntWithRange;
        protected int maxIntWithRange;
        protected long minUint;
        protected long maxUint;
        protected long minUintWithRange;
        protected long midUintWithRange;
        protected long maxUintWithRange;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<MultiRangeValidation> multiRangeValidation;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        public int negInt() {
            return negInt;
        }
        public int posInt() {
            return posInt;
        }
        public int minIntWithRange() {
            return minIntWithRange;
        }
        public int midIntWithRange() {
            return midIntWithRange;
        }
        public int maxIntWithRange() {
            return maxIntWithRange;
        }
        public long minUint() {
            return minUint;
        }
        public long maxUint() {
            return maxUint;
        }
        public long minUintWithRange() {
            return minUintWithRange;
        }
        public long midUintWithRange() {
            return midUintWithRange;
        }
        public long maxUintWithRange() {
            return maxUintWithRange;
        }
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public List<MultiRangeValidation> multiRangeValidation() {
            return multiRangeValidation;
        }

        /**
         * Returns the builder object of negInt.
         *
         * @param negInt value of negInt
         * @return builder object of negInt
         */
        public Integer32Builder negInt(int negInt) {
            getValueLeafFlags().set(LeafIdentifier.NEGINT.getLeafIndex());
            this.negInt = negInt;
            return this;
        }

        /**
         * Returns the builder object of posInt.
         *
         * @param posInt value of posInt
         * @return builder object of posInt
         */
        public Integer32Builder posInt(int posInt) {
            getValueLeafFlags().set(LeafIdentifier.POSINT.getLeafIndex());
            this.posInt = posInt;
            return this;
        }

        /**
         * Returns the builder object of minIntWithRange.
         *
         * @param minIntWithRange value of minIntWithRange
         * @return builder object of minIntWithRange
         */
        public Integer32Builder minIntWithRange(int minIntWithRange) {
            getValueLeafFlags().set(LeafIdentifier.MININTWITHRANGE.getLeafIndex());
            this.minIntWithRange = minIntWithRange;
            return this;
        }

        /**
         * Returns the builder object of midIntWithRange.
         *
         * @param midIntWithRange value of midIntWithRange
         * @return builder object of midIntWithRange
         */
        public Integer32Builder midIntWithRange(int midIntWithRange) {
            getValueLeafFlags().set(LeafIdentifier.MIDINTWITHRANGE.getLeafIndex());
            this.midIntWithRange = midIntWithRange;
            return this;
        }

        /**
         * Returns the builder object of maxIntWithRange.
         *
         * @param maxIntWithRange value of maxIntWithRange
         * @return builder object of maxIntWithRange
         */
        public Integer32Builder maxIntWithRange(int maxIntWithRange) {
            getValueLeafFlags().set(LeafIdentifier.MAXINTWITHRANGE.getLeafIndex());
            this.maxIntWithRange = maxIntWithRange;
            return this;
        }

        /**
         * Returns the builder object of minUint.
         *
         * @param minUint value of minUint
         * @return builder object of minUint
         */
        public Integer32Builder minUint(long minUint) {
            getValueLeafFlags().set(LeafIdentifier.MINUINT.getLeafIndex());
            this.minUint = minUint;
            return this;
        }

        /**
         * Returns the builder object of maxUint.
         *
         * @param maxUint value of maxUint
         * @return builder object of maxUint
         */
        public Integer32Builder maxUint(long maxUint) {
            getValueLeafFlags().set(LeafIdentifier.MAXUINT.getLeafIndex());
            this.maxUint = maxUint;
            return this;
        }

        /**
         * Returns the builder object of minUintWithRange.
         *
         * @param minUintWithRange value of minUintWithRange
         * @return builder object of minUintWithRange
         */
        public Integer32Builder minUintWithRange(long minUintWithRange) {
            getValueLeafFlags().set(LeafIdentifier.MINUINTWITHRANGE.getLeafIndex());
            this.minUintWithRange = minUintWithRange;
            return this;
        }

        /**
         * Returns the builder object of midUintWithRange.
         *
         * @param midUintWithRange value of midUintWithRange
         * @return builder object of midUintWithRange
         */
        public Integer32Builder midUintWithRange(long midUintWithRange) {
            getValueLeafFlags().set(LeafIdentifier.MIDUINTWITHRANGE.getLeafIndex());
            this.midUintWithRange = midUintWithRange;
            return this;
        }

        /**
         * Returns the builder object of maxUintWithRange.
         *
         * @param maxUintWithRange value of maxUintWithRange
         * @return builder object of maxUintWithRange
         */
        public Integer32Builder maxUintWithRange(long maxUintWithRange) {
            getValueLeafFlags().set(LeafIdentifier.MAXUINTWITHRANGE.getLeafIndex());
            this.maxUintWithRange = maxUintWithRange;
            return this;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public Integer32Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of multiRangeValidation.
         *
         * @param multiRangeValidation list of multiRangeValidation
         * @return builder object of multiRangeValidation
         */
        public Integer32Builder multiRangeValidation(List<MultiRangeValidation> multiRangeValidation) {
            this.multiRangeValidation = multiRangeValidation;
            return this;
        }
        public Integer32Builder addToMultiRangeValidation(MultiRangeValidation value) {
            if (multiRangeValidation() == null) {
                multiRangeValidation(new ArrayList<>());
            }
            multiRangeValidation().add(value);
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



        public Integer32Builder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public Integer32 build() {
            return new Integer32OpParam(this);
        }

        /**
         * Creates an instance of integer32Builder.
         */
        public Integer32Builder() {
        }
    }
}
