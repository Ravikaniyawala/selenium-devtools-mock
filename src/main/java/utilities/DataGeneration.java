package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataGeneration {

    public static String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }



}
