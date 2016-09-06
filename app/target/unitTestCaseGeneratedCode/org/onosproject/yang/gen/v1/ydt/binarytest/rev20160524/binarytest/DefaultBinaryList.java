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

package org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524.binarytest;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of binaryList.
 */
public class DefaultBinaryList implements BinaryList {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected byte[] binary;
    protected byte[] binaryWithRange;
    protected byte[] binaryWithMultiRange;

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
    public byte[] binary() {
        return binary;
    }

    @Override
    public byte[] binaryWithRange() {
        return binaryWithRange;
    }

    @Override
    public byte[] binaryWithMultiRange() {
        return binaryWithMultiRange;
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
        return Objects.hash(binary, binaryWithRange, binaryWithMultiRange);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultBinaryList) {
            DefaultBinaryList other = (DefaultBinaryList) obj;
            return
                 Objects.equals(binary, other.binary) &&
                 Objects.equals(binaryWithRange, other.binaryWithRange) &&
                 Objects.equals(binaryWithMultiRange, other.binaryWithMultiRange);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("binary", binary)
            .add("binaryWithRange", binaryWithRange)
            .add("binaryWithMultiRange", binaryWithMultiRange)
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
     * Creates an instance of binaryList.
     *
     * @param builderObject builder object of binaryList
     */
    protected DefaultBinaryList(BinaryListBuilder builderObject) {
        this.binary = builderObject.binary();
        this.binaryWithRange = builderObject.binaryWithRange();
        this.binaryWithMultiRange = builderObject.binaryWithMultiRange();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public BinaryList processSubtreeFiltering(BinaryList appInstance, boolean isSelectAllSchemaChild) {
        BinaryListBuilder subTreeFilteringResultBuilder = new BinaryListBuilder();
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
    private boolean processLeafSubtreeFiltering(BinaryList appInstance, BinaryListBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.BINARY.getLeafIndex())) {
            if (appInstance.binary() == null || !(binary().equals(appInstance.binary()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.binary(appInstance.binary());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.BINARY.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.binary(appInstance.binary());
        }

        if (getValueLeafFlags().get(LeafIdentifier.BINARYWITHRANGE.getLeafIndex())) {
            if (appInstance.binaryWithRange() == null || !(binaryWithRange().equals(appInstance.binaryWithRange()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.binaryWithRange(appInstance.binaryWithRange());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .BINARYWITHRANGE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.binaryWithRange(appInstance.binaryWithRange());
        }

        if (getValueLeafFlags().get(LeafIdentifier.BINARYWITHMULTIRANGE.getLeafIndex())) {
            if (appInstance.binaryWithMultiRange() == null || !(binaryWithMultiRange()
            .equals(appInstance.binaryWithMultiRange()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.binaryWithMultiRange(appInstance.binaryWithMultiRange());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .BINARYWITHMULTIRANGE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.binaryWithMultiRange(appInstance.binaryWithMultiRange());
        }

        return true;
    }


    /**
     * Creates an instance of defaultBinaryList.
     */
    protected DefaultBinaryList() {
    }

    /**
     * Returns the attribute BinaryListBuilder.
     *
     * @return value of BinaryListBuilder
     */
    public static BinaryListBuilder builder() {
        return new BinaryListBuilder();
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
     * Represents the builder implementation of binaryList.
     */
    public static class BinaryListBuilder implements BinaryList.BinaryListBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected byte[] binary;
        protected byte[] binaryWithRange;
        protected byte[] binaryWithMultiRange;

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
        public byte[] binary() {
            return binary;
        }

        @Override
        public byte[] binaryWithRange() {
            return binaryWithRange;
        }

        @Override
        public byte[] binaryWithMultiRange() {
            return binaryWithMultiRange;
        }

        @Override
        public BinaryListBuilder binary(byte[] binary) {
            getValueLeafFlags().set(LeafIdentifier.BINARY.getLeafIndex());
            this.binary = binary;
            return this;
        }

        @Override
        public BinaryListBuilder binaryWithRange(byte[] binaryWithRange) {
            getValueLeafFlags().set(LeafIdentifier.BINARYWITHRANGE.getLeafIndex());
            this.binaryWithRange = binaryWithRange;
            return this;
        }

        @Override
        public BinaryListBuilder binaryWithMultiRange(byte[] binaryWithMultiRange) {
            getValueLeafFlags().set(LeafIdentifier.BINARYWITHMULTIRANGE.getLeafIndex());
            this.binaryWithMultiRange = binaryWithMultiRange;
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
        public BinaryListBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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
        public BinaryListBuilder selectLeaf(LeafIdentifier leaf) {
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
        public BinaryList build() {
            return new DefaultBinaryList(this);
        }

        /**
         * Creates an instance of binaryListBuilder.
         */
        public BinaryListBuilder() {
        }
    }
}