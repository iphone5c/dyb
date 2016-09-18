package com.dyb.platforms.fixfunds.services.business.binarydata.service;

import com.dyb.platforms.fixfunds.services.business.binarydata.dao.IBinaryDataDao;
import com.dyb.platforms.fixfunds.services.business.binarydata.entity.BinaryData;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2016/3/1.
 */
@Service
@Transactional
public class BinaryDataService implements IBinaryDataService {

    @Autowired
    private IBinaryDataDao binaryDataDao;

    public String addBinaryDataFromString(String content)
    {
        if (DybUtils.isEmptyOrNull(content))
            throw new DybRuntimeException("content参数不能为null或empty.");
        byte[] buffer = null;
        try {
            buffer = content.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new DybRuntimeException(e);
        }
        return addBinaryDataFromBArray(buffer);
    }

    public String addBinaryDataFromBArray(byte[] content)
    {
        if (content == null)
            throw new DybRuntimeException("content参数不能为null.");
        BinaryData data = new BinaryData();
        data.setKeyId(UUID.randomUUID().toString());
        data.setCreateTime(new Date());
        data.setContent(content);
        this.binaryDataDao.insertObject(data);
        return data.getKeyId();
    }

    public void modifyBinaryDataFromString(String keyId, String content)
    {
        if (DybUtils.isEmptyOrNull(keyId))
            throw new DybRuntimeException("keyId参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(content))
            throw new DybRuntimeException("content参数不能为null或empty.");
        byte[] buffer = null;
        try {
            buffer = content.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new DybRuntimeException(e);
        }
        modifyBinaryDataFromBArray(keyId, buffer);
    }

    public void modifyBinaryDataFromBArray(String keyId, byte[] content)
    {
        if (DybUtils.isEmptyOrNull(keyId))
            throw new IllegalArgumentException("keyId参数不能为null或empty.");
        if (content == null)
            throw new IllegalArgumentException("content参数不能为null.");
        BinaryData data = (BinaryData)this.binaryDataDao.getObject(keyId, true);
        if (data == null)
            throw new DybRuntimeException("没有找到指定的二进制数据对象.");
        data.setContent(content);
        this.binaryDataDao.updateObject(data);
    }

    public String getBinaryDataToString(String keyId)
    {
        if (DybUtils.isEmptyOrNull(keyId))
            throw new IllegalArgumentException("keyId参数不能为null或empty.");
        BinaryData data = (BinaryData)this.binaryDataDao.getObject(keyId, true);
        if (data == null)
            throw new DybRuntimeException("没有找到指定的二进制数据对象.");
        if ((data.getContent() == null) || (data.getContent().length == 0))
            return "";
        String result = "";
        try {
            result = new String(data.getContent(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new DybRuntimeException(e);
        }
        return result;
    }

    public byte[] getBinaryDataToBArray(String keyId)
    {
        if (DybUtils.isEmptyOrNull(keyId))
            throw new IllegalArgumentException("keyId参数不能为null或empty.");
        BinaryData data = (BinaryData)this.binaryDataDao.getObject(keyId, true);
        if (data == null)
            throw new DybRuntimeException("没有找到指定的二进制数据对象.");
        return data.getContent();
    }

    public void deleteBinaryData(String keyId)
    {
        if (DybUtils.isEmptyOrNull(keyId))
            throw new IllegalArgumentException("keyId参数不能为null或empty.");
        this.binaryDataDao.deleteObject(keyId);
    }
}
