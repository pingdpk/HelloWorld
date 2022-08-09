package com.company.enums;

enum Foo {
    SUCCESS, FAILED,
    STATUSES(SUCCESS, FAILED);

    private final String success1, failed1;

    private Foo() {
        this.success1 = "";
        this.failed1 = "";
    }
    private Foo(String first, String second) {
        this.success1 = first; this.failed1 = second;
    }

    Foo(Foo success, Foo failed) {
        failed1 = failed.value();
        success1 = success.value();
    }

    String value(int x) {
        return (x == 0) ? success1 : failed1;
    }

    String value() {
        return "";
    }

    String value(String x) {
        return ("SUCCdESS".equals(x)) ? success1 : failed1;
    }
}