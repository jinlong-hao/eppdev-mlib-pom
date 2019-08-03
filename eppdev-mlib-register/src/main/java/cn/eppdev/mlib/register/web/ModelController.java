/* FileName: ModelController.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License V1.0
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
@RequestMapping("/admin/model")
public class ModelController {

    static Logger logger = LoggerFactory.getLogger(ModelController.class);

    @RequestMapping("/list/{pageNum}")
    public String listModel(){
        return null;
    }

    @RequestMapping("/deploy/to")
    public String toDeploy(){
        return null;
    }

    @RequestMapping("/deploy/do")
    public String doDeploy() {
        return null;
    }

    @RequestMapping("/{modelId}/update/to")
    public String toUpdate() {
        return null;
    }

    @RequestMapping("/{modelId}/update/do")
    public String doUpdate() {
        return null;
    }

    @RequestMapping("/{modelId}/deploy")
    public String deploy() {
        return null;
    }

    @RequestMapping("/{modelId}/undeploy")
    public String unDeploy() {
        return null;
    }

}
