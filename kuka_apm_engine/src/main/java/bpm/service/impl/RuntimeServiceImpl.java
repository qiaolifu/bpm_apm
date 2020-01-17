package bpm.service.impl;


import bpm.service.RuntimeService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RuntimeServiceImpl implements RuntimeService {

    @Autowired
    private org.flowable.engine.RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;



    @Override
    public String startProcessInstance(String processKey) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey);
        return processInstance.getId();
    }

    @Override
    public String startProcessInstanceByDefinitionId(String definitionId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(definitionId);
        return processInstance.getId();
    }

    @Override
    public String startProcessWithForm(String deploymentId) {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();

        String outcome = processDefinition.getName();
        Map<String, Object> formProperties = new HashMap<>();
        String processInstanceName = processDefinition.getName();

        ProcessInstance instance = 	runtimeService.startProcessInstanceWithForm(processDefinition.getId(), outcome, formProperties, processInstanceName);
        System.out.println("processDefinitionId：" + processDefinition.getId());
        return "processInstanceId：" + instance.getId() + ",      processDefinitionId：" + processDefinition.getId();
    }

    @Override
    public String deleteProcessInstance(String processInstanceId) {

        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        //判断该流程实例是否结束，未结束和结束两者删除表的信息是不一样的。
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()
                .processInstanceId(hpi.getId())// 使用流程实例ID查询
                .singleResult();
        if(pi==null){
            //实例已经结束
            historyService.deleteHistoricProcessInstance(hpi.getId());
        }else {
            //实例未结束的

            runtimeService.deleteProcessInstance(hpi.getId(), "");
            historyService.deleteHistoricProcessInstance(hpi.getId());//(顺序不能换)
        }
        return "ok";
    }
}
