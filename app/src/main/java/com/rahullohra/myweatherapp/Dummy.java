package com.rahullohra.myweatherapp;

public class Dummy {
    static {
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello");
    }
}

