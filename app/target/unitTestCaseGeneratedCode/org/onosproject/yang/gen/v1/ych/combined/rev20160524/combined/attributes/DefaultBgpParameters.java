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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.OptionalCapabilities;

/**
 * Represents the implementation of bgpParameters.
 */
public class DefaultBgpParameters implements BgpParameters {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
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


    protected List<OptionalCapabilities> optionalCapabilities = new ArrayList<>();
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }


    @Override
    public List<OptionalCapabilities> optionalCapabilities() {
        return optionalCapabilities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionalCapabilities);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultBgpParameters) {
            DefaultBgpParameters other = (DefaultBgpParameters) obj;
            return
                 Objects.equals(optionalCapabilities, other.optionalCapabilities);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("optionalCapabilities", optionalCapabilities)
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
     * Creates an instance of bgpParameters.
     *
     * @param builderObject builder object of bgpParameters
     */
    protected DefaultBgpParameters(BgpParametersBuilder builderObject) {
        this.optionalCapabilities = builderObject.optionalCapabilities();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public BgpParameters processSubtreeFiltering(BgpParameters appInstance, boolean isSelectAllSchemaChild) {
        BgpParametersBuilder subTreeFilteringResultBuilder = new BgpParametersBuilder();
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
    private boolean processChildNodesSubTreeFiltering(BgpParameters appInstance, BgpParametersBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (OptionalCapabilities optionalCapabilities : appInstance.optionalCapabilities()) {
                subTreeFilteringResultBuilder.addToOptionalCapabilities(optionalCapabilities);
            }
        } else if (optionalCapabilities() != null) {
            isAnySelectOrContainmentNode = true;
            if (!optionalCapabilities().isEmpty()) {
                if (appInstance.optionalCapabilities() != null && !appInstance.optionalCapabilities().isEmpty()) {
                    for (OptionalCapabilities optionalCapabilities : optionalCapabilities()) {
                        for (OptionalCapabilities optionalCapabilities2 : appInstance.optionalCapabilities()) {
                            OptionalCapabilities result = optionalCapabilities
            .processSubtreeFiltering(optionalCapabilities2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToOptionalCapabilities(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.optionalCapabilities() != null && !appInstance.optionalCapabilities().isEmpty()) {
                    for (OptionalCapabilities optionalCapabilities : appInstance.optionalCapabilities()) {
                        subTreeFilteringResultBuilder.addToOptionalCapabilities(optionalCapabilities);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultBgpParameters.
     */
    protected DefaultBgpParameters() {
    }

    /**
     * Returns the attribute BgpParametersBuilder.
     *
     * @return value of BgpParametersBuilder
     */
    public static BgpParametersBuilder builder() {
        return new BgpParametersBuilder();
    }


    /**
     * Represents the builder implementation of bgpParameters.
     */
    public static class BgpParametersBuilder implements BgpParameters.BgpParametersBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected List<OptionalCapabilities> optionalCapabilities = new ArrayList<>();

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
        public BgpParametersBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public List<OptionalCapabilities> optionalCapabilities() {
            return optionalCapabilities;
        }

        @Override
        public BgpParametersBuilder optionalCapabilities(List<OptionalCapabilities> optionalCapabilities) {
            this.optionalCapabilities = optionalCapabilities;
            return this;
        }

        @Override
        public BgpParametersBuilder addToOptionalCapabilities(OptionalCapabilities value) {
            optionalCapabilities().add(value);
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
        public BgpParameters build() {
            return new DefaultBgpParameters(this);
        }

        /**
         * Creates an instance of bgpParametersBuilder.
         */
        public BgpParametersBuilder() {
        }
    }
}
