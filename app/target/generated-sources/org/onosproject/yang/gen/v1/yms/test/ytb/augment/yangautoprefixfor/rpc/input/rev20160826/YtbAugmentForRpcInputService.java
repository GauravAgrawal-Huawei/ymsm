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

package org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826;

import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .YtbRpcResponseWithAdvancedInputAndOutput;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826
            .YtbRpcResponseWithAdvancedInputAndOutputOpParam;

/**
 * Abstraction of an entity which represents the functionality of ytbAugmentForRpcInputService.
 */
public interface YtbAugmentForRpcInputService {

    /**
     * Returns the attribute YtbRpcResponseWithAdvancedInputAndOutput.
     *
     * @param ytbRpcResponseWithAdvancedInputAndOutput value of YtbRpcResponseWithAdvancedInputAndOutput
     * @return value of YtbRpcResponseWithAdvancedInputAndOutput
     */
    YtbRpcResponseWithAdvancedInputAndOutput
           
            getAugmentedYtbRpcResponseWithAdvancedInputAndOutputActivateSoftwareImage(YtbRpcResponseWithAdvancedInputAndOutputOpParam ytbRpcResponseWithAdvancedInputAndOutput);

    /**
     * Sets the value to attribute augmentedYtbRpcResponseWithAdvancedInputAndOutputActivateSoftwareImage.
     *
     * @param augmentedYtbRpcResponseWithAdvancedInputAndOutputActivateSoftwareImage value of
            augmentedYtbRpcResponseWithAdvancedInputAndOutputActivateSoftwareImage
     */
    void
           
            setAugmentedYtbRpcResponseWithAdvancedInputAndOutputActivateSoftwareImage(YtbRpcResponseWithAdvancedInputAndOutputOpParam augmentedYtbRpcResponseWithAdvancedInputAndOutputActivateSoftwareImage);

}
