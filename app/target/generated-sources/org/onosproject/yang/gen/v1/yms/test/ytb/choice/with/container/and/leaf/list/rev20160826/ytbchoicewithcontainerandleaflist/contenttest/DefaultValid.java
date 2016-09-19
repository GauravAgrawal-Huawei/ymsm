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

package org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.contenttest;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.ContentTest;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.contenttest.valid.Validlistincase;

/**
 * Represents the implementation of valid.
 */
public class DefaultValid implements Valid {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<Validlistincase> validlistincase;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<Validlistincase> validlistincase() {
        return validlistincase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, validlistincase);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultValid) {
            DefaultValid other = (DefaultValid) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(validlistincase, other.validlistincase);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("validlistincase", validlistincase)
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
     * Creates an instance of valid.
     *
     * @param builderObject builder object of valid
     */
    protected DefaultValid(ValidBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.validlistincase = builderObject.validlistincase();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public ContentTest processSubtreeFiltering(ContentTest instance, boolean isSelectAllSchemaChild) {
        ValidBuilder subTreeFilteringResultBuilder = new ValidBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        Valid appInstance = (Valid) instance;
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
    private boolean processChildNodesSubTreeFiltering(ContentTest instance, ValidBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        Valid appInstance = (Valid) instance;
        if (isSelectAllSchemaChild) {
            for (Validlistincase validlistincase : appInstance.validlistincase()) {
                subTreeFilteringResultBuilder.addToValidlistincase(validlistincase);
            }
        } else if (validlistincase() != null) {
            isAnySelectOrContainmentNode = true;
            if (!validlistincase().isEmpty()) {
                if (appInstance.validlistincase() != null && !appInstance.validlistincase().isEmpty()) {
                    for (Validlistincase validlistincase : validlistincase()) {
                        for (Validlistincase validlistincase2 : appInstance.validlistincase()) {
                            Validlistincase result = validlistincase.processSubtreeFiltering(validlistincase2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToValidlistincase(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.validlistincase() != null && !appInstance.validlistincase().isEmpty()) {
                    for (Validlistincase validlistincase : appInstance.validlistincase()) {
                        subTreeFilteringResultBuilder.addToValidlistincase(validlistincase);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultValid.
     */
    protected DefaultValid() {
    }

    /**
     * Returns the attribute ValidBuilder.
     *
     * @return value of ValidBuilder
     */
    public static ValidBuilder builder() {
        return new ValidBuilder();
    }


    /**
     * Represents the builder implementation of valid.
     */
    public static class ValidBuilder implements Valid.ValidBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<Validlistincase> validlistincase;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<Validlistincase> validlistincase() {
            return validlistincase;
        }

        @Override
        public ValidBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public ValidBuilder validlistincase(List<Validlistincase> validlistincase) {
            this.validlistincase = validlistincase;
            return this;
        }

        @Override
        public ValidBuilder addToValidlistincase(Validlistincase value) {
            if (validlistincase() == null) {
                validlistincase(new ArrayList<>());
            }
            validlistincase().add(value);
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
        public Valid build() {
            return new DefaultValid(this);
        }

        /**
         * Creates an instance of validBuilder.
         */
        public ValidBuilder() {
        }
    }
}
