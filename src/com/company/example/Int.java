package com.company.example;

public class Int {
    public static void main(String[] args) {
        //imm
        String s1 = new String("old old");
        String s2 = "older older older";
        System.out.println(s1 + " | " + s2);
        String s11 = s1.replaceAll("old", "new");
        String s12 = s2.replaceAll("old", "new");
        System.out.println(s11 + " | " + s12);
        s1 = new String(s1.replaceAll("old", "new"));
        s2 = new String(s2.replaceAll("old", "new"));
        System.out.println(s1 + " | " + s2);


    }
}
