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

package org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageinput;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of yangAutoPrefixFinal.
 */
public class DefaultYangAutoPrefixFinal implements YangAutoPrefixFinal {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected List<Integer> value;
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
    public List<Integer> value() {
        return value;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultYangAutoPrefixFinal) {
            DefaultYangAutoPrefixFinal other = (DefaultYangAutoPrefixFinal) obj;
            return
                 Objects.equals(value, other.value) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("value", value)
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
     * Creates an instance of yangAutoPrefixFinal.
     *
     * @param builderObject builder object of yangAutoPrefixFinal
     */
    protected DefaultYangAutoPrefixFinal(YangAutoPrefixFinalBuilder builderObject) {
        this.value = builderObject.value();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public YangAutoPrefixFinal processSubtreeFiltering(YangAutoPrefixFinal appInstance, boolean
            isSelectAllSchemaChild) {
        YangAutoPrefixFinalBuilder subTreeFilteringResultBuilder = new YangAutoPrefixFinalBuilder();
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
    private boolean processLeafListSubTreeFiltering(YangAutoPrefixFinal appInstance, YangAutoPrefixFinalBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Integer value : appInstance.value()) {
                subTreeFilteringResultBuilder.addToValue(value);
            }
        } else if (value() != null) {
            if (!value().isEmpty()) {
                if (appInstance.value() == null || appInstance.value().isEmpty()) {
                    return false;
                }
                for (Integer value : value()) {
                    boolean flag = false;
                    for (Integer value2 : appInstance.value()) {
                        if (value.equals(value2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToValue(value2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.value() != null && !appInstance.value().isEmpty()) {
                    for (Integer value : appInstance.value()) {
                        subTreeFilteringResultBuilder.addToValue(value);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultYangAutoPrefixFinal.
     */
    protected DefaultYangAutoPrefixFinal() {
    }

    /**
     * Returns the attribute YangAutoPrefixFinalBuilder.
     *
     * @return value of YangAutoPrefixFinalBuilder
     */
    public static YangAutoPrefixFinalBuilder builder() {
        return new YangAutoPrefixFinalBuilder();
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
     * Represents the builder implementation of yangAutoPrefixFinal.
     */
    public static class YangAutoPrefixFinalBuilder implements YangAutoPrefixFinal.YangAutoPrefixFinalBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected List<Integer> value;
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
        public List<Integer> value() {
            return value;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public YangAutoPrefixFinalBuilder value(List<Integer> value) {
            this.value = value;
            return this;
        }

        @Override
        public YangAutoPrefixFinalBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public YangAutoPrefixFinalBuilder addToValue(Integer value) {
            if (value() == null) {
                value(new ArrayList<>());
            }
            value().add(value);
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
        public YangAutoPrefixFinalBuilder selectLeaf(LeafIdentifier leaf) {
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
        public YangAutoPrefixFinal build() {
            return new DefaultYangAutoPrefixFinal(this);
        }

        /**
         * Creates an instance of yangAutoPrefixFinalBuilder.
         */
        public YangAutoPrefixFinalBuilder() {
        }
    }
}
