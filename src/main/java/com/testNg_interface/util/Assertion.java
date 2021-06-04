package com.testNg_interface.util;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Assertion {

    public static boolean flag = true;

    public static List<Error> errors = new ArrayList<Error>();

    public static void verifyEquals(Object actual, Object expected){
        try{
            Assert.assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(Object actual, Object expected, String message){
        try{
            Assert.assertEquals(actual, expected, message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyTrue(boolean actual){
        try{
            Assert.assertTrue(actual);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyTrue(boolean actual , String message){
        try{
            Assert.assertTrue(actual, message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }
}
