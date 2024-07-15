package com.devex.tag_08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteRequest {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }

    @Test
    public void deleteExperience() {

        String token = Authorization.getToken();

        JsonPath jsonData = given().accept(ContentType.JSON)
                .and().header("x-auth-token", token)
                .and().pathParam("id", 2045)
                .when().delete("/api/profile/experience/{id}")
                .then().assertThat().statusCode(200)
                .and().log().all()
                .extract().jsonPath();

        Assert.assertTrue(jsonData.getList("experience").isEmpty());
    }
}
