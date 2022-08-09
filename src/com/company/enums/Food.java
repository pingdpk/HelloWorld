package com.company.enums;

public enum Food {
    SUCCESS, FAILURE;

    public static Food getStatus(int x){
        if(x == 0){
            return SUCCESS;
        }else if(x == 1){
            return FAILURE;
        }
        return FAILURE;
    }
}
