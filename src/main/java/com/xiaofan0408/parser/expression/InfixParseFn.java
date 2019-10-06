package com.xiaofan0408.parser.expression;

import com.xiaofan0408.parser.ast.Expression;

@FunctionalInterface
public interface InfixParseFn {
    Expression apply(Expression expression);
}
