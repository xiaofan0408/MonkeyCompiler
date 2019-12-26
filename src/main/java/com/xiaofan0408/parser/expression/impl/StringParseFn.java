package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.ast.StringLiteral;
import com.xiaofan0408.parser.expression.PrefixParseFn;

public class StringParseFn implements PrefixParseFn {

    private Parser parser;

    public StringParseFn(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Expression apply() {
        StringLiteral stringLiteral = StringLiteral.builder().token(parser.getCurToken())
                .value(parser.getCurToken().getLiteral()).build();
        return stringLiteral;
    }
}
