package com.company.Lexer.PascalLexer;

import com.company.Lexer.Lexer;
import com.company.Lexer.PascalLexer.PascalReaders.*;

public class PascalLexer {
    public static void MakePascalLexer(Lexer lex){
        lex.register(new PascalEntryPointReader());
        lex.register(new PascalSetReader());
        lex.register(new PascalKeyWordsReader());
        lex.register(new PascalSemicolonReader());
        lex.register(new PascalVarReader());
        lex.register(new PascalColonReader());
        lex.register(new PascalWhiteSpacesReader());
        lex.register(new PascalIntReader());
        lex.register(new PascalForReader());
        lex.register(new PascalBeginReader());
        lex.register(new PascalEndReader());
        lex.register(new PascalDoReader());
        lex.register(new PascalToReader());
        lex.register(new PascalDotReader());
        lex.register(new PascalTypeReader("integer","Integer"));
        lex.register(new PascalCommaReader());
        lex.register(new PascalMathOperatorsReader("+"));
        lex.register(new PascalBracketReader());
        lex.register(new PascalPrintMethodReader());
        lex.register(new PascalVariableReader());
    }
}
