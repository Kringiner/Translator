package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class PrintInstruction extends Instruction {
    public Instruction content;
    public PrintInstruction(Instruction content) {
        super("print");
        this.content = content;
    }
}
