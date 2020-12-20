package com.company.Lexer;


import java.util.HashMap;

public class BaseReader implements IReadable {
    private HashMap<Character, HashMap<Character, HashMap<Boolean, Boolean>>> transMap = new HashMap<>();
    private Boolean currentState = false;
    private Boolean previousState = false;
    private int controlLength = 1;
    private String type = "non";


    public void putTrans(Character ch, Character ch1, Boolean st1, Boolean st2) {
        if (transMap.containsKey(ch)) {
            var tCh = transMap.get(ch);
            if (tCh.containsKey(ch1)) {
                var tSt1 = tCh.get(ch1);
                if (!tSt1.containsKey(st1)) {
                    tSt1.put(st1, st2);
                }
            } else {
                tCh.put(ch1, new HashMap<>() {{
                    put(st1, st2);
                }});
            }
        } else {
            transMap.put(ch, new HashMap<>() {{
                put(ch1, new HashMap<>() {{
                    put(st1, st2);
                }});
            }});
        }
    }

    public void setType(String str){
        type = str;
    }

    public void setControlLength(int i) {
        controlLength = i;
    }

    public void validation(Character ch, Character recCh) {
        if (transMap.containsKey(ch)) {
            var temp = transMap.get(ch);
            if (temp.containsKey(recCh)) {
                var temp1 = temp.get(recCh);
                previousState = currentState;
                currentState = temp1.containsKey(previousState) && temp1.get(previousState);
            } else {
                if (temp.containsKey(null)) {
                    var temp1 = temp.get(null);
                    previousState = currentState;
                    currentState = temp1.containsKey(previousState) && temp1.get(previousState);
                }
            }
        } else {
            previousState = currentState;
        }
    }

    private void restart() {
        currentState = false;
        previousState = false;
    }

    @Override
    public Token getToken(String text) {
        var index = 0;
        while (index < text.length()) {
            validation(text.charAt(index), index + 1 < text.length() ? text.charAt(index + 1) : null);
            if (!currentState) {
                var s = text.substring(0, index + controlLength);
                restart();
                return index != 0 ? new Token(text.substring(0, index + controlLength), type) : null;
            }
            index++;
        }
        return null;
    }
}
