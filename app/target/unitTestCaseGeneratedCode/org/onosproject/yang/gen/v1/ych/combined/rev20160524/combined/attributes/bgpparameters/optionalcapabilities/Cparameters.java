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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities;

import java.util.Map;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities
            .cparameters.As4BytesCapability;

/**
 * Abstraction of an entity which represents the functionality of cparameters.
 */
public interface Cparameters {

    /**
     * Returns the attribute as4BytesCapability.
     *
     * @return value of as4BytesCapability
     */
    As4BytesCapability as4BytesCapability();

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
     * Checks if the passed Cparameters maps the content match query condition.
     *
     * @param cparameters cparameters being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Cparameters processSubtreeFiltering(Cparameters cparameters, boolean isSelectAllSchemaChild);

    /**
     * Builder for cparameters.
     */
    interface CparametersBuilder {

        /**
         * Returns the attribute as4BytesCapability.
         *
         * @return value of as4BytesCapability
         */
        As4BytesCapability as4BytesCapability();

        /**
         * Returns the builder object of as4BytesCapability.
         *
         * @param as4BytesCapability value of as4BytesCapability
         * @return builder object of as4BytesCapability
         */
        CparametersBuilder as4BytesCapability(As4BytesCapability as4BytesCapability);


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
         * Builds object of cparameters.
         *
         * @return object of cparameters.
         */
        Cparameters build();
    }
}
