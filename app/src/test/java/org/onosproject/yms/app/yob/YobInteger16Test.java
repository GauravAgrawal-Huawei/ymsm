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

package org.onosproject.yms.app.yob;

import org.junit.Test;
import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ydt.YdtTestUtils;
import org.onosproject.yms.ydt.YdtContext;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class YobInteger16Test {

    /*

    Positive scenario

    input at boundary for integer
        i. min value
        ii. max value

    input at boundary for unsigned integer
        i. min value
        ii. max value

    input with in range
        if range is 10 to 100 for integer
            i.1. input 11
            i.2. min value 10
            i.3. max value 100

        if range is 10 to 100 for unsigned integer
            i.1. input 11
            i.2. min value 10
            i.3. max value 100

    */
    @Test
    public void positiveTest() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.integer16Ydt();
        validateYangObject(defaultYdtBuilder);
    }

    public void validateYangObject(YangRequestWorkBench defaultYdtBuilder) {

        YdtContext ydtContext = defaultYdtBuilder.getRootNode();

        YdtContext ydtContext1 = ydtContext.getFirstChild();

        DefaultYobBuilder defaultYobBuilder = new DefaultYobBuilder();

        Object yangObject = defaultYobBuilder.getYangObject(
                (YdtExtendedContext) ydtContext1, YdtTestUtils
                        .getSchemaRegistry());
        assertNotNull(yangObject);
        try {
            Field negInt = yangObject.getClass().getDeclaredField("negInt");
            negInt.setAccessible(true);
            assertEquals("-32768", negInt.get(yangObject).toString());
            Field posInt = yangObject.getClass().getDeclaredField("posInt");
            posInt.setAccessible(true);
            assertEquals("32767", posInt.get(yangObject).toString());
            Field minIntWithRange = yangObject
                    .getClass().getDeclaredField("minIntWithRange");
            minIntWithRange.setAccessible(true);
            assertEquals("10", minIntWithRange.get(yangObject).toString());
            Field midIntWithRange = yangObject
                    .getClass().getDeclaredField("midIntWithRange");
            midIntWithRange.setAccessible(true);
            assertEquals("11", midIntWithRange.get(yangObject).toString());
            Field maxIntWithRange = yangObject
                    .getClass().getDeclaredField("maxIntWithRange");
            maxIntWithRange.setAccessible(true);
            assertEquals("100", maxIntWithRange.get(yangObject).toString());
            Field minUint = yangObject.getClass().getDeclaredField("minUint");
            minUint.setAccessible(true);
            assertEquals("0", minUint.get(yangObject).toString());
            Field maxUint = yangObject.getClass().getDeclaredField("maxUint");
            maxUint.setAccessible(true);
            assertEquals("65535", maxUint.get(yangObject).toString());
            Field minUintWithRange = yangObject
                    .getClass().getDeclaredField("minUintWithRange");
            minUintWithRange.setAccessible(true);
            assertEquals("10", minUintWithRange.get(yangObject).toString());
            Field midUintWithRange = yangObject
                    .getClass().getDeclaredField("midUintWithRange");
            midUintWithRange.setAccessible(true);
            assertEquals("11", midUintWithRange.get(yangObject).toString());
            Field maxUintWithRange = yangObject
                    .getClass().getDeclaredField("maxUintWithRange");
            maxUintWithRange.setAccessible(true);
            assertEquals("100", maxUintWithRange.get(yangObject).toString());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
