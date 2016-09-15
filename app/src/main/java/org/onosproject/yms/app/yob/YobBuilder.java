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

package org.onosproject.yms.app.yob;

import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;

/**
 * Abstraction of an entity which provides interfaces to YANG object
 * builder.
 */
interface YobBuilder {

    /**
     * Returns the YANG object. This will be called by NBI broker.
     *
     * @param ydtExtendedContext ydtExtendedContext is used to get application
     *                           related information maintained in YDT
     * @return YANG builder object
     */
    Object getYangObject(YdtExtendedContext ydtExtendedContext,
                         YangSchemaRegistry schemaRegistry);
}

