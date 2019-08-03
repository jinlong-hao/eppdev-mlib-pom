/* FileName: LogController.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License V1.0
 */

package cn.eppdev.mlib.register.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jinlong.hao
 */
@Controller
@RequestMapping("/admin/log")
public class LogController {
    static Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/provider/{pageNum}")
    public String providerLog(@PathVariable("pageNum") Integer pageNum,
                              @RequestParam("sortBy") String sortBy){
        return null;
    }


    @RequestMapping("/model/{pageNum}")
    public String modelLog(@PathVariable("pageNum") Integer pageNum,
                           @RequestParam("sortBy") String sortBy) {

        return null;
    }
}
