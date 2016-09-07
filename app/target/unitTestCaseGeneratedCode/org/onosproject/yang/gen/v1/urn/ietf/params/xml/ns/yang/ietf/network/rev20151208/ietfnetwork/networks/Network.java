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

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Uri;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.network
            .NetworkTypes;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.network
            .Node;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.network
            .SupportingNetwork;

/**
 * Abstraction of an entity which represents the functionality of network.
 */
public interface Network {

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
     * Identify the leaf of Network.
     */
    public enum LeafIdentifier {

        /**
         * Represents networkId.
         */
        NETWORKID(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute networkId.
     *
     * @return value of networkId
     */
    Uri networkId();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute networkTypes.
     *
     * @return value of networkTypes
     */
    NetworkTypes networkTypes();

    /**
     * Returns the attribute supportingNetwork.
     *
     * @return list of supportingNetwork
     */
    List<SupportingNetwork> supportingNetwork();

    /**
     * Returns the attribute node.
     *
     * @return list of node
     */
    List<Node> node();

    /**
     * Returns the attribute yangAugmentedInfo.
     *
     * @param classObject value of Augmentedclass
     * @return value of YangAugmentedInfo
     */
    Object yangAugmentedInfo(Class classObject);

    /**
     * Returns the attribute yangAugmentedInfoMap.
     *
     * @return value of yangAugmentedInfoMap
     */
    Map<Class<?>, Object> yangAugmentedInfoMap();

    /**
     * Checks if the passed Network maps the content match query condition.
     *
     * @param network network being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Network processSubtreeFiltering(Network network, boolean isSelectAllSchemaChild);

    /**
     * Checks if the leaf value is set.
     *
     * @param leaf leaf whose value status needs to checked
     * @return result of leaf value set in object
     */
    boolean isLeafValueSet(LeafIdentifier leaf);

    /**
     * Checks if the leaf is set to be a selected leaf.
     *
     * @param leaf if leaf needs to be selected
     * @return result of leaf value set in object
     */
    boolean isSelectLeaf(LeafIdentifier leaf);

    /**
     * Builder for network.
     */
    interface NetworkBuilder {


        /**
         * Adds to the list of supportingNetwork.
         *
         * @param value value of supportingNetwork
         * @return builder object of supportingNetwork
         */
        NetworkBuilder addToSupportingNetwork(SupportingNetwork value);

        /**
         * Adds to the list of node.
         *
         * @param value value of node
         * @return builder object of node
         */
        NetworkBuilder addToNode(Node value);
        /**
         * Returns the attribute networkId.
         *
         * @return value of networkId
         */
        Uri networkId();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute networkTypes.
         *
         * @return value of networkTypes
         */
        NetworkTypes networkTypes();

        /**
         * Returns the attribute supportingNetwork.
         *
         * @return list of supportingNetwork
         */
        List<SupportingNetwork> supportingNetwork();

        /**
         * Returns the attribute node.
         *
         * @return list of node
         */
        List<Node> node();

        /**
         * Returns the builder object of networkId.
         *
         * @param networkId value of networkId
         * @return builder object of networkId
         */
        NetworkBuilder networkId(Uri networkId);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        NetworkBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of networkTypes.
         *
         * @param networkTypes value of networkTypes
         * @return builder object of networkTypes
         */
        NetworkBuilder networkTypes(NetworkTypes networkTypes);

        /**
         * Returns the builder object of supportingNetwork.
         *
         * @param supportingNetwork list of supportingNetwork
         * @return builder object of supportingNetwork
         */
        NetworkBuilder supportingNetwork(List<SupportingNetwork> supportingNetwork);

        /**
         * Returns the builder object of node.
         *
         * @param node list of node
         * @return builder object of node
         */
        NetworkBuilder node(List<Node> node);


        /**
         * Sets the value of yangAugmentedInfoMap.
         *
         * @param value value of yangAugmentedInfo
         * @param classObject value of Augmentedclass
         */
        void addYangAugmentedInfo(Object value, Class classObject);

        /**
         * Returns the attribute yangAugmentedInfo.
         *
         * @param classObject value of Augmentedclass
         * @return value of YangAugmentedInfo
         */
        Object yangAugmentedInfo(Class classObject);

        /**
         * Returns the attribute yangAugmentedInfoMap.
         *
         * @return value of yangAugmentedInfoMap
         */
        Map<Class<?>, Object> yangAugmentedInfoMap();

        /**
         * Set a leaf to be selected.
         *
         * @param leaf leaf needs to be selected
         * @return builder object for select leaf
         */
        NetworkBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of network.
         *
         * @return object of network.
         */
        Network build();
    }
}
