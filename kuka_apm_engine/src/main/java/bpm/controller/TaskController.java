package bpm.controller;

import bpm.service.TaskService;
import org.flowable.form.api.FormInfo;
import org.flowable.task.api.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/bpm/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    /**
     * 根据用户名获取任务
     * @param assignee  用户名
     * @return  待办任务数据
     */
    @RequestMapping(value="/getTasksByAssign", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TaskInfo> getTasksByAssign(@RequestParam String assignee) {
        try {
            return taskService.getTasksByAssign(assignee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 认领任务
     * @param taskId   任务id
     * @param name  用户名
     * @return 被认领的任务id
     */
    @RequestMapping(value="/assignTask", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String assignTask(@RequestParam String taskId,@RequestParam String name) {
        try {
            return taskService.assignTask(taskId,name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 完成任务
     * @param taskId 任务id
     * @return 任务id
     */
    @RequestMapping(value="/completeTask", method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String completeTask(@RequestParam String taskId) {
        try {
            return taskService.completeTask(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 填写表单完成节点任务
     * @param taskWithForm 表单Json字符串
     */
    @RequestMapping(value="/completeTaskWithForm", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FormInfo completeTaskWithForm(@RequestBody Map<String,Object> taskWithForm) {
        try {
            return taskService.completeTaskWithForm(taskWithForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定节点的表单
     * @param taskId    节点id
     * @return  节点表单信息
     */
    @RequestMapping(value="/getStartFormModel", method= RequestMethod.POST)
    @ResponseBody
    public FormInfo getStartFormModel(@RequestParam String taskId) {
        try {
            return taskService.getStartFormModel(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除任务节点
     * @param taskId    任务节点ID（流程实例必须未启动）
     */
    @RequestMapping(value="/deleteTaskInstance", method= RequestMethod.POST)
    @ResponseBody
    public void deleteTaskInstance(@RequestParam String taskId){
        try {
            taskService.deleteTaskInstance(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 驳回到指定历史节点
     */
    @RequestMapping(value="/backToHistoryTask", method= RequestMethod.POST)
    @ResponseBody
    public String backToHistoryTask(String taskId,String taskKey,String historyTaskKey) {
        try {
            return taskService.backToHistoryTask(taskId,taskKey,historyTaskKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
