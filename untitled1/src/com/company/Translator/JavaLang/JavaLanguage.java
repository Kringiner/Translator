package com.company.Translator.JavaLang;

import com.company.Lexer.JavaLexer.JavaLexer;
import com.company.Translator.BaseLang.BaseLanguage;
import com.company.Translator.Language;

public class JavaLanguage extends Language {
    public JavaLanguage() {
        JavaLexer.MakeJavaLexer(tokensConverter);
    }

    @Override
    public String getTokensFromTree(BaseLanguage tree) {
        return null;
    }
}
