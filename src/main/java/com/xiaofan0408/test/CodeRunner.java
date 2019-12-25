package com.xiaofan0408.test;


import com.xiaofan0408.evaluator.Evaluator;
import com.xiaofan0408.lexer.Lexer;
import com.xiaofan0408.object.Environment;
import com.xiaofan0408.object.MObject;
import com.xiaofan0408.parser.Parser;
import com.xiaofan0408.parser.ast.Program;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.List;

public class CodeRunner {
	
	public static void main(String[] args) throws IOException {
		CodeDialog codeDialog = new CodeDialog();
		while (true){
			String code = codeDialog.showDialog();
			if (code.equals("exit")) {
				break;
			}
			if (code != null) {
				Environment env = new Environment(new HashMap<>());
				Lexer lexer = new Lexer();
				lexer.New(code);
				Parser parser = new Parser(lexer);
				Program program = parser.parseProgram();
				if (parser.getErrors().size() > 0) {
					printError(parser.getErrors());
				}
				Evaluator evaluator = new Evaluator();
				MObject result = evaluator.eval(program,env);
				if (result != null) {
					System.out.println(result.inspect());
				}
			}
		}


	}

	private static void printError(List<String> errorList) {
		errorList.stream().forEach((string) -> {
			System.out.println(string);
		});
	}
}
