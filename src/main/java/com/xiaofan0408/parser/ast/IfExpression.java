package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xuzefan  2019/11/4 10:10
 */

@Builder
@Getter
@Setter
public class IfExpression implements Expression{

    private Token token;

    private Expression condition;

    private BlockStatement consequence;

    private BlockStatement alternative;

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
        stringBuffer.append("if");
        stringBuffer.append(condition.string());
        stringBuffer.append(" ");
        stringBuffer.append(consequence.string());
        if (alternative != null) {
            stringBuffer.append("else");
            stringBuffer.append(alternative.string());
        }
        return stringBuffer.toString();
    }
}
