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

package org.onosproject.yang.gen.v1.ydt.integer64.rev20160524;

/**
 * Abstraction of an entity which represents the functionality of integer64Service.
 */
public interface Integer64Service {

    /**
     * Returns the attribute Integer64.
     *
     * @param integer64 value of Integer64
     * @return value of Integer64
     */
    Integer64 getInteger64(Integer64OpParam integer64);

    /**
     * Sets the value to attribute integer64.
     *
     * @param integer64 value of integer64
     */
    void setInteger64(Integer64OpParam integer64);

}
