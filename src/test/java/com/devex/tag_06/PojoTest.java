package com.devex.tag_06;
import com.devex.pojos.AllUsers;
import com.devex.pojos.TestUserQuery;
import com.devex.pojos.UserQuery;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class PojoTest {

    Gson gson;

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
        gson = new Gson();
        defaultParser = Parser.JSON;
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

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().pathParam("id", 2006)
                .and().get("/api/profile/user/{id}");

        AllUsers allUsers = response.body().as(AllUsers.class);
        //AllUsers allUsers = gson.fromJson(response.body().asString(), AllUsers.class);
        System.out.println("allUsers.getCompany() = " + allUsers.getCompany());

        System.out.println("allUsers.getUser().getPassword() = " + allUsers.getUser().getPassword());
        System.out.println("allUsers.getUser().getEmail() = " + allUsers.getUser().getEmail());

        System.out.println("allUsers.getEducation().get(1).getSchool() = " + allUsers.getEducation().get(1).getSchool());

        System.out.println("allUsers.getExperience().get(0).getLocation() = " + allUsers.getExperience().get(0).getLocation());

        System.out.println("allUsers.getGithubusername() = " + allUsers.getGithubusername());

    }

    @Test
    public void test3() {

        UserQuery userQuery = new UserQuery(68.0,"test@test.com","testName","testCompany","available",22);
        String json = gson.toJson(userQuery);

        System.out.println("json = " + json);
    }

    @Test
    public void test4() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/profile");

        AllUsers[] usersArray = gson.fromJson(response.body().asString(), AllUsers[].class);

        System.out.println("listOfUser[924].getGithubusername() = " + usersArray[924].getGithubusername());
    }
}
