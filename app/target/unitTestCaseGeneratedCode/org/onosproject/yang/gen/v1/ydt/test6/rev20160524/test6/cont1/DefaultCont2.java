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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont1;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont1.cont2.Cont3;

/**
 * Represents the implementation of cont2.
 */
public class DefaultCont2 implements Cont2 {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected Cont3 cont3;

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
    public Cont3 cont3() {
        return cont3;
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
        return Objects.hash(cont3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultCont2) {
            DefaultCont2 other = (DefaultCont2) obj;
            return
                 Objects.equals(cont3, other.cont3);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("cont3", cont3)
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
     * Creates an instance of cont2.
     *
     * @param builderObject builder object of cont2
     */
    protected DefaultCont2(Cont2Builder builderObject) {
        this.cont3 = builderObject.cont3();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Cont2 processSubtreeFiltering(Cont2 appInstance, boolean isSelectAllSchemaChild) {
        Cont2Builder subTreeFilteringResultBuilder = new Cont2Builder();
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
    private boolean processChildNodesSubTreeFiltering(Cont2 appInstance, Cont2Builder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (cont3()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.cont3() != null) {
                Cont3 result = cont3.processSubtreeFiltering(appInstance.cont3(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.cont3(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultCont2.
     */
    protected DefaultCont2() {
    }

    /**
     * Returns the attribute Cont2Builder.
     *
     * @return value of Cont2Builder
     */
    public static Cont2Builder builder() {
        return new Cont2Builder();
    }


    /**
     * Represents the builder implementation of cont2.
     */
    public static class Cont2Builder implements Cont2.Cont2Builder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected Cont3 cont3;

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;



        @Override
        public Cont3 cont3() {
            return cont3;
        }

        @Override
        public Cont2Builder cont3(Cont3 cont3) {
            this.cont3 = cont3;
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
        public Cont2Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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
        public Cont2 build() {
            return new DefaultCont2(this);
        }

        /**
         * Creates an instance of cont2Builder.
         */
        public Cont2Builder() {
        }
    }
}
