package com.company.Translator.BaseLang;

import java.util.ArrayList;

public class Instruction {
    public String type;
    public ArrayList<Instruction> Children;
    public Instruction(String type){
        this.type = type;
        Children = new ArrayList<>();
    }
}
