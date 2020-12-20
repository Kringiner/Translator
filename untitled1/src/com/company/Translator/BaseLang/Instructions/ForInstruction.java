package com.company.Translator.BaseLang.Instructions;

import com.company.Translator.BaseLang.Instruction;

public class ForInstruction extends Instruction {
    public String from;
    public String to;
    public String step;
    public String var;
    public ForInstruction(String from, String to, String var, String step) {
        super("For loop");
        this.from = from;
        this.to = to;
        this.var = var;
        this.step = step;
    }
}
