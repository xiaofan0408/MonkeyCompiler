package com.xiaofan0408.lexer;

import com.xiaofan0408.token.Token;
import com.xiaofan0408.token.TokenConstant;
import com.xiaofan0408.token.TokenType;

/**
 * @author xuzefan  2019/8/10 11:32
 */
public class Lexer {
    private String input;
    private int position;
    private int readPosition;
    private char ch;

    public Lexer() {
    }


    public Lexer(String input) {
        this.input = input;
        this.readChar();
    }

    private void readChar() {
        if (this.readPosition >= this.input.length()) {
            this.ch = 0;
        }else {
            this.ch = this.input.charAt(this.readPosition);
        }
        this.position = this.readPosition;
        this.readPosition++;

    }

    private char peekChar() {
        if (this.readPosition >= this.input.length()) {
            return 0;
        } else {
            return this.input.charAt(this.readPosition);
        }
    }

    public Token nextToken() {
        if (input == null) {
            return new Token(TokenType.EOF,"");
        }
        Token tok = new Token();
        this.skipWhitespace();
        switch (this.ch) {
            case '=':{
                if (peekChar() == '=') {
                    char current = this.ch;
                    readChar();
                    tok = new Token(TokenType.EQ, Character.toString(current)
                            + Character.toString(this.ch));
                } else {
                    tok = new Token(TokenType.ASSIGN, Character.toString(this.ch));
                }
              break;
            }
            case '!':{
                if (peekChar() == '=') {
                    char current = this.ch;
                    readChar();
                    tok = new Token(TokenType.NOT_EQ, Character.toString(current)
                            + Character.toString(this.ch));
                } else {
                    tok = new Token(TokenType.BANG, Character.toString(this.ch));
                }
                break;
            }
            case ';':{
                tok = new Token(TokenType.SEMICOLON, Character.toString(this.ch));
                break;
            }
            case '(':{
                tok = new Token(TokenType.LPAREN, Character.toString(this.ch));
                break;
            }
            case ')':{
                tok = new Token(TokenType.RPAREN, Character.toString(this.ch));
                break;
            }
            case ',':{
                tok = new Token(TokenType.COMMA, Character.toString(this.ch));
                break;
            }
            case '+':{
                tok = new Token(TokenType.PLUS, Character.toString(this.ch));
                break;
            }
            case '{':{
                tok = new Token(TokenType.LBRACE, Character.toString(this.ch));
                break;
            }
            case '}':{
                tok = new Token(TokenType.RBRACE, Character.toString(this.ch));
                break;
            }
            case 0:{
                tok = new Token(TokenType.EOF,"");
                break;
            }
            default:
                if (this.isLetter(this.ch)) {
                    String ident = this.readIdentifier();
                    tok = new Token(TokenConstant.lookUpIdent(ident),ident);
                    return tok;
                } else if (this.isDigit(this.ch)) {
                    String number = this.readNumber();
                    tok = new Token(TokenType.INT, number);
                    return tok;
                }else {
                    tok = new Token(TokenType.ILLEGAL,  Character.toString(this.ch));
                }
        }
        this.readChar();
        return tok;
    }

    private String readNumber() {
        int position = this.position;
        while (isDigit(this.ch)){
            this.readChar();
        }
        return this.input.substring(position,this.position);
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private String readIdentifier() {
        int position = this.position;
        while (isLetter(this.ch)){
            this.readChar();
        }
        return this.input.substring(position,this.position);
    }

    private boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || ch == '_';
    }

    private void skipWhitespace() {
        while (this.ch == ' ' || this.ch == '\t' || this.ch == '\n' || this.ch == '\r') {
            this.readChar();
        }
    }

    public void New(String line) {
        this.input = line;
        this.position = 0;
        this.readPosition = 0;
        this.readChar();
    }
}
