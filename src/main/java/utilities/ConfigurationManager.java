package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @Added by Ravi Kaniyawala -- ravi.kaniyawala@gmail.com
 */
public class ConfigurationManager {

    public static String env;
    public static String brand;
    public static String maxRetryCnt;
    public static String timeOut;

    public static String pnsdevurl;
    public static String pnsqaurl;
    public static String pnspreprodurl;
    public static String pnsprodurl;

    public static String nwdevurl;
    public static String nwqaurl;
    public static String nwpreprodurl;
    public static String nwprodurl;

    public static String adminuiurlqa;
    public static String adminuiurlPreprod;

    public static  String browser;

    public static String testEmail1;
    public static String testPass1;
    public static String testEmail2;
    public static String testPass2;
    public static String testTimeslotExpEmail;
    public static String testTimeslotExpPass;
    public static String testTimeslotExpEmail1;
    public static String testTimeslotExpPass1;

    public static String testTimeslotExpEmail2;
    public static String testTimeslotExpPass2;

    public static String testEmail2ux;
    public static String testPass2ux;
    public static String testEmail3;
    public static String testPass3;
    public static String testEmail4;
    public static String testPass4;
    public static String testEmail5;
    public static String testPass5;
    public static String testEmail6;
    public static String testPass6;
    public static String testEmail7;
    public static String testPass7;
    public static String testEmail8;
    public static String testPass8;
    public static String testEmail8ux;
    public static String testPass8ux;
    public static String testEmail9;
    public static String testPass9;
    public static String testEmail10;
    public static String testPass10;
    public static String testemail12;
    public static String testPass12;
    public static String testppCooTestEmail;
    public static String testppCooTestPass;
    public static String testppCooTestEmail1;
    public static String testppCooTestPass1;

    public static String testResetEmail;
    public static String testResetPassword;
    public static String testResetPassInbox;
    public static String testResetEmail1;
    public static String testResetPassword1;
    public static String inboxResetEmail1;
    public static String inboxResetPassword1;

    public static String testMyAccountEmail;
    public static String testMyAccountPass;
    public static String applitoolsTest;
    public static String useVisualGrid;
    public static String addCardEmail;
    public static String addCardPass;
    public static String boozegateOrderEmail;
    public static String boozegatepass;
    public static String testnwemail1;
    public static String testnwpass1;
    public static String testPromo;
    public static String testPromo1;
    public static String testExpiredPromo;
    public static String testPreprodExpiredPromo;
    public static String testmtRoskillPromo;
    public static String testsylviaParkPromo;


    public static String testPromoPns;
    public static String testPromoPns1;
    public static String testExpiredPromoPns;
    public static String testPreprodExpiredPromoPns;

    public static String testallowsubemail;
    public static String testallowsubpass;
    public static String testallowsubemail1;
    public static String testallowsubpass1;
    public static String testallowsubemail2;
    public static String testallowsubpass2;

    public static String testBuildNo;

    public static String testzeronwdollaremail;
    public static String testzeronwdollarpass;

    public static String testposnwdollaremail;
    public static String testposnwdollarpass;

    public static String testEmail11;
    public static String testPass11;

    public static String testBoatOrderEmail;
    public static String testBoatOrderPass;

    public static String testLockerCollectionEmail;
    public static String testLockerCollectionPass;

    public static String awsPreprodAccessKey;
    public static String awsPreprodSecretKey;
    public static String awsQaAccessKey;
    public static String awsQaSecretKey;


    public static String testadminuiqaemail, testadminuiqapass;




    public static void loadTestConfig(){
        Properties prop = new Properties();
        InputStream propFile = (ConfigurationManager.class)
                .getClassLoader()
                .getResourceAsStream("config.properties");

        if(propFile!=null) {
            try {
                prop.load(propFile);
                propFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("Failed to load config.properties...");
        }

        Map<String, String> props = fieldMapper();
        Field[] fields = (ConfigurationManager.class).getFields();

        for(Field field:fields) {
            String fieldName = field.getName();
            String propKey = props.get(fieldName);

            try{
                if(!System.getProperty(propKey).equals("")) {
                    loadFromSystemProperties(propKey, field);
                }else{
                    System.out.println(String.format("Blank system property for \"%s\", " +
                            "loading from config file...", propKey));
                    loadFromConfigFile(propKey, field, prop);
                }
            }catch(NullPointerException e) {
                try {
                    //load local config file
                    loadFromConfigFile(propKey, field, prop);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    private static Map<String, String> fieldMapper() {
        Map<String, String> props = new HashMap<>();
        props.put("env","test.env");
        props.put("brand", "test.brand");

        props.put("pnsdevurl","test.pnsdevurl");
        props.put("pnsqaurl","test.pnsqaurl");
        props.put("pnspreprodurl","test.pnspreprodurl");
        props.put("pnsprodurl","test.pnsprodurl");

        props.put("nwdevurl","test.nwdevurl");
        props.put("nwqaurl","test.nwqaurl");
        props.put("nwpreprodurl","test.nwpreprodurl");
        props.put("nwprodurl","test.nwprodurl");
        props.put("adminuiurlqa","test.adminuiurlqa");
        props.put("adminuiurlPreprod","test.adminuiurlPreprod");
        //adminuiurlPreprod

        props.put("browser", "test.browser");

        props.put("testEmail1", "test.email1");
        props.put("testPass1", "test.pass1");
        props.put("testEmail11", "test.email11");
        props.put("testPass11", "test.pass11");
        props.put("testEmail2", "test.email2");
        props.put("testPass2", "test.pass2");
        props.put("testEmail2ux", "test.email2ux");
        props.put("testPass2ux", "test.pass2ux");
        props.put("testEmail3", "test.email3");
        props.put("testPass3", "test.pass3");
        props.put("testEmail4", "test.email4");
        props.put("testPass4", "test.pass4");
        props.put("testEmail5", "test.email5");
        props.put("testPass5", "test.pass5");
        props.put("testEmail6", "test.email6");
        props.put("testPass6", "test.pass6");
        props.put("testEmail7", "test.email7");
        props.put("testPass7", "test.pass7");
        props.put("testEmail8", "test.email8");
        props.put("testPass8", "test.pass8");
        props.put("testEmail8ux", "test.email8ux");
        props.put("testPass8ux", "test.pass8ux");
        props.put("testEmail9", "test.email9");
        props.put("testPass9", "test.pass9");
        props.put("testEmail10", "test.email10");
        props.put("testPass10", "test.pass10");
        props.put("testemail12", "test.email12");
        props.put("testPass12", "test.pass12");
        //
        props.put("testallowsubemail","test.allowsubemail");
        props.put("testallowsubpass","test.allowsubpass");
        props.put("testallowsubemail1","test.allowsubemail1");
        props.put("testallowsubpass1","test.allowsubpass1");
        props.put("testallowsubemail2","test.allowsubemail2");
        props.put("testallowsubpass2","test.allowsubpass2");

        props.put("testResetEmail", "test.resetemail");
        props.put("testResetPassword", "test.resetpassword");
        props.put("testResetPassInbox", "test.resetpassinbox");
        props.put("testResetEmail1", "test.resetemail1");
        props.put("testResetPassword1", "test.resetpassword1");
        props.put("inboxResetEmail1", "inbox.resetemail1");
        props.put("inboxResetPassword1", "inbox.resetpassword1");

        props.put("testMyAccountEmail", "test.myaccountemail");
        props.put("testMyAccountPass", "test.myaccountpass");
        props.put("applitoolsTest","test.applitools");
        props.put("useVisualGrid","test.useVisualGrid");
        props.put("maxRetryCnt","test.maxRetryCnt");
        props.put("timeOut","test.timeOut");
        props.put("addCardEmail","test.addcardemail");
        props.put("addCardPass","test.addcardpass");
        props.put("boozegateOrderEmail","test.boozegateorder");
        props.put("boozegatepass","test.boozegatepass");
        props.put("testnwemail1","test.nwemail1");
        props.put("testnwpass1","test.nwpass1");
        props.put("testPromo","test.promo");
        props.put("testPromo1","test.promo1");
        props.put("testExpiredPromo","test.expiredPromo");

        props.put("testPromoPns","test.promoPns");
        props.put("testPromoPns1","test.promoPns1");
        props.put("testExpiredPromoPns","test.expiredPromoPns");
        props.put("testPreprodExpiredPromoPns","test.preprodExpiredPromoPns");

        props.put("testPreprodExpiredPromo","test.preprodExpiredPromo");
        props.put("testmtRoskillPromo","test.mtRoskillPromo");
        props.put("testsylviaParkPromo","test.sylviaParkPromo");
        props.put("testBuildNo","test.buildNo");


        props.put("testzeronwdollaremail","test.zeronwdollaremail");
        props.put("testzeronwdollarpass","test.zeronwdollarpass");


        props.put("testposnwdollaremail","test.posnwdollaremail");
        props.put("testposnwdollarpass","test.posnwdollarpass");

        props.put("testBoatOrderEmail","test.BoatOrderEmail");
        props.put("testBoatOrderPass","test.BoatOrderPass");

        props.put("testppCooTestEmail","test.ppCooTestEmail");
        props.put("testppCooTestPass","test.ppCooTestPass");

        props.put("testppCooTestEmail1","test.ppCooTestEmail1");
        props.put("testppCooTestPass1","test.ppCooTestPass1");

        props.put("testadminuiqaemail","test.adminuiqaemail");
        props.put("testadminuiqapass","test.adminuiqapass");

        props.put("testTimeslotExpEmail","test.TimeslotExpEmail");
        props.put("testTimeslotExpPass","test.TimeslotExpPass");

        props.put("testTimeslotExpEmail1","test.TimeslotExpEmail1");
        props.put("testTimeslotExpPass1","test.TimeslotExpPass1");

        props.put("testTimeslotExpEmail2","test.TimeslotExpEmail2");
        props.put("testTimeslotExpPass2","test.TimeslotExpPass2");

        props.put("testLockerCollectionEmail","test.LockerCollectionEmail");
        props.put("testLockerCollectionPass","test.LockerCollectionPass");

        props.put("awsPreprodAccessKey","test.awsPreprodAccessKey");
        props.put("awsPreprodSecretKey","test.awsPreprodSecretKey");

        props.put("awsQaAccessKey","test.awsQaAccessKey");
        props.put("awsQaSecretKey","test.awsQaSecretKey");



        return props;
    }

    private static void loadFromSystemProperties(String key, Field field) throws IllegalAccessException, NullPointerException {
        String sysProp = System.getProperty(key);
        field.set(ConfigurationManager.class, sysProp);

        //System.out.println(String.format("%s: %s loaded from System Properties", key, sysProp));
    }

    private static void loadFromConfigFile(String key, Field field, Properties prop) throws IllegalAccessException {
        String locProp = prop.getProperty(key);
        field.set(ConfigurationManager.class, locProp);
//        System.out.println(String.format("%s: %s loaded from config files", key, locProp));
    }
}

