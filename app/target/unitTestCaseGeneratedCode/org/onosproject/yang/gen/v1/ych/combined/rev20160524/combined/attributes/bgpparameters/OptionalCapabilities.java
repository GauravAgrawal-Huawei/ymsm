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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters;

import java.util.Map;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities
            .Cparameters;

/**
 * Abstraction of an entity which represents the functionality of optionalCapabilities.
 */
public interface OptionalCapabilities {

    /**
     * Returns the attribute cParameters.
     *
     * @return value of cParameters
     */
    Cparameters cParameters();

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
     * Checks if the passed OptionalCapabilities maps the content match query condition.
     *
     * @param optionalCapabilities optionalCapabilities being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    OptionalCapabilities processSubtreeFiltering(OptionalCapabilities optionalCapabilities, boolean
            isSelectAllSchemaChild);

    /**
     * Builder for optionalCapabilities.
     */
    interface OptionalCapabilitiesBuilder {

        /**
         * Returns the attribute cParameters.
         *
         * @return value of cParameters
         */
        Cparameters cParameters();

        /**
         * Returns the builder object of cParameters.
         *
         * @param cParameters value of cParameters
         * @return builder object of cParameters
         */
        OptionalCapabilitiesBuilder cParameters(Cparameters cParameters);


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
         * Builds object of optionalCapabilities.
         *
         * @return object of optionalCapabilities.
         */
        OptionalCapabilities build();
    }
}
