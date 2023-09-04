package utilities;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateAndTime {

    public static final String HR_MIN_SEC_MS = "%02d:%02d:%02d:%d";
    public static final String TIME_FORMAT_HR_MIN_SEC_MS = "HH:mm:ss";
    private static final String DAY_OF_THE_WEEK_FORMAT = "EEEE";
    private static final String DATE_FORMAT_1 = "yyyy-MM-dd";
    private static final SimpleDateFormat YEAR_MONTH_DAY_HRS_MIN_ = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    public static final String EFFECTIVE_DATE_FORMAT = "yyyy-MM-dd";

    public static String getCurrentDateWithFormatYYY_MM_DD(){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    public static String getCurrentTime(){

        Date date = new Date();
        return new SimpleDateFormat("HH:mm:ss").format(date.getTime()).toString();
    }


    public static String getDatePlusOrMinusDaysWithFormatYYY_MM_DD(Integer offsetDays){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        Date date = new Date();
        date = addDaysToDate(date, offsetDays);
        return dateFormat.format(date).toString();
    }

    public static String  getDatePlusOrMinusMonthsWiWthFormatYYY_MM_DD(Integer offsetMonths){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        Date date = new Date();
        date = addMonthsToDate(date, offsetMonths);
        return dateFormat.format(date).toString();
    }


    public static String  getDatePlusOrMinusYearsWiWthFormatYYY_MM_DD(Integer offsetYears){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        Date date = new Date();
        date = addYearsToDate(date, offsetYears);
        return dateFormat.format(date).toString();
    }

    public static Date addDaysToDate(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date addMonthsToDate(Date date, int months)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }


    public static Date addYearsToDate(Date date, int years)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    public static String getFormattedTime(String format, long milliSeconds){

        try {
            long second = (milliSeconds / 1000) % 60;
            long minute = (milliSeconds / (1000 * 60)) % 60;
            long hour = (milliSeconds / (1000 * 60 * 60)) % 24;
            return String.format(format, hour, minute, second, milliSeconds);

        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public static String getDayOfTheWeekName(String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(EFFECTIVE_DATE_FORMAT);
        Date dateObject = dateFormat.parse(date);
        dateFormat.applyPattern(DAY_OF_THE_WEEK_FORMAT);
        return dateFormat.format(dateObject);
    }

    public static Integer getDayOfTheWeek(String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(EFFECTIVE_DATE_FORMAT);
        Date dateObject = dateFormat.parse(date);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("PST"));
        cal.setTime(dateObject);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getNextDay(String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        Date dateObject = dateFormat.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateObject);
        cal.add(Calendar.DATE, 1);
        return dateFormat.format(cal.getTime());
    }

    public static String getNextWeek(String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        Date dateObject = dateFormat.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateObject);
        cal.add(Calendar.DATE, 7);
        return dateFormat.format(cal.getTime());
    }

    public static String getCurrentTimeStampZoneBased(){

        Date date = new Date();
        return new Timestamp(date.getTime()).toString();
    }

    public static String convertMillisToDateTimeStamp(long millis){
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(cal.getTime());
    }


}
