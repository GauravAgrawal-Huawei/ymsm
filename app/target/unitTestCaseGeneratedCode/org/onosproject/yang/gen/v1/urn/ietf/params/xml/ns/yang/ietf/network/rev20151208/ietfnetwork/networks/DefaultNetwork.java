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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.NetworkId;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.network
            .NetworkTypes;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.network
            .Node;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.network
            .SupportingNetwork;

/**
 * Represents the implementation of network.
 */
public class DefaultNetwork implements Network {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected NetworkId networkId;
    protected NetworkTypes networkTypes;
    protected List<SupportingNetwork> supportingNetwork = new ArrayList<>();
    protected List<Node> node = new ArrayList<>();

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

    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();

    @Override
    public NetworkId networkId() {
        return networkId;
    }

    @Override
    public NetworkTypes networkTypes() {
        return networkTypes;
    }

    @Override
    public List<SupportingNetwork> supportingNetwork() {
        return supportingNetwork;
    }

    @Override
    public List<Node> node() {
        return node;
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
        return Objects.hash(networkId, networkTypes, supportingNetwork, node);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultNetwork) {
            DefaultNetwork other = (DefaultNetwork) obj;
            return
                 Objects.equals(networkId, other.networkId) &&
                 Objects.equals(networkTypes, other.networkTypes) &&
                 Objects.equals(supportingNetwork, other.supportingNetwork) &&
                 Objects.equals(node, other.node);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("networkId", networkId)
            .add("networkTypes", networkTypes)
            .add("supportingNetwork", supportingNetwork)
            .add("node", node)
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
     * Creates an instance of network.
     *
     * @param builderObject builder object of network
     */
    protected DefaultNetwork(NetworkBuilder builderObject) {
        this.networkId = builderObject.networkId();
        this.networkTypes = builderObject.networkTypes();
        this.supportingNetwork = builderObject.supportingNetwork();
        this.node = builderObject.node();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Network processSubtreeFiltering(Network appInstance, boolean isSelectAllSchemaChild) {
        NetworkBuilder subTreeFilteringResultBuilder = new NetworkBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
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
    private boolean processLeafSubtreeFiltering(Network appInstance, NetworkBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.NETWORKID.getLeafIndex())) {
            if (appInstance.networkId() == null || !(networkId().equals(appInstance.networkId()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.networkId(appInstance.networkId());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.NETWORKID.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.networkId(appInstance.networkId());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(Network appInstance, NetworkBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (networkTypes()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.networkTypes() != null) {
                NetworkTypes result = networkTypes.processSubtreeFiltering(appInstance.networkTypes(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.networkTypes(result);
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (SupportingNetwork supportingNetwork : appInstance.supportingNetwork()) {
                subTreeFilteringResultBuilder.addToSupportingNetwork(supportingNetwork);
            }
        } else if (supportingNetwork() != null) {
            isAnySelectOrContainmentNode = true;
            if (!supportingNetwork().isEmpty()) {
                if (appInstance.supportingNetwork() != null && !appInstance.supportingNetwork().isEmpty()) {
                    for (SupportingNetwork supportingNetwork : supportingNetwork()) {
                        for (SupportingNetwork supportingNetwork2 : appInstance.supportingNetwork()) {
                            SupportingNetwork result = supportingNetwork
            .processSubtreeFiltering(supportingNetwork2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToSupportingNetwork(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.supportingNetwork() != null && !appInstance.supportingNetwork().isEmpty()) {
                    for (SupportingNetwork supportingNetwork : appInstance.supportingNetwork()) {
                        subTreeFilteringResultBuilder.addToSupportingNetwork(supportingNetwork);
                    }
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (Node node : appInstance.node()) {
                subTreeFilteringResultBuilder.addToNode(node);
            }
        } else if (node() != null) {
            isAnySelectOrContainmentNode = true;
            if (!node().isEmpty()) {
                if (appInstance.node() != null && !appInstance.node().isEmpty()) {
                    for (Node node : node()) {
                        for (Node node2 : appInstance.node()) {
                            Node result = node.processSubtreeFiltering(node2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToNode(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.node() != null && !appInstance.node().isEmpty()) {
                    for (Node node : appInstance.node()) {
                        subTreeFilteringResultBuilder.addToNode(node);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultNetwork.
     */
    protected DefaultNetwork() {
    }

    /**
     * Returns the attribute NetworkBuilder.
     *
     * @return value of NetworkBuilder
     */
    public static NetworkBuilder builder() {
        return new NetworkBuilder();
    }

    /**
     * Returns the valueLeafFlags.
     *
     * @return value of valueLeafFlags
     */
    public BitSet getValueLeafFlags() {
        return valueLeafFlags;
    }

    /**
     * Returns the selectLeafFlags.
     *
     * @return value of selectLeafFlags
     */
    public BitSet getSelectLeafFlags() {
        return selectLeafFlags;
    }


    @Override
    public boolean isLeafValueSet(LeafIdentifier leaf) {
        return getValueLeafFlags().get(leaf.getLeafIndex());
    }

    @Override
    public boolean isSelectLeaf(LeafIdentifier leaf) {
        return getSelectLeafFlags().get(leaf.getLeafIndex());
    }


    /**
     * Represents the builder implementation of network.
     */
    public static class NetworkBuilder implements Network.NetworkBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected NetworkId networkId;
        protected NetworkTypes networkTypes;
        protected List<SupportingNetwork> supportingNetwork = new ArrayList<>();
        protected List<Node> node = new ArrayList<>();

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;

        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();


        @Override
        public NetworkId networkId() {
            return networkId;
        }

        @Override
        public NetworkTypes networkTypes() {
            return networkTypes;
        }

        @Override
        public List<SupportingNetwork> supportingNetwork() {
            return supportingNetwork;
        }

        @Override
        public List<Node> node() {
            return node;
        }

        @Override
        public NetworkBuilder networkId(NetworkId networkId) {
            getValueLeafFlags().set(LeafIdentifier.NETWORKID.getLeafIndex());
            this.networkId = networkId;
            return this;
        }

        @Override
        public NetworkBuilder networkTypes(NetworkTypes networkTypes) {
            this.networkTypes = networkTypes;
            return this;
        }

        @Override
        public NetworkBuilder supportingNetwork(List<SupportingNetwork> supportingNetwork) {
            this.supportingNetwork = supportingNetwork;
            return this;
        }

        @Override
        public NetworkBuilder node(List<Node> node) {
            this.node = node;
            return this;
        }

        @Override
        public NetworkBuilder addToSupportingNetwork(SupportingNetwork value) {
            supportingNetwork().add(value);
            return this;
        }

        @Override
        public NetworkBuilder addToNode(Node value) {
            node().add(value);
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
        public NetworkBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        /**
         * Returns the valueLeafFlags.
         *
         * @return value of valueLeafFlags
         */
        public BitSet getValueLeafFlags() {
            return valueLeafFlags;
        }

        /**
         * Returns the selectLeafFlags.
         *
         * @return value of selectLeafFlags
         */
        public BitSet getSelectLeafFlags() {
            return selectLeafFlags;
        }



        @Override
        public NetworkBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
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
        public Network build() {
            return new DefaultNetwork(this);
        }

        /**
         * Creates an instance of networkBuilder.
         */
        public NetworkBuilder() {
        }
    }
}
