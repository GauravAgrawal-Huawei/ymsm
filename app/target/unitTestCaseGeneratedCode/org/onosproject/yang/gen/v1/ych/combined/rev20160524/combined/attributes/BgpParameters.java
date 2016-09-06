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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes;

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.OptionalCapabilities;

/**
 * Abstraction of an entity which represents the functionality of bgpParameters.
 */
public interface BgpParameters {

    /**
     * Returns the attribute optionalCapabilities.
     *
     * @return list of optionalCapabilities
     */
    List<OptionalCapabilities> optionalCapabilities();

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
     * Checks if the passed BgpParameters maps the content match query condition.
     *
     * @param bgpParameters bgpParameters being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    BgpParameters processSubtreeFiltering(BgpParameters bgpParameters, boolean isSelectAllSchemaChild);

    /**
     * Builder for bgpParameters.
     */
    interface BgpParametersBuilder {


        /**
         * Adds to the list of optionalCapabilities.
         *
         * @param value value of optionalCapabilities
         * @return builder object of optionalCapabilities
         */
        BgpParametersBuilder addToOptionalCapabilities(OptionalCapabilities value);
        /**
         * Returns the attribute optionalCapabilities.
         *
         * @return list of optionalCapabilities
         */
        List<OptionalCapabilities> optionalCapabilities();

        /**
         * Returns the builder object of optionalCapabilities.
         *
         * @param optionalCapabilities list of optionalCapabilities
         * @return builder object of optionalCapabilities
         */
        BgpParametersBuilder optionalCapabilities(List<OptionalCapabilities> optionalCapabilities);


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
         * Builds object of bgpParameters.
         *
         * @return object of bgpParameters.
         */
        BgpParameters build();
    }
}
