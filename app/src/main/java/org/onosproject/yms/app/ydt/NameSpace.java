package org.onosproject.yms.app.ydt;

import org.onosproject.yangutils.datamodel.YangNamespace;

/**
 * Created by v70786 on 7/10/16.
 */
public class NameSpace implements YangNamespace {

    String nameSpace;

    private NameSpace() {
    }

    public NameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    @Override
    public String getModuleNamespace() {
        return nameSpace;
    }

    @Override
    public String getModuleName() {
        return nameSpace;
    }
}
