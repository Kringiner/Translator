package com.company.Lexer;

import java.util.ArrayList;

public class Lexer {
    ArrayList<IReadable> readers = new ArrayList<>();

    public void register(IReadable reader) {
        readers.add(reader);
    }

    private Token getValidToken(String activeStr) {
        Token token = null;
        var length = 0;
        for (var reader : readers) {
            Token tmp = reader.getToken(activeStr);
            if (tmp == null) continue;
            if (length < tmp.getText().length()) {
                token = tmp;
                length = tmp.getText().length();
            }
        }
        return token;
    }

    public Token[] tokenize(String str) {
        var tokens = new ArrayList<Token>();
        while (str.length() != 0) {
            var token = getValidToken(str);
            if (token == null) return null;
            tokens.add(token);
            str = str.substring(token.getText().length());
        }
        return tokens.toArray(new Token[0]);
    }

}
