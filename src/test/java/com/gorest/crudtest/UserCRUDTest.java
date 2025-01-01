package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class UserCRUDTest extends TestBase {

    static String name = "user1";
    static String email = TestUtils.getRandomValue() + "testac20@gmail.com";
    static String gender = "female";
    static String status = "active";
    static int storeId;


    @Test
    public void verifyUserCreatedSuccessfully() {


        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                .when()
                .body(userPojo)
                .post("/users");

        response.prettyPrint();
        storeId = response.path("id");
        response.then().log().ifValidationFails().statusCode(201);
    }

    @Test
    public void verifyUserUpdateSuccessfully() {

        String name = "uvi" + "Updated";
        String email = TestUtils.getRandomValue() + "urvi123@gmail.com";
        String gender = "female";
        String status = "active";

        UserPojo userPojo = new UserPojo();

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", storeId)
                .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .put("/users/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void VerifyUserDeleteSuccessfully() {
        given().log().ifValidationFails()
                .pathParam("id",storeId)
                .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                .when()
                .delete("/users/{id}")
                .then().log().ifValidationFails().statusCode(204);
        given()
                .pathParam("id", storeId)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }

}


