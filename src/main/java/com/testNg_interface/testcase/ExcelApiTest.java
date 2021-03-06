package com.testNg_interface.testcase;


import com.alibaba.fastjson.JSONObject;
import com.testNg_interface.base.TestBase;
import com.testNg_interface.client.Client;
import com.testNg_interface.util.Xt_Operate;
//import com.testNg_interface.util.DataProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static com.testNg_interface.util.TestUtil.dtt;
import static com.testNg_interface.util.TestUtil.stringTosTringArray;
import static com.testNg_interface.util.Xt_Operate.InsertUpdateDeleteXT;
import static com.testNg_interface.util.Xt_Operate.LoadJdbc;

import java.util.HashMap;


public class ExcelApiTest extends TestBase {

    TestBase testBase;
    Client restClient;
    CloseableHttpResponse closeableHttpResponse;
    Xt_Operate xt;
    //host根url
    String host;
    //Excel路径
    String testCaseExcel;
    //header
    HashMap<String, String> postHeader = new HashMap<String, String>();

//    DataProvider dataProvider = new DataProvider();

    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        restClient = new Client();
        xt = new Xt_Operate();

//            postHeader.put("Content-Type","application/x-www-form-urlencoded");
//        postHeader.put("cookie",)
        //载入配置文件，接口endpoint
        host = prop.getProperty("Host");

//        载入配置文件，post接口参数
        testCaseExcel = prop.getProperty("testCase1data");
    }

    @DataProvider(name = "init")
    public Object[][] init() throws IOException {
        return dtt(testCaseExcel, 0);
    }

    @DataProvider(name = "login")
    public Object[][] login() throws IOException {
        return dtt(testCaseExcel, 1);
    }

    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(testCaseExcel, 2);
    }

    @DataProvider(name = "get")
    public Object[][] get() throws IOException {
        //get类型接口
        return dtt(testCaseExcel, 3);
    }

    @DataProvider(name = "delete")
    public Object[][] delete() throws IOException {
        //delete类型接口
        return dtt(testCaseExcel, 4);
    }

    @Test(dataProvider = "init")
    public void init(String initSql) {
        //初始化SQL执行
        //传入sql
        LoadJdbc();
        System.out.println(initSql);
        //执行删除操作
        InsertUpdateDeleteXT(stringTosTringArray(initSql));
    }


    @Test(dataProvider = "login")
    public void login(String project, String caseID, String apiSeq, String apiName, String testType, String priority, String url, String headInfo, String precondition, String methods, String dataParameters, String specialSetup, String contentType, String sign, String expectValue, String preResult, String sql, String jsonPath1, String jsonPath2, String jsonPath3, String jsonPath4, String para1, String para2, String para3, String para4, String port)
            throws Exception {
        int preStatusCode = Integer.parseInt(preResult);
        int portNum = Integer.parseInt(port);
        //发送登录请求
        //将传入的参数对象序列化成map对象
        Map<String, String> params = (Map<String, String>) JSONObject.parse(dataParameters);
        postHeader.put("Content-Type", "application/x-www-form-urlencoded");
        closeableHttpResponse = restClient.postKeyValue(host + portNum + url, params, postHeader);
        //状态码断言200
        Assert.assertEquals(restClient.getStatusCode(closeableHttpResponse), preStatusCode);
        //获取相关信息，写入报告中
        Reporter.log("用例编号： " + caseID);
        Reporter.log("用例标题： " + apiName);
        Reporter.log("URL： " + url);
        Reporter.log("请求方式： " + methods);
        Reporter.log("请求参数： " + dataParameters);
        Reporter.log("状态码：" + restClient.getStatusCode(closeableHttpResponse));
        Reporter.log("响应结果： " + restClient.getResponseJson(closeableHttpResponse));
    }


    @Test(dataProvider = "postData")
    public void postApi(String project, String caseID, String apiSeq, String apiName, String testType, String priority, String url, String headInfo, String precondition, String methods, String dataParameters, String specialSetup, String contentType, String sign, String expectValue, String preResult, String sql, String jsonPath1, String jsonPath2, String jsonPath3, String jsonPath4, String para1, String para2, String para3, String para4, String port)
            throws Exception {
        int preStatusCode = Integer.parseInt(preResult);
        int portNum = Integer.parseInt(port);
        System.out.println(contentType);
        System.out.println(contentType.contains("json"));
        //发送登录请求
        if (contentType.contains("json")) {
            //将传入的参数对象序列化成json对象
//                String params = JSON.toJSONString(dataParameters);
            postHeader.put("Content-Type", "application/json");
            System.out.println(dataParameters);
            closeableHttpResponse = restClient.postJson(host + portNum + url, dataParameters, postHeader);
        } else {
            //将传入的参数对象序列化成map对象
            Map<String, String> params = (Map<String, String>) JSONObject.parse(dataParameters);
            postHeader.put("Content-Type", "application/x-www-form-urlencoded");
            System.out.println(params);
            closeableHttpResponse = restClient.postKeyValue(host + portNum + url, params, postHeader);
        }

        //状态码断言200
        Assert.assertEquals(restClient.getStatusCode(closeableHttpResponse), preStatusCode);

        //获取相关信息，写入报告中
        Reporter.log("用例编号： " + caseID);
        Reporter.log("用例标题： " + apiName);
        Reporter.log("URL： " + url);
        Reporter.log("请求方式： " + methods);
        Reporter.log("请求参数： " + dataParameters);
        Reporter.log("状态码：" + restClient.getStatusCode(closeableHttpResponse));
        Reporter.log("响应结果： " + restClient.getResponseJson(closeableHttpResponse));

    }

    @Test(dataProvider = "get")
    public void getApi(String project, String caseID, String apiSeq, String apiName, String testType, String priority, String url, String headInfo, String precondition, String methods, String dataParameters, String specialSetup, String contentType, String sign, String expectValue, String preResult, String sql, String jsonPath1, String jsonPath2, String jsonPath3, String jsonPath4, String para1, String para2, String para3, String para4, String port) throws Exception {
        int preStatusCode = Integer.parseInt(preResult);
        int portNum = Integer.parseInt(port);

        if (contentType.contains("json")) {
            postHeader.put("Content-Type", "application/json");
        } else {
            postHeader.put("Content-Type", "application/x-www-form-urlencoded");
        }

        closeableHttpResponse = restClient.getApi(host + portNum + url + dataParameters, postHeader);

        //状态码断言200
        Assert.assertEquals(restClient.getStatusCode(closeableHttpResponse), preStatusCode);
//        asserts(response.getResponse(),cb.getExpected(),response.getStatusCode());
        //获取相关信息，写入报告中
        Reporter.log("用例编号： " + caseID);
//        Reporter.log("用例标题： " + apiName);
        Reporter.log("URL： " + url);
        Reporter.log("请求方式： " + methods);
        Reporter.log("请求参数： " + dataParameters);
        Reporter.log("状态码：" + restClient.getStatusCode(closeableHttpResponse));
        Reporter.log("响应结果： " + restClient.getResponseJson(closeableHttpResponse));

    }

    @AfterClass
    public void endTest() {
        System.out.println("测试结束");
    }

}
