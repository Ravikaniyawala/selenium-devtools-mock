package testExecution;

public class ApplicationLauncher {

    public final static String MESSAGE_SUPPLY_COMMAND = "\nENTER A COMMAND FROM THE LIST OF COMMANDS: \n";

    public static void main(String[] args) throws Exception {

        String command = new String();
        for ( int i = 0; i < args.length; i++ ){
            if ((i != args.length-1)){
                command += args[i] + " ";
            }else
            {
                command += args[i];
            }
        }
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.processCommand(command);
    }
}