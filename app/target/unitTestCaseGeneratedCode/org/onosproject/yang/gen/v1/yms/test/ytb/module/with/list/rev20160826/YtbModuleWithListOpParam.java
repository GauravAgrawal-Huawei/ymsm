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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826.ytbmodulewithlist.Ytblistlist;

/**
 * Represents the implementation of ytbModuleWithList.
 */
public class YtbModuleWithListOpParam implements YtbModuleWithList {

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


    protected List<Ytblistlist> ytblistlist = new ArrayList<>();
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    public List<Ytblistlist> ytblistlist() {
        return ytblistlist;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ytblistlist);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbModuleWithListOpParam) {
            YtbModuleWithListOpParam other = (YtbModuleWithListOpParam) obj;
            return
                 Objects.equals(ytblistlist, other.ytblistlist);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("ytblistlist", ytblistlist)
            .toString();
    }

    /**
     * Creates an instance of ytbModuleWithList.
     *
     * @param builderObject builder object of ytbModuleWithList
     */
    protected YtbModuleWithListOpParam(YtbModuleWithListBuilder builderObject) {
        this.ytblistlist = builderObject.ytblistlist();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public YtbModuleWithList processSubtreeFiltering(YtbModuleWithList appInstance, boolean isSelectAllSchemaChild) {
        YtbModuleWithListBuilder subTreeFilteringResultBuilder = new YtbModuleWithListBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processChildNodesSubTreeFiltering(YtbModuleWithList appInstance, YtbModuleWithListBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Ytblistlist ytblistlist : appInstance.ytblistlist()) {
                subTreeFilteringResultBuilder.addToYtblistlist(ytblistlist);
            }
        } else if (ytblistlist() != null) {
            isAnySelectOrContainmentNode = true;
            if (!ytblistlist().isEmpty()) {
                if (appInstance.ytblistlist() != null && !appInstance.ytblistlist().isEmpty()) {
                    for (Ytblistlist ytblistlist : ytblistlist()) {
                        for (Ytblistlist ytblistlist2 : appInstance.ytblistlist()) {
                            Ytblistlist result = ytblistlist.processSubtreeFiltering(ytblistlist2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToYtblistlist(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.ytblistlist() != null && !appInstance.ytblistlist().isEmpty()) {
                    for (Ytblistlist ytblistlist : appInstance.ytblistlist()) {
                        subTreeFilteringResultBuilder.addToYtblistlist(ytblistlist);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbModuleWithListOpParam.
     */
    protected YtbModuleWithListOpParam() {
    }

    /**
     * Returns the attribute YtbModuleWithListBuilder.
     *
     * @return value of YtbModuleWithListBuilder
     */
    public static YtbModuleWithListBuilder builder() {
        return new YtbModuleWithListBuilder();
    }


    /**
     * Represents the builder implementation of ytbModuleWithList.
     */
    public static class YtbModuleWithListBuilder {

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected List<Ytblistlist> ytblistlist = new ArrayList<>();

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
        public YtbModuleWithListBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }

        public List<Ytblistlist> ytblistlist() {
            return ytblistlist;
        }

        /**
         * Returns the builder object of ytblistlist.
         *
         * @param ytblistlist list of ytblistlist
         * @return builder object of ytblistlist
         */
        public YtbModuleWithListBuilder ytblistlist(List<Ytblistlist> ytblistlist) {
            this.ytblistlist = ytblistlist;
            return this;
        }
        public YtbModuleWithListBuilder addToYtblistlist(Ytblistlist value) {
            ytblistlist().add(value);
            return this;
        }

        public YtbModuleWithList build() {
            return new YtbModuleWithListOpParam(this);
        }

        /**
         * Creates an instance of ytbModuleWithListBuilder.
         */
        public YtbModuleWithListBuilder() {
        }
    }
}
