package com.eksadsupport.minilab.Common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Map;

public class Util {

    public static boolean checkStringIfNulllOrEmpty(String s){
        if(s.isEmpty() || s == null){
            return true;
        }
        return false;
    }

    public static String valueToStringOrEmpty(Map<String, ?> map, String key) {
        Object value = map.get(key);
        return value == null ? "" : value.toString();
    }

    public static boolean checkStringIfAlphabets(String s){
        if(s.replaceAll("\\s+", "").matches("[a-zA-Z]+")){
            return true;
        }
        return false;
    }

    public static boolean checkIfValidEmail(String s){
        if(s.matches("^(.+)@(\\S+)$")){
            return true;
        }
        return false;
    }

    public static String generateId(){
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddhh24mmssSSSSS"));

//        return new SimpleDateFormat("yyyyMMddHHmmssSSSSS").format(new Date());
    }

    public static boolean isValidId(String dateStr) {
//        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
//        sdf.setLenient(false);
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSS");
            dateFormatter.parse(dateStr);
//            sdf.parse(dateStr);
        }
        catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
