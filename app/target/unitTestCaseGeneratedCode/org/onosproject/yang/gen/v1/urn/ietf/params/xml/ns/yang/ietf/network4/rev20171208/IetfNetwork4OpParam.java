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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network4.rev20171208;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network4.rev20171208.ietfnetwork4.Network;

/**
 * Represents the implementation of ietfNetwork4.
 */
public class IetfNetwork4OpParam implements IetfNetwork4 {

    protected Network network;

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

    public Network network() {
        return network;
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
        return Objects.hash(network);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IetfNetwork4OpParam) {
            IetfNetwork4OpParam other = (IetfNetwork4OpParam) obj;
            return
                 Objects.equals(network, other.network);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("network", network)
            .toString();
    }

    /**
     * Creates an instance of ietfNetwork4.
     *
     * @param builderObject builder object of ietfNetwork4
     */
    protected IetfNetwork4OpParam(IetfNetwork4Builder builderObject) {
        this.network = builderObject.network();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public IetfNetwork4 processSubtreeFiltering(IetfNetwork4 appInstance, boolean isSelectAllSchemaChild) {
        IetfNetwork4Builder subTreeFilteringResultBuilder = new IetfNetwork4Builder();
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
    private boolean processChildNodesSubTreeFiltering(IetfNetwork4 appInstance, IetfNetwork4Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (network()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.network() != null) {
                Network result = network.processSubtreeFiltering(appInstance.network(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.network(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ietfNetwork4OpParam.
     */
    protected IetfNetwork4OpParam() {
    }

    /**
     * Returns the attribute IetfNetwork4Builder.
     *
     * @return value of IetfNetwork4Builder
     */
    public static IetfNetwork4Builder builder() {
        return new IetfNetwork4Builder();
    }


    /**
     * Represents the builder implementation of ietfNetwork4.
     */
    public static class IetfNetwork4Builder {

        protected Network network;

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        public Network network() {
            return network;
        }

        /**
         * Returns the builder object of network.
         *
         * @param network value of network
         * @return builder object of network
         */
        public IetfNetwork4Builder network(Network network) {
            this.network = network;
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
        public IetfNetwork4Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        public IetfNetwork4 build() {
            return new IetfNetwork4OpParam(this);
        }

        /**
         * Creates an instance of ietfNetwork4Builder.
         */
        public IetfNetwork4Builder() {
        }
    }
}
