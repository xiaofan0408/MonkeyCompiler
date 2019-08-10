package com.xiaofan0408.repl;

import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.token.Token;
import com.xiaofan0408.token.TokenType;

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
            Token token = lexer.nextToken();
            while (token.getType()!= TokenType.EOF){
                System.out.println(token);
                token = lexer.nextToken();
            }
        }
    }

}
