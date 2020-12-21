package com.company.Translator.JavaLang;

import com.company.Lexer.Token;
import com.company.Translator.BaseLang.BaseLanguage;
import com.company.Translator.BaseLang.Instruction;
import com.company.Translator.BaseLang.Instructions.*;
import com.company.Translator.IBaseTrans;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class JavaTranslatorBase extends IBaseTrans {
    private BaseLanguage baseLang = new BaseLanguage();
    private ForInstruction MakeForFromBody(ArrayList<Instruction> forBody){
        if(forBody.size() != 3) return null;
        var var = ((Initialization) forBody.get(0)).var.var;
        var from = ((LiteralInstruction) (((Initialization) forBody.get(0))).value).value;
        String to;
        if ("<".equals(((ConditionalInstruction) forBody.get(1)).type)) {
            to = (((LiteralInstruction) (((ConditionalInstruction) forBody.get(1))).right).value);
            to = (Integer.parseInt(to) - 1) + "";
        } else {
            return null;
        }
        var step = ((LiteralInstruction) (((MathInstruction) forBody.get(2))).right).value;
        return new ForInstruction(from, to, var,step);
    }

    public ArrayList<Instruction> ReadInstruction(Token[] tokens) {
        var inst = new ArrayList<Instruction>();
        var start = 0;
        var i = 0;
        while (i < tokens.length) {
            switch (tokens[i].getType()) {
                case "Type":
                    if (tokens[i + 1].getType().equals("Variable")) {
                        baseLang.vars.put(tokens[i + 1].getText(), tokens[i].getText());
                        var varName = tokens[i + 1].getText();
                        i += 2;
                        if (tokens[i].getType().equals("Set")) {
                            i++;
                            start = i;
                            while (!tokens[i + 1].getType().equals("SemilCom")) i++;
                            var body = Arrays.copyOfRange(tokens, start, i + 1);
                            var exp = ReadInstruction(body);
                            if (exp == null) return null;
                            var ini = new Initialization(new VarInstruction(varName), exp.get(0), "int");
                            inst.add(ini);
                            i++;
                        }
                    }
                    break;
                case "IntNum":
                    inst.add(new LiteralInstruction(tokens[i].getText()));
                    i++;
                    break;
                case "ForLoop":
                    if (tokens[i + 1].getText().equals("(")) {
                        i += 2;
                    } else {
                        return null;
                    }
                    start = i;
                    while (!tokens[i + 1].getType().equals("Bracket")) i++;
                    var forBody = ReadInstruction(Arrays.copyOfRange(tokens, start, i + 1));
                    if (forBody == null) return null;
                    i++;
                    var forInst = MakeForFromBody(forBody);
                    if( forInst == null ) return null;
                    if (tokens[i + 1].getType().equals("FigBracket")) {
                        i += 2;
                    } else {
                        return null;
                    }
                    start = i;
                    var skip = 0;
                    while (i < tokens.length && !tokens[i + 1].getText().equals("}")) {
                        i++;
                        if(tokens[i].getText().equals("{")) {
                            skip++;
                        }
                        if(i + 1< tokens.length && tokens[i+1].getText().equals("}") && skip > 0) {
                            i++;
                            skip--;
                        }
                    }
                    var instr = ReadInstruction(Arrays.copyOfRange(tokens, start, i + 2));
                    forInst.Children = instr;
                    inst.add(forInst);
                    i++;
                    break;
                case "Variable":
                    if(tokens.length <= i + 1){
                        inst.add(new VarInstruction(tokens[i].getText()));
                        i++;
                        break;
                    }
                    switch (tokens[i + 1].getType()) {
                        case "MathOperators" -> {
                            var left = tokens[i].getText();
                            var op = tokens[i + 1].getText();
                            i += 2;
                            start = i;
                            while (!tokens[i + 1].getType().equals("SemilCom")) i++;
                            var varMath = ReadInstruction(Arrays.copyOfRange(tokens, start, i + 2));
                            i++;
                            inst.add(new MathInstruction(op, new VarInstruction(left), varMath.get(0)));
                        }
                        case "LogicalOperator" -> {
                            var varCon = tokens[i].getText();
                            var con = tokens[i + 1].getText();
                            i = i + 2;
                            start = i;
                            while (!tokens[i + 1].getType().equals("SemilCom")) i++;
                            var condition = ReadInstruction(Arrays.copyOfRange(tokens, start, i + 2));
                            i++;
                            inst.add(new ConditionalInstruction(con, new VarInstruction(varCon), condition.get(0)));
                        }
                        case "Inc" -> {
                            var varInc = tokens[i].getText();
                            i += 2;
                            inst.add(new MathInstruction("+", new VarInstruction(varInc), new LiteralInstruction("1")));
                        }
                        case "Set" -> {
                            var setVar = tokens[i].getText();
                            i += 2;
                            start = i;
                            while (!tokens[i + 1].getType().equals("SemilCom")) i++;
                            var setValue = ReadInstruction(Arrays.copyOfRange(tokens, start, i + 2));
                            i += 2;
                            inst.add(new SetInstruction(new VarInstruction(setVar), setValue.get(0)));
                        }
                        default -> {
                            inst.add(new VarInstruction(tokens[i].getText()));
                            i++;
                        }
                    }
                    break;
                case "Print":
                    if (tokens[i + 1].getText().equals("(")) {
                        i += 2;
                    } else {
                        return null;
                    }
                    start = i;
                    while(!tokens[i + 1].getType().equals("SemilCom")) i++;
                    var content = ReadInstruction(Arrays.copyOfRange(tokens,start,i));
                    inst.add(new PrintInstruction(content.get(0)));
                    break;
                default:
                    i++;
                    break;
            }
        }
        return inst;
    }

    @Override
    public BaseLanguage translate(@NotNull Token[] tokenAr) {
        var tokens = Arrays.stream(tokenAr).filter(x -> !x.getType().equals("WhiteSpace")).toArray(Token[]::new);
        var i = 0;
        while (!tokens[i].getType().equals("Variable")) i++;
        baseLang.ProgrammeName = tokens[i].getText();
        i++;
        var body = Arrays.copyOfRange(tokens, i, tokens.length);
        var root = ReadInstruction(body);
        if (root == null) return null;
        baseLang.Root = root;
        return baseLang;
    }
}
