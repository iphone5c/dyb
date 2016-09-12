package com.dyb.platforms.fixfunds.services.business.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/spring.xml")
public class PermissionsServiceTest {

    @Autowired
    private IPermissionsService permissionsService;

    @Test
    public void testGetPermissionsList() throws Exception {
        System.out.println(permissionsService.getPermissions());
    }

}