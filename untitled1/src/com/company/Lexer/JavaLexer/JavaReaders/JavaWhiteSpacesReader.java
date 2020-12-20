package com.company.Lexer.JavaLexer.JavaReaders;

import com.company.Lexer.BaseCharReader;

public class JavaWhiteSpacesReader extends BaseCharReader {
    public JavaWhiteSpacesReader() {
        setType("WhiteSpace");
        putTrans(' ',  true, true);
        putTrans(' ', false, true);
    }
}
