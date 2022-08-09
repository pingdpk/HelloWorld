package com.company.example.uniq;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Student {
    private String name;
    private int phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}

public class Unique {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        /**
         * Set
         */
        Student student1 = new Student();
        student1.setName("A");
        student1.setPhone(123);

        Student student2 = new Student();
        student2.setName("B");
        student2.setPhone(456);

        Student student3 = new Student();
        student3.setName("C");
        student3.setPhone(123);

        students.add(student1);
        students.add(student2);
        students.add(student3);


    }
}
