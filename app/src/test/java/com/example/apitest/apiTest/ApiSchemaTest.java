package com.example.apitest.apiTest;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class ApiSchemaTest {
    private static final String NEWSPOIN_BASE_URL= "http://partnersnp.indiatimes.com/feed/fx/atp";

    @Test
    public void checkNewsPointJsonSchema() {

        RestAssured.baseURI = NEWSPOIN_BASE_URL;

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addParam("channel", "*");
        requestSpecBuilder.addParam("section", "top-news");
        requestSpecBuilder.addParam("lang", "english");
        requestSpecBuilder.addParam("curpg", "1");
        requestSpecBuilder.addParam("pp", "5");
        requestSpecBuilder.addParam("v", "v1");
        requestSpecBuilder.addParam("fromtime", "1551267146210");
        RequestSpecification requestSpec = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectBody(matchesJsonSchemaInClasspath("NewsPointSchema.json"));
        ResponseSpecification responseSpec = responseSpecBuilder.build();

        given().log().all().
                spec(requestSpec).
        when().
                get("/").
        then().log().all().
                spec(responseSpec);


    }

}