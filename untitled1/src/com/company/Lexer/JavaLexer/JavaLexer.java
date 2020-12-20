package com.company.Lexer.JavaLexer;

import com.company.Lexer.JavaLexer.JavaReaders.*;
import com.company.Lexer.Lexer;

public class JavaLexer {
    public static void MakeJavaLexer(Lexer lex){
        lex.register(new JavaForLoopReader());
        lex.register(new JavaBracketReader("(",")"));
        lex.register(new JavaModificationsReader("public","static"));
        lex.register(new JavaFigBracketReader());
        lex.register(new JavaClassReader());
        lex.register(new JavaReturnValueReader());
        lex.register(new JavaTypeReader("int"));
        lex.register(new JavaMathOperatorsReader("+","*","%","/","-"));
        lex.register(new JavaIncrementReader());
        lex.register(new JavaVariableReader());
        lex.register(new JavaSemilcommaReader());
        lex.register(new JavaWhiteSpacesReader());
        lex.register(new JavaIntReader());
        lex.register(new JavaLogicalOperatorsReader("<",">=","<=","==",">","!="));
        lex.register(new JavaSetReader());
        lex.register(new JavaPrintMethodsReader());
        lex.register(new JavaEntryPointReader());
    }
}
