package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.CallExpression;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.expression.InfixParseFn;

import java.util.List;

/**
 * @author xuzefan  2019/11/4 14:11
 */
public class CallParseFn implements InfixParseFn{

    private Parser parser;

    public CallParseFn(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Expression apply(Expression expression) {
        Expression ret = CallExpression.builder().token(parser.getCurToken()).function(expression).build();
        List<Expression> expressions = parser.parseCallArguments();
        ((CallExpression)ret).setArguments(expressions);
        return ret;
    }
}
