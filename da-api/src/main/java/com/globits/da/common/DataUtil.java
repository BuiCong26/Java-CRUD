package com.globits.da.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataUtil {
    public static boolean isNullOrEmpty(Integer obj){
        return obj == null || obj ==0;
    }
    public static boolean isNullOrEmpty(Object object){
        if(object == null){
            return true;
        }
        return false;
    }
    public static boolean isNullOrEmpty(List<Object> objects){
        return objects == null || objects.isEmpty();
    }
    public static boolean isNullOrEmpty(String s){
        return s == null || s =="";
    }
    public static LocalDateTime stringToLocalDateTime(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        return localDateTime;
    }
}
