package com.xiaofan0408.parser;

import com.xiaofan0408.token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class ParserConstant {

    public static final int LOWEST = 1;

    public static final int EQUALS = 2;

    public static final int LESSGREATER = 3;

    public static final int SUM = 4;

    public static final int PRODUCT = 5;

    public static final int PREFIX = 6;

    public static final int CALL = 7;

    public static Map<TokenType,Integer> precedences = new HashMap<>();

    static {
        precedences.put(TokenType.EQ,EQUALS);
        precedences.put(TokenType.NOT_EQ,EQUALS);
        precedences.put(TokenType.LT,LESSGREATER);
        precedences.put(TokenType.GT,LESSGREATER);
        precedences.put(TokenType.PLUS,SUM);
        precedences.put(TokenType.MINUS,SUM);
        precedences.put(TokenType.SLASH, PRODUCT);
        precedences.put(TokenType.ASTERISK,PRODUCT);
        precedences.put(TokenType.LPAREN, CALL);
    }

}
