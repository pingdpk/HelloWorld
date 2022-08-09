package com.company.salaryup;

public enum TestEnum {
    DEEPAK(10);

    private int x;
    TestEnum(int x) {
        this.x = x;
    }

    public static Integer getValue(){
        return TestEnum.DEEPAK.x;
    }
}
