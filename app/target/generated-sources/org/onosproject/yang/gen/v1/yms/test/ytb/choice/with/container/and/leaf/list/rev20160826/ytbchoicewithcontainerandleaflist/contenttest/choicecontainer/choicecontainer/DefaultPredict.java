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
            .ytbchoicewithcontainerandleaflist.contenttest.choicecontainer.choicecontainer;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.contenttest.choicecontainer.choicecontainer.predict.Reproduce;

/**
 * Represents the implementation of predict.
 */
public class DefaultPredict implements Predict {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Reproduce reproduce;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public Reproduce reproduce() {
        return reproduce;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, reproduce);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultPredict) {
            DefaultPredict other = (DefaultPredict) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(reproduce, other.reproduce);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("reproduce", reproduce)
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
     * Creates an instance of predict.
     *
     * @param builderObject builder object of predict
     */
    protected DefaultPredict(PredictBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.reproduce = builderObject.reproduce();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Predict processSubtreeFiltering(Predict appInstance, boolean isSelectAllSchemaChild) {
        PredictBuilder subTreeFilteringResultBuilder = new PredictBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Predict appInstance, PredictBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (reproduce()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.reproduce() != null) {
                Reproduce result = reproduce.processSubtreeFiltering(appInstance.reproduce(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.reproduce(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultPredict.
     */
    protected DefaultPredict() {
    }

    /**
     * Returns the attribute PredictBuilder.
     *
     * @return value of PredictBuilder
     */
    public static PredictBuilder builder() {
        return new PredictBuilder();
    }


    /**
     * Represents the builder implementation of predict.
     */
    public static class PredictBuilder implements Predict.PredictBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Reproduce reproduce;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public Reproduce reproduce() {
            return reproduce;
        }

        @Override
        public PredictBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public PredictBuilder reproduce(Reproduce reproduce) {
            this.reproduce = reproduce;
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
        public Predict build() {
            return new DefaultPredict(this);
        }

        /**
         * Creates an instance of predictBuilder.
         */
        public PredictBuilder() {
        }
    }
}
