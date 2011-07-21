package rds.testbed;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/18/11
 * Time: 11:42 AM
 */
public class SetSystemPropertiesFromPropertiesFile {
    public static void main(String[] args) {
        Properties p = new Properties();
        //p.load(...); // Load the properties from somewhere
        for (String name : p.stringPropertyNames()) {
            String value = p.getProperty(name);
            System.setProperty(name, value);
        }
    }
}
