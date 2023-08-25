package com.bookstore.tests.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bookstore.tests.TestBase;
import com.bookstore.utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends TestBase {
    @Test
    public void registerUser(){
        Map<String ,String > mapBody=new HashMap<>();
        mapBody.put("userName", ConfigurationReader.get("userName"));
        mapBody.put("password",ConfigurationReader.get("password"));

        response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(mapBody)
                .when().log().all()
                .post("/Account/v1/User")
                .prettyPeek();
    }
    @Test
    public void verifyThatNewUserIsRegistered(){
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertNotNull(response.path("userID"));
        Assert.assertEquals(ConfigurationReader.get("userName"),response.path("username"));

        ConfigurationReader.set("userID",response.path("userID"));
    }
}
