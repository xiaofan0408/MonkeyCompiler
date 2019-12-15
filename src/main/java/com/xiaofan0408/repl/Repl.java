package com.xiaofan0408.repl;

import com.xiaofan0408.evaluator.Evaluator;
import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.object.MObject;
import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Program;
import com.xiaofan0408.token.Token;
import com.xiaofan0408.token.TokenType;

import java.util.List;
import java.util.Scanner;

/**
 * @author xuzefan  2019/8/10 14:28
 */
public class Repl {

    private String PROMPT = ">> ";

    private String EXIT = "exit";

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Lexer lexer = new Lexer();
       while (true){
            System.out.printf(PROMPT);
            String line = scanner.nextLine();
            if (line.equals(EXIT)) {
                return;
            }
            lexer.New(line);
            Parser parser = new Parser(lexer);
           Program program = parser.parseProgram();
           if (parser.getErrors().size() > 0) {
               printError(parser.getErrors());
               continue;
           }
           Evaluator evaluator = new Evaluator();
           MObject result = evaluator.eval(program);
           if (result != null) {
               System.out.println(result.inspect());
           }
        }
    }

    private void printError(List<String> errorList) {
        errorList.stream().forEach((string) -> {
            System.out.println(string);
        });
    }

}
