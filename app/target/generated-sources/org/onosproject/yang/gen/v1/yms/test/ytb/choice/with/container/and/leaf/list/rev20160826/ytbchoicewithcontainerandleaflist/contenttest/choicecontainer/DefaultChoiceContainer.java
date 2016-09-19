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

package org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.contenttest.choicecontainer;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.contenttest.choicecontainer.choicecontainer.Predict;

/**
 * Represents the implementation of choiceContainer.
 */
public class DefaultChoiceContainer implements ChoiceContainer {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<Predict> predict;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<Predict> predict() {
        return predict;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, predict);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultChoiceContainer) {
            DefaultChoiceContainer other = (DefaultChoiceContainer) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(predict, other.predict);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("predict", predict)
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
     * Creates an instance of choiceContainer.
     *
     * @param builderObject builder object of choiceContainer
     */
    protected DefaultChoiceContainer(ChoiceContainerBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.predict = builderObject.predict();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public ChoiceContainer processSubtreeFiltering(ChoiceContainer appInstance, boolean isSelectAllSchemaChild) {
        ChoiceContainerBuilder subTreeFilteringResultBuilder = new ChoiceContainerBuilder();
        Boolean isAnySelectOrContainmentNode = false;
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
    private boolean processChildNodesSubTreeFiltering(ChoiceContainer appInstance, ChoiceContainerBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Predict predict : appInstance.predict()) {
                subTreeFilteringResultBuilder.addToPredict(predict);
            }
        } else if (predict() != null) {
            isAnySelectOrContainmentNode = true;
            if (!predict().isEmpty()) {
                if (appInstance.predict() != null && !appInstance.predict().isEmpty()) {
                    for (Predict predict : predict()) {
                        for (Predict predict2 : appInstance.predict()) {
                            Predict result = predict.processSubtreeFiltering(predict2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToPredict(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.predict() != null && !appInstance.predict().isEmpty()) {
                    for (Predict predict : appInstance.predict()) {
                        subTreeFilteringResultBuilder.addToPredict(predict);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultChoiceContainer.
     */
    protected DefaultChoiceContainer() {
    }

    /**
     * Returns the attribute ChoiceContainerBuilder.
     *
     * @return value of ChoiceContainerBuilder
     */
    public static ChoiceContainerBuilder builder() {
        return new ChoiceContainerBuilder();
    }


    /**
     * Represents the builder implementation of choiceContainer.
     */
    public static class ChoiceContainerBuilder implements ChoiceContainer.ChoiceContainerBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<Predict> predict;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<Predict> predict() {
            return predict;
        }

        @Override
        public ChoiceContainerBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public ChoiceContainerBuilder predict(List<Predict> predict) {
            this.predict = predict;
            return this;
        }

        @Override
        public ChoiceContainerBuilder addToPredict(Predict value) {
            if (predict() == null) {
                predict(new ArrayList<>());
            }
            predict().add(value);
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
        public ChoiceContainer build() {
            return new DefaultChoiceContainer(this);
        }

        /**
         * Creates an instance of choiceContainerBuilder.
         */
        public ChoiceContainerBuilder() {
        }
    }
}
