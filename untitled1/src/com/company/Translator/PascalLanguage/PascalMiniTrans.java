package com.company.Translator.PascalLanguage;

import com.company.Translator.BaseLang.Instruction;
import com.company.Translator.BaseLang.Instructions.*;

import java.util.HashMap;
import java.util.function.Function;

public class PascalMiniTrans {
    public void FillPascalMap(HashMap<String, Function<Instruction, String>> map) {
        map.put("Initialization", x -> {
            var sb = new StringBuilder();
            var ins = (Initialization) x;
            var var = map.get(ins.var.type).apply(ins.var);
            var value = map.get(ins.value.type).apply(ins.value);
            sb.append(var);
            sb.append(" := ");
            sb.append(value + ";" + "\n");
            return sb.toString();
        });
        map.put("Var", x -> {
            var ins = (VarInstruction) x;
            return ins.var;
        });
        map.put("Literal", x -> {
            var ins = (LiteralInstruction) x;
            return ins.value;
        });
        map.put("For loop", x -> {
            var ins = (ForInstruction) x;
            var sb = new StringBuilder();
            sb.append("for ").append(ins.var).append(" := ").append(ins.from).append(" to ").append(ins.to).append(" do begin\n");
            for (var inst : ins.Children) {
                sb.append("\t\t" + map.get(inst.type).apply(inst));
            }
            sb.append("\tend;\n");
            return sb.toString();
        });
        map.put("set", x -> {
            var sb = new StringBuilder();
            var ins = (SetInstruction) x;
            var var = map.get(ins.var.type).apply(ins.var);
            sb.append(var);
            sb.append(" := ");
            var value = map.get(ins.value.type).apply(ins.value);
            sb.append(value + ";" + "\n");
            return sb.toString();
        });
        map.put("+", x -> {
            var ins = (MathInstruction) x;
            var sb = new StringBuilder();
            var left = map.get(ins.left.type).apply(ins.left);
            var right = map.get(ins.right.type).apply(ins.right);
            sb.append(left + " + " + right);
            return sb.toString();
        });
        map.put("print", x -> {
            var ins = (PrintInstruction) x;
            var sb = new StringBuilder();
            var content = map.get(ins.content.type).apply(ins.content);
            sb.append("writeln" + "(" + content + ")" + ";\n");
            return sb.toString();
        });
    }
}
