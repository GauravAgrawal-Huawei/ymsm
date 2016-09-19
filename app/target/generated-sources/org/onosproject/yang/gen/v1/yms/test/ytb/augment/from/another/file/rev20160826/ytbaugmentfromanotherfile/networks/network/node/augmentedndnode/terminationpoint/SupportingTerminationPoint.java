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

package org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile
            .networks.network.node.augmentedndnode.terminationpoint;

import java.util.Map;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Uri;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.NodeId;

/**
 * Abstraction of an entity which represents the functionality of supportingTerminationPoint.
 */
public interface SupportingTerminationPoint {

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
     * Identify the leaf of SupportingTerminationPoint.
     */
    public enum LeafIdentifier {

        /**
         * Represents networkRef.
         */
        NETWORKREF(1),
        /**
         * Represents nodeRef.
         */
        NODEREF(2),
        /**
         * Represents tpRef.
         */
        TPREF(3);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute networkRef.
     *
     * @return value of networkRef
     */
    Uri networkRef();

    /**
     * Returns the attribute nodeRef.
     *
     * @return value of nodeRef
     */
    NodeId nodeRef();

    /**
     * Returns the attribute tpRef.
     *
     * @return value of tpRef
     */
    String tpRef();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

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
     * Checks if the passed SupportingTerminationPoint maps the content match query condition.
     *
     * @param supportingTerminationPoint supportingTerminationPoint being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    SupportingTerminationPoint processSubtreeFiltering(SupportingTerminationPoint supportingTerminationPoint, boolean
            isSelectAllSchemaChild);

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
     * Builder for supportingTerminationPoint.
     */
    interface SupportingTerminationPointBuilder {

        /**
         * Returns the attribute networkRef.
         *
         * @return value of networkRef
         */
        Uri networkRef();

        /**
         * Returns the attribute nodeRef.
         *
         * @return value of nodeRef
         */
        NodeId nodeRef();

        /**
         * Returns the attribute tpRef.
         *
         * @return value of tpRef
         */
        String tpRef();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the builder object of networkRef.
         *
         * @param networkRef value of networkRef
         * @return builder object of networkRef
         */
        SupportingTerminationPointBuilder networkRef(Uri networkRef);

        /**
         * Returns the builder object of nodeRef.
         *
         * @param nodeRef value of nodeRef
         * @return builder object of nodeRef
         */
        SupportingTerminationPointBuilder nodeRef(NodeId nodeRef);

        /**
         * Returns the builder object of tpRef.
         *
         * @param tpRef value of tpRef
         * @return builder object of tpRef
         */
        SupportingTerminationPointBuilder tpRef(String tpRef);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        SupportingTerminationPointBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType);


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
        SupportingTerminationPointBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of supportingTerminationPoint.
         *
         * @return object of supportingTerminationPoint.
         */
        SupportingTerminationPoint build();
    }
}
