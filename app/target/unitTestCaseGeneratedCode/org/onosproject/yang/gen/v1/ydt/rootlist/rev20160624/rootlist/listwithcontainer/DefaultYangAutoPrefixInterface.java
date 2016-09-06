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

package org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.rootlist.listwithcontainer;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of yangAutoPrefixInterface.
 */
public class DefaultYangAutoPrefixInterface implements YangAutoPrefixInterface {

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


    protected int invalidinterval;
    protected int invalid;
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
    public int invalidinterval() {
        return invalidinterval;
    }

    @Override
    public int invalid() {
        return invalid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invalidinterval, invalid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultYangAutoPrefixInterface) {
            DefaultYangAutoPrefixInterface other = (DefaultYangAutoPrefixInterface) obj;
            return
                 Objects.equals(invalidinterval, other.invalidinterval) &&
                 Objects.equals(invalid, other.invalid);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("invalidinterval", invalidinterval)
            .add("invalid", invalid)
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
     * Creates an instance of yangAutoPrefixInterface.
     *
     * @param builderObject builder object of yangAutoPrefixInterface
     */
    protected DefaultYangAutoPrefixInterface(YangAutoPrefixInterfaceBuilder builderObject) {
        this.invalidinterval = builderObject.invalidinterval();
        this.invalid = builderObject.invalid();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public YangAutoPrefixInterface processSubtreeFiltering(YangAutoPrefixInterface appInstance, boolean
            isSelectAllSchemaChild) {
        YangAutoPrefixInterfaceBuilder subTreeFilteringResultBuilder = new YangAutoPrefixInterfaceBuilder();
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
    private boolean processLeafSubtreeFiltering(YangAutoPrefixInterface appInstance, YangAutoPrefixInterfaceBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.INVALIDINTERVAL.getLeafIndex())) {
            if (appInstance.invalidinterval() != invalidinterval()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.invalidinterval(appInstance.invalidinterval());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .INVALIDINTERVAL.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.invalidinterval(appInstance.invalidinterval());
        }

        if (getValueLeafFlags().get(LeafIdentifier.INVALID.getLeafIndex())) {
            if (appInstance.invalid() != invalid()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.invalid(appInstance.invalid());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.INVALID.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.invalid(appInstance.invalid());
        }

        return true;
    }


    /**
     * Creates an instance of defaultYangAutoPrefixInterface.
     */
    protected DefaultYangAutoPrefixInterface() {
    }

    /**
     * Returns the attribute YangAutoPrefixInterfaceBuilder.
     *
     * @return value of YangAutoPrefixInterfaceBuilder
     */
    public static YangAutoPrefixInterfaceBuilder builder() {
        return new YangAutoPrefixInterfaceBuilder();
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
     * Represents the builder implementation of yangAutoPrefixInterface.
     */
    public static class YangAutoPrefixInterfaceBuilder implements YangAutoPrefixInterface
            .YangAutoPrefixInterfaceBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected int invalidinterval;
        protected int invalid;
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
        public YangAutoPrefixInterfaceBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public int invalidinterval() {
            return invalidinterval;
        }

        @Override
        public int invalid() {
            return invalid;
        }

        @Override
        public YangAutoPrefixInterfaceBuilder invalidinterval(int invalidinterval) {
            getValueLeafFlags().set(LeafIdentifier.INVALIDINTERVAL.getLeafIndex());
            this.invalidinterval = invalidinterval;
            return this;
        }

        @Override
        public YangAutoPrefixInterfaceBuilder invalid(int invalid) {
            getValueLeafFlags().set(LeafIdentifier.INVALID.getLeafIndex());
            this.invalid = invalid;
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
        public YangAutoPrefixInterfaceBuilder selectLeaf(LeafIdentifier leaf) {
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
        public YangAutoPrefixInterface build() {
            return new DefaultYangAutoPrefixInterface(this);
        }

        /**
         * Creates an instance of yangAutoPrefixInterfaceBuilder.
         */
        public YangAutoPrefixInterfaceBuilder() {
        }
    }
}
