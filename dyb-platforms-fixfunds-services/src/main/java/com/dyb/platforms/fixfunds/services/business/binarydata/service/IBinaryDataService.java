package com.dyb.platforms.fixfunds.services.business.binarydata.service;

/**
 * Created by Administrator on 2016/3/1.
 */
public interface IBinaryDataService {
    public abstract String addBinaryDataFromString(String paramString);

    public abstract String addBinaryDataFromBArray(byte[] paramArrayOfByte);

    public abstract void modifyBinaryDataFromString(String paramString1, String paramString2);

    public abstract void modifyBinaryDataFromBArray(String paramString, byte[] paramArrayOfByte);

    public abstract String getBinaryDataToString(String paramString);

    public abstract byte[] getBinaryDataToBArray(String paramString);

    public abstract void deleteBinaryData(String paramString);
}
