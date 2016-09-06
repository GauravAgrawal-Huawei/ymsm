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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.Networks;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.NetworksState;

/**
 * Represents the implementation of ietfNetwork.
 */
public class IetfNetworkOpParam implements IetfNetwork {

    protected Networks networks;
    protected NetworksState networksState;

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

    public Networks networks() {
        return networks;
    }
    public NetworksState networksState() {
        return networksState;
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
        return Objects.hash(networks, networksState);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IetfNetworkOpParam) {
            IetfNetworkOpParam other = (IetfNetworkOpParam) obj;
            return
                 Objects.equals(networks, other.networks) &&
                 Objects.equals(networksState, other.networksState);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("networks", networks)
            .add("networksState", networksState)
            .toString();
    }

    /**
     * Creates an instance of ietfNetwork.
     *
     * @param builderObject builder object of ietfNetwork
     */
    protected IetfNetworkOpParam(IetfNetworkBuilder builderObject) {
        this.networks = builderObject.networks();
        this.networksState = builderObject.networksState();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public IetfNetwork processSubtreeFiltering(IetfNetwork appInstance, boolean isSelectAllSchemaChild) {
        IetfNetworkBuilder subTreeFilteringResultBuilder = new IetfNetworkBuilder();
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
    private boolean processChildNodesSubTreeFiltering(IetfNetwork appInstance, IetfNetworkBuilder
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

        if (networksState()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.networksState() != null) {
                NetworksState result = networksState.processSubtreeFiltering(appInstance.networksState(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.networksState(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ietfNetworkOpParam.
     */
    protected IetfNetworkOpParam() {
    }

    /**
     * Returns the attribute IetfNetworkBuilder.
     *
     * @return value of IetfNetworkBuilder
     */
    public static IetfNetworkBuilder builder() {
        return new IetfNetworkBuilder();
    }


    /**
     * Represents the builder implementation of ietfNetwork.
     */
    public static class IetfNetworkBuilder {

        protected Networks networks;
        protected NetworksState networksState;

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        public Networks networks() {
            return networks;
        }
        public NetworksState networksState() {
            return networksState;
        }

        /**
         * Returns the builder object of networks.
         *
         * @param networks value of networks
         * @return builder object of networks
         */
        public IetfNetworkBuilder networks(Networks networks) {
            this.networks = networks;
            return this;
        }

        /**
         * Returns the builder object of networksState.
         *
         * @param networksState value of networksState
         * @return builder object of networksState
         */
        public IetfNetworkBuilder networksState(NetworksState networksState) {
            this.networksState = networksState;
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
        public IetfNetworkBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        public IetfNetwork build() {
            return new IetfNetworkOpParam(this);
        }

        /**
         * Creates an instance of ietfNetworkBuilder.
         */
        public IetfNetworkBuilder() {
        }
    }
}
