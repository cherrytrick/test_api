package com.testNg_interface.testcase;


import com.testNg_interface.util.Xt_Operate;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class testJdbc  extends Xt_Operate {
    public static Connection conn = null;
    public static PreparedStatement statement = null;
    public static ResultSet resultSet = null;
    public static String driver = null;
    public static String url = null;
    public static String user = null;
    public static String pwd = null;

    @Test
    public void testSelect2() throws IOException, ClassNotFoundException {

        InputStream is = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/testNg_interface/config/config.properties"));
        // 3.创建一个properties对象
        Properties properties = new Properties();
        // 4.加载输入流
        properties.load(is);
        // 5.获取相关参数的值
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("username");
        pwd = properties.getProperty("password");
        //加载驱动
        Class.forName(driver);

    }

    public void testjdbcXt() {

//            //查询
        LoadJdbc();
        //查询数据并返回查询结果
        afferentSQL("SELECT NAME FROM `user` WHERE login_name like '1990000000%';");
        //SelectXT（）使用list接收返回结果
        List<Object> usersa = SelectXT();

        //数据读取示例
        //获取第一条数据
        Map<String, Object> rowData = (Map<String, Object>) usersa.get(1);
        //获取第一条数据的name字段
        rowData.get("name");

        System.out.println("第1条数据的name字段：" + rowData.get("name"));


        //传入sql
        LoadJdbc();

        String[] strArray = {"UPDATE `user`  SET `name` = '555' WHERE login_name LIKE '1990000000%' AND `name` = '222';", "UPDATE `user`  SET `name` = '666' WHERE login_name LIKE '1990000000%' AND `name` = '444';"};

//       String str =
//        String[] stringArrayData = initSql.split(",");

        //执行删除操作
        InsertUpdateDeleteXT(strArray);
    }
}

