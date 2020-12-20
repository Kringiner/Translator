package com.company;


import com.company.Lexer.JavaLexer.JavaLexer;
import com.company.Translator.JavaLang.JavaLanguage;
import com.company.Translator.PascalLanguage.PascalLanguage;
import com.company.Translator.Translator;

import javax.sound.midi.Track;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static String DeleteSpecificationUTF8(String S) {
        byte[] Str2 = S.getBytes();
        try {
            Str2[0] = 0032;
            Str2[1] = 0032;
            Str2[2] = 0032;
            S = new String(Str2, "UTF8");
        } catch (Exception e) {

        }
        return S.trim();
    }

    public static String ReadFile() {
        String text;
        try {

            File file = new File("C:\\Users\\Loliconshik\\Desktop\\untitled1\\src\\com\\company\\test_program.txt");
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\Z");
            text = scanner.next().replace('\t', ' ')
                    .replace('\n', ' ')
                    .replace('\r', ' ');
            scanner.close();
            return DeleteSpecificationUTF8(text);
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String text = ReadFile();
        var translator = new Translator();
        var java = new JavaLanguage();
        var pascal = new PascalLanguage();
        translator.register("java",java);
        translator.register("pascal",pascal);
        var tr = translator.translate("java","pascal",text);
    }
}
