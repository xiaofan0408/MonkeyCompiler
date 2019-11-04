package com.xiaofan0408.parser.expression.impl;

import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ParserConstant;
import com.xiaofan0408.parser.ast.BlockStatement;
import com.xiaofan0408.parser.ast.Expression;
import com.xiaofan0408.parser.ast.IfExpression;
import com.xiaofan0408.parser.expression.PrefixParseFn;
import com.xiaofan0408.token.TokenType;

/**
 * @author xuzefan  2019/11/4 10:16
 */
public class IfExpressionParseFn implements PrefixParseFn {

    private Parser parser;

    public IfExpressionParseFn(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Expression apply() {
        Expression expression = IfExpression.builder().token(parser.getCurToken()).build();

        if (!parser.expectPeek(TokenType.LPAREN)) {
            return null;
        }

        parser.nextToken();
        Expression conditionExpression = parser.parseExpression(ParserConstant.LOWEST);
        ((IfExpression)expression).setCondition(conditionExpression);

        if (!parser.expectPeek(TokenType.RPAREN)) {
            return null;
        }

        if (!parser.expectPeek(TokenType.LBRACE)) {
            return null;
        }

        BlockStatement blockStatement = parser.parseBlockStatement();
        ((IfExpression) expression).setConsequence(blockStatement);

        if (parser.peekTokenIs(TokenType.ELSE)) {
            parser.nextToken();

            if (!parser.expectPeek(TokenType.LBRACE)) {
                return null;
            }
            BlockStatement alternativeBlockStatement = parser.parseBlockStatement();
            ((IfExpression) expression).setAlternative(alternativeBlockStatement);
        }

        return expression;
    }
}
