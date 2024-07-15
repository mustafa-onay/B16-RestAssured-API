package com.devex.tag_08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Authorization {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }


    public static String getToken () {

        String body = """
                {
                  "email": "stringshooter68@gmail.com",
                  "password": "Test123"
                }""";

        JsonPath jsonData = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/auth").jsonPath();

        String token = jsonData.getString("token");

        return token;
    }

    @Test
    public void addExperience() {

        String token = getToken();

        String postBody = """
                {
                  "title": "Jr. Full Stack SDET",
                  "company": "Samsung",
                  "location": "Vienna",
                  "from": "2018-09-12",
                  "to": "2019-05-03",
                  "current": false,
                  "description": "worked as a junior SDET"
                }""";

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("x-auth-token", token)
                .body(postBody)
                .when().post("/api/profile/experience")
                .then().assertThat().statusCode(201);
    }


    @Test
    public void getAllExperiences() {

        String token = getToken();

        given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .log().all()
                .when().get("/api/profile/experience")
                .then().assertThat().statusCode(200)
                .log().all();
    }

    @Test
    public void getAllExperienceById() {

        String token = getToken();

        given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .pathParam("id", 2045)
                .log().all()
                .when().get("/api/profile/experience/{id}")
                .then().assertThat().statusCode(200)
                .log().all();
    }
}
