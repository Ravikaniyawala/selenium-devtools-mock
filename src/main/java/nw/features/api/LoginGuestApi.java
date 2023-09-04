package nw.features.api;


import io.restassured.response.Response;
import utilities.apihelper.ApiHelper;


public class LoginGuestApi extends ApiHelper {

    private static final String PATH = "/xyz";


    public Response postDetails() {

        return  givenLoginConfig().
                body("{\"body\":\"PARAM\"}").
                when().
                post(PATH);

    }

}
