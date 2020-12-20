package com.company.Lexer;

public class StringReader extends BaseReader {
    public StringReader(){
        putTrans('"',null,false,true);
    }
}
