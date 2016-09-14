package com.dyb.platforms.fixfunds.services.utils.core.configureations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/spring.xml")
public class SettingConfigureationFactoryTest {

    @Test
    public void testGetModelConfigList() throws Exception {

    }

    @Test
    public void testGetMenuList() throws Exception {

    }

    @Test
    public void testGetBusinessDefinedList() throws Exception {
        List<Menu> menus= SettingConfigureationFactory.getMenuList();
        System.out.println(menus);
    }

    @Test
    public void testGetBusinessDefinedByBusinessType() throws Exception {
        System.out.println(SettingConfigureationFactory.getAdminLogMap());
    }
}