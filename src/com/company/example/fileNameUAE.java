package com.company.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class fileNameUAE {
    public static void main(String[] args) throws ParseException {
        String fileName = "0009700003444190614235900.SIF";

        if(fileName.length() < 29){
            System.out.println("errrr 1 "  + fileName.length());
        }

        String withOutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
        String dateTimeStr = withOutExtn.substring(Math.max(0, (withOutExtn.length() - 12)));

        System.out.println(dateTimeStr);

        if(dateTimeStr == null &&
                (!dateTimeStr.matches("[^0-9]+") || dateTimeStr.length() != 12)) {
            System.out.println("Errrrrr 2");
        }

        String molestIDStr = withOutExtn.substring(0, Math.max(0, (withOutExtn.length() - 12)));

        if(molestIDStr == null &&
                (!molestIDStr.matches("[^0-9]+") || molestIDStr.length() != 13)) {
            System.out.println("Errrrrr3");
        }



        String monthStr = dateTimeStr.substring(2,4);
        String dateStr = dateTimeStr.substring(4,6);
        String hourStr = dateTimeStr.substring(6,8);
        String minStr = dateTimeStr.substring(8,10);
        String secondsStr = dateTimeStr.substring(10,12);

        //System.out.println(dateTimeStr);
        //System.out.println( "yy" + monthStr + "|" + dateStr + "|" + hourStr + "|" + minStr + "|" + secondsStr);

        if(Integer.parseInt(monthStr) > 12){
            System.out.println("Errrrrr4");
        }
        if(Integer.parseInt(dateStr) >= 31){
            System.out.println("Errrrrr5");
        }
        if(Integer.parseInt(hourStr) > 23){
            System.out.println("Errrrrr6");
        }
        if(Integer.parseInt(minStr) > 59){
            System.out.println("Errrrrr7");
        }
        if(Integer.parseInt(secondsStr) > 59){
            System.out.println("Errrrrr8");
        }

        //Date parsedDate = DateUtilz.parseToDate(datePart, DateUtilzEnum.YYMMDDHHMMSS);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        sdf.setLenient(true);
        //System.out.println("yyMMddHHmmss");
        //System.out.println(dateTimeStr);
        Date parsedDate = sdf.parse(dateTimeStr);
        //System.out.println(molestIDStr.length());
        System.out.println("salary_upload : parsed date from the filename : " + parsedDate);
    }
}
