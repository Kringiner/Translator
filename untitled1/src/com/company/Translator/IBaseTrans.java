package com.company.Translator;

import com.company.Lexer.Token;
import com.company.Translator.BaseLang.BaseLanguage;

public abstract class IBaseTrans {
    public abstract BaseLanguage translate(Token[] tokens);
}
