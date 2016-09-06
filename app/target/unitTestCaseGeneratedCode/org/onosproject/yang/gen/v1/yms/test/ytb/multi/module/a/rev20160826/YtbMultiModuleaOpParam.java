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

package org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.a.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.a.rev20160826.ytbmultimodulea.Ytbmultilist;

/**
 * Represents the implementation of ytbMultiModulea.
 */
public class YtbMultiModuleaOpParam implements YtbMultiModulea {

    protected List<Ytbmultilist> ytbmultilist = new ArrayList<>();

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

    public List<Ytbmultilist> ytbmultilist() {
        return ytbmultilist;
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
        return Objects.hash(ytbmultilist);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbMultiModuleaOpParam) {
            YtbMultiModuleaOpParam other = (YtbMultiModuleaOpParam) obj;
            return
                 Objects.equals(ytbmultilist, other.ytbmultilist);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("ytbmultilist", ytbmultilist)
            .toString();
    }

    /**
     * Creates an instance of ytbMultiModulea.
     *
     * @param builderObject builder object of ytbMultiModulea
     */
    protected YtbMultiModuleaOpParam(YtbMultiModuleaBuilder builderObject) {
        this.ytbmultilist = builderObject.ytbmultilist();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public YtbMultiModulea processSubtreeFiltering(YtbMultiModulea appInstance, boolean isSelectAllSchemaChild) {
        YtbMultiModuleaBuilder subTreeFilteringResultBuilder = new YtbMultiModuleaBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbMultiModulea appInstance, YtbMultiModuleaBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Ytbmultilist ytbmultilist : appInstance.ytbmultilist()) {
                subTreeFilteringResultBuilder.addToYtbmultilist(ytbmultilist);
            }
        } else if (ytbmultilist() != null) {
            isAnySelectOrContainmentNode = true;
            if (!ytbmultilist().isEmpty()) {
                if (appInstance.ytbmultilist() != null && !appInstance.ytbmultilist().isEmpty()) {
                    for (Ytbmultilist ytbmultilist : ytbmultilist()) {
                        for (Ytbmultilist ytbmultilist2 : appInstance.ytbmultilist()) {
                            Ytbmultilist result = ytbmultilist.processSubtreeFiltering(ytbmultilist2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToYtbmultilist(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.ytbmultilist() != null && !appInstance.ytbmultilist().isEmpty()) {
                    for (Ytbmultilist ytbmultilist : appInstance.ytbmultilist()) {
                        subTreeFilteringResultBuilder.addToYtbmultilist(ytbmultilist);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbMultiModuleaOpParam.
     */
    protected YtbMultiModuleaOpParam() {
    }

    /**
     * Returns the attribute YtbMultiModuleaBuilder.
     *
     * @return value of YtbMultiModuleaBuilder
     */
    public static YtbMultiModuleaBuilder builder() {
        return new YtbMultiModuleaBuilder();
    }


    /**
     * Represents the builder implementation of ytbMultiModulea.
     */
    public static class YtbMultiModuleaBuilder {

        protected List<Ytbmultilist> ytbmultilist = new ArrayList<>();

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        public List<Ytbmultilist> ytbmultilist() {
            return ytbmultilist;
        }

        /**
         * Returns the builder object of ytbmultilist.
         *
         * @param ytbmultilist list of ytbmultilist
         * @return builder object of ytbmultilist
         */
        public YtbMultiModuleaBuilder ytbmultilist(List<Ytbmultilist> ytbmultilist) {
            this.ytbmultilist = ytbmultilist;
            return this;
        }
        public YtbMultiModuleaBuilder addToYtbmultilist(Ytbmultilist value) {
            ytbmultilist().add(value);
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
        public YtbMultiModuleaBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        public YtbMultiModulea build() {
            return new YtbMultiModuleaOpParam(this);
        }

        /**
         * Creates an instance of ytbMultiModuleaBuilder.
         */
        public YtbMultiModuleaBuilder() {
        }
    }
}
