package com.dyb.platforms.fixfunds.services.business.codebuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/spring.xml")
public class CodeBuilderTest {

    @Autowired
    private ICodeBuilder codeBuilder;

    @Test
    public void testGetUserCode() throws Exception {
        System.out.println(codeBuilder.getUserCode());
    }
}