package com.xiaofan0408.parser.ast;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xuzefan  2019/8/10 15:03
 */
@Setter
@Getter
public class Program implements Node{
    private List<Statement> statements;

    @Override
    public String tokenLiteral() {
        if (statements.size() > 0) {
            return statements.get(0).tokenLiteral();
        } else {
            return "";
        }
    }
}
