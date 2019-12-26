package com.xiaofan0408.object.impl;

import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;
import lombok.Getter;

@Getter
public class MString implements MObject {

    private String value;

    public MString(String value) {
        this.value = value;
    }

    @Override
    public MObjectType type() {
        return MObjectType.STRING_OBJ;
    }

    @Override
    public String inspect() {
        return value;
    }
}
