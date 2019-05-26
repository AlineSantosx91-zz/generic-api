package br.com.generic.api.genericapi.controller;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.springframework.boot.web.server.LocalServerPort;

import static com.jayway.restassured.RestAssured.reset;

public class BaseTest {

    @LocalServerPort
    protected int port = 0;

    @Before
    public void before(){
        reset();

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

}
