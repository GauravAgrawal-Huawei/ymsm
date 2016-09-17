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
import java.util.Objects;

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
        YangRequestWorkBench ydtBuilder = YdtTestUtils.booleanYdt();
        validateTree(ydtBuilder);
    }

    private void validateTree(YangRequestWorkBench ydtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = ydtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("builtInType")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(Objects.equals(ydtContext.getName(), "bool")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(Objects.equals(ydtContext.getName(),
                                           "booleanList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("boolean")));
        assertThat(true, is(Objects.equals(ydtContext.getValue(), "true")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(Objects.equals(ydtContext.getName(),
                                           "booleanList")));
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
        String appName = "org.onosproject.yang.gen.v1.ydt" +
                ".yangautoprefixboolean.rev20160524.BoolService";
        YangRequestWorkBench ydtBuilder = YdtTestUtils
                .getydtBuilder("builtInType", "bool",
                                      "ydt.boolean", appName);
        ydtBuilder.addChild("booleanList", null);

        try {
            ydtBuilder.addLeaf("boolean", null, "10");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "10" + "\" is not a " +
                            "valid BOOLEAN")));
        }

        ydtBuilder = YdtTestUtils.getydtBuilder(
                "builtInType", "bool", "ydt.boolean", appName);
        ydtBuilder.addChild("booleanList", null);

        try {
            ydtBuilder.addLeaf("boolean", null, "0");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "0" + "\" is not a " +
                            "valid BOOLEAN")));
        }

        ydtBuilder = YdtTestUtils.getydtBuilder(
                "builtInType", "bool", "ydt.boolean", appName);
        ydtBuilder.addChild("booleanList", null);

        try {
            ydtBuilder.addLeaf("boolean", null, "");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "" + "\" is not a " +
                            "valid BOOLEAN")));
        }

        ydtBuilder = YdtTestUtils.getydtBuilder(
                "builtInType", "bool", "ydt.boolean", appName);
        ydtBuilder.addChild("booleanList", null);

        try {
            ydtBuilder.addLeaf("boolean", null, "-1");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "-1" + "\" is not a " +
                            "valid BOOLEAN")));
        }

        ydtBuilder = YdtTestUtils.getydtBuilder(
                "builtInType", "bool", "ydt.boolean", appName);
        ydtBuilder.addChild("booleanList", null);

        try {
            ydtBuilder.addLeaf("boolean", null, "tru");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "tru" + "\" is " +
                            "not a valid BOOLEAN")));
        }

        ydtBuilder = YdtTestUtils.getydtBuilder(
                "builtInType", "bool", "ydt.boolean", appName);
        ydtBuilder.addChild("booleanList", null);

        try {
            ydtBuilder.addLeaf("boolean", null, "boolean");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "boolean" + "\" " +
                            "is not a " + "valid BOOLEAN")));
        }
    }
}
