package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2/posts";

        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", 25)
                .get()
                .then().statusCode(200);
    }


    // 1. Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("The title is : " + title);
        System.out.println("-------------------------------------");
    }


    // 2. Extract the total number of record
    @Test
    public void test002() {
        int total = response.extract().path("size()");
        System.out.println("The total is : " + total);
        System.out.println("-------------------------------------");
    }

    // 3. Extract the body of 15th record
    @Test
    public void test003() {
        String bodyOfFifteenthStore = response.extract().path("body[14]");
        System.out.println("The body of 15th store is : " + bodyOfFifteenthStore);
        System.out.println("-------------------------------------");
    }


    // 4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> userIds = response.extract().path("user_id");
        System.out.println("The userIds of all records is : " + userIds);
        System.out.println("-------------------------------------");
    }


    // 5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeIdOfAllStore = response.extract().path("id");
        System.out.println("The store IDs of all stores are : " + storeIdOfAllStore);
        System.out.println("-------------------------------------");
    }


    // 6. Extract the title of all records whose user_id = 7609167
    @Test
    public void test006() {
        List<String> titles = response.extract().path("findAll { it.user_id == 7609167}.title");
        System.out.println("Titles of Posts with user_id = 7609167 : " + titles);
        System.out.println("-------------------------------------");
    }


    // 7. Extract the body of all records whose id = 93957(this user id is not available) so. i've changed it with 7609169
    @Test
    public void test007() {
        List<String> bodies = response.extract().path("findAll {it.user_id == 7609169 }.body");
        System.out.println("Bodies of Posts with id = 7609169: " + bodies);
        System.out.println("-------------------------------------");
    }
}
