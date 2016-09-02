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
import org.junit.Test;
import org.onosproject.yms.ydt.YdtContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class YdtBitTest {

    /*
        BINARY

        Positive scenario
        input with position 0
        input with position 1
        input with position 2
    */
    @Test
    public void positiveTest() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.bitYdt();
        validateTree(defaultYdtBuilder);
    }

    private void validateTree(YangRequestWorkBench defaultYdtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("builtInType")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("bit")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("bitList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("bit")));
        assertThat(true, is(ydtContext.getValue().contentEquals("disable-nagle")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("bitList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("bit")));
        assertThat(true, is(ydtContext.getValue().contentEquals("auto-sense-speed")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("bitList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("bit")));
        assertThat(true, is(ydtContext.getValue().contentEquals("ten-Mb-only")));
//        ydtContext = ydtContext.getParent();
//        ydtContext = ydtContext.getNextSibling();
//        assertThat(true, is(ydtContext.getName().contentEquals("bitList")));
//        ydtContext = ydtContext.getFirstChild();
//        assertThat(true, is(ydtContext.getName().contentEquals("bit")));
//        assertThat(true, is(ydtContext.getValue().contentEquals("auto-sense-speed")));
    }

    /*
        Negative scenario

        input with position 0
        input with position 1
        input with position 2
    */
    @Test
    public void negativeTest() throws IOException {
        String appName = "org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524.BinarytestService";
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bit",
                "ydt.bit", appName);
        defaultYdtBuilder.addChild("bitList", null);

        try {
            defaultYdtBuilder.addLeaf("bit", null, "0");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "0" + "\" is not a valid" +
                    " BITS")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bit",
                "ydt.bit", appName);
        defaultYdtBuilder.addChild("bitList", null);

        try {
            defaultYdtBuilder.addLeaf("bit", null, "default");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "default" + "\" " +
                    "is not a " + "valid BITS")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bit", "ydt.bit", appName);
        defaultYdtBuilder.addChild("bitList", null);

        try {
            defaultYdtBuilder.addLeaf("bit", null, "1");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "1" + "\" is not a " +
                    "valid BITS")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bit", "ydt.bit", appName);
        defaultYdtBuilder.addChild("bitList", null);

        try {
            defaultYdtBuilder.addLeaf("bit", null, "");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "" + "\" is not a " +
                    "valid BITS")));
        }
    }

}
