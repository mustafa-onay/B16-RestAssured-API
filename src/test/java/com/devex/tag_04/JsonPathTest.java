package com.devex.tag_04;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class JsonPathTest {

    String baseUrl = "http://www.eurotech.study";

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when().queryParam("id", "34")
                .and().get(baseUrl + "/api/profile/userquery");
        response.prettyPrint();

        JsonPath jsonData = response.jsonPath();
        String status = jsonData.getString("status");
        Object status1 = response.path("status");
        System.out.println("status = " + status);
        System.out.println("status1 = " + status1);

        int profileId = jsonData.getInt("profileId");
        System.out.println("profileId = " + profileId);
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get(baseUrl + "/api/profile");

        JsonPath jsonData = response.jsonPath();
        List<Integer> userIds = jsonData.getList("user.id");
        System.out.println("userIds.size() = " + userIds.size());
        System.out.println("userIds = " + userIds);

        Map<String, Object> firstUserInfo = jsonData.getMap("user[0]");
        for (String key : firstUserInfo.keySet()) {
            System.out.println(key + " = " + firstUserInfo.get(key));
        }

        List<Map<String, Object>> allUserInfos = jsonData.getList("user");

        for (Map<String, Object> allUserInfo : allUserInfos) {

            if (allUserInfo == null){ // NullPointerException wird durch continue übersprungen
                continue;
            }

            for (String key : allUserInfo.keySet()) {
                System.out.println(key + " = " + allUserInfo.get(key));
            }
        }

        List<String> skills = jsonData.getList("[1].skills");

        skills.forEach(p -> System.out.println("p = " + p));

        List<List<String>> usersForSkills = jsonData.getList("skills");
        for (List<String> skillsList : usersForSkills) {
            for (String skill : skillsList) {
                System.out.println(skill);
            }
        }


    }
}
