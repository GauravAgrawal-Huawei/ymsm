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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network4.rev20151208;

/**
 * Abstraction of an entity which represents the functionality of ietfNetwork4.
 */
public interface IetfNetwork4 {

    /**
     * Checks if the passed IetfNetwork4 maps the content match query condition.
     *
     * @param ietfNetwork4 ietfNetwork4 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    IetfNetwork4 processSubtreeFiltering(IetfNetwork4 ietfNetwork4, boolean isSelectAllSchemaChild);
}