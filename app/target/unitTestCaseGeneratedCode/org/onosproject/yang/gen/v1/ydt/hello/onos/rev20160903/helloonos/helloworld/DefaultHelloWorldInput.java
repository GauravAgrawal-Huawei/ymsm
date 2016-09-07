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

package org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903.helloonos.helloworld;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903.helloonos.helloworld.helloworldinput.StringList;

/**
 * Represents the implementation of helloWorldInput.
 */
public class DefaultHelloWorldInput implements HelloWorldInput {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected String name;
    protected String surName;
    protected String inputDefault;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<StringList> stringList;
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
    public String name() {
        return name;
    }

    @Override
    public String surName() {
        return surName;
    }

    @Override
    public String inputDefault() {
        return inputDefault;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<StringList> stringList() {
        return stringList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surName, inputDefault, onosYangNodeOperationType, stringList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultHelloWorldInput) {
            DefaultHelloWorldInput other = (DefaultHelloWorldInput) obj;
            return
                 Objects.equals(name, other.name) &&
                 Objects.equals(surName, other.surName) &&
                 Objects.equals(inputDefault, other.inputDefault) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(stringList, other.stringList);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("name", name)
            .add("surName", surName)
            .add("inputDefault", inputDefault)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("stringList", stringList)
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
     * Creates an instance of helloWorldInput.
     *
     * @param builderObject builder object of helloWorldInput
     */
    protected DefaultHelloWorldInput(HelloWorldInputBuilder builderObject) {
        this.name = builderObject.name();
        this.surName = builderObject.surName();
        this.inputDefault = builderObject.inputDefault();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.stringList = builderObject.stringList();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public HelloWorldInput processSubtreeFiltering(HelloWorldInput appInstance, boolean isSelectAllSchemaChild) {
        HelloWorldInputBuilder subTreeFilteringResultBuilder = new HelloWorldInputBuilder();
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
    private boolean processLeafSubtreeFiltering(HelloWorldInput appInstance, HelloWorldInputBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.NAME.getLeafIndex())) {
            if (appInstance.name() == null || !(name().equals(appInstance.name()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.name(appInstance.name());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.NAME.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.name(appInstance.name());
        }

        if (getValueLeafFlags().get(LeafIdentifier.SURNAME.getLeafIndex())) {
            if (appInstance.surName() == null || !(surName().equals(appInstance.surName()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.surName(appInstance.surName());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.SURNAME.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.surName(appInstance.surName());
        }

        if (getValueLeafFlags().get(LeafIdentifier.INPUTDEFAULT.getLeafIndex())) {
            if (appInstance.inputDefault() == null || !(inputDefault().equals(appInstance.inputDefault()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.inputDefault(appInstance.inputDefault());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.INPUTDEFAULT.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.inputDefault(appInstance.inputDefault());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(HelloWorldInput appInstance, HelloWorldInputBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (StringList stringList : appInstance.stringList()) {
                subTreeFilteringResultBuilder.addToStringList(stringList);
            }
        } else if (stringList() != null) {
            isAnySelectOrContainmentNode = true;
            if (!stringList().isEmpty()) {
                if (appInstance.stringList() != null && !appInstance.stringList().isEmpty()) {
                    for (StringList stringList : stringList()) {
                        for (StringList stringList2 : appInstance.stringList()) {
                            StringList result = stringList.processSubtreeFiltering(stringList2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToStringList(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.stringList() != null && !appInstance.stringList().isEmpty()) {
                    for (StringList stringList : appInstance.stringList()) {
                        subTreeFilteringResultBuilder.addToStringList(stringList);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultHelloWorldInput.
     */
    protected DefaultHelloWorldInput() {
    }

    /**
     * Returns the attribute HelloWorldInputBuilder.
     *
     * @return value of HelloWorldInputBuilder
     */
    public static HelloWorldInputBuilder builder() {
        return new HelloWorldInputBuilder();
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
     * Represents the builder implementation of helloWorldInput.
     */
    public static class HelloWorldInputBuilder implements HelloWorldInput.HelloWorldInputBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected String name;
        protected String surName;
        protected String inputDefault;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<StringList> stringList;
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
        public String name() {
            return name;
        }

        @Override
        public String surName() {
            return surName;
        }

        @Override
        public String inputDefault() {
            return inputDefault;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<StringList> stringList() {
            return stringList;
        }

        @Override
        public HelloWorldInputBuilder name(String name) {
            getValueLeafFlags().set(LeafIdentifier.NAME.getLeafIndex());
            this.name = name;
            return this;
        }

        @Override
        public HelloWorldInputBuilder surName(String surName) {
            getValueLeafFlags().set(LeafIdentifier.SURNAME.getLeafIndex());
            this.surName = surName;
            return this;
        }

        @Override
        public HelloWorldInputBuilder inputDefault(String inputDefault) {
            getValueLeafFlags().set(LeafIdentifier.INPUTDEFAULT.getLeafIndex());
            this.inputDefault = inputDefault;
            return this;
        }

        @Override
        public HelloWorldInputBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public HelloWorldInputBuilder stringList(List<StringList> stringList) {
            this.stringList = stringList;
            return this;
        }

        @Override
        public HelloWorldInputBuilder addToStringList(StringList value) {
            if (stringList() == null) {
                stringList(new ArrayList<>());
            }
            stringList().add(value);
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
        public HelloWorldInputBuilder selectLeaf(LeafIdentifier leaf) {
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
        public HelloWorldInput build() {
            return new DefaultHelloWorldInput(this);
        }

        /**
         * Creates an instance of helloWorldInputBuilder.
         */
        public HelloWorldInputBuilder() {
        }
    }
}
