//package com.testNg_interface.util;
//
//import com.testNg_interface.base.TestBase;
//
//import java.io.IOException;
//
//import static com.testNg_interface.util.TestUtil.dtt;
//
//
//
//public class DataProvider extends TestBase {
//
////    String testCaseExcel;
//
//    String testCaseExcel = prop.getProperty("testCase1data");
//    @org.testng.annotations.DataProvider(name = "init")
//    public Object[][] init() throws IOException {
//        return dtt(testCaseExcel, 0);
//    }
//
//    @org.testng.annotations.DataProvider(name = "login")
//    public Object[][] login() throws IOException {
//        return dtt(testCaseExcel, 1);
//    }
//
//    @org.testng.annotations.DataProvider(name = "postData")
//    public Object[][] post() throws IOException {
//        return dtt(testCaseExcel, 2);
//    }
//
//    @org.testng.annotations.DataProvider(name = "get")
//    public Object[][] get() throws IOException {
//        //get类型接口
//        return dtt(testCaseExcel, 3);
//    }
//
//    @org.testng.annotations.DataProvider(name = "delete")
//    public Object[][] delete() throws IOException {
//        //delete类型接口
//        return dtt(testCaseExcel, 4);
//    }
//}
