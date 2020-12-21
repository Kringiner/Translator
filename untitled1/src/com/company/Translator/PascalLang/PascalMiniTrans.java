package com.company.Translator.PascalLang;

import com.company.Translator.BaseLang.Instruction;
import com.company.Translator.BaseLang.Instructions.*;

import java.util.HashMap;
import java.util.function.Function;

public class PascalMiniTrans {
    public void FillPascalMap(HashMap<String, Function<Instruction, String>> map, PascalLanguage pascal) {
        map.put("Initialization", x -> {
            var sb = new StringBuilder();
            var ins = (Initialization) x;
            var var = map.get(ins.var.type).apply(ins.var);
            var value = map.get(ins.value.type).apply(ins.value);
            sb.append("\t".repeat(Math.max(0, pascal.tabs )));
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
            if(!((ForInstruction) x).step.equals("1")) return null;
            var sb = new StringBuilder();
            sb.append("\t".repeat(Math.max(0, pascal.tabs )));
            sb.append("for ").append(ins.var).append(" := ").append(ins.from).append(" to ").append(ins.to).append(" do begin\n");
            pascal.tabs++;
            for (var inst : ins.Children) {
                sb.append(map.get(inst.type).apply(inst));
            }
            pascal.tabs--;
            sb.append("\t".repeat(Math.max(0, pascal.tabs)));
            sb.append("end;\n");
            return sb.toString();
        });
        map.put("set", x -> {
            var sb = new StringBuilder();
            var ins = (SetInstruction) x;
            var var = map.get(ins.var.type).apply(ins.var);
            sb.append("\t".repeat(Math.max(0, pascal.tabs)));
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
            sb.append("\t".repeat(Math.max(0, pascal.tabs - 1)));
            sb.append("writeln" + "(" + content + ")" + ";\n");
            return sb.toString();
        });
    }
}
