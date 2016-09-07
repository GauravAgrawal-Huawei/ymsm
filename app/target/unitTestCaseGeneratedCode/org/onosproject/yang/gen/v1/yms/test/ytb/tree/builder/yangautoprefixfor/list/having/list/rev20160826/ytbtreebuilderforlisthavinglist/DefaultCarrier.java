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

package org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.carrier.Multiplexes;

/**
 * Represents the implementation of carrier.
 */
public class DefaultCarrier implements Carrier {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<Multiplexes> multiplexes;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<Multiplexes> multiplexes() {
        return multiplexes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, multiplexes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultCarrier) {
            DefaultCarrier other = (DefaultCarrier) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(multiplexes, other.multiplexes);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("multiplexes", multiplexes)
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
     * Creates an instance of carrier.
     *
     * @param builderObject builder object of carrier
     */
    protected DefaultCarrier(CarrierBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.multiplexes = builderObject.multiplexes();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Carrier processSubtreeFiltering(Carrier appInstance, boolean isSelectAllSchemaChild) {
        CarrierBuilder subTreeFilteringResultBuilder = new CarrierBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Carrier appInstance, CarrierBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Multiplexes multiplexes : appInstance.multiplexes()) {
                subTreeFilteringResultBuilder.addToMultiplexes(multiplexes);
            }
        } else if (multiplexes() != null) {
            isAnySelectOrContainmentNode = true;
            if (!multiplexes().isEmpty()) {
                if (appInstance.multiplexes() != null && !appInstance.multiplexes().isEmpty()) {
                    for (Multiplexes multiplexes : multiplexes()) {
                        for (Multiplexes multiplexes2 : appInstance.multiplexes()) {
                            Multiplexes result = multiplexes.processSubtreeFiltering(multiplexes2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToMultiplexes(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.multiplexes() != null && !appInstance.multiplexes().isEmpty()) {
                    for (Multiplexes multiplexes : appInstance.multiplexes()) {
                        subTreeFilteringResultBuilder.addToMultiplexes(multiplexes);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultCarrier.
     */
    protected DefaultCarrier() {
    }

    /**
     * Returns the attribute CarrierBuilder.
     *
     * @return value of CarrierBuilder
     */
    public static CarrierBuilder builder() {
        return new CarrierBuilder();
    }


    /**
     * Represents the builder implementation of carrier.
     */
    public static class CarrierBuilder implements Carrier.CarrierBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<Multiplexes> multiplexes;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<Multiplexes> multiplexes() {
            return multiplexes;
        }

        @Override
        public CarrierBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public CarrierBuilder multiplexes(List<Multiplexes> multiplexes) {
            this.multiplexes = multiplexes;
            return this;
        }

        @Override
        public CarrierBuilder addToMultiplexes(Multiplexes value) {
            if (multiplexes() == null) {
                multiplexes(new ArrayList<>());
            }
            multiplexes().add(value);
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
        public Carrier build() {
            return new DefaultCarrier(this);
        }

        /**
         * Creates an instance of carrierBuilder.
         */
        public CarrierBuilder() {
        }
    }
}
