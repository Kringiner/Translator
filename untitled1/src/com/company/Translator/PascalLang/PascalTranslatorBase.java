package com.company.Translator.PascalLang;

import com.company.Lexer.Token;
import com.company.Translator.BaseLang.BaseLanguage;
import com.company.Translator.BaseLang.Instruction;
import com.company.Translator.BaseLang.Instructions.*;
import com.company.Translator.IBaseTrans;

import java.util.ArrayList;
import java.util.Arrays;

public class PascalTranslatorBase extends IBaseTrans {
    private ArrayList<String> iniVars;

    private String getForString(Token token) {
        if (token.getType().equals("Variable")) {
            return token.getText();
        } else if (token.getType().equals("IntNum")) {
            return token.getText();
        } else return null;
    }

    private ForInstruction MakeForInst(Token[] tokens) {
        var i = 0;
        var var = getForString(tokens[i]);
        if (i + 1 < tokens.length && tokens[i + 1].getType().equals("set")) i += 2;
        var from = getForString(tokens[i]);
        if (i + 1 < tokens.length && tokens[i + 1].getType().equals("To")) {
            i += 2;
            var to = getForString(tokens[i]);
            var step = "1";
            return new ForInstruction(from, to, var, step);
        } else return null;
    }

    private ArrayList<Instruction> ReadInstruction(Token[] tokens) {
        var ins = new ArrayList<Instruction>();
        var i = 0;
        var start = 0;
        while (i < tokens.length) {
            switch (tokens[i].getType()) {
                case "Variable":
                    if (i + 1 < tokens.length) {
                        switch (tokens[i + 1].getType()) {
                            case "set":
                                var setVar = tokens[i].getText();
                                i += 2;
                                start = i;
                                while (i + 1 < tokens.length && !tokens[i + 1].getType().equals("SemilCom")) i++;
                                if (i + 1 < tokens.length && !tokens[i + 1].getType().equals("SemilCom")) return null;
                                i++;
                                var value = ReadInstruction(Arrays.copyOfRange(tokens, start, i + 1));
                                if (value == null || value.size() != 1) return null;
                                if (iniVars.contains(setVar)) {
                                    ins.add(new SetInstruction(new VarInstruction(setVar), value.get(0)));
                                } else {
                                    iniVars.add(setVar);
                                    ins.add(new Initialization(new VarInstruction(setVar), value.get(0), "int"));
                                }
                                i++;
                                break;
                            case "MathOperators":
                                var mathVar = tokens[i].getText();
                                var mathOperator = tokens[i + 1].getText();
                                i += 2;
                                start = i;
                                while (i + 1 < tokens.length && !tokens[i + 1].getType().equals("SemilCom")) i++;
                                if (i + 1 < tokens.length && !tokens[i + 1].getType().equals("SemilCom")) return null;
                                var right = ReadInstruction(Arrays.copyOfRange(tokens, start, i + 2));
                                if (right == null || right.size() != 1) return null;
                                ins.add(new MathInstruction(mathOperator, new VarInstruction(mathVar), right.get(0)));
                                i += 2;
                                break;
                            default:
                                return null;
                        }
                    } else {
                        ins.add(new VarInstruction(tokens[i].getText()));
                        i++;
                    }
                    break;
                case "IntNum":
                    if (i + 1 < tokens.length) {
                        switch ((tokens[i + 1].getType())) {
                            case "SemilCom":
                                ins.add(new LiteralInstruction(tokens[i].getText()));
                                i += 2;
                                break;
                        }
                    } else {
                        ins.add(new LiteralInstruction(tokens[i].getText()));
                        i++;
                    }
                    break;
                case "For loop":
                    if (i + 1 < tokens.length) {
                        i++;
                        start = i;
                        while (i + 1 < tokens.length && !tokens[i + 1].getType().equals("do"))
                            i++;
                        if (i + 1 < tokens.length && !tokens[i + 1].getType().equals("do"))
                            return null;
                        var forIns = MakeForInst(Arrays.copyOfRange(tokens, start, i + 1));
                        if (forIns == null) return null;
                        i++;
                        if (i + 1 < tokens.length && tokens[i + 1].getType().equals("Begin")) {
                            i += 2;
                            start = i;
                            var skip = 0;
                            while (i + 1 < tokens.length && !tokens[i + 1].getType().equals("End") ) {
                                i++;
                                if(tokens[i].getText().equals("do")) {
                                    skip++;
                                }
                                if(i + 1< tokens.length && tokens[i+1].getType().equals("End") && skip > 0) {
                                    i++;
                                    skip--;
                                }
                            }
                            var forBody = ReadInstruction(Arrays.copyOfRange(tokens, start, i + 1));
                            if (forBody == null) return null;
                            forIns.Children = forBody;
                            ins.add(forIns);
                            i += 2;
                        } else return null;
                    } else return null;
                    break;
                case "print":
                    if (i + 1 < tokens.length && tokens[i + 1].getText().equals("(")) i += 2;
                    start = i;
                    while (i + 1 < tokens.length && !tokens[i + 1].getType().equals("SemilCom")) i++;
                    if (!tokens[i].getType().equals("Bracket")) return null;
                    var content = ReadInstruction(Arrays.copyOfRange(tokens, start, i));
                    if (content == null || content.size() != 1) return null;
                    ins.add(new PrintInstruction(content.get(0)));
                    i += 2;
                    break;
            }
        }
        return ins.size() == 0 ? null : ins;
    }

    @Override
    public BaseLanguage translate(Token[] tokensSrc) {
        iniVars = new ArrayList<>();
        var tokens = Arrays.stream(tokensSrc).filter(x -> !x.getType().equals("WhiteSpace")).toArray(Token[]::new);
        var baseLang = new BaseLanguage();
        var i = 0;
        if (tokens.length > i + 1 && tokens[i].getType().equals("EntryPoint") && tokens[i + 1].getType().equals("Variable")) {
            i++;
            baseLang.ProgrammeName = tokens[i].getText();
            i += 2;
        } else {
            return null;
        }
        if (i < tokens.length && tokens[i].getType().equals("Vars")) {
            var start = i + 1;
            while (i + 1 < tokens.length && !tokens[i + 1].getType().equals("Begin")) i++;
            if (tokens[i + 1].getType().equals("Begin")) {
                if (TryFillVars(baseLang, Arrays.copyOfRange(tokens, start, i + 1))) {
                    i++;
                } else return null;
            } else {
                return null;
            }
        } else return null;
        var start = i + 1;
        while (i + 1 < tokens.length && !tokens[i + 1].getType().equals("Dot")) i++;
        if (tokens[i].getType().equals("End")) {
            var root = Arrays.copyOfRange(tokens, start, i);
            if (root.length == 0) return baseLang;
            var instRoot = ReadInstruction(root);
            if (instRoot == null) return null;
            baseLang.Root = instRoot;
        }
        return baseLang;
    }

    private static boolean TryFillVars(BaseLanguage baseLang, Token[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            var tempList = new ArrayList<String>();
            while (i + 1 < tokens.length && !tokens[i + 1].getType().equals("Type")) {
                switch (tokens[i].getType()) {
                    case "Variable":
                        tempList.add(tokens[i].getText());
                        i++;
                        break;
                    case "Comma":
                    case "Colon":
                        i++;
                        break;
                    default:
                        return false;
                }
            }
            if (i + 1 < tokens.length && tokens[i + 1].getType().equals("Type")) {
                i++;
                var type = getBaseType(tokens[i].getText());
                if (type == null) return false;
                for (var temp : tempList) {
                    baseLang.vars.put(temp, type);
                }
                tempList = new ArrayList<String>();
            } else {
                return false;
            }
            if (i + 1 < tokens.length && !tokens[i + 1].getType().equals("SemilCom")) {
                return false;
            } else {
                i++;
            }
        }
        return true;
    }

    private static String getBaseType(String type) {
        if (type.equals("integer")) {
            return "int";
        }
        return null;
    }
}
