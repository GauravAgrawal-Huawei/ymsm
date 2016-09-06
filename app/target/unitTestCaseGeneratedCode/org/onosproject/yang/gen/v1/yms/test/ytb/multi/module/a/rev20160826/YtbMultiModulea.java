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

package org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.a.rev20160826;

import java.util.List;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.a.rev20160826.ytbmultimodulea.Ytbmultilist;

/**
 * Abstraction of an entity which represents the functionality of ytbMultiModulea.
 */
public interface YtbMultiModulea {

    /**
     * Returns the attribute ytbmultilist.
     *
     * @return list of ytbmultilist
     */
    List<Ytbmultilist> ytbmultilist();

    /**
     * Checks if the passed YtbMultiModulea maps the content match query condition.
     *
     * @param ytbMultiModulea ytbMultiModulea being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    YtbMultiModulea processSubtreeFiltering(YtbMultiModulea ytbMultiModulea, boolean isSelectAllSchemaChild);
}