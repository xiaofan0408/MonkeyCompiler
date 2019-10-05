package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;

public class Identifer implements Expression{

    private Token token;

    private String value;


    public Identifer(Token token,String value){
        this.token = token;
        this.value = value;
    }

    @Override
    public Expression expressionNode() {
        return this;
    }

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }
}
