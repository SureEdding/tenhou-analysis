package org.suree.model;

/**
 * Created by Sure on 8/16/16.
 */
public class Xml {

    private String xml;
    private String code;

    public Xml(String xml, String code) {
        this.xml = xml;
        this.code = code;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
