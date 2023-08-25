package com.bookstore.tests.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bookstore.tests.TestBase;
import com.bookstore.utilities.ConfigurationReader;

import java.util.List;

public class GetAllBooks extends TestBase {
    @Test
    public void getAllBooks() {
        response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when().log().all()
                .get("/BookStore/v1/Books")
                .prettyPeek();
    }

    @Test
    public void verfiyAllBooks() {
        Assert.assertEquals(response.statusCode(), 200);
        List<String> allISBN = response.path("books.isbn");

        for (int i = 0; i < allISBN.size(); i++) {
            ConfigurationReader.set("isbn" + (i + 1) + "", allISBN.get(i));
        }
    }
}
