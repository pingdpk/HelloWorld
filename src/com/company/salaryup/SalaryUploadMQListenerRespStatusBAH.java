package com.company.salaryup;

public enum SalaryUploadMQListenerRespStatusBAH {
    SALARY_UPLOAD_FAIL("HEADER","FAIL", 1),
    SALARY_UPLOAD_INTERIM("HEADER", "INTERIM", 2),
    SALARY_UPLOAD_FINAL("HEADER", "FINAL", 3),

    SALARY_RECORD_FAIL("BODY", "FAIL", 1), //TODO: In the document, for the transaction status is "01", "02",..etc
    SALARY_RECORD_OK("BODY", "OK", 2), //TODO: In the document, for the transaction status is "01", "02",..etc
    SALARY_RECORD_ACK("BODY", "ACK", 3), //TODO: In the document, for the transaction status is "01", "02",..etc
    SALARY_RECORD_NACK("BODY", "NACK", 4),
    SALARY_RECORD_SUCCESS("BODY", "SUCCESS", 5),
    SALARY_RECORD_FAILURE("BODY", "FAILURE", 6),
    SALARY_RECORD_ASCP("BODY", "ASCP", 7),
    SALARY_RECORD_RJCT("BODY", "RJCT", 8);

    private String type;
    private String status;
    private int statusNum;

    SalaryUploadMQListenerRespStatusBAH(String type, String status, int statusNum){
        this.type = type;
        this.status = status;
        this.statusNum = statusNum;
    }

    public static String getHeaderStatusTextVal(int statusNum){
        for(SalaryUploadMQListenerRespStatusBAH s : values()){
            if(s.statusNum == statusNum &&  "HEADER".equals(s.type)){
                return s.status;
            }
        }
        throw new IllegalArgumentException("wrong");
    }

    public static int getHeaderStatusNumber(String statusText){
        //null check
        for(SalaryUploadMQListenerRespStatusBAH s : values()){
            //System.out.println(s.status + " - " + s.statusNum + " - " + s.type);
            if(s.status.equals(statusText) &&  "HEADER".equals(s.type)){
                return s.statusNum;
            }
        }
        throw new IllegalArgumentException("wrong");
    }

    public static String getBodyStatusTextVal(int statusNum){
        for(SalaryUploadMQListenerRespStatusBAH s : values()){
            if(s.statusNum == statusNum &&  "BODY".equals(s.type)){
                return s.status;
            }
        }
        throw new IllegalArgumentException("wrong");
    }

    public static int getBodyStatusNumber(String statusText){
        //null check
        for(SalaryUploadMQListenerRespStatusBAH s : values()){
            if(s.status.equals(statusText) &&  "BODY".equals(s.type)){
                return s.statusNum;
            }
        }
        throw new IllegalArgumentException("wrong");
    }
}