package com.devex.tag_05;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class JsonToJavaTest {
    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .when().queryParam("id", 2006)
                .log().all()
                .get("api/profile/userquery");
        response.prettyPrint();

        Map<String, Object> map = response.as(Map.class);
        System.out.println("map = " + map);
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                //.log().all()
                .when().get("/api/profile");

        List<Map<String, Object>> list = response.as(List.class);

        System.out.println("list = " + list);
    }
}
