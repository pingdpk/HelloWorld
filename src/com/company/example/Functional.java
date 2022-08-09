package com.company.example;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Functional{
    public static void main(String[] args) {
        Functional anon = new Functional();
        anon.hello();
    }
    interface Anonimous{
        void printMe();
        long calculate();
    }
    public void hello(){
                Anonimous x = new Anonimous(){
                    String y = "alphalpha";
                    @Override
                    public void printMe(){
                        System.out.println(y);
                    }
                    @Override
                    public long calculate(){
                        long x = y.chars().filter(i -> i == 'a').count();
                        System.out.println(x);
                        return x;
                    }
            };
        Supplier calculate = x::calculate;
        calculate.get();

    }
}
