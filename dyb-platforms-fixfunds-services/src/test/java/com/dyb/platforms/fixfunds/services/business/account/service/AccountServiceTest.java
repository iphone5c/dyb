package com.dyb.platforms.fixfunds.services.business.account.service;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountStatus;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/spring.xml")
public class AccountServiceTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void testGetAccountByCode() throws Exception {
        Account account = new Account();
        account.setPassword("123456");
        account.setAccountName("admin");
        account.setAccountPhone("666666");
        account.setTradePassword("654321");
        account.setAccountType(AccountType.信使);
        account.setAccountForeignKey("1");
        account.setReferrerCode("1");
        account.setAccountStatus(AccountStatus.正常);
        accountService.createAccount(account);
    }
}