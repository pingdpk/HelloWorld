package com.company.enums;

public interface Nested {
    enum Header implements Nested {
        COMPLETED("completed!"), FAILED("failed!");
        private String x;
        Header(String x){
            this.x = x;
        }

        public String getX(){
            return this.x;
        }

    }

    enum Body implements Nested {
        DONE, FAILED;
    }
}
