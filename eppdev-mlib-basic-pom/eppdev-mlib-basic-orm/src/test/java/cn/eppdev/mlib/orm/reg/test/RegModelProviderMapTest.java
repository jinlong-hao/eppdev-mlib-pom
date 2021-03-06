/* FileName: RegModelProviderMapTest.java
 * Copyright EPPDEV.CN, All Rights Preserved!
 * License: Anti-996 License 1.0
 * Auto created by eppdev-jee(http://jee.eppdev.cn)!
 */


 package cn.eppdev.mlib.orm.reg.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import cn.eppdev.mlib.orm.ApplicationTest;
import cn.eppdev.mlib.orm.reg.param.RegModelProviderMapParam;
import cn.eppdev.mlib.orm.reg.service.RegModelProviderMapService;

@SpringBootTest(classes = {ApplicationTest.class})
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application_test.properties")
 public class RegModelProviderMapTest{

    @Autowired
    RegModelProviderMapService  entityService;

    @Test
    public void  testBasic(){
        RegModelProviderMapParam param = new RegModelProviderMapParam();
        param.set_pageNum(1);
        param.set_pageSize(1);
        Assert.assertNotNull(entityService.list(param));
    }

 }
