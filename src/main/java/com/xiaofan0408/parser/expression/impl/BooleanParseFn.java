package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.BooleanLiteral;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.expression.PrefixParseFn;
import com.xiaofan0408.token.TokenType;

/**
 * @author xuzefan  2019/11/1 17:10
 */
public class BooleanParseFn implements PrefixParseFn{

    private Parser parser;

    public BooleanParseFn(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Expression apply() {
        return BooleanLiteral.builder().token(parser.getCurToken()).value(parser.curTokenIs(TokenType.TRUE)).build();
    }
}
