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


package org.onosproject.yms.app.ydt;

import org.onosproject.yms.ydt.YdtContextOperationType;

import static org.onosproject.yms.app.ydt.YdtAppNodeOperationType.DELETE_ONLY;
import static org.onosproject.yms.app.ydt.YdtAppNodeOperationType.OTHER_EDIT;

/**
 * Utils to support yang data tree node creation.
 */
final class YdtUtils {

    //No instantiation.
    private YdtUtils() {
    }

    /**
     * Returns the app tree operation type with the help of YdtOperation type.
     *
     * @param opType ydt operation type
     * @return app tree operation type
     */
    protected static YdtAppNodeOperationType getAppOpTypeFromYdtOpType(
            YdtContextOperationType opType) {
        // Get the app tree operation type.
        switch (opType) {
            case CREATE:
            case MERGE:
            case REPLACE:
                return OTHER_EDIT;

            case DELETE:
            case REMOVE:
                return DELETE_ONLY;

            default:
                return null;
            //TODO handle the default data type.
        }
    }
}
