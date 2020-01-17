package bpm.service;

import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface HistoryService {

    public HistoricProcessInstance getHistoricProcessInstance(String processInstanceId);

    public List<HistoricTaskInstance> getHistoricTaskInstance(String taskId);
}
