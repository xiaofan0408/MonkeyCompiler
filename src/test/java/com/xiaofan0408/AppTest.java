package com.xiaofan0408;

import static org.junit.Assert.assertTrue;

import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Program;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test1(){
        String input = "let x = 5;\n" +
                "let y = 10;\n" +
                "let foobar = 838383;\n";
        Lexer lexer = new Lexer();
        lexer.New(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parseProgram();

        parser.getErrors().stream().forEach((string) -> {
            System.out.println(string);
        });

    }

}
