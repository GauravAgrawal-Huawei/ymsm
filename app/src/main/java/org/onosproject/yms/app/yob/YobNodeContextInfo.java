package org.onosproject.yms.app.yob;

/**
 * Represents the YANG object builder's work bench corresponding to a YANG data
 * tree node.
 */
public class YobNodeContextInfo {

    /**
     * builder object or the built object corresponding to the current schema
     * node.
     */
    private Object schemaNodeBuilderOrBuiltObject;

    /**
     * Returns the builder object or the built object corresponding to the
     * current schema node.
     *
     * @return builder or built object
     */
    public Object getSchemaNodeBuilderOrBuiltObject() {
        return schemaNodeBuilderOrBuiltObject;
    }

    /**
     * Sets the builder object or the built object corresponding to the
     * current schema node.
     *
     * @param schemaNodeBuilderOrBuiltObject builder or built object
     */
    public void setSchemaNodeBuilderOrBuiltObject(
            Object schemaNodeBuilderOrBuiltObject) {
        this.schemaNodeBuilderOrBuiltObject = schemaNodeBuilderOrBuiltObject;
    }
}
