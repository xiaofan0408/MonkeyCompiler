package com.xiaofan0408.object.impl;

import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;

public class MBoolean implements MObject {

    public static MObject TRUE = new MBoolean(true);

    public static MObject FALSE = new MBoolean(false);

    private boolean value;

    public MBoolean(boolean value){
        this.value = value;
    }

    @Override
    public MObjectType type() {
        return MObjectType.BOOLEAN_OBJ;
    }

    @Override
    public String inspect() {
        return Boolean.toString(value);
    }

    public static MObject valueOf(boolean input) {
        if (input) {
            return TRUE;
        }
        return FALSE;
    }
}
