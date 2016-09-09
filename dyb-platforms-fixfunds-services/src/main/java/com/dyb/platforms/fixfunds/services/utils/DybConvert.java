package com.dyb.platforms.fixfunds.services.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2016/9/8.
 */
public class DybConvert {
    /**
     * 日期格式：yyyyMMdd
     */
    public static String DATEFORMAT_DATA_EN = "yyyyMMdd";
    /**
     * 日期格式：yyyyMMddHHmmss
     */
    public static String DATEFORMAT_DATA_EN_ALL = "yyyyMMddHHmmss";
    /**
     * 日期格式：yyyyMMddHHmmss
     */
    public static String DATEFORMAT_DATA_EN_2 = "yyyy/MM/dd";
    /**
     * 日期格式：yyyyMMdd HH:mm:ss
     */
    public static String DATEFORMAT_DATA_EN_1 = "yyyyMMdd HH:mm:ss";
    /**
     * 日期格式：yy-MM-dd
     */
    public static String DATEFORMAT_DATA_EN_SORT = "yy-MM-dd";
    /**
     * 日期格式：yyyy-MM-dd
     */
    public static String DATEFORMAT_DATA_EN_LONG = "yyyy-MM-dd";
    /**
     * 日期格式：HH:mm
     */
    public static String DATEFORMAT_TIME_EN_SORT = "HH:mm";
    /**
     * 日期格式：HH:mm:ss
     */
    public static String DATEFORMAT_TIME_EN_LONG = "HH:mm:ss";
    /**
     * 日期格式：yy-MM-dd HH:mm
     */
    public static String DATEFORMAT_DATETIME_EN_SORT = "yy-MM-dd HH:mm";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static String DATEFORMAT_DATETIME_EN_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式：yy年M月d日
     */
    public static String DATEFORMAT_DATA_CH_SORT = "yy年M月d日";
    /**
     * 日期格式：yyyy年M月d日
     */
    public static String DATEFORMAT_DATA_CH_LONG = "yyyy年M月d日";
    /**
     * 日期格式：H点m分
     */
    public static String DATEFORMAT_TIME_CH_SORT = "H点m分";
    /**
     * 日期格式：H点m分s秒
     */
    public static String DATEFORMAT_TIME_CH_LONG = "H点m分s秒";
    /**
     * 日期格式：yy年M月d日H点m分
     */
    public static String DATEFORMAT_DATETIME_CH_SORT = "yy年M月d日H点m分";
    /**
     * 日期格式：yyyy年M月d日H点m分s秒
     */
    public static String DATEFORMAT_DATETIME_CH_LONG = "yyyy年M月d日H点m分s秒";

    public static String intToString(int value) {
        return Integer.toString(value);
    }

    public static String byteToString(byte value) {
        return Byte.toString(value);
    }

    public static String shortToString(short value) {
        return Short.toString(value);
    }

    public static String longToString(long value) {
        return Long.toString(value);
    }

    public static String floatToString(float value) {
        return Float.toString(value);
    }

    public static String doubleToString(double value) {
        return Double.toString(value);
    }

    public static String booleanToString(boolean value) {
        return Boolean.toString(value);
    }

    public static String charToString(char value) {
        return Character.toString(value);
    }

    public static String dateToString(Date value) {
        return dateToString(value, DATEFORMAT_DATETIME_EN_LONG);
    }

    public static String dateToString(Date value, String format) {
        if (value == null)
            return null;
        DateFormat dataFormat = new SimpleDateFormat(format);
        return dataFormat.format(value);
    }

    public static String enumToString(Enum value) {
        if (value == null)
            return null;
        return value.toString();
    }

    public static int toInt(String text) {
        if (DybUtils.isEmptyOrNull(text))
            return 0;
        return Integer.parseInt(text);
    }

    public static byte toByte(String text) {
        if (DybUtils.isEmptyOrNull(text))
            return 0;
        return Byte.parseByte(text);
    }

    public static short toShort(String text) {
        if (DybUtils.isEmptyOrNull(text))
            return 0;
        return Short.parseShort(text);
    }

    public static long toLong(String text) {
        if (DybUtils.isEmptyOrNull(text))
            return 0;
        return Long.parseLong(text);
    }

    public static float toFloat(String text) {
        if (DybUtils.isEmptyOrNull(text))
            return 0;
        return Float.parseFloat(text);
    }

    public static double toDouble(String text) {
        if (DybUtils.isEmptyOrNull(text))
            return 0;
        return Double.parseDouble(text);
    }

    public static boolean toBoolean(String text) {
        return !DybUtils.isEmptyOrNull(text) && Boolean.parseBoolean(text);
    }

    public static char toChar(String text) {
        if (DybUtils.isEmptyOrNull(text))
            return '\0';
        return text.charAt(0);
    }

    public static Date toDate(String text) throws ParseException {
        return toDate(text, DATEFORMAT_DATETIME_EN_LONG);
    }

    /**
     *
     * @param text 格式一：20150102 格式二：20150102 12:12:12
     * @return
     * @throws java.text.ParseException
     */
    public static Date toDateByString(String text) throws ParseException {
        text=text.trim();
        StringBuilder builder=new StringBuilder(text);
        builder.insert(4,"-");
        builder.insert(7,"-");
        text=builder.toString();
        if (text.length()>10){
            return toDate(text, DATEFORMAT_DATETIME_EN_LONG);
        }else {
            return DybConvert.toDate(text,DATEFORMAT_DATA_EN_LONG);
        }
    }

    public static Date toDate(String text, String format) throws ParseException {
        if (DybUtils.isEmptyOrNull(text))
            return null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(text);
    }

    public static Enum toEnum(Class enumClazz, String text) {
        if (DybUtils.isEmptyOrNull(text))
            return null;
        return Enum.valueOf(enumClazz, text);
    }

    public static DybConvertResult toIntTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toInt(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toByteTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toByte(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toShortTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toShort(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toLongTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toLong(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toFloatTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toFloat(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toDoubleTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toDouble(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toBooleanTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toBoolean(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toCharTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toChar(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toDateTry(String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toDate(text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    public static DybConvertResult toEnumTry(Class enumClazz, String text) {
        DybConvertResult result = null;
        try {
            result = DybConvertResult.NewSuccess(toEnum(enumClazz, text));
        } catch (Exception e) {
            result = DybConvertResult.NewFail();
        }
        return result;
    }

    /**
     * 将基础类型转换为字符串，基础类型为 简单类型、java.util.String、java.util.Date、包装类、枚举
     *
     * @param value 基础类型值
     * @return 字符串
     */
    public static String basisToString(Object value) {
        if (!DybUtils.isBasisClass(value.getClass()))
            throw new IllegalArgumentException("仅支持简单类型、java.util.String、java.util.Date、包装类、枚举");
        if (value instanceof String)
            return (String) value;
        if (value instanceof Integer)
            return intToString((int) value);
        if (value instanceof Byte)
            return byteToString((byte) value);
        if (value instanceof Short)
            return shortToString((short) value);
        if (value instanceof Long)
            return longToString((long) value);
        if (value instanceof Float)
            return floatToString((float) value);
        if (value instanceof Double)
            return doubleToString((double) value);
        if (value instanceof Boolean)
            return booleanToString((boolean) value);
        if (value instanceof Character)
            return charToString((char) value);
        if (value instanceof Date)
            return dateToString((Date) value);
        if (value instanceof Enum)
            return enumToString((Enum) value);
        throw new IllegalArgumentException("不支持的类型'" + value.getClass().getName() + "'");
    }

    /**
     * 将字符串转换为基础类型值，基础类型为 简单类型、java.util.String、java.util.Date、包装类、枚举
     *
     * @param text 字符串
     * @param cls  基础类型
     * @return 基础类型值
     */
    @SuppressWarnings("unchecked")
    public static <T> T basisFromString(String text, Class<T> cls) throws ParseException {
        if (!DybUtils.isBasisClass(cls))
            throw new IllegalArgumentException("仅支持简单类型、java.util.String、java.util.Date、包装类、枚举");
        Object result = null;
        if (cls.getName().compareTo(String.class.getName()) == 0)
            result = text;
        else if (cls.getName().compareTo(int.class.getName()) == 0)
            result = toInt(text);
        else if (cls.getName().compareTo(Integer.class.getName()) == 0)
            result = new Integer(toInt(text));
        else if (cls.getName().compareTo(byte.class.getName()) == 0)
            result = toByte(text);
        else if (cls.getName().compareTo(Byte.class.getName()) == 0)
            result = new Byte(toByte(text));
        else if (cls.getName().compareTo(short.class.getName()) == 0)
            result = toShort(text);
        else if (cls.getName().compareTo(Short.class.getName()) == 0)
            result = new Short(toShort(text));
        else if (cls.getName().compareTo(long.class.getName()) == 0)
            result = toLong(text);
        else if (cls.getName().compareTo(Long.class.getName()) == 0)
            result = new Long(toLong(text));
        else if (cls.getName().compareTo(float.class.getName()) == 0)
            result = toFloat(text);
        else if (cls.getName().compareTo(Float.class.getName()) == 0)
            result = new Float(toFloat(text));
        else if (cls.getName().compareTo(double.class.getName()) == 0)
            result = toDouble(text);
        else if (cls.getName().compareTo(Double.class.getName()) == 0)
            result = new Double(toDouble(text));
        else if (cls.getName().compareTo(boolean.class.getName()) == 0)
            result = toBoolean(text);
        else if (cls.getName().compareTo(Boolean.class.getName()) == 0)
            result = new Boolean(toBoolean(text));
        else if (cls.getName().compareTo(char.class.getName()) == 0)
            result = toChar(text);
        else if (cls.getName().compareTo(Character.class.getName()) == 0)
            result = new Character(toChar(text));
        else if (cls.isAssignableFrom(Date.class))
            result = toDate(text);
        else if (cls.isEnum())
            result = toEnum(cls, text);
        else
            throw new IllegalArgumentException("不支持的类型'" + cls.getName() + "'");
        return (T) result;
    }
}
