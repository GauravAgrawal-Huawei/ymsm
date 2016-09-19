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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageinput
            .YangAutoPrefixFinal;

/**
 * Represents the implementation of activateSoftwareImageInput.
 */
public class DefaultActivateSoftwareImageInput implements ActivateSoftwareImageInput {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected YangAutoPrefixFinal yangAutoPrefixFinal;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public YangAutoPrefixFinal yangAutoPrefixFinal() {
        return yangAutoPrefixFinal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, yangAutoPrefixFinal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultActivateSoftwareImageInput) {
            DefaultActivateSoftwareImageInput other = (DefaultActivateSoftwareImageInput) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(yangAutoPrefixFinal, other.yangAutoPrefixFinal);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("yangAutoPrefixFinal", yangAutoPrefixFinal)
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
     * Creates an instance of activateSoftwareImageInput.
     *
     * @param builderObject builder object of activateSoftwareImageInput
     */
    protected DefaultActivateSoftwareImageInput(ActivateSoftwareImageInputBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.yangAutoPrefixFinal = builderObject.yangAutoPrefixFinal();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public ActivateSoftwareImageInput processSubtreeFiltering(ActivateSoftwareImageInput appInstance, boolean
            isSelectAllSchemaChild) {
        ActivateSoftwareImageInputBuilder subTreeFilteringResultBuilder = new ActivateSoftwareImageInputBuilder();
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
    private boolean processChildNodesSubTreeFiltering(ActivateSoftwareImageInput appInstance,
            ActivateSoftwareImageInputBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (yangAutoPrefixFinal()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.yangAutoPrefixFinal() != null) {
                YangAutoPrefixFinal result = yangAutoPrefixFinal.processSubtreeFiltering(appInstance
            .yangAutoPrefixFinal(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.yangAutoPrefixFinal(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultActivateSoftwareImageInput.
     */
    protected DefaultActivateSoftwareImageInput() {
    }

    /**
     * Returns the attribute ActivateSoftwareImageInputBuilder.
     *
     * @return value of ActivateSoftwareImageInputBuilder
     */
    public static ActivateSoftwareImageInputBuilder builder() {
        return new ActivateSoftwareImageInputBuilder();
    }


    /**
     * Represents the builder implementation of activateSoftwareImageInput.
     */
    public static class ActivateSoftwareImageInputBuilder implements ActivateSoftwareImageInput
            .ActivateSoftwareImageInputBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected YangAutoPrefixFinal yangAutoPrefixFinal;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public YangAutoPrefixFinal yangAutoPrefixFinal() {
            return yangAutoPrefixFinal;
        }

        @Override
        public ActivateSoftwareImageInputBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public ActivateSoftwareImageInputBuilder yangAutoPrefixFinal(YangAutoPrefixFinal yangAutoPrefixFinal) {
            this.yangAutoPrefixFinal = yangAutoPrefixFinal;
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
        public ActivateSoftwareImageInput build() {
            return new DefaultActivateSoftwareImageInput(this);
        }

        /**
         * Creates an instance of activateSoftwareImageInputBuilder.
         */
        public ActivateSoftwareImageInputBuilder() {
        }
    }
}
