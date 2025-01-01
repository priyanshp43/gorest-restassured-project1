package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2/users";

        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", 20)
                .get()
                .then().statusCode(200);
    }


    // 1. Verify the if the total record is 20
    @Test
    public void test01() {
        response.body("users.size()", equalTo(10));
    }

    // 2. Verify the if the name of id = 5914197 is equal to ”Bhudev Varman MD”
    @Test
    public void test02() {
        response.body("[1].name", equalTo("Bhudev Varman MD"));
    }

    // 3. Check the single ‘Name’ in the Array list (Dr. Vedanga Bharadwaj)
    @Test
    public void test03() {
        response.body("[9].name", equalTo("Dr. Vedanga Bharadwaj"));
    }

    // 4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test04() {
        response.body("name", hasItems("Gajabahu Bhat", "Bhudev Varman MD", "Mandakini Reddy"));
    }

    // 5. Verify the email of userid = 7609144 is equal “kapoor_avantika@howe-douglas.test”
    @Test
    public void test05() {
        response.body("[3].email", equalTo("kapoor_avantika@howe-douglas.test"));
    }

    // 6. Verify the status is “Active” of user name is “Jagdeep Pandey JD”
    @Test
    public void test06() {
        response.body("[5].status", equalTo("active"));
    }

    // 7. Verify the Gender = male of user name is “Dhyaneshwar Tagore”
    @Test
    public void test07() {
        response.body("[8].gender", equalTo("male"));
    }

}
