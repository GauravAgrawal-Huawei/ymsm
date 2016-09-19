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

package org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826;

import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.ActivateSoftwareImageInput;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.ActivateSoftwareImageOutput;

/**
 * Abstraction of an entity which represents the functionality of ytbRpcResponseWithAdvancedInputAndOutputService.
 */
public interface YtbRpcResponseWithAdvancedInputAndOutputService {

    /**
     * Returns the attribute YtbRpcResponseWithAdvancedInputAndOutput.
     *
     * @param ytbRpcResponseWithAdvancedInputAndOutput value of YtbRpcResponseWithAdvancedInputAndOutput
     * @return value of YtbRpcResponseWithAdvancedInputAndOutput
     */
    YtbRpcResponseWithAdvancedInputAndOutput
            getYtbRpcResponseWithAdvancedInputAndOutput(YtbRpcResponseWithAdvancedInputAndOutputOpParam
            ytbRpcResponseWithAdvancedInputAndOutput);

    /**
     * Sets the value to attribute ytbRpcResponseWithAdvancedInputAndOutput.
     *
     * @param ytbRpcResponseWithAdvancedInputAndOutput value of ytbRpcResponseWithAdvancedInputAndOutput
     */
    void setYtbRpcResponseWithAdvancedInputAndOutput(YtbRpcResponseWithAdvancedInputAndOutputOpParam
            ytbRpcResponseWithAdvancedInputAndOutput);


    /**
     * Service interface of activateSoftwareImage.
     *
     * @param inputVar input of service interface activateSoftwareImage
     * @return ActivateSoftwareImageOutput output of service interface activateSoftwareImage
     */
    ActivateSoftwareImageOutput activateSoftwareImage(ActivateSoftwareImageInput inputVar);
}
