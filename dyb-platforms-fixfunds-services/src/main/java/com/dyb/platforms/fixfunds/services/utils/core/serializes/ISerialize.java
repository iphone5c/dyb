package com.dyb.platforms.fixfunds.services.utils.core.serializes;

import java.util.List;

/**
 * Created by Administrator on 2016/3/2.
 */
public interface ISerialize {
    public abstract String serialize(Object paramObject);

    public abstract Object deserialize(String paramString, Class<?> paramClass);

    public abstract <T> T deserializeT(String paramString, Class<T> paramClass);

    public abstract <T> List<T> deserializeListT(String paramString, Class<T> paramClass);
}
