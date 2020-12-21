package com.company.Lexer.PascalLexer.PascalReaders;

public class PascalPrintMethodReader extends PascalKeyWordsReader{
    public PascalPrintMethodReader(){
        super("Writeln","writeln");
        setType("print");
    }
}
