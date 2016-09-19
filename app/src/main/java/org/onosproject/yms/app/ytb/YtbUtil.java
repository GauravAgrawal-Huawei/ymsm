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

package org.onosproject.yms.app.ytb;

import org.onosproject.yangutils.datamodel.YangAugment;
import org.onosproject.yangutils.datamodel.YangCase;
import org.onosproject.yangutils.datamodel.YangLeafRef;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangNotification;
import org.onosproject.yangutils.datamodel.YangRpc;
import org.onosproject.yangutils.datamodel.YangType;
import org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContextOperationType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.ListIterator;

import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_AUGMENT_NODE;
import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_MULTI_INSTANCE_NODE;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.BOOLEAN;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.DECIMAL64;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.EMPTY;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.INT16;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.INT32;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.INT64;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.INT8;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.LEAFREF;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.UINT16;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.UINT32;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.UINT64;
import static org.onosproject.yangutils.datamodel.utils.builtindatatype.YangDataTypes.UINT8;

/**
 * Representation of utility for YANG tree builder.
 */
public final class YtbUtil {

    public static final String STR_NULL = "null";
    public static final String PERIOD = ".";
    private static final String SCHEMA_NAME_IN_ENUM = "schemaName";
    private static final String OPERATION_TYPE = "onosYangNodeOperationType";
    private static final String STR_NONE = "NONE";
    private static final String EQUALS_TO = "=";
    private static final String ENUM_LEAF_IDENTIFIER = "$LeafIdentifier";
    private static final char FLOWER_CLOSE_BRACE = '}';
    private static final int LENGTH_ONE = 1;

    private YtbUtil() {
    }

    /**
     * Returns parent object of the holder of leaf/leaf-list node. The object
     * has to be taken from node info when the holder is case and augment.
     *
     * @param ytbNodeInfo    node info of the holder
     * @param yangSchemaNode schema node of the holder
     * @return object of the parent
     */
    public static Object getParentObjectForTheLeafOrLeafList(
            YtbNodeInfo ytbNodeInfo, YangNode yangSchemaNode) {
        Object parentObject;
        if (yangSchemaNode instanceof YangCase) {
            parentObject = ytbNodeInfo.getCaseObject();
        } else if (yangSchemaNode instanceof YangAugment) {
            parentObject = ytbNodeInfo.getAugmentObject();
        } else {
            parentObject = ytbNodeInfo.getYangObject();
        }
        return parentObject;
    }

    /**
     * Returns the capital cased first letter of the given string.
     *
     * @param name string to be capital cased
     * @return capital cased string
     */
    public static String getCapitalCase(String name) {
        // TODO: It will be removed if common util is commited.
        return name.substring(0, 1).toUpperCase() + name
                .substring(1);
    }

    /**
     * Returns the parent object of the node. The object has to be taken from
     * node info when the holder is case and augment.
     *
     * @param parentNodeInfo    node info of the parent
     * @param currentSchemaNode schema node of the parent
     * @return object of the parent
     */
    public static Object getParentObjectForTheNode(YtbNodeInfo parentNodeInfo,
                                                   YangNode currentSchemaNode) {
        Object parentObject;
        if (currentSchemaNode.getParent() instanceof YangCase) {
            parentObject = parentNodeInfo.getCaseObject();
        } else if (currentSchemaNode.getParent() instanceof YangAugment) {
            parentObject = parentNodeInfo.getAugmentObject();
        } else {
            parentObject = parentNodeInfo.getYangObject();
        }
        return parentObject;
    }

    /**
     * Returns the value of an attribute, in a class object.
     *
     * @param objectOfTheNode object of the node
     * @param nameOfTheField  name of the attribute
     * @return object of the attribute
     */
    public static Object getAttributeOfObject(Object objectOfTheNode,
                                              String nameOfTheField) {
        Class classOfNode = objectOfTheNode.getClass();
        Method getterMethodOfField;
        try {
            getterMethodOfField = classOfNode.getDeclaredMethod(nameOfTheField);
            return getterMethodOfField.invoke(objectOfTheNode);
        } catch (InvocationTargetException | NoSuchMethodException |
                IllegalAccessException e) {
            throw new YtbException(e);
        }
    }

    /**
     * Returns the object of the declared method in parent class by invoking
     * through the child class object.
     *
     * @param parentClass     parent class of the declared method
     * @param childClass      child class which inherits the parent class
     * @param nameOfTheMethod name of the declared method
     * @return value of the method
     */
    public static Object getAttributeFromInheritance(Class parentClass, Object
            childClass, String nameOfTheMethod) {
        Method getterMethodOfField;
        try {
            getterMethodOfField = parentClass.getDeclaredMethod(
                    nameOfTheMethod);
            return getterMethodOfField.invoke(childClass);
        } catch (InvocationTargetException | NoSuchMethodException |
                IllegalAccessException e) {
            throw new YtbException(e);
        }
    }

    /**
     * Returns interface class from an implementation class object.
     *
     * @param implClassObject implementation class object
     * @return interface class
     */
    public static Class getInterfaceClassFromImplClass(Object implClassObject) {
        Class implClass = implClassObject.getClass();
        Class[] interfacesOfClass = implClass.getInterfaces();
        Class interfaceClass;
        if (interfacesOfClass.length == LENGTH_ONE) {
            ListIterator<Class> rootClassInterfacesIterator = Arrays.asList(
                    interfacesOfClass).listIterator();
            interfaceClass = rootClassInterfacesIterator.next();
        } else {
            // TODO: Need to handle when a impl class has more than one
            // interface.
            throw new YtbException("Implementation class having more than one" +
                                           " interface is not handled");
        }
        return interfaceClass;
    }

    /**
     * Returns the operation type value for a class object. If the operation
     * type is not set, then none will be set to the YDT node.
     *
     * @param objectOfTheNode object of the class
     * @return operation type of the class
     */
    public static YdtContextOperationType getOperationTypeOfTheNode(
            Object objectOfTheNode) {
        Object operationTypeObject = getAttributeOfObject(objectOfTheNode,
                                                          OPERATION_TYPE);
        String valueOfOpType = String.valueOf(operationTypeObject);
        if (valueOfOpType.equals(STR_NULL) || valueOfOpType.isEmpty()) {
            valueOfOpType = STR_NONE;
        }
        return YdtContextOperationType.valueOf(valueOfOpType);
    }

    /**
     * Decides if the data type of the leaf is primitive data type from its
     * respective data type.
     *
     * @param yangType type of the leaf
     * @return status of the type to be primitive
     */
    public static boolean isTypePrimitive(YangType yangType) {
        if (yangType.getDataType() == LEAFREF) {
            YangLeafRef leafref = (YangLeafRef) yangType
                    .getDataTypeExtendedInfo();
            return isPrimitiveDataType(leafref.getEffectiveDataType()
                                               .getDataType());
        }
        return isPrimitiveDataType(yangType.getDataType());
    }

    /**
     * Returns the class loader for augment, by loading the module where the
     * augment is present.
     *
     * @param currentSchemaNode current augment node
     * @param appSchemaRegistry schema registry
     * @return class loader of module
     */
    public static ClassLoader getClassLoaderForAugment(
            YangNode currentSchemaNode, YangSchemaRegistry appSchemaRegistry) {
        // Gets the module node.
        YangNode moduleNode = currentSchemaNode.getParent();
        String moduleName = moduleNode.getJavaClassNameOrBuiltInType();
        // Gets the module package
        String modulePackage = moduleNode.getJavaPackage();
        // Gets the module class from schema registry.
        Class moduleClass = appSchemaRegistry.getRegisteredClass(
                moduleNode, modulePackage + PERIOD + moduleName);
        return moduleClass.getClassLoader();
    }

    /**
     * Decides if the leaf data is actually set or not, by passing the enum
     * value through reflection.
     *
     * @param objectOfNode            object if the node
     * @param javaNameOfLeaf          java name of the leaf
     * @param valueOrSelectMethodName name of the method
     * @return value of the boolean method
     */
    public static String isValueOrSelectLeafSet(Object objectOfNode, String
            javaNameOfLeaf, String valueOrSelectMethodName) {

        Class classOfNode = objectOfNode.getClass();
        // Gets the interface class.
        Class interfaceClass = getInterfaceClassFromImplClass(objectOfNode);

        // Appends the enum inner package to the interface class package.
        String enumPackage = interfaceClass.getName() + ENUM_LEAF_IDENTIFIER;
        // Loads the interface class.
        ClassLoader classLoader = interfaceClass.getClassLoader();
        Class leafIdentifierEnumeration;
        try {
            // Takes the enum from the loaded interface class.
            leafIdentifierEnumeration = classLoader.loadClass(enumPackage);
            // Gets the method from the object.
            Method methodMyMethod = classOfNode.getMethod(
                    valueOrSelectMethodName, leafIdentifierEnumeration);
            // Gets the value of the enum.
            Enum valueOfEnum = Enum.valueOf(leafIdentifierEnumeration,
                                            javaNameOfLeaf.toUpperCase());
            // Invokes the method with the value of enum as param.
            return String.valueOf(methodMyMethod.invoke(objectOfNode, valueOfEnum));
        } catch (IllegalAccessException | InvocationTargetException |
                NoSuchMethodException | ClassNotFoundException e) {
            throw new YtbException(e);
        }
    }

    /**
     * Returns the string value from the respective data types of the
     * leaf/leaf-list.
     * // TODO: Remove this method and append to the data model utils.
     *
     * @param objectOfField object of the leaf/leaf-list field
     * @param dataType      type of the leaf/leaf-list
     * @return string value from the type
     */
    public static String getStringFromDataType(Object objectOfField, YangType
            dataType) {
        YangDataTypes yangDataTypes = dataType.getDataType();
        switch (yangDataTypes) {
            case INT8:
            case INT16:
            case INT32:
            case INT64:
            case UINT8:
            case UINT16:
            case UINT32:
            case UINT64:
            case EMPTY:
            case IDENTITYREF:
            case STRING:
            case DECIMAL64:
            case INSTANCE_IDENTIFIER:
            case DERIVED:
            case UNION:
                //TODO: Generated code has to be changed, it must select the
                // setting leaf and it must give back the
                // corresponding toString of that type.
            case BOOLEAN:
            case BITS:
                String valueOfTheField = String.valueOf(objectOfField);
                return getValueFromTheToStringHelper(valueOfTheField);
            case BINARY:
                return Base64.getEncoder().encodeToString((byte[])
                                                                  objectOfField);
            case LEAFREF:
                YangLeafRef leafRef = (YangLeafRef) dataType
                        .getDataTypeExtendedInfo();
                YangType leafrefType = leafRef.getEffectiveDataType();
                return getStringFromDataType(objectOfField, leafrefType);
            case ENUMERATION:
                Object value;
                value = getAttributeOfObject(
                        objectOfField,
                        SCHEMA_NAME_IN_ENUM);
                String valueOfTheFieldInEnum = String.valueOf(value);
                return getValueFromTheToStringHelper(valueOfTheFieldInEnum);
            default:
                throw new YtbException("Unsupported data type. Cannot be " +
                                               "processed.");
        }
    }

    /**
     * Returns the required string from the raw string received. The string
     * helper sends the raw string which is parsed to get the exact values.
     *
     * @param strWithToStringHelper raw string
     * @return parsed value
     */
    private static String getValueFromTheToStringHelper(
            String strWithToStringHelper) {
        if (strWithToStringHelper.contains(EQUALS_TO)) {
            int indexValue = strWithToStringHelper.lastIndexOf(EQUALS_TO);
            int indexValueOfFlowerOpenBracket = strWithToStringHelper.indexOf(FLOWER_CLOSE_BRACE);
            if (indexValue != -1) {
                return strWithToStringHelper.substring(
                        indexValue + 1, indexValueOfFlowerOpenBracket);
            }
        }
        return strWithToStringHelper;
    }

    /**
     * Returns whether the data type is of primitive data type.
     *
     * @param dataType data type to be checked
     * @return status of the data type as primitive
     */
    private static boolean isPrimitiveDataType(YangDataTypes dataType) {
        return dataType == INT8 || dataType == INT16 || dataType == INT32 ||
                dataType == INT64 || dataType == UINT8 || dataType == UINT16 ||
                dataType == UINT32 || dataType == UINT64 ||
                dataType == DECIMAL64 || dataType == BOOLEAN ||
                dataType == EMPTY;

    }

    /**
     * Decides if the node needs to be processed. For the nodes such as
     * notification, RPC, augment there is a different flow. So at normal
     * conditions it must be skipped.
     *
     * @param yangNode node to be checked
     * @return status of the process to be proceeded.
     */
    public static boolean isProcessableNode(YangNode yangNode) {
        return yangNode != null && !(yangNode instanceof YangNotification) &&
                !(yangNode instanceof YangRpc) &&
                !(yangNode instanceof YangAugment);
    }

    /**
     * Decides if the YANG node is multi instance.
     *
     * @param yangNode YANG node
     * @return status if multi instance
     */
    public static boolean isMultiInstanceNode(YangNode yangNode) {
        return yangNode.getYangSchemaNodeType() == YANG_MULTI_INSTANCE_NODE;
    }

    /**
     * Decides if the YANG node is augment.
     *
     * @param yangNode YANG node
     * @return status if augment
     */
    public static boolean isAugmentNode(YangNode yangNode) {
        return yangNode.getYangSchemaNodeType() == YANG_AUGMENT_NODE;
    }
}
