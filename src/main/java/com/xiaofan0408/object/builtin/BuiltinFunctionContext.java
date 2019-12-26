package com.xiaofan0408.object.builtin;

import com.xiaofan0408.object.builtin.impl.LenFunction;
import com.xiaofan0408.object.impl.MBuiltin;

import java.util.HashMap;
import java.util.Map;

public class BuiltinFunctionContext {

    private static Map<String,MBuiltin> builtinFunctionMap = new HashMap<>();

    static {
        builtinFunctionMap.put("len", new MBuiltin(new LenFunction()));
    }
    public static MBuiltin get(String name) {
        return builtinFunctionMap.get(name);
    }
}
