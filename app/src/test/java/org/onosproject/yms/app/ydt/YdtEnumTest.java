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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class YdtEnumTest {

/*
    ENUM

    Positive scenario

        input with in enum
        input with "ten"
        input with "hundred"
        input with "thousand"
*/

    @Test
    public void positiveTest() throws IOException {
        YangRequestWorkBench ydtBuilder = YdtTestUtils.enumYdt();
        validateTree(ydtBuilder);
    }

    private void validateTree(YangRequestWorkBench ydtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = ydtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("builtInType")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("enumtest")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("enumList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("enumleaf")));
        assertThat(true, is(ydtContext.getValue().contentEquals("ten")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("enumList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("enumleaf")));
        assertThat(true, is(ydtContext.getValue().contentEquals("hundred")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("enumList")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("enumleaf")));
        assertThat(true, is(ydtContext.getValue().contentEquals("thousand")));
    }

    /*
        Negative scenario

        input with "10"
        input with "thousands"
    */
    @Test
    public void negativeTest() throws IOException {
        String appName = "org.onosproject.yang.gen.v1.ydt.enumtest" +
                ".rev20160524.EnumtestService";
        YangRequestWorkBench ydtBuilder = YdtTestUtils
                .getydtBuilder("builtInType", "enumtest",
                                      "ydt.enumtest", appName);
        ydtBuilder.addChild("enumList", null);

        try {
            ydtBuilder.addLeaf("enumleaf", null, "10");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "10" + "\" " +
                            "is not a valid ENUMERATION")));
        }

        ydtBuilder = YdtTestUtils.getydtBuilder(
                "builtInType", "enumtest", "ydt.enumtest", appName);
        ydtBuilder.addChild("enumList", null);
        try {
            ydtBuilder.addLeaf("enumleaf", null, "thousands");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "thousands" + "\" " +
                            "is not a valid ENUMERATION")));
        }

        ydtBuilder = YdtTestUtils.getydtBuilder(
                "builtInType", "enumtest", "ydt.enumtest", appName);
        ydtBuilder.addChild("enumList", null);
        try {
            ydtBuilder.addLeaf("enumleaf", null, "enumeration");
        } catch (Exception e) {
            assertThat(true, is(e.getMessage().contains(
                    "YANG file error : Input value \"" + "enumeration" + "\" " +
                            "is not a valid ENUMERATION")));
        }
    }
}
