package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class ConditionalInstruction extends Instruction {
    public Instruction left;
    public Instruction right;
    public ConditionalInstruction(String type,Instruction left, Instruction right) {
        super(type);
        this.left = left;
        this.right = right;
    }
}
