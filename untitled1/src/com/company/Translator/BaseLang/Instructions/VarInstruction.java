package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class VarInstruction extends Instruction {
    public String var;
    public VarInstruction(String value) {
        super("Var");
        var = value;
    }
}
