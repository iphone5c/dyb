package com.dyb.platforms.fixfunds.services.business.user.service;

import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/spring.xml")
public class UserServiceTest {

    @Autowired
    public IUserService userService;

    @Test
    public void testCreateUser() throws Exception {
        User user=new User();
        user.setUserCode("code");
        user.setUserName("name");
        user.setUserPassword("password");
        userService.createUser(user);
    }
}