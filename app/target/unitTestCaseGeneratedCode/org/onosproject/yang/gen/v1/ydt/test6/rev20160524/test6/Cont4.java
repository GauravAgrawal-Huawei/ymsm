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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6;

import java.util.Map;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont4.Cont5;

/**
 * Abstraction of an entity which represents the functionality of cont4.
 */
public interface Cont4 {

    /**
     * Returns the attribute cont5.
     *
     * @return value of cont5
     */
    Cont5 cont5();

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
     * Checks if the passed Cont4 maps the content match query condition.
     *
     * @param cont4 cont4 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Cont4 processSubtreeFiltering(Cont4 cont4, boolean isSelectAllSchemaChild);

    /**
     * Builder for cont4.
     */
    interface Cont4Builder {

        /**
         * Returns the attribute cont5.
         *
         * @return value of cont5
         */
        Cont5 cont5();

        /**
         * Returns the builder object of cont5.
         *
         * @param cont5 value of cont5
         * @return builder object of cont5
         */
        Cont4Builder cont5(Cont5 cont5);


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
         * Builds object of cont4.
         *
         * @return object of cont4.
         */
        Cont4 build();
    }
}
