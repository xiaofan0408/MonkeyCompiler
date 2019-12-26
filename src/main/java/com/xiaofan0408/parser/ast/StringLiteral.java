package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StringLiteral implements Expression{

    private Token token;

    private String value;

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
        return token.getLiteral();
    }
}
