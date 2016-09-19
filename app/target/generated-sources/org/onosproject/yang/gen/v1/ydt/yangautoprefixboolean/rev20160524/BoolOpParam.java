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

package org.onosproject.yang.gen.v1.ydt.yangautoprefixboolean.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.yangautoprefixboolean.rev20160524.bool.BooleanList;

/**
 * Represents the implementation of bool.
 */
public class BoolOpParam implements Bool {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<BooleanList> booleanList;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public List<BooleanList> booleanList() {
        return booleanList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, booleanList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BoolOpParam) {
            BoolOpParam other = (BoolOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(booleanList, other.booleanList);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("booleanList", booleanList)
            .toString();
    }

    /**
     * Creates an instance of bool.
     *
     * @param builderObject builder object of bool
     */
    protected BoolOpParam(BoolBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.booleanList = builderObject.booleanList();
    }

    @Override
    public Bool processSubtreeFiltering(Bool appInstance, boolean isSelectAllSchemaChild) {
        BoolBuilder subTreeFilteringResultBuilder = new BoolBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Bool appInstance, BoolBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (BooleanList booleanList : appInstance.booleanList()) {
                subTreeFilteringResultBuilder.addToBooleanList(booleanList);
            }
        } else if (booleanList() != null) {
            isAnySelectOrContainmentNode = true;
            if (!booleanList().isEmpty()) {
                if (appInstance.booleanList() != null && !appInstance.booleanList().isEmpty()) {
                    for (BooleanList booleanList : booleanList()) {
                        for (BooleanList booleanList2 : appInstance.booleanList()) {
                            BooleanList result = booleanList.processSubtreeFiltering(booleanList2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToBooleanList(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.booleanList() != null && !appInstance.booleanList().isEmpty()) {
                    for (BooleanList booleanList : appInstance.booleanList()) {
                        subTreeFilteringResultBuilder.addToBooleanList(booleanList);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of boolOpParam.
     */
    protected BoolOpParam() {
    }

    /**
     * Returns the attribute BoolBuilder.
     *
     * @return value of BoolBuilder
     */
    public static BoolBuilder builder() {
        return new BoolBuilder();
    }


    /**
     * Represents the builder implementation of bool.
     */
    public static class BoolBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<BooleanList> booleanList;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public List<BooleanList> booleanList() {
            return booleanList;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public BoolBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of booleanList.
         *
         * @param booleanList list of booleanList
         * @return builder object of booleanList
         */
        public BoolBuilder booleanList(List<BooleanList> booleanList) {
            this.booleanList = booleanList;
            return this;
        }
        public BoolBuilder addToBooleanList(BooleanList value) {
            if (booleanList() == null) {
                booleanList(new ArrayList<>());
            }
            booleanList().add(value);
            return this;
        }

        public Bool build() {
            return new BoolOpParam(this);
        }

        /**
         * Creates an instance of boolBuilder.
         */
        public BoolBuilder() {
        }
    }
}
