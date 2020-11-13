package com.gamejam.test.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static Date getDate(String day) {
        LocalDate src = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Date.from(src.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
