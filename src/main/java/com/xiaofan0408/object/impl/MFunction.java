package com.xiaofan0408.object.impl;

import com.xiaofan0408.object.Environment;
import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;
import com.xiaofan0408.parser.ast.BlockStatement;
import com.xiaofan0408.parser.ast.Identifier;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzefan  2019/12/24 11:19
 */

@Getter
public class MFunction implements MObject{

    private List<Identifier> parameters;

    private BlockStatement body;

    private Environment environment;

    public MFunction(List<Identifier> parameters, BlockStatement body, Environment environment) {
        this.parameters = parameters;
        this.body = body;
        this.environment = environment;
    }

    @Override
    public MObjectType type() {
        return MObjectType.FUNCTION_OBJ;
    }

    @Override
    public String inspect() {
        StringBuffer  out  = new StringBuffer();
        List<String> params = parameters.stream().map( p -> p.string()).collect(Collectors.toList());
        out.append("fn");
        out.append("(");
        out.append(String.join(",",params));
        out.append(") {\n");
        out.append(body.string());
        out.append("\n}");
        return out.toString();
    }
}
