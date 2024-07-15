package com.devex.tag_07;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PostProfileTest {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }
    @Test
    public void test1() {

        String userBody = """
                {
                 "email": "stringshooter68@gmail.com",
                 "password": "Test123",
                 "name": "String Shooter",
                 "google": "string",
                 "facebook": "string",
                 "github": "string"
     
               }""";

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


    // benutzen Sie Map und Pojo für den gleichen Test
    }
}