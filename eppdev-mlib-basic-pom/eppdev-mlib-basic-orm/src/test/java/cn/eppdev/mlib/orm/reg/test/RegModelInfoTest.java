/* FileName: RegModelInfoTest.java
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
import cn.eppdev.mlib.orm.reg.param.RegModelInfoParam;
import cn.eppdev.mlib.orm.reg.service.RegModelInfoService;

@SpringBootTest(classes = {ApplicationTest.class})
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application_test.properties")
 public class RegModelInfoTest{

    @Autowired
    RegModelInfoService  entityService;

    @Test
    public void  testBasic(){
        RegModelInfoParam param = new RegModelInfoParam();
        param.set_pageNum(1);
        param.set_pageSize(1);
        Assert.assertNotNull(entityService.list(param));
    }

 }
