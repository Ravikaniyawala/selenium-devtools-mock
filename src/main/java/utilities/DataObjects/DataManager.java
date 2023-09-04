package utilities.DataObjects;


import org.openqa.selenium.By;
import utilities.ApplitoolsEyes;
import utilities.MockService;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public String currentStoreName;

    public List<String> groupedProductsId = new ArrayList<>();
    public ApplitoolsEyes applitoolsEyes = new ApplitoolsEyes();
    public UserModel userModel = new UserModel();
    public List<String> autoCompleteProducts = new ArrayList<>();
    public String shopFromOrderId;

    public MockService mockService;

}