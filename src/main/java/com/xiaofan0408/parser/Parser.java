package com.xiaofan0408.parser;

import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.parser.ast.*;
import com.xiaofan0408.token.Token;
import com.xiaofan0408.token.TokenType;


import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Lexer lexer;

    private Token curToken;

    private Token peekToken;

    private List<String> errors;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.errors = new ArrayList<>();
        this.nextToken();
        this.nextToken();
    }

    public List<String> getErrors(){
        return this.errors;
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
                return null;
        }
    }

    private Statement parseReturnStatement() {
        Statement statement = ReturnStatement.builder().token(this.curToken).build();
        this.nextToken();
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

        while (!this.curTokenIs(TokenType.SEMICOLON)) {
            this.nextToken();
        }
        return statement;
    }

    private boolean curTokenIs(TokenType tokenType) {
        return this.curToken.getType() == tokenType;
    }

    private boolean expectPeek(TokenType ident) {
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

}
