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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4.augmentedcont4.Cont6;

/**
 * Represents the implementation of augmentedCont4.
 */
public class DefaultAugmentedCont4 implements AugmentedCont4 {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Cont6 cont6;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public Cont6 cont6() {
        return cont6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, cont6);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultAugmentedCont4) {
            DefaultAugmentedCont4 other = (DefaultAugmentedCont4) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(cont6, other.cont6);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("cont6", cont6)
            .toString();
    }

    /**
     * Creates an instance of augmentedCont4.
     *
     * @param builderObject builder object of augmentedCont4
     */
    protected DefaultAugmentedCont4(AugmentedCont4Builder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.cont6 = builderObject.cont6();
    }

    @Override
    public AugmentedCont4 processSubtreeFiltering(AugmentedCont4 appInstance, boolean isSelectAllSchemaChild) {
        AugmentedCont4Builder subTreeFilteringResultBuilder = new AugmentedCont4Builder();
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
    private boolean processChildNodesSubTreeFiltering(AugmentedCont4 appInstance, AugmentedCont4Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cont6()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont6() != null) {
                Cont6 result = cont6.processSubtreeFiltering(appInstance.cont6(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont6(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultAugmentedCont4.
     */
    protected DefaultAugmentedCont4() {
    }

    /**
     * Returns the attribute AugmentedCont4Builder.
     *
     * @return value of AugmentedCont4Builder
     */
    public static AugmentedCont4Builder builder() {
        return new AugmentedCont4Builder();
    }


    /**
     * Represents the builder implementation of augmentedCont4.
     */
    public static class AugmentedCont4Builder implements AugmentedCont4.AugmentedCont4Builder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Cont6 cont6;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public Cont6 cont6() {
            return cont6;
        }

        @Override
        public AugmentedCont4Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public AugmentedCont4Builder cont6(Cont6 cont6) {
            this.cont6 = cont6;
            return this;
        }
        @Override
        public AugmentedCont4 build() {
            return new DefaultAugmentedCont4(this);
        }

        /**
         * Creates an instance of augmentedCont4Builder.
         */
        public AugmentedCont4Builder() {
        }
    }
}
