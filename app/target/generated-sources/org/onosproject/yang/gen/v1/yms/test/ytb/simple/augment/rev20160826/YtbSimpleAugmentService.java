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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826;

/**
 * Abstraction of an entity which represents the functionality of ytbSimpleAugmentService.
 */
public interface YtbSimpleAugmentService {

    /**
     * Returns the attribute YtbSimpleAugment.
     *
     * @param ytbSimpleAugment value of YtbSimpleAugment
     * @return value of YtbSimpleAugment
     */
    YtbSimpleAugment getYtbSimpleAugment(YtbSimpleAugmentOpParam ytbSimpleAugment);

    /**
     * Sets the value to attribute ytbSimpleAugment.
     *
     * @param ytbSimpleAugment value of ytbSimpleAugment
     */
    void setYtbSimpleAugment(YtbSimpleAugmentOpParam ytbSimpleAugment);

    /**
     * Returns the attribute YtbSimpleAugment.
     *
     * @param ytbSimpleAugment value of YtbSimpleAugment
     * @return value of YtbSimpleAugment
     */
    YtbSimpleAugment getAugmentedYtbSimpleAugmentCont1(YtbSimpleAugmentOpParam ytbSimpleAugment);

    /**
     * Sets the value to attribute augmentedYtbSimpleAugmentCont1.
     *
     * @param augmentedYtbSimpleAugmentCont1 value of augmentedYtbSimpleAugmentCont1
     */
    void setAugmentedYtbSimpleAugmentCont1(YtbSimpleAugmentOpParam augmentedYtbSimpleAugmentCont1);

}
