package com.xiaofan0408.object.impl;

import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MInteger implements MObject {

    private BigDecimal value;

    public MInteger(BigDecimal value){
        this.value = value;
    }

    @Override
    public MObjectType type() {
        return MObjectType.INTEGER_OBJ;
}

    @Override
    public String inspect() {
        return value.toString();
    }
}
