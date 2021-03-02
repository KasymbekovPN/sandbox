package org.kpn.ch4.custom;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomEditorExample {

    private FullName name;

    public FullName getName() {
        return name;
    }

    public void setName(FullName name) {
        this.name = name;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/custom/app-context-xml-custom-editor.xml");
        CustomEditorExample exampleBean = (CustomEditorExample) ctx.getBean("exampleBean");

        System.out.println(exampleBean.getName());

        ctx.close();;
    }
}
