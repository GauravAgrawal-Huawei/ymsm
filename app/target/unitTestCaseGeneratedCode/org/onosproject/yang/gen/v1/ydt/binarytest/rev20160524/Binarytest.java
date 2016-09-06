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

package org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524;

import java.util.List;
import org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524.binarytest.BinaryList;

/**
 * Abstraction of an entity which represents the functionality of binarytest.
 */
public interface Binarytest {

    /**
     * Returns the attribute binaryList.
     *
     * @return list of binaryList
     */
    List<BinaryList> binaryList();

    /**
     * Checks if the passed Binarytest maps the content match query condition.
     *
     * @param binarytest binarytest being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Binarytest processSubtreeFiltering(Binarytest binarytest, boolean isSelectAllSchemaChild);
}
