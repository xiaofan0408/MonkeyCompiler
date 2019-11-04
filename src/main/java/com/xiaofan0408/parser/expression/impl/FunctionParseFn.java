package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.BlockStatement;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.ast.FunctionLiteral;
import com.xiaofan0408.parser.ast.Identifier;
import com.xiaofan0408.parser.expression.PrefixParseFn;
import com.xiaofan0408.token.Token;
import com.xiaofan0408.token.TokenType;

import java.util.List;

/**
 * @author xuzefan  2019/11/4 13:50
 */
public class FunctionParseFn implements PrefixParseFn{

    private Parser parser;

    public FunctionParseFn(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Expression apply() {
        Expression lit = FunctionLiteral.builder().token(parser.getCurToken()).build();

        if (!parser.expectPeek(TokenType.LPAREN)){
            return null;
        }

        List<Identifier> parameters = parser.parseFunctionParameters();
        ((FunctionLiteral)lit).setParameters(parameters);

        if (!parser.expectPeek(TokenType.LBRACE)){
            return null;
        }


        BlockStatement blockStatement = parser.parseBlockStatement();
        ((FunctionLiteral) lit).setBody(blockStatement);

        return lit;
    }
}
