package bpm.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;


public interface RepositoryService {


    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processInstanceId) throws Exception;
}
