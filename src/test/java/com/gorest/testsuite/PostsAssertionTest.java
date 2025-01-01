package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest {

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

    // 1.  Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("posts.size()", equalTo(25));
    }

    // 2. Verify the if the title of id = 93997 is equal to ”Dedico cultura stipes terreo unde.”
    @Test
    public void test002() {
        response.body("[6].title", equalTo("Dedico cultura stipes terreo unde."));
    }

    // 3. Check the single user_id in the Array list (5914249) --- this id is not vailable so i changed it with user_id 7609165
    @Test
    public void test003() {
        response.body("[9].user_id", equalTo(7609165));
    }

    // 4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199) -- not available so i changed it with 184659, 184656, 184655
    @Test
    public void test004() {
        response.body("id", hasItems("184659", "184656", "184655"));
    }

    // (not available) 5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus. Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.
    // 5. verify the body of userid = 7609178 is equal "Defleo totus vomer. Delectus titulus sulum. Tamisium arbustum tendo. Acerbitas varius cibo. Vinculum laboriosam tenetur. Capitulus deripio quo. Uredo decretum talio. Ustilo basium tutamen. Subnecto tamen voco. Amor vilitas auris."
    @Test
    public void test005() {
        response.body("[0].body", equalTo("Defleo totus vomer. Delectus titulus sulum. Tamisium arbustum tendo. Acerbitas varius cibo. Vinculum laboriosam tenetur. Capitulus deripio quo. Uredo decretum talio. Ustilo basium tutamen. Subnecto tamen voco. Amor vilitas auris."));
    }
}
