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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities
            .Cparameters;

/**
 * Represents the implementation of optionalCapabilities.
 */
public class DefaultOptionalCapabilities implements OptionalCapabilities {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected Cparameters cParameters;

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
    public Cparameters cParameters() {
        return cParameters;
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
        return Objects.hash(cParameters);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultOptionalCapabilities) {
            DefaultOptionalCapabilities other = (DefaultOptionalCapabilities) obj;
            return
                 Objects.equals(cParameters, other.cParameters);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("cParameters", cParameters)
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
     * Creates an instance of optionalCapabilities.
     *
     * @param builderObject builder object of optionalCapabilities
     */
    protected DefaultOptionalCapabilities(OptionalCapabilitiesBuilder builderObject) {
        this.cParameters = builderObject.cParameters();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public OptionalCapabilities processSubtreeFiltering(OptionalCapabilities appInstance, boolean
            isSelectAllSchemaChild) {
        OptionalCapabilitiesBuilder subTreeFilteringResultBuilder = new OptionalCapabilitiesBuilder();
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
    private boolean processChildNodesSubTreeFiltering(OptionalCapabilities appInstance, OptionalCapabilitiesBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cParameters()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cParameters() != null) {
                Cparameters result = cParameters.processSubtreeFiltering(appInstance.cParameters(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cParameters(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultOptionalCapabilities.
     */
    protected DefaultOptionalCapabilities() {
    }

    /**
     * Returns the attribute OptionalCapabilitiesBuilder.
     *
     * @return value of OptionalCapabilitiesBuilder
     */
    public static OptionalCapabilitiesBuilder builder() {
        return new OptionalCapabilitiesBuilder();
    }


    /**
     * Represents the builder implementation of optionalCapabilities.
     */
    public static class OptionalCapabilitiesBuilder implements OptionalCapabilities.OptionalCapabilitiesBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected Cparameters cParameters;

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;



        @Override
        public Cparameters cParameters() {
            return cParameters;
        }

        @Override
        public OptionalCapabilitiesBuilder cParameters(Cparameters cParameters) {
            this.cParameters = cParameters;
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
        public OptionalCapabilitiesBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
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
        public OptionalCapabilities build() {
            return new DefaultOptionalCapabilities(this);
        }

        /**
         * Creates an instance of optionalCapabilitiesBuilder.
         */
        public OptionalCapabilitiesBuilder() {
        }
    }
}
