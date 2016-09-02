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
import java.util.Objects;
import org.junit.Test;
import org.onosproject.yms.ydt.YdtContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class YdtBooleanTest {

    /*
        BOOLEAN
        Positive scenario
        input with in true and false
    */
    @Test
    public void positiveTest() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.booleanYdt();
        validateTree(defaultYdtBuilder);
    }

    private void validateTree(YangRequestWorkBench defaultYdtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("builtInType")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(Objects.equals(ydtContext.getName(), "bool")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(Objects.equals(ydtContext.getName(), "booleanList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("boolean")));
        assertThat(true, is(Objects.equals(ydtContext.getValue(), "true")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(Objects.equals(ydtContext.getName(), "booleanList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("boolean")));
        assertThat(true, is(Objects.equals(ydtContext.getValue(), "false")));

    }

    /*
        Negative scenario

        input with in non zero value in case of true
        input with zero value in case of false
        input with empty value in case of false
    */
    @Test
    public void negativeTest() throws IOException {
        String appName = "org.onosproject.yang.gen.v1.ydt.yangautoprefixboolean.rev20160524.BoolService";
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bool",
                "ydt.boolean", appName);
        defaultYdtBuilder.addChild("booleanList", null);

        try {
            defaultYdtBuilder.addLeaf("boolean", null, "10");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "10" + "\" is not a " +
                    "valid BOOLEAN")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bool",
                "ydt.boolean", appName);
        defaultYdtBuilder.addChild("booleanList", null);

        try {
            defaultYdtBuilder.addLeaf("boolean", null, "0");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "0" + "\" is not a " +
                    "valid BOOLEAN")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bool",
                "ydt.boolean", appName);
        defaultYdtBuilder.addChild("booleanList", null);

        try {
            defaultYdtBuilder.addLeaf("boolean", null, "");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "" + "\" is not a " +
                    "valid BOOLEAN")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bool",
                "ydt.boolean", appName);
        defaultYdtBuilder.addChild("booleanList", null);

        try {
            defaultYdtBuilder.addLeaf("boolean", null, "-1");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "-1" + "\" is not a " +
                    "valid BOOLEAN")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bool",
                "ydt.boolean", appName);
        defaultYdtBuilder.addChild("booleanList", null);

        try {
            defaultYdtBuilder.addLeaf("boolean", null, "tru");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "tru" + "\" is not a " +
                    "valid BOOLEAN")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "bool",
                "ydt.boolean", appName);
        defaultYdtBuilder.addChild("booleanList", null);

        try {
            defaultYdtBuilder.addLeaf("boolean", null, "boolean");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "boolean" + "\" " +
                    "is not a " + "valid BOOLEAN")));
        }
    }

}
