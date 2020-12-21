package com.company.Translator;

import com.company.Lexer.IReadable;
import com.company.Lexer.Lexer;
import com.company.Lexer.Token;
import com.company.Translator.BaseLang.BaseLanguage;
import com.company.Translator.BaseLang.Instruction;
import com.company.Translator.JavaLang.JavaTranslatorBase;

import java.util.HashMap;
import java.util.function.Function;

public abstract class Language {
    public Lexer tokensConverter = new Lexer();
    public IBaseTrans translatorBase;
    public HashMap<String, Function<Instruction,String>> MiniTrans = new HashMap<>();

    public void registerReader(IReadable reader) {
        tokensConverter.register(reader);
    }
    public void putTrans(String str , Function<Instruction,String> func){
        if(!MiniTrans.containsKey(str)) MiniTrans.put(str,func);
    }

    public BaseLanguage getTreeFromString(String src){
        var tokens = tokensConverter.tokenize(src);
        return translatorBase.translate(tokens);
    }

    public abstract String getTokensFromTree(BaseLanguage tree);
}
