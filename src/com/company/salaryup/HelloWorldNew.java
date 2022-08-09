package com.company.salaryup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


enum FieldSizeBAH{
    beneficiary_account(35, 1200030),
    beneficiary_account_title(35, 1200031),
    beneficiary_address_1(35, 1200032),
    beneficiary_address_2(35, 1200033),
    beneficiary_address_3(35, 1200034),

    transaction_type_code(3, 1200035),
    credit_card_type(1, 1200036),
    ordering_customer_account(30, 1200037);

    int size = -1;
    int errCode = -1;

    FieldSizeBAH(int size, int errCode){
        this.size = size;
        this.errCode = errCode;
    }

    public static int getSize(String field){
        for(FieldSizeBAH f : values()) {
            if(f.toString().equals(field)) {
                return f.size;
            }
        }
        return -1;
    }

    public static int getErrCode(String field){
        for(FieldSizeBAH f : values()) {
            if(f.toString().equals(field)) {
                return f.errCode;
            }
        }
        return -1;
    }

    public static int getSize1(FieldSizeBAH field){
        return field.size;
    }

}



public class HelloWorldNew {
    public static void main(String[] args) {
        //dateUtils();
        //simpleDF();
        //xyz();
        //substring();
        //filedelete();
        //enumTest();
        //hashmaptest();
        //regexSplCharRemarsks();
        emptyLineTest();
    }

    private static void emptyLineTest() {
        String[] emtLne = {"", " ", "    ", null, "         "};
        System.out.println((Arrays.stream(emtLne).map(val -> (val != null) ? val.trim() : "").collect(Collectors.joining())).isEmpty());
    }

    private static void regexSplCharRemarsks() {
        HashMap<Integer, String> x = new HashMap<>();
                x.put(1, "");
                x.put(2, "^^^&^");
                x.put(3, "(),.-/?':+");
                x.put(4, "!\"#$%&*/;<=>@[\\]^_`{|}~");
                x.put(5, "xxccdd");
                x.put(6, "بيسشسيبيسشيب");
                x.put(7, "X");
                x.put(8, "99-89");
        for (Map.Entry y : x.entrySet()){
            System.out.println(y.getKey() + " -> " + ((String)(y.getValue())).matches("^((?![!\\\"#$%&\\\\*;<=>@\\[\\]^_`{|}~]).)*$"));
        }
    }

    private static void hashmaptest() {
        HashMap<String, Object> x = new HashMap<>();
        x.put("i5", "10");

        int i = (String)x.get("i") == null ? 0 : 1;

        System.out.println(i);
    }

    private static void enumTest() {
        System.out.println(FieldSizeBAH.beneficiary_account.size);
        System.out.println(FieldSizeBAH.beneficiary_account_title.errCode);
    }

    private static void filedelete() {
        File old = new File("record.txt");
        //old.delete();

        try{
            FileReader c = new FileReader("record.txt");
            BufferedReader d = new BufferedReader(c);
            String curr = null;
            while((curr = d.readLine()) != null){
                System.out.println(curr);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void substring() {
        for (String x : new String[] {"123", "1234", "12345678", "abcd1234", "1234", "1234"})
        System.out.println(Math.max(5, x.length()));
    }

    private static void xyz() {
        String recordData = "10,NULL"+null;
        List<String> errors = Stream.of(recordData.split(",")).map(err -> err.trim()).collect(Collectors.toList());
        System.out.println(errors.stream().collect(Collectors.joining("#")));

    }

    private static void simpleDF() {
        SimpleDateFormat valueDatePattern = new SimpleDateFormat("yyyyMMddHHmmss");//DateUtil.YYYYMMDDHHMMSS); yyyyMMddHHmmss
        SimpleDateFormat salaryDatePattern = new SimpleDateFormat("yyyyMMdd"); //DateUtil.YYYYMMDD);
        SimpleDateFormat salaryTimePattern = new SimpleDateFormat("HHmm"); // DateUtil.HHMM);
        Date actualDateTime = null;
        Date salaryDate = null;
        String salaryTime = null;
        String valueDateWithoutTime = null;
        try {
            actualDateTime = valueDatePattern.parse("20220308050000");
            valueDateWithoutTime = salaryDatePattern.format(actualDateTime);
            salaryDate = salaryDatePattern.parse(valueDateWithoutTime);
            salaryTime = salaryTimePattern.format(actualDateTime);

        } catch (ParseException e1) {
            //logger.error("salary_upload : exception...................", e1);
            System.out.println(e1);
        }

        System.out.println(actualDateTime);
        System.out.println(valueDateWithoutTime);
        System.out.println(salaryDate);
        System.out.println(salaryTime);
        System.out.println(new java.sql.Date(salaryDate.getTime()));

    }

    private static void dateUtils() {
        DateUtil dateUtilTime = new DateUtil("yyyyMMddHHmmss");
        Date x = dateUtilTime.parseToDate("20220305050000");
        System.out.println(x.getHours() + ":" + x.getMinutes());
    }
}
