package com.xiaofan0408;

import com.xiaofan0408.evaluator.Evaluator;
import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.object.MObject;
import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Program;

public class Test2 {
    public static void main(String[] args) {
        String input = "5";
        Lexer lexer = new Lexer();
        lexer.New(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parseProgram();
        Evaluator evaluator = new Evaluator();
        MObject result = evaluator.eval(program);
        System.out.println(result.inspect());
    }
}
