package service;

import repository.IssuedBooksRepository;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskService {

    private final IssuedBooksRepository issuedBooksRepository;
    private final ScheduledExecutorService scheduler;

    public ScheduledTaskService() {
        this.issuedBooksRepository = new IssuedBooksRepository();
        this.scheduler = Executors.newScheduledThreadPool(1);
        scheduleRenewCountDecrement();
    }

    private void scheduleRenewCountDecrement() {
        scheduler.scheduleAtFixedRate(() -> {
            boolean success = issuedBooksRepository.decrementRenewCount();
            if (success) {
                System.out.println("Successfully decremented renew counts.");
            } else {
                System.out.println("Failed to decrement renew counts.");
            }
        }, 0, 24, TimeUnit.HOURS);
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }
}
