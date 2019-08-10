package com.xiaofan0408;

import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.repl.Repl;
import com.xiaofan0408.token.Token;
import com.xiaofan0408.token.TokenType;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

//        String code = "let five = 5; let ten = 10;let add = fn(x,y){x+y}; let result= add(five,ten);" +
//                "if (5 < 10 ) { return true; } else { return false; } 10 == 10; 10!=9;";
//        String code2 = "10!=9";
//        Lexer lexer = new Lexer(code);
//        Token token = lexer.nextToken();
//        while (token.getType()!= TokenType.EOF){
//            System.out.println(token);
//            token = lexer.nextToken();
//        }
        Repl repl = new Repl();
        repl.start();
    }
}
