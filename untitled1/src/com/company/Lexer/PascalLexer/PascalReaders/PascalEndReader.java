package com.company.Lexer.PascalLexer.PascalReaders;

public class PascalEndReader extends PascalKeyWordsReader{
    public PascalEndReader(){
        super("end;", "End;", "End.", "end.");
        setType("End");
    }
}
