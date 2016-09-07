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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import org.onosproject.yangutils.datamodel.RpcNotificationContainer;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangRevision;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.exceptions.DataModelException;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.onosproject.yangutils.datamodel.utils.DataModelUtils.parseJarFile;
import static org.onosproject.yangutils.utils.UtilConstants.DEFAULT;
import static org.onosproject.yangutils.utils.UtilConstants.EVENT_STRING;
import static org.onosproject.yangutils.utils.UtilConstants.OP_PARAM;
import static org.onosproject.yangutils.utils.UtilConstants.PERIOD;
import static org.onosproject.yangutils.utils.io.impl.YangIoUtils.getCapitalCase;
import static org.osgi.framework.FrameworkUtil.getBundle;


/**
 * Represent YANG schema registry. Yang schema registry provides interface to
 * an application to register its YANG schema
 * with YMS. It provides YANG schema nodes to YDT, YNB and YSB.
 */
public class DefaultYangSchemaRegistry
        implements YangSchemaRegistry {

    private static final String SYSTEM = "/system/";
    private static final String MAVEN = "mvn:";
    private static final String HYPHEN = "-";
    private static final String DELIMITER = ".";
    private static final String SERVICE = "Service";
    private static final String SER = ".ser";
    private static final String JAR = ".jar";
    private static final String YANG = ".yang";
    private static final String USER_DIRECTORY = "user.dir";
    private static final String SLASH = File.separator;

    private static final Logger log =
            LoggerFactory.getLogger(DefaultYangSchemaRegistry.class);

    /**
     * Map for storing app objects.
     */
    private ConcurrentMap<String, YsrRegisteredAppContext> appObjectStore;

    /**
     * Map for storing YANG schema nodes.
     */
    private ConcurrentMap<String, YsrRegisteredAppContext> yangSchemaStore;

    /**
     * Map for storing YANG schema nodes with respect to root's generated
     * interface file name.
     */
    private ConcurrentMap<String, YsrRegisteredAppContext>
            yangSchemaStoreForRootInterface;

    /**
     * Map for storing YANG schema nodes root's generated op param file name.
     */
    private ConcurrentMap<String, YsrRegisteredAppContext>
            yangSchemaStoreForRootOpParam;

    /**
     * Map for storing YANG schema nodes with respect to notifications.
     */
    private ConcurrentMap<String, YsrRegisteredAppContext>
            yangSchemaNotificationStore;

    /**
     * Map for storing registered classes.
     */
    private ConcurrentMap<String, Class<?>> registerClassStore;

    /**
     * Context of application which is registering with YMS.
     */
    private YsrRegisteredAppContext ysrRegisteredAppContext;

    /**
     * Context of application which is registering with YMS with multiple
     * revision.
     */
    private YsrRegisteredAppContext ysrRegisteredAppContextForSchemaMap;

    /**
     * Context of application which is registering with YMS with multiple
     * manager object.
     */
    private YsrRegisteredAppContext ysrAppContextForApplicationStore;

    /**
     * Class loader of service application.
     */
    private ClassLoader classLoader;

    /**
     * Creates an instance of YANG schema registry.
     */
    public DefaultYangSchemaRegistry() {
        appObjectStore = new ConcurrentHashMap<>();
        yangSchemaStore = new ConcurrentHashMap<>();
        yangSchemaStoreForRootInterface = new ConcurrentHashMap<>();
        yangSchemaStoreForRootOpParam = new ConcurrentHashMap<>();
        yangSchemaNotificationStore = new ConcurrentHashMap<>();
        registerClassStore = new ConcurrentHashMap<>();
    }

    /**
     * Returns app object store.
     *
     * @return app object store
     */
    private ConcurrentMap<String, YsrRegisteredAppContext> getAppObjectStore() {
        return appObjectStore;
    }

    /**
     * Returns register class store.
     *
     * @return register class store.
     */
    private ConcurrentMap<String, Class<?>> getRegisterClassStore() {
        return registerClassStore;
    }

    /**
     * Returns schema store.
     *
     * @return schema store
     */
    private ConcurrentMap<String, YsrRegisteredAppContext> getYangSchemaStore() {
        return yangSchemaStore;
    }

    /**
     * Returns schema store.
     *
     * @return schema store
     */
    private ConcurrentMap<String, YsrRegisteredAppContext>
    getYangSchemaStoreForRootInterface() {
        return yangSchemaStoreForRootInterface;
    }

    /**
     * Returns schema store.
     *
     * @return schema store
     */
    private ConcurrentMap<String, YsrRegisteredAppContext>
    getYangSchemaStoreForRootOpParam() {
        return yangSchemaStoreForRootOpParam;
    }

    /**
     * Returns schema notification store.
     *
     * @return schema notification store
     */
    private ConcurrentMap<String, YsrRegisteredAppContext>
    getYangSchemaNotificationStore() {
        return yangSchemaNotificationStore;
    }

    @Override
    public void registerApplication(Object appObject, Class<?> serviceClass) {

        BundleContext bundleContext =
                getBundle(serviceClass).getBundleContext();
        String jarPath = getJarPathFromBundleLocation(
                bundleContext.getBundle().getLocation(),
                bundleContext.getProperty(USER_DIRECTORY));
        processRegistration(serviceClass, appObject, jarPath);
    }

    /**
     * Process application registration.
     *
     * @param serviceClass service class
     * @param appObject    application object
     * @param jarPath      jar path
     */
    void processRegistration(Class<?> serviceClass, Object appObject,
                             String jarPath) {

        // set class loader for service class.
        setClassLoader(serviceClass.getClassLoader());

        //Check if service should be registered?
        verifyApplicationRegistration(appObject, serviceClass);

        //Add app class to registered serice store.
        if (!getRegisterClassStore().containsKey(serviceClass.getName())) {
            updateServiceClass(serviceClass);
        }

        // process storing operations.
        if (!verifyIfApplicationAlreadyRegistered(serviceClass)) {
            List<YangNode> curNodes =
                    processJarParsingOperations(jarPath);
            for (YangSchemaNode schemaNode : curNodes) {
                processApplicationContext(schemaNode);
            }
            ysrAppContext().jarPath(jarPath);
            ysrAppContextForSchemaStore().jarPath(jarPath);
            ysrAppContextForApplicationStore().jarPath(jarPath);
            //Store the YANG file handles.
            updateYangFileSet(jarPath);
        }

        //Verifies if object is updated for app store.
        updateApplicationObject(appObject, serviceClass);
    }

    /**
     * Verifies if service class should be registered or not.
     *
     * @param appObject application object
     * @param appClass  application class
     */
    private void verifyApplicationRegistration(Object appObject,
                                               Class<?> appClass) {
        Class<?> managerClass = appObject.getClass();
        Class<?>[] services = managerClass.getInterfaces();
        List<Class<?>> classes = new ArrayList<>();
        Collections.addAll(classes, services);
        if (!classes.contains(appClass)) {
            throw new RuntimeException(
                    "YSR: service class " + appClass.getName()
                            + "is not being implemented by "
                            + managerClass.getName());
        }
    }

    /**
     * Verifies if application is already registered with YMS.
     *
     * @param appClass application class
     * @return true if application already registered
     */
    private boolean verifyIfApplicationAlreadyRegistered(Class<?> appClass) {
        String appName = appClass.getSimpleName();
        if (!getAppObjectStore().containsKey(appClass.getName())) {
            if (appName.contains(OP_PARAM)) {
                return getYangSchemaStoreForRootOpParam()
                        .containsKey(appClass.getName());
            } else {
                return getYangSchemaStoreForRootInterface()
                        .containsKey(appClass.getName());
            }
        }
        return true;
    }

    /**
     * Verifies if service is being implemented by some new object.
     *
     * @param appObject application's object
     * @param appClass  application's class
     */
    private void updateApplicationObject(Object appObject, Class<?> appClass) {
        if (getAppObjectStore().containsKey(appClass.getName())) {
            YsrRegisteredAppContext appContext =
                    getAppObjectStore().get(appClass.getName());
            YangSchemaNode schemaNode = appContext.curNode();
            String name = schemaNode.getJavaPackage() + PERIOD +
                    getCapitalCase(schemaNode.getJavaClassNameOrBuiltInType());
            if (appContext.appObject() == null) {
                //update in application store.
                appContext.appObject(appObject);
                if (getYangSchemaStoreForRootInterface().containsKey(name)) {
                    getYangSchemaStoreForRootInterface().get(name)
                            .appObject(appObject);
                }
                if (getYangSchemaStoreForRootOpParam()
                        .containsKey(name + OP_PARAM)) {
                    getYangSchemaStoreForRootOpParam().get(name + OP_PARAM)
                            .appObject(appObject);
                }
            }
        }
    }

    @Override
    public void unRegisterApplication(Object managerObject,
                                      Class<?> serviceClass) {
        YangSchemaNode curNode = null;
        String appName = serviceClass.getName();

        //Remove registered class from store.
        if (getRegisterClassStore().containsKey(serviceClass.getName())) {
            getRegisterClassStore().remove(serviceClass.getName());
        }

        //check if service is in app store.
        if (getAppObjectStore().containsKey(serviceClass.getName())) {
            curNode = retrieveNodeForUnregister(appName, getAppObjectStore());
        } else if (getYangSchemaStoreForRootInterface()
                .containsKey(serviceClass.getName())) {
            //check if service is in interface store.
            curNode = retrieveNodeForUnregister(appName,
                                                getYangSchemaStoreForRootInterface());
        } else if (getYangSchemaStoreForRootOpParam()
                .containsKey(serviceClass.getName())) {
            //check if service is in op param store.
            curNode = retrieveNodeForUnregister(appName,
                                                getYangSchemaStoreForRootOpParam());
        }
        if (curNode != null) {
            String javaName = curNode.getJavaPackage() + PERIOD +
                    getCapitalCase(curNode.getJavaClassNameOrBuiltInType());
            removeFromYangSchemaStore(curNode);
            removeFromYangNotificationStore(curNode, javaName);
            removeFromAppSchemaStore(appName);
            removeFromYangSchemaNodeForRootInterface(javaName);
            removeFromYangSchemaNodeForRootOpParam(javaName);
            log.info("YSR: service " + serviceClass.getSimpleName() +
                             " is unregistered.");
        } else {
            log.error("YSR: service " + serviceClass.getSimpleName() + " was " +
                              "not registered.");
        }
    }

    @Override
    public Object getRegisteredApplication(YangSchemaNode schemaNode) {
        if (schemaNode != null) {
            String name = schemaNode.getJavaPackage() + PERIOD +
                    getCapitalCase(schemaNode.getJavaClassNameOrBuiltInType());
            if (getYangSchemaStoreForRootInterface().containsKey(name)) {
                return getYangSchemaStoreForRootInterface().get(name)
                        .appObject();
            }
            log.error("YSR: " + name + " not found.");
        }
        return null;
    }

    @Override
    public YangSchemaNode getYangSchemaNodeUsingSchemaName(String schemaName) {
        return getSchemaNodeUsingSchemaNameWithRev(schemaName);
    }

    @Override
    public YangSchemaNode getYangSchemaNodeUsingAppName(String appName) {
        if (getAppObjectStore().containsKey(appName)) {
            return getAppObjectStore().get(appName).curNode();
        }
        log.error("YSR: " + appName + " not found.");
        return null;
    }

    @Override
    public YangSchemaNode
    getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
            String rootInterfaceFileName) {
        if (getYangSchemaStoreForRootInterface()
                .containsKey(rootInterfaceFileName)) {
            return getYangSchemaStoreForRootInterface()
                    .get(rootInterfaceFileName).curNode();
        }
        log.error("YSR: " + rootInterfaceFileName + " not found.");
        return null;
    }

    @Override
    public YangSchemaNode getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
            String rootOpParamFileName) {
        if (getYangSchemaStoreForRootOpParam()
                .containsKey(rootOpParamFileName)) {
            return getYangSchemaStoreForRootOpParam().get(rootOpParamFileName)
                    .curNode();
        }
        log.error("YSR: " + rootOpParamFileName + " not found.");
        return null;
    }

    @Override
    public YangSchemaNode getRootYangSchemaNodeForNotification(
            String eventSubject) {
        if (getYangSchemaNotificationStore().containsKey(eventSubject)) {
            return getYangSchemaNotificationStore().get(eventSubject).curNode();
        }
        log.error("YSR: " + eventSubject + " not found.");
        return null;
    }

    public Class<?> getRegisteredClass(YangSchemaNode schemaNode,
                                       String appName) {
        String interfaceName = schemaNode.getJavaPackage() + PERIOD +
                getCapitalCase(schemaNode.getJavaClassNameOrBuiltInType());
        String serviceName = interfaceName + SERVICE;
        String defaultClass;
        if (schemaNode instanceof RpcNotificationContainer) {
            defaultClass = schemaNode.getJavaPackage()
                    + getCapitalCase(schemaNode.getJavaClassNameOrBuiltInType()
                                             + OP_PARAM);
        } else {
            defaultClass = schemaNode.getJavaPackage() + PERIOD
                    + getCapitalCase(DEFAULT) +
                    getCapitalCase(schemaNode.getJavaClassNameOrBuiltInType());
        }
        //If application class is registered.
        if (getRegisterClassStore().containsKey(appName)) {
            return getRegisterClassStore().get(appName);
        } else if (getRegisterClassStore().containsKey(interfaceName)) {
            //If interface class is registered.
            return getRegisterClassStore().get(interfaceName);
        } else if (getRegisterClassStore().containsKey(serviceName)) {
            //If service class is registered.
            return getRegisterClassStore().get(serviceName);
        } else if (getRegisterClassStore().containsKey(defaultClass)) {
            //If default class is registered.
            return getRegisterClassStore().get(defaultClass);
        }
        return null;
    }

    /**
     * Returns notification schema store.
     *
     * @return notification schema store
     */
    private ConcurrentMap<String, YsrRegisteredAppContext>
    getNotificationSchemaMap() {
        return getYangSchemaNotificationStore();
    }

    /**
     * Updates service class store.
     *
     * @param serviceClass service class
     */
    void updateServiceClass(Class<?> serviceClass) {
        getRegisterClassStore().put(serviceClass.getName(), serviceClass);
    }

    /**
     * Updates application object store.
     *
     * @param appName application name
     */
    private void updateAppObjectStore(String appName) {
        if (verifyClassExistence(appName)) {
            getAppObjectStore()
                    .put(appName, ysrAppContextForApplicationStore());
        }
    }

    /**
     * Updates YANG schema object store.
     *
     * @param schemaNode application's schema node
     */
    private void updateYangSchemaStore(YangSchemaNode schemaNode) {
        addSchemaNodeUsingSchemaNameWithRev(schemaNode);
    }

    /**
     * Updates YANG schema notification object store.
     *
     * @param notificationName application's notification name
     */
    private void updateYangNotificationStore(String notificationName) {
        if (verifyClassExistence(notificationName)) {
            getYangSchemaNotificationStore()
                    .put(notificationName, ysrAppContext());
        }
    }

    /**
     * Updates YANG schema object store for root interface file name.
     *
     * @param rootInterfaceFileName name of generated interface file for root
     *                              node
     */
    private void updateYangSchemaForRootInterfaceFileNameStore(
            String rootInterfaceFileName) {
        if (verifyClassExistence(rootInterfaceFileName)) {
            getYangSchemaStoreForRootInterface()
                    .put(rootInterfaceFileName, ysrAppContext());
        }
    }

    /**
     * Updates YANG schema object store  for root op param file name.
     *
     * @param rootOpParamFileName name of generated op param file for root node
     */
    private void updateYangSchemaForRootOpParamFileNameStore(
            String rootOpParamFileName) {
        if (verifyClassExistence(rootOpParamFileName)) {
            getYangSchemaStoreForRootOpParam()
                    .put(rootOpParamFileName, ysrAppContext());
        }
    }

    /**
     * Updates the YANG file set.
     *
     * @param path YANG file path
     */
    private void updateYangFileSet(String path) {
        File yangFilePath = new File(path);
        File[] fileArray = yangFilePath.listFiles();
        if (fileArray != null) {
            for (File curFile : fileArray) {
                if (curFile.getName().endsWith(YANG)) {
                    ysrAppContext().addToYangFileSet(curFile);
                    ysrAppContextForSchemaStore()
                            .addToYangFileSet(curFile);
                    ysrAppContextForApplicationStore()
                            .addToYangFileSet(curFile);
                }
            }
        }
    }

    /**
     * Process jar file for fetching YANG nodes.
     *
     * @param path jar file path
     * @return YANG schema nodes
     */
    private List<YangNode> processJarParsingOperations(String path) {
        //Deserialize data model and get the YANG node set.
        try {
            return parseJarFile(path + JAR, path);
        } catch (IOException e) {
            log.error("YSR: failed to parse the jar file in path " + path);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Process an application an updates the maps for YANG schema registry.
     *
     * @param appNode application YANG schema nodes
     */
    void processApplicationContext(YangSchemaNode appNode) {

        String appName = appNode.getJavaPackage() + PERIOD +
                getCapitalCase(appNode.getJavaClassNameOrBuiltInType());

        //Create a new instance of ysr app context for each node.
        ysrAppContext(new YsrRegisteredAppContext());
        ysrAppContextForSchemaStore(new YsrRegisteredAppContext());
        ysrAppContextForApplicationStore(new YsrRegisteredAppContext());

        //add cur node to app context.
        ysrAppContext().curNode(appNode);
        ysrAppContextForApplicationStore().curNode(appNode);

        //Updates maps wih schema nodes
        updateAppObjectStore(appName + SERVICE);

        // Updates schema store
        updateYangSchemaStore(appNode);
        // update interface store
        updateYangSchemaForRootInterfaceFileNameStore(appName);
        //update op param store
        updateYangSchemaForRootOpParamFileNameStore(appName + OP_PARAM);
        //Checks if notification is present then update notification store map.
        String eventSubject = null;
        try {
            if (appNode.isNotificationPresent()) {
                eventSubject = appName.toLowerCase() + PERIOD +
                        getCapitalCase(
                                appNode.getJavaClassNameOrBuiltInType()) +
                        EVENT_STRING;
            }
        } catch (DataModelException e) {
            e.printStackTrace();
        }
        if (eventSubject != null) {
            updateYangNotificationStore(eventSubject);
        }
        log.info("YSR: successfully registered this application " + appName +
                         SERVICE);

    }

    /**
     * Returns jar path from bundle mvnLocationPath.
     *
     * @param mvnLocationPath mvnLocationPath of bundle
     * @return path of jar
     */
    private String getJarPathFromBundleLocation(String mvnLocationPath,
                                                String currentDirectory) {
        String path = currentDirectory + SYSTEM;
        String[] strArray = mvnLocationPath.split(MAVEN);
        String[] split = strArray[1].split(File.separator);
        String[] groupId = split[0].split(Pattern.quote(DELIMITER));

        path = path + groupId[0] + SLASH + groupId[1] + SLASH + split[1] +
                SLASH + split[2] + SLASH + split[1] + HYPHEN + split[2];
        return path;
    }

    /**
     * Returns de-serializes YANG data-model nodes.
     *
     * @param serializedFileInfo serialized File Info
     * @return de-serializes YANG data-model nodes
     */
    Set<YangSchemaNode> deSerializeDataModel(String serializedFileInfo) {

        Set<YangSchemaNode> nodes = new HashSet<>();
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(serializedFileInfo);
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(fileInputStream);
            nodes = (Set<YangSchemaNode>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error(serializedFileInfo + " not found.");
        }

        return nodes;
    }

    /**
     * Returns ysr app context.
     *
     * @return ysr app context
     */
    YsrRegisteredAppContext ysrAppContext() {
        return ysrRegisteredAppContext;
    }

    /**
     * Sets ysr app context.
     *
     * @param ysrRegisteredAppContext ysr app context
     */
    private void ysrAppContext(
            YsrRegisteredAppContext ysrRegisteredAppContext) {
        this.ysrRegisteredAppContext = ysrRegisteredAppContext;
    }

    /**
     * Returns schema node based on the revision.
     *
     * @param name name of the schema node
     * @return schema node based on the revision.
     */
    private YangSchemaNode getSchemaNodeUsingSchemaNameWithRev(String name) {
        YsrRegisteredAppContext appContext;
        YangSchemaNode schemaNode;
        if (name.contains("@")) {
            String[] revArray = name.split("@");
            appContext = getYangSchemaStore().get(revArray[0]);
            schemaNode = appContext.getSchemaNodeForRevisionStore(name);
            if (schemaNode != null) {
                return schemaNode;
            }
            return appContext.curNode();
        }
        if (getYangSchemaStore().containsKey(name)) {
            appContext = getYangSchemaStore().get(name);
            if (appContext != null) {
                Iterator<YangSchemaNode> iterator =
                        appContext.getYangSchemaNodeForRevisionStore().values()
                                .iterator();
                if (iterator.hasNext()) {
                    return appContext.getYangSchemaNodeForRevisionStore()
                            .values()
                            .iterator().next();
                } else {
                    return null;
                }
            }
        }
        log.error("YSR: " + name + " not found.");
        return null;
    }

    /**
     * Adds schema node when different revision of node has received.
     *
     * @param schemaNode schema node
     */
    private void addSchemaNodeUsingSchemaNameWithRev(
            YangSchemaNode schemaNode) {

        String date = getDateInStringFormat(schemaNode);
        String name = schemaNode.getName();
        if (!date.equals("")) {
            name = name + "@" + date;
        }
        //check if already present.
        if (!getYangSchemaStore().containsKey(schemaNode.getName())) {
            ysrAppContextForSchemaStore().curNode(schemaNode);
            //if revision is not present no need to add in revision store.
            ysrAppContextForSchemaStore()
                    .addSchemaNodeWithRevisionStore(name, schemaNode);
            getYangSchemaStore().put(schemaNode.getName(),
                                     ysrAppContextForSchemaStore());
        } else {
            YsrRegisteredAppContext appContext =
                    getYangSchemaStore().get(schemaNode.getName());
            appContext.addSchemaNodeWithRevisionStore(name, schemaNode);
            appContext.curNode(schemaNode);
        }
    }

    /**
     * Returns date in string format.
     *
     * @param schemaNode schema node
     * @return date in string format.
     */
    String getDateInStringFormat(YangSchemaNode schemaNode) {
        if (schemaNode != null) {
            if (((YangNode) schemaNode).getRevision() != null) {
                return new SimpleDateFormat("yyyy-mm-dd")
                        .format(((YangNode) schemaNode).getRevision()
                                        .getRevDate());
            }
        }
        return "";
    }

    /**
     * Removes schema node from schema map.
     *
     * @param removableNode schema node which needs to be removed
     */
    private void removeSchemaNode(YangSchemaNode removableNode) {

        YangRevision removeNodeRev = ((YangNode) removableNode).getRevision();
        String name = removableNode.getName();
        if (removeNodeRev != null) {
            name = removableNode.getName() + "@" +
                    getDateInStringFormat(removableNode);
        }
        YsrRegisteredAppContext appContext = getYangSchemaStore()
                .get(removableNode.getName());
        if (!appContext.getYangSchemaNodeForRevisionStore().isEmpty()
                && appContext.getYangSchemaNodeForRevisionStore().size() != 1) {
            appContext.removeSchemaNodeForRevisionStore(name);
        } else {
            getYangSchemaStore().remove(removableNode.getName());
        }
    }

    /**
     * Verifies if the manager object is already registered with notification
     * handler.
     *
     * @param serviceClass service class
     * @return true if the manager object is already registered with
     * notification handler.
     */
    public boolean verifyNotificationObject(Class<?> serviceClass) {
        YangSchemaNode schemaNode = null;
        if (getAppObjectStore().containsKey(serviceClass.getName())) {
            schemaNode = getYangSchemaNodeUsingAppName(serviceClass.getName());
        } else if (getYangSchemaStoreForRootInterface()
                .containsKey(serviceClass.getName())) {
            schemaNode =
                    getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                            serviceClass.getName());
        } else if (getYangSchemaStoreForRootOpParam()
                .containsKey(serviceClass.getName())) {
            schemaNode = getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                    serviceClass.getName());
        }

        if (schemaNode != null) {
            String name = (schemaNode.getJavaPackage() + PERIOD +
                    schemaNode.getJavaClassNameOrBuiltInType() + PERIOD)
                    .toLowerCase()
                    +
                    getCapitalCase(schemaNode.getJavaClassNameOrBuiltInType()) +
                    EVENT_STRING;

            if (getNotificationSchemaMap().containsKey(name)) {
                if (!getNotificationSchemaMap().get(name)
                        .isNotificationRegistered()) {
                    getNotificationSchemaMap().get(name)
                            .setNotificationRegistered(true);
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * Returns YSR application context for schema map.
     *
     * @return YSR application context for schema map
     */
    YsrRegisteredAppContext ysrAppContextForSchemaStore() {
        return ysrRegisteredAppContextForSchemaMap;
    }

    /**
     * Sets YSR application context for schema map.
     *
     * @param ysrRegisteredAppContextForSchemaMap YSR application context for
     *                                            schema map
     */
    void ysrAppContextForSchemaStore(
            YsrRegisteredAppContext ysrRegisteredAppContextForSchemaMap) {
        this.ysrRegisteredAppContextForSchemaMap =
                ysrRegisteredAppContextForSchemaMap;
    }

    /**
     * Returns YSR app context for application store.
     *
     * @return YSR app context for application store
     */
    YsrRegisteredAppContext ysrAppContextForApplicationStore() {
        return ysrAppContextForApplicationStore;
    }

    /**
     * Sets YSR app context for application store.
     *
     * @param ysrAppContextForApplicationMap YSR app context for application
     *                                       store
     */
    private void ysrAppContextForApplicationStore(
            YsrRegisteredAppContext ysrAppContextForApplicationMap) {
        this.ysrAppContextForApplicationStore = ysrAppContextForApplicationMap;
    }

    /**
     * Retrieves schema node from the store and deletes jar file path.
     *
     * @param appName application name
     * @param store   YSR stores
     * @return schema node from the store
     */
    private YangSchemaNode retrieveNodeForUnregister(String appName,
                                                     ConcurrentMap<String,
                                                             YsrRegisteredAppContext> store) {
        YsrRegisteredAppContext curContext = store.get(appName);
        YangSchemaNode curNode = curContext.curNode();
        //Delete all the generated ysr information in application's package.
        if (curContext.jarPath() != null) {
            File jarPath = new File(curContext.jarPath());
            if (jarPath.exists()) {
                try {
                    deleteDirectory(new File(curContext.jarPath()));
                } catch (IOException e) {
                    log.error("failed to delete ysr resources for " + appName);
                    e.printStackTrace();
                }
            }
        }
        return curNode;
    }

    /**
     * Removes from YANG schema store.
     *
     * @param curNode schema node
     */
    private void removeFromYangSchemaStore(YangSchemaNode curNode) {
        if (getYangSchemaStore().containsKey(curNode.getName())) {
            removeSchemaNode(curNode);
        }
    }

    /**
     * Removes from YANG schema  notification store.
     *
     * @param curNode schema node
     */
    private void removeFromYangNotificationStore(YangSchemaNode curNode,
                                                 String appName) {
        appName = appName.toLowerCase() + PERIOD +
                getCapitalCase(curNode.getJavaClassNameOrBuiltInType()) +
                EVENT_STRING;
        if (getYangSchemaNotificationStore()
                .containsKey(appName)) {
            getYangSchemaNotificationStore()
                    .remove(appName);
        }
    }

    /**
     * Removes from app store.
     *
     * @param appName application name
     */
    private void removeFromAppSchemaStore(String appName) {
        if (getAppObjectStore().containsKey(appName)) {
            getAppObjectStore().remove(appName);
        }
    }

    /**
     * Removes from interface store.
     *
     * @param appName application name
     */
    private void removeFromYangSchemaNodeForRootInterface(String appName) {
        if (getYangSchemaStoreForRootInterface()
                .containsKey(appName)) {
            getYangSchemaStoreForRootInterface().remove(appName);
        }
    }

    /**
     * Removes from op param store.
     *
     * @param appName application name
     */
    private void removeFromYangSchemaNodeForRootOpParam(String appName) {
        if (getYangSchemaStoreForRootOpParam()
                .containsKey(appName + OP_PARAM)) {
            getYangSchemaStoreForRootOpParam()
                    .remove(appName + OP_PARAM);
        }
    }

    /**
     * Returns class loader of service class.
     *
     * @return class loader of service class
     */
    private ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * Sets class loader of service class.
     *
     * @param classLoader class loader of service class
     */
    void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * Verifies if class with given name exists.
     *
     * @param appName application name
     * @return true if class exists
     */
    boolean verifyClassExistence(String appName) {
        try {
            getClassLoader().loadClass(appName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
