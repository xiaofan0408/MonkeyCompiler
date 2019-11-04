package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzefan  2019/11/4 14:06
 */
@Getter
@Setter
@Builder
public class CallExpression implements Expression {

    private Token token;

    private Expression function;

    private List<Expression> arguments;

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
        StringBuffer buffer = new StringBuffer();
        buffer.append(function.string());
        buffer.append("(");
        buffer.append(String.join(",",arguments.stream().map(expression -> expression.string()).collect(Collectors.toList())));
        buffer.append(")");
        return buffer.toString();
    }
}
