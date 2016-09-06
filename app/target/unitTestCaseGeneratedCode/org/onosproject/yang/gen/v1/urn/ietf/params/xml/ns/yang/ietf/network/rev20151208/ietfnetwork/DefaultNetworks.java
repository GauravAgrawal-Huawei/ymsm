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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.Network;

/**
 * Represents the implementation of networks.
 */
public class DefaultNetworks implements Networks {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
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


    protected List<Network> network = new ArrayList<>();
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }


    @Override
    public List<Network> network() {
        return network;
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
        if (obj instanceof DefaultNetworks) {
            DefaultNetworks other = (DefaultNetworks) obj;
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

    @Override
    public Object yangAugmentedInfo(Class classObject) {
        return yangAugmentedInfoMap.get(classObject);
    }

    @Override
    public Map<Class<?>, Object> yangAugmentedInfoMap() {
        return yangAugmentedInfoMap;
    }

    /**
     * Creates an instance of networks.
     *
     * @param builderObject builder object of networks
     */
    protected DefaultNetworks(NetworksBuilder builderObject) {
        this.network = builderObject.network();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Networks processSubtreeFiltering(Networks appInstance, boolean isSelectAllSchemaChild) {
        NetworksBuilder subTreeFilteringResultBuilder = new NetworksBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        for (Object yangAugmentedInfo : this.yangAugmentedInfoMap().values()) {
            Object yangAugmentedOpParamInfo = appInstance.yangAugmentedInfo(yangAugmentedInfo.getClass());
            Object processSubtreeFiltering;
            try {
                Class<?>[] interfaces = yangAugmentedInfo.getClass().getInterfaces();
                processSubtreeFiltering = yangAugmentedInfo.getClass()
                    .getMethod("processSubtreeFiltering", interfaces[0]).invoke(yangAugmentedInfo,
                        yangAugmentedOpParamInfo);
                if (processSubtreeFiltering != null) {
                    subTreeFilteringResultBuilder
            .addYangAugmentedInfo(processSubtreeFiltering, processSubtreeFiltering.getClass());
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                continue;
            }
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processChildNodesSubTreeFiltering(Networks appInstance, NetworksBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Network network : appInstance.network()) {
                subTreeFilteringResultBuilder.addToNetwork(network);
            }
        } else if (network() != null) {
            isAnySelectOrContainmentNode = true;
            if (!network().isEmpty()) {
                if (appInstance.network() != null && !appInstance.network().isEmpty()) {
                    for (Network network : network()) {
                        for (Network network2 : appInstance.network()) {
                            Network result = network.processSubtreeFiltering(network2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToNetwork(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.network() != null && !appInstance.network().isEmpty()) {
                    for (Network network : appInstance.network()) {
                        subTreeFilteringResultBuilder.addToNetwork(network);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultNetworks.
     */
    protected DefaultNetworks() {
    }

    /**
     * Returns the attribute NetworksBuilder.
     *
     * @return value of NetworksBuilder
     */
    public static NetworksBuilder builder() {
        return new NetworksBuilder();
    }


    /**
     * Represents the builder implementation of networks.
     */
    public static class NetworksBuilder implements Networks.NetworksBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected List<Network> network = new ArrayList<>();

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
        public NetworksBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public List<Network> network() {
            return network;
        }

        @Override
        public NetworksBuilder network(List<Network> network) {
            this.network = network;
            return this;
        }

        @Override
        public NetworksBuilder addToNetwork(Network value) {
            network().add(value);
            return this;
        }

        @Override
        public void addYangAugmentedInfo(Object value, Class classObject) {
            yangAugmentedInfoMap.put(classObject, value);
        }

        @Override
        public Object yangAugmentedInfo(Class classObject) {
            return yangAugmentedInfoMap.get(classObject);
        }

        @Override
        public Map<Class<?>, Object> yangAugmentedInfoMap() {
            return yangAugmentedInfoMap;
        }
        @Override
        public Networks build() {
            return new DefaultNetworks(this);
        }

        /**
         * Creates an instance of networksBuilder.
         */
        public NetworksBuilder() {
        }
    }
}
