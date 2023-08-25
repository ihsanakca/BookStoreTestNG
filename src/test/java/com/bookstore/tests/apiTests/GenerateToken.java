package com.bookstore.tests.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bookstore.tests.TestBase;
import com.bookstore.utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class GenerateToken extends TestBase {
    @Test
    public void generateToken() {
        Map<String ,String> mapBody=new HashMap<>();
        mapBody.put("userName", ConfigurationReader.get("userName"));
        mapBody.put("password",ConfigurationReader.get("password"));

        response= RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(mapBody)
                .when().log().all()
                .post("/Account/v1/GenerateToken")
                .prettyPeek();
    }

    @Test
    public void verifyToken() {
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.path("token"));
        Assert.assertEquals(response.path("result"),"User authorized successfully.");
        Assert.assertEquals(response.path("status"),"Success");

        ConfigurationReader.set("token",response.path("token"));
    }
}
