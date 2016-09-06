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

package org.onosproject.yang.gen.v1.ydt.purchasing.supervisor.rev20160524.purchasingsupervisor;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of supervisor.
 */
public class DefaultSupervisor implements Supervisor {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected String purchasingSpecialist;
    protected String support;

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
    public String purchasingSpecialist() {
        return purchasingSpecialist;
    }

    @Override
    public String support() {
        return support;
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
        return Objects.hash(purchasingSpecialist, support);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultSupervisor) {
            DefaultSupervisor other = (DefaultSupervisor) obj;
            return
                 Objects.equals(purchasingSpecialist, other.purchasingSpecialist) &&
                 Objects.equals(support, other.support);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("purchasingSpecialist", purchasingSpecialist)
            .add("support", support)
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
     * Creates an instance of supervisor.
     *
     * @param builderObject builder object of supervisor
     */
    protected DefaultSupervisor(SupervisorBuilder builderObject) {
        this.purchasingSpecialist = builderObject.purchasingSpecialist();
        this.support = builderObject.support();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Supervisor processSubtreeFiltering(Supervisor appInstance, boolean isSelectAllSchemaChild) {
        SupervisorBuilder subTreeFilteringResultBuilder = new SupervisorBuilder();
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
    private boolean processLeafSubtreeFiltering(Supervisor appInstance, SupervisorBuilder
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
     * Creates an instance of defaultSupervisor.
     */
    protected DefaultSupervisor() {
    }

    /**
     * Returns the attribute SupervisorBuilder.
     *
     * @return value of SupervisorBuilder
     */
    public static SupervisorBuilder builder() {
        return new SupervisorBuilder();
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
     * Represents the builder implementation of supervisor.
     */
    public static class SupervisorBuilder implements Supervisor.SupervisorBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected String purchasingSpecialist;
        protected String support;

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
        public String purchasingSpecialist() {
            return purchasingSpecialist;
        }

        @Override
        public String support() {
            return support;
        }

        @Override
        public SupervisorBuilder purchasingSpecialist(String purchasingSpecialist) {
            getValueLeafFlags().set(LeafIdentifier.PURCHASINGSPECIALIST.getLeafIndex());
            this.purchasingSpecialist = purchasingSpecialist;
            return this;
        }

        @Override
        public SupervisorBuilder support(String support) {
            getValueLeafFlags().set(LeafIdentifier.SUPPORT.getLeafIndex());
            this.support = support;
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
        public SupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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
        public SupervisorBuilder selectLeaf(LeafIdentifier leaf) {
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
        public Supervisor build() {
            return new DefaultSupervisor(this);
        }

        /**
         * Creates an instance of supervisorBuilder.
         */
        public SupervisorBuilder() {
        }
    }
}