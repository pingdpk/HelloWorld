package com.company.salaryup;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class SalaryUpload {
    public static void main(String[] args) {
        parseBahOnUsPayroll(new File("Z:\\shared_vm\\textfiles\\090990000003444190614092558.SIF"));
    }

    public static void parseBahOnUsPayroll(File uploadedFile) {
        HashMap<String, Integer> errors = new HashMap<>();

        validateCommonThings(uploadedFile, errors); //todo: actual args are uploadedFile, errors, mainBean, subBean if requ

        goThroughTheFileAndPopulateBean(uploadedFile, errors);

        //validate here!!!!
        //validateEachValues

        if(errors.size()>0){
            for (Map.Entry<String, Integer> entry : errors.entrySet()){
                System.out.println("\nproblem: " + entry.getKey() + " at line# " + entry.getValue());
            }
        }



        //No of SCR records in the file only one -> done
        //No of SCR records in the file is mandatory -> done
        //No of EDR records in the file at least one -> done
        //No of EDR records in the file mandatory -> done (same if condition)
        //Salary month year of SCR = Start month year EDR
        //Pay start date format (yyyy-MM-dd)
        //Pay start date !< pay end date

        //EDR --------------------------
        //Emp a/c with agent bank cant be empty
        //emp a/c with agent can be either IBAN or card number
        //Date formats (allll)

    }

    private static void goThroughTheFileAndPopulateBean(File uploadedFile, HashMap<String, Integer> errors) {
        try {
            FileInputStream fis = new FileInputStream(uploadedFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            String line = null;
            int lineNumber = 0;
            int numberOfSCRs = 0;
            int scrLineNumber = 0;
            int numberOfEDRs = 0;
            int edrLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(line);

                String[] lineContents = line.split(",");

                if(lineContents[0].equals("EDR")){ //TODO: Constants
                    //numberOfEDRs++;
                    //edrLineNumber = lineNumber;
                    System.out.println("EDR..." + lineNumber);
                    //Do populate Record
                    //validateEDR(lineContents, lineNumber, errors); //move to after populate
                }
                if(lineContents[0].equals("EVP")) { //TODO: Constants
                    //Do populate Record
                    //validateEVP(lineContents, lineNumber, errors); //move to after populate
                }
                if(lineContents[0].equals("SCR")) { //TODO: Constants
                    //numberOfSCRs++;
                    //scrLineNumber = lineNumber;
                    //Do populate Record
                    //validateSCR(lineContents, errors); //move to after populate
                }


                /**
                 * Do common line validations
                 */



                /**
                 * Populate beans
                 */
                populateSalaryRecord(uploadedFile, errors);
                populateSalaryUpload(uploadedFile, errors);

            }

            /*
            if(numberOfSCRs > 1){
                errors.put("more_than_one_scr_found", scrLineNumber);
            }else if(numberOfSCRs == 0){
                errors.put("no_scr_found", 0);
            }

            if(numberOfEDRs == 0){
                errors.put("no_edr_found", 0);
            }*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private static void validateCommonThings(File uploadedFile, HashMap<String, Integer> errors) {
        System.out.println(uploadedFile.getAbsoluteFile());

        String fileName = uploadedFile.getName();

        /**
         * As in old parser
         * TODO: Need to confirm: what for this
         * //Same file name (more than one file with same file name) - as in doc
         * //if file name already done? (Need to check from where)? - as in doc
         */
        if (fileName != null) {
            while (fileName.indexOf("\\") >= 0) {
                fileName = fileName.replace('\\', '/');
            }
            int lastIdx = fileName.lastIndexOf("/");
            if (lastIdx >= 0) {
                String fileNameValue = fileName.substring(lastIdx + 1);
                //DebugLog.log("SIF fileName :"+fileNameValue);
                //salaryUpload.setSIFFileName(fileName);
            }
        }

        /**
         * Validate file extension format (check SIF format)
         */
        String extension = "";
        if (fileName.lastIndexOf('.') > 0) {
            extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        }
        if (!"sif".equalsIgnoreCase(extension)) {
            errors.put("file_extension_not_valid", 0);  //TODO: Should not hard-code (Put salary_upload_err001 and map it)
        }

        /**
         * Validate file upload size
         * TODO: get limit from configuration???
         */
        long fileSizeInBytes = uploadedFile.length();
        if (fileSizeInBytes >= 1048576) { //TODO: get from config?
            errors.put("file_size_is_more_than_expected", 0);
        }
    }

    private static void validateEDR(String[] linContents, int lineNumber, HashMap<String, Integer> errors) throws ArrayIndexOutOfBoundsException{

        String empAccWithAgentBank = linContents[3];
        String payStartDate = linContents[4];
        //4.	Employee A/c with Agent Bank cannot be empty
        if(empAccWithAgentBank.isEmpty()){
            errors.put("emp_acc_with_agent_bank_cannot_be_empty", lineNumber);
        }


        //5.	Employee A/c with Agent can be either IBAN (23 digit) or card account number (16 digit). Card account number must be padded with zeros in front of the card number to form 23 digit.
        //TODO: com.sap.banking.mdsap.bpw.master.CustomWirePayeeProcessor#validateIBAN(java.lang.String) -> use this?
        if(empAccWithAgentBank.substring(0,1).matches(".*[a-z].*") && empAccWithAgentBank.length() != 23){//if IBAN EVP_point#4
            errors.put("emp_acc_with_agent_bank_IBAN_should_contain_23_char", lineNumber);
        }else if(empAccWithAgentBank.matches("[0-9]+") && empAccWithAgentBank.length() != 23){
            errors.put("emp_acc_with_agent_bank_CARD_should_contain_23_char", lineNumber); //do prompt actual error (EVP_point#5 as in doc) todo: do we need to add 00000s on the LHS of card? or prompt user? - if so check 16
        }

        //13.   Pay start date  must be in yyyy-MM-dd format
        SimpleDateFormatValidator simpleDateFormatValidator = new SimpleDateFormatValidator(new SimpleDateFormat("yyyy-MM-dd"));
        boolean isPayStartDateValid = simpleDateFormatValidator.isValid(payStartDate);
        if(!isPayStartDateValid){
            errors.put("pay_start_date_not_valid_format", lineNumber);
        }

    }

    private static void validateEVP(String[] linContents, int lineNumber, HashMap<String, Integer> errors) throws ArrayIndexOutOfBoundsException{

    }

    private static void validateSCR(String[] linContents, HashMap<String, Integer> errors) {
    }

    private static void populateSalaryRecord(File uploadedFile, HashMap<String, Integer> errors) {
    }

    private static void populateSalaryUpload(File uploadedFile, HashMap<String, Integer> errors) {
    }





    public static class SimpleDateFormatValidator {
        private SimpleDateFormat SimpleDateFormat;

        public SimpleDateFormatValidator(SimpleDateFormat simpleDateFormat) {
            this.SimpleDateFormat = simpleDateFormat;
        }

        public boolean isValid(String dateStr) {
            try {
                this.SimpleDateFormat.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Could not parse the date");
                return false;
            }
            return true;
        }
    }


}
