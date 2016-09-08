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
import java.util.List;

import org.junit.Test;
import org.onosproject.yms.ydt.YdtContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IetfNetworkTest {

    @Test
    public void ietfNetwork1Test() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.ietfNetwork1Ydt();

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

        assertThat(true, is(logger.get(0).contentEquals("Entry Node is " +
                                                                "yms-ietf-network.")));
        assertThat(true, is(logger.get(1).contentEquals("Entry Node is " +
                                                                "yms-ietf-network.")));
        assertThat(true, is(logger.get(2).contentEquals("Entry Node is networks.")));
        assertThat(true, is(logger.get(3).contentEquals("Entry Node is network.")));
        assertThat(true, is(logger.get(4).contentEquals("Entry Node is network-id.")));
        assertThat(true, is(logger.get(5).contentEquals("Exit Node is network-id.")));
        assertThat(true, is(logger.get(6).contentEquals("Entry Node is supporting-network.")));
        assertThat(true, is(logger.get(7).contentEquals("Entry Node is network-ref.")));
        assertThat(true, is(logger.get(8).contentEquals("Exit Node is network-ref.")));
        assertThat(true, is(logger.get(9).contentEquals("Exit Node is supporting-network.")));
        assertThat(true, is(logger.get(10).contentEquals("Entry Node is node.")));
        assertThat(true, is(logger.get(11).contentEquals("Entry Node is node-id.")));
        assertThat(true, is(logger.get(12).contentEquals("Exit Node is node-id.")));
        assertThat(true, is(logger.get(13).contentEquals("Entry Node is supporting-node.")));
        assertThat(true, is(logger.get(14).contentEquals("Entry Node is network-ref.")));
        assertThat(true, is(logger.get(15).contentEquals("Exit Node is network-ref.")));
        assertThat(true, is(logger.get(16).contentEquals("Entry Node is node-ref.")));
        assertThat(true, is(logger.get(17).contentEquals("Exit Node is node-ref.")));
        assertThat(true, is(logger.get(18).contentEquals("Exit Node is supporting-node.")));
        assertThat(true, is(logger.get(19).contentEquals("Exit Node is node.")));
        assertThat(true, is(logger.get(20).contentEquals("Exit Node is network.")));
        assertThat(true, is(logger.get(21).contentEquals("Exit Node is networks.")));

        assertThat(true, is(logger.get(22).contentEquals("Entry Node is networks-state.")));
        assertThat(true, is(logger.get(23).contentEquals("Entry Node is network.")));
        assertThat(true, is(logger.get(24).contentEquals("Entry Node is network-ref.")));
        assertThat(true, is(logger.get(25).contentEquals("Exit Node is network-ref.")));
        assertThat(true, is(logger.get(26).contentEquals("Entry Node is server-provided.")));
        assertThat(true, is(logger.get(27).contentEquals("Exit Node is server-provided.")));
        assertThat(true, is(logger.get(28).contentEquals("Exit Node is network.")));
        assertThat(true, is(logger.get(29).contentEquals("Exit Node is networks-state.")));
        assertThat(true, is(logger.get(30).contentEquals("Exit Node is " +
                                                                 "yms-ietf-network.")));
        assertThat(true, is(logger.get(31).contentEquals("Exit Node is " +
                                                                 "yms-ietf-network.")));
    }


    private void validateTree(YangRequestWorkBench defaultYdtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("yms-ietf-network")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("yms-ietf-network")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("networks")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("network")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("network-id")));

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("supporting-network")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("network-ref")));
        ydtContext = ydtContext.getParent();

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("node")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("node-id")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("supporting-node")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("network-ref")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("node-ref")));

        ydtContext = ydtContext.getParent().getParent().getParent().getParent();

        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("networks-state")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("network")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("network-ref")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("server-provided")));

    }
}
