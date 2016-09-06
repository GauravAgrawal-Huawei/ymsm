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
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont1.Cont2;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont1.List1;

/**
 * Represents the implementation of cont1.
 */
public class DefaultCont1 implements Cont1 {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected String leaf4;
    protected Cont2 cont2;
    protected List<List1> list1 = new ArrayList<>();

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
    public String leaf4() {
        return leaf4;
    }

    @Override
    public Cont2 cont2() {
        return cont2;
    }

    @Override
    public List<List1> list1() {
        return list1;
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
        return Objects.hash(leaf4, cont2, list1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultCont1) {
            DefaultCont1 other = (DefaultCont1) obj;
            return
                 Objects.equals(leaf4, other.leaf4) &&
                 Objects.equals(cont2, other.cont2) &&
                 Objects.equals(list1, other.list1);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("leaf4", leaf4)
            .add("cont2", cont2)
            .add("list1", list1)
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
     * Creates an instance of cont1.
     *
     * @param builderObject builder object of cont1
     */
    protected DefaultCont1(Cont1Builder builderObject) {
        this.leaf4 = builderObject.leaf4();
        this.cont2 = builderObject.cont2();
        this.list1 = builderObject.list1();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Cont1 processSubtreeFiltering(Cont1 appInstance, boolean isSelectAllSchemaChild) {
        Cont1Builder subTreeFilteringResultBuilder = new Cont1Builder();
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
    private boolean processLeafSubtreeFiltering(Cont1 appInstance, Cont1Builder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.LEAF4.getLeafIndex())) {
            if (appInstance.leaf4() == null || !(leaf4().equals(appInstance.leaf4()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.leaf4(appInstance.leaf4());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.LEAF4.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.leaf4(appInstance.leaf4());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(Cont1 appInstance, Cont1Builder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cont2()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont2() != null) {
                Cont2 result = cont2.processSubtreeFiltering(appInstance.cont2(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont2(result);
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (List1 list1 : appInstance.list1()) {
                subTreeFilteringResultBuilder.addToList1(list1);
            }
        } else if (list1() != null) {
            isAnySelectOrContainmentNode = true;
            if (!list1().isEmpty()) {
                if (appInstance.list1() != null && !appInstance.list1().isEmpty()) {
                    for (List1 list1 : list1()) {
                        for (List1 list12 : appInstance.list1()) {
                            List1 result = list1.processSubtreeFiltering(list12, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToList1(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.list1() != null && !appInstance.list1().isEmpty()) {
                    for (List1 list1 : appInstance.list1()) {
                        subTreeFilteringResultBuilder.addToList1(list1);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultCont1.
     */
    protected DefaultCont1() {
    }

    /**
     * Returns the attribute Cont1Builder.
     *
     * @return value of Cont1Builder
     */
    public static Cont1Builder builder() {
        return new Cont1Builder();
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
     * Represents the builder implementation of cont1.
     */
    public static class Cont1Builder implements Cont1.Cont1Builder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected String leaf4;
        protected Cont2 cont2;
        protected List<List1> list1 = new ArrayList<>();

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
        public String leaf4() {
            return leaf4;
        }

        @Override
        public Cont2 cont2() {
            return cont2;
        }

        @Override
        public List<List1> list1() {
            return list1;
        }

        @Override
        public Cont1Builder leaf4(String leaf4) {
            getValueLeafFlags().set(LeafIdentifier.LEAF4.getLeafIndex());
            this.leaf4 = leaf4;
            return this;
        }

        @Override
        public Cont1Builder cont2(Cont2 cont2) {
            this.cont2 = cont2;
            return this;
        }

        @Override
        public Cont1Builder list1(List<List1> list1) {
            this.list1 = list1;
            return this;
        }

        @Override
        public Cont1Builder addToList1(List1 value) {
            list1().add(value);
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
        public Cont1Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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
        public Cont1Builder selectLeaf(LeafIdentifier leaf) {
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
        public Cont1 build() {
            return new DefaultCont1(this);
        }

        /**
         * Creates an instance of cont1Builder.
         */
        public Cont1Builder() {
        }
    }
}
