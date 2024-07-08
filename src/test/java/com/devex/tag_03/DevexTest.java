package com.devex.tag_03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class DevexTest {

    String url = "http://www.eurotech.study";

    @Test
    public void testDevex(){
    Response response = get(url + "/api/profile");
    System.out.println("response.statusCode() = " + response.statusCode());
    assertEquals(response.statusCode(),200);
    }

    @Test
    public void testDevex2(){
        Response response = given().accept(ContentType.JSON)
                .when().get(url + "/api/profile");

        System.out.println("response.header(\"ETag\") = " + response.header("ETag"));
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertEquals(response.header("ETag"),"W/\"c13cc-Q/gbyg1iKn2vJ0kpzRTfTr/4R2w\"");
        assertEquals(response.header("Content-Length"),"791500");
        assertEquals(response.header("Connection"),"keep-alive");
        //assertEquals(response.header("Date"),"Mon, 08 Jul 2024 18:01:04 GMT");
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertTrue(response.headers().hasHeaderWithName("X-Powered-By"));
        assertTrue(response.headers().hasHeaderWithName("ETag"));

        System.out.println("LocalDate.now() = " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)));
        String actualDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));

        assertTrue(response.header("Date").contains(actualDate));
    }
}
