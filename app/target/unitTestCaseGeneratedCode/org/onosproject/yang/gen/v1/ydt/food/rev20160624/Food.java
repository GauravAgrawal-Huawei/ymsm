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

package org.onosproject.yang.gen.v1.ydt.food.rev20160624;

/**
 * Abstraction of an entity which represents the functionality of food.
 */
public interface Food {

    /**
     * Returns the attribute food.
     *
     * @return value of food
     */
    org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.Food food();

    /**
     * Checks if the passed Food maps the content match query condition.
     *
     * @param food food being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Food processSubtreeFiltering(Food food, boolean isSelectAllSchemaChild);
}
