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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826;

/**
 * Abstraction of an entity which represents the functionality of ytbIetfScheduleService.
 */
public interface YtbIetfScheduleService {

    /**
     * Returns the attribute YtbIetfSchedule.
     *
     * @param ytbIetfSchedule value of YtbIetfSchedule
     * @return value of YtbIetfSchedule
     */
    YtbIetfSchedule getYtbIetfSchedule(YtbIetfScheduleOpParam ytbIetfSchedule);

    /**
     * Sets the value to attribute ytbIetfSchedule.
     *
     * @param ytbIetfSchedule value of ytbIetfSchedule
     */
    void setYtbIetfSchedule(YtbIetfScheduleOpParam ytbIetfSchedule);

}