package com.company;

import com.company.salaryup.DateUtil;
//import org.apache.commons.collections4.iterators.EntrySetMapIterator;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Hello {

    private static List p;

    public static List getP() {
        if(p == null){
            p = new ArrayList();
        }
        return p;
    }

    public static void main(String[] args) throws ParseException {

        //bigDecimalSetScale();
        //splitNull();
        //System.out.println(getP().get(0));

        //isAllowed();

        //mapDescendingOrder();

/*        Object test = getBooleanCheck();
        System.out.println("=============");
        if(test instanceof Boolean){
            System.out.println((Boolean)test);
        }*/

        //whileIncrement();

        //fileParentDir();

        //cutofftime();

        //System.out.println(dateFormat(new Date()));

        //dateUtils();

        //hashMapEmtpyDuplicateKeys();

//        System.out.println("00 to int : " + Integer.parseInt("00"));
//        System.out.println("04 to int : " + Integer.parseInt("04"));
//        System.out.println("000 to int : " + Integer.parseInt("000"));
//        System.out.println("00.0 to int : " + Integer.parseInt("00.0"));

        //fileExteMolestID();

        //visa();

        //allowedChar();

        //alphanumeric();

        //patterncompile();

        firstDoesNotRepea();


    }

    private static void firstDoesNotRepea() {
        String[] repeaL = {"s", "s", "p", "k", "p", "k", "p", "k", "a"}; //p, g
        String s = null;
        int count = 0;

        for(String x : repeaL){
            for(String y : repeaL){
                s = x;
                if(x.equals(y)){
                    count ++;
                    if(count > 1) {
                        s = null;
                        count = 0 ;
                        break;
                    }
                }
            }
            if(count == 1){
                break;
            }
        }
        System.out.println(s);
    }

    private static void patterncompile() {
        String x = "x y z";

        String[] y = x.split("\\s");
        String[] r = new String[y.length];
        List<String> s = new ArrayList<>();
        for(int i = y.length -1; i >= 0 ; i--){
            r[y.length - 1 - i] = (y[i]);
            s.add(y[i]);
            //System.out.println("s @ " + (y.length - 1 - i) + " = " + s.get(y.length - 1 - i));
        }

        System.out.println(String.join(" ", r));
        System.out.println(s.stream().collect(Collectors.joining(" ")));
    }

    private static void alphanumeric() {
        String regx =  "^\\d*\\.?\\d*$"; //"^[0-9]*.?[0-9]*$"; //"^[0-9A-Za-z]{0,3}$";
        String[] words = {
                "9",
                "",
                "00908.fdfdf",
                "99.99999999",
                "090.0.",
                ".99",
                "abc",
                "XY",
                "f9ds0adf09s78df",
                "S0AD0FS09D8FSDF",
                "k",
                "P",
                "9",
                "DFDF*FDFDF9",
                "999999.8",
                "0000000009"
        };

        for (String w : words) {
            System.out.println(w.matches(regx));
        }
    }

    private static void allowedChar() {
        String fieldContent = "?????.?ab?c .d ) and (, - or ' any : and +/";
        String allowedChar = "^((?![!\\\"#$%&\\\\*;<=>@\\[\\]^_`{|}~]).)*$";

        System.out.println("Allowed: " + fieldContent.matches(allowedChar));

    }

    private static void visa() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date taken = sdf.parse("04/04/2022");
        Date expiry = sdf.parse("03/07/2022");
        Date today = new Date();

        long diffMilli = expiry.getTime() - taken.getTime();
        long daysLeft = expiry.getTime() - today.getTime();
        long totalDays = TimeUnit.DAYS.convert(diffMilli, TimeUnit.MILLISECONDS);
        long totalDaysLeft = TimeUnit.DAYS.convert(daysLeft, TimeUnit.MILLISECONDS);

        System.out.println("Last Visa taken on \t:\t" + taken);
        System.out.println("Expiry on\t\t\t:\t" + expiry);
        System.out.println("Total days\t\t\t:\t" + totalDays);
        System.out.println("Days left\t\t\t:\t" + totalDaysLeft);
    }

    private static void fileExteMolestID() {
        String filename = "0000000228658220426101052.SIF";
        String withoutExt = "0000000228658220426101052";
        String molId = withoutExt.substring(0, Math.max(0, (withoutExt.length() - 3)));
        String molId2 = withoutExt.substring(0, Math.min(withoutExt.length(), 4));
        System.out.println(molId);
        System.out.println(molId2);
        if(!molId.matches("^[0-9]*$")){
            System.out.println("Errr");
        }
    }

    private static void hashMapEmtpyDuplicateKeys() {
        HashMap<String, String> x = new HashMap<>();
        x.put("", "a");
        x.put("", "b");
        x.put("1", "c");
        x.put("2", "d");
        x.put(null, "e");

        for (Map.Entry entry : x.entrySet()){
            System.out.println("------>" + entry.getKey() + "<-------->" + entry.getValue());
        }
    }

    private static void dateUtils() {
        DateUtil dateUtilTime = new DateUtil("HHmm");
       // dateUtilTime
    }

    private static String dateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ffffffff");
        if(simpleDateFormat == null) {
            throw new IllegalArgumentException("DateUtil: SimpleDateFormat pattern should be provided through the DateUtil(String simpleDateFormatPattern) constructor");
        }

            return simpleDateFormat.format(date);


    }

    private static void cutofftime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current time : " + currentDateTime);
        LocalDateTime cutoffdate = LocalDateTime.of(currentDateTime.getYear(), currentDateTime.getMonth(), currentDateTime.getDayOfMonth(), 17, 0,0);
        System.out.println("cut off time: " + cutoffdate);

        System.out.println(cutoffdate.isAfter(currentDateTime));
    }

    private static void fileParentDir() {
        File pp = new File("C:\\Users\\user\\Documents\\major\\sample\\pp.txt");

        if(pp != null && pp.isFile() && pp.exists()){
            System.out.println("Current folder : " + pp.getParent());
            System.out.println("Parent folder : " + pp.getParentFile().getParent());
            System.out.println(new File(pp.getParentFile().getParent(), "lump.txt").exists());
        }
    }

    private static void whileIncrement() {
        int lineNum = 0;
        while(true){
            lineNum++;
            System.out.println(lineNum);
            if (lineNum == 10){
                break;
            }
        }
    }

    private static void mapDescendingOrder() {
        Map<String, ArrayList<String>> container = new HashMap<String, ArrayList<String>>();
        ArrayList<String> arr1 = new ArrayList<>();
        arr1.add("zip");
        arr1.add("2");

        ArrayList<String> arr2 = new ArrayList<>();
        arr2.add("text");
        arr2.add("10");

        ArrayList<String> arr3 = new ArrayList<>();
        arr3.add("jpg");
        arr3.add("500");

        container.put("zip file", arr1);
        container.put("text file", arr2);
        container.put("jpg file", arr3);

        System.out.println(container);

        //------------------------------------------------------

        ArrayList<Integer> sizes = new ArrayList<Integer>();
        ArrayList<ArrayList<String>> allArrays = new ArrayList<>();
        for(Map.Entry e : container.entrySet()){
            //System.out.println( "-> " + (ArrayList<String>)e.getValue());
            allArrays.add((ArrayList<String>)e.getValue());
        }

        //System.out.println(allArrays.size());

        for(ArrayList<String> arr : allArrays){
            sizes.add(Integer.parseInt(arr.get(1)));
        }

    }

    private static Object getBooleanCheck() {
        System.setProperty("isSomeProperty", "true");
        Boolean a = Boolean.getBoolean("isSomeProperty");
        Boolean b = Boolean.getBoolean("true");
        Boolean c = Boolean.getBoolean("TRUE");
        Boolean d = Boolean.getBoolean("false");
        Boolean e = Boolean.getBoolean(null);

        Boolean f = Boolean.valueOf("true");
        Boolean g = Boolean.valueOf("TRUE");
        Boolean h = Boolean.valueOf(null);
        Boolean i = Boolean.valueOf((String)null);
        Boolean j = Boolean.valueOf("false");

        for(Boolean val : new Boolean[]{a,b,c,d,e,f,g,h,i,j}){
            System.out.println(val);
        }

        System.out.println("-->>>>>");
        System.out.println((Integer) null);
        System.out.println("<<<<<<---");
    return true;
    }

    private static void isAllowed(){
        String[] allowedFileTypes = {".txt", ".sif", ".csv", ".xls", ".xlsx"};
        boolean isAllowed = false;
        String salaryFileName = "some.xml";
        for(String allowedType : allowedFileTypes) {
            if(salaryFileName.toLowerCase().endsWith(allowedType.toLowerCase())) {
                isAllowed = true;
            }
        }

        System.out.println("isallowed: " + isAllowed);
        System.out.println("is lllowed: " + Arrays.asList(allowedFileTypes).contains(salaryFileName.toLowerCase()));
    }

    private static void splitNull() {
        String s = "SUCCESS";
        String[] arr = s.split("\\|");
        System.out.println("length: " + arr.length);
        System.out.println("[0] -> " + arr[0]);
        System.out.println("[1] -> " + arr[1]);
        System.out.println(arr);
        System.out.println(arr.length);
        String val = arr.length > 1 ? arr[1] : "";
        System.out.println(arr[0] + " -> " + val);
    }

    private static void bigDecimalSetScale() {
        System.out.println("1) " + new BigDecimal("54.159").setScale(2,4));
        System.out.println("2) " + new BigDecimal("54.159").setScale(2, RoundingMode.CEILING));
        System.out.println("3) " + new BigDecimal("54.00001").setScale(3,4));
        System.out.println("4) " + new BigDecimal("54.09").setScale(3,4));
        System.out.println("5) " + new BigDecimal("54.099").setScale(3,4));
        System.out.println("6) " + new BigDecimal("54.0999").setScale(3,4));
        System.out.println("7) " + new BigDecimal("54.9999").setScale(3,4));
        System.out.println("8) " + new BigDecimal("545.123").setScale(3,4));
        System.out.println("9) " + new BigDecimal("545.129").setScale(3,4));
        System.out.println("10) " + new BigDecimal("548.1299").setScale(3,4));


        System.out.println("12) " + new BigDecimal("548.99999").setScale(3,3));
        System.out.println("13) " + new BigDecimal("548.99999").setScale(3,2));
        System.out.println("14) " + new BigDecimal("549.99999").setScale(3,4));
    }
}
