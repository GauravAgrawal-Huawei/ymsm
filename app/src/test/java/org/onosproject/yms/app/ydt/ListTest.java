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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.onosproject.yms.ydt.YdtContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.onosproject.yms.app.ydt.YdtTestUtils.compareValueSet;

public class ListTest {

    @SuppressWarnings("unchecked")
    private
    Set<String> valueSet = new HashSet();

    @Test
    public void listwithoutcontainerTest()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder =
                YdtTestUtils.listWithoutContainerYdt();
        validateTree(defaultYdtBuilder);
        // walker test
        walkINTree(defaultYdtBuilder);
    }

    @Test
    public void listwithcontainerTest()
            throws IOException {
        YangRequestWorkBench defaultYdtBuilder =
                YdtTestUtils.listWithContainerYdt();
        validateListwithcontainerTree(defaultYdtBuilder);
    }


    private void walkINTree(YangRequestWorkBench defaultYdtBuilder) {
        DefaultYdtWalker ydtWalker = new DefaultYdtWalker();
        YdtTestUtils ydtListenerService = new YdtTestUtils();

        // Logger list is used for walker testing.
        YdtTestUtils.resetLogger();
        // Assign root node to YdtContext to walk the tree.
        ydtWalker.walk(ydtListenerService, defaultYdtBuilder.getRootNode());

        // Logger list is used for walker testing.
        List<String> logger = YdtTestUtils.getLogger();
        assertThat(true,
                   is(logger.get(0).contentEquals("Entry Node is list.")));
        assertThat(true,
                   is(logger.get(1).contentEquals("Entry Node is rootlist.")));
        assertThat(true, is(logger.get(2).contentEquals(
                "Entry Node is listwithoutcontainer.")));
        assertThat(true, is(logger.get(3).contentEquals(
                "Entry Node is invalidinterval.")));
        assertThat(true, is(logger.get(4).contentEquals(
                "Exit Node is invalidinterval.")));
        assertThat(true, is(logger.get(5).contentEquals(
                "Exit Node is listwithoutcontainer.")));
        assertThat(true,
                   is(logger.get(6).contentEquals("Exit Node is rootlist.")));
        assertThat(true, is(logger.get(7).contentEquals("Exit Node is list.")));

    }

    private void validateTree(YangRequestWorkBench defaultYdtBuilder) {

        // assign root node to ydtNode for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("list")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("rootlist")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName()
                                    .contentEquals("listwithoutcontainer")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true,
                   is(ydtContext.getName().contentEquals("invalidinterval")));
        assertThat(true, is(ydtContext.getValue().contentEquals("12")));


    }

    private void validateListwithcontainerTree(
            YangRequestWorkBench defaultYdtBuilder) {

        valueSet.add("1");
        valueSet.add("2");
        // assign root node to ydtNode for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("list")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("rootlist")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true,
                   is(ydtContext.getName().contentEquals("listwithcontainer")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true,
                   is(ydtContext.getName().contentEquals("invalidinterval")));
        compareValueSet(valueSet, ydtContext.getValueSet());
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("interface")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true,
                   is(ydtContext.getName().contentEquals("invalidinterval")));
        assertThat(true, is(ydtContext.getValue().contentEquals("12")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("invalid")));
        assertThat(true, is(ydtContext.getValue().contentEquals("12")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("invalid1")));
        assertThat(true, is(ydtContext.getValue().contentEquals("12")));
    }

    @Test
    public void negativeTest()
            throws IOException {
        String appName =
                "org.onosproject.yang.gen.v1.ydt.rootlist" +
                        ".rev20160624.RootlistService";
        YangRequestWorkBench defaultYdtBuilder =
                YdtTestUtils.getDefaultYdtBuilder("list", "rootlist", "ydt" +
                        ".rootlist", appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        try {
            defaultYdtBuilder.traverseToParent();
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "rootlist is missing some of the keys of " +
                            "listwithcontainer.")));
        }

        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        try {
            defaultYdtBuilder.addLeaf("invalid", null, "12");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "Duplicate entry with name invalid.")));
        }

        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        try {
            defaultYdtBuilder.traverseToParent();
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "Some of the key elements are " +
                            "not unique in listwithcontainer.")));
        }

        List<String> keysValueList = new ArrayList<>();
        keysValueList.add("1");
        keysValueList.add("2");
        keysValueList.add("2");
        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        try {
            defaultYdtBuilder.addMultiInstanceChild("", "", keysValueList);
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "Too many key parameter in listwithcontainer." +
                            " Expected fixed count 2.")));
        }

        keysValueList.clear();
        keysValueList.add("1");
        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        try {
            defaultYdtBuilder.addMultiInstanceChild("", "", keysValueList);
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "Too few key parameter in listwithcontainer." +
                            " Expected fixed count 2.")));
        }

        try {
            YdtTestUtils.getDefaultYdtBuilder("list", "invalid", "ydt.invalid",
                                              appName);
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "Schema node with name invalid doesn't exist.")));
        }

    }

    @Test
    public void negative1Test()
            throws IOException {
        String appName =
                "org.onosproject.yang.gen.v1.ydt.rootlist" +
                        ".rev20160624.RootlistService";
        YangRequestWorkBench defaultYdtBuilder =
                YdtTestUtils.getDefaultYdtBuilder("list", "rootlist", "ydt" +
                        ".rootlist", appName);

        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        List<String> keysValueList = new ArrayList<>();
        keysValueList.clear();
        keysValueList.add("12");
        keysValueList.add("12");
        defaultYdtBuilder.addMultiInstanceChild("", "", keysValueList);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "121");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "1211");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "21");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        try {
            defaultYdtBuilder.traverseToParent();
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "Too many instances of listwithcontainer." +
                            " Expected maximum instance 3.")));
        }

        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        try {
            defaultYdtBuilder.traverseToParent();
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "rootlist is missing some of the " +
                            "keys of listwithcontainer.")));
        }

        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "12");
        defaultYdtBuilder.traverseToParent();
        try {
            defaultYdtBuilder.addLeaf("invalidinterval", null, "12");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "Duplicate entry found under invalidinterval " +
                            "leaf-list node.")));
        }


        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "12");
        defaultYdtBuilder.traverseToParent();
        try {
            defaultYdtBuilder.addLeaf("invalidinterval", null, "string");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"string\" " +
                            "is not a valid uint16.")));
        }

        valueSet.clear();
        valueSet.add("1");
        valueSet.add("2");
        valueSet.add("12");
        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "12");
        defaultYdtBuilder.traverseToParent();
        try {
            defaultYdtBuilder.addLeaf("invalidinterval", null, valueSet);
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "Duplicate entry found under invalidinterval " +
                            "leaf-list node.")));
        }

        valueSet.clear();
        valueSet.add("string");
        defaultYdtBuilder = YdtTestUtils
                .getDefaultYdtBuilder("list", "rootlist", "ydt.rootlist",
                                      appName);
        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "12");
        defaultYdtBuilder.traverseToParent();
        try {
            defaultYdtBuilder.addLeaf("invalidinterval", null, valueSet);
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"string\" " +
                            "is not a valid uint16.")));
        }
    }
}
