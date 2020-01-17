package bpm.controller;

import bpm.service.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value="/bpm/history")
public class HistoryController {

  @Autowired
  private HistoryService historyService;


    /**
     * 获取流程历史（已结束的流程）
     * @param processInstanceId 流程实例id
     * @return 流程历史对象
     */
    @RequestMapping(value="/processInstance", method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HistoricProcessInstance getHistoricProcessInstance(@RequestParam String processInstanceId) {
        try {
            return historyService.getHistoricProcessInstance(processInstanceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取流程节点历史（未结束的流程）
     * @param taskId 节点id
     * @return 之前历史节点对象
     */
    @RequestMapping(value="/taskInstance", method= RequestMethod.POST)
    @ResponseBody
    public List<HistoricTaskInstance> getHistoricTaskInstance(String taskId) {
        try {
            return historyService.getHistoricTaskInstance(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
