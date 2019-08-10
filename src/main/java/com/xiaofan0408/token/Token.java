package com.xiaofan0408.token;

import lombok.*;

/**
 * @author xuzefan  2019/8/10 11:20
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private TokenType type;
    private String literal;

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", literal='" + literal + '\'' +
                '}';
    }
}
