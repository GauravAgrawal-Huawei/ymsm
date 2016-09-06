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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.container.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.container.rev20160826.ytbmodulewithcontainer.Sched;

/**
 * Represents the implementation of ytbModuleWithContainer.
 */
public class YtbModuleWithContainerOpParam implements YtbModuleWithContainer {

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


    protected Sched sched;
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    public Sched sched() {
        return sched;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sched);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbModuleWithContainerOpParam) {
            YtbModuleWithContainerOpParam other = (YtbModuleWithContainerOpParam) obj;
            return
                 Objects.equals(sched, other.sched);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("sched", sched)
            .toString();
    }

    /**
     * Creates an instance of ytbModuleWithContainer.
     *
     * @param builderObject builder object of ytbModuleWithContainer
     */
    protected YtbModuleWithContainerOpParam(YtbModuleWithContainerBuilder builderObject) {
        this.sched = builderObject.sched();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public YtbModuleWithContainer processSubtreeFiltering(YtbModuleWithContainer appInstance, boolean
            isSelectAllSchemaChild) {
        YtbModuleWithContainerBuilder subTreeFilteringResultBuilder = new YtbModuleWithContainerBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbModuleWithContainer appInstance,
            YtbModuleWithContainerBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (sched()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.sched() != null) {
                Sched result = sched.processSubtreeFiltering(appInstance.sched(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.sched(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbModuleWithContainerOpParam.
     */
    protected YtbModuleWithContainerOpParam() {
    }

    /**
     * Returns the attribute YtbModuleWithContainerBuilder.
     *
     * @return value of YtbModuleWithContainerBuilder
     */
    public static YtbModuleWithContainerBuilder builder() {
        return new YtbModuleWithContainerBuilder();
    }


    /**
     * Represents the builder implementation of ytbModuleWithContainer.
     */
    public static class YtbModuleWithContainerBuilder {

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected Sched sched;

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
        public YtbModuleWithContainerBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }

        public Sched sched() {
            return sched;
        }

        /**
         * Returns the builder object of sched.
         *
         * @param sched value of sched
         * @return builder object of sched
         */
        public YtbModuleWithContainerBuilder sched(Sched sched) {
            this.sched = sched;
            return this;
        }

        public YtbModuleWithContainer build() {
            return new YtbModuleWithContainerOpParam(this);
        }

        /**
         * Creates an instance of ytbModuleWithContainerBuilder.
         */
        public YtbModuleWithContainerBuilder() {
        }
    }
}
