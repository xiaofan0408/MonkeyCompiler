package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ParserConstant;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.ast.PrefixExpression;
import com.xiaofan0408.parser.expression.PrefixParseFn;

public class PrefixExpressionParseFn implements PrefixParseFn {

    private Parser parser;

    public PrefixExpressionParseFn(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Expression apply() {
        Expression expression = PrefixExpression.builder()
                .token(parser.getCurToken())
                .operator(parser.getCurToken().getLiteral())
                .build();
        parser.nextToken();
        ((PrefixExpression) expression).setRight(parser.parseExpression(ParserConstant.PREFIX));
        return expression;
    }
}
