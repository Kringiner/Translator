package com.company.Lexer.JavaLexer.JavaReaders;

import com.company.Lexer.BaseCharReader;

public class JavaSemilcommaReader extends BaseCharReader {
    public JavaSemilcommaReader(){
        setType("SemilCom");
        putTrans(';',false,true);
    }
}
