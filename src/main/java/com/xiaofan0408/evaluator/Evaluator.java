package com.xiaofan0408.evaluator;

import com.xiaofan0408.object.MObject;
import com.xiaofan0408.object.MObjectType;
import com.xiaofan0408.object.impl.MBoolean;
import com.xiaofan0408.object.impl.MInteger;
import com.xiaofan0408.object.impl.MNull;
import com.xiaofan0408.object.impl.MReturnValue;
import com.xiaofan0408.parser.ast.*;

import java.math.BigDecimal;
import java.util.List;

public class Evaluator {

    public MObject eval(Node node) {
        if (node instanceof IntegerLiteral) {
            return new  MInteger(((IntegerLiteral) node).getValue());
        } else if (node instanceof Program) {
            return evalProgram((Program) node);
        } else if (node instanceof ExpressionStatement) {
            return eval(((ExpressionStatement) node).getExpression());
        } else if (node instanceof BooleanLiteral) {
            return MBoolean.valueOf(((BooleanLiteral) node).isValue());
        } else if (node instanceof PrefixExpression) {
            MObject right = eval(((PrefixExpression) node).getRight());
            return evalPrefixExpression(((PrefixExpression) node).getOperator(),right);
        } else if (node instanceof InfixExpression){
            MObject left = eval(((InfixExpression) node).getLeft());
            MObject right = eval(((InfixExpression) node).getRight());
            return evalInfixExpression(((InfixExpression) node).getOperator(),left,right);
        } else if (node instanceof BlockStatement) {
            return evalBlockStatement((BlockStatement)node);
        } else if (node instanceof IfExpression) {
            return evalIfExpression(node);
        } else if (node instanceof ReturnStatement) {
            MObject value = eval(((ReturnStatement) node).getReturnValue());
            return new MReturnValue(value);
        }
        return null;
    }

    private MObject evalBlockStatement(BlockStatement blockStatement) {
        MObject result = null;
        for (Statement statement: blockStatement.getStatements()){
            result = eval(statement);
            if (result != null && result.type() == MObjectType.RETURN_VALUE_OBJ) {
                return result;
            }
        }
        return result;
    }

    private MObject evalIfExpression(Node node) {
        MObject condition = eval(((IfExpression)node).getCondition());
        if (isTruthy(condition)) {
            return eval(((IfExpression)node).getConsequence());
        } else if (((IfExpression)node).getAlternative() != null) {
            return eval(((IfExpression)node).getAlternative());
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
        }else {
            return MNull.NULL;
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
               return MNull.NULL;
            }
        }
    }

    private MObject evalProgram(Program program) {
        MObject result = null;
        for (Statement statement: program.getStatements()){
            result = eval(statement);
            if (result instanceof MReturnValue) {
                return ((MReturnValue) result).getValue();
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
                return MNull.NULL;
            }
        }
    }

    private MObject evalMinusPrefixOperatorExpression(MObject right) {
        if (right.type() != MObjectType.INTEGER_OBJ) {
            return MNull.NULL;
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
}
