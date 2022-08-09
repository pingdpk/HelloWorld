package com.company.salaryup;

public enum SalaryUploadMQRespStatusBAH {
    SUCCESS(0), FAILURE(1);

    private int intStatus;

    SalaryUploadMQRespStatusBAH(int status){
        this.intStatus = status;
    }

    public static SalaryUploadMQRespStatusBAH getStatusText(int statusNum){
        for(SalaryUploadMQRespStatusBAH s : values()){
            if(s.intStatus == statusNum){
                return s;
            }
        }
        return SalaryUploadMQRespStatusBAH.getStatusText(1);
    }

    public static int getStatusNumber(String statusText){
        //null check
        for(SalaryUploadMQRespStatusBAH s : values()){
            if(s.equals(statusText)){
                return s.intStatus;
            }
        }
        return FAILURE.intStatus;
    }
}