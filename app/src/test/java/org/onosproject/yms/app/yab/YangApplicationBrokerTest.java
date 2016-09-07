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

package org.onosproject.yms.app.yab;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.Test;
import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ydt.YdtAppContext;
import org.onosproject.yms.app.ydt.YdtAppNodeOperationType;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YmsOperationType;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.onosproject.yms.ydt.YdtContextOperationType.DELETE;
import static org.onosproject.yms.ydt.YdtContextOperationType.MERGE;

/**
 * Unit test case for YANG application broker.
 */
public class YangApplicationBrokerTest {

    YmsManagerTest ymsManager = new YmsManagerTest();

    @After
    public void unregisterService() {
        ymsManager.unregisterService(
                "org.onosproject.yang.gen.v1.ydt.test6.rev20160524.Test6");
    }

    /**
     * Returns YANG data tree to check edit operation of container.
     *
     * @return YANG data tree
     */
    public YangRequestWorkBench buildYdtForEditOperationWithoutDelete()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "root";
        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.EDIT_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6", MERGE);
        defaultYdtBuilder.addChild("cont1", null, MERGE);
        defaultYdtBuilder.addChild("cont2", null, MERGE);
        defaultYdtBuilder.addChild("cont3", null, MERGE);
        defaultYdtBuilder.addLeaf("leaf1", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf4", null, "4");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("cont4", null, MERGE);
        defaultYdtBuilder.addChild("cont5", null, MERGE);
        defaultYdtBuilder.addLeaf("leaf9", null, "9");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf10", null, "10");
        return defaultYdtBuilder;
    }

    private YangRequestWorkBench buildYdtForKeyLeavesInDeleteTree() {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "root";
        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.EDIT_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6", MERGE);
        defaultYdtBuilder.addChild("list2", null, MERGE);
        defaultYdtBuilder.addLeaf("leaf5", null, "5");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf6", null, "6");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf7", null, "7");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("cont7", null, DELETE);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        return defaultYdtBuilder;
    }

    /**
     * Returns YANG data tree to check delete operation of container.
     *
     * @return YANG data tree
     */
    private YangRequestWorkBench buildYdtForEditOperationWithDelete()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "rootNode";
        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.EDIT_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6", MERGE);
        defaultYdtBuilder.addChild("cont1", null, MERGE);
        defaultYdtBuilder.addChild("cont2", null, DELETE);
        defaultYdtBuilder.addChild("cont3", null, DELETE);
        defaultYdtBuilder.addLeaf("leaf1", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf4", null, "4");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("cont4", null, DELETE);
        defaultYdtBuilder.addChild("cont5", null, DELETE);
        defaultYdtBuilder.addLeaf("leaf9", null, "9");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf10", null, "10");
        return defaultYdtBuilder;
    }

    /**
     * Returns YANG data tree to check edit operation of list.
     *
     * @return YANG data tree
     */
    private YangRequestWorkBench buildYdtForListEditOperationWithoutDelete()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "listWithoutDelete";
        Set<String> valueSet = new HashSet<>();
        valueSet.add("10");
        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.EDIT_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6", MERGE);
        defaultYdtBuilder.addChild("cont1", null, MERGE);
        defaultYdtBuilder.addChild("list1", null, MERGE);
        defaultYdtBuilder.addLeaf("leaf2", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf3", null, "3");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf4", null, "4");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("list2", null, MERGE);
        defaultYdtBuilder.addLeaf("leaf5", null, "5");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf6", null, "6");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf7", null, "7");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaflist8", null, valueSet);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf10", null, "10");
        return defaultYdtBuilder;
    }

    /**
     * Returns YANG data tree to check delete operation of list.
     *
     * @return YANG data tree
     */
    private YangRequestWorkBench buildYdtForListEditOperationWithDelete()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "listWithDelete";

        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.EDIT_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6", MERGE);
        defaultYdtBuilder.addChild("cont1", null, MERGE);
        defaultYdtBuilder.addChild("list1", null, DELETE);
        defaultYdtBuilder.addLeaf("leaf2", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf3", null, "3");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf4", null, "4");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("list2", null, DELETE);
        defaultYdtBuilder.addLeaf("leaf5", null, "5");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf6", null, "6");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf10", null, "10");
        return defaultYdtBuilder;
    }

    /**
     * Returns YANG data tree to check query operation of container.
     *
     * @return YANG data tree
     */
    private YangRequestWorkBench buildYdtForQueryOperation()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "root";

        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.QUERY_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6");
        defaultYdtBuilder.addChild("cont1", null);
        defaultYdtBuilder.addChild("cont2", null);
        defaultYdtBuilder.addChild("cont3", null);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("cont4", null);
        defaultYdtBuilder.addChild("cont5", null);
        return defaultYdtBuilder;
    }

    /**
     * Returns YANG data tree to check query operation of list.
     *
     * @return YANG data tree
     */
    private YangRequestWorkBench buildYdtForListQueryOperation()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "listQuery";

        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.QUERY_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6");
        defaultYdtBuilder.addChild("cont1", null);
        defaultYdtBuilder.addChild("list1", null);
        defaultYdtBuilder.addLeaf("leaf2", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("list2", null);
        defaultYdtBuilder.addLeaf("leaf5", null, "5");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf6", null, "6");
        return defaultYdtBuilder;
    }

    private YangRequestWorkBench buildYdtWithOneDeleteNode()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "root";

        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.EDIT_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6");
        defaultYdtBuilder.addChild("cont1", null, MERGE);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("cont4", null, DELETE);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf10", null, "10");
        return defaultYdtBuilder;
    }

    private YangRequestWorkBench buildYdtWithDeleteNodeAsLastChild()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "root";

        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.EDIT_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6", MERGE);
        defaultYdtBuilder.addChild("cont1", null, MERGE);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("list2", null, MERGE);
        defaultYdtBuilder.addLeaf("leaf5", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf6", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("cont4", null, DELETE);
        return defaultYdtBuilder;
    }

    private YangRequestWorkBench buildYdtWithAllDeleteNode()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "root";

        defaultYdtBuilder =
                (YangRequestWorkBench) ymsManager.getYdtBuilder(rootName, null,
                        YmsOperationType.EDIT_CONFIG_REQUEST);
        defaultYdtBuilder.addChild("test6", "ydt.test6", DELETE);
        defaultYdtBuilder.addChild("cont1", null, DELETE);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("list2", null, DELETE);
        defaultYdtBuilder.addLeaf("leaf5", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("leaf6", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("cont4", null, DELETE);
        return defaultYdtBuilder;
    }

    /**
     * Checks whether YANG data tree and delete tree is correct.
     */
    @Test
    public void validateDeleteTreeOnlyOneNodeInDeleteList()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder =
                buildYdtForEditOperationWithDelete();
        YdtAppContext appContext =
                defaultYdtBuilder.getAppRootNode().getFirstChild();
        YdtContext ydtContext = appContext.getModuleNode();
        List<YdtContext> deleteNodes = appContext.getDeleteNodes();

        YdtContext cont1YdtContext;
        YdtContext cont2YdtContext;
        YdtContext cont3YdtContext;
        YdtContext cont4YdtContext;
        YdtContext deleteTree;

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        cont1YdtContext = ydtContext.getFirstChild();
        assertThat(true, is(cont1YdtContext.getName().contentEquals("cont1")));

        cont2YdtContext = cont1YdtContext.getFirstChild();
        assertThat(true, is(cont2YdtContext.getName().contentEquals("cont2")));

        cont3YdtContext = cont2YdtContext.getFirstChild();
        assertThat(true, is(cont3YdtContext.getName().contentEquals("cont3")));

        ydtContext = cont3YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf1")));
        assertThat(true, is(ydtContext.getValue().contentEquals("1")));

        ydtContext = cont2YdtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf4")));
        assertThat(true, is(ydtContext.getValue().contentEquals("4")));

        cont4YdtContext = cont1YdtContext.getNextSibling();
        assertThat(true, is(cont4YdtContext.getName().contentEquals("cont4")));

        ydtContext = cont4YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont5")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf9")));
        assertThat(true, is(ydtContext.getValue().contentEquals("9")));

        ydtContext = cont4YdtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf10")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));

        // build delete tree
        YangApplicationBroker yab = new YangApplicationBroker(null);
        deleteTree = yab.buildDeleteTree(deleteNodes);

        // verify whether ydt tree is correct
        assertThat(true, is(deleteTree.getName().contentEquals("test6")));

        cont1YdtContext = deleteTree.getFirstChild();
        assertThat(true, is(cont1YdtContext.getName().contentEquals("cont1")));

        cont2YdtContext = cont1YdtContext.getFirstChild();
        assertThat(true, is(cont2YdtContext.getName().contentEquals("cont2")));

        cont3YdtContext = cont2YdtContext.getFirstChild();
        assertThat(true, is(cont3YdtContext.getName().contentEquals("cont3")));

        ydtContext = cont3YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf1")));
        assertThat(true, is(ydtContext.getValue().contentEquals("1")));

        assertThat(cont2YdtContext.getNextSibling(), nullValue());

        cont4YdtContext = cont1YdtContext.getNextSibling();
        assertThat(true, is(cont4YdtContext.getName().contentEquals("cont4")));

        ydtContext = cont4YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont5")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf9")));
        assertThat(true, is(ydtContext.getValue().contentEquals("9")));

        assertThat(cont4YdtContext.getNextSibling(), nullValue());

        // ydtTree after removing delete nodes
        ydtContext = appContext.getModuleNode();
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        cont1YdtContext = ydtContext.getFirstChild();
        assertThat(true, is(cont1YdtContext.getName().contentEquals("cont1")));

        ydtContext = cont1YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf4")));
        assertThat(true, is(ydtContext.getValue().contentEquals("4")));

        ydtContext = cont1YdtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf10")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
    }

    /**
     * Checks whether YANG data tree and delete tree is correct.
     */
    @Test
    public void validateListDeleteTree()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder =
                buildYdtForListEditOperationWithDelete();
        YdtAppContext appContext =
                defaultYdtBuilder.getAppRootNode().getFirstChild();
        YdtContext ydtContext = appContext.getModuleNode();
        List<YdtContext> deleteNodes = appContext.getDeleteNodes();

        YdtContext cont1YdtContext;
        YdtContext list1YdtContext;
        YdtContext list2YdtContext;
        YdtContext deleteTree;

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        cont1YdtContext = ydtContext.getFirstChild();
        assertThat(true, is(cont1YdtContext.getName().contentEquals("cont1")));

        list1YdtContext = cont1YdtContext.getFirstChild();
        assertThat(true, is(list1YdtContext.getName().contentEquals("list1")));

        ydtContext = list1YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf2")));
        assertThat(true, is(ydtContext.getValue().contentEquals("2")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf3")));
        assertThat(true, is(ydtContext.getValue().contentEquals("3")));

        ydtContext = list1YdtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf4")));
        assertThat(true, is(ydtContext.getValue().contentEquals("4")));

        list2YdtContext = cont1YdtContext.getNextSibling();
        assertThat(true, is(list2YdtContext.getName().contentEquals("list2")));

        ydtContext = list2YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf5")));
        assertThat(true, is(ydtContext.getValue().contentEquals("5")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf6")));
        assertThat(true, is(ydtContext.getValue().contentEquals("6")));

        ydtContext = list2YdtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf10")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));

        // build delete tree
        YangApplicationBroker yab = new YangApplicationBroker(null);
        deleteTree = yab.buildDeleteTree(deleteNodes);

        assertThat(true, is(deleteTree.getName().contentEquals("test6")));

        cont1YdtContext = deleteTree.getFirstChild();
        assertThat(true, is(cont1YdtContext.getName().contentEquals("cont1")));

        list1YdtContext = cont1YdtContext.getFirstChild();
        assertThat(true, is(list1YdtContext.getName().contentEquals("list1")));

        ydtContext = list1YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf2")));
        assertThat(true, is(ydtContext.getValue().contentEquals("2")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf3")));
        assertThat(true, is(ydtContext.getValue().contentEquals("3")));

        assertThat(list1YdtContext.getNextSibling(), nullValue());

        list2YdtContext = cont1YdtContext.getNextSibling();
        assertThat(true, is(list2YdtContext.getName().contentEquals("list2")));

        ydtContext = list2YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf5")));
        assertThat(true, is(ydtContext.getValue().contentEquals("5")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf6")));
        assertThat(true, is(ydtContext.getValue().contentEquals("6")));

        assertThat(ydtContext.getNextSibling(), nullValue());

        // verify whether ydt tree is correct
        ydtContext = appContext.getModuleNode();
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        cont1YdtContext = ydtContext.getFirstChild();
        assertThat(true, is(cont1YdtContext.getName().contentEquals("cont1")));

        ydtContext = cont1YdtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf4")));
        assertThat(true, is(ydtContext.getValue().contentEquals("4")));

        ydtContext = cont1YdtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf10")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
    }

    /**
     * Checks execute operation for edit request.
     */
    @Test
    public void testExecuteEditOperationWithoutDelete()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder =
                buildYdtForEditOperationWithoutDelete();
        ymsManager.executeOperation(defaultYdtBuilder);
    }

    /**
     * Checks execute operation for delete request.
     */
    @Test
    public void testExecuteEditOperationWithDelete()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder =
                buildYdtForEditOperationWithDelete();
        ymsManager.executeOperation(defaultYdtBuilder);
    }

    /**
     * Checks execute operation for edit request of list.
     */
    @Test
    public void testExecuteListEditOperationWithoutDelete()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder =
                buildYdtForListEditOperationWithoutDelete();
        ymsManager.executeOperation(defaultYdtBuilder);
    }

    /**
     * Checks execute operation for delete request of list.
     */
    @Test
    public void testExecuteListEditOperationWithDelete()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder =
                buildYdtForListEditOperationWithDelete();
        ymsManager.executeOperation(defaultYdtBuilder);
    }

    /**
     * Checks execute operation for get request.
     */
    @Test
    public void testExecuteQueryOperation()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder = buildYdtForQueryOperation();
        ymsManager.executeOperation(defaultYdtBuilder);
    }

    /**
     * Checks execute operation for get request of list.
     */
    @Test
    public void testExecuteListQueryOperation()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder =
                buildYdtForListQueryOperation();
        ymsManager.executeOperation(defaultYdtBuilder);
    }

    /**
     * Checks whether delete tree is updated correctly.
     */
    @Test
    public void testSiblingsInDeleteTree()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder = buildYdtWithOneDeleteNode();
        YdtAppContext appContext =
                defaultYdtBuilder.getAppRootNode().getFirstChild();
        YdtContext ydtContext = appContext.getModuleNode();
        List<YdtContext> deleteNodes = appContext.getDeleteNodes();

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont1")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("cont4")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf10")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));

        // build delete tree
        YangApplicationBroker yab = new YangApplicationBroker(null);
        YdtContext deleteTree = yab.buildDeleteTree(deleteNodes);

        assertThat(true, is(deleteTree.getName().contentEquals("test6")));

        ydtContext = deleteTree.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont4")));

        assertThat(ydtContext.getNextSibling(), nullValue());
        assertThat(ydtContext.getPreviousSibling(), nullValue());

        ydtContext = appContext.getModuleNode();

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont1")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf10")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));

        assertThat(ydtContext.getNextSibling(), nullValue());
    }

    /**
     * Checks last child is updated correctly after delete tree is built.
     */
    @Test
    public void testLastChildInYdtTree()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder =
                buildYdtWithDeleteNodeAsLastChild();
        YdtAppContext appContext =
                defaultYdtBuilder.getAppRootNode().getFirstChild();
        YdtContext ydtContext = appContext.getModuleNode();
        List<YdtContext> deleteNodes = appContext.getDeleteNodes();
        assertThat(YdtAppNodeOperationType.BOTH,
                is(appContext.getOperationType()));

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont1")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("list2")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("cont4")));

        assertThat(ydtContext.getNextSibling(), nullValue());

        // build delete tree
        YangApplicationBroker yab = new YangApplicationBroker(null);
        YdtContext deleteTree = yab.buildDeleteTree(deleteNodes);

        assertThat(true, is(deleteTree.getName().contentEquals("test6")));

        ydtContext = deleteTree.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont4")));

        ydtContext = deleteTree.getLastChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont4")));

        assertThat(ydtContext.getNextSibling(), nullValue());
        assertThat(ydtContext.getPreviousSibling(), nullValue());

        ydtContext = appContext.getModuleNode();

        assertThat(true, is(ydtContext.getLastChild().getName()
                .contentEquals("list2")));

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont1")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("list2")));

        assertThat(ydtContext.getNextSibling(), nullValue());
    }

    /**
     * Checks YDT tree with all delete nodes.
     */
    @Test
    public void testYdtTreeWithAllDeleteNodes()
            throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder = buildYdtWithAllDeleteNode();
        YdtAppContext appContext =
                defaultYdtBuilder.getAppRootNode().getFirstChild();
        YdtContext ydtContext = appContext.getModuleNode();
        List<YdtContext> deleteNodes = appContext.getDeleteNodes();

        assertThat(YdtAppNodeOperationType.DELETE_ONLY,
                is(appContext.getOperationType()));

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont1")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("list2")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("cont4")));

        assertThat(ydtContext.getNextSibling(), nullValue());

        // build delete tree
        YangApplicationBroker yab = new YangApplicationBroker(null);
        YdtContext deleteTree = yab.buildDeleteTree(deleteNodes);

        assertThat(true, is(deleteTree.getName().contentEquals("test6")));

        ydtContext = deleteTree.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("cont1")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("list2")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("cont4")));

        assertThat(ydtContext.getNextSibling(), nullValue());
    }


    @Test
    public void testKeyLeavesInDeleteTree() throws IOException, CloneNotSupportedException {
        YangRequestWorkBench defaultYdtBuilder = buildYdtForKeyLeavesInDeleteTree();

        YdtAppContext appContext =
                defaultYdtBuilder.getAppRootNode().getFirstChild();
        YdtContext ydtContext = appContext.getModuleNode();
        List<YdtContext> deleteNodes = appContext.getDeleteNodes();

        assertThat(YdtAppNodeOperationType.BOTH, is(appContext.getOperationType()));

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("list2")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf5")));
        assertThat(true, is(ydtContext.getValue().contentEquals("5")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf6")));
        assertThat(true, is(ydtContext.getValue().contentEquals("6")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf7")));
        assertThat(true, is(ydtContext.getValue().contentEquals("7")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("cont7")));

        assertThat(ydtContext.getNextSibling(), nullValue());

        // build delete tree
        YangApplicationBroker yab = new YangApplicationBroker(null);
        YdtContext deleteTree = yab.buildDeleteTree(deleteNodes);

        assertThat(true, is(deleteTree.getName().contentEquals("test6")));

        ydtContext = deleteTree.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("list2")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf6")));
        assertThat(true, is(ydtContext.getValue().contentEquals("6")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf5")));
        assertThat(true, is(ydtContext.getValue().contentEquals("5")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("cont7")));

        assertThat(ydtContext.getNextSibling(), nullValue());

        ydtContext = appContext.getModuleNode();

        // verify whether ydt tree is correct
        assertThat(true, is(ydtContext.getName().contentEquals("test6")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("list2")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf5")));
        assertThat(true, is(ydtContext.getValue().contentEquals("5")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf6")));
        assertThat(true, is(ydtContext.getValue().contentEquals("6")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("leaf7")));
        assertThat(true, is(ydtContext.getValue().contentEquals("7")));

        assertThat(ydtContext.getNextSibling(), nullValue());
    }
}
