package com.xiaofan0408.object;

public enum  MObjectType {

    NULL_OBJ("NULL"),
    ERROR_OBJ ("ERROR"),

    INTEGER_OBJ ("INTEGER"),
    BOOLEAN_OBJ ("BOOLEAN"),

    RETURN_VALUE_OBJ ("RETURN_VALUE"),

    FUNCTION_OBJ ("FUNCTION")
    ;

    private String value;

    private MObjectType(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}