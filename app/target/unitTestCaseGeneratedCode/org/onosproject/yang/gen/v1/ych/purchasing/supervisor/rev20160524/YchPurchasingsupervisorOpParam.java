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

package org.onosproject.yang.gen.v1.ych.purchasing.supervisor.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ych.purchasing.supervisor.rev20160524.ychpurchasingsupervisor
            .YchPurchasingSupervisor;

/**
 * Represents the implementation of ychPurchasingsupervisor.
 */
public class YchPurchasingsupervisorOpParam implements YchPurchasingsupervisor {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected YchPurchasingSupervisor ychPurchasingSupervisor;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public YchPurchasingSupervisor ychPurchasingSupervisor() {
        return ychPurchasingSupervisor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, ychPurchasingSupervisor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YchPurchasingsupervisorOpParam) {
            YchPurchasingsupervisorOpParam other = (YchPurchasingsupervisorOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(ychPurchasingSupervisor, other.ychPurchasingSupervisor);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("ychPurchasingSupervisor", ychPurchasingSupervisor)
            .toString();
    }

    /**
     * Creates an instance of ychPurchasingsupervisor.
     *
     * @param builderObject builder object of ychPurchasingsupervisor
     */
    protected YchPurchasingsupervisorOpParam(YchPurchasingsupervisorBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.ychPurchasingSupervisor = builderObject.ychPurchasingSupervisor();
    }

    @Override
    public YchPurchasingsupervisor processSubtreeFiltering(YchPurchasingsupervisor appInstance, boolean
            isSelectAllSchemaChild) {
        YchPurchasingsupervisorBuilder subTreeFilteringResultBuilder = new YchPurchasingsupervisorBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YchPurchasingsupervisor appInstance,
            YchPurchasingsupervisorBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (ychPurchasingSupervisor()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.ychPurchasingSupervisor() != null) {
                YchPurchasingSupervisor result = ychPurchasingSupervisor.processSubtreeFiltering(appInstance
            .ychPurchasingSupervisor(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.ychPurchasingSupervisor(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ychPurchasingsupervisorOpParam.
     */
    protected YchPurchasingsupervisorOpParam() {
    }

    /**
     * Returns the attribute YchPurchasingsupervisorBuilder.
     *
     * @return value of YchPurchasingsupervisorBuilder
     */
    public static YchPurchasingsupervisorBuilder builder() {
        return new YchPurchasingsupervisorBuilder();
    }


    /**
     * Represents the builder implementation of ychPurchasingsupervisor.
     */
    public static class YchPurchasingsupervisorBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected YchPurchasingSupervisor ychPurchasingSupervisor;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public YchPurchasingSupervisor ychPurchasingSupervisor() {
            return ychPurchasingSupervisor;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YchPurchasingsupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of ychPurchasingSupervisor.
         *
         * @param ychPurchasingSupervisor value of ychPurchasingSupervisor
         * @return builder object of ychPurchasingSupervisor
         */
        public YchPurchasingsupervisorBuilder ychPurchasingSupervisor(YchPurchasingSupervisor
            ychPurchasingSupervisor) {
            this.ychPurchasingSupervisor = ychPurchasingSupervisor;
            return this;
        }

        public YchPurchasingsupervisor build() {
            return new YchPurchasingsupervisorOpParam(this);
        }

        /**
         * Creates an instance of ychPurchasingsupervisorBuilder.
         */
        public YchPurchasingsupervisorBuilder() {
        }
    }
}
