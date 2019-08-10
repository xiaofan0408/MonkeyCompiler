package com.xiaofan0408.token;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuzefan  2019/8/10 11:20
 */
public class TokenConstant {
    private static Map<String,TokenType> keywords = new HashMap<>();

    static {
        keywords.put("fn",TokenType.FUNCTION);
        keywords.put("let",TokenType.LET);
        keywords.put("true",TokenType.TRUE);
        keywords.put("false",TokenType.FALSE);
        keywords.put("if",TokenType.IF);
        keywords.put("else",TokenType.ELSE);
        keywords.put("return",TokenType.RETURN);
    }

    public static TokenType lookUpIdent(String ident){
        if (keywords.get(ident)!=null) {
            return keywords.get(ident);
        }
        return TokenType.IDENT;
    }

}
