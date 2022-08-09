package com.company.salaryup;

import java.text.SimpleDateFormat;
import java.util.Date;


import java.text.ParseException;

public class DateUtil {
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String DDMMYYYY = "ddMMyyyy";
    public static final String DDMMYY = "ddMMyy";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String MMDDYYYY = "MMddyyyy";

    private static SimpleDateFormat simpleDateFormat = null;

    public DateUtil() {
    }

    public DateUtil(String simpleDateFormatPattern) {
        this.simpleDateFormat = new SimpleDateFormat(simpleDateFormatPattern);
    }

    public SimpleDateFormat getSimpleDateFormat(){
        return this.simpleDateFormat;
    }

    /***
     * Is valid date string <br>
     * 		Checks whether provided string is valid as per the
     * 		given SimpleDateFormat pattern
     * @param dateStr
     * @return
     */
    public boolean isValidDate(String dateStr) {
        return (parseToDate(dateStr) != null);
    }

    /***
     * Parse to string from date object with given format
     * @param date
     * @return
     */
    public  String parseToDateString(Date date) {
        if(simpleDateFormat == null) {
            throw new IllegalArgumentException("DateUtil: SimpleDateFormat pattern should be provided through the DateUtil(String simpleDateFormatPattern) constructor");
        }

        try {
            return simpleDateFormat.format(date);
        } catch (IllegalArgumentException e) {
            //logger.error("DateUtil : Exception happened while parsing the given date string : [" + date + "] with pattern: [" + simpleDateFormat.toPattern() + "]" , e);
            return null;
        }
    }

    /***
     * DateUtil(String simpleDateFormatPattern)
     * new DateUtil(DateUtil.YYYY_hyphen_MM_hyphen_DD)
     * Parse given date string to date
     * 		* Exceptions are handled
     * todo: TO BE ACCESSED AS STATIC
     * @param dateStr
     * @return
     */
    public Date parseToDate(String dateStr) { //TODO: Make parseToDate(String pattern, String dateStr) and call constructor / make simpleDF rather than making exception
        if(simpleDateFormat == null) {
            throw new IllegalArgumentException("DateUtil: SimpleDateFormat pattern should be provided through the DateUtil(String simpleDateFormatPattern) constructor");
        }
        try {
            System.out.println("sdf : " + simpleDateFormat);
            System.out.println("--> " + simpleDateFormat.parse(dateStr));
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException | IllegalArgumentException e) {
            //logger.error("DateUtil : Exception happened while parsing the given date string : [" + dateStr + "] with pattern: [" + simpleDateFormat.toPattern() + "]" , e);
            return null;
        }
    }

    public void parseme() {
    }
}
