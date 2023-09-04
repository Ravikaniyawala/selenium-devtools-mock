package utilities;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

public class AwsS3Operation {
    public static AmazonS3 s3client;
    public static String offersDate = "";
    public static String priceDate= "";
    public static String productsDate = "";
    public static String rangeDate= "";
    public static String rangeJsonDate= "";

    static {
        AWSCredentials credentials;
        ConfigurationManager.loadTestConfig();
        if(SystemUtils.IS_OS_LINUX) {
            credentials = new BasicAWSCredentials(
                    Session.isQa ? ConfigurationManager.awsQaAccessKey : ConfigurationManager.awsPreprodAccessKey,
                    Session.isQa ? ConfigurationManager.awsQaSecretKey : ConfigurationManager.awsPreprodSecretKey
            );
        }else{
            credentials = new BasicAWSCredentials(
                    Session.isQa?System.getenv("awsQaAccessKey"):System.getenv("awsPreprodAccessKey"),
                    Session.isQa?System.getenv("awsQaSecretKey"):System.getenv("awsPreprodSecretKey")
            );


        }


        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.DEFAULT_REGION)
                .withClientConfiguration(new ClientConfiguration().withMaxConnections(10)//
                .withConnectionTimeout(10 * 1000)//
                .withMaxErrorRetry(4))//
                .build();
    }

    public static boolean isNwExportFilesUpdated() throws IOException {
        S3ObjectInputStream inputStream;
        S3Object s3object;

        Path productPath = Paths.get("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "a.json");
        Path rangeCsvPath = Paths.get("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "b.csv");
        Path rangePath = Paths.get("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "c.json");
        Path offersPath = Paths.get("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "d.json");


        s3object = s3client.getObject(Session.isNwQa?"qa-bucket":Session.isNwPreprod?"preprod-bucket":"dev-bucket", "abc.json");
        System.out.println(s3object.getObjectMetadata().getLastModified());

        try{
            Files.getAttribute(productPath, "lastModifiedTime").toString();
        }catch (NoSuchFileException n){
            File myFile = new File(productPath.toString());
            FileUtils.touch(myFile);
        }

        if(!s3object.getObjectMetadata().getLastModified().toInstant().toString().equals(Files.getAttribute(productPath, "lastModifiedTime").toString())) {
            return true;
        }else{
            System.out.println("abc.json S3 file is uptodate...");
        }

        s3object.close();

        return false;
    }

    public static void downloadNwS3Object() throws IOException {


        S3ObjectInputStream inputStream;
        S3Object s3object;

        Path productPath = Paths.get("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "a.json");
        Path rangeCsvPath = Paths.get("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "b.csv");
        Path rangePath = Paths.get("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "c.json");
        Path offersPath = Paths.get("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "d.json");


        s3object = s3client.getObject(Session.isNwQa?"qa-bucket":Session.isNwPreprod?"preprod-bucket":"dev-bucket", "a.json");
        System.out.println(s3object.getObjectMetadata().getLastModified());

        try{
            Files.getAttribute(productPath, "lastModifiedTime").toString();
        }catch (NoSuchFileException n){
            File myFile = new File(productPath.toString());
            FileUtils.touch(myFile);
        }

        if(!s3object.getObjectMetadata().getLastModified().toInstant().toString().equals(Files.getAttribute(productPath, "lastModifiedTime").toString())) {
            inputStream = s3object.getObjectContent();
            FileUtils.copyInputStreamToFile(inputStream, new File("target/classes/testData/" + ConfigurationManager.brand.toLowerCase() + "/" + ConfigurationManager.env.toLowerCase() + "/" + "a.json"));
            productsDate = s3object.getObjectMetadata().getLastModified().toString();
            //FileTime now = FileTime.from(s3object.getObjectMetadata().getLastModified().toInstant());
            Files.setAttribute(productPath, "lastModifiedTime", FileTime.from(s3object.getObjectMetadata().getLastModified().toInstant()));
        }else{
            System.out.println("Products.json S3 file is uptodate...");
        }

    }
}
