package bpm.controller;

import bpm.service.DeployService;
import bpm.service.TaskService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/bpm/deploy")
public class DeployController {


    @Autowired
    private DeployService deployService;
    @Autowired
    private TaskService taskService;


    /**
     * 部署流程
     * @param processId     流程id
     * @return  部署对象
     */
    @RequestMapping(value="/process", method= RequestMethod.POST)
    @ResponseBody
    public Deployment deployProcess(@RequestParam String processId) {
        try {
            return deployService.deployProcess(processId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 部署表单
     * @param deploymentId  所依附流程的部署id
     * @param formKey   表单的key（也是文件名）
     * @return 表单部署id
     */
    @RequestMapping(value="/form", method= RequestMethod.POST)
    @ResponseBody
    public String deployForm(@RequestParam String deploymentId,String formKey) {
        try {
            return deployService.deployForm(deploymentId,formKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除流程定义
     * @param processDefinitionId 流程定义ID
     */
    @RequestMapping(value="/deleteProcessDefinition", method= RequestMethod.POST)
    @ResponseBody
    public void deleteProcessDefinition(@RequestParam String processDefinitionId){
        try {
            taskService.deleteProcessDefinition(processDefinitionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
