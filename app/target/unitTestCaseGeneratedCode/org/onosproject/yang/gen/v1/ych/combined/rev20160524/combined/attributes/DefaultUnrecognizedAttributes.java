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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of unrecognizedAttributes.
 */
public class DefaultUnrecognizedAttributes implements UnrecognizedAttributes {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
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


    protected boolean partial;
    protected boolean transitive;
    protected short type;
    protected byte[] value;
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


    @Override
    public boolean partial() {
        return partial;
    }

    @Override
    public boolean transitive() {
        return transitive;
    }

    @Override
    public short type() {
        return type;
    }

    @Override
    public byte[] value() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(partial, transitive, type, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultUnrecognizedAttributes) {
            DefaultUnrecognizedAttributes other = (DefaultUnrecognizedAttributes) obj;
            return
                 Objects.equals(partial, other.partial) &&
                 Objects.equals(transitive, other.transitive) &&
                 Objects.equals(type, other.type) &&
                 Objects.equals(value, other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("partial", partial)
            .add("transitive", transitive)
            .add("type", type)
            .add("value", value)
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
     * Creates an instance of unrecognizedAttributes.
     *
     * @param builderObject builder object of unrecognizedAttributes
     */
    protected DefaultUnrecognizedAttributes(UnrecognizedAttributesBuilder builderObject) {
        this.partial = builderObject.partial();
        this.transitive = builderObject.transitive();
        this.type = builderObject.type();
        this.value = builderObject.value();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public UnrecognizedAttributes processSubtreeFiltering(UnrecognizedAttributes appInstance, boolean
            isSelectAllSchemaChild) {
        UnrecognizedAttributesBuilder subTreeFilteringResultBuilder = new UnrecognizedAttributesBuilder();
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
    private boolean processLeafSubtreeFiltering(UnrecognizedAttributes appInstance, UnrecognizedAttributesBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.PARTIAL.getLeafIndex())) {
            if (appInstance.partial() != partial()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.partial(appInstance.partial());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.PARTIAL.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.partial(appInstance.partial());
        }

        if (getValueLeafFlags().get(LeafIdentifier.TRANSITIVE.getLeafIndex())) {
            if (appInstance.transitive() != transitive()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.transitive(appInstance.transitive());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.TRANSITIVE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.transitive(appInstance.transitive());
        }

        if (getValueLeafFlags().get(LeafIdentifier.TYPE.getLeafIndex())) {
            if (appInstance.type() != type()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.type(appInstance.type());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.TYPE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.type(appInstance.type());
        }

        if (getValueLeafFlags().get(LeafIdentifier.VALUE.getLeafIndex())) {
            if (appInstance.value() == null || !(value().equals(appInstance.value()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.value(appInstance.value());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.VALUE.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.value(appInstance.value());
        }

        return true;
    }


    /**
     * Creates an instance of defaultUnrecognizedAttributes.
     */
    protected DefaultUnrecognizedAttributes() {
    }

    /**
     * Returns the attribute UnrecognizedAttributesBuilder.
     *
     * @return value of UnrecognizedAttributesBuilder
     */
    public static UnrecognizedAttributesBuilder builder() {
        return new UnrecognizedAttributesBuilder();
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
     * Represents the builder implementation of unrecognizedAttributes.
     */
    public static class UnrecognizedAttributesBuilder implements UnrecognizedAttributes
            .UnrecognizedAttributesBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected boolean partial;
        protected boolean transitive;
        protected short type;
        protected byte[] value;
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
        public UnrecognizedAttributesBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public boolean partial() {
            return partial;
        }

        @Override
        public boolean transitive() {
            return transitive;
        }

        @Override
        public short type() {
            return type;
        }

        @Override
        public byte[] value() {
            return value;
        }

        @Override
        public UnrecognizedAttributesBuilder partial(boolean partial) {
            getValueLeafFlags().set(LeafIdentifier.PARTIAL.getLeafIndex());
            this.partial = partial;
            return this;
        }

        @Override
        public UnrecognizedAttributesBuilder transitive(boolean transitive) {
            getValueLeafFlags().set(LeafIdentifier.TRANSITIVE.getLeafIndex());
            this.transitive = transitive;
            return this;
        }

        @Override
        public UnrecognizedAttributesBuilder type(short type) {
            getValueLeafFlags().set(LeafIdentifier.TYPE.getLeafIndex());
            this.type = type;
            return this;
        }

        @Override
        public UnrecognizedAttributesBuilder value(byte[] value) {
            getValueLeafFlags().set(LeafIdentifier.VALUE.getLeafIndex());
            this.value = value;
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
        public UnrecognizedAttributesBuilder selectLeaf(LeafIdentifier leaf) {
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
        public UnrecognizedAttributes build() {
            return new DefaultUnrecognizedAttributes(this);
        }

        /**
         * Creates an instance of unrecognizedAttributesBuilder.
         */
        public UnrecognizedAttributesBuilder() {
        }
    }
}
