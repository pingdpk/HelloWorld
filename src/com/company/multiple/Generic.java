package com.company.multiple;

public class Generic {
    public static void main(String[] args) {
        Adaptor adaptor;
        adaptor = new Point1();
        System.out.println(adaptor.connectA());

//        int x = p1.connectB();
//        System.out.println(p1.connectA());
//        System.out.println(p2.connectA());
//
//        System.out.println(Integer.toString(x));
//        System.out.println(p2.connectB());
    }
}

interface Adaptor<T, K> {
    T connectA();
    K connectB();
}

class Point1 implements Adaptor<String, Integer>{
   @Override
    public String connectA() {
        return "A connected";
    }

    @Override
    public Integer connectB() {
        return 2;
    }
}

class Point2 implements Adaptor<Integer, String>{
    @Override
    public Integer connectA() {
        return 1;
    }
    @Override
    public String connectB() {
        return "B connected";
    }
}