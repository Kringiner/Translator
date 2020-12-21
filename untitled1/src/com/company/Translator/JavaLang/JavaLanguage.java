package com.company.Translator.JavaLang;

import com.company.Lexer.JavaLexer.JavaLexer;
import com.company.Translator.BaseLang.BaseLanguage;
import com.company.Translator.Language;

public class JavaLanguage extends Language {
    public int tabs = 0;
    public JavaLanguage() {
        new JavaMiniTrans().FillJavaMiniTrans(MiniTrans, this);
        JavaLexer.MakeJavaLexer(tokensConverter);
        translatorBase = new JavaTranslatorBase();
    }

    @Override
    public String getTokensFromTree(BaseLanguage tree) {
        var sb = new StringBuilder();
        sb.append("public class " + tree.ProgrammeName +" ");
        sb.append("{\n");
        tabs++;
        sb.append("\t".repeat(Math.max(0, tabs )));
        sb.append("public static void main(String[] args) {\n");
        tabs++;
        for (var ins : tree.Root) {
            if (MiniTrans.containsKey(ins.type)) {
                sb.append(MiniTrans.get(ins.type).apply(ins));
            } else {
                return null;
            }
        }
        tabs--;
        sb.append("\t".repeat(Math.max(0, tabs )));
        sb.append("}\n");
        tabs--;
        sb.append("\t".repeat(Math.max(0, tabs )));
        sb.append("}\n");
        return sb.toString();
    }
}
