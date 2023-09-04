package nw.features.api;


import io.restassured.response.Response;
import utilities.DataObjects.UserModel;
import utilities.apihelper.ApiHelper;


public class CartApi extends ApiHelper {

    LoginApi loginApi = new LoginApi();

    private static final String PATH = "/abcd";

    public CartApi getAuthToken(UserModel userModel) {
        authToken = loginApi.postDetails(userModel).jsonPath().get("access_token");
        return this;
    }

    public Response addItemTocart(String productsJson) {

        return givenConfig().
                when().body(productsJson).
                post(PATH);
    }

    public Response getCart() {

        return givenConfig().
                when().
                get(PATH);
    }

    public Response emptyCart() {

        return givenConfig().
                when().
                delete(PATH);
    }

}
