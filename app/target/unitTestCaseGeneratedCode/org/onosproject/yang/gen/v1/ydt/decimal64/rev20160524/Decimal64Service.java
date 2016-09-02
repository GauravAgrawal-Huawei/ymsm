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

package org.onosproject.yang.gen.v1.ydt.decimal64.rev20160524;

/**
 * Abstraction of an entity which represents the functionality of decimal64Service.
 */
public interface Decimal64Service {

    /**
     * Returns the attribute Decimal64.
     *
     * @param decimal64 value of Decimal64
     * @return value of Decimal64
     */
    Decimal64 getDecimal64(Decimal64OpParam decimal64);

    /**
     * Sets the value to attribute decimal64.
     *
     * @param decimal64 value of decimal64
     */
    void setDecimal64(Decimal64OpParam decimal64);

}
