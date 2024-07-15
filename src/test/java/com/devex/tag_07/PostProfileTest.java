package com.devex.tag_07;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class PostProfileTest {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }


    @Test
    public void postProfileWithString() {

        String userBody = """
                {
                  "email": "forrestgump7@gmail.com",
                  "password": "Test123",
                  "name": "Forrest Gump",
                  "google": "string",
                  "facebook": "string",
                  "github": "string"
                }""";
        String postedName = "Forrest Gump";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(userBody)
                .when().post("/api/users");

        response.then().assertThat().statusCode(200);
        Assert.assertTrue(response.body().asString().contains("token"));

        String token = response.path("token");
        System.out.println("token = " + token);


        String profileBody = """
                {
                  "company": "Bubba Gump",
                  "website": "bubbagump.com",
                  "location": "USA",
                  "status": "Available",
                  "skills": "HTML,CSS,Javascript",
                  "githubusername": "forrestGithub",
                  "youtube": "string",
                  "twitter": "string",
                  "facebook": "string",
                  "linkedin": "string",
                  "instagram": "string"
                }""";

        String website = "bubbagump.com";

        Response responseProfile = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("x-auth-token", token)
                .and().body(profileBody)
                .when().post("/api/profile")
                .then().assertThat().statusCode(200)
                .log().all()
                .extract().response();

        Response responseGetInfo = given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .and().get("/api/profile/me");

        Assert.assertEquals(responseGetInfo.path("website").toString(), responseProfile.path("website").toString());

        response.prettyPrint();

    }
    @Test
    public void postProfileWithMap() {

        Map<String, Object> profileMap = new HashMap<String, Object>();
        profileMap.put("email", "forrestgump8@gmail.com");
        profileMap.put("password", "Test123");
        profileMap.put("name", "Forrest Gump");
        profileMap.put("google", "string");
        profileMap.put("facebook", "string");
        profileMap.put("github", "string");


        String postedName = "Forrest Gump";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(profileMap)
                .when().post("/api/users");

        response.then().assertThat().statusCode(200);
        Assert.assertTrue(response.body().asString().contains("token"));

        String token = response.path("token");
        System.out.println("token = " + token);



        Map<String, Object> profileBodyMap = new HashMap<>();
        profileBodyMap.put("company", "Bubba Gump");
        profileBodyMap.put("website", "bubbagump.com");
        profileBodyMap.put("location", "USA");
        profileBodyMap.put("status", "Available");
        profileBodyMap.put("skills", "HTML,CSS,Javascript");
        profileBodyMap.put("githubusername", "forrestGithub");
        profileBodyMap.put("youtube", "string");
        profileBodyMap.put("twitter", "string");
        profileBodyMap.put("facebook", "string");
        profileBodyMap.put("linkedin", "string");
        profileBodyMap.put("instagram", "string");

        String website = "bubbagump.com";

        Response responseProfile = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("x-auth-token", token)
                .and().body(profileBodyMap)
                .when().post("/api/profile")
                .then().assertThat().statusCode(200)
                .log().all()
                .extract().response();

        Response responseGetInfo = given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .and().get("/api/profile/me");

        Assert.assertEquals(responseGetInfo.path("website").toString(), responseProfile.path("website").toString());

        response.prettyPrint();

    }


    // benutzen Sie Map und Pojo für den gleichen Test
    }
