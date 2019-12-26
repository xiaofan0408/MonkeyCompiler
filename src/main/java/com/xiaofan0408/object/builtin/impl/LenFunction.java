package com.xiaofan0408.object.builtin.impl;

import com.xiaofan0408.object.builtin.BuiltinFunction;
import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.impl.MInteger;
import com.xiaofan0408.object.impl.MString;
import com.xiaofan0408.util.CommonUtil;


import java.math.BigDecimal;
import java.util.List;

public class LenFunction implements BuiltinFunction {
    @Override
    public MObject apply(List<MObject> args) {
        if (args.size() != 1) {
            return CommonUtil.newError("wrong number of arguments. got=%d, want=1",
                    args.size());
        }
        MObject arg = args.get(0);
        if (arg instanceof MString){
            return new MInteger(new BigDecimal(((MString) arg).getValue().length()));
        } else {
            return CommonUtil.newError("argument to `len` not supported, got %s",
                   arg.type());
        }
    }
}
