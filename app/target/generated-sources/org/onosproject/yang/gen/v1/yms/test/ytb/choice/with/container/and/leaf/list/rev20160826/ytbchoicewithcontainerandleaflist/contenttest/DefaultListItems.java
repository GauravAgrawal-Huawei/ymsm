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
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.ContentTest;

/**
 * Represents the implementation of listItems.
 */
public class DefaultListItems implements ListItems {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected List<byte[]> listItems;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
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
    public List<byte[]> listItems() {
        return listItems;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(listItems, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultListItems) {
            DefaultListItems other = (DefaultListItems) obj;
            return
                 Objects.equals(listItems, other.listItems) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("listItems", listItems)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
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
     * Creates an instance of listItems.
     *
     * @param builderObject builder object of listItems
     */
    protected DefaultListItems(ListItemsBuilder builderObject) {
        this.listItems = builderObject.listItems();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public ContentTest processSubtreeFiltering(ContentTest instance, boolean isSelectAllSchemaChild) {
        ListItemsBuilder subTreeFilteringResultBuilder = new ListItemsBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        ListItems appInstance = (ListItems) instance;
        if (!processLeafListSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
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
    private boolean processLeafListSubTreeFiltering(ContentTest instance, ListItemsBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        ListItems appInstance = (ListItems) instance;
        if (isSelectAllSchemaChild) {
            for (byte[] listItems : appInstance.listItems()) {
                subTreeFilteringResultBuilder.addToListItems(listItems);
            }
        } else if (listItems() != null) {
            if (!listItems().isEmpty()) {
                if (appInstance.listItems() == null || appInstance.listItems().isEmpty()) {
                    return false;
                }
                for (byte[] listItems : listItems()) {
                    boolean flag = false;
                    for (byte[] listItems2 : appInstance.listItems()) {
                        if (listItems.equals(listItems2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToListItems(listItems2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.listItems() != null && !appInstance.listItems().isEmpty()) {
                    for (byte[] listItems : appInstance.listItems()) {
                        subTreeFilteringResultBuilder.addToListItems(listItems);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultListItems.
     */
    protected DefaultListItems() {
    }

    /**
     * Returns the attribute ListItemsBuilder.
     *
     * @return value of ListItemsBuilder
     */
    public static ListItemsBuilder builder() {
        return new ListItemsBuilder();
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
     * Represents the builder implementation of listItems.
     */
    public static class ListItemsBuilder implements ListItems.ListItemsBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected List<byte[]> listItems;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
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
        public List<byte[]> listItems() {
            return listItems;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public ListItemsBuilder listItems(List<byte[]> listItems) {
            this.listItems = listItems;
            return this;
        }

        @Override
        public ListItemsBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public ListItemsBuilder addToListItems(byte[] value) {
            if (listItems() == null) {
                listItems(new ArrayList<>());
            }
            listItems().add(value);
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
        public ListItemsBuilder selectLeaf(LeafIdentifier leaf) {
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
        public ListItems build() {
            return new DefaultListItems(this);
        }

        /**
         * Creates an instance of listItemsBuilder.
         */
        public ListItemsBuilder() {
        }
    }
}
