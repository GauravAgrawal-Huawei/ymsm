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

package org.onosproject.yang.gen.v1.ydt.decimal64.rev20160524;

import com.google.common.base.MoreObjects;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.decimal64.rev20160524.decimal64.MultiRangeValidation;

/**
 * Represents the implementation of decimal64.
 */
public class Decimal64OpParam implements Decimal64 {

    protected BigDecimal negInt;
    protected BigDecimal negIntWithMaxFraction;
    protected BigDecimal negIntWithMinFraction;
    protected BigDecimal posInt;
    protected BigDecimal posIntWithMaxFraction;
    protected BigDecimal posIntWithMinFraction;
    protected BigDecimal minIntWithRange;
    protected BigDecimal midIntWithRange;
    protected BigDecimal maxIntWithRange;
    protected List<MultiRangeValidation> multiRangeValidation = new ArrayList<>();

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

    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    public BigDecimal negInt() {
        return negInt;
    }
    public BigDecimal negIntWithMaxFraction() {
        return negIntWithMaxFraction;
    }
    public BigDecimal negIntWithMinFraction() {
        return negIntWithMinFraction;
    }
    public BigDecimal posInt() {
        return posInt;
    }
    public BigDecimal posIntWithMaxFraction() {
        return posIntWithMaxFraction;
    }
    public BigDecimal posIntWithMinFraction() {
        return posIntWithMinFraction;
    }
    public BigDecimal minIntWithRange() {
        return minIntWithRange;
    }
    public BigDecimal midIntWithRange() {
        return midIntWithRange;
    }
    public BigDecimal maxIntWithRange() {
        return maxIntWithRange;
    }
    public List<MultiRangeValidation> multiRangeValidation() {
        return multiRangeValidation;
    }
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }


    @Override
    public int hashCode() {
        return Objects
            .hash(negInt, negIntWithMaxFraction, negIntWithMinFraction, posInt, posIntWithMaxFraction,
            posIntWithMinFraction, minIntWithRange, midIntWithRange, maxIntWithRange, multiRangeValidation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Decimal64OpParam) {
            Decimal64OpParam other = (Decimal64OpParam) obj;
            return
                 Objects.equals(negInt, other.negInt) &&
                 Objects.equals(negIntWithMaxFraction, other.negIntWithMaxFraction) &&
                 Objects.equals(negIntWithMinFraction, other.negIntWithMinFraction) &&
                 Objects.equals(posInt, other.posInt) &&
                 Objects.equals(posIntWithMaxFraction, other.posIntWithMaxFraction) &&
                 Objects.equals(posIntWithMinFraction, other.posIntWithMinFraction) &&
                 Objects.equals(minIntWithRange, other.minIntWithRange) &&
                 Objects.equals(midIntWithRange, other.midIntWithRange) &&
                 Objects.equals(maxIntWithRange, other.maxIntWithRange) &&
                 Objects.equals(multiRangeValidation, other.multiRangeValidation);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("negInt", negInt)
            .add("negIntWithMaxFraction", negIntWithMaxFraction)
            .add("negIntWithMinFraction", negIntWithMinFraction)
            .add("posInt", posInt)
            .add("posIntWithMaxFraction", posIntWithMaxFraction)
            .add("posIntWithMinFraction", posIntWithMinFraction)
            .add("minIntWithRange", minIntWithRange)
            .add("midIntWithRange", midIntWithRange)
            .add("maxIntWithRange", maxIntWithRange)
            .add("multiRangeValidation", multiRangeValidation)
            .toString();
    }

    /**
     * Creates an instance of decimal64.
     *
     * @param builderObject builder object of decimal64
     */
    protected Decimal64OpParam(Decimal64Builder builderObject) {
        this.negInt = builderObject.negInt();
        this.negIntWithMaxFraction = builderObject.negIntWithMaxFraction();
        this.negIntWithMinFraction = builderObject.negIntWithMinFraction();
        this.posInt = builderObject.posInt();
        this.posIntWithMaxFraction = builderObject.posIntWithMaxFraction();
        this.posIntWithMinFraction = builderObject.posIntWithMinFraction();
        this.minIntWithRange = builderObject.minIntWithRange();
        this.midIntWithRange = builderObject.midIntWithRange();
        this.maxIntWithRange = builderObject.maxIntWithRange();
        this.multiRangeValidation = builderObject.multiRangeValidation();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public Decimal64 processSubtreeFiltering(Decimal64 appInstance, boolean isSelectAllSchemaChild) {
        Decimal64Builder subTreeFilteringResultBuilder = new Decimal64Builder();
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
    private boolean processLeafSubtreeFiltering(Decimal64 appInstance, Decimal64Builder subTreeFilteringResultBuilder,
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

        if (getValueLeafFlags().get(LeafIdentifier.NEGINTWITHMAXFRACTION.getLeafIndex())) {
            if (appInstance.negIntWithMaxFraction() != negIntWithMaxFraction()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.negIntWithMaxFraction(appInstance.negIntWithMaxFraction());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .NEGINTWITHMAXFRACTION.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.negIntWithMaxFraction(appInstance.negIntWithMaxFraction());
        }

        if (getValueLeafFlags().get(LeafIdentifier.NEGINTWITHMINFRACTION.getLeafIndex())) {
            if (appInstance.negIntWithMinFraction() != negIntWithMinFraction()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.negIntWithMinFraction(appInstance.negIntWithMinFraction());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .NEGINTWITHMINFRACTION.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.negIntWithMinFraction(appInstance.negIntWithMinFraction());
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

        if (getValueLeafFlags().get(LeafIdentifier.POSINTWITHMAXFRACTION.getLeafIndex())) {
            if (appInstance.posIntWithMaxFraction() != posIntWithMaxFraction()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.posIntWithMaxFraction(appInstance.posIntWithMaxFraction());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .POSINTWITHMAXFRACTION.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.posIntWithMaxFraction(appInstance.posIntWithMaxFraction());
        }

        if (getValueLeafFlags().get(LeafIdentifier.POSINTWITHMINFRACTION.getLeafIndex())) {
            if (appInstance.posIntWithMinFraction() != posIntWithMinFraction()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.posIntWithMinFraction(appInstance.posIntWithMinFraction());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .POSINTWITHMINFRACTION.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.posIntWithMinFraction(appInstance.posIntWithMinFraction());
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

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(Decimal64 appInstance, Decimal64Builder
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
     * Creates an instance of decimal64OpParam.
     */
    protected Decimal64OpParam() {
    }

    /**
     * Returns the attribute Decimal64Builder.
     *
     * @return value of Decimal64Builder
     */
    public static Decimal64Builder builder() {
        return new Decimal64Builder();
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
     * Represents the builder implementation of decimal64.
     */
    public static class Decimal64Builder {

        protected BigDecimal negInt;
        protected BigDecimal negIntWithMaxFraction;
        protected BigDecimal negIntWithMinFraction;
        protected BigDecimal posInt;
        protected BigDecimal posIntWithMaxFraction;
        protected BigDecimal posIntWithMinFraction;
        protected BigDecimal minIntWithRange;
        protected BigDecimal midIntWithRange;
        protected BigDecimal maxIntWithRange;
        protected List<MultiRangeValidation> multiRangeValidation = new ArrayList<>();

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;

        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        public BigDecimal negInt() {
            return negInt;
        }
        public BigDecimal negIntWithMaxFraction() {
            return negIntWithMaxFraction;
        }
        public BigDecimal negIntWithMinFraction() {
            return negIntWithMinFraction;
        }
        public BigDecimal posInt() {
            return posInt;
        }
        public BigDecimal posIntWithMaxFraction() {
            return posIntWithMaxFraction;
        }
        public BigDecimal posIntWithMinFraction() {
            return posIntWithMinFraction;
        }
        public BigDecimal minIntWithRange() {
            return minIntWithRange;
        }
        public BigDecimal midIntWithRange() {
            return midIntWithRange;
        }
        public BigDecimal maxIntWithRange() {
            return maxIntWithRange;
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
        public Decimal64Builder negInt(BigDecimal negInt) {
            getValueLeafFlags().set(LeafIdentifier.NEGINT.getLeafIndex());
            this.negInt = negInt;
            return this;
        }

        /**
         * Returns the builder object of negIntWithMaxFraction.
         *
         * @param negIntWithMaxFraction value of negIntWithMaxFraction
         * @return builder object of negIntWithMaxFraction
         */
        public Decimal64Builder negIntWithMaxFraction(BigDecimal negIntWithMaxFraction) {
            getValueLeafFlags().set(LeafIdentifier.NEGINTWITHMAXFRACTION.getLeafIndex());
            this.negIntWithMaxFraction = negIntWithMaxFraction;
            return this;
        }

        /**
         * Returns the builder object of negIntWithMinFraction.
         *
         * @param negIntWithMinFraction value of negIntWithMinFraction
         * @return builder object of negIntWithMinFraction
         */
        public Decimal64Builder negIntWithMinFraction(BigDecimal negIntWithMinFraction) {
            getValueLeafFlags().set(LeafIdentifier.NEGINTWITHMINFRACTION.getLeafIndex());
            this.negIntWithMinFraction = negIntWithMinFraction;
            return this;
        }

        /**
         * Returns the builder object of posInt.
         *
         * @param posInt value of posInt
         * @return builder object of posInt
         */
        public Decimal64Builder posInt(BigDecimal posInt) {
            getValueLeafFlags().set(LeafIdentifier.POSINT.getLeafIndex());
            this.posInt = posInt;
            return this;
        }

        /**
         * Returns the builder object of posIntWithMaxFraction.
         *
         * @param posIntWithMaxFraction value of posIntWithMaxFraction
         * @return builder object of posIntWithMaxFraction
         */
        public Decimal64Builder posIntWithMaxFraction(BigDecimal posIntWithMaxFraction) {
            getValueLeafFlags().set(LeafIdentifier.POSINTWITHMAXFRACTION.getLeafIndex());
            this.posIntWithMaxFraction = posIntWithMaxFraction;
            return this;
        }

        /**
         * Returns the builder object of posIntWithMinFraction.
         *
         * @param posIntWithMinFraction value of posIntWithMinFraction
         * @return builder object of posIntWithMinFraction
         */
        public Decimal64Builder posIntWithMinFraction(BigDecimal posIntWithMinFraction) {
            getValueLeafFlags().set(LeafIdentifier.POSINTWITHMINFRACTION.getLeafIndex());
            this.posIntWithMinFraction = posIntWithMinFraction;
            return this;
        }

        /**
         * Returns the builder object of minIntWithRange.
         *
         * @param minIntWithRange value of minIntWithRange
         * @return builder object of minIntWithRange
         */
        public Decimal64Builder minIntWithRange(BigDecimal minIntWithRange) {
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
        public Decimal64Builder midIntWithRange(BigDecimal midIntWithRange) {
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
        public Decimal64Builder maxIntWithRange(BigDecimal maxIntWithRange) {
            getValueLeafFlags().set(LeafIdentifier.MAXINTWITHRANGE.getLeafIndex());
            this.maxIntWithRange = maxIntWithRange;
            return this;
        }

        /**
         * Returns the builder object of multiRangeValidation.
         *
         * @param multiRangeValidation list of multiRangeValidation
         * @return builder object of multiRangeValidation
         */
        public Decimal64Builder multiRangeValidation(List<MultiRangeValidation> multiRangeValidation) {
            this.multiRangeValidation = multiRangeValidation;
            return this;
        }
        public Decimal64Builder addToMultiRangeValidation(MultiRangeValidation value) {
            multiRangeValidation().add(value);
            return this;
        }
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
        public Decimal64Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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



        public Decimal64Builder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public Decimal64 build() {
            return new Decimal64OpParam(this);
        }

        /**
         * Creates an instance of decimal64Builder.
         */
        public Decimal64Builder() {
        }
    }
}
