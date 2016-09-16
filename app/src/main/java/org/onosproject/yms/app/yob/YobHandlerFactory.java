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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.onosproject.yms.app.yob.YobConstants.YDT_TYPE_IS_NOT_SUPPORT;

/**
 * Represents an YANG object builder factory to create different types
 * of YANG data tree node.
 */
final class YobHandlerFactory {

    private static final Logger log =
            LoggerFactory.getLogger(YobSingleInstanceLeafHandler.class);

    /**
     * Create instance of YobHandlerFactory.
     */
    private YobHandlerFactory() {
    }

    /**
     * This function is used to return the corresponding YOB handler for current context.
     *
     * @param ydtExtendedContext ydtExtendedContext is used to get application
     *                           related information maintained in YDT
     * @return YANG object builder node
     */
    static YobHandler getYobHandlerForContext(YdtExtendedContext ydtExtendedContext) {
        switch (ydtExtendedContext.getYdtType()) {
            /**
             * Single instance node.
             */
            case SINGLE_INSTANCE_NODE: {
                return new YobSingleInstanceHandler();
            }

            /**
             * Multi instance node.
             */
            case MULTI_INSTANCE_NODE: {
                return new YobMultiInstanceHandler();
            }

            /**
             * Single instance leaf node.
             */
            case SINGLE_INSTANCE_LEAF_VALUE_NODE: {
                return new YobSingleInstanceLeafHandler();
            }

            /**
             * Multi instance leaf node.
             */
            case MULTI_INSTANCE_LEAF_VALUE_NODE: {
                return new YobMultiInstanceLeafHandler();
            }

            default: {
                log.error(YDT_TYPE_IS_NOT_SUPPORT);
            }
        }
        return null;
    }
}
