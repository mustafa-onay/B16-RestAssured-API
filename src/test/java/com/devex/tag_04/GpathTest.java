package com.devex.tag_04;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class GpathTest {

    String baseUrl = "http://www.eurotech.study";

    @Test
    public void test1() {

        JsonPath jsonData = given().accept(ContentType.JSON)
                .when().get(baseUrl + "/api/profile").jsonPath();

        for (Object status : jsonData.getList("findAll{it.id<100}.status")) {
            System.out.println("status = " + status);

            String username19 = jsonData.getString("find{it.id==19}.user.name");
            System.out.println("username19 = " + username19);

            List<String> jDevs = jsonData.getList("findAll{it.status='Junior Developer'}.user.email");
            jDevs.forEach(System.out::println);
        }

    }
}
