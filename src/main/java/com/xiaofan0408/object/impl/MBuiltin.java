package com.xiaofan0408.object.impl;

import com.xiaofan0408.object.builtin.BuiltinFunction;
import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;
import lombok.Getter;

@Getter
public class MBuiltin implements MObject {

    private BuiltinFunction fn;

    public MBuiltin(BuiltinFunction fn) {
        this.fn = fn;
    }

    @Override
    public MObjectType type() {
        return MObjectType.BUILTIN_OBJ;
    }

    @Override
    public String inspect() {
        return "builtin function";
    }
}
