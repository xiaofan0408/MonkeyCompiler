package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzefan  2019/11/4 10:48
 */

@Getter
@Setter
@Builder
public class FunctionLiteral implements Expression{

    private Token token;

    private List<Identifier> parameters;

    private BlockStatement body;

    @Override
    public Expression expressionNode() {
        return this;
    }

    @Override
    public String tokenLiteral() {
        return token.getLiteral();
    }

    @Override
    public String string() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(tokenLiteral());
        stringBuffer.append("(");
        stringBuffer.append(String.join(",",parameters.stream().map(identifier -> identifier.string()).collect(Collectors.toList())));
        stringBuffer.append(")");
        stringBuffer.append(body.string());
        return stringBuffer.toString();
    }
}
