package com.company.Lexer.JavaLexer.JavaReaders;

public class JavaFigBracketReader extends JavaKeyWordsReader {
    public JavaFigBracketReader(){
        super("{","}");
        setType("FigBracket");
    }
}
