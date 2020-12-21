package com.company.Lexer.PascalLexer.PascalReaders;

import com.company.Lexer.IReadable;
import com.company.Lexer.Token;

public class PascalVariableReader implements IReadable {
    @Override
    public Token getToken(String text) {
        if(text.length() == 0) return null;
        var i = 0;
        while(i < text.length() && Character.isLetter(text.charAt(i))) i++;
        return i == 0 ? null : new Token(text.substring(0,i),"Variable");
    }
}
