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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network2.rev20151208;

import org.onosproject.event.ListenerService;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network2.rev20151208.ietfnetwork2
            .IetfNetwork2Event;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network2.rev20151208.ietfnetwork2
            .IetfNetwork2EventListener;

/**
 * Abstraction of an entity which represents the functionality of ietfNetwork2Service.
 */
public interface IetfNetwork2Service
        extends ListenerService<IetfNetwork2Event, IetfNetwork2EventListener> {

    /**
     * Returns the attribute IetfNetwork2.
     *
     * @param ietfNetwork2 value of IetfNetwork2
     * @return value of IetfNetwork2
     */
    IetfNetwork2 getIetfNetwork2(IetfNetwork2OpParam ietfNetwork2);

    /**
     * Sets the value to attribute ietfNetwork2.
     *
     * @param ietfNetwork2 value of ietfNetwork2
     */
    void setIetfNetwork2(IetfNetwork2OpParam ietfNetwork2);

}
