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

package org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput
            .activatesoftwareimage.output.augmentedrpcoutput.Selection;

/**
 * Represents the implementation of valueIn.
 */
public class DefaultValueIn implements ValueIn {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn> valueIn;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection
            .valuein.ValueIn> valueIn() {
        return valueIn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, valueIn);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultValueIn) {
            DefaultValueIn other = (DefaultValueIn) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(valueIn, other.valueIn);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("valueIn", valueIn)
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
     * Creates an instance of valueIn.
     *
     * @param builderObject builder object of valueIn
     */
    protected DefaultValueIn(ValueInBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueIn = builderObject.valueIn();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Selection processSubtreeFiltering(Selection instance, boolean isSelectAllSchemaChild) {
        ValueInBuilder subTreeFilteringResultBuilder = new ValueInBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        ValueIn appInstance = (ValueIn) instance;
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
    private boolean processChildNodesSubTreeFiltering(Selection instance, ValueInBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        ValueIn appInstance = (ValueIn) instance;
        if (isSelectAllSchemaChild) {
            for (org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn valueIn : appInstance.valueIn()) {
                subTreeFilteringResultBuilder.addToValueIn(valueIn);
            }
        } else if (valueIn() != null) {
            isAnySelectOrContainmentNode = true;
            if (!valueIn().isEmpty()) {
                if (appInstance.valueIn() != null && !appInstance.valueIn().isEmpty()) {
                    for (org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection
            .valuein.ValueIn valueIn : valueIn()) {
                        for (org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input
            .rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn valueIn2 : appInstance.valueIn()) {
                            org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn result = valueIn
            .processSubtreeFiltering(valueIn2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToValueIn(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.valueIn() != null && !appInstance.valueIn().isEmpty()) {
                    for (org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn valueIn : appInstance.valueIn()) {
                        subTreeFilteringResultBuilder.addToValueIn(valueIn);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultValueIn.
     */
    protected DefaultValueIn() {
    }

    /**
     * Returns the attribute ValueInBuilder.
     *
     * @return value of ValueInBuilder
     */
    public static ValueInBuilder builder() {
        return new ValueInBuilder();
    }


    /**
     * Represents the builder implementation of valueIn.
     */
    public static class ValueInBuilder implements ValueIn.ValueInBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn> valueIn;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection
            .valuein.ValueIn> valueIn() {
            return valueIn;
        }

        @Override
        public ValueInBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public ValueInBuilder valueIn(List<org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc
            .input.rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection
            .valuein.ValueIn> valueIn) {
            this.valueIn = valueIn;
            return this;
        }

        @Override
        public ValueInBuilder addToValueIn(org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc
            .input.rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection
            .valuein.ValueIn value) {
            if (valueIn() == null) {
                valueIn(new ArrayList<>());
            }
            valueIn().add(value);
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
        public ValueIn build() {
            return new DefaultValueIn(this);
        }

        /**
         * Creates an instance of valueInBuilder.
         */
        public ValueInBuilder() {
        }
    }
}
