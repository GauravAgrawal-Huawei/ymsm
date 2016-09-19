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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase
            .YtbFood;

/**
 * Represents the implementation of ytbSimpleChoiceCase.
 */
public class YtbSimpleChoiceCaseOpParam implements YtbSimpleChoiceCase {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected YtbFood ytbFood;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public YtbFood ytbFood() {
        return ytbFood;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, ytbFood);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbSimpleChoiceCaseOpParam) {
            YtbSimpleChoiceCaseOpParam other = (YtbSimpleChoiceCaseOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(ytbFood, other.ytbFood);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("ytbFood", ytbFood)
            .toString();
    }

    /**
     * Creates an instance of ytbSimpleChoiceCase.
     *
     * @param builderObject builder object of ytbSimpleChoiceCase
     */
    protected YtbSimpleChoiceCaseOpParam(YtbSimpleChoiceCaseBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.ytbFood = builderObject.ytbFood();
    }

    @Override
    public YtbSimpleChoiceCase processSubtreeFiltering(YtbSimpleChoiceCase appInstance, boolean
            isSelectAllSchemaChild) {
        YtbSimpleChoiceCaseBuilder subTreeFilteringResultBuilder = new YtbSimpleChoiceCaseBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbSimpleChoiceCase appInstance, YtbSimpleChoiceCaseBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (ytbFood()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.ytbFood() != null) {
                YtbFood result = ytbFood.processSubtreeFiltering(appInstance.ytbFood(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.ytbFood(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbSimpleChoiceCaseOpParam.
     */
    protected YtbSimpleChoiceCaseOpParam() {
    }

    /**
     * Returns the attribute YtbSimpleChoiceCaseBuilder.
     *
     * @return value of YtbSimpleChoiceCaseBuilder
     */
    public static YtbSimpleChoiceCaseBuilder builder() {
        return new YtbSimpleChoiceCaseBuilder();
    }


    /**
     * Represents the builder implementation of ytbSimpleChoiceCase.
     */
    public static class YtbSimpleChoiceCaseBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected YtbFood ytbFood;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public YtbFood ytbFood() {
            return ytbFood;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YtbSimpleChoiceCaseBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of ytbFood.
         *
         * @param ytbFood value of ytbFood
         * @return builder object of ytbFood
         */
        public YtbSimpleChoiceCaseBuilder ytbFood(YtbFood ytbFood) {
            this.ytbFood = ytbFood;
            return this;
        }

        public YtbSimpleChoiceCase build() {
            return new YtbSimpleChoiceCaseOpParam(this);
        }

        /**
         * Creates an instance of ytbSimpleChoiceCaseBuilder.
         */
        public YtbSimpleChoiceCaseBuilder() {
        }
    }
}
