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
        Pattern pattern = Pattern.compile("^(?=.{8,})(?=.*\\d{2,}).*$");
        System.out.println(pattern.matcher("a2cd1fgh").matches());
//        String[] strings = "foo@bar.com".split("@|\\.");
//        for (String string : strings) {
//            System.out.println(string);
//        }
    }
}
