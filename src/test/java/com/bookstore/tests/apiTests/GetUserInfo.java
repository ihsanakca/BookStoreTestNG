package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bookstore.utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserInfo extends TestBase {
    @Test
    public void getUserInfo() {
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("Authorization","Bearer "+ ConfigurationReader.get("token"));

        response = RestAssured.given()
                .accept(ContentType.JSON)
                .pathParam("UserId", ConfigurationReader.get("userID"))
                //        .header("Authorization", "Bearer " + ConfigurationReader.get("token"))
                .headers(tokenMap)
                .when().log().all()
                .get("/Account/v1/User/{UserId}")
                .prettyPeek();
    }

    @Test
    public void verifyUserInfo() {
        List<String> addedBooks=response.path("books.isbn");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.path("userId"), ConfigurationReader.get("userID"));
        Assert.assertEquals(response.path("username"), ConfigurationReader.get("userName"));
        Assert.assertEquals(response.path("books[0].isbn"), ConfigurationReader.get("isbn8"));
        Assert.assertTrue(addedBooks.contains(ConfigurationReader.get("isbn5")));
    }
}
