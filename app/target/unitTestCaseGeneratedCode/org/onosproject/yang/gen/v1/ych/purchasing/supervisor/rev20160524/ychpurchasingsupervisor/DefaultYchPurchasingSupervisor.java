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

package org.onosproject.yang.gen.v1.ych.purchasing.supervisor.rev20160524.ychpurchasingsupervisor;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of ychPurchasingSupervisor.
 */
public class DefaultYchPurchasingSupervisor implements YchPurchasingSupervisor {

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


    protected String ychPurchasingSpecialist;
    protected String ychPurchasingSupport;
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
    public String ychPurchasingSpecialist() {
        return ychPurchasingSpecialist;
    }

    @Override
    public String ychPurchasingSupport() {
        return ychPurchasingSupport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ychPurchasingSpecialist, ychPurchasingSupport);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultYchPurchasingSupervisor) {
            DefaultYchPurchasingSupervisor other = (DefaultYchPurchasingSupervisor) obj;
            return
                 Objects.equals(ychPurchasingSpecialist, other.ychPurchasingSpecialist) &&
                 Objects.equals(ychPurchasingSupport, other.ychPurchasingSupport);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("ychPurchasingSpecialist", ychPurchasingSpecialist)
            .add("ychPurchasingSupport", ychPurchasingSupport)
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
     * Creates an instance of ychPurchasingSupervisor.
     *
     * @param builderObject builder object of ychPurchasingSupervisor
     */
    protected DefaultYchPurchasingSupervisor(YchPurchasingSupervisorBuilder builderObject) {
        this.ychPurchasingSpecialist = builderObject.ychPurchasingSpecialist();
        this.ychPurchasingSupport = builderObject.ychPurchasingSupport();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public YchPurchasingSupervisor processSubtreeFiltering(YchPurchasingSupervisor appInstance, boolean
            isSelectAllSchemaChild) {
        YchPurchasingSupervisorBuilder subTreeFilteringResultBuilder = new YchPurchasingSupervisorBuilder();
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
    private boolean processLeafSubtreeFiltering(YchPurchasingSupervisor appInstance, YchPurchasingSupervisorBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.YCHPURCHASINGSPECIALIST.getLeafIndex())) {
            if (appInstance.ychPurchasingSpecialist() == null || !(ychPurchasingSpecialist()
            .equals(appInstance.ychPurchasingSpecialist()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.ychPurchasingSpecialist(appInstance.ychPurchasingSpecialist());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .YCHPURCHASINGSPECIALIST.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.ychPurchasingSpecialist(appInstance.ychPurchasingSpecialist());
        }

        if (getValueLeafFlags().get(LeafIdentifier.YCHPURCHASINGSUPPORT.getLeafIndex())) {
            if (appInstance.ychPurchasingSupport() == null || !(ychPurchasingSupport()
            .equals(appInstance.ychPurchasingSupport()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.ychPurchasingSupport(appInstance.ychPurchasingSupport());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .YCHPURCHASINGSUPPORT.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.ychPurchasingSupport(appInstance.ychPurchasingSupport());
        }

        return true;
    }


    /**
     * Creates an instance of defaultYchPurchasingSupervisor.
     */
    protected DefaultYchPurchasingSupervisor() {
    }

    /**
     * Returns the attribute YchPurchasingSupervisorBuilder.
     *
     * @return value of YchPurchasingSupervisorBuilder
     */
    public static YchPurchasingSupervisorBuilder builder() {
        return new YchPurchasingSupervisorBuilder();
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
     * Represents the builder implementation of ychPurchasingSupervisor.
     */
    public static class YchPurchasingSupervisorBuilder implements YchPurchasingSupervisor
            .YchPurchasingSupervisorBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected String ychPurchasingSpecialist;
        protected String ychPurchasingSupport;
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
        public YchPurchasingSupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public String ychPurchasingSpecialist() {
            return ychPurchasingSpecialist;
        }

        @Override
        public String ychPurchasingSupport() {
            return ychPurchasingSupport;
        }

        @Override
        public YchPurchasingSupervisorBuilder ychPurchasingSpecialist(String ychPurchasingSpecialist) {
            getValueLeafFlags().set(LeafIdentifier.YCHPURCHASINGSPECIALIST.getLeafIndex());
            this.ychPurchasingSpecialist = ychPurchasingSpecialist;
            return this;
        }

        @Override
        public YchPurchasingSupervisorBuilder ychPurchasingSupport(String ychPurchasingSupport) {
            getValueLeafFlags().set(LeafIdentifier.YCHPURCHASINGSUPPORT.getLeafIndex());
            this.ychPurchasingSupport = ychPurchasingSupport;
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
        public YchPurchasingSupervisorBuilder selectLeaf(LeafIdentifier leaf) {
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
        public YchPurchasingSupervisor build() {
            return new DefaultYchPurchasingSupervisor(this);
        }

        /**
         * Creates an instance of ychPurchasingSupervisorBuilder.
         */
        public YchPurchasingSupervisorBuilder() {
        }
    }
}
