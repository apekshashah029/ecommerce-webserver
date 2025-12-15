package concurrency;

import dao.ProductSelection;
import dao.UserOrderData;
import datastore.Data;
import entity.User;
import input.ConsoleInput;
import service.OrderProcessingService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderProcessor {
    public static void init(){

        List<UserOrderData> finalData = ConsoleInput.takeconsoleInput();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (UserOrderData data : finalData) {

            executor.submit(() -> {

                User u1 = data.getUser();
                List<ProductSelection> selections = data.getSelections();

                for (ProductSelection ps : selections) {
                    UUID pid = ps.getPid();
                    int quantity = ps.getQuantity();

                    OrderProcessingService service = new OrderProcessingService();
                    service.processOrder(u1, pid, quantity);
                }
            });
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(5, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }



    }
}
