package com.devex.tag_05;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
public class HamcrestMatchersTest {

    @BeforeMethod
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }

    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .when().get("/api/profile")
                .then().assertThat().statusCode(200)
                .and()
                .contentType("application/json; charset=utf-8")
                .and()
                .header("Content-Length", "805972");
    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when().queryParam("id", 25)
                .get("/api/profile/userquery");
        response.prettyPrint();
    }

    @Test
    public void test3() {
        given().accept(ContentType.JSON)
                .when().queryParam("id", 25)
                .get("/api/profile/userquery")
                .then()
                .assertThat().statusCode(200)
                .and()
                .contentType("application/json; charset=utf-8")
                .and()
                .body("id", Matchers.equalTo(25))
                .and()
                .body("email", Matchers.equalTo("jrdev@gmail.com"),
                "name",Matchers.equalTo("Jr. Dev"),
                        "company",Matchers.equalTo("google"),
                        "status",Matchers.is("Junior Developer"),
                        "profileId",Matchers.is(1)
                        );
    }

    @Test
    public void test4() {
        given().accept(ContentType.JSON)
                .when().queryParam("id",2006)
                .log().all()
                .get("/api/profile/userquery")
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().contentType("application/json; charset=utf-8")
                .and()
                .header("ETag", Matchers.is("W/\"80-fhUysXhC1S9dWXuDiV09Sgdn+68\""))
                .and()
                .header("Date", Matchers.notNullValue())
                .body("profileId", Matchers.is(1119))
                .log().body();
    }

    @Test
    public void test5() {
        given().accept(ContentType.JSON)
                .log().all()
                .when().get("/api/profile")
                .then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .and()
                .body("website", hasItem("sarra.microsoft.de"),
                        "location", hasItem("Frankfurt"),
                        "githubusername", hasItem("eurotech_35"))
                .log().headers();
    }

    @Test
    public void test6() {
        List<String> websites = new ArrayList<>();
        websites.add("google.com");
        websites.add("apple.com");
        websites.add("amazon.com");

        String [] websitesArray = {"google.com","apple.com","amazon.com"};
        given().accept(ContentType.JSON)
                .log().all()
                .when().get("/api/profile")
                .then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .and()
                //.body("website", hasItems("google.com","amazon.com","apple.com"))
                //.body("website", hasItems(websites.toArray()))
                .body("website",hasItems(websitesArray))
                .log().headers();
    }

    @Test
    public void test7() {
        given().accept(ContentType.JSON)
                .log().all()
                .when().get("/api/profile")
                .then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .and()
                .body("user.email",hasItems("talisca@gmail.com","euroTechStudy@gmail.com","sdet_blg@gmail.com"),
                        "user.name",hasItems("Blg","Study","Anderson Talisca"))
                .log().headers();
    }

    @Test
    public void testTask() {
        /**
         * /api/profile
         * status code : 200
         * Content-Type: application/json; charset=utf-8
         * Date = not null
         * index 924 :
         * company = EuroTech Study
         * skill[2] = Cucumber
         * first education school = Full Stack SDET
         * first experience title = SDET
         * second experience company = Amazon
         */

        given().accept(ContentType.JSON)
                .log().all()
                .when().get("/api/profile")
                .then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .and()
                .body("[924].company",is("EuroTech Study"))
                .body("[924].skills[2]",equalTo("Cucumber"))
                .body("education[924][0].school",is("Full Stack SDET"))
                .body("experience[924][0].title",is("SDET"))
                .body("experience[924][1].company",is("Amazon"));

    }
}
