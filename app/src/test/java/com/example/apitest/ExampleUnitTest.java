package com.example.apitest;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        get("/events?id=390").then().statusCode(200).assertThat()
                .body("data.leagueId", equalTo(35));
    }
}