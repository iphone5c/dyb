package com.dyb.platforms.fixfunds.services.utils.core;

/**
 * Created by 魏源 on 2015/6/14 0014.
 */
public class NameValue implements java.io.Serializable {
    private static final long serialVersionUID = -8916832542303234638L;
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public NameValue() { /* compiled code */ }

    public static NameValue create(String name, String value) {
        NameValue nameValue=new NameValue();
        nameValue.setName(name);
        nameValue.setValue(value);
        return nameValue;
    }

}
