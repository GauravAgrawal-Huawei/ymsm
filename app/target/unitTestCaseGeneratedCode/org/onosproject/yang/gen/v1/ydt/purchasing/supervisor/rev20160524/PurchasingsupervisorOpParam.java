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

package org.onosproject.yang.gen.v1.ydt.purchasing.supervisor.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.purchasing.supervisor.rev20160524.purchasingsupervisor.Supervisor;

/**
 * Represents the implementation of purchasingsupervisor.
 */
public class PurchasingsupervisorOpParam implements Purchasingsupervisor {

    protected Supervisor supervisor;

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

    public Supervisor supervisor() {
        return supervisor;
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
        return Objects.hash(supervisor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PurchasingsupervisorOpParam) {
            PurchasingsupervisorOpParam other = (PurchasingsupervisorOpParam) obj;
            return
                 Objects.equals(supervisor, other.supervisor);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("supervisor", supervisor)
            .toString();
    }

    /**
     * Creates an instance of purchasingsupervisor.
     *
     * @param builderObject builder object of purchasingsupervisor
     */
    protected PurchasingsupervisorOpParam(PurchasingsupervisorBuilder builderObject) {
        this.supervisor = builderObject.supervisor();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public Purchasingsupervisor processSubtreeFiltering(Purchasingsupervisor appInstance, boolean
            isSelectAllSchemaChild) {
        PurchasingsupervisorBuilder subTreeFilteringResultBuilder = new PurchasingsupervisorBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Purchasingsupervisor appInstance, PurchasingsupervisorBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (supervisor()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.supervisor() != null) {
                Supervisor result = supervisor.processSubtreeFiltering(appInstance.supervisor(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.supervisor(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of purchasingsupervisorOpParam.
     */
    protected PurchasingsupervisorOpParam() {
    }

    /**
     * Returns the attribute PurchasingsupervisorBuilder.
     *
     * @return value of PurchasingsupervisorBuilder
     */
    public static PurchasingsupervisorBuilder builder() {
        return new PurchasingsupervisorBuilder();
    }


    /**
     * Represents the builder implementation of purchasingsupervisor.
     */
    public static class PurchasingsupervisorBuilder {

        protected Supervisor supervisor;

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        public Supervisor supervisor() {
            return supervisor;
        }

        /**
         * Returns the builder object of supervisor.
         *
         * @param supervisor value of supervisor
         * @return builder object of supervisor
         */
        public PurchasingsupervisorBuilder supervisor(Supervisor supervisor) {
            this.supervisor = supervisor;
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
        public PurchasingsupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        public Purchasingsupervisor build() {
            return new PurchasingsupervisorOpParam(this);
        }

        /**
         * Creates an instance of purchasingsupervisorBuilder.
         */
        public PurchasingsupervisorBuilder() {
        }
    }
}
