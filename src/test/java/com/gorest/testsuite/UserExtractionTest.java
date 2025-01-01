package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

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


    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> ids = response.extract().path("id");
        System.out.println("The all Ids is : " + ids);
        System.out.println("-------------------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> names = response.extract().path("name");
        System.out.println("The all names are : " + names);
        System.out.println("-------------------------------------");
    }


    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String nameOfFifthObject = response.extract().path("[4].name");
        System.out.println("The all Ids is : " + nameOfFifthObject);
        System.out.println("-------------------------------------");
    }


    //4. Extract the names of all object whose status = inactive

    @Test
    public void test004() {
        List<String> inActiveNames = response.extract().path("findAll { it.status == 'inactive' }.name");
        System.out.println("The all names with inactive status : " + inActiveNames);
        System.out.println("-------------------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> ActiveGender = response.extract().path("findAll { it.status == 'active' }.gender");
        System.out.println("The gender of all users with active status : " + ActiveGender);
        System.out.println("-------------------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> namesWithFemaleGender = response.extract().path("findAll { it.gender == 'female' }.name");
        System.out.println("All users with female gender : " + namesWithFemaleGender);
        System.out.println("-------------------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> emailsWithInActiveStatus = response.extract().path("findAll { it.status == 'inactive' }.email");
        System.out.println("The email ids with inactive status : " + emailsWithInActiveStatus);
        System.out.println("-------------------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> idsOfMaleGender = response.extract().path("findAll { it.gender == 'male' }.id");
        System.out.println("The all Ids of male users : " + idsOfMaleGender);
        System.out.println("-------------------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<String> allUsersStatus = response.extract().path("status");
        System.out.println("The status of all users is : " + allUsersStatus);
        System.out.println("-------------------------------------");
    }

    //10. Get email of the object where name = Avantika Kapoor
    @Test
    public void test010() {
        String email = response.extract().path("[3].email");
        System.out.println("The email of Avantika kapoor : " + email);
        System.out.println("-------------------------------------");
    }

    //11. Get gender of id = 7609143
    @Test
    public void test011() {
        String gender = response.extract().path("[4].gender");
        System.out.println("The gender of 5th id : " + gender);
        System.out.println("-------------------------------------");
    }

}
