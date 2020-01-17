package bpm.service.impl;



import bpm.service.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private TaskService taskService;

    @Autowired
    private org.flowable.engine.HistoryService historyService;


    @Override
    public HistoricProcessInstance getHistoricProcessInstance(String processInstanceId) {
        org.flowable.engine.HistoryService historyService = processEngine.getHistoryService();
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

    }

    @Override
    public List<HistoricTaskInstance> getHistoricTaskInstance(String taskId) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        List<HistoricTaskInstance> historicTaskInstance = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .list();
        return historicTaskInstance;
    }
}
