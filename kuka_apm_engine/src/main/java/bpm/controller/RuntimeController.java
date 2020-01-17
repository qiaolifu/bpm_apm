package bpm.controller;


import bpm.service.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/bpm/runtime")
public class RuntimeController {

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 开始流程
     * @param processKey 流程的KEY
     * @return  流程的实例id
     */
    @RequestMapping(value="/startProcess", method= RequestMethod.POST)
    @ResponseBody
    public String startProcessInstance(@RequestParam String processKey) {
        try {
            return runtimeService.startProcessInstance(processKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据流程定义ID开始流程
     * @param definitionId 流程定义ID
     * @return 流程的实例id
     */
    @RequestMapping(value="/startProcessByDefinitionId", method= RequestMethod.POST)
    @ResponseBody
    public String startProcessInstanceByDefinitionId(@RequestParam String definitionId) {
        try {
            return runtimeService.startProcessInstanceByDefinitionId(definitionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 开始流程（带表单）
     * @param deploymentId 流程部署id
     * @return 流程实例id
     */
    @RequestMapping(value="/startProcessWithForm", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String startProcessWithForm(@RequestParam String deploymentId) {
        try {
            return runtimeService.startProcessWithForm(deploymentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 删除流程实例
     * @param processInstanceId 流程实例id
     */
    @RequestMapping(value="/deleteProcessInstance", method= RequestMethod.POST)
    @ResponseBody
    public  String deleteProcessInstance(@RequestParam String processInstanceId){
        try {
            return runtimeService.deleteProcessInstance(processInstanceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
