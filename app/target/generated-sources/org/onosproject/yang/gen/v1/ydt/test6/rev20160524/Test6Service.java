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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524;

import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.rockthehouse.RockTheHouseInput;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.rockthehouse.RockTheHouseOutput;

/**
 * Abstraction of an entity which represents the functionality of test6Service.
 */
public interface Test6Service {

    /**
     * Returns the attribute Test6.
     *
     * @param test6 value of Test6
     * @return value of Test6
     */
    Test6 getTest6(Test6OpParam test6);

    /**
     * Sets the value to attribute test6.
     *
     * @param test6 value of test6
     */
    void setTest6(Test6OpParam test6);

    /**
     * Returns the attribute Test6.
     *
     * @param test6 value of Test6
     * @return value of Test6
     */
    Test6 getAugmentedTest6Cont4(Test6OpParam test6);

    /**
     * Sets the value to attribute augmentedTest6Cont4.
     *
     * @param augmentedTest6Cont4 value of augmentedTest6Cont4
     */
    void setAugmentedTest6Cont4(Test6OpParam augmentedTest6Cont4);


    /**
     * Service interface of rockTheHouse.
     *
     * @param inputVar input of service interface rockTheHouse
     * @return RockTheHouseOutput output of service interface rockTheHouse
     */
    RockTheHouseOutput rockTheHouse(RockTheHouseInput inputVar);
}
