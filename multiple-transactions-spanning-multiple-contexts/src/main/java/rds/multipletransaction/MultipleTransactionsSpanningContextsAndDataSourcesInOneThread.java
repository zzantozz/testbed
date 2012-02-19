package rds.multipletransaction;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import rds.multipletransaction.one.FirstThing;
import rds.multipletransaction.two.SecondThing;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 2/18/12
 * Time: 6:11 PM
 */
public class MultipleTransactionsSpanningContextsAndDataSourcesInOneThread {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("/applicationContext1.xml");
        ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext("/applicationContext2.xml");
        FirstThing firstThing = context1.getBean(FirstThing.class);
        SecondThing secondThing = context2.getBean(SecondThing.class);
        firstThing.setSecondThing(secondThing);
        firstThing.go();
        firstThing.seeWhatsThere();
    }
}
