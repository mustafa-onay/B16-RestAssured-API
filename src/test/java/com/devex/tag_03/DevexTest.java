package com.devex.tag_03;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class DevexTest {

    String url = "http://www.eurotech.study";

    @Test
    public void testDevex(){
    Response response = get(url + "/api/profile");
    System.out.println("response.statusCode() = " + response.statusCode());
    assertEquals(response.statusCode(),200);
    }

    @Test

}
