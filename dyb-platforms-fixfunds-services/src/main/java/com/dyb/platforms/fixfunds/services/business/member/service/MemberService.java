package com.dyb.platforms.fixfunds.services.business.member.service;

import com.dyb.platforms.fixfunds.services.business.codebuilder.ICodeBuilder;
import com.dyb.platforms.fixfunds.services.business.member.dao.IMemberDao;
import com.dyb.platforms.fixfunds.services.business.member.entity.Member;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("memberService")
public class MemberService extends BaseService implements IMemberService {

    public Logger log = Logger.getLogger(MemberService.class);//日志

    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IMemberDao memberDao;

    /**
     * 根据信使code查找信使信息
     * @param memberCode 信使code
     * @return 信使信息
     */
    @Override
    public Member getMemberByCode(String memberCode) {
        if (DybUtils.isEmptyOrNull(memberCode))
            throw new DybRuntimeException("根据code查找信使信息时，code不能为空");
        return memberDao.getObject(memberCode,true);
    }
}
