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

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.Networks;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.NetworksState;

/**
 * Abstraction of an entity which represents the functionality of ietfNetwork.
 */
public interface IetfNetwork {

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
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute networks.
     *
     * @return value of networks
     */
    Networks networks();

    /**
     * Returns the attribute networksState.
     *
     * @return value of networksState
     */
    NetworksState networksState();

    /**
     * Checks if the passed IetfNetwork maps the content match query condition.
     *
     * @param ietfNetwork ietfNetwork being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    IetfNetwork processSubtreeFiltering(IetfNetwork ietfNetwork, boolean isSelectAllSchemaChild);
}
