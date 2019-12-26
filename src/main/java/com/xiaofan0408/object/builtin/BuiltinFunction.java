package com.xiaofan0408.object.builtin;

import com.xiaofan0408.object.MObject;

import java.util.List;

public interface BuiltinFunction{
    MObject apply(List<MObject> args);
}
