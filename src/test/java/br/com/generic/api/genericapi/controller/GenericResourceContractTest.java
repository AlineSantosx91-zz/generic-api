package br.com.generic.api.genericapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class GenericResourceContractTest extends BaseTest {

    @Test
    public void deveRetornar200() throws JsonProcessingException {
        final JSONObject h1 = new JSONObject();
        h1.put("key", "ContentType");
        h1.put("value", "application/json");

        final List<JSONObject> headers = new ArrayList<>();
        headers.add(h1);

        final JSONObject genericRequest = new JSONObject();
        genericRequest.put("headers", headers);
        genericRequest.put("body", new JSONObject());
        genericRequest.put("key", "teste");
        genericRequest.put("method", "GET");

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(genericRequest));

        given()
                .body(genericRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/meubff/generic")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

}