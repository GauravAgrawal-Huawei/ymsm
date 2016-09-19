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

package org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection;

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput
            .activatesoftwareimage.output.augmentedrpcoutput.Selection;

/**
 * Abstraction of an entity which represents the functionality of valueIn.
 */
public interface ValueIn extends Selection  {

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
     * Returns the attribute valueIn.
     *
     * @return list of valueIn
     */
    List<org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn> valueIn();

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
     * Checks if the passed Selection maps the content match query condition.
     *
     * @param selection selection being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Selection processSubtreeFiltering(Selection selection, boolean isSelectAllSchemaChild);

    /**
     * Builder for valueIn.
     */
    interface ValueInBuilder {


        /**
         * Adds to the list of valueIn.
         *
         * @param value value of valueIn
         * @return builder object of valueIn
         */
        ValueInBuilder addToValueIn(org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input
            .rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn value);
        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute valueIn.
         *
         * @return list of valueIn
         */
        List<org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn> valueIn();

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        ValueInBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of valueIn.
         *
         * @param valueIn list of valueIn
         * @return builder object of valueIn
         */
        ValueInBuilder valueIn(List<org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input
            .rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein
            .ValueIn> valueIn);


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
         * Builds object of valueIn.
         *
         * @return object of valueIn.
         */
        ValueIn build();
    }
}
