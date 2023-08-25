package com.bookstore.tests.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bookstore.tests.TestBase;
import com.bookstore.utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class AuthorizedUser extends TestBase {
    @Test
    public void authorizedUser(){
        Map<String ,String> mapBody=new HashMap<>();
        mapBody.put("userName", ConfigurationReader.get("userName"));
        mapBody.put("password",ConfigurationReader.get("password"));

        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(mapBody)
                .when().log().all()
                .post("/Account/v1/Authorized")
                .prettyPeek();
    }

    @Test
    public void verifyAuthorization(){
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals("true",response.asString());
    }
}
