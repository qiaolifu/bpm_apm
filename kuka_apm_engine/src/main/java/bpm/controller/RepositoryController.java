package bpm.controller;


import bpm.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/bpm/repository")
public class RepositoryController {


    @Autowired
    private RepositoryService repositoryService;




    /**
     * 获取流程节点图
     * @param processInstanceId     流程实例id
     * @throws Exception
     */
    @RequestMapping(value = "/processDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processInstanceId) throws Exception {
        try {
            repositoryService.genProcessDiagram(httpServletResponse,processInstanceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
