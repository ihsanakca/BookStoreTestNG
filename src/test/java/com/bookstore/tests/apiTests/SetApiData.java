package com.bookstore.tests.apiTests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import com.bookstore.utilities.ConfigurationReader;

public class SetApiData {
    Faker faker=new Faker();
    @Test
    public void setData(){
        ConfigurationReader.set("userName",faker.name().fullName());
        ConfigurationReader.set("password","Ia1*"+faker.internet().password());
    }
}
