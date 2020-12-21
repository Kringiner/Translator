package com.company.Lexer.PascalLexer.PascalReaders;

public class PascalSetReader extends PascalKeyWordsReader{
    public PascalSetReader(){
        super(":=");
        setType("set");
    }
}
