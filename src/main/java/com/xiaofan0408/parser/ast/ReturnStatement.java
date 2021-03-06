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

    @Override
    public String string() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.token.getLiteral()+ " ");
        if (this.returnValue != null) {
            stringBuffer.append(returnValue.string());
        }
        stringBuffer.append(";");
        return stringBuffer.toString();
    }
}
