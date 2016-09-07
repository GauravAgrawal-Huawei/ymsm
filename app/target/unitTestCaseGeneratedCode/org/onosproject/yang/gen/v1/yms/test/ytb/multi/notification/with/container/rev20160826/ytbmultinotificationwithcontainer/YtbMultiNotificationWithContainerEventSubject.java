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

package org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826
            .ytbmultinotificationwithcontainer;
/**
 * Represents the implementation of ytbMultiNotificationWithContainerEventSubject.
 */
public class YtbMultiNotificationWithContainerEventSubject {

    protected Fortesta fortesta;
    protected Fortestb fortestb;

    /**
     * Returns the attribute fortesta.
     *
     * @return value of fortesta
     */
    public Fortesta fortesta() {
        return fortesta;
    }

    /**
     * Returns the attribute fortestb.
     *
     * @return value of fortestb
     */
    public Fortestb fortestb() {
        return fortestb;
    }

    /**
     * Sets the value to attribute fortesta.
     *
     * @param fortesta value of fortesta
     */
    public void fortesta(Fortesta fortesta) {
        this.fortesta = fortesta;
    }

    /**
     * Sets the value to attribute fortestb.
     *
     * @param fortestb value of fortestb
     */
    public void fortestb(Fortestb fortestb) {
        this.fortestb = fortestb;
    }
}
