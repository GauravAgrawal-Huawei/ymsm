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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FoodArenaTest {

    // Logger list is used for walker testing.
    private final List<String> logger = new ArrayList<>();

    @Test
    public void foodArenaTest() throws IOException {
        YangRequestWorkBench ydtBuilder = YdtTestUtils.foodArenaYdt();
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
                "Entry Node is foodarena.")));
        assertThat(true, is(logger.get(1).contentEquals(
                "Entry Node is food.")));
        assertThat(true, is(logger.get(2).contentEquals(
                "Entry Node is food.")));
        assertThat(true, is(logger.get(3).contentEquals(
                "Entry Node is chocolate.")));
        assertThat(true, is(logger.get(4).contentEquals(
                "Exit Node is chocolate.")));
        assertThat(true, is(logger.get(5).contentEquals(
                "Exit Node is food.")));
        assertThat(true, is(logger.get(6).contentEquals(
                "Exit Node is food.")));
        assertThat(true, is(logger.get(7).contentEquals(
                "Exit Node is foodarena.")));
    }


    private void validateTree(YangRequestWorkBench ydtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = ydtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("foodarena")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("food")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("food")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("chocolate")));
        assertThat(true, is(ydtContext.getValue().contentEquals("dark")));
    }
}
