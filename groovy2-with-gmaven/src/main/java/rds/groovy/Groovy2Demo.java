package rds.groovy;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/26/13
 * Time: 10:00 PM
 */
public class Groovy2Demo {
    public static void main(String[] args) {
        Groovy2 g = new Groovy2();
        g.sayHi();
        String s = g.someString();
        System.out.println(s);
        g.sayBye();
    }
}
