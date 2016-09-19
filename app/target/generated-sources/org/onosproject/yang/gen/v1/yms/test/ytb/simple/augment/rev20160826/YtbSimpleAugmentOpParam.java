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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.Cont1;

/**
 * Represents the implementation of ytbSimpleAugment.
 */
public class YtbSimpleAugmentOpParam implements YtbSimpleAugment {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Cont1 cont1;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public Cont1 cont1() {
        return cont1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, cont1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbSimpleAugmentOpParam) {
            YtbSimpleAugmentOpParam other = (YtbSimpleAugmentOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(cont1, other.cont1);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("cont1", cont1)
            .toString();
    }

    /**
     * Creates an instance of ytbSimpleAugment.
     *
     * @param builderObject builder object of ytbSimpleAugment
     */
    protected YtbSimpleAugmentOpParam(YtbSimpleAugmentBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.cont1 = builderObject.cont1();
    }

    @Override
    public YtbSimpleAugment processSubtreeFiltering(YtbSimpleAugment appInstance, boolean isSelectAllSchemaChild) {
        YtbSimpleAugmentBuilder subTreeFilteringResultBuilder = new YtbSimpleAugmentBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbSimpleAugment appInstance, YtbSimpleAugmentBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cont1()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont1() != null) {
                Cont1 result = cont1.processSubtreeFiltering(appInstance.cont1(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont1(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbSimpleAugmentOpParam.
     */
    protected YtbSimpleAugmentOpParam() {
    }

    /**
     * Returns the attribute YtbSimpleAugmentBuilder.
     *
     * @return value of YtbSimpleAugmentBuilder
     */
    public static YtbSimpleAugmentBuilder builder() {
        return new YtbSimpleAugmentBuilder();
    }


    /**
     * Represents the builder implementation of ytbSimpleAugment.
     */
    public static class YtbSimpleAugmentBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Cont1 cont1;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public Cont1 cont1() {
            return cont1;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YtbSimpleAugmentBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of cont1.
         *
         * @param cont1 value of cont1
         * @return builder object of cont1
         */
        public YtbSimpleAugmentBuilder cont1(Cont1 cont1) {
            this.cont1 = cont1;
            return this;
        }

        public YtbSimpleAugment build() {
            return new YtbSimpleAugmentOpParam(this);
        }

        /**
         * Creates an instance of ytbSimpleAugmentBuilder.
         */
        public YtbSimpleAugmentBuilder() {
        }
    }
}
