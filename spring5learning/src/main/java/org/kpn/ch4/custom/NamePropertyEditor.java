package org.kpn.ch4.custom;

import java.beans.PropertyEditorSupport;

public class NamePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] splitName = text.split("\\s");
        setValue(new FullName(splitName));
    }
}
