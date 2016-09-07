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

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.Aigp;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.BgpParameters;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.LocalPref;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.MultiExitDisc;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.Origin;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.UnrecognizedAttributes;

/**
 * Represents the implementation of attributes.
 */
public class DefaultAttributes implements Attributes {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected Origin origin;
    protected MultiExitDisc multiExitDisc;
    protected LocalPref localPref;
    protected Aigp aigp;
    protected List<UnrecognizedAttributes> unrecognizedAttributes = new ArrayList<>();
    protected List<BgpParameters> bgpParameters = new ArrayList<>();

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
     * Specify the node specific operation in protocols like NETCONF.
     * Applicable in protocol edit operation, will be ignored in query operation
     */
    private OnosYangNodeOperationType onosYangNodeOperationType;


    @Override
    public Origin origin() {
        return origin;
    }

    @Override
    public MultiExitDisc multiExitDisc() {
        return multiExitDisc;
    }

    @Override
    public LocalPref localPref() {
        return localPref;
    }

    @Override
    public Aigp aigp() {
        return aigp;
    }

    @Override
    public List<UnrecognizedAttributes> unrecognizedAttributes() {
        return unrecognizedAttributes;
    }

    @Override
    public List<BgpParameters> bgpParameters() {
        return bgpParameters;
    }
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }


    @Override
    public int hashCode() {
        return Objects.hash(origin, multiExitDisc, localPref, aigp, unrecognizedAttributes, bgpParameters);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultAttributes) {
            DefaultAttributes other = (DefaultAttributes) obj;
            return
                 Objects.equals(origin, other.origin) &&
                 Objects.equals(multiExitDisc, other.multiExitDisc) &&
                 Objects.equals(localPref, other.localPref) &&
                 Objects.equals(aigp, other.aigp) &&
                 Objects.equals(unrecognizedAttributes, other.unrecognizedAttributes) &&
                 Objects.equals(bgpParameters, other.bgpParameters);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("origin", origin)
            .add("multiExitDisc", multiExitDisc)
            .add("localPref", localPref)
            .add("aigp", aigp)
            .add("unrecognizedAttributes", unrecognizedAttributes)
            .add("bgpParameters", bgpParameters)
            .toString();
    }

    @Override
    public Object yangAugmentedInfo(Class classObject) {
        return yangAugmentedInfoMap.get(classObject);
    }

    @Override
    public Map<Class<?>, Object> yangAugmentedInfoMap() {
        return yangAugmentedInfoMap;
    }

    /**
     * Creates an instance of attributes.
     *
     * @param builderObject builder object of attributes
     */
    protected DefaultAttributes(AttributesBuilder builderObject) {
        this.origin = builderObject.origin();
        this.multiExitDisc = builderObject.multiExitDisc();
        this.localPref = builderObject.localPref();
        this.aigp = builderObject.aigp();
        this.unrecognizedAttributes = builderObject.unrecognizedAttributes();
        this.bgpParameters = builderObject.bgpParameters();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Attributes processSubtreeFiltering(Attributes appInstance, boolean isSelectAllSchemaChild) {
        AttributesBuilder subTreeFilteringResultBuilder = new AttributesBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        for (Object yangAugmentedInfo : this.yangAugmentedInfoMap().values()) {
            Object yangAugmentedOpParamInfo = appInstance.yangAugmentedInfo(yangAugmentedInfo.getClass());
            Object processSubtreeFiltering;
            try {
                Class<?>[] interfaces = yangAugmentedInfo.getClass().getInterfaces();
                processSubtreeFiltering = yangAugmentedInfo.getClass()
                    .getMethod("processSubtreeFiltering", interfaces[0]).invoke(yangAugmentedInfo,
                        yangAugmentedOpParamInfo);
                if (processSubtreeFiltering != null) {
                    subTreeFilteringResultBuilder
            .addYangAugmentedInfo(processSubtreeFiltering, processSubtreeFiltering.getClass());
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                continue;
            }
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processChildNodesSubTreeFiltering(Attributes appInstance, AttributesBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (origin()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.origin() != null) {
                Origin result = origin.processSubtreeFiltering(appInstance.origin(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.origin(result);
                }
            }
        }

        if (multiExitDisc()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.multiExitDisc() != null) {
                MultiExitDisc result = multiExitDisc.processSubtreeFiltering(appInstance.multiExitDisc(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.multiExitDisc(result);
                }
            }
        }

        if (localPref()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.localPref() != null) {
                LocalPref result = localPref.processSubtreeFiltering(appInstance.localPref(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.localPref(result);
                }
            }
        }

        if (aigp()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.aigp() != null) {
                Aigp result = aigp.processSubtreeFiltering(appInstance.aigp(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.aigp(result);
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (UnrecognizedAttributes unrecognizedAttributes : appInstance.unrecognizedAttributes()) {
                subTreeFilteringResultBuilder.addToUnrecognizedAttributes(unrecognizedAttributes);
            }
        } else if (unrecognizedAttributes() != null) {
            isAnySelectOrContainmentNode = true;
            if (!unrecognizedAttributes().isEmpty()) {
                if (appInstance.unrecognizedAttributes() != null && !appInstance.unrecognizedAttributes().isEmpty()) {
                    for (UnrecognizedAttributes unrecognizedAttributes : unrecognizedAttributes()) {
                        for (UnrecognizedAttributes unrecognizedAttributes2 : appInstance.unrecognizedAttributes()) {
                            UnrecognizedAttributes result = unrecognizedAttributes
            .processSubtreeFiltering(unrecognizedAttributes2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToUnrecognizedAttributes(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.unrecognizedAttributes() != null && !appInstance.unrecognizedAttributes().isEmpty()) {
                    for (UnrecognizedAttributes unrecognizedAttributes : appInstance.unrecognizedAttributes()) {
                        subTreeFilteringResultBuilder.addToUnrecognizedAttributes(unrecognizedAttributes);
                    }
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (BgpParameters bgpParameters : appInstance.bgpParameters()) {
                subTreeFilteringResultBuilder.addToBgpParameters(bgpParameters);
            }
        } else if (bgpParameters() != null) {
            isAnySelectOrContainmentNode = true;
            if (!bgpParameters().isEmpty()) {
                if (appInstance.bgpParameters() != null && !appInstance.bgpParameters().isEmpty()) {
                    for (BgpParameters bgpParameters : bgpParameters()) {
                        for (BgpParameters bgpParameters2 : appInstance.bgpParameters()) {
                            BgpParameters result = bgpParameters.processSubtreeFiltering(bgpParameters2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToBgpParameters(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.bgpParameters() != null && !appInstance.bgpParameters().isEmpty()) {
                    for (BgpParameters bgpParameters : appInstance.bgpParameters()) {
                        subTreeFilteringResultBuilder.addToBgpParameters(bgpParameters);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultAttributes.
     */
    protected DefaultAttributes() {
    }

    /**
     * Returns the attribute AttributesBuilder.
     *
     * @return value of AttributesBuilder
     */
    public static AttributesBuilder builder() {
        return new AttributesBuilder();
    }


    /**
     * Represents the builder implementation of attributes.
     */
    public static class AttributesBuilder implements Attributes.AttributesBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected Origin origin;
        protected MultiExitDisc multiExitDisc;
        protected LocalPref localPref;
        protected Aigp aigp;
        protected List<UnrecognizedAttributes> unrecognizedAttributes = new ArrayList<>();
        protected List<BgpParameters> bgpParameters = new ArrayList<>();

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;



        @Override
        public Origin origin() {
            return origin;
        }

        @Override
        public MultiExitDisc multiExitDisc() {
            return multiExitDisc;
        }

        @Override
        public LocalPref localPref() {
            return localPref;
        }

        @Override
        public Aigp aigp() {
            return aigp;
        }

        @Override
        public List<UnrecognizedAttributes> unrecognizedAttributes() {
            return unrecognizedAttributes;
        }

        @Override
        public List<BgpParameters> bgpParameters() {
            return bgpParameters;
        }

        @Override
        public AttributesBuilder origin(Origin origin) {
            this.origin = origin;
            return this;
        }

        @Override
        public AttributesBuilder multiExitDisc(MultiExitDisc multiExitDisc) {
            this.multiExitDisc = multiExitDisc;
            return this;
        }

        @Override
        public AttributesBuilder localPref(LocalPref localPref) {
            this.localPref = localPref;
            return this;
        }

        @Override
        public AttributesBuilder aigp(Aigp aigp) {
            this.aigp = aigp;
            return this;
        }

        @Override
        public AttributesBuilder unrecognizedAttributes(List<UnrecognizedAttributes> unrecognizedAttributes) {
            this.unrecognizedAttributes = unrecognizedAttributes;
            return this;
        }

        @Override
        public AttributesBuilder bgpParameters(List<BgpParameters> bgpParameters) {
            this.bgpParameters = bgpParameters;
            return this;
        }

        @Override
        public AttributesBuilder addToUnrecognizedAttributes(UnrecognizedAttributes value) {
            unrecognizedAttributes().add(value);
            return this;
        }

        @Override
        public AttributesBuilder addToBgpParameters(BgpParameters value) {
            bgpParameters().add(value);
            return this;
        }
        /**
         * Returns the onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        /**
         * Set node operation type.
         *
         * @param onosYangNodeOperationType node operation type
         * @return builder object for node operation type
         */
        public AttributesBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public void addYangAugmentedInfo(Object value, Class classObject) {
            yangAugmentedInfoMap.put(classObject, value);
        }

        @Override
        public Object yangAugmentedInfo(Class classObject) {
            return yangAugmentedInfoMap.get(classObject);
        }

        @Override
        public Map<Class<?>, Object> yangAugmentedInfoMap() {
            return yangAugmentedInfoMap;
        }
        @Override
        public Attributes build() {
            return new DefaultAttributes(this);
        }

        /**
         * Creates an instance of attributesBuilder.
         */
        public AttributesBuilder() {
        }
    }
}
