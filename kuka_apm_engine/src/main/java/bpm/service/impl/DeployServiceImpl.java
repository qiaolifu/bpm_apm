package bpm.service.impl;


import bpm.service.DeployService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.form.api.FormRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeployServiceImpl implements DeployService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormRepositoryService formRepositoryService;

    @Override
    public Deployment deployProcess(String processId) {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/"+processId +".bpmn20.xml")
                .deploy();
        if (deployment != null){
            return deployment;
        }
        return null;
    }

    @Override
    public String deployForm(String deploymentId, String formKey) {
        return formRepositoryService.createDeployment().addClasspathResource("forms/"+formKey +".form")
                .parentDeploymentId(deploymentId)
                .deploy().getId();
    }
}
