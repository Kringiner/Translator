package com.company.Lexer;

import java.util.ArrayList;

public class Token {
    private final String stringValue;
    private final String type;
    public ArrayList<Token> children = new ArrayList<>();

    public Token(String text, String type) {
        stringValue = text;
        this.type = type;
    }

    public String getText() {
        return stringValue;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return new StringBuilder().append('[').append(stringValue).append(']').toString();
    }

    @Override
    public boolean equals(Object obj) {
        Token other = (Token) obj;
        return stringValue.equals(other.stringValue);
    }
}
