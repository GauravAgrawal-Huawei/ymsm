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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Represents registered application's context for YANG schema registry.
 */
public class YsrRegisteredAppContext {

    /**
     * Current application's YANG schema node.
     */
    private YangSchemaNode curNode;

    /**
     * Current application's YANG schema node with different revision store.
     */
    private ConcurrentMap<String, YangSchemaNode> yangSchemaNodeConcurrentStore;

    /**
     * Current application's object.
     */
    private Object appObject;

    /**
     * YANG file set.
     */
    private Set<File> yangFileSet;

    /**
     * Jar file path.
     */
    private String jarPath;

    /**
     * If for current object notification is registered.
     */
    private boolean isNotificationRegistered;

    /**
     * Creates an instance of YANG schema registry application context.
     */
    YsrRegisteredAppContext() {
        yangSchemaNodeConcurrentStore = new ConcurrentHashMap<>();
        yangFileSet = new HashSet<>();
    }

    /**
     * Returns current application's object.
     *
     * @return current application's object
     */
    Object appObject() {
        return appObject;
    }

    /**
     * Sets current application's object.
     *
     * @param appObject current application's object
     */
    void appObject(Object appObject) {
        this.appObject = appObject;
    }

    /**
     * Returns current application's YANG schema node.
     *
     * @return current application's YANG schema node
     */
    YangSchemaNode curNode() {
        return curNode;
    }

    /**
     * Sets current application's schema node.
     *
     * @param node current schema's node
     */
    void curNode(YangSchemaNode node) {
        this.curNode = node;
    }

    /**
     * Returns yang file set.
     *
     * @return yang file set
     */
    private Set<File> yangFileSet() {
        return yangFileSet;
    }

    /**
     * Returns specific YANG file.
     *
     * @param yangFileName YANG file name
     * @return specific YANG file
     */
    public File yangFile(String yangFileName) {
        for (File file : yangFileSet()) {
            if (file.getName().equals(yangFileName)) {
                return file;
            }
        }
        return null;
    }

    /**
     * Adds to yang file set.
     *
     * @param yangFile YANG file
     */
    void addToYangFileSet(File yangFile) {
        yangFileSet().add(yangFile);
    }

    /**
     * Returns jar file path.
     *
     * @return jar file path
     */
    String jarPath() {
        return jarPath;
    }

    /**
     * Sets jar file path.
     *
     * @param jarPath jar file path
     */
    void jarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    @Override
    public int hashCode() {
        return Objects.hash(curNode, appObject, yangFileSet);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YsrRegisteredAppContext) {
            YsrRegisteredAppContext that = (YsrRegisteredAppContext) obj;
            return Objects.equals(curNode, that.curNode) &&
                    Objects.equals(appObject, that.appObject);
        }
        return false;
    }

    /**
     * Returns true if for application object notification is registered.
     *
     * @return true if for application object notification is registered
     */
    boolean isNotificationRegistered() {
        return isNotificationRegistered;
    }

    /**
     * Sets true if for application object notification is registered.
     *
     * @param notificationRegistered true if for application object notification is registered
     */
    void setNotificationRegistered(boolean notificationRegistered) {
        isNotificationRegistered = notificationRegistered;
    }

    /**
     * Returns YANG schema node store for specific revision.
     *
     * @return YANG schema node store for specific revision
     */
    public ConcurrentMap<String, YangSchemaNode> getYangSchemaNodeForRevisionStore() {
        return yangSchemaNodeConcurrentStore;
    }

    /**
     * Returns a schema node for specific revision from store.
     *
     * @param nodeNameWithRevision schema node name for specific revision
     * @return schema node for specific revision.
     */
    YangSchemaNode getSchemaNodeForRevisionStore(String nodeNameWithRevision) {
        if (getYangSchemaNodeForRevisionStore().containsKey(nodeNameWithRevision)) {
            return getYangSchemaNodeForRevisionStore().get(nodeNameWithRevision);
        }
        return null;
    }

    /**
     * Removes a schema node of specific revision from store.
     *
     * @param nodeNameWithRevision schema node name for specific revision
     */
    void removeSchemaNodeForRevisionStore(String nodeNameWithRevision) {
        if (getYangSchemaNodeForRevisionStore().containsKey(nodeNameWithRevision)) {
            getYangSchemaNodeForRevisionStore().remove(nodeNameWithRevision);
        }
    }

    /**
     * Adds schema node with revision from store.
     *
     * @param nodeNameWithRevision schema node name for specific revision
     * @param schemaNode           schema node name for specific revision
     */
    void addSchemaNodeWithRevisionStore(String nodeNameWithRevision, YangSchemaNode schemaNode) {
        getYangSchemaNodeForRevisionStore().put(nodeNameWithRevision, schemaNode);
    }
}
