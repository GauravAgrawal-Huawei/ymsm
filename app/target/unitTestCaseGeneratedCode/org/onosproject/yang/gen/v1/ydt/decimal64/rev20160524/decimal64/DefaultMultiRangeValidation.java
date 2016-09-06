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

package org.onosproject.yang.gen.v1.ydt.decimal64.rev20160524.decimal64;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of multiRangeValidation.
 */
public class DefaultMultiRangeValidation implements MultiRangeValidation {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected BigDecimal decimal;
    protected BigDecimal revDecimal;

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

    @Override
    public BigDecimal decimal() {
        return decimal;
    }

    @Override
    public BigDecimal revDecimal() {
        return revDecimal;
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
        return Objects.hash(decimal, revDecimal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultMultiRangeValidation) {
            DefaultMultiRangeValidation other = (DefaultMultiRangeValidation) obj;
            return
                 Objects.equals(decimal, other.decimal) &&
                 Objects.equals(revDecimal, other.revDecimal);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("decimal", decimal)
            .add("revDecimal", revDecimal)
            .toString();
    }

    @Override
    public Object yangAugmentedInfo(Class classObject) {
        return yangAugmentedInfoMap.get(classObject);
    }

    @Override
    public Map<Class<?>, Object> yangAugmentedInfoMap() {
        return yangAugmentedInfoMap;
    }

    /**
     * Creates an instance of multiRangeValidation.
     *
     * @param builderObject builder object of multiRangeValidation
     */
    protected DefaultMultiRangeValidation(MultiRangeValidationBuilder builderObject) {
        this.decimal = builderObject.decimal();
        this.revDecimal = builderObject.revDecimal();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public MultiRangeValidation processSubtreeFiltering(MultiRangeValidation appInstance, boolean
            isSelectAllSchemaChild) {
        MultiRangeValidationBuilder subTreeFilteringResultBuilder = new MultiRangeValidationBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        for (Object yangAugmentedInfo : this.yangAugmentedInfoMap().values()) {
            Object yangAugmentedOpParamInfo = appInstance.yangAugmentedInfo(yangAugmentedInfo.getClass());
            Object processSubtreeFiltering;
            try {
                Class<?>[] interfaces = yangAugmentedInfo.getClass().getInterfaces();
                processSubtreeFiltering = yangAugmentedInfo.getClass()
                    .getMethod("processSubtreeFiltering", interfaces[0]).invoke(yangAugmentedInfo,
                        yangAugmentedOpParamInfo);
                if (processSubtreeFiltering != null) {
                    subTreeFilteringResultBuilder
            .addYangAugmentedInfo(processSubtreeFiltering, processSubtreeFiltering.getClass());
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                continue;
            }
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(MultiRangeValidation appInstance, MultiRangeValidationBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.DECIMAL.getLeafIndex())) {
            if (appInstance.decimal() != decimal()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.decimal(appInstance.decimal());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.DECIMAL.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.decimal(appInstance.decimal());
        }

        if (getValueLeafFlags().get(LeafIdentifier.REVDECIMAL.getLeafIndex())) {
            if (appInstance.revDecimal() != revDecimal()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.revDecimal(appInstance.revDecimal());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.REVDECIMAL.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.revDecimal(appInstance.revDecimal());
        }

        return true;
    }


    /**
     * Creates an instance of defaultMultiRangeValidation.
     */
    protected DefaultMultiRangeValidation() {
    }

    /**
     * Returns the attribute MultiRangeValidationBuilder.
     *
     * @return value of MultiRangeValidationBuilder
     */
    public static MultiRangeValidationBuilder builder() {
        return new MultiRangeValidationBuilder();
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
     * Represents the builder implementation of multiRangeValidation.
     */
    public static class MultiRangeValidationBuilder implements MultiRangeValidation.MultiRangeValidationBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected BigDecimal decimal;
        protected BigDecimal revDecimal;

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


        @Override
        public BigDecimal decimal() {
            return decimal;
        }

        @Override
        public BigDecimal revDecimal() {
            return revDecimal;
        }

        @Override
        public MultiRangeValidationBuilder decimal(BigDecimal decimal) {
            getValueLeafFlags().set(LeafIdentifier.DECIMAL.getLeafIndex());
            this.decimal = decimal;
            return this;
        }

        @Override
        public MultiRangeValidationBuilder revDecimal(BigDecimal revDecimal) {
            getValueLeafFlags().set(LeafIdentifier.REVDECIMAL.getLeafIndex());
            this.revDecimal = revDecimal;
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
        public MultiRangeValidationBuilder onosYangNodeOperationType(OnosYangNodeOperationType
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



        @Override
        public MultiRangeValidationBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        @Override
        public void addYangAugmentedInfo(Object value, Class classObject) {
            yangAugmentedInfoMap.put(classObject, value);
        }

        @Override
        public Object yangAugmentedInfo(Class classObject) {
            return yangAugmentedInfoMap.get(classObject);
        }

        @Override
        public Map<Class<?>, Object> yangAugmentedInfoMap() {
            return yangAugmentedInfoMap;
        }
        @Override
        public MultiRangeValidation build() {
            return new DefaultMultiRangeValidation(this);
        }

        /**
         * Creates an instance of multiRangeValidationBuilder.
         */
        public MultiRangeValidationBuilder() {
        }
    }
}
