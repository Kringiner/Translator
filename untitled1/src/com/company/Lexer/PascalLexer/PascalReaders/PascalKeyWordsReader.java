package com.company.Lexer.PascalLexer.PascalReaders;

import com.company.Lexer.IReadable;
import com.company.Lexer.Token;

public class PascalKeyWordsReader implements IReadable {
    private String[] words;
    private String type = "KeyWord";

    public PascalKeyWordsReader(String... args ){
        words = args;
    }

    protected void setType(String type){
        this.type = type;
    }

    @Override
    public Token getToken(String text) {
        for (var word: words) {
            if(text.startsWith(word)) return new Token(text.substring(0,word.length()),type);
        }
        return null;
    }
}