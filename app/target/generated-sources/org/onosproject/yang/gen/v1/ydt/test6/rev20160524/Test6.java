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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524;

import java.util.List;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.Cont1;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.Cont4;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.List2;

/**
 * Abstraction of an entity which represents the functionality of test6.
 */
public interface Test6 {

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
     * Identify the leaf of Test6.
     */
    public enum LeafIdentifier {

        /**
         * Represents leaf10.
         */
        LEAF10(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute leaf10.
     *
     * @return value of leaf10
     */
    String leaf10();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute cont1.
     *
     * @return value of cont1
     */
    Cont1 cont1();

    /**
     * Returns the attribute list2.
     *
     * @return list of list2
     */
    List<List2> list2();

    /**
     * Returns the attribute cont4.
     *
     * @return value of cont4
     */
    Cont4 cont4();

    /**
     * Checks if the passed Test6 maps the content match query condition.
     *
     * @param test6 test6 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Test6 processSubtreeFiltering(Test6 test6, boolean isSelectAllSchemaChild);

    /**
     * Checks if the leaf value is set.
     *
     * @param leaf leaf whose value status needs to checked
     * @return result of leaf value set in object
     */
    boolean isLeafValueSet(LeafIdentifier leaf);

    /**
     * Checks if the leaf is set to be a selected leaf.
     *
     * @param leaf if leaf needs to be selected
     * @return result of leaf value set in object
     */
    boolean isSelectLeaf(LeafIdentifier leaf);
}
