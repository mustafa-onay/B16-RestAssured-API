package com.devex.tag_04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class PathMethodReview {

    String baseUrl = "http://www.eurotech.study";

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get(baseUrl + "/api/profile");
        List<Integer> id = response.path("id"); // alle id INFOS
        //System.out.println("id = " + id);
        System.out.println("id.size() = " + id.size());

        //Integer firstId = response.path("[0].id");  // einzelne ID
        Integer firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        System.out.println("response.path(\"website[1165]\") = " + response.path("website[946]"));
        System.out.println("response.path(\"website[-1]\") = " + response.path("website[-1]"));
        System.out.println("response.path(\"status[-2]\") = " + response.path("status[-2]"));
        System.out.println("response.path(\"[3].skills\") = " + response.path("[3].skills"));
        System.out.println("response.path(\"[3].skills[3]\") = " + response.path("[3].skills[3]"));
        System.out.println("response.path(\"skills[3][4]\") = " + response.path("skills[3][4]"));
        //System.out.println("response.path(\"user.email\") = " + response.path("user.email")); // gibt uns alle emails aus
        System.out.println("response.path(\"user[1].email\") = " + response.path("user[1].email"));
        System.out.println("response.path(\"[1].user.name\") = " + response.path("[1].user.name"));
        System.out.println("response.path(\"[5]\") = " + response.path("[5]"));
        System.out.println("response.path(\"experience[5]\") = " + response.path("experience[5]"));
        System.out.println("response.path(\"experience[5].title\") = " + response.path("experience[5].title"));
        System.out.println("response.path(\"experience.title[5]\") = " + response.path("experience.title[5]"));
        System.out.println("response.path(\"[5].experience.title\") = " + response.path("[5].experience.title"));

    }


}
