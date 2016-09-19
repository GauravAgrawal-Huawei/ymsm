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

package org.onosproject.yang.gen.v1.ydt.integer32.rev20160524;

/**
 * Abstraction of an entity which represents the functionality of integer32Service.
 */
public interface Integer32Service {

    /**
     * Returns the attribute Integer32.
     *
     * @param integer32 value of Integer32
     * @return value of Integer32
     */
    Integer32 getInteger32(Integer32OpParam integer32);

    /**
     * Sets the value to attribute integer32.
     *
     * @param integer32 value of integer32
     */
    void setInteger32(Integer32OpParam integer32);

}
