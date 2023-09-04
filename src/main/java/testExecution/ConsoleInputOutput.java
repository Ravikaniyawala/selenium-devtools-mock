package testExecution;

public class ConsoleInputOutput {

    //region STATIC STRINGS
    private final static String CONFIG_DESCRIPTOR = "/configurations/CommandDescriptors.xml";
    private final static String COMMAND = "Command";
    private final static String COMMAND_DESC = "description";
    private final static String COMMAND_MESSAGE = "message";
    private final static String COMMAND_INSTRUCTIONS = "           COMMAND INSTRUCTIONS";
    private final static String COMMAND_NOMENCLATURE = "COMMAND TYPE: ";
    private final static String COMMAND_INSTRUCTION = "INSTRUCTION: ";
    private final static String AVAILABLE_COMMANDS = "AVAILABLE COMMANDS:";
    private final static String UNDER_SCORE_COMMAND_INSTRUCTIONS = "           --------------------\n";
    private final static String UNDER_SCORE_AVAILABLE_COMMANDS = "-------------------";
    //endregion


    public static void showOutput(String output) {
        System.out.println(output);
    }

    public static void displayLogo() throws InterruptedException {
        showOutput("\n");
        showOutput(" -----------------------------------------");
        showOutput("|        FSNI ONLINE WEB TESTS            |");
        showOutput(" -----------------------------------------");
        showOutput("\n");
        showOutput("                      |\\");
        showOutput("             \\`-. _.._| \\");
        showOutput("              |_,'  __`. \\");
        showOutput("              (.\\ _/.| _  |");
        showOutput("             ,'      __ \\ |");
        showOutput("           ,'     __/||\\  |");
        showOutput("          (o o  ,/|||||/  |");
        showOutput("             `-'_----    /");
        showOutput("                /`-._.-'/");
        showOutput("                `-.__.-'\n\n  ");
        showOutput("\n");
        showOutput(" ------------------------------------------");
        showOutput("|           BUGS BEWARE OF TESTS           |");
        showOutput(" ------------------------------------------");
        showOutput("\n");
        Thread.sleep(1000);
    }
}
