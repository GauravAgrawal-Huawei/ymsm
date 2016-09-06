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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208;

import org.onosproject.event.ListenerService;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208.ietfnetwork1
            .IetfNetwork1Event;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208.ietfnetwork1
            .IetfNetwork1EventListener;

/**
 * Abstraction of an entity which represents the functionality of ietfNetwork1Service.
 */
public interface IetfNetwork1Service
        extends ListenerService<IetfNetwork1Event, IetfNetwork1EventListener> {

    /**
     * Returns the attribute IetfNetwork1.
     *
     * @param ietfNetwork1 value of IetfNetwork1
     * @return value of IetfNetwork1
     */
    IetfNetwork1 getIetfNetwork1(IetfNetwork1OpParam ietfNetwork1);

    /**
     * Sets the value to attribute ietfNetwork1.
     *
     * @param ietfNetwork1 value of ietfNetwork1
     */
    void setIetfNetwork1(IetfNetwork1OpParam ietfNetwork1);

}
