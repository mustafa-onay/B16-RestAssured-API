package com.devex.tag_02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequestTest {

    String url = "http://www.eurotech.study";

    @Test
    public void testSimpleGetRequest(){

        Response response = RestAssured.given().get(url + "/api/profile");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void testSimpleGetRequest2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(url + "/api/profile");

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testSimpleGetRequest3(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(url + "/api/profile");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }

    @Test
    public void testSimpleGetRequest4(){
        RestAssured.given().accept(ContentType.JSON)
                .when().get(url + "/api/profile")
                .then().assertThat().statusCode(200);
        Response response = RestAssured.get(url + "/api/profile");
        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.asString() = " + response.asString());

        Assert.assertTrue(response.asString().contains("jrdev@gmail.com"));
    }
}
