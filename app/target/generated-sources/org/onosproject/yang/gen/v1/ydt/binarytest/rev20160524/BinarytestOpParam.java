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

package org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524.binarytest.BinaryList;

/**
 * Represents the implementation of binarytest.
 */
public class BinarytestOpParam implements Binarytest {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<BinaryList> binaryList;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public List<BinaryList> binaryList() {
        return binaryList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, binaryList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BinarytestOpParam) {
            BinarytestOpParam other = (BinarytestOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(binaryList, other.binaryList);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("binaryList", binaryList)
            .toString();
    }

    /**
     * Creates an instance of binarytest.
     *
     * @param builderObject builder object of binarytest
     */
    protected BinarytestOpParam(BinarytestBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.binaryList = builderObject.binaryList();
    }

    @Override
    public Binarytest processSubtreeFiltering(Binarytest appInstance, boolean isSelectAllSchemaChild) {
        BinarytestBuilder subTreeFilteringResultBuilder = new BinarytestBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Binarytest appInstance, BinarytestBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (BinaryList binaryList : appInstance.binaryList()) {
                subTreeFilteringResultBuilder.addToBinaryList(binaryList);
            }
        } else if (binaryList() != null) {
            isAnySelectOrContainmentNode = true;
            if (!binaryList().isEmpty()) {
                if (appInstance.binaryList() != null && !appInstance.binaryList().isEmpty()) {
                    for (BinaryList binaryList : binaryList()) {
                        for (BinaryList binaryList2 : appInstance.binaryList()) {
                            BinaryList result = binaryList.processSubtreeFiltering(binaryList2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToBinaryList(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.binaryList() != null && !appInstance.binaryList().isEmpty()) {
                    for (BinaryList binaryList : appInstance.binaryList()) {
                        subTreeFilteringResultBuilder.addToBinaryList(binaryList);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of binarytestOpParam.
     */
    protected BinarytestOpParam() {
    }

    /**
     * Returns the attribute BinarytestBuilder.
     *
     * @return value of BinarytestBuilder
     */
    public static BinarytestBuilder builder() {
        return new BinarytestBuilder();
    }


    /**
     * Represents the builder implementation of binarytest.
     */
    public static class BinarytestBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<BinaryList> binaryList;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public List<BinaryList> binaryList() {
            return binaryList;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public BinarytestBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of binaryList.
         *
         * @param binaryList list of binaryList
         * @return builder object of binaryList
         */
        public BinarytestBuilder binaryList(List<BinaryList> binaryList) {
            this.binaryList = binaryList;
            return this;
        }
        public BinarytestBuilder addToBinaryList(BinaryList value) {
            if (binaryList() == null) {
                binaryList(new ArrayList<>());
            }
            binaryList().add(value);
            return this;
        }

        public Binarytest build() {
            return new BinarytestOpParam(this);
        }

        /**
         * Creates an instance of binarytestBuilder.
         */
        public BinarytestBuilder() {
        }
    }
}
