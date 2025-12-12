package concurrency;

import dao.ProductSelection;
import dao.UserOrderData;
import datastore.Data;
import entity.User;
import input.ConsoleInput;
import service.OrderProcessingService;

import java.util.List;
import java.util.UUID;

public class OrderProcessor {
    public static void init(){

        List<UserOrderData> finalData = ConsoleInput.takeconsoleInput();

        Thread t1 = new Thread(()->{
            User u1 = finalData.get(0).getUser();
            List<ProductSelection> ps1 = finalData.get(0).getSelections();
            for(ProductSelection ps :ps1){
                UUID id = ps.getPid();
                int quantity = ps.getQuantity();

                OrderProcessingService obj = new OrderProcessingService();
                obj.processOrder(u1,id,quantity);

            }
        });

        Thread t2 = new Thread(()->{
            User u1 = finalData.get(1).getUser();
            List<ProductSelection>ps1 = finalData.get(1).getSelections();
            for(ProductSelection ps :ps1){
                UUID id = ps.getPid();
                int quantity = ps.getQuantity();

                OrderProcessingService obj = new OrderProcessingService();
                obj.processOrder(u1,id,quantity);

            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            System.out.println("Exception occurred in thread");

        }

        Data.printAll();

    }
}
