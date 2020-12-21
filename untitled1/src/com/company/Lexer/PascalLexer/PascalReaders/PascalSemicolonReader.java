package com.company.Lexer.PascalLexer.PascalReaders;

import com.company.Lexer.JavaLexer.JavaReaders.JavaKeyWordsReader;

public class PascalSemicolonReader extends JavaKeyWordsReader {
    public PascalSemicolonReader(){
        super(";");
        setType("SemilCom");
    }
}
