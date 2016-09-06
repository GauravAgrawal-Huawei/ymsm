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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaflist.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;

/**
 * Represents the implementation of ytbModuleWithLeafList.
 */
public class YtbModuleWithLeafListOpParam implements YtbModuleWithLeafList {

    protected List<Long> time = new ArrayList<>();

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

    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    public List<Long> time() {
        return time;
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
        return Objects.hash(time);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbModuleWithLeafListOpParam) {
            YtbModuleWithLeafListOpParam other = (YtbModuleWithLeafListOpParam) obj;
            return
                 Objects.equals(time, other.time);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("time", time)
            .toString();
    }

    /**
     * Creates an instance of ytbModuleWithLeafList.
     *
     * @param builderObject builder object of ytbModuleWithLeafList
     */
    protected YtbModuleWithLeafListOpParam(YtbModuleWithLeafListBuilder builderObject) {
        this.time = builderObject.time();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public YtbModuleWithLeafList processSubtreeFiltering(YtbModuleWithLeafList appInstance, boolean
            isSelectAllSchemaChild) {
        YtbModuleWithLeafListBuilder subTreeFilteringResultBuilder = new YtbModuleWithLeafListBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafListSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafListSubTreeFiltering(YtbModuleWithLeafList appInstance, YtbModuleWithLeafListBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Long time : appInstance.time()) {
                subTreeFilteringResultBuilder.addToTime(time);
            }
        } else if (time() != null) {
            if (!time().isEmpty()) {
                if (appInstance.time() == null || appInstance.time().isEmpty()) {
                    return false;
                }
                for (Long time : time()) {
                    boolean flag = false;
                    for (Long time2 : appInstance.time()) {
                        if (time.equals(time2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToTime(time2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.time() != null && !appInstance.time().isEmpty()) {
                    for (Long time : appInstance.time()) {
                        subTreeFilteringResultBuilder.addToTime(time);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbModuleWithLeafListOpParam.
     */
    protected YtbModuleWithLeafListOpParam() {
    }

    /**
     * Returns the attribute YtbModuleWithLeafListBuilder.
     *
     * @return value of YtbModuleWithLeafListBuilder
     */
    public static YtbModuleWithLeafListBuilder builder() {
        return new YtbModuleWithLeafListBuilder();
    }

    /**
     * Returns the valueLeafFlags.
     *
     * @return value of valueLeafFlags
     */
    public BitSet getValueLeafFlags() {
        return valueLeafFlags;
    }

    /**
     * Returns the selectLeafFlags.
     *
     * @return value of selectLeafFlags
     */
    public BitSet getSelectLeafFlags() {
        return selectLeafFlags;
    }


    @Override
    public boolean isLeafValueSet(LeafIdentifier leaf) {
        return getValueLeafFlags().get(leaf.getLeafIndex());
    }

    @Override
    public boolean isSelectLeaf(LeafIdentifier leaf) {
        return getSelectLeafFlags().get(leaf.getLeafIndex());
    }


    /**
     * Represents the builder implementation of ytbModuleWithLeafList.
     */
    public static class YtbModuleWithLeafListBuilder {

        protected List<Long> time = new ArrayList<>();

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;

        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        public List<Long> time() {
            return time;
        }

        /**
         * Returns the builder object of time.
         *
         * @param time list of time
         * @return builder object of time
         */
        public YtbModuleWithLeafListBuilder time(List<Long> time) {
            this.time = time;
            return this;
        }
        public YtbModuleWithLeafListBuilder addToTime(Long value) {
            time().add(value);
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
        public YtbModuleWithLeafListBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        /**
         * Returns the valueLeafFlags.
         *
         * @return value of valueLeafFlags
         */
        public BitSet getValueLeafFlags() {
            return valueLeafFlags;
        }

        /**
         * Returns the selectLeafFlags.
         *
         * @return value of selectLeafFlags
         */
        public BitSet getSelectLeafFlags() {
            return selectLeafFlags;
        }



        public YtbModuleWithLeafListBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public YtbModuleWithLeafList build() {
            return new YtbModuleWithLeafListOpParam(this);
        }

        /**
         * Creates an instance of ytbModuleWithLeafListBuilder.
         */
        public YtbModuleWithLeafListBuilder() {
        }
    }
}
