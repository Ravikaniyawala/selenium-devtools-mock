package utilities;


import com.applitools.eyes.BatchInfo;

public class Session {

    public static BatchInfo batch = new BatchInfo(ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase()+"_" + ConfigurationManager.testBuildNo);

    public static BatchInfo getBatchInfo(){
        batch.setId(ConfigurationManager.brand.toUpperCase()+"_" + ConfigurationManager.env.toUpperCase() +"_" + ConfigurationManager.testBuildNo);
        return batch;
    }

    public static boolean isPns = brandIsPns();
    public static boolean isNw = brandIsNw();
    public static boolean isQa = EnvIsQa();
    public static boolean isDev = EnvIsDev();
    public static boolean isPreprod = EnvIsPreprod();
    public static boolean isProd = EnvIsProd();

    public static boolean isPnsDev = isPnsDev();
    public static boolean isPnsQa = isPnsQa();
    public static boolean isPnsPreprod = isPnsPreprod();
    public static boolean isPnsProd= isPnsProd();

    public static boolean isNwDev = isNwDev();
    public static boolean isNwQa = isNwQa();
    public static boolean isNwPreprod = isNwPreprod();
    public static boolean isNwProd= isNwProd();


    public static boolean isHeadless = BrowserIsHeadless();

    public static boolean IsEdgeHeadless = BrowserIsEdgeHeadless();
    public static boolean isChrome = BrowserIsChrome();
    public static boolean isMobile = BrowserIsMobile();
    public static boolean isSafari = BrowserIsSafari();
    public static boolean isSafariSauce = BrowserIsSafariSauce();

    public static String BasePageURL = getBasePageURL();
    public static int maxRetryCnt = getMaxRetryCnt();
    public static int timeOut = getTimeOut();


    public static String aws_AccessKey;

    public static String aws_secretKey;

    public static String getAws_AccessKey(){
        return null;
    }

    public static String getAws_secretKey(){
        return null;
    }

    public static int getTimeOut(){
        ConfigurationManager.loadTestConfig();
        return Integer.parseInt(ConfigurationManager.timeOut);
    }

    public static int getMaxRetryCnt(){
        return Integer.parseInt(ConfigurationManager.maxRetryCnt);
    }

    private static boolean brandIsPns() {

        return ConfigurationManager.brand.toLowerCase().trim().equals("pns");
    }

    private static boolean brandIsNw() {
        return ConfigurationManager.brand.toLowerCase().trim().equals("nw");
    }

    private static boolean EnvIsQa() {
        return ConfigurationManager.env.toLowerCase().trim().equals("qa");
    }

    private static boolean EnvIsDev() {
        return ConfigurationManager.env.toLowerCase().trim().equals("dev");
    }

    private static boolean EnvIsPreprod() {

        return ConfigurationManager.env.toLowerCase().trim().equals("preprod");
    }

    private static boolean EnvIsProd() {

        return ConfigurationManager.env.toLowerCase().trim().equals("prod");
    }

    private static boolean BrowserIsHeadless() {
        return ConfigurationManager.browser.toLowerCase().trim().equals("headless");
    }

    private static boolean BrowserIsEdgeHeadless() {
        return ConfigurationManager.browser.toLowerCase().trim().equals("edge");
    }

    private static boolean BrowserIsChrome() {
        return ConfigurationManager.browser.toLowerCase().trim().equals("chrome");
    }

    private static boolean BrowserIsMobile() {
        return ConfigurationManager.browser.toLowerCase().trim().equals("mobile");
    }

    private static boolean BrowserIsSafari() {
        return ConfigurationManager.browser.toLowerCase().trim().equals("safari");
    }

    private static boolean BrowserIsSafariSauce() {
        return ConfigurationManager.browser.toLowerCase().trim().equals("safarisauce");
    }

    private static boolean isPnsDev(){
        return brandIsPns() && EnvIsDev();
    }

    private static boolean isPnsQa(){
        return brandIsPns() && EnvIsQa();
    }

    private static boolean isPnsPreprod(){
        return brandIsPns() && EnvIsPreprod();
    }

    private static boolean isPnsProd(){
        return brandIsPns() && EnvIsProd();
    }

    private static boolean isNwDev(){
        return brandIsNw() && EnvIsDev();
    }

    private static boolean isNwQa(){
        return brandIsNw() && EnvIsQa();
    }

    private static boolean isNwPreprod(){
        return brandIsNw() && EnvIsPreprod();
    }

    private static boolean isNwProd(){
        return brandIsNw() && EnvIsProd();
    }

    private static String getBasePageURL() {
        if (brandIsPns() && EnvIsDev())
            return ConfigurationManager.pnsdevurl;
        else if (brandIsPns() && EnvIsQa())
            return ConfigurationManager.pnsqaurl;
        else if (brandIsPns() && EnvIsPreprod())
            return ConfigurationManager.pnspreprodurl;
        else if (brandIsPns() && EnvIsProd())
            return ConfigurationManager.pnsprodurl;
        else if (brandIsNw() && EnvIsDev())
            return ConfigurationManager.nwdevurl;
        else if (brandIsNw() && EnvIsQa())
            return ConfigurationManager.nwqaurl;
        else if (brandIsNw() && EnvIsPreprod())
            return ConfigurationManager.nwpreprodurl;
        else if (brandIsNw() && EnvIsProd())
            return ConfigurationManager.nwprodurl;

        return ConfigurationManager.pnsqaurl;
    }
}
