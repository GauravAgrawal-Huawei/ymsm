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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RpcTest {

    // Logger list is used for walker testing.
    private final List<String> logger = new ArrayList<>();

    @Test
    public void rpc1Test() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.helloOnos();
        validateTree(defaultYdtBuilder);
        // walker test
        walkINTree(defaultYdtBuilder);
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
        assertThat(true, is(logger.get(0).contentEquals("Entry Node is Hello-ONOS.")));
        assertThat(true, is(logger.get(1).contentEquals("Entry Node is Hello_ONOS.")));
        assertThat(true, is(logger.get(2).contentEquals("Entry Node is hello-world.")));
        assertThat(true, is(logger.get(3).contentEquals("Entry Node is input.")));
        assertThat(true, is(logger.get(4).contentEquals("Entry Node is name.")));
        assertThat(true, is(logger.get(5).contentEquals("Exit Node is name.")));
        assertThat(true, is(logger.get(6).contentEquals("Entry Node is surName.")));
        assertThat(true, is(logger.get(7).contentEquals("Exit Node is surName.")));
        assertThat(true, is(logger.get(8).contentEquals("Entry Node is stringList.")));
        assertThat(true, is(logger.get(9).contentEquals("Entry Node is string1.")));
        assertThat(true, is(logger.get(10).contentEquals("Exit Node is string1.")));
        assertThat(true, is(logger.get(11).contentEquals("Entry Node is string2.")));
        assertThat(true, is(logger.get(12).contentEquals("Exit Node is string2.")));
        assertThat(true, is(logger.get(13).contentEquals("Exit Node is stringList.")));
        assertThat(true, is(logger.get(14).contentEquals("Exit Node is input.")));
        assertThat(true, is(logger.get(15).contentEquals("Exit Node is hello-world.")));
        assertThat(true, is(logger.get(16).contentEquals("Exit Node is Hello_ONOS.")));
        assertThat(true, is(logger.get(17).contentEquals("Exit Node is Hello-ONOS.")));
    }

    private void validateTree(YangRequestWorkBench defaultYdtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("Hello-ONOS")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("Hello_ONOS")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("hello-world")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("input")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("name")));
        assertThat(true, is(ydtContext.getValue().contentEquals("onos")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("surName")));
        assertThat(true, is(ydtContext.getValue().contentEquals("yang")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("stringList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("string1")));
        assertThat(true, is(ydtContext.getValue().contentEquals("ON")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("string2")));
        assertThat(true, is(ydtContext.getValue().contentEquals("LAB")));


    }

}
