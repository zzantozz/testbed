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
        String openbrace = Pattern.quote("{");
        String closebrace = Pattern.quote("}");
        Pattern p = Pattern.compile(openbrace + "[ ]?\"(.*?)\"[ ]?,[ ]?\"(.*?)\"[ ]?" + closebrace);

//        Pattern p = Pattern.compile("\\{\\s*\"(.*?)\"\\s*,\\s*\"(.*?)\"\\s*\\}");
        Matcher m = p.matcher("{ \"working\", \"working\"}");

        while(m.find())
        {
            System.out.println(m.start(1) + " - " + m.end(1));
            System.out.println(m.start(2) + " - " + m.end(2));
        }
    }
}
