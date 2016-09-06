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

import java.util.Map;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.aigp.AigpTlv;

/**
 * Abstraction of an entity which represents the functionality of aigp.
 */
public interface Aigp {

    /**
     * Returns the attribute aigpTlv.
     *
     * @return value of aigpTlv
     */
    AigpTlv aigpTlv();

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
     * Checks if the passed Aigp maps the content match query condition.
     *
     * @param aigp aigp being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Aigp processSubtreeFiltering(Aigp aigp, boolean isSelectAllSchemaChild);

    /**
     * Builder for aigp.
     */
    interface AigpBuilder {

        /**
         * Returns the attribute aigpTlv.
         *
         * @return value of aigpTlv
         */
        AigpTlv aigpTlv();

        /**
         * Returns the builder object of aigpTlv.
         *
         * @param aigpTlv value of aigpTlv
         * @return builder object of aigpTlv
         */
        AigpBuilder aigpTlv(AigpTlv aigpTlv);


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
         * Builds object of aigp.
         *
         * @return object of aigp.
         */
        Aigp build();
    }
}