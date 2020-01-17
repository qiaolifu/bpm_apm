package bpm.controller;

import io.swagger.annotations.Api;
import org.flowable.engine.FormService;
import org.flowable.ui.common.model.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value="流程相关接口")
public class BpmController {

    @Autowired
    private FormService formService;

    @RequestMapping(value = "/rest/account", method = RequestMethod.GET, produces = "application/json")
    public UserRepresentation getAccount() {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId("admin");
        userRepresentation.setEmail("admin@flowable.org");
        userRepresentation.setFullName("Admin");
        userRepresentation.setLastName("Admin");
        userRepresentation.setFirstName("Test");
        List<String> privileges = new ArrayList<>();
        privileges.add("access-idm");
        privileges.add("access-rest-api");
        privileges.add("access-task");
        privileges.add("access-modeler");
        privileges.add("access-admin");
        userRepresentation.setPrivileges(privileges);
        return userRepresentation;
    }


    /**
     * 对象转map
     * @param obj   对象
     * @return  对象属性键值对
     */
        private Map<String, Object> objectToMap(Object obj){
            if(obj == null){
                return null;
            }

            Map<String, Object> map = null;
            try {
                map = new HashMap<String, Object>();

                Field[] declaredFields = obj.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return map;
    }
}