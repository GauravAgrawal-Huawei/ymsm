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

public class YdtEmptyTest {

    /*
        EMPTY
        Positive scenario
        input with in empty.
    */
    @Test
    public void positiveTest() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.emptyTypeYdt();
        validateTree(defaultYdtBuilder);
    }

    private void validateTree(YangRequestWorkBench defaultYdtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("builtInType")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("emptydata")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("emptyList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("empty")));
        assertThat(true, is(ydtContext.getValue().contentEquals("")));

    }

    /*
        Negative scenario

        input with " "
        input with "tab"
        input with """"
    */
    @Test
    public void negativeTest() throws IOException {
        String appName = "org.onosproject.yang.gen.v1.ydt.emptydata.rev20160524.EmptydataService";
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "emptydata",
                "ydt.emptydata", appName);
        defaultYdtBuilder.addChild("emptyList", null);

        try {
            defaultYdtBuilder.addLeaf("empty", null, " ");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + " " + "\" is not " +
                    "allowed for a data type EMPTY")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "emptydata",
                "ydt.emptydata", appName);
        defaultYdtBuilder.addChild("emptyList", null);
        try {
            defaultYdtBuilder.addLeaf("empty", null, "    ");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "    " + "\" is not " +
                    "allowed for a data type EMPTY")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "emptydata",
                "ydt.emptydata", appName);
        defaultYdtBuilder.addChild("emptyList", null);
        try {
            defaultYdtBuilder.addLeaf("empty", null, "" + "" + " ");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + " " + "\" is not " +
                    "allowed for a data type EMPTY")));
        }

    }

}
