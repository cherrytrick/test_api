# Test_api接口自动化测试框架
**项目架构**
   * 采用TestNg
   * **_base_** 
       * TestBase  接口请求父类，加载properties
   * **_client_**
       * Client 封装http的基本方法，POST,GET,PUT,DELETE封装为可以使用复用的请求方法
       * CookieManager  处理cookie的方法
   *  **_failed_**
       * FailedRetry    FailedRetryListener 失败重试的listener，失败用例可重复执行
   * **_report_**
       * ExtentTestNGReporterListener   产出测试报告的listener
   * **_testcase_**
       * ExcelApiTest 在config.properties的excel数据路径，读取数据并根据封装
        的请求方法进行接口自动化测试
       * DeleteApiTest 模拟删除接口
       * GetApiTest 模拟get请求方法接口
       * TestInterface * 接口测试用
                             1.连接系统接口,发送接收json数据
                             2.准备数据 excel准备测试数据,接收数据写入excel中
                             3.解析响应的json
   * **_util_**
       * 关于jdbc，读取json数据，读取excel数据的配置类
   * 感谢 https://github.com/LeoXuLiang ，使用了其接口数据并在其架构基础改进了架构。
   
