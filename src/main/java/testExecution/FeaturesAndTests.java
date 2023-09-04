package testExecution;

import reporting.Attribute;

import java.util.ArrayList;
import java.util.List;

//FeatureCategory -> Feature List -> Feature -> Scenario List(Feature Class) -> Scenario 1.......Scenario n

public class FeaturesAndTests {

    //region SCENARIO CLASSES

    //Ensure every class added here must be later added to a Feature Lists (below)
    public enum ScenarioClasses {

        //region Scenario Classes for CATEGORY - REGISTRATION
        ACCESS_PREVIOUSLY_ENTERED_DETAILS("features.registration.AccessPreviousDetails", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        REGISTRATION_INPUT_VALIDATION("features.registration.InputValidation", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        MANDATORY_REGISTRATION("features.placeOrder.MandatoryRegistrationForPlaceOrder", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        PROMOTIONS_OPTION("features.registration.Promotions", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        REGISTRATION_WITH_CLUB_CARD("features.registration.RegisterWithAClubCard", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        REGISTRATION_WITHOUT_CLUB_CARD("features.registration.RegisterWithoutAClubCard", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        //endregion

        //region Scenario Classes for CATEGORY - USER AUTHENTICATION
        LOGIN_VALIDATION("features.userAuthentication.LoginValidation", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        SUCCESSFUL_LOGIN("features.userAuthentication.SuccessfulLogin", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        ACCOUNT_LOCK_OUT("features.userAuthentication.AccountLockOut", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        PASSWORD_RESET_LINK_ON_LOCKOUT("features.userAuthentication.AccessingPasswordReset", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        PASSWORD_RESET("features.userAuthentication.PasswordReset", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        EMAIL_ID_CONFIRMATION_ON_PWD_RESET("features.userAuthentication.MailIdConfirmationOnPasswordReset", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        PASSWORD_RESET_LINK_TIME_SPAN("features.userAuthentication.PasswordResetLinkTimeSpan", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        PASSWORD_RESET_LINK_SINGLE_USAGE("features.userAuthentication.PasswordResetLinkSingleUsage", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        PASSWORD_RESET_VALIDATION("features.userAuthentication.PasswordResetValidation", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        MANDATORY_LOGIN_ON_CHECK_OUT("features.userAuthentication.MandatoryLoginOnCheckOut", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        //endregion
        ;

        //region INITIALIZER, GETTERS AND SETTERS
        private String scenarioTestClass;
        private Attribute.Exposure exposure;
        private Attribute.Risk risk;

        public Attribute.Exposure getExposure() {
            return exposure;
        }

        public Attribute.Risk getRisk() {
            return risk;
        }

        ScenarioClasses(String scenarioTestClass, Attribute.Exposure exposure, Attribute.Risk risk){
            this.scenarioTestClass = scenarioTestClass;
            this.exposure = exposure;
            this.risk = risk;
        }

        public String getScenarioTestClass(){
            return scenarioTestClass;
        }
        //endregion
    }
    //endregion

    //region SCENARIO LIST

    // region REGISTRATION_FOR_UNREGISTERED_USERS
    static final List<ScenarioClasses> SCENARIO_LIST_REGISTRATION_FOR_UNREGISTERED_USERS = new ArrayList<ScenarioClasses>() {{
        add(ScenarioClasses.REGISTRATION_WITHOUT_CLUB_CARD);
        add(ScenarioClasses.REGISTRATION_WITH_CLUB_CARD);
        add(ScenarioClasses.ACCESS_PREVIOUSLY_ENTERED_DETAILS);
        add(ScenarioClasses.REGISTRATION_INPUT_VALIDATION);
        add(ScenarioClasses.PROMOTIONS_OPTION);
    }};
    //endregion

    //region MANDATORY_REGISTRATION
    static final List<ScenarioClasses> SCENARIO_LIST_MANDATORY_REGISTRATION = new ArrayList<ScenarioClasses>(){{
        add(ScenarioClasses.MANDATORY_REGISTRATION);
    }};
    //endregion

    //region LOGIN_FOR_REGISTERED_USERS
    static final List<ScenarioClasses> SCENARIO_LIST_LOGIN_FOR_REGISTERED_USERS = new ArrayList<ScenarioClasses>(){{
        add(ScenarioClasses.LOGIN_VALIDATION);
        add(ScenarioClasses.SUCCESSFUL_LOGIN);
        add(ScenarioClasses.ACCOUNT_LOCK_OUT);
    }};
    //endregion

    //region PASSWORD_RESET_LINK_ON_LOCKOUT
    static final List<ScenarioClasses> SCENARIO_LIST_PASSWORD_RESET_LINK_ON_LOCKOUT = new ArrayList<ScenarioClasses>(){{
        add(ScenarioClasses.PASSWORD_RESET_LINK_ON_LOCKOUT);
    }};
    //endregion

    //region PASSWORD_RESET
    static final List<ScenarioClasses> SCENARIO_LIST_PASSWORD_RESET = new ArrayList<ScenarioClasses>(){{
        add(ScenarioClasses.PASSWORD_RESET);
        add(ScenarioClasses.EMAIL_ID_CONFIRMATION_ON_PWD_RESET);
        add(ScenarioClasses.PASSWORD_RESET_LINK_TIME_SPAN);
        add(ScenarioClasses.PASSWORD_RESET_LINK_SINGLE_USAGE);
        add(ScenarioClasses.PASSWORD_RESET_VALIDATION);
    }};
    //endregion

    //region MANDATORY_LOGIN
    static final List<ScenarioClasses> SCENARIO_LIST_MANDATORY_LOGIN = new ArrayList<ScenarioClasses>(){{
        add(ScenarioClasses.MANDATORY_LOGIN_ON_CHECK_OUT);
    }};
    //endregion

    //endregion

    //region FEATURES (Each Feature will have a list of Scenario Classes)
    public enum Feature {

        REGISTRATION_FOR_UNREGISTERED_USERS(SCENARIO_LIST_REGISTRATION_FOR_UNREGISTERED_USERS, "Registration for Unregistered Users", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        MANDATORY_REGISTRATION_ON_CHECKOUT(SCENARIO_LIST_MANDATORY_REGISTRATION, "Mandatory Registration on Checkout", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        LOGIN_FOR_REGISTERED_USERS(SCENARIO_LIST_LOGIN_FOR_REGISTERED_USERS, "Login for Registered Users", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        PASSWORD_RESET_LINK_ON_LOCKOUT(SCENARIO_LIST_PASSWORD_RESET_LINK_ON_LOCKOUT, "Password Reset on Account Lockout", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        PASSWORD_RESET(SCENARIO_LIST_PASSWORD_RESET, "Resetting a Password", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        MANDATORY_LOGIN(SCENARIO_LIST_MANDATORY_LOGIN, "Mandatory Login", Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),
        ;


        //region INITIALIZER, GETTERS AND SETTERS
        private List<ScenarioClasses> scenarioClasses;
        private String testName;
        private Attribute.Exposure exposure;
        private Attribute.Risk risk;

        public Attribute.Exposure getExposure() {
            return exposure;
        }

        public Attribute.Risk getRisk() {
            return risk;
        }

        Feature(List<ScenarioClasses> scenarioList, String testName, Attribute.Exposure exposure, Attribute.Risk risk) {
            this.scenarioClasses = scenarioList;
            this.testName = testName;
            this.exposure = exposure;
            this.risk = risk;
        }

        public List<ScenarioClasses> getScenarioClasses(){
            return scenarioClasses;
        }

        public String getTestName(){return testName;}
        //endregion
    }
    //endregion

    //region FEATURE LISTS (Each of these Feature lists will belong to a Feature Category - below)

    //region FEATURE_LIST_REGISTRATION
    static final List<Feature> FEATURE_LIST_REGISTRATION = new ArrayList<Feature>() {{
        add(Feature.REGISTRATION_FOR_UNREGISTERED_USERS);
        add(Feature.MANDATORY_REGISTRATION_ON_CHECKOUT);
    }};
    //endregion

    //region FEATURE_LIST_USER_AUTHENTICATION
    static final List<Feature> FEATURE_LIST_USER_AUTHENTICATION = new ArrayList<Feature>() {{
        add(Feature.LOGIN_FOR_REGISTERED_USERS);
        add(Feature.PASSWORD_RESET_LINK_ON_LOCKOUT);
        add(Feature.PASSWORD_RESET);
        add(Feature.MANDATORY_LOGIN);
    }};
    //endregion

    //endregion

    //region FEATURE CATEGORIES (Each Feature Category will have a list of Features)
    public enum FeatureCategory {

        REGISTRATION("Registration", FEATURE_LIST_REGISTRATION, Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),

        USER_AUTHENTICATION("User Authentication", FEATURE_LIST_USER_AUTHENTICATION, Attribute.Exposure.INTENDED_USE, Attribute.Risk.HIGH),

        ;//region INITIALIZER, GETTERS AND SETTERS
        private List<Feature> features;
        private String categoryDescription;

        FeatureCategory(String categoryDescription, List<Feature> features, Attribute.Exposure exposure, Attribute.Risk risk) {
            this.features = features;
            this.categoryDescription = categoryDescription;
            this.exposure = exposure;
            this.risk = risk;
        }

        private Attribute.Exposure exposure;
        private Attribute.Risk risk;


        public List<Feature> getFeatures(){
            return features;
        }
        public String getCategoryDescription(){return categoryDescription;}
        public Attribute.Exposure getExposure() {
            return exposure;
        }
        //endregion
    }
    //endregion
}