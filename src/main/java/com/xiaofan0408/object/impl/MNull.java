package com.xiaofan0408.object.impl;

import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;

public class MNull implements MObject {

    public static MNull NULL = new MNull();

    @Override
    public MObjectType type() {
        return MObjectType.NULL_OBJ;
    }

    @Override
    public String inspect() {
        return "null";
    }
}
