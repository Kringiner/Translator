package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class MathInstruction extends Instruction {
    public Instruction left;
    public Instruction right;
    public MathInstruction(String type,Instruction left, Instruction right) {
        super(type);
        this.left = left;
        this.right = right;
    }
}
