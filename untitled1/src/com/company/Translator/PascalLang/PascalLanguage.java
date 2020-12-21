package com.company.Translator.PascalLang;

import com.company.Lexer.PascalLexer.PascalLexer;
import com.company.Translator.BaseLang.BaseLanguage;
import com.company.Translator.Language;

public class PascalLanguage extends Language {
    public int tabs = 0 ;
    public PascalLanguage() {
        new PascalMiniTrans().FillPascalMap(MiniTrans, this);
        new PascalLexer().MakePascalLexer(tokensConverter);
        translatorBase = new PascalTranslatorBase();
    }

    private String getType(String type) {
        switch (type){
            case "int" -> {return "integer"; }
            case "String" -> {return "string";}
            default -> {return null;}
        }
    }

    @Override
    public String getTokensFromTree(BaseLanguage tree) {
        var sb = new StringBuilder();
        sb.append("Program " + tree.ProgrammeName + ";\n");
        sb.append("var ");
        for (var var : tree.vars.keySet()) {
            sb.append(var + " : " + getType(tree.vars.get(var)) + ";\n");
        }
        sb.append("begin\n");
        for (var ins : tree.Root) {
            if (MiniTrans.containsKey(ins.type)) {
                sb.append(MiniTrans.get(ins.type).apply(ins));
            } else {
                return null;
            }
        }
        sb.append("end.");
        return sb.toString();
    }
}
