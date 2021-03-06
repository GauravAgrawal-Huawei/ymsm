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

import org.onosproject.yangutils.datamodel.RpcNotificationContainer;
import org.onosproject.yangutils.datamodel.YangBit;
import org.onosproject.yangutils.datamodel.YangBits;
import org.onosproject.yangutils.datamodel.YangDerivedInfo;
import org.onosproject.yangutils.datamodel.YangIdentity;
import org.onosproject.yangutils.datamodel.YangIdentityRef;
import org.onosproject.yangutils.datamodel.YangLeaf;
import org.onosproject.yangutils.datamodel.YangLeafList;
import org.onosproject.yangutils.datamodel.YangLeafRef;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeContextInfo;
import org.onosproject.yangutils.datamodel.YangType;
import org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.yob.exception.YobException;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;
import java.util.BitSet;
import java.util.Map;
import java.util.regex.Pattern;

import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_AUGMENT_NODE;
import static org.onosproject.yms.app.ydt.AppType.YOB;
import static org.onosproject.yms.app.yob.YobConstants.DEFAULT;
import static org.onosproject.yms.app.yob.YobConstants.E_DATA_TYPE_NOT_SUPPORT;
import static org.onosproject.yms.app.yob.YobConstants.E_FAIL_TO_LOAD_CLASS;
import static org.onosproject.yms.app.yob.YobConstants.E_FAIL_TO_LOAD_CONSTRUCTOR;
import static org.onosproject.yms.app.yob.YobConstants.E_INVALID_DATA_TREE;
import static org.onosproject.yms.app.yob.YobConstants.E_INVALID_EMPTY_DATA;
import static org.onosproject.yms.app.yob.YobConstants.FROM_STRING;
import static org.onosproject.yms.app.yob.YobConstants.LEAF_IDENTIFIER;
import static org.onosproject.yms.app.yob.YobConstants.L_FAIL_TO_LOAD_CLASS;
import static org.onosproject.yms.app.yob.YobConstants.OF;
import static org.onosproject.yms.app.yob.YobConstants.OP_PARAM;
import static org.onosproject.yms.app.yob.YobConstants.PERIOD;
import static org.onosproject.yms.app.yob.YobConstants.SELECT_LEAF;
import static org.onosproject.yms.app.yob.YobConstants.SPACE;
import static org.onosproject.yms.app.yob.YobConstants.VALUE_OF;

/**
 * Utils to support object creation.
 */
final class YobUtils {

    private static final Logger log = LoggerFactory.getLogger(YobUtils.class);

    // no instantiation
    private YobUtils() {
    }

    /**
     * Sets data from string value in parent method.
     *
     * @param type                refers to YANG type
     * @param leafValue           leafValue argument is used to set the value
     *                            in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the
     *                            underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get
     *                            application related
     *                            information maintained in YDT
     * @throws InvocationTargetException failed to invoke method
     * @throws IllegalAccessException    cannot access the member
     * @throws NoSuchMethodException     method not found
     */
    static void setDataFromStringValue(YangDataTypes type, String leafValue,
                                       Method parentSetterMethod,
                                       Object parentBuilderObject,
                                       YdtExtendedContext ydtExtendedContext)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        switch (type) {
            case INT8:
                parentSetterMethod.invoke(parentBuilderObject,
                                          Byte.parseByte(leafValue));
                break;

            case UINT8:
            case INT16:
                parentSetterMethod.invoke(parentBuilderObject,
                                          Short.parseShort(leafValue));
                break;

            case UINT16:
            case INT32:
                parentSetterMethod.invoke(parentBuilderObject,
                                          Integer.parseInt(leafValue));
                break;

            case UINT32:
            case INT64:
                parentSetterMethod.invoke(parentBuilderObject,
                                          Long.parseLong(leafValue));
                break;

            case UINT64:
                parentSetterMethod.invoke(parentBuilderObject,
                                          new BigInteger(leafValue));
                break;

            case EMPTY:
                if (leafValue == null || leafValue.equals("")) {
                    parentSetterMethod.invoke(parentBuilderObject, true);
                } else {
                    log.info(E_INVALID_EMPTY_DATA);
                }
                break;
            case BOOLEAN:
                parentSetterMethod.invoke(parentBuilderObject,
                                          Boolean.parseBoolean(leafValue));
                break;

            case STRING:
                parentSetterMethod.invoke(parentBuilderObject, leafValue);
                break;

            case BINARY:
                parentSetterMethod.invoke(parentBuilderObject,
                                          Base64.getDecoder().decode(leafValue));
                break;

            case BITS:
                parseBitSetTypeInfo(ydtExtendedContext, parentSetterMethod,
                                    parentBuilderObject, leafValue);
                break;

            case DECIMAL64:
                parentSetterMethod.invoke(parentBuilderObject,
                                          new BigDecimal(leafValue));
                break;

            case DERIVED:
                parseDerivedTypeInfo(ydtExtendedContext, parentSetterMethod,
                                     parentBuilderObject, leafValue, false);
                break;

            case IDENTITYREF:
                parseIdentityRefInfo(ydtExtendedContext, parentSetterMethod,
                                     parentBuilderObject, leafValue);
                break;

            case UNION:
                parseDerivedTypeInfo(ydtExtendedContext, parentSetterMethod,
                                     parentBuilderObject, leafValue, false);
                break;

            case LEAFREF:
                parseLeafRefTypeInfo(ydtExtendedContext, parentSetterMethod,
                                     parentBuilderObject, leafValue);
                break;

            case ENUMERATION:
                parseDerivedTypeInfo(ydtExtendedContext, parentSetterMethod,
                                     parentBuilderObject, leafValue, true);
                break;

            default:
                log.error(E_DATA_TYPE_NOT_SUPPORT);
        }
    }

    /**
     * Sets data from string value in parent method.
     *
     * @throws InvocationTargetException failed to invoke method
     * @throws IllegalAccessException    cannot access the member
     * @throws NoSuchMethodException     method not found
     */
    static void setSelectLeaf(Class builderClass,
                              YdtExtendedContext leafNode,
                              YangSchemaRegistry schemaRegistry,
                              Object builderObject) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        YangSchemaNode parentSchema = ((YdtExtendedContext) leafNode
                .getParent()).getYangSchemaNode();
        while (parentSchema.getReferredSchema() != null) {
            parentSchema = parentSchema.getReferredSchema();
        }

        while (((YangNode) parentSchema).getParent() != null) {
            parentSchema = ((YangNode) parentSchema).getParent();
        }
        String qualName = getQualifiedinterface(parentSchema);
        Class<?> regClass = schemaRegistry.getRegisteredClass(parentSchema
        );
        if (regClass == null) {
            throw new YobException(E_FAIL_TO_LOAD_CLASS + qualName);
        }

        Class<?> interfaceClass = null;
        try {
            interfaceClass = regClass.getClassLoader().loadClass(qualName);
        } catch (ClassNotFoundException e) {
            log.info(E_FAIL_TO_LOAD_CLASS, qualName);
        }

        Class<?>[] innerClasses = interfaceClass.getClasses();
        for (Class<?> innerEnumClass : innerClasses) {
            if (innerEnumClass.getSimpleName().equals(LEAF_IDENTIFIER)) {
                Method valueOfMethod = innerEnumClass
                        .getDeclaredMethod(VALUE_OF, String.class);
                String leafName = leafNode.getYangSchemaNode()
                        .getJavaAttributeName().toUpperCase();
                Object obj = valueOfMethod.invoke(null, leafName);
                Method selectLeafMethod = builderClass
                        .getDeclaredMethod(SELECT_LEAF, innerEnumClass);
                selectLeafMethod.invoke(builderObject, obj);
                break;
            }
        }
    }

    /**
     * To set data into parent setter method from string value for derived type.
     *
     * @param leafValue           leafValue argument is used to set the value
     *                            in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the
     *                            underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get
     *                            application related
     *                            information maintained in YDT
     * @param isEnum              isEnum parameter is used to check whether
     *                            type is enum or derived
     *                            information maintained in YDT
     * @throws InvocationTargetException throws InvocationTargetException
     * @throws IllegalAccessException    throws IllegalAccessException
     * @throws NoSuchMethodException     throws NoSuchMethodException
     */
    private static void parseDerivedTypeInfo(YdtExtendedContext ydtExtendedContext,
                                             Method parentSetterMethod,
                                             Object parentBuilderObject,
                                             String leafValue, boolean isEnum)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        Class<?> childSetClass = null;
        Constructor<?> childConstructor = null;
        Object childValue = null;
        Object childObject = null;
        Method childMethod = null;

        YangSchemaNode yangJavaModule = ydtExtendedContext.getYangSchemaNode();
        while (yangJavaModule.getReferredSchema() != null) {
            yangJavaModule = yangJavaModule.getReferredSchema();
        }

        String qualifiedClassName = yangJavaModule.getJavaPackage() + PERIOD +
                getCapitalCase(yangJavaModule.getJavaClassNameOrBuiltInType());
        ClassLoader classLoader = getClassLoader(null, qualifiedClassName,
                                                 ydtExtendedContext, null);
        try {
            childSetClass = classLoader.loadClass(qualifiedClassName);
        } catch (ClassNotFoundException e) {
            log.error(L_FAIL_TO_LOAD_CLASS, qualifiedClassName);
        }
        if (!isEnum) {

            if (childSetClass != null) {
                childConstructor = childSetClass.getDeclaredConstructor();
            }

            if (childConstructor != null) {
                childConstructor.setAccessible(true);
            }

            try {
                if (childConstructor != null) {
                    childObject = childConstructor.newInstance();
                }
            } catch (InstantiationException e) {
                log.error(E_FAIL_TO_LOAD_CONSTRUCTOR, qualifiedClassName);
            }
            if (childSetClass != null) {
                childMethod = childSetClass
                        .getDeclaredMethod(FROM_STRING, String.class);
            }
        } else {
            if (childSetClass != null) {
                childMethod = childSetClass.getDeclaredMethod(OF, String.class);
            }
            //leafValue = JavaIdentifierSyntax.getEnumJavaAttribute(leafValue);
            //leafValue = leafValue.toUpperCase();
        }
        if (childMethod != null) {
            childValue = childMethod.invoke(childObject, leafValue);
        }

        parentSetterMethod.invoke(parentBuilderObject, childValue);
    }

    /**
     * To set data into parent setter method from string value for bits type.
     *
     * @param leafValue           leafValue argument is used to set the value
     *                            in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the
     *                            underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get
     *                            application related
     *                            information maintained in YDT
     * @throws InvocationTargetException throws InvocationTargetException
     * @throws IllegalAccessException    throws IllegalAccessException
     * @throws NoSuchMethodException     throws NoSuchMethodException
     */
    private static void parseBitSetTypeInfo(YdtExtendedContext
                                                    ydtExtendedContext,
                                            Method parentSetterMethod,
                                            Object parentBuilderObject,
                                            String leafValue)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        Class<?> childSetClass = null;
        Object childValue = null;
        Object childObject = null;
        Method childMethod = null;

        YangSchemaNode yangJavaModule = ydtExtendedContext.getYangSchemaNode();
        while (yangJavaModule.getReferredSchema() != null) {
            yangJavaModule = yangJavaModule.getReferredSchema();
        }

        YangSchemaNode parentSchema = ((YdtExtendedContext) ydtExtendedContext
                .getParent()).getYangSchemaNode();
        String qualifiedClassName = parentSchema.getJavaPackage() + PERIOD +
                parentSchema.getJavaAttributeName().toLowerCase() +
                PERIOD + getCapitalCase(yangJavaModule.getJavaAttributeName());

        ClassLoader classLoader = getClassLoader(null, qualifiedClassName,
                                                 ydtExtendedContext, null);
        try {
            childSetClass = classLoader.loadClass(qualifiedClassName);
        } catch (ClassNotFoundException e) {
            log.error(L_FAIL_TO_LOAD_CLASS, qualifiedClassName);
        }

        if (childSetClass != null) {
            childMethod = childSetClass.getDeclaredMethod(FROM_STRING, String.class);
        }
        if (childMethod != null) {
            childValue = childMethod.invoke(childObject, leafValue);
        }

        parentSetterMethod.invoke(parentBuilderObject, childValue);
    }

    /**
     * To set data into parent setter method from string value for leafref type.
     *
     * @param leafValue           leafValue argument is used to set the value
     *                            in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the
     *                            underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get
     *                            application related
     *                            information maintained in YDT
     * @throws InvocationTargetException throws InvocationTargetException
     * @throws IllegalAccessException    throws IllegalAccessException
     * @throws NoSuchMethodException     throws NoSuchMethodException
     */
    private static void parseLeafRefTypeInfo(YdtExtendedContext ydtExtendedContext,
                                             Method parentSetterMethod,
                                             Object parentBuilderObject,
                                             String leafValue)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        YangSchemaNode schemaNode = ydtExtendedContext
                .getYangSchemaNode();
        while (schemaNode.getReferredSchema() != null) {
            schemaNode = schemaNode.getReferredSchema();
        }
        YangLeafRef leafRef;
        if (schemaNode instanceof YangLeaf) {
            leafRef = (YangLeafRef) ((YangLeaf) schemaNode)
                    .getDataType().getDataTypeExtendedInfo();
        } else {
            leafRef = (YangLeafRef) ((YangLeafList) schemaNode)
                    .getDataType().getDataTypeExtendedInfo();
        }

        YangType type = leafRef.getEffectiveDataType();
        if (type.getDataType() == YangDataTypes.DERIVED &&
                schemaNode.getJavaPackage().equals(YobConstants.JAVA_LANG)) {
            /*
             * If leaf is inside grouping, then its return type will be of type
             * Object and if its actual type is derived type then get the
             * effective built-in type and set the value.
             */
            YangDerivedInfo derivedInfo = (YangDerivedInfo) leafRef
                    .getEffectiveDataType()
                    .getDataTypeExtendedInfo();
            YobUtils.setDataFromStringValue(derivedInfo.getEffectiveBuiltInType(),
                                            leafValue, parentSetterMethod,
                                            parentBuilderObject,
                                            ydtExtendedContext);
        } else {
            YobUtils.setDataFromStringValue(type.getDataType(),
                                            leafValue, parentSetterMethod,
                                            parentBuilderObject,
                                            ydtExtendedContext);
        }
    }

    /**
     * Updates class loader for all the classes.
     *
     * @param registry           YANG schema registry
     * @param qualifiedClassName qualified class name
     * @param curNode            YDT context
     * @param rootNode           application root node
     * @return current class loader
     */
    static ClassLoader getClassLoader(YangSchemaRegistry registry,
                                      String qualifiedClassName,
                                      YdtExtendedContext curNode,
                                      YdtExtendedContext rootNode) {

        if (rootNode != null && curNode == rootNode) {
            YangSchemaNode curSchemaNode = curNode.getYangSchemaNode();
            while (!(curSchemaNode instanceof RpcNotificationContainer)) {
                curNode = (YdtExtendedContext) curNode.getParent();
                if (curNode == null) {
                    throw new YobException(E_INVALID_DATA_TREE);
                }
                curSchemaNode = curNode.getYangSchemaNode();
            }

            Class<?> regClass = registry.getRegisteredClass(curSchemaNode
            );
            return regClass.getClassLoader();
        }

        YdtExtendedContext parent =
                (YdtExtendedContext) curNode.getParent();
        YobWorkBench parentBuilderContainer =
                (YobWorkBench) parent.getAppInfo(YOB);
        Object parentObj =
                parentBuilderContainer.getParentBuilder(curNode, registry);
        return parentObj.getClass().getClassLoader();
    }

    /**
     * Returns the class loader to be used for the switched context schema node.
     *
     * @param curLoader current context class loader
     * @param context   switched context
     * @param registry  schema registry
     * @return class loader to be used for the switched context schema node
     */
    static ClassLoader getTargetClassLoader(
            ClassLoader curLoader,
            YangSchemaNodeContextInfo context,
            YangSchemaRegistry registry) {
        YangSchemaNode augmentSchemaNode = context.getContextSwitchedNode();
        if (augmentSchemaNode.getYangSchemaNodeType() == YANG_AUGMENT_NODE) {
            YangSchemaNode moduleNode =
                    ((YangNode) augmentSchemaNode).getParent();

            Class<?> moduleClass = registry.getRegisteredClass(
                    moduleNode);
            if (moduleClass == null) {
                throw new YobException(E_FAIL_TO_LOAD_CLASS + moduleNode
                        .getJavaClassNameOrBuiltInType());
            }
            return moduleClass.getClassLoader();
        }
        return curLoader;
    }

    public static Class<?> getModuleInterface(YangSchemaNode schemaNode,
                                              YangSchemaRegistry schemaRegistry) {
        YangNode yangNode = (YangNode) schemaNode;
        while (yangNode.getReferredSchema() != null) {
            yangNode = (YangNode) yangNode.getReferredSchema();
        }
        while (yangNode.getParent() != null) {
            yangNode = yangNode.getParent();
        }
        String qualName = getQualifiedinterface(yangNode);
        Class<?> regClass = schemaRegistry.getRegisteredClass(yangNode
        );
        if (regClass == null) {
            throw new YobException(E_FAIL_TO_LOAD_CLASS + qualName);
        }
        try {
            return regClass.getClassLoader().loadClass(qualName);
        } catch (ClassNotFoundException e) {
            log.error(L_FAIL_TO_LOAD_CLASS, qualName);
        }
        return null;
    }

    /**
     * Returns the qualified default / op param class.
     *
     * @param schemaNode schema node of the required class
     * @return qualified default / op param class name
     */
    static String getQualifiedDefaultClass(YangSchemaNode schemaNode) {
        String packageName = schemaNode.getJavaPackage();
        String className = getCapitalCase(
                schemaNode.getJavaClassNameOrBuiltInType());

        if (schemaNode instanceof RpcNotificationContainer) {
            return packageName + PERIOD + className + OP_PARAM;
        }

        return packageName + PERIOD + DEFAULT + className;
    }

    /**
     * Returns the qualified interface name.
     *
     * @param schemaNode schema node of the required class
     * @return qualified interface name
     */
    static String getQualifiedinterface(YangSchemaNode schemaNode) {
        String packageName = schemaNode.getJavaPackage();
        String className = getCapitalCase(
                schemaNode.getJavaClassNameOrBuiltInType());

        return packageName + PERIOD + className;
    }

    /**
     * Returns BitSet value from string.
     *
     * @param yangBits  schema node of the YANG bits
     * @param leafValue leaf value from RESTCONF
     * @return BitSet value
     */
    private static BitSet getBitSetValueFromString(YangBits yangBits,
                                                   String leafValue) {
        String[] bitNames = leafValue.trim().split(Pattern.quote(SPACE));
        Map<String, YangBit> bitNameMap = yangBits.getBitNameMap();
        BitSet bitDataSet = new BitSet();
        YangBit bit;
        for (String bitName : bitNames) {
            bit = bitNameMap.get(bitName);
            if (bit == null) {
                throw new YobException("Unable to find corresponding bit" +
                                               " position for bit name: "
                                               + bitName);
            }
            bitDataSet.set(bit.getPosition());
        }
        return bitDataSet;
    }

    /**
     * Returns the capital cased first letter of the given string.
     *
     * @param name string to be capital cased
     * @return capital cased string
     */
    public static String getCapitalCase(String name) {
        // TODO: It will be removed if common util is committed.
        return name.substring(0, 1).toUpperCase() +
                name.substring(1);
    }

    /**
     * To set data into parent setter method from string value for identity ref.
     *
     * @param leafValue           leafValue argument is used to set the value
     *                            in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the
     *                            underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get
     *                            application related
     *                            information maintained in YDT
     * @throws InvocationTargetException throws InvocationTargetException
     * @throws IllegalAccessException    throws IllegalAccessException
     * @throws NoSuchMethodException     throws NoSuchMethodException
     */
    private static void parseIdentityRefInfo(YdtExtendedContext
                                                     ydtExtendedContext,
                                             Method parentSetterMethod,
                                             Object parentBuilderObject,
                                             String leafValue)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        Class<?> childSetClass = null;
        Object childValue = null;
        Method childMethod = null;

        YangSchemaNode yangJavaModule = ydtExtendedContext.getYangSchemaNode();
        while (yangJavaModule.getReferredSchema() != null) {
            yangJavaModule = yangJavaModule.getReferredSchema();
        }

        String qualifiedClassName = null;

        YangType type;
        if (yangJavaModule instanceof YangLeaf) {
            type = ((YangLeaf) yangJavaModule).getDataType();
        } else {
            type = ((YangLeafList) yangJavaModule).getDataType();
        }

        if (type.getDataType() == YangDataTypes.LEAFREF && yangJavaModule
                .getJavaPackage().equals(YobConstants.JAVA_LANG)) {
            YangLeafRef leafref = ((YangLeafRef) type.getDataTypeExtendedInfo());
            YangType effectiveType = leafref.getEffectiveDataType();
            if (effectiveType.getDataType() == YangDataTypes.IDENTITYREF) {
                YangIdentityRef identityref = ((YangIdentityRef) effectiveType
                        .getDataTypeExtendedInfo());
                YangIdentity identity = identityref.getReferredIdentity();
                qualifiedClassName = identity.getJavaPackage() + PERIOD +
                        getCapitalCase(identity.getJavaClassNameOrBuiltInType());
            }
        } else {
            qualifiedClassName = yangJavaModule.getJavaPackage() + PERIOD +
                    getCapitalCase(yangJavaModule.getJavaClassNameOrBuiltInType());
        }

        ClassLoader classLoader = getClassLoader(null, qualifiedClassName,
                                                 ydtExtendedContext, null);
        try {
            childSetClass = classLoader.loadClass(qualifiedClassName);
        } catch (ClassNotFoundException e) {
            log.error(L_FAIL_TO_LOAD_CLASS, qualifiedClassName);
        }

        if (childSetClass != null) {
            childMethod = childSetClass
                    .getDeclaredMethod(FROM_STRING, String.class);
        }

        if (childMethod != null) {
            childValue = childMethod.invoke(null, leafValue);
        }

        parentSetterMethod.invoke(parentBuilderObject, childValue);
    }
}
