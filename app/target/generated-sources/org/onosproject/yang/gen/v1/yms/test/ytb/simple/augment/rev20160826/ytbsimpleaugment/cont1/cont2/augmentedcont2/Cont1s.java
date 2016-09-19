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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of cont1s.
 */
public interface Cont1s {

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
     * Returns the attribute cont1s.
     *
     * @return value of cont1s
     */
    org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2.augmentedcont2
            .cont1s.Cont1s cont1s();

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
     * Checks if the passed Cont1s maps the content match query condition.
     *
     * @param cont1s cont1s being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Cont1s processSubtreeFiltering(Cont1s cont1s, boolean isSelectAllSchemaChild);

    /**
     * Builder for cont1s.
     */
    interface Cont1sBuilder {

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute cont1s.
         *
         * @return value of cont1s
         */
        org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2.cont1s.Cont1s cont1s();

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        Cont1sBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of cont1s.
         *
         * @param cont1s value of cont1s
         * @return builder object of cont1s
         */
        Cont1sBuilder cont1s(org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment
            .cont1.cont2.augmentedcont2.cont1s.Cont1s cont1s);


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
         * Builds object of cont1s.
         *
         * @return object of cont1s.
         */
        Cont1s build();
    }
}
