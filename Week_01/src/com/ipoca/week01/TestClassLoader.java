package com.ipoca.week01;

import java.lang.reflect.Method;
import java.util.Base64;

public class TestClassLoader extends ClassLoader {



    public static void main(String[] args){
        try {
            Object hello = new TestClassLoader().findClass("Hello").newInstance();
            Method method = hello.getClass().getMethod("hello");
            method.invoke(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String testClazz = "NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfWqf7/+7yQm5r+//CzlpGasYqSnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpuajd74/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCshoyLmpL+//yQiov+/+qzlZ6JntCWkNCvjZaRi6yLjZqeksT+/+yVnome0JaQ0K+NlpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/+f///////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v////7//v/0//f//v/2////2v/9//7////2Tf/97fxJ//tO/////v/1////9f/9////+//3//r//v/z/////f/y";
        byte[] bytes = decode(testClazz);
        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] decode(String testClazz) {
        byte[] bytes = Base64.getDecoder().decode(testClazz);
        for (int i = 0; i < bytes.length; i++){
            bytes[i] = (byte)(255-bytes[i]);
        }
        return bytes;
    }

}
