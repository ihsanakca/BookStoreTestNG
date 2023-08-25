package com.bookstore.tests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import static io.restassured.RestAssured.*;

public class TestBase {
   public static Response response;

    @BeforeMethod
    public void setUp(){
        baseURI="https://bookstore.toolsqa.com";
    }
}
