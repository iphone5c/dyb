//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dyb.platforms.fixfunds.services.utils.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class QueryParams extends HashMap<String, Object> {
    private static final long serialVersionUID = -8916984542395986638L;
    private static final String DEFAULT_MULATTR_PAR_NAME = "_default_mulattr";

    public QueryParams() {
    }

    public void addMulAttrParameter(String name, String attrName, String attrValue) {
        if(!this.containsKey(name)) {
            this.put(name, new ArrayList());
        }

        ((List)this.get(name)).add(NameValue.create(attrName, attrValue));
    }

    public void addMulAttrParameter(String attrName, String attrValue) {
        this.addMulAttrParameter("_default_mulattr", attrName, attrValue);
    }

    public void addParameter(String name, Object value) {
        this.put(name, value);
    }

    public void addParameter(String name, String value) {
        this.put(name, value);
    }

    public void addParameter(String name, int value) {
        this.put(name, Integer.valueOf(value));
    }

    public void addParameter(String name, long value) {
        this.put(name, Long.valueOf(value));
    }

    public void addParameter(String name, short value) {
        this.put(name, Short.valueOf(value));
    }

    public void addParameter(String name, float value) {
        this.put(name, Float.valueOf(value));
    }

    public void addParameter(String name, double value) {
        this.put(name, Double.valueOf(value));
    }

    public void addParameter(String name, boolean value) {
        this.put(name, Boolean.valueOf(value));
    }

    public void addParameter(String name, Date value) {
        this.put(name, value);
    }

    public void addParameter(String name, Enum value) {
        this.put(name, value);
    }

    public void addParameterByRange(String name, int min, int max) {
        this.put(name + "_r_min", Integer.valueOf(min));
        this.put(name + "_r_max", Integer.valueOf(max));
    }

    public void addParameterByRange(String name, long min, long max) {
        this.put(name + "_r_min", Long.valueOf(min));
        this.put(name + "_r_max", Long.valueOf(max));
    }

    public void addParameterByRange(String name, short min, short max) {
        this.put(name + "_r_min", Short.valueOf(min));
        this.put(name + "_r_max", Short.valueOf(max));
    }

    public void addParameterByRange(String name, float min, float max) {
        this.put(name + "_r_min", Float.valueOf(min));
        this.put(name + "_r_max", Float.valueOf(max));
    }

    public void addParameterByRange(String name, double min, double max) {
        this.put(name + "_r_min", Double.valueOf(min));
        this.put(name + "_r_max", Double.valueOf(max));
    }

    public void addParameterByRange(String name, Date min, Date max) {
        this.put(name + "_r_min", min);
        this.put(name + "_r_max", max);
    }

    public void addParameterByEnum(String name, int... values) {
        this.put(name + "_enum", values);
    }

    public void addParameterByEnum(String name, long... values) {
        this.put(name + "_enum", values);
    }

    public void addParameterByEnum(String name, short... values) {
        this.put(name + "_enum", values);
    }

    public void addParameterByEnum(String name, float... values) {
        this.put(name + "_enum", values);
    }

    public void addParameterByEnum(String name, double... values) {
        this.put(name + "_enum", values);
    }

    public void addParameterByEnum(String name, String... values) {
        this.put(name + "_enum", values);
    }

    public void addParameterByEnum(String name, Enum... values) {
        this.put(name + "_enum", values);
    }

    public void addOrderBy(String name, boolean isAsc) {
        this.put("_orderBy", name + (isAsc?"_asc":"_desc"));
    }
}
