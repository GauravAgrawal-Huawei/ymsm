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

package org.onosproject.yang.gen.v1.ydt.root.rev20160524.logisticsmanager;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of purchasingSupervisor.
 */
public class DefaultPurchasingSupervisor implements PurchasingSupervisor {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected String purchasingSpecialist;
    protected String support;
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

    @Override
    public String purchasingSpecialist() {
        return purchasingSpecialist;
    }

    @Override
    public String support() {
        return support;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchasingSpecialist, support, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultPurchasingSupervisor) {
            DefaultPurchasingSupervisor other = (DefaultPurchasingSupervisor) obj;
            return
                 Objects.equals(purchasingSpecialist, other.purchasingSpecialist) &&
                 Objects.equals(support, other.support) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("purchasingSpecialist", purchasingSpecialist)
            .add("support", support)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
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
     * Creates an instance of purchasingSupervisor.
     *
     * @param builderObject builder object of purchasingSupervisor
     */
    protected DefaultPurchasingSupervisor(PurchasingSupervisorBuilder builderObject) {
        this.purchasingSpecialist = builderObject.purchasingSpecialist();
        this.support = builderObject.support();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public PurchasingSupervisor processSubtreeFiltering(PurchasingSupervisor appInstance, boolean
            isSelectAllSchemaChild) {
        PurchasingSupervisorBuilder subTreeFilteringResultBuilder = new PurchasingSupervisorBuilder();
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
    private boolean processLeafSubtreeFiltering(PurchasingSupervisor appInstance, PurchasingSupervisorBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.PURCHASINGSPECIALIST.getLeafIndex())) {
            if (appInstance.purchasingSpecialist() == null || !(purchasingSpecialist()
            .equals(appInstance.purchasingSpecialist()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.purchasingSpecialist(appInstance.purchasingSpecialist());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .PURCHASINGSPECIALIST.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.purchasingSpecialist(appInstance.purchasingSpecialist());
        }

        if (getValueLeafFlags().get(LeafIdentifier.SUPPORT.getLeafIndex())) {
            if (appInstance.support() == null || !(support().equals(appInstance.support()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.support(appInstance.support());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.SUPPORT.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.support(appInstance.support());
        }

        return true;
    }


    /**
     * Creates an instance of defaultPurchasingSupervisor.
     */
    protected DefaultPurchasingSupervisor() {
    }

    /**
     * Returns the attribute PurchasingSupervisorBuilder.
     *
     * @return value of PurchasingSupervisorBuilder
     */
    public static PurchasingSupervisorBuilder builder() {
        return new PurchasingSupervisorBuilder();
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
     * Represents the builder implementation of purchasingSupervisor.
     */
    public static class PurchasingSupervisorBuilder implements PurchasingSupervisor.PurchasingSupervisorBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected String purchasingSpecialist;
        protected String support;
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


        @Override
        public String purchasingSpecialist() {
            return purchasingSpecialist;
        }

        @Override
        public String support() {
            return support;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public PurchasingSupervisorBuilder purchasingSpecialist(String purchasingSpecialist) {
            getValueLeafFlags().set(LeafIdentifier.PURCHASINGSPECIALIST.getLeafIndex());
            this.purchasingSpecialist = purchasingSpecialist;
            return this;
        }

        @Override
        public PurchasingSupervisorBuilder support(String support) {
            getValueLeafFlags().set(LeafIdentifier.SUPPORT.getLeafIndex());
            this.support = support;
            return this;
        }

        @Override
        public PurchasingSupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType
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
        public PurchasingSupervisorBuilder selectLeaf(LeafIdentifier leaf) {
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
        public PurchasingSupervisor build() {
            return new DefaultPurchasingSupervisor(this);
        }

        /**
         * Creates an instance of purchasingSupervisorBuilder.
         */
        public PurchasingSupervisorBuilder() {
        }
    }
}
