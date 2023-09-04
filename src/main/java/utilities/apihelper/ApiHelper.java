package utilities.apihelper;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigurationManager;
import utilities.FileHelpers;
import utilities.Session;

import static io.restassured.RestAssured.given;

/**
 * Every Api Step definition class should extend this class
 */

public class ApiHelper {
    public static Gson gson;
    public String authToken = "";
    public String email = "";

    static {
        ConfigurationManager.loadTestConfig();
        RestAssured.baseURI = Session.BasePageURL;
    }

    protected RequestSpecification givenLoginConfig() {
        RestAssured.baseURI = Session.BasePageURL;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return given().
                header("Accept", "application/json").header("Content-Type", "application/json");
    }

    protected RequestSpecification givenConfig() {
        RestAssured.baseURI = Session.BasePageURL;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return given().
                header("Authorization", "Bearer " + authToken).header("Content-Type", "application/json");
    }

    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }

    //Custom Gson config to override Default Gson  configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }

}