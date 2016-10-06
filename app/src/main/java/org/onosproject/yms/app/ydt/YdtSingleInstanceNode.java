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

package org.onosproject.yms.app.ydt;

import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;

import static org.onosproject.yms.app.ydt.YdtConstants.DUP_NAME;
import static org.onosproject.yms.app.ydt.YdtConstants.getErrorString;
import static org.onosproject.yms.ydt.YdtType.SINGLE_INSTANCE_NODE;

/**
 * Represents a single instance YANG data tree node.
 */
class YdtSingleInstanceNode extends YdtNode {

    /**
     * Creates a YANG single instance node object.
     *
     * @param id node identifier of YDT single instance node
     */
    protected YdtSingleInstanceNode(YangSchemaNodeIdentifier id) {
        super(SINGLE_INSTANCE_NODE, id);
    }

    @Override
    public void validDuplicateEntryProcessing() {
        errorHandler(getErrorString(DUP_NAME, getYdtNodeIdentifier().getName()),
                     this);
    }
}
