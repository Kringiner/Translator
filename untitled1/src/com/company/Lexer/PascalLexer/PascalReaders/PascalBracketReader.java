package com.company.Lexer.PascalLexer.PascalReaders;

public class PascalBracketReader extends  PascalKeyWordsReader{
    public PascalBracketReader(){
        super("(",")");
        setType("Bracket");
    }
}
