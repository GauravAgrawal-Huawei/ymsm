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

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.Network;

/**
 * Abstraction of an entity which represents the functionality of networks.
 */
public interface Networks {

    /**
     * Returns the attribute network.
     *
     * @return list of network
     */
    List<Network> network();

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
     * Checks if the passed Networks maps the content match query condition.
     *
     * @param networks networks being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Networks processSubtreeFiltering(Networks networks, boolean isSelectAllSchemaChild);

    /**
     * Builder for networks.
     */
    interface NetworksBuilder {


        /**
         * Adds to the list of network.
         *
         * @param value value of network
         * @return builder object of network
         */
        NetworksBuilder addToNetwork(Network value);
        /**
         * Returns the attribute network.
         *
         * @return list of network
         */
        List<Network> network();

        /**
         * Returns the builder object of network.
         *
         * @param network list of network
         * @return builder object of network
         */
        NetworksBuilder network(List<Network> network);


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
         * Builds object of networks.
         *
         * @return object of networks.
         */
        Networks build();
    }
}