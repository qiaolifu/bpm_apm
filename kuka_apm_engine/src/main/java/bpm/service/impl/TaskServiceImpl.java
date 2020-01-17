package bpm.service.impl;


import bpm.service.TaskService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.form.api.FormDefinition;
import org.flowable.form.api.FormDeployment;
import org.flowable.form.api.FormInfo;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private org.flowable.engine.TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private FormRepositoryService formRepositoryService;

    @Override
    public List<TaskInfo> getTasksByAssign(String assignee) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        return new ArrayList<TaskInfo>(tasks);
    }

    @Override
    public String assignTask(String taskId, String name) {
        taskService.claim(taskId, name);//领取任务，或者分配任务 设置后不能再调用
        return taskId;
    }

    @Override
    public String completeTask(String taskId) {
        taskService.complete(taskId);
        return taskId;
    }

    @Override
    public FormInfo completeTaskWithForm(Map<String, Object> taskForm) {
        String taskId = (String) taskForm.get("taskId");
        Task task=processEngine.getTaskService().createTaskQuery() // 创建任务查询
                .taskId(taskId) // 根据任务id查询
                .singleResult();

        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(task.getProcessDefinitionId());

        FormDeployment formDeployment = formRepositoryService
                .createDeploymentQuery()
                .parentDeploymentId(processDefinition.getDeploymentId())
                .singleResult();
        FormDefinition formDefinition = formRepositoryService.createFormDefinitionQuery()
                .deploymentId(formDeployment.getId())
                .singleResult();

        Map map= (Map) taskForm.get(formDefinition.getKey());
        String outcome = formDefinition.getName();



        taskService.completeTaskWithForm(taskId,formDefinition.getId(),outcome,map);
        return taskService.getTaskFormModel(taskId);
    }

    @Override
    public FormInfo getStartFormModel(String taskId) {
        return taskService.getTaskFormModel(taskId);
    }

    @Override
    public void deleteTaskInstance(String taskId) {
        taskService.deleteTask(taskId);
    }

    @Override
    public void deleteProcessDefinition(String processDefinitionId) {
        repositoryService.deleteDeployment(processDefinitionId);

    }

    @Override
    public String backToHistoryTask(String taskId, String taskKey, String historyTaskKey) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();

        List<String> taskKeys = new ArrayList<>();
        taskKeys.add(taskKey);

        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(task.getProcessInstanceId())
                .moveActivityIdsToSingleActivityId(taskKeys, historyTaskKey)
                .changeState();
        return "ok";
    }
}
