package utilities;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.json.JsonMapper;
import nw.features.api.FoodstuffsApi;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import utilities.DataObjects.DataManager;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class FileHelpers {

    public enum FileExtensions {

        PDF(".pdf"),
        ;

        private String extension;

        FileExtensions(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return extension;
        }
    }

    final static String LINE_SEPERATOR = "line.separator";

    public static String getTextFromFile(String relativePath) throws IOException {
        String content = null;
        InputStream inputStream = FileHelpers.class.getResourceAsStream(relativePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        content = reader.lines().collect(Collectors.joining(System.getProperty(LINE_SEPERATOR))).toString();
        reader.close();
        inputStream.close();
        return content;
    }

    public static String readFileAsString(String fileName) {
        try (InputStream inputStream = FileHelpers.class.getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            System.out.println("No File found");
            e.printStackTrace();
            return null;
        }
        /*File file = new File(fileName);

        try {
            return FileUtils.readFileToString(file,"UTF-8");
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }*/
    }

    public static File getFileFromRelativePath(String relativePath) {

        //Get the absolute path and create the file
        File file = null;
        String resource = relativePath;
        URL res = FileHelpers.class.getResource(resource);

        if (res == null) {
            throw new RuntimeException("Error: Unable to get the resource from path: " + resource);
        } else if (res.toString().startsWith("jar:")) {
            try {
                InputStream input = FileHelpers.class.getResourceAsStream(resource);
                file = File.createTempFile(new Date().getTime() + "", ".tmp");
                OutputStream out = new FileOutputStream(file);
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
                out.close();
                input.close();
                file.deleteOnExit();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            //this will probably work in your IDE, but not from a JAR
            file = new File(res.getFile());
        }

        if (file != null && !file.exists()) {
            throw new RuntimeException("Error: File " + file + " not found!");
        }
        return file;
    }

    public static String getPathOfExecutingJar() throws UnsupportedEncodingException {
        String absolutePath = FileHelpers.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
        absolutePath = absolutePath.replaceAll("%20", " ");
        return absolutePath;
    }

    public static void writeOutFileAsHTML(Document resource, String fileName) throws IOException {
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, resource.outerHtml(), "UTF-8");
    }

    public static void writeOutFile(String resource, String fileName) throws IOException {
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, resource, "UTF-8");
    }

    public static String readFileAsStrng(String fileName) {
        File file = new File(fileName);

        try {
            return FileUtils.readFileToString(file);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static InputStream readFileAsInputStrem(String fileName) {
        File file = new File(fileName);

        try {
            return FileUtils.openInputStream(file);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
