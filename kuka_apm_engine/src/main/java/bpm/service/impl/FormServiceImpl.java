package bpm.service.impl;


import bpm.model.formElement.Field;
import bpm.model.formElement.Structure;
import bpm.service.FormService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FormServiceImpl implements FormService {


    @Override
    public Structure convertToFormModel(Map<String,Object> formData){
        Structure structure = new Structure();
        List<Field> fields = new ArrayList<>();


        structure.setName((String) formData.get("name"));
        structure.setKey((String) formData.get("key"));
        structure.setVersion((String) formData.get("version"));
       List<Map<String,Object>> mapList = (List<Map<String, Object>>) formData.get("list");

       for (Map map : mapList){
           Field field = new Field();
           field.setFieldType("FormField");

           field.setId((String) map.get("model"));
           field.setName((String) map.get("name"));
           field.setType((String) map.get("type"));
           field.setValue((String) map.get("defaultValue"));
           field.setRequired((Boolean) map.get("required"));
//           field.setReadOnly((Boolean) map.get("readOnly"));
           field.setPlaceholder((String) map.get("placeholder"));

            fields.add(field);
       }
       structure.setFields(fields);
        return structure;
    }
}
