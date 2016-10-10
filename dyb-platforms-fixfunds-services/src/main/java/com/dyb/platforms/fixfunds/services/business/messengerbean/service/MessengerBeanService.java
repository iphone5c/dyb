package com.dyb.platforms.fixfunds.services.business.messengerbean.service;

import com.dyb.platforms.fixfunds.services.business.messengerbean.dao.IMessengerBeanDao;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("messengerBeanService")
public class MessengerBeanService extends BaseService implements IMessengerBeanService {

    public Logger log = Logger.getLogger(MessengerBeanService.class);//日志

    @Autowired
    private IMessengerBeanDao messengerBeanDao;

}
