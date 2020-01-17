package bpm.service;


import org.springframework.stereotype.Service;



public interface RuntimeService {
    public String startProcessInstance(String processKey);

    public String startProcessInstanceByDefinitionId(String definitionId);

    public String startProcessWithForm(String deploymentId);

    public  String deleteProcessInstance(String processInstanceId);



}
