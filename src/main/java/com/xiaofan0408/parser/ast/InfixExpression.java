package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InfixExpression implements Expression{

    private Token token;

    private Expression left;

    private String operator;

    private Expression right;

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
        StringBuffer out = new StringBuffer();
        out.append("(");
        out.append(left.string());
        out.append(" " + operator + " ");
        out.append(right.string());
        out.append(")");
        return out.toString();
    }
}
