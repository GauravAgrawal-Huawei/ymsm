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

import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtResponse;
import org.onosproject.yms.ydt.YmsOperationExecutionStatus;
import org.onosproject.yms.ydt.YmsOperationType;

public class YangResponseWorkBench implements YdtResponse {

    /**
     * YDT root node context.
     */
    private YdtContext rootNode;

    /**
     * YMS operation execution status.
     */
    private YmsOperationExecutionStatus ymsOperationExecutionStatus;

    /**
     * YMS operation type.
     */
    private YmsOperationType ymsOperationType;

    /**
     * Creates an instance of YangResponseWorkBench which is use to initialize rootNode and childNode.
     *
     * @param ydtContext                  root node context
     * @param ymsOperationExecutionStatus YMS operation execution status
     * @param ymsOperationType            YMS operation type
     */
    public YangResponseWorkBench(YdtContext ydtContext,
                                 YmsOperationExecutionStatus ymsOperationExecutionStatus,
                                 YmsOperationType ymsOperationType) {
        this.rootNode = ydtContext;
        this.ymsOperationExecutionStatus = ymsOperationExecutionStatus;
        this.ymsOperationType = ymsOperationType;
    }

    @Override
    public YmsOperationExecutionStatus getYmsOperationResult() {
        return ymsOperationExecutionStatus;
    }

    @Override
    public YdtContext getRootNode() {
        return rootNode;
    }

    @Override
    public YmsOperationType getYmsOperationType() {
        return ymsOperationType;
    }

    /**
     * Sets root node.
     *
     * @param rootNode root node
     */
    public void setRootNode(YdtContext rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * Sets YMS operation execution status.
     *
     * @param ymsOperationExecutionStatus YMS operation execution status
     */
    public void setYmsOperationExecutionStatus(YmsOperationExecutionStatus ymsOperationExecutionStatus) {
        this.ymsOperationExecutionStatus = ymsOperationExecutionStatus;
    }

    /**
     * Sets YMS operation type.
     *
     * @param ymsOperationType YMS operation type
     */
    public void setYmsOperationType(YmsOperationType ymsOperationType) {
        this.ymsOperationType = ymsOperationType;
    }
}
