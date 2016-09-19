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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4.cont6;

import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4.cont6.augmentedcont6.Cont7;

/**
 * Abstraction of an entity which represents the functionality of augmentedCont6.
 */
public interface AugmentedCont6 {

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
     * Returns the attribute cont7.
     *
     * @return value of cont7
     */
    Cont7 cont7();

    /**
     * Checks if the passed AugmentedCont6 maps the content match query condition.
     *
     * @param augmentedCont6 augmentedCont6 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    AugmentedCont6 processSubtreeFiltering(AugmentedCont6 augmentedCont6, boolean isSelectAllSchemaChild);

    /**
     * Builder for augmentedCont6.
     */
    interface AugmentedCont6Builder {

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute cont7.
         *
         * @return value of cont7
         */
        Cont7 cont7();

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        AugmentedCont6Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of cont7.
         *
         * @param cont7 value of cont7
         * @return builder object of cont7
         */
        AugmentedCont6Builder cont7(Cont7 cont7);


        /**
         * Builds object of augmentedCont6.
         *
         * @return object of augmentedCont6.
         */
        AugmentedCont6 build();
    }
}
