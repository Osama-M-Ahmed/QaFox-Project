package Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static String readValue(String key) {

        String value="";
        try (InputStream input = new FileInputStream("./src/test/resources/data/data.properties")) {
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            value = prop.getProperty(key);

        } catch (Exception e) {

        }
        return value;
    }





}
