package com.company.Translator;

import com.company.Lexer.Token;

import java.util.HashMap;

public class Translator {
    private HashMap<String, Language> languages;

    public Translator() {
        languages = new HashMap<String, Language>();
    }

    public void register(String languageStr, Language language) {
        languages.put(languageStr, language);
    }

    public String translate(String srcLanguageStr, String distLanguageStr, String src) {
        Language srcLanguage = languages.get(srcLanguageStr);
        Language distLanguage = languages.get(distLanguageStr);
        var baseLang = srcLanguage.getTreeFromString(src);
        return  distLanguage.getTokensFromTree(baseLang);
    }
}
