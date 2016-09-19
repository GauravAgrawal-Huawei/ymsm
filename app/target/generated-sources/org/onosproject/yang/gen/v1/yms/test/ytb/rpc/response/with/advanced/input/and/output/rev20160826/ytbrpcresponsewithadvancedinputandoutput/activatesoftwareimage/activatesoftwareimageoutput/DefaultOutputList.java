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
            .ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageoutput;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageoutput.outputlist
            .ContentInside;

/**
 * Represents the implementation of outputList.
 */
public class DefaultOutputList implements OutputList {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected byte[] listKey;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected ContentInside contentInside;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();

    @Override
    public byte[] listKey() {
        return listKey;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public ContentInside contentInside() {
        return contentInside;
    }

    @Override
    public int hashCode() {
        return Objects.hash(listKey, onosYangNodeOperationType, contentInside);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultOutputList) {
            DefaultOutputList other = (DefaultOutputList) obj;
            return
                 Objects.equals(listKey, other.listKey) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(contentInside, other.contentInside);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("listKey", listKey)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("contentInside", contentInside)
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
     * Creates an instance of outputList.
     *
     * @param builderObject builder object of outputList
     */
    protected DefaultOutputList(OutputListBuilder builderObject) {
        this.listKey = builderObject.listKey();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.contentInside = builderObject.contentInside();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public OutputList processSubtreeFiltering(OutputList appInstance, boolean isSelectAllSchemaChild) {
        OutputListBuilder subTreeFilteringResultBuilder = new OutputListBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
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
    private boolean processLeafSubtreeFiltering(OutputList appInstance, OutputListBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.LISTKEY.getLeafIndex())) {
            if (appInstance.listKey() == null || !(listKey().equals(appInstance.listKey()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.listKey(appInstance.listKey());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.LISTKEY.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.listKey(appInstance.listKey());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(OutputList appInstance, OutputListBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (contentInside()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.contentInside() != null) {
                ContentInside result = contentInside.processSubtreeFiltering(appInstance.contentInside(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.contentInside(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultOutputList.
     */
    protected DefaultOutputList() {
    }

    /**
     * Returns the attribute OutputListBuilder.
     *
     * @return value of OutputListBuilder
     */
    public static OutputListBuilder builder() {
        return new OutputListBuilder();
    }

    /**
     * Returns the valueLeafFlags.
     *
     * @return value of valueLeafFlags
     */
    public BitSet getValueLeafFlags() {
        return valueLeafFlags;
    }

    /**
     * Returns the selectLeafFlags.
     *
     * @return value of selectLeafFlags
     */
    public BitSet getSelectLeafFlags() {
        return selectLeafFlags;
    }


    @Override
    public boolean isLeafValueSet(LeafIdentifier leaf) {
        return getValueLeafFlags().get(leaf.getLeafIndex());
    }

    @Override
    public boolean isSelectLeaf(LeafIdentifier leaf) {
        return getSelectLeafFlags().get(leaf.getLeafIndex());
    }


    /**
     * Represents the builder implementation of outputList.
     */
    public static class OutputListBuilder implements OutputList.OutputListBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected byte[] listKey;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected ContentInside contentInside;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();


        @Override
        public byte[] listKey() {
            return listKey;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public ContentInside contentInside() {
            return contentInside;
        }

        @Override
        public OutputListBuilder listKey(byte[] listKey) {
            getValueLeafFlags().set(LeafIdentifier.LISTKEY.getLeafIndex());
            this.listKey = listKey;
            return this;
        }

        @Override
        public OutputListBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public OutputListBuilder contentInside(ContentInside contentInside) {
            this.contentInside = contentInside;
            return this;
        }

        /**
         * Returns the valueLeafFlags.
         *
         * @return value of valueLeafFlags
         */
        public BitSet getValueLeafFlags() {
            return valueLeafFlags;
        }

        /**
         * Returns the selectLeafFlags.
         *
         * @return value of selectLeafFlags
         */
        public BitSet getSelectLeafFlags() {
            return selectLeafFlags;
        }



        @Override
        public OutputListBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
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
        public OutputList build() {
            return new DefaultOutputList(this);
        }

        /**
         * Creates an instance of outputListBuilder.
         */
        public OutputListBuilder() {
        }
    }
}
