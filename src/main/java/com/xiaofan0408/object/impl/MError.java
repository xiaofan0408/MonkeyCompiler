package com.xiaofan0408.object.impl;

import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;


public class MError implements MObject {

    private String message;

    public MError(String message) {
        this.message = message;
    }

    @Override
    public MObjectType type() {
        return MObjectType.ERROR_OBJ;
    }

    @Override
    public String inspect() {
        return "ERROR: " + message;
    }
}
