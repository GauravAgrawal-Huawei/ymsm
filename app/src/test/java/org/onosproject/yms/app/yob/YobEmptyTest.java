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
import org.onosproject.yms.app.ydt.YdtTestUtils;
import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.ydt.YdtContext;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class YobEmptyTest {

    /*
        EMPTY
        Positive scenario
        input with in empty.
    */
    @Test
    public void positiveTest() throws IOException {
        YangRequestWorkBench defaultYdtBuilder = YdtTestUtils.emptyTypeYdt();
        validateYangObject(defaultYdtBuilder);
    }

    public void validateYangObject(YangRequestWorkBench defaultYdtBuilder) {

        YdtContext ydtContext = defaultYdtBuilder.getRootNode();

        YdtContext ydtContext1 = ydtContext.getFirstChild();

        DefaultYobBuilder defaultYobBuilder = new DefaultYobBuilder();

        Object yangObject = defaultYobBuilder.getYangObject((YdtExtendedContext) ydtContext1);
        assertNotNull(yangObject);
        try {

            Field field = yangObject.getClass().getDeclaredField("emptyList");
            field.setAccessible(true);
            List booleanList = (List) field.get(yangObject);
            Field invalidinterval = booleanList.get(0).getClass().getDeclaredField("empty");
            invalidinterval.setAccessible(true);
            assertEquals(false, invalidinterval.get(booleanList.get(0)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
