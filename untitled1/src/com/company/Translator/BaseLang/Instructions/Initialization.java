package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class Initialization extends Instruction {
    public VarInstruction var;
    public Instruction value;
    public Initialization(VarInstruction var, Instruction value) {
        super("Initialization");
        this.var = var;
        this.value = value;
    }
}
