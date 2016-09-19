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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of cont1s.
 */
public class DefaultCont1s implements Cont1s {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2.cont1s.Cont1s cont1s;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2
            .cont1s.Cont1s cont1s() {
        return cont1s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, cont1s);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultCont1s) {
            DefaultCont1s other = (DefaultCont1s) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(cont1s, other.cont1s);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("cont1s", cont1s)
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
     * Creates an instance of cont1s.
     *
     * @param builderObject builder object of cont1s
     */
    protected DefaultCont1s(Cont1sBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.cont1s = builderObject.cont1s();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Cont1s processSubtreeFiltering(Cont1s appInstance, boolean isSelectAllSchemaChild) {
        Cont1sBuilder subTreeFilteringResultBuilder = new Cont1sBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Cont1s appInstance, Cont1sBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cont1s()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont1s() != null) {
                org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2.cont1s.Cont1s result = cont1s.processSubtreeFiltering(appInstance.cont1s(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont1s(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultCont1s.
     */
    protected DefaultCont1s() {
    }

    /**
     * Returns the attribute Cont1sBuilder.
     *
     * @return value of Cont1sBuilder
     */
    public static Cont1sBuilder builder() {
        return new Cont1sBuilder();
    }


    /**
     * Represents the builder implementation of cont1s.
     */
    public static class Cont1sBuilder implements Cont1s.Cont1sBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2.cont1s.Cont1s cont1s;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2
            .cont1s.Cont1s cont1s() {
            return cont1s;
        }

        @Override
        public Cont1sBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public Cont1sBuilder cont1s(org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826
            .ytbsimpleaugment.cont1.cont2.augmentedcont2
            .cont1s.Cont1s cont1s) {
            this.cont1s = cont1s;
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
        public Cont1s build() {
            return new DefaultCont1s(this);
        }

        /**
         * Creates an instance of cont1Sbuilder.
         */
        public Cont1sBuilder() {
        }
    }
}
