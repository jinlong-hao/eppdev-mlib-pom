/* FileName: ClientController.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License V1.0
 */

package cn.eppdev.mlib.register.web;

import cn.eppdev.mlib.register.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jinlong.hao
 */
@Controller
@RequestMapping("/admin/client")
public class ClientController {
    static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    RegisterService registerService;

    @RequestMapping("/list")
    public String listClients(Model model) {
        model.addAttribute("providers", registerService.listProviders());
        model.addAttribute("consumers", registerService.listConsumers());
        model.addAttribute("monitors", registerService.listMonitor());
        return "client/list";
    }


    @RequestMapping("/provider/{instanceName}")
    public String viewProvider() {
        return null;
    }

}
