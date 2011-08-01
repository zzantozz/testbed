package rds.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/31/11
 * Time: 8:47 PM
 */
@Component
public class JmsInteractor {
    @Autowired
    private ConnectionFactory connectionFactory;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void startProducer() {
        executorService.submit(new TextMessageProducer());
    }

    @PreDestroy
    public void stopExecutor() {
        executorService.shutdown();
    }

    public void methodThatReceivesMessages(String message) {
        System.out.format("Got message in thread [%s]: %s%n", Thread.currentThread().getName(), message);
    }

    private class TextMessageProducer implements Runnable {
        private int count = 1;
        @Override
        public void run() {
            System.out.println("Starting to send messages twice per second");
            try {
                Session session = connectionFactory.createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue queue = session.createQueue("test.queue");
                MessageProducer producer = session.createProducer(queue);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                while (true) {
                    TextMessage textMessage = session.createTextMessage("hello world " + count++);
                    producer.send(textMessage);
                    Thread.sleep(500);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
