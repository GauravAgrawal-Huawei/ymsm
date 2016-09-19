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

package org.onosproject.yang.gen.v1.ydt.bit.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.bit.rev20160524.bit.BitList;

/**
 * Represents the implementation of bit.
 */
public class BitOpParam implements Bit {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<BitList> bitList;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public List<BitList> bitList() {
        return bitList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, bitList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BitOpParam) {
            BitOpParam other = (BitOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(bitList, other.bitList);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("bitList", bitList)
            .toString();
    }

    /**
     * Creates an instance of bit.
     *
     * @param builderObject builder object of bit
     */
    protected BitOpParam(BitBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.bitList = builderObject.bitList();
    }

    @Override
    public Bit processSubtreeFiltering(Bit appInstance, boolean isSelectAllSchemaChild) {
        BitBuilder subTreeFilteringResultBuilder = new BitBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Bit appInstance, BitBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (BitList bitList : appInstance.bitList()) {
                subTreeFilteringResultBuilder.addToBitList(bitList);
            }
        } else if (bitList() != null) {
            isAnySelectOrContainmentNode = true;
            if (!bitList().isEmpty()) {
                if (appInstance.bitList() != null && !appInstance.bitList().isEmpty()) {
                    for (BitList bitList : bitList()) {
                        for (BitList bitList2 : appInstance.bitList()) {
                            BitList result = bitList.processSubtreeFiltering(bitList2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToBitList(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.bitList() != null && !appInstance.bitList().isEmpty()) {
                    for (BitList bitList : appInstance.bitList()) {
                        subTreeFilteringResultBuilder.addToBitList(bitList);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of bitOpParam.
     */
    protected BitOpParam() {
    }

    /**
     * Returns the attribute BitBuilder.
     *
     * @return value of BitBuilder
     */
    public static BitBuilder builder() {
        return new BitBuilder();
    }


    /**
     * Represents the builder implementation of bit.
     */
    public static class BitBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<BitList> bitList;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public List<BitList> bitList() {
            return bitList;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public BitBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of bitList.
         *
         * @param bitList list of bitList
         * @return builder object of bitList
         */
        public BitBuilder bitList(List<BitList> bitList) {
            this.bitList = bitList;
            return this;
        }
        public BitBuilder addToBitList(BitList value) {
            if (bitList() == null) {
                bitList(new ArrayList<>());
            }
            bitList().add(value);
            return this;
        }

        public Bit build() {
            return new BitOpParam(this);
        }

        /**
         * Creates an instance of bitBuilder.
         */
        public BitBuilder() {
        }
    }
}
