package nw.features.api;


import io.restassured.response.Response;
import utilities.DataObjects.UserModel;
import utilities.apihelper.ApiHelper;


public class LoginApi extends ApiHelper {

    private static final String PATH = "/xyz/abc";
    Response res;



    public Response postDetails(UserModel userModel) {

        res =  givenLoginConfig().
                body(gson().toJson(userModel)).
                when().
                post(PATH);
        return res.getStatusCode()!=200?givenLoginConfig().
                body(gson().toJson(userModel)).
                when().
                post(PATH):res;

    }

}
