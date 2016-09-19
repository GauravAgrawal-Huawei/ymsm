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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase
            .ytbfood.YtbSnack;

/**
 * Represents the implementation of ytbFood.
 */
public class DefaultYtbFood implements YtbFood {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected YtbSnack ytbSnack;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public YtbSnack ytbSnack() {
        return ytbSnack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, ytbSnack);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultYtbFood) {
            DefaultYtbFood other = (DefaultYtbFood) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(ytbSnack, other.ytbSnack);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("ytbSnack", ytbSnack)
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
     * Creates an instance of ytbFood.
     *
     * @param builderObject builder object of ytbFood
     */
    protected DefaultYtbFood(YtbFoodBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.ytbSnack = builderObject.ytbSnack();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public YtbFood processSubtreeFiltering(YtbFood appInstance, boolean isSelectAllSchemaChild) {
        YtbFoodBuilder subTreeFilteringResultBuilder = new YtbFoodBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbFood appInstance, YtbFoodBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (ytbSnack()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.ytbSnack() != null) {
                YtbSnack result = ytbSnack.processSubtreeFiltering(appInstance.ytbSnack(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.ytbSnack(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultYtbFood.
     */
    protected DefaultYtbFood() {
    }

    /**
     * Returns the attribute YtbFoodBuilder.
     *
     * @return value of YtbFoodBuilder
     */
    public static YtbFoodBuilder builder() {
        return new YtbFoodBuilder();
    }


    /**
     * Represents the builder implementation of ytbFood.
     */
    public static class YtbFoodBuilder implements YtbFood.YtbFoodBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected YtbSnack ytbSnack;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public YtbSnack ytbSnack() {
            return ytbSnack;
        }

        @Override
        public YtbFoodBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public YtbFoodBuilder ytbSnack(YtbSnack ytbSnack) {
            this.ytbSnack = ytbSnack;
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
        public YtbFood build() {
            return new DefaultYtbFood(this);
        }

        /**
         * Creates an instance of ytbFoodBuilder.
         */
        public YtbFoodBuilder() {
        }
    }
}
