package com.xiaofan0408.parser.ast;


/**
 * @author xuzefan  2019/8/10 15:03
 */
public class Program implements Node{
    Statement[] statements;

    @Override
    public String tokenLiteral() {
        if (statements.length > 0) {
            return statements[0].tokenLiteral();
        } else {
            return "";
        }
    }
}
