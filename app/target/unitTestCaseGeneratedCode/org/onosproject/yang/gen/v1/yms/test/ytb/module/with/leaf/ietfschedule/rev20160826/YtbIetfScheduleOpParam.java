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
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.ytbietfschedule.Enum1Enum;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.ytbietfschedule.Enum2Enum;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.ytbietfschedule.Monitor;

/**
 * Represents the implementation of ytbIetfSchedule.
 */
public class YtbIetfScheduleOpParam implements YtbIetfSchedule {

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


    protected byte time;
    protected Enum1Enum enum1;
    protected List<Enum2Enum> enum2 = new ArrayList<>();
    protected Monitor monitor;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    public byte time() {
        return time;
    }
    public Enum1Enum enum1() {
        return enum1;
    }
    public List<Enum2Enum> enum2() {
        return enum2;
    }
    public Monitor monitor() {
        return monitor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, enum1, enum2, monitor);
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
                 Objects.equals(enum1, other.enum1) &&
                 Objects.equals(enum2, other.enum2) &&
                 Objects.equals(monitor, other.monitor);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("time", time)
            .add("enum1", enum1)
            .add("enum2", enum2)
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
        this.enum1 = builderObject.enum1();
        this.enum2 = builderObject.enum2();
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
        if (!processLeafListSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
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

        if (getValueLeafFlags().get(LeafIdentifier.ENUM1.getLeafIndex())) {
            if (appInstance.enum1() == null || !(enum1().equals(appInstance.enum1()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.enum1(appInstance.enum1());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.ENUM1.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.enum1(appInstance.enum1());
        }

        return true;
    }

    private boolean processLeafListSubTreeFiltering(YtbIetfSchedule appInstance, YtbIetfScheduleBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Enum2Enum enum2 : appInstance.enum2()) {
                subTreeFilteringResultBuilder.addToEnum2(enum2);
            }
        } else if (enum2() != null) {
            if (!enum2().isEmpty()) {
                if (appInstance.enum2() == null || appInstance.enum2().isEmpty()) {
                    return false;
                }
                for (Enum2Enum enum2 : enum2()) {
                    boolean flag = false;
                    for (Enum2Enum enum22 : appInstance.enum2()) {
                        if (enum2.equals(enum22)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToEnum2(enum22);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.enum2() != null && !appInstance.enum2().isEmpty()) {
                    for (Enum2Enum enum2 : appInstance.enum2()) {
                        subTreeFilteringResultBuilder.addToEnum2(enum2);
                    }
                }
            }
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

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected byte time;
        protected Enum1Enum enum1;
        protected List<Enum2Enum> enum2 = new ArrayList<>();
        protected Monitor monitor;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

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

        public byte time() {
            return time;
        }
        public Enum1Enum enum1() {
            return enum1;
        }
        public List<Enum2Enum> enum2() {
            return enum2;
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
         * Returns the builder object of enum1.
         *
         * @param enum1 value of enum1
         * @return builder object of enum1
         */
        public YtbIetfScheduleBuilder enum1(Enum1Enum enum1) {
            getValueLeafFlags().set(LeafIdentifier.ENUM1.getLeafIndex());
            this.enum1 = enum1;
            return this;
        }

        /**
         * Returns the builder object of enum2.
         *
         * @param enum2 list of enum2
         * @return builder object of enum2
         */
        public YtbIetfScheduleBuilder enum2(List<Enum2Enum> enum2) {
            this.enum2 = enum2;
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
        public YtbIetfScheduleBuilder addToEnum2(Enum2Enum value) {
            enum2().add(value);
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
