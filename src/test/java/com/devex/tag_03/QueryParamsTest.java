package com.devex.tag_03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class QueryParamsTest {

    String devexUrl = "http://www.eurotech.study";
    String psUrl = "https://petstore.swagger.io/v2";

    @Test
    public void testQueryParam() {

        Response response = given().accept(ContentType.JSON)
                .when().queryParam("id", 34)
                .and().get(devexUrl + "/api/profile/userquery");
        response.prettyPrint();
        assertTrue(response.asString().contains("sdet_blg@gmail.com"));
    }
    @Test
    public void testQueryParams() {
        Response response = given().accept(ContentType.JSON)
                .when().queryParams("id", 34
                        , "name", "Blg", "company", "Amazon")
                .and().get(devexUrl + "/api/profile/userquery");
        response.prettyPrint();
    }

    @Test
    public void testQueryParamsWithMap() {
        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("id", 34);
        queryParamsMap.put("name", "Blg");
        queryParamsMap.put("company", "Amazon");
        queryParamsMap.put("email", "sdet_blg@gmail.com");
        queryParamsMap.put("status", "Junior Developer");
        queryParamsMap.put("profileId", 7);

        Response response = given().accept(ContentType.JSON)
                .when().queryParams(queryParamsMap)
                .and().get(devexUrl + "/api/profile/userquery");
        response.prettyPrint();
        assertTrue(response.headers().hasHeaderWithName("Date"));
    }
}
