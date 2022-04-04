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
        if(s == null || s.isEmpty()){
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
                .format(DateTimeFormatter.ofPattern("yyyyMMddHH24mmssSSSSS"));
    }

    public static String generateQueryDate(){
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static boolean isValidId(String dateStr) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMddHH24mmssSSSSS");
            dateFormatter.parse(dateStr);
        }
        catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    public static boolean checkIfValidTelp(String s) {
        if (s.matches("\\d[0-9]{0,2}[-]*[0-9]{8}")) {
            return true;
        }
        return false;

    }
    public static boolean checkIfValidTelp2(String s){
        if(s.matches("^[0-9]{3}-[0-9]{8}")){
            return true;
        }
        return false;
    }
    public static boolean checkIfValidTHp(String s){
        if(s.matches("^(\\+628\\d{1,11}\\s*$)")){
            return true;
        }
        return false;
    }

    public static boolean checkValidKkDanKtp(String s){
        if(s.matches("^(1[1-9]|21|[37][1-6]|5[1-3]|6[1-5]|[89][12])" +
                "\\d{2}\\d{2}([04][1-9]|[1256][0-9]|[37][01])(0[1-9]|1[0-2])\\d{2}\\d{4}$"))
        {
            return true;
        }
        return false;
    }
}
