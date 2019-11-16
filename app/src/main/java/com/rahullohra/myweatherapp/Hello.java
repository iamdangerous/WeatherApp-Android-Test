package com.rahullohra.myweatherapp;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class Hello {

    public static void main(String[] args) {

        Function1<String, Boolean> f = new Function1<String, Boolean>() {
            @Override
            public Boolean invoke(String s) {
                return false;
            }
        };

        Function1<Integer, String> f2 = new Function1<Integer, String>() {
            @Override
            public String invoke(Integer s) {
                return "";
            }
        };


        Adapter adapter = new Adapter(() -> {

        }, new Function0() {
            @Override
            public Object invoke() {
                //show toast
                return null;
            }
        }, f);
    }


    static class Adapter {
        Listener listener;
        Function0 fs;
        Function1<String, Boolean> fs1;

        Adapter(Listener listener, Function0 fs, Function1<String, Boolean> fs1) {
            this.listener = listener;
            this.fs = fs;
            this.fs1 = fs1;
        }

        void doSomething() {
            listener.onClick();
            fs.invoke();
            fs1.invoke("Hello");
        }
    }


    interface Listener {
        void onClick();
    }

}
