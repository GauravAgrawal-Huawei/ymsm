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
import org.onosproject.yms.app.ydt.exceptions.YdtExceptions;
import org.onosproject.yms.ydt.YdtType;

/**
 * Represents a single instance YANG data tree node.
 */
class YdtSingleInstanceNode extends YdtNode {

    /**
     * Creates a YANG single instance node object.
     *
     * @param nodeIdentifier node identifier of YANG data tree single instance node .
     */
    public YdtSingleInstanceNode(YangSchemaNodeIdentifier nodeIdentifier) {
        super(YdtType.SINGLE_INSTANCE_NODE, nodeIdentifier);
    }

    /**
     * if CollidingChild node found of type YdtSingleInstanceNode then
     * throw the exception as it is duplicate node.
     *
     * @throws YdtExceptions exception for duplicate node.
     */
    @Override
    public void isDuplicateEntriesValid() {
        // Free resources
        freeRestResources();
        String errorInfo = "Duplicate entry with name " + this.getYdtNodeIdentifier().getName() + ".";
        throw new YdtExceptions(errorInfo);
    }
}
