package nw.features.api;

import com.google.gson.Gson;
import io.restassured.response.Response;
import utilities.*;
import utilities.DataObjects.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static utilities.DataGeneration.objectToJson;

public class FoodstuffsApi{

    CartApi cartApi = new CartApi();

    GetStoreProductApi getStoreProductApi = new GetStoreProductApi();



    public Response emptyCart(UserModel userModel){
        return cartApi.getAuthToken(userModel).emptyCart();
    }

    public Response addToCart(UserModel userModel, String productsJson){
        return cartApi.getAuthToken(userModel).addItemTocart(productsJson);
    }


    public Response getCart(UserModel userModel){
        return cartApi.getAuthToken(userModel).getCart();
    }

    public boolean isAwsFilesUpdated() throws IOException {
        return AwsS3Operation.isNwExportFilesUpdated();
    }


}