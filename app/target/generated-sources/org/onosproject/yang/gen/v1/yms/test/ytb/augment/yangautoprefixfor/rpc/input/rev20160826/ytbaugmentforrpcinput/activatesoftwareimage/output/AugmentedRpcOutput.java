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

package org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output;

import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput
            .activatesoftwareimage.output.augmentedrpcoutput.Selection;

/**
 * Abstraction of an entity which represents the functionality of augmentedRpcOutput.
 */
public interface AugmentedRpcOutput {

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
     * Returns the attribute selection.
     *
     * @return value of selection
     */
    Selection selection();

    /**
     * Checks if the passed AugmentedRpcOutput maps the content match query condition.
     *
     * @param augmentedRpcOutput augmentedRpcOutput being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    AugmentedRpcOutput processSubtreeFiltering(AugmentedRpcOutput augmentedRpcOutput, boolean isSelectAllSchemaChild);

    /**
     * Builder for augmentedRpcOutput.
     */
    interface AugmentedRpcOutputBuilder {

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute selection.
         *
         * @return value of selection
         */
        Selection selection();

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        AugmentedRpcOutputBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of selection.
         *
         * @param selection value of selection
         * @return builder object of selection
         */
        AugmentedRpcOutputBuilder selection(Selection selection);


        /**
         * Builds object of augmentedRpcOutput.
         *
         * @return object of augmentedRpcOutput.
         */
        AugmentedRpcOutput build();
    }
}
