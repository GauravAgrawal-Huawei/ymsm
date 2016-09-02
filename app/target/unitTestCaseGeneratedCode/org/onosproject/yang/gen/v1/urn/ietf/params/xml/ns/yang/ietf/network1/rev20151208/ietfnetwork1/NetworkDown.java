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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208.ietfnetwork1;

import java.util.Map;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208.ietfnetwork1.networkdown
            .Networks;

/**
 * Abstraction of an entity which represents the functionality of networkDown.
 */
public interface NetworkDown {

    /**
     * Returns the attribute networks.
     *
     * @return value of networks
     */
    Networks networks();

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
     * Checks if the passed NetworkDown maps the content match query condition.
     *
     * @param networkDown networkDown being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    NetworkDown processSubtreeFiltering(NetworkDown networkDown, boolean isSelectAllSchemaChild);

    /**
     * Builder for networkDown.
     */
    interface NetworkDownBuilder {

        /**
         * Returns the attribute networks.
         *
         * @return value of networks
         */
        Networks networks();

        /**
         * Returns the builder object of networks.
         *
         * @param networks value of networks
         * @return builder object of networks
         */
        NetworkDownBuilder networks(Networks networks);


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
         * Builds object of networkDown.
         *
         * @return object of networkDown.
         */
        NetworkDown build();
    }
}
