package com.company.Lexer.JavaLexer.JavaReaders;

public class JavaEntryPointReader extends JavaKeyWordsReader{
    public JavaEntryPointReader(){
        super("main(String[] args)");
        setType("EntryPoint");
    }
}
