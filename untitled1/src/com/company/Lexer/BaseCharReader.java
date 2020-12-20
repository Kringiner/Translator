package com.company.Lexer;

import java.util.HashMap;

public class BaseCharReader implements IReadable {
    private HashMap<Character, HashMap<Boolean, Boolean>> transMap = new HashMap<>();
    private Boolean currentState = false;
    private Boolean previousState = false;
    private String type = "non";

    public void putTrans(Character ch, Boolean st1, Boolean st2) {
        if (transMap.containsKey(ch)) {
            var tCh = transMap.get(ch);
            if (!tCh.containsKey(st1)) {
                tCh.put(st1, st2);
            }
        } else {
            transMap.put(ch, new HashMap<>() {{
                put(st1, st2);
            }});
        }
    }

    public void validation(Character ch) {
        if (transMap.containsKey(ch)) {
            var temp = transMap.get(ch);
            previousState = currentState;
            currentState = temp.containsKey(previousState) && temp.get(previousState);
        } else {
            restart();
        }
    }

    private void restart() {
        currentState = false;
        previousState = false;
    }

    public void setType(String str){
        type = str;
    }


    @Override
    public Token getToken(String text) {
        var index = 0;
        while (index < text.length()) {
            validation(text.charAt(index));
            if (!currentState) {
                var s = text.substring(0, index );
                restart();
                return index != 0 ? new Token(text.substring(0, index ), type) : null;
            }
            index++;
        }
        return currentState ?  new Token(text.substring(0, index), type) : null;
    }
}
