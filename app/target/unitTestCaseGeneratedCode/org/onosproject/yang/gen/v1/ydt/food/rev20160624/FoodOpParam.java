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

    protected org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food;

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

    public org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food() {
        return food;
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
        return Objects.hash(food);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FoodOpParam) {
            FoodOpParam other = (FoodOpParam) obj;
            return
                 Objects.equals(food, other.food);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("food", food)
            .toString();
    }

    /**
     * Creates an instance of food.
     *
     * @param builderObject builder object of food
     */
    protected FoodOpParam(FoodBuilder builderObject) {
        this.food = builderObject.food();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
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

        protected org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food;

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        public org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food() {
            return food;
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
        public FoodBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
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
