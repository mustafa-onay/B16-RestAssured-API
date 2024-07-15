package com.devex.tag_08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PutAndPatch {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }

    @Test
    public void putRequestTest() {

        String token = Authorization.getToken();


        Map<String, Object> putBodyMap = new HashMap<>();
        putBodyMap.put("title", "Sr. SDET");
        putBodyMap.put("company", "Lenovo");
        putBodyMap.put("location", "Stuttgart");
        putBodyMap.put("from", "2021-05-01");
        putBodyMap.put("to", "-");
        putBodyMap.put("current", true);
        putBodyMap.put("description", "works as a professional Senior SDET");

        given().contentType(ContentType.JSON)
                .and().pathParam("experienceID", 2045)
                .and().header("x-auth-token", token)
                .and().body(putBodyMap)
                .when().put("/api/profile/experience/{experienceID}")
                .then().assertThat().statusCode(204);

        JsonPath jsonData = given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .pathParam("id", 2045)
                .log().all()
                .when().get("/api/profile/experience/{id}")
                .then().assertThat().statusCode(200)
                .log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonData.getString("title"),"Sr. SDET");
    }

    @Test
    public void patchRequestTest() {

        String token = Authorization.getToken();


        Map<String, Object> putBodyMap = new HashMap<>();
        putBodyMap.put("from","2024-05-30");
        putBodyMap.put("current", false);


        given().contentType(ContentType.JSON)
                .and().pathParam("experienceID", 2045)
                .and().header("x-auth-token", token)
                .and().body(putBodyMap)
                .when().patch("/api/profile/experience/{experienceID}")
                .then().assertThat().statusCode(204);

        JsonPath jsonData = given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .pathParam("id", 2045)
                .log().all()
                .when().get("/api/profile/experience/{id}")
                .then().assertThat().statusCode(200)
                .log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonData.getBoolean("current"),false);
}
}
