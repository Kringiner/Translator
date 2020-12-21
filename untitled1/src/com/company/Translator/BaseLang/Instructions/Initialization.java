package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class Initialization extends Instruction {
    public VarInstruction var;
    public Instruction value;
    public String typeVar;
    public Initialization(VarInstruction var, Instruction value, String typeVar) {
        super("Initialization");
        this.var = var;
        this.value = value;
        this.typeVar = typeVar;
    }
}
