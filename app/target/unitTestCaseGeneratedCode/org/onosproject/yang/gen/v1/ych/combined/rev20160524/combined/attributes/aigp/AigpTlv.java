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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.aigp;

import java.util.Map;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.Metric;

/**
 * Abstraction of an entity which represents the functionality of aigpTlv.
 */
public interface AigpTlv {

    /**
     * Identify the leaf of AigpTlv.
     */
    public enum LeafIdentifier {

        /**
         * Represents metric.
         */
        METRIC(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute metric.
     *
     * @return value of metric
     */
    Metric metric();

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
     * Checks if the passed AigpTlv maps the content match query condition.
     *
     * @param aigpTlv aigpTlv being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    AigpTlv processSubtreeFiltering(AigpTlv aigpTlv, boolean isSelectAllSchemaChild);

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

    /**
     * Builder for aigpTlv.
     */
    interface AigpTlvBuilder {

        /**
         * Returns the attribute metric.
         *
         * @return value of metric
         */
        Metric metric();

        /**
         * Returns the builder object of metric.
         *
         * @param metric value of metric
         * @return builder object of metric
         */
        AigpTlvBuilder metric(Metric metric);


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
         * Set a leaf to be selected.
         *
         * @param leaf leaf needs to be selected
         * @return builder object for select leaf
         */
        AigpTlvBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of aigpTlv.
         *
         * @return object of aigpTlv.
         */
        AigpTlv build();
    }
}
