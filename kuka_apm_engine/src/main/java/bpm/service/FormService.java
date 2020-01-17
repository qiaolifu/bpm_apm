package bpm.service;

import bpm.model.formElement.Structure;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface FormService {
    public Structure convertToFormModel(Map<String,Object> formData);
}
