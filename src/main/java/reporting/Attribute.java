package reporting;


public class Attribute {

    public static final String SCENARIO_RISK_HIGH = "Attention ! - HIGH RISK SCENARIOS";
    public static final String SCENARIO_RISK_LOW = "LOW RISK";
    public static final String AUTOMATION_NOT_IMPLEMENTED = "Attention ! - NO AUTOMATION COVERAGE - WARNING";
    public static final String SCENARIO_STATUS_NOT_IMPLEMENTED = "Attention ! - UN-IMPLEMENTED SCENARIOS - WARNING";
    public static final String SCENARIO_STATUS_IMPLEMENTED = "IMPLEMENTED SCENARIOS";
    public static final String AUTOMATION_IMPLEMENTED = "TEST AUTOMATION COVERAGE";

    public static final String NO_AUTOMATION_OR_IMPLEMENTATION = "IMPLEMENTATION AND TEST AUTOMATION ARE PENDING ";

    public enum Risk{
        FATAL("Fatal"),
        HIGH("High"),
        MEDIUM("Medium"),
        LOW("Low"),
        ;
        private String description;

        Risk(String description){
            this.description = description;
        }
        public String getDescription(){
            return description;
        }
    }

    public enum Exposure{

        AVAILABILITY(Risk.FATAL, "Availability for Intended Usage."),
        INTENDED_USE(Risk.HIGH, "Intended Use"),
        CHANGE_IN_UNDERLYING_DEPENDENCIES(Risk.HIGH, "Changes or Non Conformity of the Underlying Dependencies (of the API)."),
        CHANGE_IN_CONSUMER_SOFTWARE_AND_HACK_ATTEMPTS(Risk.HIGH, "Key expiry, Hack Attempts, Change in Consumer Software."),
        CHANGE_IN_API_VERSION_AND_CONSUMER_SOFTWARE(Risk.MEDIUM, "Change in Host Infrastructure, Change in API (Version), Change in Consumer Software."),
        CHANGE_CONSUMER_SOFTWARE(Risk.MEDIUM, "Change in Consumer Software."),
        ;
        private String description;
        private Risk risk;

        Exposure(Risk risk, String description){
            this.risk = risk;
            this.description = description;
        }
        public Risk getRisk(){
            return risk;
        }
        public String getDescription(){
            return description;
        }
    }

}


