package com.xiaofan0408;

import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Program;
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
//        Repl repl = new Repl();
//        repl.start();

        String input = "let x = 5;\n" +
                "let y = 10;\n" +
                "let foobar = 838383;\n" +
                "let flag = true;\n" +
                "let f = false;\n" +
                "return false;\n" +
                "true != true;\n" +
                " ( 5 + 5 ) * 2;\n" +
                "let foobar = if (x >y){x}else{y};";
        Lexer lexer = new Lexer();
        lexer.New(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parseProgram();

        if (parser.getErrors().size() > 0 ) {
            parser.getErrors().stream().forEach((string) -> {
                System.out.println(string);
            });
        } else {
            program.getStatements().stream().forEach((statement) ->{
                System.out.println(statement.string());
            });
        }

    }
}
