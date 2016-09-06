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

package org.onosproject.yang.gen.v1.ydt.trading.supervisor.rev20160524;

/**
 * Abstraction of an entity which represents the functionality of tradingsupervisor.
 */
public interface Tradingsupervisor {

    /**
     * Identify the leaf of Tradingsupervisor.
     */
    public enum LeafIdentifier {

        /**
         * Represents supervisor.
         */
        SUPERVISOR(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute supervisor.
     *
     * @return value of supervisor
     */
    String supervisor();

    /**
     * Checks if the passed Tradingsupervisor maps the content match query condition.
     *
     * @param tradingsupervisor tradingsupervisor being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Tradingsupervisor processSubtreeFiltering(Tradingsupervisor tradingsupervisor, boolean isSelectAllSchemaChild);

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
