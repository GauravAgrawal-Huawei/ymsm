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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208.ietfnetwork1;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208.ietfnetwork1.networkdown
            .Networks;

/**
 * Represents the implementation of networkDown.
 */
public class DefaultNetworkDown implements NetworkDown {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected Networks networks;

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
    public Networks networks() {
        return networks;
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
        return Objects.hash(networks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultNetworkDown) {
            DefaultNetworkDown other = (DefaultNetworkDown) obj;
            return
                 Objects.equals(networks, other.networks);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("networks", networks)
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
     * Creates an instance of networkDown.
     *
     * @param builderObject builder object of networkDown
     */
    protected DefaultNetworkDown(NetworkDownBuilder builderObject) {
        this.networks = builderObject.networks();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public NetworkDown processSubtreeFiltering(NetworkDown appInstance, boolean isSelectAllSchemaChild) {
        NetworkDownBuilder subTreeFilteringResultBuilder = new NetworkDownBuilder();
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
    private boolean processChildNodesSubTreeFiltering(NetworkDown appInstance, NetworkDownBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (networks()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.networks() != null) {
                Networks result = networks.processSubtreeFiltering(appInstance.networks(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.networks(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultNetworkDown.
     */
    protected DefaultNetworkDown() {
    }

    /**
     * Returns the attribute NetworkDownBuilder.
     *
     * @return value of NetworkDownBuilder
     */
    public static NetworkDownBuilder builder() {
        return new NetworkDownBuilder();
    }


    /**
     * Represents the builder implementation of networkDown.
     */
    public static class NetworkDownBuilder implements NetworkDown.NetworkDownBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected Networks networks;

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;



        @Override
        public Networks networks() {
            return networks;
        }

        @Override
        public NetworkDownBuilder networks(Networks networks) {
            this.networks = networks;
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
        public NetworkDownBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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
        public NetworkDown build() {
            return new DefaultNetworkDown(this);
        }

        /**
         * Creates an instance of networkDownBuilder.
         */
        public NetworkDownBuilder() {
        }
    }
}