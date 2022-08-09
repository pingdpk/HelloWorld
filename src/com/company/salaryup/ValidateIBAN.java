package com.company.salaryup;

public class ValidateIBAN {
    public static void main(String[] args) {
        String[] ibans = {  "AE080230000001002501839", "AE120930000242206358001", "AE820930010143949217001", "BH67BMAG00001299123456", //valid
                            "PPABCDEFGHIJKLMNOPQRSTU", "1234567890123456789012", "pk65BAHL0000001123456702", "BH667BMAG00001299123456", "BH54ABCM00001234567890", "AE0703312345678901234568", "AE07NWBK234567890123456"}; //invalid
        int i = 1;
        for(String iban : ibans){
            System.out.println( "ValidateIBAN ------>>>>> " + iban + "-->>"  + validateIBAN(iban));
        }

    }

    public static boolean validateIBAN(String iban) {
        int i = 0;
        int k = 0;
        String ibanStr = null;

        if(isNullOrEmpty(iban)) {
            return Boolean.FALSE;
        }

        if (iban.trim().length() < 5) {
            return Boolean.FALSE;
        }

        ibanStr = iban.trim().substring(4) + iban.trim().substring(0, 4);

        if(isNullOrEmpty(ibanStr)) {
            return Boolean.FALSE;
        }

        StringBuffer sb = new StringBuffer();
        String ibanParseStr = "";

        for (i = 0; i < ibanStr.length(); ++i) {
            char c = ibanStr.charAt(i);
            if (('0' <= c) && (c <= '9')) {

                if ((i == ibanStr.length() - 4) || (i == ibanStr.length() - 3)) {
                    return Boolean.FALSE;
                }

                k = c - '0';
            } else if (('A' <= c) && (c <= 'Z')) {
                if ((i == ibanStr.length() - 2) || (i == ibanStr.length() - 1)) {
                    return Boolean.FALSE;
                }
                k = c - '7';
            } else {
                return Boolean.FALSE;
            }
            sb.append(String.valueOf(k));
        }

        ibanParseStr = sb.toString();
        int a = 1;
        int r = 0;
        for (i = ibanParseStr.length() - 1; i >= 0; --i) {
            char c = ibanParseStr.charAt(i);
            k = c - '0';
            r = (r + k * a) % 97;
            a = a * 10 % 97;
        }
        return (r == 1);
    }

    public static boolean validateIBAN__1(String iban, int j) {

        System.out.println("\nIBAN -> " + iban);
        System.out.println("Iban length: " + iban.trim().length());

        int i = 0;
        int k = 0;

        String ibanStr = null;

        if (iban.trim().length() < 5) {
            return false;
        }

        ibanStr = iban.trim().substring(4) + iban.trim().substring(0, 4);

        //System.out.println("first 4 letters of iban kept at last | ibanStr -> " + ibanStr);

        StringBuffer sb = new StringBuffer();
        String ibanParseStr = "";

        for (i = 0; i < ibanStr.length(); ++i) {
            char c = ibanStr.charAt(i);
            if (('0' <= c) && (c <= '9')) {
                //System.out.print(c);
                if ((i == ibanStr.length() - 4) || (i == ibanStr.length() - 3)) {
                    return false;
                }

                k = c - '0';
            } else if (('A' <= c) && (c <= 'Z')) {
                if ((i == ibanStr.length() - 2) || (i == ibanStr.length() - 1)) {
                    return false;
                }
                k = c - '7';
            } else {
                return false;
            }
            sb.append(String.valueOf(k));
        }

        ibanParseStr = sb.toString();
        int a = 1;
        int r = 0;
        for (i = ibanParseStr.length() - 1; i >= 0; --i) {
            char c = ibanParseStr.charAt(i);
            k = c - '0';
            r = (r + k * a) % 97;
            a = a * 10 % 97;
        }
        return (r == 1);
    }



    public static boolean isNullOrEmpty(String str, boolean forceCheck) {
        if(forceCheck) {
            return (str == null) || (str != null && str.trim().length() == 0);
        }
        return (str == null) || (str != null && str.length() == 0);
    }

    /**
     * This will check whether the given string is null &
     * 			will check empty only after doing trim() <br> <br>
     *
     * If you do not want to trim(), use,<br>
     * 		com.sap.banking.mdsap.util.StringHelper.isNullOrEmpty(String str, boolean forceCheck)
     * 		with forceCheck as false
     * @param str
     * @return boolean
     */
    public static boolean isNullOrEmpty(String str) {
        return isNullOrEmpty(str, Boolean.TRUE);
    }


}



