/* FileName: IndexController.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By Anti-996 License v1.0
 */

package cn.eppdev.mlib.register.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jinlong.hao
 */
@Controller
public class IndexController {
    static Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping("/")
    public String index(){
        return "redirect:/admin/client/list";
    }
}
