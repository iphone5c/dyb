package com.dyb.platforms.fixfunds.services.business.serianum.service;

import com.dyb.platforms.fixfunds.services.business.serianum.dao.ISeriaNumDao;
import com.dyb.platforms.fixfunds.services.business.serianum.entity.SeriaNum;
import com.dyb.platforms.fixfunds.services.utils.DybConvert;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
@Transactional
@Service("seriaNumService")
public class SeriaNumService extends BaseService implements ISeriaNumService {

    private static final ReentrantLock lock = new ReentrantLock();

    @Autowired
    private ISeriaNumDao seriaNumDao;


    /**
     * 根据指定的键获取一个新的序列号，序列从1开始
     *
     * @param keyName 序列号发生器的键
     * @return 序列号
     */
    @Override
    public int getNewSerialNumByInt(String keyName) {
        if (DybUtils.isEmptyOrNull(keyName))
            throw new IllegalArgumentException("keyName参数不能为null或empty.");
        lock.lock();
        try {
            SeriaNum data = seriaNumDao.getObject(keyName, true);
            int result = 1;
            if (data == null) {
                data = new SeriaNum();
                data.setKeyName(keyName);
                data.setKeyValue(1);
                seriaNumDao.insertObject(data);
            } else {
                result = data.getKeyValue() + 1;
                data.setKeyValue(result);
                seriaNumDao.updateObject(data);
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据指定的键获取一个字符串表示的新序列号，序列从1开始
     *
     * @param keyName 序列号发生器的键
     * @param len     返回符串序列号的长度，实际序列右对齐前面补0，如果指定的长度小于序列的实际位数，将抛出异常.
     * @return 序列号
     */
    @Override
    public String getNewSerialNumByString(String keyName, int len) {
        if (DybUtils.isEmptyOrNull(keyName))
            throw new IllegalArgumentException("keyName参数不能为null或empty.");
        if (len <= 0)
            throw new IllegalArgumentException("len参数必须大于0.");
        int ret = getNewSerialNumByInt(keyName);
        String result = DybConvert.intToString(ret);
        if (result.length() > len)
            throw new IllegalArgumentException("序列号长度大于指定的返回长度.");
        while (result.length() < len)
            result = "0" + result;
        return result;
    }

    /**
     * 清除指定的序列发生器，清除后序列将从1开始重新计算。
     *
     * @param keyName 序列号发生器的键
     */
    @Override
    public void clearSerial(String keyName) {
        if (DybUtils.isEmptyOrNull(keyName))
            throw new IllegalArgumentException("keyName参数不能为null或empty.");
        lock.lock();
        try {
            seriaNumDao.deleteObject(keyName);
        } finally {
            lock.unlock();
        }
    }

}
