package com.xiaofan0408.parser;

import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.parser.ast.*;
import com.xiaofan0408.parser.expression.InfixParseFn;
import com.xiaofan0408.parser.expression.ParseFnConstant;
import com.xiaofan0408.parser.expression.PrefixParseFn;
import com.xiaofan0408.parser.expression.impl.*;
import com.xiaofan0408.token.Token;
import com.xiaofan0408.token.TokenType;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Lexer lexer;

    @Getter
    private Token curToken;

    private Token peekToken;

    private List<String> errors;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.errors = new ArrayList<>();
        this.nextToken();
        this.nextToken();

        ParseFnConstant.registerPrefix(TokenType.IDENT, new IdentiferParseFn(this));
        ParseFnConstant.registerPrefix(TokenType.INT, new IntegerParseFn(this));
        ParseFnConstant.registerPrefix(TokenType.BANG, new PrefixExpressionParseFn(this));
        ParseFnConstant.registerPrefix(TokenType.MINUS, new PrefixExpressionParseFn(this));
        ParseFnConstant.registerPrefix(TokenType.TRUE,new BooleanParseFn(this));
        ParseFnConstant.registerPrefix(TokenType.FALSE,new BooleanParseFn(this));
        ParseFnConstant.registerPrefix(TokenType.LPAREN, new GroupedParseFn(this));

        ParseFnConstant.registerInfix(TokenType.PLUS, new InfixExpressionParseFn(this));
        ParseFnConstant.registerInfix(TokenType.MINUS, new InfixExpressionParseFn(this));
        ParseFnConstant.registerInfix(TokenType.SLASH, new InfixExpressionParseFn(this));
        ParseFnConstant.registerInfix(TokenType.ASTERISK, new InfixExpressionParseFn(this));
        ParseFnConstant.registerInfix(TokenType.EQ, new InfixExpressionParseFn(this));
        ParseFnConstant.registerInfix(TokenType.NOT_EQ, new InfixExpressionParseFn(this));
        ParseFnConstant.registerInfix(TokenType.LT, new InfixExpressionParseFn(this));
        ParseFnConstant.registerInfix(TokenType.GT, new InfixExpressionParseFn(this));
    }

    public List<String> getErrors(){
        return this.errors;
    }

    public void addError(String errorMsg) {
        this.errors.add(errorMsg);
    }

    public void nextToken() {
        this.curToken = this.peekToken;
        this.peekToken = this.lexer.nextToken();
    }

    public Program parseProgram() {
        Program program = new Program();
        program.setStatements(new ArrayList<>());

        while (this.curToken.getType() != TokenType.EOF){
            Statement statement = this.parseStatement();
            if (statement != null) {
                program.getStatements().add(statement);
            }
            this.nextToken();
        }
        return program;
    }

    private Statement parseStatement() {
        switch (curToken.getType()){
            case LET:{
                return this.parseLetStatement();
            }
            case RETURN:{
                return this.parseReturnStatement();
            }
            default:
                return this.parseExpressionStatement();
        }
    }

    private Statement parseExpressionStatement() {
        Statement statement = ExpressionStatement.builder().token(this.curToken).build();
        ((ExpressionStatement) statement).setExpression(this.parseExpression(ParserConstant.LOWEST));
        if(peekTokenIs(TokenType.SEMICOLON)){
            nextToken();
        }
        return statement;
    }

    public Expression parseExpression(int precedence) {
        PrefixParseFn parseFn = ParseFnConstant.prefixParseFns.get(curToken.getType());
        if (parseFn == null) {
            noPrefixParseFnError(curToken.getType());
            return null;
        }
        Expression leftExp = parseFn.apply();

        while (!peekTokenIs(TokenType.SEMICOLON) && precedence < peekPrecedence()) {
            InfixParseFn infixParseFn = ParseFnConstant.infixParseFns.get(peekToken.getType());
            if (infixParseFn == null) {
                return leftExp;
            }
            nextToken();
            leftExp =  infixParseFn.apply(leftExp);
        }
        return leftExp;
    }

    private Statement parseReturnStatement() {
        Statement statement = ReturnStatement.builder().token(this.curToken).build();
        this.nextToken();
        ((ReturnStatement)statement).setReturnValue(parseExpression(ParserConstant.LOWEST));
        while (!this.curTokenIs(TokenType.SEMICOLON)) {
            this.nextToken();
        }
        return statement;
    }

    private Statement parseLetStatement() {
        Statement statement = LetStatement.builder().token(this.curToken).build();

        if (!this.expectPeek(TokenType.IDENT)) {
            return null;
        }
        Identifer identifer = new Identifer(this.curToken,this.curToken.getLiteral());
        ((LetStatement) statement).setName(identifer);

        if (!this.expectPeek(TokenType.ASSIGN)) {
            return null;
        }
        nextToken();
        ((LetStatement) statement).setValue(parseExpression(ParserConstant.LOWEST));

        while (!this.curTokenIs(TokenType.SEMICOLON)) {
            this.nextToken();
        }
        return statement;
    }

    public boolean curTokenIs(TokenType tokenType) {
        return this.curToken.getType() == tokenType;
    }

    public boolean expectPeek(TokenType ident) {
        if (this.peekTokenIs(ident)) {
            this.nextToken();
            return true;
        } else {
            this.peekError(ident);
            return false;
        }
    }

    private boolean peekTokenIs(TokenType tokenType) {
        return this.peekToken.getType() == tokenType;
    }

    private void peekError(TokenType tokenType) {
        String msg = String.format("expected next token to be %s, got %s instead", tokenType.toString(),
                this.peekToken.getType().toString());
        this.errors.add(msg);
    }
    private void noPrefixParseFnError(TokenType tokenType){
        String msg = String.format("no prefix parse function for %s found",tokenType.toString());
        errors.add(msg);
    }

    public int peekPrecedence (){
        if (ParserConstant.precedences.get(peekToken.getType())!= null) {
            return ParserConstant.precedences.get(peekToken.getType());
        }
        return ParserConstant.LOWEST;
    }

    public int curPrecedence() {
        if (ParserConstant.precedences.get(curToken.getType())!= null) {
            return ParserConstant.precedences.get(curToken.getType());
        }
        return ParserConstant.LOWEST;
    }
}
