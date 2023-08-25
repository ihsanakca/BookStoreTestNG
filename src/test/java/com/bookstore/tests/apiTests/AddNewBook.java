package com.bookstore.tests.apiTests;

import com.bookstore.Main;
import com.bookstore.tests.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bookstore.utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewBook extends TestBase {
    @Test
    public void addNewBookWithString() {
        String body = "{\n" +
                "  \"userId\": \"" + ConfigurationReader.get("userID") + "\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"" + ConfigurationReader.get("isbn5") + "\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + ConfigurationReader.get("token"))
                .body(body)
                .when().log().all()
                .post("/BookStore/v1/Books")
                .prettyPeek();
    }
    @Test
    public void addNewBookWithMap() {

        Map<String, Object> mapBody = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("isbn", ConfigurationReader.get("isbn3"));

        list.add(map);

        mapBody.put("userId", ConfigurationReader.get("userID"));
        mapBody.put("collectionOfIsbns", list);


        response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + ConfigurationReader.get("token"))
                .body(mapBody)
                .when().log().all()
                .post("/BookStore/v1/Books")
                .prettyPeek();
    }
    @Test
    public void verifyAddingBook() {
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.path("books[0].isbn"), ConfigurationReader.get("isbn5"));
    }
}
