package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class SetInstruction extends Instruction {
    public VarInstruction var;
    public Instruction value;
    public SetInstruction(VarInstruction var, Instruction value) {
        super("set");
        this.var = var;
        this.value = value;
    }
}
