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

package org.onosproject.yang.gen.v1.ydt.food.rev20160624;

import com.google.common.base.MoreObjects;
import java.util.Objects;

/**
 * Represents the implementation of food.
 */
public class FoodOpParam implements Food {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food() {
        return food;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, food);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FoodOpParam) {
            FoodOpParam other = (FoodOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(food, other.food);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("food", food)
            .toString();
    }

    /**
     * Creates an instance of food.
     *
     * @param builderObject builder object of food
     */
    protected FoodOpParam(FoodBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.food = builderObject.food();
    }

    @Override
    public Food processSubtreeFiltering(Food appInstance, boolean isSelectAllSchemaChild) {
        FoodBuilder subTreeFilteringResultBuilder = new FoodBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processChildNodesSubTreeFiltering(Food appInstance, FoodBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (food()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.food() != null) {
                org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food result = food
            .processSubtreeFiltering(appInstance.food(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.food(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of foodOpParam.
     */
    protected FoodOpParam() {
    }

    /**
     * Returns the attribute FoodBuilder.
     *
     * @return value of FoodBuilder
     */
    public static FoodBuilder builder() {
        return new FoodBuilder();
    }


    /**
     * Represents the builder implementation of food.
     */
    public static class FoodBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food() {
            return food;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public FoodBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of food.
         *
         * @param food value of food
         * @return builder object of food
         */
        public FoodBuilder food(org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food) {
            this.food = food;
            return this;
        }

        public Food build() {
            return new FoodOpParam(this);
        }

        /**
         * Creates an instance of foodBuilder.
         */
        public FoodBuilder() {
        }
    }
}
