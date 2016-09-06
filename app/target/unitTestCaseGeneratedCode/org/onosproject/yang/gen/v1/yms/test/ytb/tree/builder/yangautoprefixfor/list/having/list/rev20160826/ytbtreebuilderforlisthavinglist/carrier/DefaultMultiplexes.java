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

package org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.carrier;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.carrier.multiplexes.ApplicationAreas;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.carrier.multiplexes.TypesEnum;

/**
 * Represents the implementation of multiplexes.
 */
public class DefaultMultiplexes implements Multiplexes {

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


    protected TypesEnum types;
    protected List<ApplicationAreas> applicationAreas = new ArrayList<>();
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
    public TypesEnum types() {
        return types;
    }

    @Override
    public List<ApplicationAreas> applicationAreas() {
        return applicationAreas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(types, applicationAreas);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultMultiplexes) {
            DefaultMultiplexes other = (DefaultMultiplexes) obj;
            return
                 Objects.equals(types, other.types) &&
                 Objects.equals(applicationAreas, other.applicationAreas);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("types", types)
            .add("applicationAreas", applicationAreas)
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
     * Creates an instance of multiplexes.
     *
     * @param builderObject builder object of multiplexes
     */
    protected DefaultMultiplexes(MultiplexesBuilder builderObject) {
        this.types = builderObject.types();
        this.applicationAreas = builderObject.applicationAreas();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Multiplexes processSubtreeFiltering(Multiplexes appInstance, boolean isSelectAllSchemaChild) {
        MultiplexesBuilder subTreeFilteringResultBuilder = new MultiplexesBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
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
    private boolean processLeafSubtreeFiltering(Multiplexes appInstance, MultiplexesBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.TYPES.getLeafIndex())) {
            if (appInstance.types() == null || !(types().equals(appInstance.types()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.types(appInstance.types());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.TYPES.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.types(appInstance.types());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(Multiplexes appInstance, MultiplexesBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (ApplicationAreas applicationAreas : appInstance.applicationAreas()) {
                subTreeFilteringResultBuilder.addToApplicationAreas(applicationAreas);
            }
        } else if (applicationAreas() != null) {
            isAnySelectOrContainmentNode = true;
            if (!applicationAreas().isEmpty()) {
                if (appInstance.applicationAreas() != null && !appInstance.applicationAreas().isEmpty()) {
                    for (ApplicationAreas applicationAreas : applicationAreas()) {
                        for (ApplicationAreas applicationAreas2 : appInstance.applicationAreas()) {
                            ApplicationAreas result = applicationAreas
            .processSubtreeFiltering(applicationAreas2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToApplicationAreas(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.applicationAreas() != null && !appInstance.applicationAreas().isEmpty()) {
                    for (ApplicationAreas applicationAreas : appInstance.applicationAreas()) {
                        subTreeFilteringResultBuilder.addToApplicationAreas(applicationAreas);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultMultiplexes.
     */
    protected DefaultMultiplexes() {
    }

    /**
     * Returns the attribute MultiplexesBuilder.
     *
     * @return value of MultiplexesBuilder
     */
    public static MultiplexesBuilder builder() {
        return new MultiplexesBuilder();
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
     * Represents the builder implementation of multiplexes.
     */
    public static class MultiplexesBuilder implements Multiplexes.MultiplexesBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected TypesEnum types;
        protected List<ApplicationAreas> applicationAreas = new ArrayList<>();
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
        public MultiplexesBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public TypesEnum types() {
            return types;
        }

        @Override
        public List<ApplicationAreas> applicationAreas() {
            return applicationAreas;
        }

        @Override
        public MultiplexesBuilder types(TypesEnum types) {
            getValueLeafFlags().set(LeafIdentifier.TYPES.getLeafIndex());
            this.types = types;
            return this;
        }

        @Override
        public MultiplexesBuilder applicationAreas(List<ApplicationAreas> applicationAreas) {
            this.applicationAreas = applicationAreas;
            return this;
        }

        @Override
        public MultiplexesBuilder addToApplicationAreas(ApplicationAreas value) {
            applicationAreas().add(value);
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
        public MultiplexesBuilder selectLeaf(LeafIdentifier leaf) {
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
        public Multiplexes build() {
            return new DefaultMultiplexes(this);
        }

        /**
         * Creates an instance of multiplexesBuilder.
         */
        public MultiplexesBuilder() {
        }
    }
}
