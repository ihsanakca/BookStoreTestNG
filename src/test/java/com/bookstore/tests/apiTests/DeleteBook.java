package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bookstore.utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class DeleteBook extends TestBase {
    @Test
    public void deleteBook() {
        Map<String,String> mapBody=new HashMap<>();
        mapBody.put("userId", ConfigurationReader.get("userID"));
        mapBody.put("isbn", ConfigurationReader.get("isbn8"));

        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(mapBody)
                .header("Authorization","Bearer "+ConfigurationReader.get("token"))
                .when().log().all()
                .delete("/BookStore/v1/Book")
                .prettyPeek();
    }

    @Test
    public void verifyDeleting() {
        Assert.assertEquals(response.statusCode(),204);
    }
}
