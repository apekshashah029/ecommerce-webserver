package concurrency;

import dao.ProductSelection;
import dao.UserOrderData;
import datastore.Data;
import entity.User;
import input.ConsoleInput;
import service.OrderProcessingService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class OrderProcessor {
    private static final OrderProcessingService orderService = new OrderProcessingService();

    public static void init(){

        List<UserOrderData> finalData = ConsoleInput.takeconsoleInput();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<?>> futures = new ArrayList<>();

        for (UserOrderData data : finalData) {

            Future<?>future = executor.submit(() -> {

                User u1 = data.getUser();
                List<ProductSelection> selections = data.getSelections();

                for (ProductSelection ps : selections) {
                    UUID pid = ps.getPid();
                    int quantity = ps.getQuantity();

                    orderService.processOrder(u1, pid, quantity);
                }
            });

            futures.add(future);

        }

        for (Future<?> future : futures) {

            try {
                // in execution if any exception is thrown then at that time exception won't be displayed on console
                // because submit method wrap the exception and handle via ExecutionException
                // so need to use future.get() for cause of exception
                future.get();
            }catch (ExecutionException e ){
                System.out.println("Exception Occurred: "+e.getCause());
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }

        }
        executor.shutdown();

    }
}
