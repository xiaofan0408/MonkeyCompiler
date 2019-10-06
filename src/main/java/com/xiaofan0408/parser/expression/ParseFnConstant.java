package com.xiaofan0408.parser.expression;

import com.xiaofan0408.token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class ParseFnConstant {

    public final static Map<TokenType,PrefixParseFn> prefixParseFns = new HashMap<>();

    public final static Map<TokenType,InfixParseFn> infixParseFns = new HashMap<>();

    public static void registerPrefix(TokenType tokenType,PrefixParseFn fn){
        prefixParseFns.put(tokenType,fn);
    }

    public static void registerInfix(TokenType tokenType,InfixParseFn fn) {
        infixParseFns.put(tokenType,fn);
    }

}
