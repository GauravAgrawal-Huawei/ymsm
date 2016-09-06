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

import org.onosproject.yms.app.yob.exception.YobExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the container of YANG object being built or the builder.
 */
public class YobBuilderOrBuiltObject {
    private static final Logger log
            = LoggerFactory.getLogger(YobBuilderContainer.class);

    /**
     * Is the contained object a built object.
     */
    boolean isBuilt;

    /**
     * Builder or built object.
     */
    Object builderOrBuiltObject;

    /**
     * Default / op param class.
     */
    Class<?> yangDefaultClass;

    /**
     * Default / op param builder class.
     */
    Class<?>[] yangDefaultClassBuilder;

    public YobBuilderOrBuiltObject(
            String qualifiedClassName, ClassLoader registeredAppClassLoader) {

        try {
            yangDefaultClass =
                    registeredAppClassLoader.loadClass(qualifiedClassName);
            yangDefaultClassBuilder = yangDefaultClass.getDeclaredClasses();
            setBuilderObject(yangDefaultClassBuilder[0].newInstance());
        } catch (ClassNotFoundException e) {
            log.error("YOB: failed to load class for class "
                              + qualifiedClassName);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("YOB: failed to create an object for class "
                              + qualifiedClassName);
        } catch (NullPointerException e) {
            log.error("YOB: Reflection failed to create an object for " +
                              "class " + qualifiedClassName);
        }

    }

    /**
     * Return the built status of the contained object.
     *
     * @return if the object is a built object
     */
    public boolean isBuilt() {
        return isBuilt;
    }

    /**
     * Assign if the object is built or not.
     *
     * @param built status of built object
     */
    public void setBuilt(boolean built) {
        isBuilt = built;
    }

    /**
     * Returns the builder or built object.
     *
     * @return builder or built object
     */
    public Object getBuilderOrBuiltObject() {
        return builderOrBuiltObject;
    }

    /**
     * Sets the builder or built object.
     *
     * @param builderOrBuiltObject object to be mainted
     */
    public void setBuilderOrBuiltObject(Object builderOrBuiltObject) {
        this.builderOrBuiltObject = builderOrBuiltObject;
    }

    /**
     * Returns the builder object if it is set.
     *
     * @return builder object
     * @throws YobExceptions builder is not available
     */
    public Object getBuilderObject() {
        if (isBuilt()) {
            throw new YobExceptions("Object is already built, cannot fetch " +
                                            "builder");
        }

        if (getBuilderOrBuiltObject() == null) {
            throw new YobExceptions("Builder is not yet set, cannot fetch it");
        }

        return getBuilderOrBuiltObject();
    }

    /**
     * Check if the builder object is being initialized for the 1st time and
     * set it.
     *
     * @param builderObject new builder object
     */
    private void setBuilderObject(Object builderObject) {
        if (isBuilt()) {
            throw new YobExceptions("Object is already built, cannot set " +
                                            "builder");
        }

        if (getBuilderOrBuiltObject() != null) {
            throw new YobExceptions("ERROR: Builder is not already set");
        }

        setBuilderOrBuiltObject(builderObject);
    }

    /**
     * Returns the built object.
     *
     * @return built object
     */
    public Object getBuiltObject() {
        if (!isBuilt()) {
            throw new YobExceptions("Object is not built, cannot fetch it");
        }

        if (getBuilderOrBuiltObject() == null) {
            throw new YobExceptions("ERROR: Built object is not set");
        }

        return getBuilderOrBuiltObject();
    }

    public void setBuiltObject(Object builtObject) {
        if (isBuilt()) {
            throw new YobExceptions("ERROR Object is already built, cannot " +
                                            "build again");
        }

        if (getBuilderOrBuiltObject() == null) {
            throw new YobExceptions("ERROR: object building without builder");
        }

        setBuilt(true);
        setBuilderOrBuiltObject(builtObject);
    }
}
