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
            .networks.network.node.augmentedndnode;

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile
            .networks.network.node.augmentedndnode.terminationpoint.SupportingTerminationPoint;

/**
 * Abstraction of an entity which represents the functionality of terminationPoint.
 */
public interface TerminationPoint {

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
     * Identify the leaf of TerminationPoint.
     */
    public enum LeafIdentifier {

        /**
         * Represents tpId.
         */
        TPID(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute tpId.
     *
     * @return value of tpId
     */
    String tpId();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute supportingTerminationPoint.
     *
     * @return list of supportingTerminationPoint
     */
    List<SupportingTerminationPoint> supportingTerminationPoint();

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
     * Checks if the passed TerminationPoint maps the content match query condition.
     *
     * @param terminationPoint terminationPoint being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    TerminationPoint processSubtreeFiltering(TerminationPoint terminationPoint, boolean isSelectAllSchemaChild);

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
     * Builder for terminationPoint.
     */
    interface TerminationPointBuilder {


        /**
         * Adds to the list of supportingTerminationPoint.
         *
         * @param value value of supportingTerminationPoint
         * @return builder object of supportingTerminationPoint
         */
        TerminationPointBuilder addToSupportingTerminationPoint(SupportingTerminationPoint value);
        /**
         * Returns the attribute tpId.
         *
         * @return value of tpId
         */
        String tpId();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute supportingTerminationPoint.
         *
         * @return list of supportingTerminationPoint
         */
        List<SupportingTerminationPoint> supportingTerminationPoint();

        /**
         * Returns the builder object of tpId.
         *
         * @param tpId value of tpId
         * @return builder object of tpId
         */
        TerminationPointBuilder tpId(String tpId);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        TerminationPointBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of supportingTerminationPoint.
         *
         * @param supportingTerminationPoint list of supportingTerminationPoint
         * @return builder object of supportingTerminationPoint
         */
        TerminationPointBuilder supportingTerminationPoint(List<SupportingTerminationPoint>
            supportingTerminationPoint);


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
        TerminationPointBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of terminationPoint.
         *
         * @return object of terminationPoint.
         */
        TerminationPoint build();
    }
}
