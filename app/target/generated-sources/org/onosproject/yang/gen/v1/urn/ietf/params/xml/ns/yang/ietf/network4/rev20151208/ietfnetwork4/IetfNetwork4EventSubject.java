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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network4.rev20151208.ietfnetwork4;
/**
 * Represents the implementation of ietfNetwork4EventSubject.
 */
public class IetfNetwork4EventSubject {

    protected NetworkUp networkUp;
    protected NetworkDown networkDown;

    /**
     * Returns the attribute networkUp.
     *
     * @return value of networkUp
     */
    public NetworkUp networkUp() {
        return networkUp;
    }

    /**
     * Returns the attribute networkDown.
     *
     * @return value of networkDown
     */
    public NetworkDown networkDown() {
        return networkDown;
    }

    /**
     * Sets the value to attribute networkUp.
     *
     * @param networkUp value of networkUp
     */
    public void networkUp(NetworkUp networkUp) {
        this.networkUp = networkUp;
    }

    /**
     * Sets the value to attribute networkDown.
     *
     * @param networkDown value of networkDown
     */
    public void networkDown(NetworkDown networkDown) {
        this.networkDown = networkDown;
    }
}
