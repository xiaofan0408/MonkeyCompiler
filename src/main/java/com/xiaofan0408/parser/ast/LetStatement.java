package com.xiaofan0408.parser.ast;

import com.xiaofan0408.token.Token;
import lombok.*;

/**
 * @author xuzefan  2019/8/10 15:08
 */

@Getter
@Setter
@Builder
public class LetStatement implements Statement{

    private Token token;

    private Identifer name;

    private Expression value;


    @Override
    public Statement statmentNode() {
        return null;
    }

    @Override
    public String tokenLiteral() {
        return null;
    }
}
