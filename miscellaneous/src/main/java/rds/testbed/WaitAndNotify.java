package rds.testbed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/21/11
 * Time: 4:17 PM
 */
public class WaitAndNotify {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new One());
        executorService.execute(new Two());
    }

    static class One implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("(One) I own the lock");
                System.out.println("(One) Giving up the lock and waiting");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.err.println("(One) I shouldn't have been interrupted");
                }
                System.out.println("(One) I have the lock back now");
            }
        }
    }

    static class Two implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("(Two) I shouldn't have been interrupted");
            }
            synchronized (lock) {
                System.out.println("(Two) Now I own the lock (Two)");
                System.out.println("(Two) Giving up the lock using notify()");
                lock.notify();
            }
        }
    }
}
