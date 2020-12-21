package com.company.Translator.JavaLang;

import com.company.Translator.BaseLang.Instruction;
import com.company.Translator.BaseLang.Instructions.*;

import java.util.HashMap;
import java.util.function.Function;

public class JavaMiniTrans {
    public static void FillJavaMiniTrans(HashMap<String, Function<Instruction, String>> map, JavaLanguage java){
        map.put("Initialization", x -> {
            var sb = new StringBuilder();
            var ins = (Initialization) x;
            sb.append("\t".repeat(Math.max(0, java.tabs )));
            sb.append(ins.typeVar + " ");
            sb.append(map.get(ins.var.type).apply(ins.var));
            sb.append(" = ");
            sb.append(map.get(ins.value.type).apply(ins.value));
            sb.append(";\n");
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
            sb.append("\t".repeat(Math.max(0, java.tabs )));
            sb.append("for(int ").append(ins.var).append(" = ").append(ins.from).append("; ").append(ins.var);
            sb.append(" < " + (Integer.parseInt(ins.to) + 1) + "; " + ins.var + "++" +"){\n");
            java.tabs++;
            for (var inst : ins.Children) {
                sb.append(map.get(inst.type).apply(inst));
            }
            java.tabs--;
            sb.append("\t".repeat(Math.max(0, java.tabs)));
            sb.append("}\n");
            return sb.toString();
        });
        map.put("set", x -> {
            var sb = new StringBuilder();
            var ins = (SetInstruction) x;
            var var = map.get(ins.var.type).apply(ins.var);
            sb.append("\t".repeat(Math.max(0, java.tabs)));
            sb.append(var);
            sb.append(" = ");
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
            sb.append("\t".repeat(Math.max(0, java.tabs)));
            sb.append("System.out.println" + "(" + content + ")" + ";\n");
            return sb.toString();
        });
    }
}
