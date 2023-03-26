package de.hftstuttgart.vs.task02;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

class MainTest {

    //TODO: Change path (and maybe the http method)
    @Test
    void getAllUsers() {
        given()
                .log().all()
                .port(7070).
        when().
                get("").
        then().
                log().all();
    }

    //TODO: Change path (and maybe the http method)
    @Test
    void getSpecificUser() {
        given()
                .log().all()
                .port(7070).
        when().
                get("").
        then().
                log().all();
    }

    //TODO: Change path (and maybe the http method)
    @Test
    void addUser() {
        given()
                .log().all()
                .port(7070).
        when().
                get("").
        then().
                log().all();
    }

}
