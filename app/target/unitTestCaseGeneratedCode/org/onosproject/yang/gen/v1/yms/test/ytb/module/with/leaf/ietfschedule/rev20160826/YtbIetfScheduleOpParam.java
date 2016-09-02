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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.BitSet;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.ytbietfschedule.Monitor;

/**
 * Represents the implementation of ytbIetfSchedule.
 */
public class YtbIetfScheduleOpParam implements YtbIetfSchedule {

    protected byte time;
    protected Monitor monitor;

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
    public byte time() {
        return time;
    }
    public Monitor monitor() {
        return monitor;
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
        return Objects.hash(time, monitor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbIetfScheduleOpParam) {
            YtbIetfScheduleOpParam other = (YtbIetfScheduleOpParam) obj;
            return
                 Objects.equals(time, other.time) &&
                 Objects.equals(monitor, other.monitor);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("time", time)
            .add("monitor", monitor)
            .toString();
    }

    /**
     * Creates an instance of ytbIetfSchedule.
     *
     * @param builderObject builder object of ytbIetfSchedule
     */
    protected YtbIetfScheduleOpParam(YtbIetfScheduleBuilder builderObject) {
        this.time = builderObject.time();
        this.monitor = builderObject.monitor();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public YtbIetfSchedule processSubtreeFiltering(YtbIetfSchedule appInstance, boolean isSelectAllSchemaChild) {
        YtbIetfScheduleBuilder subTreeFilteringResultBuilder = new YtbIetfScheduleBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(YtbIetfSchedule appInstance, YtbIetfScheduleBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.TIME.getLeafIndex())) {
            if (appInstance.time() != time()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.time(appInstance.time());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.TIME.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.time(appInstance.time());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(YtbIetfSchedule appInstance, YtbIetfScheduleBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (monitor()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.monitor() != null) {
                Monitor result = monitor.processSubtreeFiltering(appInstance.monitor(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.monitor(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbIetfScheduleOpParam.
     */
    protected YtbIetfScheduleOpParam() {
    }

    /**
     * Returns the attribute YtbIetfScheduleBuilder.
     *
     * @return value of YtbIetfScheduleBuilder
     */
    public static YtbIetfScheduleBuilder builder() {
        return new YtbIetfScheduleBuilder();
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
     * Represents the builder implementation of ytbIetfSchedule.
     */
    public static class YtbIetfScheduleBuilder {

        protected byte time;
        protected Monitor monitor;

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

        public byte time() {
            return time;
        }
        public Monitor monitor() {
            return monitor;
        }

        /**
         * Returns the builder object of time.
         *
         * @param time value of time
         * @return builder object of time
         */
        public YtbIetfScheduleBuilder time(byte time) {
            getValueLeafFlags().set(LeafIdentifier.TIME.getLeafIndex());
            this.time = time;
            return this;
        }

        /**
         * Returns the builder object of monitor.
         *
         * @param monitor value of monitor
         * @return builder object of monitor
         */
        public YtbIetfScheduleBuilder monitor(Monitor monitor) {
            this.monitor = monitor;
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
        public YtbIetfScheduleBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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



        public YtbIetfScheduleBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public YtbIetfSchedule build() {
            return new YtbIetfScheduleOpParam(this);
        }

        /**
         * Creates an instance of ytbIetfScheduleBuilder.
         */
        public YtbIetfScheduleBuilder() {
        }
    }
}
