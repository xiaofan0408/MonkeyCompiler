package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Override
    public String string() {
        return this.value;
    }
}
