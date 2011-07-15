package rds.testbed;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/13/11
 * Time: 4:08 PM
 */
public class PrintNewlines {
    public static void main(String[] args) {
        System.out.println(1);
        System.out.println(String.format("%5s", System.getProperty("line.separator")));
        System.out.println(2);
    }
}
