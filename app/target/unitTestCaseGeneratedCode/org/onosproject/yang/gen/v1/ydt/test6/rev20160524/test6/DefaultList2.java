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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of list2.
 */
public class DefaultList2 implements List2 {

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


    protected String leaf5;
    protected String leaf6;
    protected String leaf7;
    protected List<String> leaflist8 = new ArrayList<>();
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
    public String leaf5() {
        return leaf5;
    }

    @Override
    public String leaf6() {
        return leaf6;
    }

    @Override
    public String leaf7() {
        return leaf7;
    }

    @Override
    public List<String> leaflist8() {
        return leaflist8;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaf5, leaf6, leaf7, leaflist8);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultList2) {
            DefaultList2 other = (DefaultList2) obj;
            return
                 Objects.equals(leaf5, other.leaf5) &&
                 Objects.equals(leaf6, other.leaf6) &&
                 Objects.equals(leaf7, other.leaf7) &&
                 Objects.equals(leaflist8, other.leaflist8);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("leaf5", leaf5)
            .add("leaf6", leaf6)
            .add("leaf7", leaf7)
            .add("leaflist8", leaflist8)
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
     * Creates an instance of list2.
     *
     * @param builderObject builder object of list2
     */
    protected DefaultList2(List2Builder builderObject) {
        this.leaf5 = builderObject.leaf5();
        this.leaf6 = builderObject.leaf6();
        this.leaf7 = builderObject.leaf7();
        this.leaflist8 = builderObject.leaflist8();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public List2 processSubtreeFiltering(List2 appInstance, boolean isSelectAllSchemaChild) {
        List2Builder subTreeFilteringResultBuilder = new List2Builder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
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
    private boolean processLeafSubtreeFiltering(List2 appInstance, List2Builder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.LEAF5.getLeafIndex())) {
            if (appInstance.leaf5() == null || !(leaf5().equals(appInstance.leaf5()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.leaf5(appInstance.leaf5());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.LEAF5.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.leaf5(appInstance.leaf5());
        }

        if (getValueLeafFlags().get(LeafIdentifier.LEAF6.getLeafIndex())) {
            if (appInstance.leaf6() == null || !(leaf6().equals(appInstance.leaf6()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.leaf6(appInstance.leaf6());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.LEAF6.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.leaf6(appInstance.leaf6());
        }

        if (getValueLeafFlags().get(LeafIdentifier.LEAF7.getLeafIndex())) {
            if (appInstance.leaf7() == null || !(leaf7().equals(appInstance.leaf7()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.leaf7(appInstance.leaf7());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.LEAF7.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.leaf7(appInstance.leaf7());
        }

        return true;
    }

    private boolean processLeafListSubTreeFiltering(List2 appInstance, List2Builder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (String leaflist8 : appInstance.leaflist8()) {
                subTreeFilteringResultBuilder.addToLeaflist8(leaflist8);
            }
        } else if (leaflist8() != null) {
            if (!leaflist8().isEmpty()) {
                if (appInstance.leaflist8() == null || appInstance.leaflist8().isEmpty()) {
                    return false;
                }
                for (String leaflist8 : leaflist8()) {
                    boolean flag = false;
                    for (String leaflist82 : appInstance.leaflist8()) {
                        if (leaflist8.equals(leaflist82)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToLeaflist8(leaflist82);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.leaflist8() != null && !appInstance.leaflist8().isEmpty()) {
                    for (String leaflist8 : appInstance.leaflist8()) {
                        subTreeFilteringResultBuilder.addToLeaflist8(leaflist8);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultList2.
     */
    protected DefaultList2() {
    }

    /**
     * Returns the attribute List2Builder.
     *
     * @return value of List2Builder
     */
    public static List2Builder builder() {
        return new List2Builder();
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
     * Represents the builder implementation of list2.
     */
    public static class List2Builder implements List2.List2Builder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected String leaf5;
        protected String leaf6;
        protected String leaf7;
        protected List<String> leaflist8 = new ArrayList<>();
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
        public List2Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public String leaf5() {
            return leaf5;
        }

        @Override
        public String leaf6() {
            return leaf6;
        }

        @Override
        public String leaf7() {
            return leaf7;
        }

        @Override
        public List<String> leaflist8() {
            return leaflist8;
        }

        @Override
        public List2Builder leaf5(String leaf5) {
            getValueLeafFlags().set(LeafIdentifier.LEAF5.getLeafIndex());
            this.leaf5 = leaf5;
            return this;
        }

        @Override
        public List2Builder leaf6(String leaf6) {
            getValueLeafFlags().set(LeafIdentifier.LEAF6.getLeafIndex());
            this.leaf6 = leaf6;
            return this;
        }

        @Override
        public List2Builder leaf7(String leaf7) {
            getValueLeafFlags().set(LeafIdentifier.LEAF7.getLeafIndex());
            this.leaf7 = leaf7;
            return this;
        }

        @Override
        public List2Builder leaflist8(List<String> leaflist8) {
            this.leaflist8 = leaflist8;
            return this;
        }

        @Override
        public List2Builder addToLeaflist8(String value) {
            leaflist8().add(value);
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
        public List2Builder selectLeaf(LeafIdentifier leaf) {
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
        public List2 build() {
            return new DefaultList2(this);
        }

        /**
         * Creates an instance of list2Builder.
         */
        public List2Builder() {
        }
    }
}
