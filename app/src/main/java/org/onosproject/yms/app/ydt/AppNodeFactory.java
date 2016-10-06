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

/**
 * Represents an application tree node factory to create different types of
 * application tree node.
 */
public final class AppNodeFactory {

    /**
     * No instantiation.
     */
    private AppNodeFactory() {
    }

    /**
     * Returns the application context by creating an instance of the
     * application context for requested node based on the isContextSwitch flag,
     * if it is set that means its an augmented node so augmented application
     * context will be sent else it will be treated as new application module
     * node request.
     *
     * @param flag to identify request type is augment/module
     * @return appContext application context
     */
    public static DefaultYdtAppContext getAppContext(boolean flag) {
        return flag ? DefaultYdtAppContext.getAugmentAppContext() :
                DefaultYdtAppContext.getModuleAppContext();
    }
}
