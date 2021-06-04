package com.testNg_interface.testcase;

import com.testNg_interface.base.TestBase;
import com.testNg_interface.client.Client;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteApiTest extends TestBase {
    TestBase testBase;
    String host;
    String url;
    Client restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/user/2";//找到delete的api
    }

    @Test
    public void deleteApiTest() throws IOException {
        restClient = new Client();
        closeableHttpResponse = restClient.delete(url);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,204,"status code is not 204");
    }
}
