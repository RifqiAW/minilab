package com.eksadsupport.minilab.Common;

import java.util.Map;

public class Util {

    public boolean checkStringIfNulllOrEmpty(String s){
        if(s.isEmpty() || s == null){
            return true;
        }
        return false;
    }

    public String valueToStringOrEmpty(Map<String, ?> map, String key) {
        Object value = map.get(key);
        return value == null ? "" : value.toString();
    }

    public boolean checkStringIfAlphabets(String s){
        if(s.replaceAll("\\s+", "").matches("[a-zA-Z]+")){
            return true;
        }
        return false;
    }
}
