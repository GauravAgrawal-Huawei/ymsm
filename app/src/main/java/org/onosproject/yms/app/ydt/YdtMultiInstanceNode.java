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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.onosproject.yangutils.datamodel.YangList;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yms.app.ydt.exceptions.YdtExceptions;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtType;

/**
 * Represents a multi instance node in YANG data tree.
 */
public class YdtMultiInstanceNode extends YdtNode {

    /**
     * Reference for list of key element's ydtContext.
     */
    private Set<YdtContext> keyNodeList = new HashSet<>();

    /**
     * Reference for composite key string for multi Instance Node..
     */
    private String compositeKeyString;

    /**
     * Returns the composite key string for current multi instance node.
     *
     * @return composite key string
     */
    public String getCompositeKeyString() {
        return compositeKeyString;
    }

    /**
     * Sets the composite key string for current multi instance node.
     *
     * @param compositeKeyString composite key string
     */
    private void setCompositeKeyString(String compositeKeyString) {
        this.compositeKeyString = compositeKeyString;
    }

    /**
     * Returns the list of key element's ydtContext.
     *
     * @return list of key element's ydtContext
     */
    public Set<YdtContext> getKeyNodeList() {
        return keyNodeList;
    }

    /**
     * Sets the list of key element's ydtContext.
     *
     * @param keyNodeList list of key element's ydtContext.
     */
    private void setKeyNodeList(Set<YdtContext> keyNodeList) {
        this.keyNodeList = keyNodeList;
    }

    /**
     * Creates a YANG multi instance node object.
     *
     * @param nodeIdentifier node identifier of YANG data tree multi instance node .
     */
    public YdtMultiInstanceNode(YangSchemaNodeIdentifier nodeIdentifier) {
        super(YdtType.MULTI_INSTANCE_NODE, nodeIdentifier);
    }

    @Override
    public void createKeyNodeList() {
        YangList yangListHolder = (YangList) this.getYangSchemaNode();
        List<String> schemaKeyList = yangListHolder.getKeyList();

        //Reference for composite key string.
        String keyString = "";

        /**
         * If key element not defined in schema or config is false then
         * return no need to do create key list.
         */
        if ((schemaKeyList == null) || (!yangListHolder.isConfig())) {
            return;
        }

        // Iterator for schema key name list.
        Iterator<String> it1 = schemaKeyList.iterator();

        String namespace = this.getYdtNodeIdentifier().getNameSpace();

        Set<YdtContext> keyNodeList = new HashSet<>();

        // This loop should run till schema key list are not finished.
        while (it1.hasNext()) {
            String name = it1.next();
            YangSchemaNodeIdentifier nodeIdentifier = new YangSchemaNodeIdentifier();
            nodeIdentifier.setNameSpace(namespace);
            nodeIdentifier.setName(name);
            List<YdtNode<?>> collidingChild = (List<YdtNode<?>>) ydtNodeMap.get(nodeIdentifier);

            if (collidingChild == null) {
                // Free resources
                freeRestResources();
                String errorInfo = yangListHolder.getParent().getName() + " is missing some of the keys of " +
                        yangListHolder.getName() + ".";
                throw new YdtExceptions(errorInfo);
            }

            YdtNode<?> ydtNode = collidingChild.get(0);
            /**
             * Preparing composite key string by concatenating values of
             * all the key leaf.
             */
            keyString = keyString + ydtNode.getValue() + " ";
            keyNodeList.add(ydtNode);
        }
        //Setting te key object in List.
        this.setKeyNodeList(keyNodeList);
        this.setCompositeKeyString(keyString);
    }
}
