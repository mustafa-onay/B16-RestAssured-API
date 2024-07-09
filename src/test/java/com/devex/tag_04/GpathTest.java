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

            int sumOfIds = jsonData.getInt("id.sum()");
            System.out.println("sumOfIds = " + sumOfIds);

            int minId = jsonData.getInt("id.min()");
            System.out.println("minId = " + minId);

            int maxId = jsonData.getInt("id.max()");
            System.out.println("maxId = " + maxId);

            int sizeId = jsonData.getInt("id.size()");
            System.out.println("sizeId = " + sizeId);

            List<String> githubMails = jsonData.getList("findAll{it.githubusername!='null'}.user.email");
            System.out.println("githubMails = " + githubMails);

            List<Object> experiencedUser = jsonData.getList("findAll{it.experience!=null}.user.email");
            System.out.println("experiencedUser = " + experiencedUser);
        }

    }
}
