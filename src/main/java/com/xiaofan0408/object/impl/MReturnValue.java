package com.xiaofan0408.object.impl;

import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;
import lombok.Getter;

@Getter
public class MReturnValue implements MObject {

    private MObject value;

    public MReturnValue(MObject value) {
        this.value = value;
    }

    @Override
    public MObjectType type() {
        return MObjectType.RETURN_VALUE_OBJ;
    }

    @Override
    public String inspect() {
        return value.inspect();
    }
}
