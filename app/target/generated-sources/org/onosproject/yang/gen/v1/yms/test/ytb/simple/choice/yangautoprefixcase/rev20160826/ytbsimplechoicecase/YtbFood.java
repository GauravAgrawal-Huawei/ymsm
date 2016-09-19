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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase;

import java.util.Map;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase
            .ytbfood.YtbSnack;

/**
 * Abstraction of an entity which represents the functionality of ytbFood.
 */
public interface YtbFood {

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
     * Returns the attribute ytbSnack.
     *
     * @return value of ytbSnack
     */
    YtbSnack ytbSnack();

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
     * Checks if the passed YtbFood maps the content match query condition.
     *
     * @param ytbFood ytbFood being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    YtbFood processSubtreeFiltering(YtbFood ytbFood, boolean isSelectAllSchemaChild);

    /**
     * Builder for ytbFood.
     */
    interface YtbFoodBuilder {

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute ytbSnack.
         *
         * @return value of ytbSnack
         */
        YtbSnack ytbSnack();

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        YtbFoodBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of ytbSnack.
         *
         * @param ytbSnack value of ytbSnack
         * @return builder object of ytbSnack
         */
        YtbFoodBuilder ytbSnack(YtbSnack ytbSnack);


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
         * Builds object of ytbFood.
         *
         * @return object of ytbFood.
         */
        YtbFood build();
    }
}
