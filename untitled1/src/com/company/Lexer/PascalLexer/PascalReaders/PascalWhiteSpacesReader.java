package com.company.Lexer.PascalLexer.PascalReaders;

import com.company.Lexer.BaseCharReader;

public class PascalWhiteSpacesReader extends BaseCharReader {
    public PascalWhiteSpacesReader(){
        setType("WhiteSpace");
        putTrans(' ',  true, true);
        putTrans(' ', false, true);
    }
}
