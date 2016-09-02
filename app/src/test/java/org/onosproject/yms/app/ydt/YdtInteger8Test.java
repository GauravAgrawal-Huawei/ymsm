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

public class YdtInteger8Test {

    /*

    Positive scenario

    input at boundry for integer
        i. min value
        ii. max value

    input at boundry for uinteger
        i. min value
        ii. max value

    input with in range
        if range is 10 to 100 for integer
            i.1. input 11
            i.2. min value 10
            i.3. max value 100

        if range is 10 to 100 for uinteger
            i.1. input 11
            i.2. min value 10
            i.3. max value 100

    input with multi interval range
        if range is 10..40 | 50..100 for integer
            i.1. input 11
            i.2. input 10
            i.3. input 40
            i.4. input 50
            i.5. input 55
            i.6. input 100

        if range is 10..40 | 50..100 for uinteger
            i.1. input 11
            i.2. input 10
            i.3. input 40
            i.4. input 50
            i.5. input 55
            i.6. input 100

        if range is "min .. 2 | 10 | 20..max" for integer
            i.1. input -128
            i.2. input 1
            i.3. input 2
            i.4. input 10
            i.5. input 20
            i.6. input 100
            i.7. input 127

         if range is "min .. 2 | 10 | 20..max" for uInteger
            i.1. input 0
            i.2. input 1
            i.3. input 2
            i.4. input 10
            i.5. input 20
            i.6. input 100
            i.7. input 255
    */
    @Test
    public void positiveTest() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.integer8Ydt();
        validateTree(defaultYdtBuilder);
    }

    private void validateTree(YangRequestWorkBench defaultYdtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = defaultYdtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("builtInType")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("integer8")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("negInt")));
        assertThat(true, is(ydtContext.getValue().contentEquals("-128")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("posInt")));
        assertThat(true, is(ydtContext.getValue().contentEquals("127")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("minUInt")));
        assertThat(true, is(ydtContext.getValue().contentEquals("0")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("maxUInt")));
        assertThat(true, is(ydtContext.getValue().contentEquals("255")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("midIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("11")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("minIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("maxIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("100")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("midUIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("11")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("minUIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("maxUIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("100")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("integer")));
        assertThat(true, is(ydtContext.getValue().contentEquals("11")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("integer")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("integer")));
        assertThat(true, is(ydtContext.getValue().contentEquals("40")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("integer")));
        assertThat(true, is(ydtContext.getValue().contentEquals("50")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("integer")));
        assertThat(true, is(ydtContext.getValue().contentEquals("55")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("integer")));
        assertThat(true, is(ydtContext.getValue().contentEquals("100")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("UnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("11")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("UnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("UnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("40")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("UnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("50")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("UnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("55")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("UnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("100")));

        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("-128")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("1")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("2")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("20")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("100")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("127")));

        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revUnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("0")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revUnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("1")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revUnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("2")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revUnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revUnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("20")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revUnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("100")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revUnInteger")));
        assertThat(true, is(ydtContext.getValue().contentEquals("255")));

    }

    /*
        Negative scenario

        wrong type input
            i. input string instead of integer
            ii. input string instead of uinteger

        input out of range
            i. input for int 8 range -128 to 127
            i.1. input -129
            i.2. input 128

            ii. input for uint 8 range 0 to 255
            ii.1. input -128
            ii.2. input 256

        input out of range parameter
            if range is 10 to 100 for int
                i.1. input 9
                i.2. input 101

            if range is 10 to 100 for uInt
                i.1. input 9
                i.2. input 101

        input with multi interval range
        if range is 10..40 | 50..100 for integer
            i.1. input 9
            i.2. input 41
            i.3. input 49
            i.4. input 101

        if range is 10..40 | 50..100 for uinteger
            i.1. input 9
            i.2. input 41
            i.3. input 49
            i.4. input 101

        input with multi interval range
        if range is min ..  | 10 | 20..max for integer
            i.1. input -129
            i.2. input 4
            i.3. input 9
            i.4. input 11
            i.5. input 19
            i.6. input 128

        if range is min .. 3 | 10 | 20..max for uinteger
            i.1. input -129
            i.2. input 4
            i.3. input 9
            i.4. input 11
            i.5. input 19
            i.6. input 256

    */
    @Test
    public void negative1Test() throws IOException {
        String appName = "org.onosproject.yang.gen.v1.ydt.integer8.rev20160524.Integer8Service";
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8",
                "ydt.integer8", appName);

        try {
            defaultYdtBuilder.addLeaf("posInt", null, "integer");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "integer" + "\" " +
                    "is not a valid int8.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("posInt", null, "127.0");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "127.0" + "\" " +
                    "is not a valid int8.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("maxUInt", null, "integer");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "integer" + "\" " +
                    "is not a valid uint8.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("maxUInt", null, "127.0");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "127.0" + "\" " +
                    "is not a valid uint8.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("negInt", null, "-129");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "-129" + "\" " +
                    "is not a valid int8.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("posInt", null, "128");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "128" + "\" " +
                    "is not a valid int8.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("minUInt", null, "-128");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : -128 is lesser than minimum value 0.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("maxUInt", null, "256");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : 256 is greater than maximum value 255.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("minIntWithRange", null, "9");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "9" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("maxIntWithRange", null, "101");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "101" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("minUIntWithRange", null, "9");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "9" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        try {
            defaultYdtBuilder.addLeaf("maxUIntWithRange", null, "101");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "101" + "\" " +
                    "is not a valid UINT8")));
        }

    }


    @Test
    public void negative2Test() throws IOException {
        String appName = "org.onosproject.yang.gen.v1.ydt.integer8.rev20160524.Integer8Service";
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8",
                "ydt.integer8", appName);

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("integer", null, "9");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "9" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("integer", null, "41");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "41" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("integer", null, "49");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "49" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("integer", null, "101");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "101" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "9");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "9" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "41");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "41" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "49");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "49" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "101");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "101" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "9");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "9" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "41");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "41" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "49");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "49" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "101");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "101" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "9");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "9" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "41");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "41" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "49");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "49" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("UnInteger", null, "101");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "101" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revInteger", null, "-129");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "-129" + "\" " +
                    "is not a valid int8.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revInteger", null, "4");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "4" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revInteger", null, "9");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "9" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revInteger", null, "11");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "11" + "\" " +
                    "is not a valid INT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revInteger", null, "19");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "19" + "\" " +
                    "is not a valid INT8")));
        }

    }


    @Test
    public void negative3Test() throws IOException {

        String appName = "org.onosproject.yang.gen.v1.ydt.integer8.rev20160524.Integer8Service";
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8",
                "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revInteger", null, "128");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "128" + "\" " +
                    "is not a valid int8.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revUnInteger", null, "-129");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : -129 is lesser than minimum value 0.")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revUnInteger", null, "4");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "4" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revUnInteger", null, "9");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "9" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revUnInteger", null, "11");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "11" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revUnInteger", null, "19");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : Input value \"" + "19" + "\" " +
                    "is not a valid UINT8")));
        }

        defaultYdtBuilder = YdtTestUtils.getDefaultYdtBuilder("builtInType", "integer8", "ydt.integer8", appName);
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        try {
            defaultYdtBuilder.addLeaf("revUnInteger", null, "256");
        } catch (final Exception e) {
            assertThat(true, is(e.getMessage().contains("YANG file error : 256 is greater than maximum value 255.")));
        }

    }

}
