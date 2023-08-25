package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bookstore.utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class UpdateBook extends TestBase {
    @Test
    public void updateBook() {
        Map<String,String> mapBody=new HashMap<>();
        mapBody.put("userId", ConfigurationReader.get("userID"));
        mapBody.put("isbn", ConfigurationReader.get("isbn8"));

        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("ISBN",ConfigurationReader.get("isbn3"))
                .header("Authorization", "Bearer "+ConfigurationReader.get("token"))
                .body(mapBody)
                .when().log().all()
                .put("/BookStore/v1/Books/{ISBN}")
                .prettyPeek();
    }

    @Test
    public void verifyUpdateProcess() {
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.path("userId"), ConfigurationReader.get("userID"));
        Assert.assertEquals(response.path("username"), ConfigurationReader.get("userName"));
        Assert.assertTrue(response.path("books.isbn").toString().contains(ConfigurationReader.get("isbn8")));
    }
}
