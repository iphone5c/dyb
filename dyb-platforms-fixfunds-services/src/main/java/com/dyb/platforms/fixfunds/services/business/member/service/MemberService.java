package com.dyb.platforms.fixfunds.services.business.member.service;

import com.dyb.platforms.fixfunds.services.business.member.dao.IMemberDao;
import com.dyb.platforms.fixfunds.services.business.member.entity.Member;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("memberService")
public class MemberService extends BaseService implements IMemberService {

    public Logger log = Logger.getLogger(MemberService.class);//日志

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

    /**
     * 添加信使详情新
     * @param member 信使对象
     * @return 信使对象
     */
    @Override
    public Member createMember(Member member) {
        if (member==null)
            throw new DybRuntimeException("信使添加时，member对象不能为空或null");
        if (DybUtils.isEmptyOrNull(member.getRealName()))
            throw new DybRuntimeException("信使添加时，真实姓名不能为空");
        if (member.getSex()==null)
            throw new DybRuntimeException("信使添加时，性别不能为空");
        if (member.getBirthday()==null)
            throw new DybRuntimeException("信使添加时，出生日期不能为空");
        if (DybUtils.isEmptyOrNull(member.getNativePlace()))
            throw new DybRuntimeException("信使添加时，籍贯不能为空");
        if (DybUtils.isEmptyOrNull(member.getProvince()))
            throw new DybRuntimeException("信使添加时，省级代码不能为空");
        if (DybUtils.isEmptyOrNull(member.getCity()))
            throw new DybRuntimeException("信使添加时，市级代码不能为空");
        if (member.getCertificate()==null)
            throw new DybRuntimeException("信使添加时，证件类型不能为空");
        if (DybUtils.isEmptyOrNull(member.getCertificateNumber()))
            throw new DybRuntimeException("信使添加时，证件号码不能为空");
        if (member.getIndustry()==null)
            throw new DybRuntimeException("信使添加时，所属行业不能为空");
        if (DybUtils.isEmptyOrNull(member.getMemberEmail()))
            throw new DybRuntimeException("信使添加时，邮箱地址不能为空");
        if (DybUtils.isEmptyOrNull(member.getAccountCode()))
            throw new DybRuntimeException("信使添加时，信使所属账户不能为空");
        member.setMemberCode(UUID.randomUUID().toString());
        int info = memberDao.insertObject(member);
        return info>0?member:null;
    }
}
