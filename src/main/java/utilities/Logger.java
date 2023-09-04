package utilities;

import reporting.CustomTableBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Logger {

    public static void writeToLog(CustomTableBuilder builder) throws Exception {
        String drive = FileHelpers.getPathOfExecutingJar().substring(1,3);
        String folderPath = drive + "/Logs";
        String filePath = folderPath + "/" + DateAndTime.getCurrentDateWithFormatYYY_MM_DD() + ".txt";
        if (!Files.isDirectory(Paths.get(folderPath))) {
            Files.createDirectories(Paths.get(folderPath));
        }
        builder.writeToFile(filePath);
    }

    public static void writeToLog(String message) {
        System.out.print(message + "\n");
        String drive = null;
        try {
            drive = FileHelpers.getPathOfExecutingJar().substring(1,3);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String folderPath = drive + "/Logs";
        String filePath = folderPath + "/" + DateAndTime.getCurrentDateWithFormatYYY_MM_DD() + ".txt";
        if (!Files.isDirectory(Paths.get(folderPath))) {
            try {
                Files.createDirectories(Paths.get(folderPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "utf-8"))) {
            writer.write(DateAndTime.getCurrentTime() + " " + message + "\n");
            writer.flush();
            writer.close();
        }catch (Exception e){

        };
    }
}
