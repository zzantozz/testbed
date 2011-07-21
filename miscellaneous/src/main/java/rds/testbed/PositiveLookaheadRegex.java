package rds.testbed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/18/11
 * Time: 2:53 PM
 */
public class PositiveLookaheadRegex {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("@@FOO\\[(.*?)(?=~~)~~(.*?)(?=\\]@@)\\]@@");
        Matcher matcher = pattern.matcher("@@FOO[abc~~hi]@@    @@FOO[def~~hey]@@");
        int count = 0;
        while (matcher.find()) {
            System.out.println("Match " + ++count + ":");
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }
}
