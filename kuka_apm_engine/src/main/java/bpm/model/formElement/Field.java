package bpm.model.formElement;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Field {
    String fieldType;                       //数据类型
    String id;                                      //控件的id
    String name;                            //控件的名字
    String type;                                //控件的类型
    String value;
    boolean required;                   //是否必须
    boolean readOnly;                   //是否只可读
    boolean overrideId;                 //是否可重写id
    String placeholder;                 //占位符
    String layout;                              //布局？
    List<Map> attribute;               //特有属性
}
