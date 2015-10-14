package demo.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created by thibautvirolle on 12/10/15.
 */
public class PropertiesReader {

    String result = "";
    InputStream inputStream;

    public String getPropValues(String propertyName) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "application.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
            result = prop.getProperty(propertyName);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }


}
