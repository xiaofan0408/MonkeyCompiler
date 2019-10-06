package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.ast.IntegerLiteral;
import com.xiaofan0408.parser.expression.PrefixParseFn;

import java.math.BigDecimal;

public class IntegerParseFn implements PrefixParseFn {

    private Parser parser;

    public IntegerParseFn(Parser parser){
        this.parser = parser;
    }

    @Override
    public Expression apply() {
        try {
            Expression expression = IntegerLiteral.builder()
                    .token(this.parser.getCurToken())
                    .value(new BigDecimal(this.parser.getCurToken().getLiteral()))
                    .build();
            return expression;
        }catch (Exception e){
            String msg = String.format("could not parse %q as integer", this.parser.getCurToken().getLiteral());
            this.parser.addError(msg);
        }
        return null;
    }
}
