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

package org.onosproject.yms.app.yab;

import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.Test6;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.Test6OpParam;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.Test6Service;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.Cont1;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.DefaultCont1;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.rockthehouse.RockTheHouseInput;

/**
 * Implementation of the application management service.
 */
public class Test6Manager implements Test6Service {

    Test6 response;

    @Override
    public Test6 getTest6(Test6OpParam test6) {
        Cont1 cont = new DefaultCont1.Cont1Builder().leaf4("4").build();
        Test6 response = new Test6OpParam.Test6Builder().cont1(cont).build();
        return response;
    }

    @Override
    public void setTest6(Test6OpParam test6) {
        response = test6;
    }

    @Override
    public Test6 getAugmentedTest6Cont4(Test6OpParam test6) {
        return null;
    }

    @Override
    public void setAugmentedTest6Cont4(Test6OpParam augmentedTest6Cont4) {

    }

    @Override
    public void rockTheHouse(RockTheHouseInput inputVar) {
        // TODO: to be implemented
    }
}
