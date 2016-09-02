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
import java.util.List;
import org.junit.Test;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FoodArenaTest {

    // Logger list is used for walker testing.
    private final List<String> logger = new ArrayList<>();

    @Test
    public void foodArenaTest() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.foodArenaYdt();
        validateTree(defaultYdtBuilder);
        // walker test
        walkINTree(defaultYdtBuilder);
    }


    private void walkINTree(YangRequestWorkBench defaultYdtBuilder) {
        DefaultYdtWalker ydtWalker = new DefaultYdtWalker();
        YdtListenerForTesting ydtListenerService = new YdtListenerForTesting();
        // Assign root node to YdtContext to walk the tree.
        ydtWalker.walk(ydtListenerService, defaultYdtBuilder.getRootNode());
        assertThat(true, is(logger.get(0).contentEquals("Entry Node is foodarena.")));
        assertThat(true, is(logger.get(1).contentEquals("Entry Node is food.")));
        assertThat(true, is(logger.get(2).contentEquals("Entry Node is food.")));
        assertThat(true, is(logger.get(3).contentEquals("Entry Node is chocolate.")));
        assertThat(true, is(logger.get(4).contentEquals("Exit Node is chocolate.")));
        assertThat(true, is(logger.get(5).contentEquals("Exit Node is food.")));
        assertThat(true, is(logger.get(6).contentEquals("Exit Node is food.")));
        assertThat(true, is(logger.get(7).contentEquals("Exit Node is foodarena.")));
    }

    // This implementation is totally for testing purpose it doesn't have any other usage.
    public class YdtListenerForTesting implements YdtListener {
        @Override
        public void enterYdtNode(YdtContext ydtContext) {
            logger.add("Entry Node is " + ydtContext.getName() + ".");
        }

        @Override
        public void exitYdtNode(YdtContext ydtContext) {
            logger.add("Exit Node is " + ydtContext.getName() + ".");
        }
    }

    private void validateTree(YangRequestWorkBench defaultYdtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
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
