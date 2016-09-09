package com.dyb.platforms.fixfunds.services.utils.core.serializes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/2.
 */
public class JsonSerialize
        implements ISerialize
{
    public String serialize(Object obj)
    {
        if (obj == null)
            return null;
        SerializeConfig mapping = new SerializeConfig();
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));

        List features = new ArrayList();
        features.add(SerializerFeature.WriteDateUseDateFormat);
        features.add(SerializerFeature.WriteMapNullValue);
        features.add(SerializerFeature.WriteNullBooleanAsFalse);
        features.add(SerializerFeature.WriteNullListAsEmpty);
        features.add(SerializerFeature.WriteNullNumberAsZero);
        features.add(SerializerFeature.WriteNullStringAsEmpty);
        features.add(SerializerFeature.DisableCircularReferenceDetect);
        if (!(obj instanceof JSONObject)) {
            features.add(SerializerFeature.WriteClassName);
        }
        String json = JSON.toJSONString(obj, mapping, (SerializerFeature[])features.toArray(new SerializerFeature[features.size()]));
        return json;
    }

    public Object deserialize(String text, Class<?> cls)
    {
        if (DybUtils.isEmptyOrNull(text))
            return null;
        return JSON.parseObject(text, cls, new Feature[] { Feature.AllowUnQuotedFieldNames, Feature.AllowSingleQuotes, Feature.DisableCircularReferenceDetect });
    }

    public <T> T deserializeT(String text, Class<T> cls)
    {
        if (DybUtils.isEmptyOrNull(text))
            return null;
        return JSON.parseObject(text, cls, new Feature[] { Feature.AllowUnQuotedFieldNames, Feature.AllowSingleQuotes, Feature.DisableCircularReferenceDetect });
    }

    @Override
    public <T> List<T> deserializeListT(String text, Class<T> paramClass) {
        if (DybUtils.isEmptyOrNull(text))
            return null;
        return JSON.parseArray(text,paramClass);
    }
}
