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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network2.rev20151208.ietfnetwork2;

import org.onosproject.event.AbstractEvent;

/**
 * Represents event implementation of ietfNetwork2Event.
 */
public class IetfNetwork2Event extends AbstractEvent<IetfNetwork2Event.Type, IetfNetwork2EventSubject> {

    public enum Type {
        /**
         * Represents network-up.
         */
        NETWORK_UP,
        /**
         * Represents network-down.
         */
        NETWORK_DOWN
    }

    /**
     * Creates IetfNetwork2EventSubject event with type and subject.
     *
     * @param type event type
     * @param subject subject IetfNetwork2EventSubject
     */
    public IetfNetwork2Event(Type type, IetfNetwork2EventSubject subject) {
        super(type, subject);
    }

    /**
     * Creates IetfNetwork2EventSubject event with type, subject and time.
     *
     * @param type event type
     * @param subject subject IetfNetwork2EventSubject
     * @param time time of event
     */
    public IetfNetwork2Event(Type type, IetfNetwork2EventSubject subject, long time) {
        super(type, subject, time);
    }

}
