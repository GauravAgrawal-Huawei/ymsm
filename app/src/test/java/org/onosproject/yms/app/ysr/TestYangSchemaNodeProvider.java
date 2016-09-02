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

package org.onosproject.yms.app.ysr;

import org.onosproject.yangutils.datamodel.YangSchemaNode;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.onosproject.yangutils.utils.UtilConstants.TEMP;
import static org.onosproject.yangutils.utils.io.impl.YangIoUtils.deleteDirectory;

/**
 * Represents mock bundle context. provides bundle context for YSR to do unit testing.
 */
public class TestYangSchemaNodeProvider {

    private static final String PATH = System.getProperty("user.dir")
            + File.separator + "target/classes/";
    private static final String SER_FILE_PATH = "yang/resources/YangMetaData.ser";
    private static final String TEMP_FOLDER_PATH = PATH + TEMP;
    private DefaultYangSchemaRegistry defaultYangSchemaRegistry = new DefaultYangSchemaRegistry();

    /**
     * Creates an instance of mock bundle context.
     */
    public TestYangSchemaNodeProvider() {
    }

    /**
     * Process YANG schema node for a application.
     *
     * @param appObject application object
     */
    public void processSchemaRegistry(Object appObject) {

        //Update schema registry.
        YsrRegisteredAppContext ysrRegisteredAppContext = new YsrRegisteredAppContext();
        defaultYangSchemaRegistry.ysrRegisteredAppContext(ysrRegisteredAppContext);

        Set<YangSchemaNode> appNode = defaultYangSchemaRegistry.deSerializeDataModel(PATH + SER_FILE_PATH);
        for (YangSchemaNode node : appNode) {
            defaultYangSchemaRegistry.processApplicationContext(node, appObject);
        }

        try {
            deleteDirectory(TEMP_FOLDER_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unregisters services.
     *
     * @param appName application name
     */
    public void unregisterService(String appName) {

        try {
            Class<?> curClass = Class.forName(appName);
            getDefaultYangSchemaRegistry().unRegisterApplication(null, curClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns schema registry.
     *
     * @return schema registry
     */
    public DefaultYangSchemaRegistry getDefaultYangSchemaRegistry() {
        return defaultYangSchemaRegistry;
    }
}
