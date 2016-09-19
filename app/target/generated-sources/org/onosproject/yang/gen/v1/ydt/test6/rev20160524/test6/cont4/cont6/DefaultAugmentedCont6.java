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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4.cont6;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4.cont6.augmentedcont6.Cont7;

/**
 * Represents the implementation of augmentedCont6.
 */
public class DefaultAugmentedCont6 implements AugmentedCont6 {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Cont7 cont7;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public Cont7 cont7() {
        return cont7;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, cont7);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultAugmentedCont6) {
            DefaultAugmentedCont6 other = (DefaultAugmentedCont6) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(cont7, other.cont7);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("cont7", cont7)
            .toString();
    }

    /**
     * Creates an instance of augmentedCont6.
     *
     * @param builderObject builder object of augmentedCont6
     */
    protected DefaultAugmentedCont6(AugmentedCont6Builder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.cont7 = builderObject.cont7();
    }

    @Override
    public AugmentedCont6 processSubtreeFiltering(AugmentedCont6 appInstance, boolean isSelectAllSchemaChild) {
        AugmentedCont6Builder subTreeFilteringResultBuilder = new AugmentedCont6Builder();
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
    private boolean processChildNodesSubTreeFiltering(AugmentedCont6 appInstance, AugmentedCont6Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cont7()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont7() != null) {
                Cont7 result = cont7.processSubtreeFiltering(appInstance.cont7(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont7(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultAugmentedCont6.
     */
    protected DefaultAugmentedCont6() {
    }

    /**
     * Returns the attribute AugmentedCont6Builder.
     *
     * @return value of AugmentedCont6Builder
     */
    public static AugmentedCont6Builder builder() {
        return new AugmentedCont6Builder();
    }


    /**
     * Represents the builder implementation of augmentedCont6.
     */
    public static class AugmentedCont6Builder implements AugmentedCont6.AugmentedCont6Builder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Cont7 cont7;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public Cont7 cont7() {
            return cont7;
        }

        @Override
        public AugmentedCont6Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public AugmentedCont6Builder cont7(Cont7 cont7) {
            this.cont7 = cont7;
            return this;
        }
        @Override
        public AugmentedCont6 build() {
            return new DefaultAugmentedCont6(this);
        }

        /**
         * Creates an instance of augmentedCont6Builder.
         */
        public AugmentedCont6Builder() {
        }
    }
}
