package com.devex.tag_06;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;

public class DeserializationTest {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/profile");

        List<Map<String, Object>> userList = response.as(List.class);

        Integer id = (Integer) userList.get(924).get("id");

        System.out.println("id = " + id);

        List<String> skills = (List<String>) userList.get(924).get("skills");
        skills.forEach(p -> System.out.println(p));

        Map<String, Object> userInfo = (Map<String, Object>) userList.get(924).get("user");
        String nameOf924 = (String) userInfo.get("name");

        System.out.println("nameOf924 = " + nameOf924);

        List<Map<String, Object>> educations924 = (List<Map<String, Object>>) userList.get(924).get("education");
        for (Map<String, Object> education : educations924) {
            for (String key : education.keySet()) {
                System.out.println(key  + " = " + education.get(key));
            }
            System.out.println("*************************************************");
        }


    }
}
