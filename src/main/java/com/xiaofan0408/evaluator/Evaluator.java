package com.xiaofan0408.evaluator;

import com.xiaofan0408.object.Environment;
import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;
import com.xiaofan0408.object.impl.*;
import com.xiaofan0408.parser.ast.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator {

    public MObject eval(Node node,Environment env) {
        if (node instanceof IntegerLiteral) {
            return new  MInteger(((IntegerLiteral) node).getValue());
        } else if (node instanceof Program) {
            return evalProgram((Program) node,env);
        } else if (node instanceof ExpressionStatement) {
            return eval(((ExpressionStatement) node).getExpression(),env);
        } else if (node instanceof BooleanLiteral) {
            return MBoolean.valueOf(((BooleanLiteral) node).isValue());
        } else if (node instanceof PrefixExpression) {
            MObject right = eval(((PrefixExpression) node).getRight(),env);
            if(isError(right)){
                return right;
            }
            return evalPrefixExpression(((PrefixExpression) node).getOperator(),right);
        } else if (node instanceof InfixExpression){
            MObject left = eval(((InfixExpression) node).getLeft(),env);
            if (isError(left)) {
                return left;
            }
            MObject right = eval(((InfixExpression) node).getRight(),env);
            if (isError(right)) {
                return right;
            }
            return evalInfixExpression(((InfixExpression) node).getOperator(),left,right);
        } else if (node instanceof BlockStatement) {
            return evalBlockStatement((BlockStatement)node,env);
        } else if (node instanceof IfExpression) {
            return evalIfExpression(node,env);
        } else if (node instanceof ReturnStatement) {
            MObject value = eval(((ReturnStatement) node).getReturnValue(),env);
            if (isError(value)) {
                return value;
            }
            return new MReturnValue(value);
        } else if (node instanceof LetStatement) {
            MObject value = eval(((LetStatement) node).getValue(),env);
            if (isError(value)) {
                return value;
            }
            env.set(((LetStatement) node).getName().getValue(),value);
        } else if (node instanceof Identifier) {
            return evalIdentifer(node,env);
        } else if (node instanceof FunctionLiteral) {
            List<Identifier> params = ((FunctionLiteral) node).getParameters();
            BlockStatement body = ((FunctionLiteral) node).getBody();
            return new MFunction(params,body,env);
        }
        return null;
    }

    private MObject evalIdentifer(Node node, Environment env) {
        MObject value = env.get(((Identifier)node).getValue());
        if (value==null) {
            return newError("identifier not found: " +((Identifier)node).getValue());
        }
        return value;
    }

    private MObject evalBlockStatement(BlockStatement blockStatement,Environment env) {
        MObject result = null;
        for (Statement statement: blockStatement.getStatements()){
            result = eval(statement,env);
            if (result != null && (result.type() == MObjectType.RETURN_VALUE_OBJ
                    || result.type() == MObjectType.ERROR_OBJ)) {
                return result;
            }
        }
        return result;
    }

    private MObject evalIfExpression(Node node,Environment env) {
        MObject condition = eval(((IfExpression)node).getCondition(),env);
        if (isError(condition)){
            return condition;
        }
        if (isTruthy(condition)) {
            return eval(((IfExpression)node).getConsequence(),env);
        } else if (((IfExpression)node).getAlternative() != null) {
            return eval(((IfExpression)node).getAlternative(),env);
        } else {
            return null;
        }
    }

    private boolean isTruthy(MObject condition) {
        if (MNull.NULL == condition) {
            return  false;
        } else if (MBoolean.TRUE == condition) {
            return true;
        }else if (MBoolean.FALSE == condition) {
            return false;
        } else {
            return true;
        }
    }

    private MObject evalInfixExpression(String operator, MObject left, MObject right) {
        if (left.type() == MObjectType.INTEGER_OBJ && right.type() == MObjectType.INTEGER_OBJ) {
            return evalIntegerInfixExpression(operator,left,right);
        } else if (operator.equals("==")){
            return MBoolean.valueOf(left == right);
        } else if(operator.equals("!=")) {
            return MBoolean.valueOf(left != right);
        }else if (left.type() != right.type()) {
            return newError("type mismatch: %s %s %s",
                    left.type(), operator, right.type());
        } else{
            return newError("unknown operator: %s %s %s",
                    left.type(), operator, right.type());
        }
    }

    private MObject evalIntegerInfixExpression(String operator, MObject left, MObject right) {
        BigDecimal leftVal = ((MInteger)left).getValue();
        BigDecimal rightVal = ((MInteger)right).getValue();

        switch (operator){
            case "+":{
                return new MInteger(leftVal.add(rightVal));
            }
            case "-":{
                return new MInteger(leftVal.subtract(rightVal));
            }
            case "*":{
                return new MInteger(leftVal.multiply(rightVal));
            }
            case "/":{
                return new MInteger(leftVal.divide(rightVal));
            }
            case "<":{
                return MBoolean.valueOf(leftVal.compareTo(rightVal) == -1);
            }
            case ">":{
                return MBoolean.valueOf(leftVal.compareTo(rightVal) == 1);
            }
            case "==":{
                return MBoolean.valueOf(leftVal.compareTo(rightVal) == 0);
            }
            case "!=":{
                return MBoolean.valueOf(leftVal.compareTo(rightVal)!= 0);
            }

            default:{
                return newError("unknown operator: %s %s %s",
                        left.type(), operator, right.type());
            }
        }
    }

    private MObject evalProgram(Program program,Environment env) {
        MObject result = null;
        for (Statement statement: program.getStatements()){
            result = eval(statement,env);
            if (result instanceof MReturnValue) {
                return ((MReturnValue) result).getValue();
            } else if (result instanceof MError) {
                return result;
            }
        }
        return result;
    }

    private MObject evalPrefixExpression(String operator,MObject right){
        switch (operator){
            case "!":{
                return evalBangOperatorExpression(right);
            }
            case "-":{
                return evalMinusPrefixOperatorExpression(right);
            }
            default:{
                return newError("unknown operator: %s%s", operator, right.type());
            }
        }
    }

    private MObject evalMinusPrefixOperatorExpression(MObject right) {
        if (right.type() != MObjectType.INTEGER_OBJ) {
            return newError("unknown operator: -%s", right.type());
        }
        BigDecimal value = ((MInteger)right).getValue();
        return new MInteger(value.negate());
    }

    private MObject evalBangOperatorExpression(MObject right) {
        if (right == MBoolean.TRUE) {
            return MBoolean.FALSE;
        } else if (right == MBoolean.FALSE){
            return MBoolean.TRUE;
        } else if (right == MNull.NULL) {
            return MBoolean.TRUE;
        } else {
            return MBoolean.FALSE;
        }
    }

    private MError newError(String format,Object... a) {
        return new MError(
                String.format(format,a)
        );
    }

    private boolean isError(MObject object) {
        if (object != null) {
            return object.type() == MObjectType.ERROR_OBJ;
        }
        return false;
    }
}
