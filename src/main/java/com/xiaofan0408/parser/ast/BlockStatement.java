package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xuzefan  2019/11/4 10:07
 */
@Getter
@Setter
@Builder
public class BlockStatement  implements Statement{

    private Token token;

    private List<Statement> statements;


    @Override
    public Statement statmentNode() {
        return this;
    }

    @Override
    public String tokenLiteral() {
        return token.getLiteral();
    }

    @Override
    public String string() {
        StringBuffer stringBuffer = new StringBuffer();
        statements.stream().forEach(statement -> {
            stringBuffer.append(statement.string());
        });
        return stringBuffer.toString();
    }
}
