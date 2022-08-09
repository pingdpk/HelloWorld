package com.company.salaryup;

public enum SalaryUploadMQRespStatusUAE {
    SUCCESS, FAILED;

    public static String getStatusText(String status){
        for(SalaryUploadMQRespStatusUAE s : values()){
            if(s.name().equalsIgnoreCase(status)){
                return s.name();
            }
        }
        return SalaryUploadMQRespStatusUAE.FAILED.name();
    }
}