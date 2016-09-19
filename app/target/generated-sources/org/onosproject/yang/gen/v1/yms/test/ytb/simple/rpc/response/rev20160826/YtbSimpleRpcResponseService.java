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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.rpc.response.rev20160826;

import org.onosproject.yang.gen.v1.yms.test.ytb.simple.rpc.response.rev20160826.ytbsimplerpcresponse.rpc.RpcInput;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.rpc.response.rev20160826.ytbsimplerpcresponse.rpc.RpcOutput;

/**
 * Abstraction of an entity which represents the functionality of ytbSimpleRpcResponseService.
 */
public interface YtbSimpleRpcResponseService {

    /**
     * Returns the attribute YtbSimpleRpcResponse.
     *
     * @param ytbSimpleRpcResponse value of YtbSimpleRpcResponse
     * @return value of YtbSimpleRpcResponse
     */
    YtbSimpleRpcResponse getYtbSimpleRpcResponse(YtbSimpleRpcResponseOpParam ytbSimpleRpcResponse);

    /**
     * Sets the value to attribute ytbSimpleRpcResponse.
     *
     * @param ytbSimpleRpcResponse value of ytbSimpleRpcResponse
     */
    void setYtbSimpleRpcResponse(YtbSimpleRpcResponseOpParam ytbSimpleRpcResponse);


    /**
     * Service interface of rpc.
     *
     * @param inputVar input of service interface rpc
     * @return RpcOutput output of service interface rpc
     */
    RpcOutput rpc(RpcInput inputVar);
}
