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

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Supervisor supervisor;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public Supervisor supervisor() {
        return supervisor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, supervisor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PurchasingsupervisorOpParam) {
            PurchasingsupervisorOpParam other = (PurchasingsupervisorOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(supervisor, other.supervisor);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("supervisor", supervisor)
            .toString();
    }

    /**
     * Creates an instance of purchasingsupervisor.
     *
     * @param builderObject builder object of purchasingsupervisor
     */
    protected PurchasingsupervisorOpParam(PurchasingsupervisorBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.supervisor = builderObject.supervisor();
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

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Supervisor supervisor;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public Supervisor supervisor() {
            return supervisor;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public PurchasingsupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
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
