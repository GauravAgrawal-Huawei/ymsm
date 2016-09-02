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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208.ietfnetwork1;

import org.onosproject.event.AbstractEvent;

/**
 * Represents event implementation of ietfNetwork1Event.
 */
public class IetfNetwork1Event extends AbstractEvent<IetfNetwork1Event.Type, IetfNetwork1EventSubject> {

    public enum Type {
        /**
         * Represents networkUp.
         */
    NETWORK_UP,
        /**
         * Represents networkDown.
         */
    NETWORK_DOWN
    }

    /**
     * Creates IetfNetwork1EventSubject event with type and subject.
     *
     * @param type event type
     * @param subject subject IetfNetwork1EventSubject
     */
    public IetfNetwork1Event(Type type, IetfNetwork1EventSubject subject) {
        super(type, subject);
    }

    /**
     * Creates IetfNetwork1EventSubject event with type, subject and time.
     *
     * @param type event type
     * @param subject subject IetfNetwork1EventSubject
     * @param time time of event
     */
    public IetfNetwork1Event(Type type, IetfNetwork1EventSubject subject, long time) {
        super(type, subject, time);
    }

}
