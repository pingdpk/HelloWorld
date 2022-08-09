package com.company;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleInfo;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //compareAmountz();
        //remarkz();
        //nullCheckEquals();
        //ArrayListRemove();
        //getCurrencyStringNoSymbolNoComma("–12,$3.1234,5678");
        //getCurrencyStringNoSymbolNoComma("-12,$3.1234,5678");
        // bigDecimalSetScale();
        //splittest();
        getMethodNameClassName();

    }

    private static void getMethodNameClassName() {
        //System.out.println(new Object(){}.getClass().getName());
        System.out.println(getCurrentMethodName());
    }

    private static void splittest() {
        System.out.println("Some error happened".split(null)[0]);
    }

    public static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    private static void bigDecimalSetScale() {
        System.out.println("1) " + new BigDecimal("54.01").setScale(3,4));
        System.out.println("2) " + new BigDecimal("54.001").setScale(3,4));
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

    private static void ArrayListRemove() {
        List<Object> letters = new ArrayList<>();
        letters.add("a");
        letters.add("b");
        letters.add("c");

        System.out.println("Before:");
        for(int i = 0; i < letters.size(); i++){
            System.out.println( i + ") " + letters.get(i));
            if(i == 2){
                letters.remove(i);
            }
        }

        System.out.println("After:");
        for(int i = 0; i < letters.size(); i++){
            System.out.println( i + ") " + letters.get(i));
            if(i == 2){
                letters.remove(i);
            }
        }

    }


    private static void nullCheckEquals() {
        Character k = null;
        System.out.println("something".equals(k));
        System.out.println(k.equals("something"));
    }

    private static void compareAmountz() {
        System.out.println(compareAmounts("10", "15"));       //------> left small, right big -->> 1
        System.out.println(compareAmounts("15", "10"));
        System.out.println(compareAmounts("-10", "15"));
        System.out.println(compareAmounts("15", "-10"));
        System.out.println(compareAmounts("10", "-15"));
        System.out.println(compareAmounts("15", "-10"));
        System.out.println(compareAmounts("10", "10"));

        System.out.println("--------------");
        System.out.println(compareAmounts("10", "25000"));      // not allowed
        System.out.println(compareAmounts("25001", "25000"));   // allowed (if not -1 raise error)
        System.out.println(compareAmounts("25000", "25000"));   // not allowed
        System.out.println("--------------");

        System.out.println(compareAmounts("10", "abcd"));
    }

    private static void remarkz() {
        String remarks = null;
        if(remarks == null || remarks.isEmpty()){
            System.out.println("ERROR>>>>" + remarks + "<<<<ERROR");
        }
    }

    public static int compareAmounts(String fromAmount, String toAmount) {
        BigDecimal fromAmountDec = new BigDecimal(fromAmount);
        BigDecimal toAmountDec = new BigDecimal(toAmount);
        return toAmountDec.compareTo(fromAmountDec);
    }

    private static String getCurrencyStringNoSymbolNoComma(String currencyString) {
        //String currencyString = this.toString();
        StringBuffer stringBuff = new StringBuffer(currencyString);
        //char dash = '\u2010';
        //char dash2 = '\u002D';
        //System.out.println(dash);
        //System.out.println((char) 45);

        Character[] dashes = new Character[]{45, 8211, '\u2010', '\u002D'}; //OR do replace all other characters to actual minus symbol (45)
        //replaceAll would be the best solution (as it may be used anywhere else as well)

        for (int charPos = stringBuff.length() - 1; charPos > -1; --charPos) {
            char currentChar = stringBuff.charAt(charPos);
            //System.out.println((int) currentChar);
            if (!Character.isDigit(currentChar) || currentChar == 44) {
                if (currentChar == 46) {
                    if (charPos == stringBuff.length() - 1) {
                        stringBuff.deleteCharAt(charPos);
                    }
                } else if (currentChar != 43 && !Arrays.asList(dashes).contains(currentChar)) {
                    stringBuff.deleteCharAt(charPos);
                }
            }
        }
        System.out.println(stringBuff.toString());
        return stringBuff.toString();
    }


}
