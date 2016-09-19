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
            .networks.network.node;

import java.util.List;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile
            .networks.network.node.augmentedndnode.TerminationPoint;

/**
 * Abstraction of an entity which represents the functionality of augmentedNdNode.
 */
public interface AugmentedNdNode {

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
     * Returns the attribute terminationPoint.
     *
     * @return list of terminationPoint
     */
    List<TerminationPoint> terminationPoint();

    /**
     * Checks if the passed AugmentedNdNode maps the content match query condition.
     *
     * @param augmentedNdNode augmentedNdNode being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    AugmentedNdNode processSubtreeFiltering(AugmentedNdNode augmentedNdNode, boolean isSelectAllSchemaChild);

    /**
     * Builder for augmentedNdNode.
     */
    interface AugmentedNdNodeBuilder {


        /**
         * Adds to the list of terminationPoint.
         *
         * @param value value of terminationPoint
         * @return builder object of terminationPoint
         */
        AugmentedNdNodeBuilder addToTerminationPoint(TerminationPoint value);
        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute terminationPoint.
         *
         * @return list of terminationPoint
         */
        List<TerminationPoint> terminationPoint();

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        AugmentedNdNodeBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of terminationPoint.
         *
         * @param terminationPoint list of terminationPoint
         * @return builder object of terminationPoint
         */
        AugmentedNdNodeBuilder terminationPoint(List<TerminationPoint> terminationPoint);


        /**
         * Builds object of augmentedNdNode.
         *
         * @return object of augmentedNdNode.
         */
        AugmentedNdNode build();
    }
}
