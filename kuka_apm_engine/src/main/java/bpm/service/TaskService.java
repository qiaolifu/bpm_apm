package bpm.service;

import org.flowable.form.api.FormInfo;
import org.flowable.task.api.TaskInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface TaskService {
    public List<TaskInfo> getTasksByAssign(String assignee);

    public String assignTask(String taskId, String name);

    public String completeTask(String taskId);

    public FormInfo completeTaskWithForm(Map<String, Object> taskWithForm);

    public FormInfo getStartFormModel(String taskId);

    public void deleteTaskInstance(String taskId);

    public void deleteProcessDefinition(String processDefinitionId);

    public String backToHistoryTask(String taskId, String taskKey, String historyTaskKey);

}
