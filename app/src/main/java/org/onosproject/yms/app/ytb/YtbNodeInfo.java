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

package org.onosproject.yms.app.ytb;

import java.util.Iterator;
import org.onosproject.yangutils.datamodel.YangSchemaNodeType;

/**
 * Represents YTB node info for all the nodes that are added to the YDT builder tree.
 * Contains the information which can be attached and retrieved back from YDT while walking.
 */
public class YtbNodeInfo {

    /**
     * Creates a default constructor for YTB node info.
     */
    public YtbNodeInfo() {
    }

    /**
     * The YANG schema node type, such as multi instance or single instance, is bound to the YDT node.
     * When ever multi instance walking happens this is taken to iterate through the node.
     */
    private YangSchemaNodeType schemaNodeType;

    /**
     * Object of the corresponding YANG construct. This object is bound to each and every YDT node.
     * So, whenever walk of parent and sibling happens, object can be retrieved from its YDT node.
     */
    private Object yangObject;

    /**
     * The list iterator since first content of the multi instance node is faced. With this iterator the
     * node can be walked multiple times till it becomes empty.
     */
    private Iterator<Object> currentListIterator;

    /**
     * Returns schema node type of the YANG schema node.
     *
     * @return schema node type
     */
    public YangSchemaNodeType getSchemaNodeType() {
        return schemaNodeType;
    }

    /**
     * Sets the schema node type of the YANG schema node.
     *
     * @param schemaNodeType schema node type
     */
    public void setSchemaNodeType(YangSchemaNodeType schemaNodeType) {
        this.schemaNodeType = schemaNodeType;
    }

    /**
     * Returns the object of the YANG schema node.
     *
     * @return YANG node object
     */
    public Object getYangObject() {
        return yangObject;
    }

    /**
     * Sets the object of the YANG schema node.
     *
     * @param yangObject YANG node object
     */
    public void setYangObject(Object yangObject) {
        this.yangObject = yangObject;
    }

    /**
     * Returns the current list iterator of the YANG schema node.
     *
     * @return current list iterator for the schema node
     */
    public Iterator<Object> getCurrentListIterator() {
        return currentListIterator;
    }

    /**
     * Sets the current list iterator of the YANG schema node.
     *
     * @param currentListIterator current list iterator for the schema node
     */
    public void setCurrentListIterator(Iterator<Object> currentListIterator) {
        this.currentListIterator = currentListIterator;
    }
}
