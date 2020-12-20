package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class LiteralInstruction extends Instruction {
    public String value;
    public LiteralInstruction(String value) {
        super("Literal");
        this.value = value;
    }
}
