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

public class YdtDecimal64Test {

    /*

    Positive scenario

    input at boundry for decimal64 with fraction 2
        i. min value
        ii. max value

    input at boundry for decimal64 with minimum fraction
        i. min value
        ii. mid value
        iii. max value

    input at boundry for decimal64 with maximum fraction
        i. min value
        ii. mid value
        iii. max value

    input with in range
        if range is 10 to 100 for integer
            i.1. input 11
            i.2. min value 10
            i.3. max value 100

    input with multi interval range
        if range is 10..40 | 50..100 for decimal64
            i.1. input 11
            i.2. input 10
            i.3. input 40
            i.4. input 50
            i.5. input 55
            i.6. input 100

        if range is "min .. 3.14 | 10 | 20..max" for decimal64
            i.1. input min
            i.2. input 2.505
            i.3. input 3.14
            i.4. input 10
            i.5. input 20
            i.6. input 92233720368547757
            i.7. input 92233720368547758.07

    */
    @Test
    public void positiveTest() throws IOException {
        YangRequestWorkBench ydtBuilder = YdtTestUtils.decimal64Ydt();
        validateTree(ydtBuilder);
    }

    private void validateTree(YangRequestWorkBench ydtBuilder) {

        // assign root node to ydtContext for validating purpose.
        YdtContext ydtContext = ydtBuilder.getRootNode();
        assertThat(true, is(ydtContext.getName().contentEquals("builtInType")));

        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("decimal64")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("negInt")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "-92233720368547758.08")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals("posInt")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "92233720368547758.07")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "negIntWithMinFraction")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "-922337203685477580.8")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "posIntWithMinFraction")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "922337203685477580.7")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "negIntWithMaxFraction")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "-9.223372036854775808")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "posIntWithMaxFraction")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "9.223372036854775807")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "midIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("11")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "minIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "maxIntWithRange")));
        assertThat(true, is(ydtContext.getValue().contentEquals("100")));
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("decimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("11")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("decimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("decimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("40")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("decimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("50")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("decimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("55")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("decimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("100")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revDecimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "-92233720368547758.08")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revDecimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("2.505")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revDecimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("3.14")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revDecimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("10")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revDecimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals("20")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revDecimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "92233720368547757")));
        ydtContext = ydtContext.getParent();
        ydtContext = ydtContext.getNextSibling();
        assertThat(true, is(ydtContext.getName().contentEquals(
                "multiRangeValidation")));
        ydtContext = ydtContext.getFirstChild();
        assertThat(true, is(ydtContext.getName().contentEquals("revDecimal")));
        assertThat(true, is(ydtContext.getValue().contentEquals(
                "92233720368547758.07")));
    }
}
