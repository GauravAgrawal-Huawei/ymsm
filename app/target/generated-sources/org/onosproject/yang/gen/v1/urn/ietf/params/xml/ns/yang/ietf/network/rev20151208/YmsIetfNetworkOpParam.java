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
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.Networks;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.NetworksState;

/**
 * Represents the implementation of ymsIetfNetwork.
 */
public class YmsIetfNetworkOpParam implements YmsIetfNetwork {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Networks networks;
    protected NetworksState networksState;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public Networks networks() {
        return networks;
    }
    public NetworksState networksState() {
        return networksState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, networks, networksState);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YmsIetfNetworkOpParam) {
            YmsIetfNetworkOpParam other = (YmsIetfNetworkOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(networks, other.networks) &&
                 Objects.equals(networksState, other.networksState);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("networks", networks)
            .add("networksState", networksState)
            .toString();
    }

    /**
     * Creates an instance of ymsIetfNetwork.
     *
     * @param builderObject builder object of ymsIetfNetwork
     */
    protected YmsIetfNetworkOpParam(YmsIetfNetworkBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.networks = builderObject.networks();
        this.networksState = builderObject.networksState();
    }

    @Override
    public YmsIetfNetwork processSubtreeFiltering(YmsIetfNetwork appInstance, boolean isSelectAllSchemaChild) {
        YmsIetfNetworkBuilder subTreeFilteringResultBuilder = new YmsIetfNetworkBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YmsIetfNetwork appInstance, YmsIetfNetworkBuilder
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
     * Creates an instance of ymsIetfNetworkOpParam.
     */
    protected YmsIetfNetworkOpParam() {
    }

    /**
     * Returns the attribute YmsIetfNetworkBuilder.
     *
     * @return value of YmsIetfNetworkBuilder
     */
    public static YmsIetfNetworkBuilder builder() {
        return new YmsIetfNetworkBuilder();
    }


    /**
     * Represents the builder implementation of ymsIetfNetwork.
     */
    public static class YmsIetfNetworkBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Networks networks;
        protected NetworksState networksState;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public Networks networks() {
            return networks;
        }
        public NetworksState networksState() {
            return networksState;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YmsIetfNetworkBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of networks.
         *
         * @param networks value of networks
         * @return builder object of networks
         */
        public YmsIetfNetworkBuilder networks(Networks networks) {
            this.networks = networks;
            return this;
        }

        /**
         * Returns the builder object of networksState.
         *
         * @param networksState value of networksState
         * @return builder object of networksState
         */
        public YmsIetfNetworkBuilder networksState(NetworksState networksState) {
            this.networksState = networksState;
            return this;
        }

        public YmsIetfNetwork build() {
            return new YmsIetfNetworkOpParam(this);
        }

        /**
         * Creates an instance of ymsIetfNetworkBuilder.
         */
        public YmsIetfNetworkBuilder() {
        }
    }
}
