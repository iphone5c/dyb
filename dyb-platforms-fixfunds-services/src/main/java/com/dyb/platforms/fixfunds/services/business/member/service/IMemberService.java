package com.dyb.platforms.fixfunds.services.business.member.service;

import com.dyb.platforms.fixfunds.services.business.member.entity.Member;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IMemberService {

    /**
     * 根据信使code查找信使信息
     * @param memberCode 信使code
     * @return 信使信息
     */
    public Member getMemberByCode(String memberCode);

}
