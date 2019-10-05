package com.xiaofan0408.parser.ast;


import com.xiaofan0408.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReturnStatement  implements Statement{

    private Token token;

    private Expression returnValue;

    @Override
    public Statement statmentNode() {
        return this;
    }

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }
}
