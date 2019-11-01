package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ParserConstant;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.expression.PrefixParseFn;
import com.xiaofan0408.token.TokenType;

/**
 * @author xuzefan  2019/11/1 17:29
 */
public class GroupedParseFn implements PrefixParseFn{

    private Parser parser;

    public GroupedParseFn(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Expression apply() {
        parser.nextToken();

        Expression exp = parser.parseExpression(ParserConstant.LOWEST);

        if (!parser.expectPeek(TokenType.RPAREN)) {
            return null;
        }
        return exp;
    }
}
