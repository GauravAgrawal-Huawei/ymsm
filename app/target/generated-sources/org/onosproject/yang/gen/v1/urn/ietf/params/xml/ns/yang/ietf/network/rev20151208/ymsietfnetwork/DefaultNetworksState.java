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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.networksstate
            .Network;

/**
 * Represents the implementation of networksState.
 */
public class DefaultNetworksState implements NetworksState {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<Network> network;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<Network> network() {
        return network;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, network);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultNetworksState) {
            DefaultNetworksState other = (DefaultNetworksState) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(network, other.network);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
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
     * Creates an instance of networksState.
     *
     * @param builderObject builder object of networksState
     */
    protected DefaultNetworksState(NetworksStateBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.network = builderObject.network();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public NetworksState processSubtreeFiltering(NetworksState appInstance, boolean isSelectAllSchemaChild) {
        NetworksStateBuilder subTreeFilteringResultBuilder = new NetworksStateBuilder();
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
    private boolean processChildNodesSubTreeFiltering(NetworksState appInstance, NetworksStateBuilder
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
     * Creates an instance of defaultNetworksState.
     */
    protected DefaultNetworksState() {
    }

    /**
     * Returns the attribute NetworksStateBuilder.
     *
     * @return value of NetworksStateBuilder
     */
    public static NetworksStateBuilder builder() {
        return new NetworksStateBuilder();
    }


    /**
     * Represents the builder implementation of networksState.
     */
    public static class NetworksStateBuilder implements NetworksState.NetworksStateBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<Network> network;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<Network> network() {
            return network;
        }

        @Override
        public NetworksStateBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public NetworksStateBuilder network(List<Network> network) {
            this.network = network;
            return this;
        }

        @Override
        public NetworksStateBuilder addToNetwork(Network value) {
            if (network() == null) {
                network(new ArrayList<>());
            }
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
        public NetworksState build() {
            return new DefaultNetworksState(this);
        }

        /**
         * Creates an instance of networksStateBuilder.
         */
        public NetworksStateBuilder() {
        }
    }
}
