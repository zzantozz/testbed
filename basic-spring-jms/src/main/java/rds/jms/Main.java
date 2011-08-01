package rds.jms;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.store.memory.MemoryPersistenceAdapter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/31/11
 * Time: 5:55 PM
 */
public class Main {
    private static BrokerService broker;
    private static ClassPathXmlApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        startAJmsServer();
        startSpring();
        waitForShutdownCommand();
    }

    private static void startAJmsServer() throws Exception {
        System.out.println("Starting an ActiveMQ message broker");
        broker = new BrokerService();
        broker.start();
    }

    private static void startSpring() {
        System.out.println("Starting Spring");
        applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
    }

    private static void waitForShutdownCommand() throws Exception {
        System.out.println("Done with startup; enter to exit");
        while (System.in.available() == 0) {
            Thread.sleep(250);
        }
        System.out.println("Shutting down by request");
        applicationContext.close();
        broker.stop();
    }
}
