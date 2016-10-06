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

/**
 * Represents common constant utility for YANG data tree.
 */
final class YdtConstants {

    /**
     * No instantiation.
     */
    private YdtConstants() {
    }

    // Ydt constant strings
    public static final String DUP = "Duplicate entry found under ";
    public static final String DUP_NAME = "Duplicate entry with name ";
    public static final String NOT_EXIST = " doesn't exist";
    public static final String APP = "Application with name ";
    public static final String NON_SET = "cannot be set in non ";
    public static final String NODE = " node";
    public static final String LIST = " leaf-list";
    public static final String LEAF = " leaf";
    public static final String PERIOD = ".";
    public static final String BACKSLASH = "\"";
    public static final String LIST_N = LIST + NODE;
    public static final String UNI_KEY =
            "Some of the key elements are not unique in ";
    public static final String KEY_LIST_STRING =
            "List of key cannot be created for leaf and leaf-list ";
    public static final String VAL = "Value ";
    public static final String VAL_S = "ValueSet ";
    public static final String INST = "instances ";
    public static final String FEW = "Too few ";
    public static final String MANY = "Too many ";
    public static final String EXP = "Expected ";
    public static final String MIN = "minimum ";
    public static final String MAX = "maximum ";
    public static final String OF = "of ";
    public static final String SPACE = " ";
    public static final String INVOKE = "cannot be invoke from non ";
    public static final String COUNT = "count ";
    public static final String KEY_PARA = "key parameter in ";
    public static final String KEY_M = " is missing some of the keys of ";
    public static final String SCHEMA = "Schema node with name ";
    public static final String M_KEY_PARA = MANY + KEY_PARA;
    public static final String F_KEY_PARA = FEW + KEY_PARA;
    public static final String EXP_COUNT = PERIOD + SPACE + EXP + COUNT;
    public static final String VAL_N = VAL + NON_SET + LEAF;
    public static final String VAL_NS = VAL_S + NON_SET + LIST;
    public static final String VAL_IN = VAL + INVOKE + LEAF;
    public static final String VAL_INS = VAL_S + INVOKE + LIST;
    public static final String M_INS = MANY + INST + OF;
    public static final String F_INS = FEW + INST + OF;
    public static final String EXP_MINS = PERIOD + SPACE + EXP + MIN + INST;
    public static final String EXP_MXINS = PERIOD + SPACE + EXP + MAX + INST;

    //Error strings
    public static final String E_USE_ADDLEAF =
            "Requested Node should be created using addLeaf interface";
    public static final String E_MULTI_INS =
            "Adds an instance of type list or leaf-list node only";
    public static final String E_CREATE =
            "Create request is not allowed under delete operation";
    public static final String E_DEL =
            "Delete request is not allowed under create operation";
    public static final String E_EXIST = "Node is already part of a tree";
    public static final String E_ATOMIC =
            "Child to be added is not atomic, it already has a child";
    public static final String E_SIB =
            "Child to be added is not atomic, it already has a next sibling";
    public static final String E_PRE =
            "Child to be added is not atomic, it already has a previous " +
                    "sibling";
    public static final String E_SUPPORT = "Requested node type not supported";
    public static final String E_INVOKE_PARENT =
            "Can't invoke get parent at logical root node";

    /**
     * Returns the error string by appending all the supplied strings
     * followed by period.
     *
     * @param str1
     * @return error string
     */
    public static String getErrorString(String... str1) {
        StringBuilder msg = new StringBuilder();
        for (String str : str1) {
            msg.append(str);
        }
        msg.append(PERIOD);
        return msg.toString();
    }

    /**
     * Returns the error string by appending all the supplied strings in such a
     * way that value comes under quotes followed by other two strings.
     *
     * @param str1  prefix string
     * @param value node value
     * @param str2  first suffix string
     * @param str3  last suffix string
     * @return error string
     */
    public static String getErrorStringWithQuote(String str1, String value,
                                                 String str2, String str3) {
        StringBuilder msg = new StringBuilder();
        msg.append(str1).append(BACKSLASH).append(value).append(BACKSLASH)
                .append(str2).append(str3);
        return msg.toString();
    }
}