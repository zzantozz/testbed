package rds.testbed;

import java.util.concurrent.*;

public class ThreadSignaling {
    public static void main(String[] args) {
        BlockingQueue<Integer> evens = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> odds = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> takeAndOfferNext(evens, odds));
        executorService.submit(() -> takeAndOfferNext(odds, evens));
        evens.offer(0);
    }

    private static void takeAndOfferNext(BlockingQueue<Integer> takeFrom,
                                         BlockingQueue<Integer> offerTo) {
        while (true) {
            try {
                int i = takeFrom.take();
                System.out.println(i);
                offerTo.offer(i + 1);
            } catch (InterruptedException e) {
                throw new IllegalStateException("Unexpected interrupt", e);
            }
        }
    }
}
