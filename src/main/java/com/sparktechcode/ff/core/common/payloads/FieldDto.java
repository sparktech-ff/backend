package com.sparktechcode.ff.core.common.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class FieldDto extends HashMap<String, Object> {

    public static FieldDto of(String key, Object value) {
        var field = new FieldDto();
        field.put(key, value);
        return field;
    }
}
