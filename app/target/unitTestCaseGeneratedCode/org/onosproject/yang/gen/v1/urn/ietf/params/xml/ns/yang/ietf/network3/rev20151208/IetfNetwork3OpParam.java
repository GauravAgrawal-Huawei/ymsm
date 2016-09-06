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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network3.rev20151208;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network3.rev20151208.ietfnetwork3.Networks;

/**
 * Represents the implementation of ietfNetwork3.
 */
public class IetfNetwork3OpParam implements IetfNetwork3 {

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


    protected Networks networks;
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    public Networks networks() {
        return networks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(networks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IetfNetwork3OpParam) {
            IetfNetwork3OpParam other = (IetfNetwork3OpParam) obj;
            return
                 Objects.equals(networks, other.networks);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("networks", networks)
            .toString();
    }

    /**
     * Creates an instance of ietfNetwork3.
     *
     * @param builderObject builder object of ietfNetwork3
     */
    protected IetfNetwork3OpParam(IetfNetwork3Builder builderObject) {
        this.networks = builderObject.networks();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public IetfNetwork3 processSubtreeFiltering(IetfNetwork3 appInstance, boolean isSelectAllSchemaChild) {
        IetfNetwork3Builder subTreeFilteringResultBuilder = new IetfNetwork3Builder();
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
    private boolean processChildNodesSubTreeFiltering(IetfNetwork3 appInstance, IetfNetwork3Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (networks()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.networks() != null) {
                Networks result = networks.processSubtreeFiltering(appInstance.networks(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.networks(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ietfNetwork3OpParam.
     */
    protected IetfNetwork3OpParam() {
    }

    /**
     * Returns the attribute IetfNetwork3Builder.
     *
     * @return value of IetfNetwork3Builder
     */
    public static IetfNetwork3Builder builder() {
        return new IetfNetwork3Builder();
    }


    /**
     * Represents the builder implementation of ietfNetwork3.
     */
    public static class IetfNetwork3Builder {

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected Networks networks;

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
        public IetfNetwork3Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }

        public Networks networks() {
            return networks;
        }

        /**
         * Returns the builder object of networks.
         *
         * @param networks value of networks
         * @return builder object of networks
         */
        public IetfNetwork3Builder networks(Networks networks) {
            this.networks = networks;
            return this;
        }

        public IetfNetwork3 build() {
            return new IetfNetwork3OpParam(this);
        }

        /**
         * Creates an instance of ietfNetwork3Builder.
         */
        public IetfNetwork3Builder() {
        }
    }
}
