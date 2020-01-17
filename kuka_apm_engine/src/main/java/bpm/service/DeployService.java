package bpm.service;

import org.flowable.engine.repository.Deployment;
import org.springframework.stereotype.Service;

public interface DeployService {
    public Deployment deployProcess(String processId);

    public String deployForm(String deploymentId, String formKey);
}
