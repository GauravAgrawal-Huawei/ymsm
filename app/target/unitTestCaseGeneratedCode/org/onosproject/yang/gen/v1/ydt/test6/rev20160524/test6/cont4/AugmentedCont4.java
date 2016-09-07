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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4;

import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4.augmentedcont4.Cont6;

/**
 * Abstraction of an entity which represents the functionality of augmentedCont4.
 */
public interface AugmentedCont4 {

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
     * Returns the attribute cont6.
     *
     * @return value of cont6
     */
    Cont6 cont6();

    /**
     * Checks if the passed AugmentedCont4 maps the content match query condition.
     *
     * @param augmentedCont4 augmentedCont4 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    AugmentedCont4 processSubtreeFiltering(AugmentedCont4 augmentedCont4, boolean isSelectAllSchemaChild);

    /**
     * Builder for augmentedCont4.
     */
    interface AugmentedCont4Builder {

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute cont6.
         *
         * @return value of cont6
         */
        Cont6 cont6();

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        AugmentedCont4Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of cont6.
         *
         * @param cont6 value of cont6
         * @return builder object of cont6
         */
        AugmentedCont4Builder cont6(Cont6 cont6);


        /**
         * Builds object of augmentedCont4.
         *
         * @return object of augmentedCont4.
         */
        AugmentedCont4 build();
    }
}
