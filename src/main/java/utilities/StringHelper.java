package utilities;


import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

public class StringHelper {

    static final String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String alphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String numeric = "0123456789";
    static final String invalidCharacters = "[\\\\\\\\/:*?\\\"<>|~`$#@%^&()_+]";
    static SecureRandom rnd = new SecureRandom();
    static Random r = new Random();

    public static String generateUUID(){
        UUID temp = UUID.randomUUID();
        String uuidString = Long.toHexString(temp.getMostSignificantBits()) + Long.toHexString(temp.getLeastSignificantBits());
        return uuidString;
    }

    public static String generateRandomAlphabetString(Integer length){
        StringBuilder sb = new StringBuilder( length );
        for( int i = 0; i < length; i++ )
            sb.append( alphabets.charAt( rnd.nextInt(alphabets.length()) ) );
        return sb.toString();
    }

    public static String generateRandomAlphanumericString(Integer length){
        StringBuilder sb = new StringBuilder( length );
        for( int i = 0; i < length; i++ )
            sb.append( alphaNumeric.charAt( rnd.nextInt(alphaNumeric.length()) ) );
        return sb.toString();
    }

    public static String generateRandomNumericString(Integer length){
        StringBuilder sb = new StringBuilder( length );
        for( int i = 0; i < length; i++ )
            sb.append( numeric.charAt( rnd.nextInt(numeric.length()) ) );
        return sb.toString();
    }

    public static String generateRandomInvalidString(Integer length){

        StringBuilder sb = new StringBuilder( length );
        for( int i = 0; i < length; i++ )
            sb.append( invalidCharacters.charAt( rnd.nextInt(invalidCharacters.length()) ) );
        return sb.toString();
    }

    public static String generateRandomInvalidAlphaNumericString(Integer length){
       return generateRandomAlphanumericString(length/2) + generateRandomInvalidString(length/2);
    }

    public static Integer getRandomInteger(Integer maxValue){
        return r.nextInt((maxValue) );
    }

    public static String getFormatedDateForOrderConfirmation(String _date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.ENGLISH);
        Date date= sdf.parse(_date);
//specifies the pattern to print
        sdf.applyPattern("EEEEEEEE, dd MMMMMMMMM");
        return sdf.format(date);
    }

    public static boolean isValidDate(String _startDate, String _endDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("NZDT"));

        Date startDate = df.parse(_startDate);//2022-10-03
        Date endDate = df.parse(_endDate);

        Date currentDate = df.parse(df.format(new Date()));
        return (currentDate.after(startDate) && currentDate.before(endDate)) || (currentDate.equals(startDate) || currentDate.equals(endDate));
    }

    public static String getCurrentNZDT() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("NZDT"));

        Date currentDate = df.parse(df.format(new Date()));
        return String.valueOf(currentDate.getTime());
    }
}
