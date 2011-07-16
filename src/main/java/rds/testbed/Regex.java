package rds.testbed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/14/11
 * Time: 8:33 PM
 */
public class Regex {
    public static void main(String[] args) {
        String[] strings = "foo@bar.com".split("@|\\.");
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
