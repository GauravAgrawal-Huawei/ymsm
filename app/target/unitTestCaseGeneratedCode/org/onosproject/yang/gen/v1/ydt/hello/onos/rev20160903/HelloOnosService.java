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

package org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903;

import org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903.helloonos.helloworld.HelloWorldInput;
import org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903.helloonos.helloworld.HelloWorldOutput;

/**
 * Abstraction of an entity which represents the functionality of helloOnosService.
 */
public interface HelloOnosService {

    /**
     * Returns the attribute HelloOnos.
     *
     * @param helloOnos value of HelloOnos
     * @return value of HelloOnos
     */
    HelloOnos getHelloOnos(HelloOnosOpParam helloOnos);

    /**
     * Sets the value to attribute helloOnos.
     *
     * @param helloOnos value of helloOnos
     */
    void setHelloOnos(HelloOnosOpParam helloOnos);


    /**
     * Service interface of helloWorld.
     *
     * @param inputVar input of service interface helloWorld
     * @return HelloWorldOutput output of service interface helloWorld
     */
    HelloWorldOutput helloWorld(HelloWorldInput inputVar);
}
