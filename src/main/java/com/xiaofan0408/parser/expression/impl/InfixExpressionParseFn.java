package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.ast.InfixExpression;
import com.xiaofan0408.parser.expression.InfixParseFn;

public class InfixExpressionParseFn implements InfixParseFn {

    private Parser parser;

    public InfixExpressionParseFn(Parser parser){
        this.parser = parser;
    }

    @Override
    public Expression apply(Expression left) {
        Expression expression = InfixExpression.builder()
                .token(parser.getCurToken())
                .operator(parser.getCurToken().getLiteral())
                .left(left)
                .build();
        int precedence = parser.curPrecedence();
        parser.nextToken();
        ((InfixExpression) expression).setRight(parser.parseExpression(precedence));
        return expression;
    }
}
