package com.devex.tag_06;
import com.devex.pojos.TestUserQuery;
import com.devex.pojos.UserQuery;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
public class PojoTest {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .when().queryParam("id", 2006)
                .get("/api/profile/userquery");

        response.prettyPrint();
        Map<String, Object> user = response.as(Map.class);

        UserQuery pojoUser = response.as(UserQuery.class); // deserialization with pojo classes

        System.out.println("pojoUser.getCompany() = " + pojoUser.getCompany());
        System.out.println("pojoUser.getId() = " + pojoUser.getId());
        System.out.println("pojoUser.getProfileId() = " + pojoUser.getProfileId());
        System.out.println("pojoUser.getEmail() = " + pojoUser.getEmail());
        System.out.println("pojoUser.getStatus() = " + pojoUser.getStatus());
        System.out.println("pojoUser.getName() = " + pojoUser.getName());

        System.out.println("************************************************************");

        TestUserQuery testUserQuery = response.as(TestUserQuery.class);
        System.out.println("testUserQuery.getCompany() = " + testUserQuery.getCompany());
    }
}
