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

package org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageoutput.OutputList;

/**
 * Represents the implementation of activateSoftwareImageOutput.
 */
public class DefaultActivateSoftwareImageOutput implements ActivateSoftwareImageOutput {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<OutputList> outputList;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<OutputList> outputList() {
        return outputList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, outputList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultActivateSoftwareImageOutput) {
            DefaultActivateSoftwareImageOutput other = (DefaultActivateSoftwareImageOutput) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(outputList, other.outputList);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("outputList", outputList)
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
     * Creates an instance of activateSoftwareImageOutput.
     *
     * @param builderObject builder object of activateSoftwareImageOutput
     */
    protected DefaultActivateSoftwareImageOutput(ActivateSoftwareImageOutputBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.outputList = builderObject.outputList();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public ActivateSoftwareImageOutput processSubtreeFiltering(ActivateSoftwareImageOutput appInstance, boolean
            isSelectAllSchemaChild) {
        ActivateSoftwareImageOutputBuilder subTreeFilteringResultBuilder = new ActivateSoftwareImageOutputBuilder();
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
    private boolean processChildNodesSubTreeFiltering(ActivateSoftwareImageOutput appInstance,
            ActivateSoftwareImageOutputBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (OutputList outputList : appInstance.outputList()) {
                subTreeFilteringResultBuilder.addToOutputList(outputList);
            }
        } else if (outputList() != null) {
            isAnySelectOrContainmentNode = true;
            if (!outputList().isEmpty()) {
                if (appInstance.outputList() != null && !appInstance.outputList().isEmpty()) {
                    for (OutputList outputList : outputList()) {
                        for (OutputList outputList2 : appInstance.outputList()) {
                            OutputList result = outputList.processSubtreeFiltering(outputList2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToOutputList(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.outputList() != null && !appInstance.outputList().isEmpty()) {
                    for (OutputList outputList : appInstance.outputList()) {
                        subTreeFilteringResultBuilder.addToOutputList(outputList);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultActivateSoftwareImageOutput.
     */
    protected DefaultActivateSoftwareImageOutput() {
    }

    /**
     * Returns the attribute ActivateSoftwareImageOutputBuilder.
     *
     * @return value of ActivateSoftwareImageOutputBuilder
     */
    public static ActivateSoftwareImageOutputBuilder builder() {
        return new ActivateSoftwareImageOutputBuilder();
    }


    /**
     * Represents the builder implementation of activateSoftwareImageOutput.
     */
    public static class ActivateSoftwareImageOutputBuilder implements ActivateSoftwareImageOutput
            .ActivateSoftwareImageOutputBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<OutputList> outputList;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<OutputList> outputList() {
            return outputList;
        }

        @Override
        public ActivateSoftwareImageOutputBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public ActivateSoftwareImageOutputBuilder outputList(List<OutputList> outputList) {
            this.outputList = outputList;
            return this;
        }

        @Override
        public ActivateSoftwareImageOutputBuilder addToOutputList(OutputList value) {
            if (outputList() == null) {
                outputList(new ArrayList<>());
            }
            outputList().add(value);
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
        public ActivateSoftwareImageOutput build() {
            return new DefaultActivateSoftwareImageOutput(this);
        }

        /**
         * Creates an instance of activateSoftwareImageOutputBuilder.
         */
        public ActivateSoftwareImageOutputBuilder() {
        }
    }
}
