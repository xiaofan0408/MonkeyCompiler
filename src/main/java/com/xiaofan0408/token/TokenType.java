package com.xiaofan0408.token;

/**
 * @author xuzefan  2019/8/10 11:20
 */
public enum  TokenType {
    ILLEGAL(1,"ILLEGAL"),
    EOF ( 2,"EOF"),

    // Identifiers + literals
    IDENT ( 3,"IDENT" ),// add, foobar, x, y, ...
    INT   (4, "INT"   ),// 1343456

    // Operators
    ASSIGN   ( 5,"=" ),
    PLUS     ( 6,"+" ),
    MINUS    ( 7,"-" ),
    BANG     ( 8,"!" ),
    ASTERISK ( 9,"*" ),
    SLASH    ( 10,"/" ),

    LT (11, "<" ),
    GT ( 12,">" ),

    EQ     (13,"==" ),
    NOT_EQ ( 14,"!=" ),

    // Delimiters
    COMMA     ( 15,"," ),
    SEMICOLON ( 16,";" ),

    LPAREN ( 17,"(" ),
    RPAREN ( 18,")" ),
    LBRACE (19, "{" ),
    RBRACE ( 20,"}" ),

    // Keywords
    FUNCTION ( 21,"FUNCTION" ),
    LET      ( 22,"LET" ),
    TRUE     ( 23,"TRUE" ),
    FALSE    ( 24,"FALSE" ),
    IF       ( 25,"IF" ),
    ELSE     ( 26,"ELSE" ),
    RETURN   ( 27, "RETURN"),
    STRING   (28,"STRING");

    private int code;
    private String value;

    private TokenType(int code,String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
