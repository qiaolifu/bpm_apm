package bpm.controller;

import bpm.model.formElement.Structure;
import bpm.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value="/bpm/form")
public class FormController {

    @Autowired
    private FormService formService;


    @RequestMapping(value="/convertToFormModel", method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Structure convertToFormModel(@RequestBody Map<String,Object> formData){
        return formService.convertToFormModel(formData);
    }
}
