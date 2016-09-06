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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.Cont1;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.Cont4;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.List2;

/**
 * Represents the implementation of test6.
 */
public class Test6OpParam implements Test6 {

    protected String leaf10;
    protected Cont1 cont1;
    protected List<List2> list2 = new ArrayList<>();
    protected Cont4 cont4;

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
    public String leaf10() {
        return leaf10;
    }
    public Cont1 cont1() {
        return cont1;
    }
    public List<List2> list2() {
        return list2;
    }
    public Cont4 cont4() {
        return cont4;
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
        return Objects.hash(leaf10, cont1, list2, cont4);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Test6OpParam) {
            Test6OpParam other = (Test6OpParam) obj;
            return
                 Objects.equals(leaf10, other.leaf10) &&
                 Objects.equals(cont1, other.cont1) &&
                 Objects.equals(list2, other.list2) &&
                 Objects.equals(cont4, other.cont4);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("leaf10", leaf10)
            .add("cont1", cont1)
            .add("list2", list2)
            .add("cont4", cont4)
            .toString();
    }

    /**
     * Creates an instance of test6.
     *
     * @param builderObject builder object of test6
     */
    protected Test6OpParam(Test6Builder builderObject) {
        this.leaf10 = builderObject.leaf10();
        this.cont1 = builderObject.cont1();
        this.list2 = builderObject.list2();
        this.cont4 = builderObject.cont4();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public Test6 processSubtreeFiltering(Test6 appInstance, boolean isSelectAllSchemaChild) {
        Test6Builder subTreeFilteringResultBuilder = new Test6Builder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(Test6 appInstance, Test6Builder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.LEAF10.getLeafIndex())) {
            if (appInstance.leaf10() == null || !(leaf10().equals(appInstance.leaf10()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.leaf10(appInstance.leaf10());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.LEAF10.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.leaf10(appInstance.leaf10());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(Test6 appInstance, Test6Builder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cont1()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont1() != null) {
                Cont1 result = cont1.processSubtreeFiltering(appInstance.cont1(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont1(result);
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (List2 list2 : appInstance.list2()) {
                subTreeFilteringResultBuilder.addToList2(list2);
            }
        } else if (list2() != null) {
            isAnySelectOrContainmentNode = true;
            if (!list2().isEmpty()) {
                if (appInstance.list2() != null && !appInstance.list2().isEmpty()) {
                    for (List2 list2 : list2()) {
                        for (List2 list22 : appInstance.list2()) {
                            List2 result = list2.processSubtreeFiltering(list22, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToList2(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.list2() != null && !appInstance.list2().isEmpty()) {
                    for (List2 list2 : appInstance.list2()) {
                        subTreeFilteringResultBuilder.addToList2(list2);
                    }
                }
            }
        }

        if (cont4()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont4() != null) {
                Cont4 result = cont4.processSubtreeFiltering(appInstance.cont4(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont4(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of test6OpParam.
     */
    protected Test6OpParam() {
    }

    /**
     * Returns the attribute Test6Builder.
     *
     * @return value of Test6Builder
     */
    public static Test6Builder builder() {
        return new Test6Builder();
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
     * Represents the builder implementation of test6.
     */
    public static class Test6Builder {

        protected String leaf10;
        protected Cont1 cont1;
        protected List<List2> list2 = new ArrayList<>();
        protected Cont4 cont4;

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

        public String leaf10() {
            return leaf10;
        }
        public Cont1 cont1() {
            return cont1;
        }
        public List<List2> list2() {
            return list2;
        }
        public Cont4 cont4() {
            return cont4;
        }

        /**
         * Returns the builder object of leaf10.
         *
         * @param leaf10 value of leaf10
         * @return builder object of leaf10
         */
        public Test6Builder leaf10(String leaf10) {
            getValueLeafFlags().set(LeafIdentifier.LEAF10.getLeafIndex());
            this.leaf10 = leaf10;
            return this;
        }

        /**
         * Returns the builder object of cont1.
         *
         * @param cont1 value of cont1
         * @return builder object of cont1
         */
        public Test6Builder cont1(Cont1 cont1) {
            this.cont1 = cont1;
            return this;
        }

        /**
         * Returns the builder object of list2.
         *
         * @param list2 list of list2
         * @return builder object of list2
         */
        public Test6Builder list2(List<List2> list2) {
            this.list2 = list2;
            return this;
        }

        /**
         * Returns the builder object of cont4.
         *
         * @param cont4 value of cont4
         * @return builder object of cont4
         */
        public Test6Builder cont4(Cont4 cont4) {
            this.cont4 = cont4;
            return this;
        }
        public Test6Builder addToList2(List2 value) {
            list2().add(value);
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
        public Test6Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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



        public Test6Builder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public Test6 build() {
            return new Test6OpParam(this);
        }

        /**
         * Creates an instance of test6Builder.
         */
        public Test6Builder() {
        }
    }
}
