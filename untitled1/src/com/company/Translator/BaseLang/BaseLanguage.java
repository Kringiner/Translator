package com.company.Translator.BaseLang;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseLanguage {
    public String ProgrammeName;
    public HashMap<String,String> vars = new HashMap<>();
    public ArrayList<Instruction> Root = new ArrayList<>();
}
