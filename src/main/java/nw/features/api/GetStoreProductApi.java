package nw.features.api;

import io.restassured.response.Response;
import utilities.DataObjects.UserModel;
import utilities.apihelper.ApiHelper;


public class GetStoreProductApi extends ApiHelper {

    LoginApi loginApi = new LoginApi();
    LoginGuestApi loginGuestApi = new LoginGuestApi();
    private String storeId;
    private String productId;
    Response response;
    private String PATH;


    public GetStoreProductApi getAuthToken(UserModel userModel) {
        authToken = loginApi.postDetails(userModel).jsonPath().get("access_token");
        return this;
    }

    public GetStoreProductApi getAuthToken() {
        authToken = loginGuestApi.postDetails().jsonPath().get("access_token");
        return this;
    }

    public Response getStoreProductApi(String storeIdd,String productIdd) {
        this.storeId = storeIdd;
        this.productId = productIdd;
        this.PATH = "/store/"+storeId+"/product/"+productId;
        this.response = givenConfig().
                when().
                get(PATH);
        return this.response;
    }

}
