package com.company.Lexer.PascalLexer.PascalReaders;

public class PascalEntryPointReader extends PascalKeyWordsReader {
    public PascalEntryPointReader(){
        super("Program");
        setType("EntryPoint");
    }
}
