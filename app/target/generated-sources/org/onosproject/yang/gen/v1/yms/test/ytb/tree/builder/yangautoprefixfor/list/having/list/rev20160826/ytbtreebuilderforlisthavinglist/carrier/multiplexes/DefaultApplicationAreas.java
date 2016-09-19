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
            .ytbtreebuilderforlisthavinglist.carrier.multiplexes;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of applicationAreas.
 */
public class DefaultApplicationAreas implements ApplicationAreas {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected List<byte[]> destinationAreas;
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
    public List<byte[]> destinationAreas() {
        return destinationAreas;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinationAreas, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultApplicationAreas) {
            DefaultApplicationAreas other = (DefaultApplicationAreas) obj;
            return
                 Objects.equals(destinationAreas, other.destinationAreas) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("destinationAreas", destinationAreas)
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
     * Creates an instance of applicationAreas.
     *
     * @param builderObject builder object of applicationAreas
     */
    protected DefaultApplicationAreas(ApplicationAreasBuilder builderObject) {
        this.destinationAreas = builderObject.destinationAreas();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public ApplicationAreas processSubtreeFiltering(ApplicationAreas appInstance, boolean isSelectAllSchemaChild) {
        ApplicationAreasBuilder subTreeFilteringResultBuilder = new ApplicationAreasBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafListSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
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
    private boolean processLeafListSubTreeFiltering(ApplicationAreas appInstance, ApplicationAreasBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (byte[] destinationAreas : appInstance.destinationAreas()) {
                subTreeFilteringResultBuilder.addToDestinationAreas(destinationAreas);
            }
        } else if (destinationAreas() != null) {
            if (!destinationAreas().isEmpty()) {
                if (appInstance.destinationAreas() == null || appInstance.destinationAreas().isEmpty()) {
                    return false;
                }
                for (byte[] destinationAreas : destinationAreas()) {
                    boolean flag = false;
                    for (byte[] destinationAreas2 : appInstance.destinationAreas()) {
                        if (destinationAreas.equals(destinationAreas2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToDestinationAreas(destinationAreas2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.destinationAreas() != null && !appInstance.destinationAreas().isEmpty()) {
                    for (byte[] destinationAreas : appInstance.destinationAreas()) {
                        subTreeFilteringResultBuilder.addToDestinationAreas(destinationAreas);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultApplicationAreas.
     */
    protected DefaultApplicationAreas() {
    }

    /**
     * Returns the attribute ApplicationAreasBuilder.
     *
     * @return value of ApplicationAreasBuilder
     */
    public static ApplicationAreasBuilder builder() {
        return new ApplicationAreasBuilder();
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
     * Represents the builder implementation of applicationAreas.
     */
    public static class ApplicationAreasBuilder implements ApplicationAreas.ApplicationAreasBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected List<byte[]> destinationAreas;
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
        public List<byte[]> destinationAreas() {
            return destinationAreas;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public ApplicationAreasBuilder destinationAreas(List<byte[]> destinationAreas) {
            this.destinationAreas = destinationAreas;
            return this;
        }

        @Override
        public ApplicationAreasBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public ApplicationAreasBuilder addToDestinationAreas(byte[] value) {
            if (destinationAreas() == null) {
                destinationAreas(new ArrayList<>());
            }
            destinationAreas().add(value);
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
        public ApplicationAreasBuilder selectLeaf(LeafIdentifier leaf) {
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
        public ApplicationAreas build() {
            return new DefaultApplicationAreas(this);
        }

        /**
         * Creates an instance of applicationAreasBuilder.
         */
        public ApplicationAreasBuilder() {
        }
    }
}
