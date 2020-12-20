package com.company.Lexer.JavaLexer.JavaReaders;

import com.company.Lexer.IReadable;
import com.company.Lexer.Token;

public class JavaKeyWordsReader implements IReadable {
    private String[] words;
    private String type = "KeyWord";

    public JavaKeyWordsReader(String... args ){
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
