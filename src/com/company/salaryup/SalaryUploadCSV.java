package com.company.salaryup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class SalaryUploadCSV {
    public static final String BHR_FILE_FIRST_COLUMN_HEADING_REGX = "[b|B]eneficiary\\s?[a|A]ccount";
    public static void main(String[] args) {

        //goThroughTheFileAndPopulateBeanBAH(new File("Z:\\shared_vm\\corporate\\salaryupload_testing_files\\sample_BAH_SalaryUpload_csv.csv")); //

        //sbTest();

        //utilDateToSqlDate();

        //simpleDateFormatValidator();

        enumTest();

        //isEven();

    }

    private static void isEven() {
        System.out.println(((10 & 1)));
    }

    private static void enumTest() {
        //System.out.println(SalaryUploadStatus.ACTIVE.theValue());
        //System.out.println(Foo.STATUSES.value(0));
        //System.out.println(Foo.STATUSES.S);
        //System.out.println(SalaryUploadMQRespStatusBAH.getStatusText(0));
        //System.out.println(SalaryUploadMQRespStatusBAH.SUCCESS);
        //System.out.println(SalaryUploadMQRespStatusBAH.getStatusNumber("FAILURE"));

        /*System.out.println(SalaryUploadMQListenerRespStatusBAH.getHeaderStatusTextVal(1));
        System.out.println(SalaryUploadMQListenerRespStatusBAH.getHeaderStatusTextVal(3));
        System.out.println(SalaryUploadMQListenerRespStatusBAH.getHeaderStatusNumber ("INTERIM"));
        System.out.println(SalaryUploadMQListenerRespStatusBAH.getBodyStatusNumber ("RJCT"));
        System.out.println(SalaryUploadMQListenerRespStatusBAH.getBodyStatusNumber ("FAILURE"));
        System.out.println(SalaryUploadMQListenerRespStatusBAH.getBodyStatusTextVal (9));
        System.out.println(SalaryUploadMQListenerRespStatusBAH.getBodyStatusNumber ("INTERIM"));*/

        System.out.println(TestEnum.getValue());
    }





    private static void simpleDF() throws ParseException {
        SimpleDateFormat sdf = new DateUtil("yyMMdd").getSimpleDateFormat();
        sdf.parse("210304");
    }

    private static void simpleDateFormatValidator() {
        DateUtil dateUtil = new DateUtil("");//"yyMMddxx"
        boolean x = dateUtil.isValidDate("210327");
        Date y = dateUtil.parseToDate("230707");

        System.out.println(y);

        System.out.println(dateUtil.getSimpleDateFormat().format(y));

    }

    private static void utilDateToSqlDate() {
        java.util.Date uDate = new java.util.Date();

        System.out.println("utilDate: " + uDate);
        System.out.println("sqlDate with getTime: " + new java.sql.Date(uDate.getTime()));
        System.out.println("sqlTime with getTime: " + new java.sql.Time(uDate.getTime()));
    }

    private static void sbTest() {
        StringBuffer sb = new StringBuffer();
        sb.append("something");
        sb.append(System.lineSeparator());
        sb.append(0);
        sb.append(System.lineSeparator());
        sb.append('c');
        sb.append(System.lineSeparator());
        sb.append(true);
        sb.append(System.lineSeparator());
        sb.append(10.4f);
        sb.append(System.lineSeparator());
        sb.append("dlsajlsjfd fsfdsfja;fkjsf");

        System.out.println(sb.toString());

    }


    private static void goThroughTheFileAndPopulateBeanBAH(File uploadedFile) throws ArrayIndexOutOfBoundsException{ //TODO: Either throw or check for each String[] | try-catch used.
         //("salary_upload :: inside goThroughTheFileAndPopulateBeanBAH()"); //TODO: Remove this line
        try {
            FileInputStream fis = new FileInputStream(uploadedFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            /*FilteredList paymentRecords = new FilteredList();
            BigDecimal totalInstructedAmount = new BigDecimal(0);*/

            String line = null;
            int lineNumber = 0;
            int headerRowNumber = 0;
            int numberOfSCRs = 0;
            int scrLineNumber = 0;
            int numberOfEDRs = 0;
            int edrLineNumber = 0;
            boolean foundHeading = false;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                //System.out.println("salary_upload: reading BAH file : Line -> " + line);

                //SalaryRecord record = new SalaryRecord();
                String[] lineContents = line.split(",", -1);


                /*
                 * Exclude header line and start from next line.
                 */
                if(lineContents[0] != null && lineContents[0].matches(BHR_FILE_FIRST_COLUMN_HEADING_REGX)) { //heading row started
                    headerRowNumber = lineNumber;
                    foundHeading = true;
                    System.out.println("salary_upload: [a]linecontentmatch : headerRowNumber" + headerRowNumber + " | foundHeading: " + foundHeading);
                    continue;
                }

                if(!foundHeading){
                    System.out.println("salary_upload: [b]linecontentmatch : headerRowNumber" + headerRowNumber + " | foundHeading: " + foundHeading);
                    continue;
                }else if(lineContents != null && Arrays.stream(lineContents).collect(Collectors.joining()).isEmpty()){
                    System.out.println("No content @ Line: " + lineNumber);
                    continue;
                }


                System.out.println("salary_upload:: BAH reading...line:#" + lineNumber + " | lineContents.length : " + Arrays.stream(lineContents).collect(Collectors.joining())); //TODO: Remove this line

                //TODO: Remove
                int y = 0;
                for(String x : lineContents) {
                    System.out.println("salary_upload BAH: column[" + y + "] -> " + x);
                    y++;
                }

                System.out.println("Total lines: " + lineContents.length);

                /*
                 * set details
                 * starting from zero
                 */
                String beneficiaryAccount = doMandatoryCheck(lineContents[0], lineNumber); //TODO confirm M/O/c means mandatory or optional
                String beneficiaryAccountTitle = doMandatoryCheck(lineContents[1], lineNumber); //M
                String beneficiaryAdress1 = isNullOrEmpty(lineContents[2]) ? null : lineContents[2]; //O
                String beneficiaryAdress2 = isNullOrEmpty(lineContents[3]) ? null : lineContents[3]; //O
                String beneficiaryAdress3 = isNullOrEmpty(lineContents[4]) ? null : lineContents[4]; //O
                String transactionTypeCode = isNullOrEmpty(lineContents[5]) ? null : lineContents[5]; //O
                String creditCardType = isNullOrEmpty(lineContents[6]) ? null : lineContents[6]; //O
                String orderingCustomerAccount = doMandatoryCheck(lineContents[7], lineNumber);
                String orderingCustomerName = doMandatoryCheck(lineContents[8], lineNumber);
                String orderingCustomerAddress1 = isNullOrEmpty(lineContents[9]) ? null : lineContents[9]; //O
                String orderingCustomerAddress2 = isNullOrEmpty(lineContents[10]) ? null : lineContents[10]; //O
                String orderingCustomerAddress3 = isNullOrEmpty(lineContents[11]) ? null : lineContents[11]; //O
                String remittanceInfo1 = doMandatoryCheck(lineContents[12], lineNumber);
                String remittanceInfo2 = isNullOrEmpty(lineContents[13]) ? null : lineContents[13]; //O
                String remittanceInfo3 = isNullOrEmpty(lineContents[14]) ? null : lineContents[14]; //O
                String remittanceInfo4 = isNullOrEmpty(lineContents[15]) ? null : lineContents[15]; //O
                String valueDate = doMandatoryCheck(lineContents[16], lineNumber); //Date
                String currencyCode = isNullOrEmpty(lineContents[17]) ? null : lineContents[17]; //O
                String instructedAmount = doMandatoryCheck(lineContents[18], lineNumber); //BigDecimal
                String detailsOfCharges = isNullOrEmpty(lineContents[17]) ? null : lineContents[19]; //O
                String sendingInstitutionRef = doMandatoryCheck(lineContents[20], lineNumber);
                String regulatoryReporting1 = isNullOrEmpty(lineContents[21]) ? null : lineContents[21]; //O
                String regulatoryReporting2 = isNullOrEmpty(lineContents[22]) ? null : lineContents[22]; //O
                String regulatoryReporting3 = isNullOrEmpty(lineContents[23]) ? null : lineContents[23]; //O
                String senderToReceiver1 = isNullOrEmpty(lineContents[24]) ? null : lineContents[24]; //O
                String senderToReceiver2 = isNullOrEmpty(lineContents[25]) ? null : lineContents[25]; //O
                String senderToReceiver3 = isNullOrEmpty(lineContents[26]) ? null : lineContents[26]; //O
                String senderToReceiver4 = isNullOrEmpty(lineContents[27]) ? null : lineContents[27]; //O
                String senderToReceiver5 = isNullOrEmpty(lineContents[28]) ? null : lineContents[28]; //O
/*
                //set all values to record
                record.setDebitAcccount(beneficiaryAccount);
                record.setDebitAcccountName(beneficiaryAccountTitle);
                record.setBeneficiaryAddress1(beneficiaryAdress1);
                record.setBeneficiaryAddress2(beneficiaryAdress2);
                record.setBeneficiaryAddress3(beneficiaryAdress3);
                record.setTransactionTypeCode(transactionTypeCode);
                record.setCreditCardType(creditCardType);
                record.setOrderingCustomerAccount(orderingCustomerAccount);
                record.setOrderingCustomerName(orderingCustomerName);
                record.setOrderingCustomerAddress1(orderingCustomerAddress1);
                record.setOrderingCustomerAddress2(orderingCustomerAddress2);
                record.setOrderingCustomerAddress3(orderingCustomerAddress3);
                record.setRemittanceInfo1(remittanceInfo1);
                record.setRemittanceInfo2(remittanceInfo2);
                record.setRemittanceInfo3(remittanceInfo3);
                record.setRemittanceInfo4(remittanceInfo4);

                //TODO: Make use of com.sap.banking.mdsap.salaryuploadconfig.util.SimpleDateFormatValidator
                SimpleDateFormatValidator dateValidator = new SimpleDateFormatValidator(new SimpleDateFormat("YYMMDD")); //TODO: Confirm YYMMDD or yymmdd
                if(dateValidator.isValid(valueDate)) {
                    record.setValueDate(dateValidator.parseToDate(valueDate));
                }else {
                    updateErrorListWithDescription("Error120000", lineNumber, uploadErrorsList); //TODO: add a new error as "Date should be given in YYMMDD format"
                    logger.error("Exception happened while parsing the given date");
                }


                record.setCurrencyCode(currencyCode);

                //TODO: Confirm cellValue is a valid number/digit/double
                try {
                    Currency currency = new Currency(instructedAmount, AffiliateConstants.BHR_BASE_CURRENCY, LocaleUtil.getDefaultLocale());
                    record.setInstructedAmount(currency.getAmountValue());
                    totalInstructedAmount.add(currency.getAmountValue());
                } catch (Exception e) {
                    updateErrorListWithDescription("Error120000", lineNumber, uploadErrorsList); //TODO: add a new error as "Amount should be given in number format"
                    logger.error("Exception happened while parsing the given amount", e);
                }

                record.setDetailsOfCharges(detailsOfCharges);
                record.setSendingInstitutionReference(sendingInstitutionRef);
                record.setRegulatoryReporting1(regulatoryReporting1);
                record.setRegulatoryReporting2(regulatoryReporting2);
                record.setRegulatoryReporting3(regulatoryReporting3);
                record.setSenderToReceiver1(senderToReceiver1);
                record.setSenderToReceiver2(senderToReceiver2);
                record.setSenderToReceiver3(senderToReceiver3);
                record.setSenderToReceiver4(senderToReceiver4);
                record.setSenderToReceiver5(senderToReceiver5);*/

            }
/*

            salaryUpload.setPaymentRecords(paymentRecords); //paymentRecords should contain EDR and EVP records
            salaryUpload.setFileName(uploadedFile.getName());
            salaryUpload.setFileType(SalaryUploadConstants.FILE_EXTENSION_SIF); //TODO: Confirm fileType is extension
*/


        }catch (Exception e){
            //updateErrorListWithDescription("Error120000", 0, uploadErrorsList); //TODO: add a new error
            System.out.println("salary_upload : Some error happened while iterating the file : Error120000 is thrown - BAH : " + e);
        }
    }

    private static boolean isNullOrEmpty(String lineContent) {
        return lineContent == null || (lineContent != null && lineContent.isEmpty());
    }

    private static String doMandatoryCheck(String lineContent, int lineNumber) {
        if(isNullOrEmpty(lineContent)){
            //TODO: updateErrorListWithDescription("Error120000", lineNumber, uploadErrorsList); //TODO: add a new error "Is mandatory"
            return "NO_VALUE_FOUND";
        }
        return lineContent;
    }


}
