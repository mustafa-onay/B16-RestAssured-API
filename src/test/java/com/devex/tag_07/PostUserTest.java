package com.devex.tag_07;
import com.devex.pojos.PostUser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostUserTest {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }

    @Test
    public void postUserWithString() {

        String userBody = """
                {
                  "email": "forrestgump3@gmail.com",
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

        response = given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .when().get("/api/auth");
        response.prettyPrint();
        assertThat(response.path("name"), equalTo(postedName));


    }
    @Test
    public void postUserWithMap() {


        Map<String, Object> userBodyMap = new HashMap<>();
        userBodyMap.put("email", "forrestgump4@gmail.com");
        userBodyMap.put("password", "Test123");
        userBodyMap.put("name", "Forrest Gump");
        userBodyMap.put("google", "string");
        userBodyMap.put("facebook", "string");
        userBodyMap.put("github", "string");


        String postedName = "Forrest Gump";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(userBodyMap) // serialization
                .when().post("/api/users");

        response.then().assertThat().statusCode(200);
        Assert.assertTrue(response.body().asString().contains("token"));

        String token = response.path("token");
        System.out.println("token = " + token);

        response = given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .when().get("/api/auth");
        response.prettyPrint();
        assertThat(response.path("name"), equalTo(postedName));


    }
    @Test
    public void postUserWithPojo() {


        PostUser postUserBody = new PostUser();
        postUserBody.setEmail("forrestgump5@gmail.com");
        postUserBody.setPassword("Test123");
        postUserBody.setName("Forrest Gump");
        postUserBody.setGoogle("string");
        postUserBody.setFacebook("string");
        postUserBody.setGithub("string");


        String postedName = "Forrest Gump";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postUserBody) // serialization
                .when().post("/api/users");

        response.then().assertThat().statusCode(200);
        Assert.assertTrue(response.body().asString().contains("token"));

        String token = response.path("token");
        System.out.println("token = " + token);

        response = given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .when().get("/api/auth");
        response.prettyPrint();
        assertThat(response.path("name"), equalTo(postedName));

    }
}