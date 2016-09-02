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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined;

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.Aigp;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.BgpParameters;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.LocalPref;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.MultiExitDisc;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.Origin;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.UnrecognizedAttributes;

/**
 * Abstraction of an entity which represents the functionality of attributes.
 */
public interface Attributes {

    /**
     * Returns the attribute origin.
     *
     * @return value of origin
     */
    Origin origin();

    /**
     * Returns the attribute multiExitDisc.
     *
     * @return value of multiExitDisc
     */
    MultiExitDisc multiExitDisc();

    /**
     * Returns the attribute localPref.
     *
     * @return value of localPref
     */
    LocalPref localPref();

    /**
     * Returns the attribute aigp.
     *
     * @return value of aigp
     */
    Aigp aigp();

    /**
     * Returns the attribute unrecognizedAttributes.
     *
     * @return list of unrecognizedAttributes
     */
    List<UnrecognizedAttributes> unrecognizedAttributes();

    /**
     * Returns the attribute bgpParameters.
     *
     * @return list of bgpParameters
     */
    List<BgpParameters> bgpParameters();

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
     * Checks if the passed Attributes maps the content match query condition.
     *
     * @param attributes attributes being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Attributes processSubtreeFiltering(Attributes attributes, boolean isSelectAllSchemaChild);

    /**
     * Builder for attributes.
     */
    interface AttributesBuilder {


        /**
         * Adds to the list of unrecognizedAttributes.
         *
         * @param value value of unrecognizedAttributes
         * @return builder object of unrecognizedAttributes
         */
        AttributesBuilder addToUnrecognizedAttributes(UnrecognizedAttributes value);

        /**
         * Adds to the list of bgpParameters.
         *
         * @param value value of bgpParameters
         * @return builder object of bgpParameters
         */
        AttributesBuilder addToBgpParameters(BgpParameters value);
        /**
         * Returns the attribute origin.
         *
         * @return value of origin
         */
        Origin origin();

        /**
         * Returns the attribute multiExitDisc.
         *
         * @return value of multiExitDisc
         */
        MultiExitDisc multiExitDisc();

        /**
         * Returns the attribute localPref.
         *
         * @return value of localPref
         */
        LocalPref localPref();

        /**
         * Returns the attribute aigp.
         *
         * @return value of aigp
         */
        Aigp aigp();

        /**
         * Returns the attribute unrecognizedAttributes.
         *
         * @return list of unrecognizedAttributes
         */
        List<UnrecognizedAttributes> unrecognizedAttributes();

        /**
         * Returns the attribute bgpParameters.
         *
         * @return list of bgpParameters
         */
        List<BgpParameters> bgpParameters();

        /**
         * Returns the builder object of origin.
         *
         * @param origin value of origin
         * @return builder object of origin
         */
        AttributesBuilder origin(Origin origin);

        /**
         * Returns the builder object of multiExitDisc.
         *
         * @param multiExitDisc value of multiExitDisc
         * @return builder object of multiExitDisc
         */
        AttributesBuilder multiExitDisc(MultiExitDisc multiExitDisc);

        /**
         * Returns the builder object of localPref.
         *
         * @param localPref value of localPref
         * @return builder object of localPref
         */
        AttributesBuilder localPref(LocalPref localPref);

        /**
         * Returns the builder object of aigp.
         *
         * @param aigp value of aigp
         * @return builder object of aigp
         */
        AttributesBuilder aigp(Aigp aigp);

        /**
         * Returns the builder object of unrecognizedAttributes.
         *
         * @param unrecognizedAttributes list of unrecognizedAttributes
         * @return builder object of unrecognizedAttributes
         */
        AttributesBuilder unrecognizedAttributes(List<UnrecognizedAttributes> unrecognizedAttributes);

        /**
         * Returns the builder object of bgpParameters.
         *
         * @param bgpParameters list of bgpParameters
         * @return builder object of bgpParameters
         */
        AttributesBuilder bgpParameters(List<BgpParameters> bgpParameters);


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
         * Builds object of attributes.
         *
         * @return object of attributes.
         */
        Attributes build();
    }
}
