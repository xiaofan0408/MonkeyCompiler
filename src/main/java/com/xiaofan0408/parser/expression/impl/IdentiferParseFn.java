package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.ast.Identifier;
import com.xiaofan0408.parser.expression.PrefixParseFn;

public class IdentiferParseFn implements PrefixParseFn {

     private Parser parser;

     public IdentiferParseFn(Parser parser){
         this.parser = parser;
     }

    @Override
    public Expression apply() {
        return new Identifier(parser.getCurToken(),parser.getCurToken().getLiteral());
    }
}
