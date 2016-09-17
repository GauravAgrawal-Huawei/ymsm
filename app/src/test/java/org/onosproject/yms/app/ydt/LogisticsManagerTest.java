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

import org.junit.Test;
import org.onosproject.yms.ydt.YdtContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.onosproject.yms.app.ydt.YdtTestUtils.compareValueSet;

public class LogisticsManagerTest {

    // Logger list is used for walker testing.
    private final List<String> logger = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private Set<String> valueSet = new HashSet();

    @Test
    public void logisticsManagerTest() throws IOException {
        YangRequestWorkBench ydtBuilder = YdtTestUtils
                .logisticsManagerYdt();
        validateTree(ydtBuilder);
        // walker test
        walkINTree(ydtBuilder);
    }

    private void walkINTree(YangRequestWorkBench ydtBuilder) {
        DefaultYdtWalker ydtWalker = new DefaultYdtWalker();
        YdtTestUtils ydtListenerService = new YdtTestUtils();

        // Logger list is used for walker testing.
        YdtTestUtils.resetLogger();

        // Assign root node to YdtContext to walk the tree.
        ydtWalker.walk(ydtListenerService, ydtBuilder.getRootNode());
        // Logger list is used for walker testing.
        List<String> logger = YdtTestUtils.getLogger();

        assertThat(true, is(logger.get(0).contentEquals(
                "Entry Node is logisticsmanager.")));
        assertThat(true, is(logger.get(1).contentEquals(
                "Entry Node is customssupervisor.")));
        assertThat(true, is(logger.get(2).contentEquals(
                "Entry Node is supervisor.")));
        assertThat(true, is(logger.get(3).contentEquals(
                "Exit Node is supervisor.")));
        assertThat(true, is(logger.get(4).contentEquals(
                "Exit Node is customssupervisor.")));
        assertThat(true, is(logger.get(5).contentEquals(
                "Entry Node is merchandisersupervisor.")));
        assertThat(true, is(logger.get(6).contentEquals(
                "Entry Node is supervisor.")));
        assertThat(true, is(logger.get(7).contentEquals(
                "Exit Node is supervisor.")));
        assertThat(true, is(logger.get(8).contentEquals(
                "Exit Node is merchandisersupervisor.")));
        assertThat(true, is(logger.get(9).contentEquals(
                "Entry Node is materialsupervisor.")));
        assertThat(true, is(logger.get(10).contentEquals(
                "Entry Node is supervisor.")));
        assertThat(true, is(logger.get(11).contentEquals(
                "Entry Node is name.")));
        assertThat(true, is(logger.get(12).contentEquals(
                "Exit Node is name.")));
        assertThat(true, is(logger.get(13).contentEquals(
                "Entry Node is departmentId.")));
        assertThat(true, is(logger.get(14).contentEquals(
                "Exit Node is departmentId.")));
        assertThat(true, is(logger.get(15).contentEquals(
                "Exit Node is supervisor.")));
        assertThat(true, is(logger.get(16).contentEquals(
                "Entry Node is supervisor.")));
        assertThat(true, is(logger.get(17).contentEquals(
                "Entry Node is name.")));
        assertThat(true, is(logger.get(18).contentEquals(
                "Exit Node is name.")));
        assertThat(true, is(logger.get(19).contentEquals(
                "Entry Node is departmentId.")));
        assertThat(true, is(logger.get(20).contentEquals(
                "Exit Node is departmentId.")));
        assertThat(true, is(logger.get(21).contentEquals(
                "Exit Node is supervisor.")));
        assertThat(true, is(logger.get(22).contentEquals(
                "Exit Node is materialsupervisor.")));
        assertThat(true, is(logger.get(23).contentEquals(
                "Entry Node is purchasingsupervisor.")));
        assertThat(true, is(logger.get(24).contentEquals(
                "Entry Node is supervisor.")));
        assertThat(true, is(logger.get(25).contentEquals(
                "Entry Node is purchasing-specialist.")));
        assertThat(true, is(logger.get(26).contentEquals(
                "Exit Node is purchasing-specialist.")));
        assertThat(true, is(logger.get(27).contentEquals(
                "Entry Node is support.")));
        assertThat(true, is(logger.get(28).contentEquals(
                "Exit Node is support.")));
        assertThat(true, is(logger.get(29).contentEquals(
                "Exit Node is supervisor.")));
        assertThat(true, is(logger.get(30).contentEquals(
                "Exit Node is purchasingsupervisor.")));
        assertThat(true, is(logger.get(31).contentEquals(
                "Entry Node is warehousesupervisor.")));
        assertThat(true, is(logger.get(32).contentEquals(
                "Entry Node is supervisor.")));
        assertThat(true, is(logger.get(33).contentEquals(
                "Exit Node is supervisor.")));
        assertThat(true, is(logger.get(34).contentEquals(
                "Exit Node is warehousesupervisor.")));
        assertThat(true, is(logger.get(35).contentEquals(
                "Entry Node is tradingsupervisor.")));
        assertThat(true, is(logger.get(36).contentEquals(
                "Entry Node is supervisor.")));
        assertThat(true, is(logger.get(37).contentEquals(
                "Exit Node is supervisor.")));
        assertThat(true, is(logger.get(38).contentEquals(
                "Exit Node is tradingsupervisor.")));
        assertThat(true, is(logger.get(39).contentEquals(
                "Entry Node is employeeid.")));
        assertThat(true, is(logger.get(40).contentEquals(
                "Entry Node is employeeid.")));
        assertThat(true, is(logger.get(41).contentEquals(
                "Exit Node is employeeid.")));
        assertThat(true, is(logger.get(42).contentEquals(
                "Exit Node is employeeid.")));
        assertThat(true, is(logger.get(43).contentEquals(
                "Exit Node is logisticsmanager.")));
    }


    private void validateTree(YangRequestWorkBench ydtBuilder) {

        valueSet.add("1");
        valueSet.add("2");
        valueSet.add("3");
        valueSet.add("4");
        valueSet.add("5");
        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = ydtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "logisticsmanager")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "customssupervisor")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("supervisor")));
        assertThat(true, is(ydtContext.getValue().contentEquals("abc")));

        ydtContext = ydtContext.getParent();

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "merchandisersupervisor")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("supervisor")));
        assertThat(true, is(ydtContext.getValue().contentEquals("abc")));

        ydtContext = ydtContext.getParent();

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "materialsupervisor")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("supervisor")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("name")));
        assertThat(true, is(ydtContext.getValue().contentEquals("abc")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "departmentId")));
        assertThat(true, is(ydtContext.getValue().contentEquals("xyz")));

        ydtContext = ydtContext.getParent();

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("supervisor")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("name")));
        assertThat(true, is(ydtContext.getValue().contentEquals("ab")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "departmentId")));
        assertThat(true, is(ydtContext.getValue().contentEquals("xy")));

        ydtContext = ydtContext.getParent().getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "purchasingsupervisor")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("supervisor")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "purchasing-specialist")));
        assertThat(true, is(ydtContext.getValue().contentEquals("abc")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("support")));
        assertThat(true, is(ydtContext.getValue().contentEquals("xyz")));

        ydtContext = ydtContext.getParent().getParent();

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "warehousesupervisor")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("supervisor")));
        compareValueSet(ydtContext.getValueSet(), valueSet);

        ydtContext = ydtContext.getParent();

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "tradingsupervisor")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("supervisor")));
        assertThat(true, is(ydtContext.getValue().contentEquals("abc")));

        ydtContext = ydtContext.getParent();

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("employeeid")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("employeeid")));
        compareValueSet(valueSet, ydtContext.getValueSet());
    }
}
