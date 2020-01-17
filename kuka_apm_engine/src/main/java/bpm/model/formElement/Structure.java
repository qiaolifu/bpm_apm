package bpm.model.formElement;

import lombok.Data;

import java.util.List;

@Data
public class Structure {
    String name;
    String key;
    String version;
    List<Field> fields;
}
