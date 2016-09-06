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

package org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903.helloonos.helloworld.helloworldinput;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of stringList.
 */
public class DefaultStringList implements StringList {

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


    protected String string1;
    protected String string2;
    protected String string3;
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
    public String string1() {
        return string1;
    }

    @Override
    public String string2() {
        return string2;
    }

    @Override
    public String string3() {
        return string3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(string1, string2, string3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultStringList) {
            DefaultStringList other = (DefaultStringList) obj;
            return
                 Objects.equals(string1, other.string1) &&
                 Objects.equals(string2, other.string2) &&
                 Objects.equals(string3, other.string3);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("string1", string1)
            .add("string2", string2)
            .add("string3", string3)
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
     * Creates an instance of stringList.
     *
     * @param builderObject builder object of stringList
     */
    protected DefaultStringList(StringListBuilder builderObject) {
        this.string1 = builderObject.string1();
        this.string2 = builderObject.string2();
        this.string3 = builderObject.string3();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public StringList processSubtreeFiltering(StringList appInstance, boolean isSelectAllSchemaChild) {
        StringListBuilder subTreeFilteringResultBuilder = new StringListBuilder();
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
    private boolean processLeafSubtreeFiltering(StringList appInstance, StringListBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.STRING1.getLeafIndex())) {
            if (appInstance.string1() == null || !(string1().equals(appInstance.string1()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.string1(appInstance.string1());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.STRING1.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.string1(appInstance.string1());
        }

        if (getValueLeafFlags().get(LeafIdentifier.STRING2.getLeafIndex())) {
            if (appInstance.string2() == null || !(string2().equals(appInstance.string2()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.string2(appInstance.string2());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.STRING2.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.string2(appInstance.string2());
        }

        if (getValueLeafFlags().get(LeafIdentifier.STRING3.getLeafIndex())) {
            if (appInstance.string3() == null || !(string3().equals(appInstance.string3()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.string3(appInstance.string3());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.STRING3.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.string3(appInstance.string3());
        }

        return true;
    }


    /**
     * Creates an instance of defaultStringList.
     */
    protected DefaultStringList() {
    }

    /**
     * Returns the attribute StringListBuilder.
     *
     * @return value of StringListBuilder
     */
    public static StringListBuilder builder() {
        return new StringListBuilder();
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
     * Represents the builder implementation of stringList.
     */
    public static class StringListBuilder implements StringList.StringListBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected String string1;
        protected String string2;
        protected String string3;
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
        public StringListBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public String string1() {
            return string1;
        }

        @Override
        public String string2() {
            return string2;
        }

        @Override
        public String string3() {
            return string3;
        }

        @Override
        public StringListBuilder string1(String string1) {
            getValueLeafFlags().set(LeafIdentifier.STRING1.getLeafIndex());
            this.string1 = string1;
            return this;
        }

        @Override
        public StringListBuilder string2(String string2) {
            getValueLeafFlags().set(LeafIdentifier.STRING2.getLeafIndex());
            this.string2 = string2;
            return this;
        }

        @Override
        public StringListBuilder string3(String string3) {
            getValueLeafFlags().set(LeafIdentifier.STRING3.getLeafIndex());
            this.string3 = string3;
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
        public StringListBuilder selectLeaf(LeafIdentifier leaf) {
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
        public StringList build() {
            return new DefaultStringList(this);
        }

        /**
         * Creates an instance of stringListBuilder.
         */
        public StringListBuilder() {
        }
    }
}
