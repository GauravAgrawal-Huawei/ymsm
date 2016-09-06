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

package org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826
            .ytbmultinotificationwithcontainer;

import java.util.Map;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826
            .ytbmultinotificationwithcontainer.fortesta.Ytbnot;

/**
 * Abstraction of an entity which represents the functionality of fortesta.
 */
public interface Fortesta {

    /**
     * Returns the attribute ytbnot.
     *
     * @return value of ytbnot
     */
    Ytbnot ytbnot();

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
     * Checks if the passed Fortesta maps the content match query condition.
     *
     * @param fortesta fortesta being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Fortesta processSubtreeFiltering(Fortesta fortesta, boolean isSelectAllSchemaChild);

    /**
     * Builder for fortesta.
     */
    interface FortestaBuilder {

        /**
         * Returns the attribute ytbnot.
         *
         * @return value of ytbnot
         */
        Ytbnot ytbnot();

        /**
         * Returns the builder object of ytbnot.
         *
         * @param ytbnot value of ytbnot
         * @return builder object of ytbnot
         */
        FortestaBuilder ytbnot(Ytbnot ytbnot);


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
         * Builds object of fortesta.
         *
         * @return object of fortesta.
         */
        Fortesta build();
    }
}
