package com.devex.tag_03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PathParamTest {

    String devexUrl = "http://www.eurotech.study";
    String psUrl = "https://petstore.swagger.io/v2";

    @Test
    public void testPathParam(){

       Response response = given().accept(ContentType.JSON)
                .when().get(devexUrl + "/api/profile/user/34");
        response.prettyPrint();
    }
    @Test
    public void testPathParam2() {
        Response response = given().accept(ContentType.JSON)
                .when().pathParam("userId", 34)
                .and().get(devexUrl + "/api/profile/user/{userId}");
        response.prettyPrint();
        assertTrue(response.asString().contains("sdet_blg@gmail.com"));
    }
}
