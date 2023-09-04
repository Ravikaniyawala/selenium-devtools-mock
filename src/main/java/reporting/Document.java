package reporting;

import org.testng.Reporter;
import testExecution.ConfigHelper;


public class Document {

    //region OUTPUT

    //Document Output to the Console as Well as the Report
    public void output(Behaviour behaviour, String statement) {
        System.out.println(behaviour.text() + statement);
        Reporter.log(behaviour.text() + statement);
    }

    public void output(String statement) {
        System.out.println(statement);
        Reporter.log(statement);
    }

    public void display(String statement) {
        System.out.println(statement);
    }

    public void SCENARIO_DETAILS(String scenario){
        SCENARIO(scenario);
    }

    public void DESIGN(String link){
        link = Behaviour.DESIGN.text() + link;
        System.out.println(link);
        Reporter.log(link);
    }

    public void SOT(){
        System.out.println("--------------------------------------------------------------------------------");
        Reporter.log("--------------------------------------------------------------------------------");
    }

    public void EOT(){
        System.out.println("--------------------------------------------------------------------------------");
        Reporter.log("--------------------------------------------------------------------------------");
    }

    public void SCENARIO(String scenario){
        scenario = Behaviour.SCENARIO.text() + scenario;
        System.out.println("\u001B[41m" + "\u001B[30m" + scenario + "\u001B[0m");
        Reporter.log(scenario);
    }

    public void EXPOSURE(Attribute.Exposure exposure){
        //System.out.println(Behaviour.EXPOSURE.text() + exposure.getDescription());
        Reporter.log(Behaviour.EXPOSURE.text() + exposure.getDescription());
    }

    public void RISK(Attribute.Risk risk){

        if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getDocumentation().equals("INFO")){
            //System.out.println(Behaviour.RISK.text() + risk.getDescription() + "\n");
            Reporter.log(Behaviour.RISK.text() + risk.getDescription() + "\n");
        }else {
            //System.out.println(Behaviour.RISK.text() + risk.getDescription());
            Reporter.log(Behaviour.RISK.text() + risk.getDescription());
        }
    }

    public void GIVEN(String given){

        if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getDocumentation().equals("ALL")){
            given = Behaviour.GIVEN.text() + given;
            System.out.println("\u001B[31m" + given + "\u001B[0m");
            Reporter.log(given);
        }
    }

    public void AND(String and){
        if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getDocumentation().equals("ALL")) {
            and = Behaviour.AND.text() + and;
            System.out.println("\u001B[31m" + and + "\u001B[0m");
            Reporter.log(and);
        }
    }

    public void WHEN(String when){

        if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getDocumentation().equals("ALL")) {
            when = Behaviour.WHEN.text() + when;
            System.out.println("\u001B[31m" + when + "\u001B[0m");
            Reporter.log(when);
        }
    }

    public void THEN(String then){

        if (ConfigHelper.TEST_EXECUTION_CONFIGURATION.getDocumentation().equals("ALL")) {
            then = Behaviour.THEN.text() + then;
            System.out.println("\u001B[31m" + then + "\u001B[0m");
            Reporter.log(then);
        }
    }

    //endregion

    //region GERKHIN SYNTAX
    public enum Behaviour {

        DESIGN ("  DESIGN: "),
        SERVICE(" SERVICE: "),
        SCENARIO("SCENARIO: "),
        VERSION(" VERSION: "),
        CAUSE("   CAUSE: "),
        EXPOSURE("EXPOSURE: "),
        FEATURE("\n FEATURE: "),
        GIVEN("   GIVEN: "),
        AND("     AND: "),
        WHEN("    WHEN: "),
        RISK("    RISK: "),
        STATUS("  STATUS: "),
        EXPECTED("    EXPECTED: "),
        ACTUAL("     ACTUAL: "),
        IMP_NOTE("     IMPORTANT NOTE: "),
        THEN("    THEN: "),
        DATA_LIST_KEY("          Key: "),
        DATA_LIST_VALUE("  Value: "),
        LIST_ITEM("     "),
        INDENT("          "),
        ERROR("AN ERROR: The system broke owing to the following exception - "),;

        private String bddStatement;

        Behaviour(String bddStatement) {
            this.bddStatement = bddStatement;
        }

        public String text() {
            return bddStatement;
        }
    }
    //endregion
}
