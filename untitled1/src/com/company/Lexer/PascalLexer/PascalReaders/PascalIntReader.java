package com.company.Lexer.PascalLexer.PascalReaders;

import com.company.Lexer.BaseCharReader;

public class PascalIntReader extends BaseCharReader {
    public PascalIntReader(){
        setType("IntNum");
        putTrans('-',false,true);
        putTrans('0',false,true);
        putTrans('0',true,true);
        putTrans('1',false,true);
        putTrans('1',true,true);
        putTrans('2',false,true);
        putTrans('2',true,true);
        putTrans('3',false,true);
        putTrans('3',true,true);
        putTrans('4',false,true);
        putTrans('4',true,true);
        putTrans('5',false,true);
        putTrans('5',true,true);
        putTrans('6',false,true);
        putTrans('6',true,true);
        putTrans('7',false,true);
        putTrans('7',true,true);
        putTrans('8',false,true);
        putTrans('8',true,true);
        putTrans('9',false,true);
        putTrans('9',true,true);
    }
}
