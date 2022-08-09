package com.company.example;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Foo {
    public static void main(String[] args) {
//        Vehicle vehicle = new Vehicle();
//        Car newCar = vehicle.getCar();
//        newCar.setWheelCount(3);
//        System.out.println(vehicle.getCar().getWheelCount());
//        Car anotherCar = vehicle.getCar();
//        vehicle.setCar(anotherCar);
//        vehicle.getCar().setWheelCount(4);
//        System.out.println(vehicle.getCar().getWheelCount());
//        Car car1 = new Car();
//        vehicle.setCar(car1);
//
//        System.out.println(vehicle.getCar().getWheelCount());
        //dateTimeCompare();
        //addToExistingList();

        //System.out.println(isInteger("123.8"));

        //hashmapPrint();

        streamAll();
    }

    private static void streamAll() {
        HashMap params = new HashMap();
        params.put("1", "one");
        params.put(2, "two");
        params.put("3", 3);
        params.put(4, 4);
        params.put(5, null);
        params.put(5, null);
        params.put(5, null);
        params.put(null, 6);


        System.out.println("Stream -> " +
                params.entrySet().stream().filter(x -> ((Map.Entry)x).getKey()!=null).map(x->((Map.Entry)x).getKey())
                );

    }

    private static void hashmapPrint() {
        HashMap params = new HashMap();
        params.put("1", "one");
        params.put(2, "two");
        params.put("3", 3);
        params.put(4, 4);
        params.put(5, null);
        params.put(5, null);
        params.put(5, null);
        params.put(null, 6);

        System.out.println(Collections.singleton(params));
        //params.forEach((k,v) -> System.out.println(k + "->" + v ));
    }

    public static Integer tryParseInt(String text) {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException var2) {
            return null;
        }
    }

    public static boolean isInteger(String text) {
        return tryParseInt(text) != null;
    }

    private static void addToExistingList() {
        List<String> existingList = new ArrayList<>();
        existingList.add("existingVal1");
        existingList.add("existingVal2");
        existingList.add("existingVal3");

        List<String> newList = new ArrayList<String>();
        newList.add(null);
        newList.add("");
        newList.add("newVal3");

        existingList.addAll(newList);
        //existingList = null;
//        for(String val : existingList){
//            System.out.println(val);
//        }

        //Stream.of(existingList).filter(x -> x.contains("2")).collect(Collectors.joining(""))

        String[] txnL = {
                "",
                "something" + existingList.get(0),
                "dsaedf dsadfsd dfsadf" + existingList.get(1),
                "ddfdfdfdfdfdf" + existingList.get(3),
                ""
        };
        System.out.println("97:898:08" + String.join(System.lineSeparator(), txnL));
        Arrays.stream(txnL);

    }

    private static Object doThis(String x) {
        System.out.println(x);
        return x;
    }

    private static void dateTimeCompare() {
        Date date_1_current = new Date();


        Calendar cal = Calendar.getInstance();
        cal.setTime(date_1_current);
        cal.add(Calendar.HOUR, -1);

        Date date_1_pastTime = cal.getTime();

        System.out.println(date_1_pastTime);
        System.out.println(date_1_current);

        System.out.println(date_1_pastTime.before(date_1_current));

    }


/*    public static void main(String args[])
    {
        for(int i=1;i<=10;i++)
        {
            for(int j=1;j<=i;j++)
            {
                for(int k=1;k<=j;k++)
                {
                    System.out.print(k+"\t");
                    return;
                }
            }
            System.out.println();
        }
    }*/

}
