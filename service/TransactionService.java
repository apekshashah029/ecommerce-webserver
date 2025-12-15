package service;

import dao.ProductDao;
import datastore.Data;
import entity.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    public synchronized static void performTransaction(UUID pid, int quantity, Order o1, OrderItem oi1, User u1){
        boolean exist1 = ProductDao.checkProductQuantity(pid,quantity);
        if(exist1) {
            System.out.println("Let's proceed for transaction: ");

            Transaction tx1 = new Transaction(false, o1);

            List<Transaction> txList = o1.getTransactionList();
            if (txList == null) txList = new ArrayList<>();
            txList.add(tx1);
            o1.setTransactionList(txList);


            if (tx1.isStatus()) {
                ProductDao.updateQuantity(pid, quantity);
                Data.orders.add(o1);
                Data.transactionList.add(tx1);
                Data.users.add(u1);
                Data.orderItemList.add(oi1);

                try (FileOutputStream output = new FileOutputStream("Success.txt", true)) {

                    Product p1 = ProductDao.getProduct(pid);

                    String text =
                                    "User Name   : " + u1.getUserName() + "\n" +
                                    "Product     : " + p1.getName() + "\n" +
                                    "Quantity    : " + quantity + "\n" +
                                    "Order ID    : " + o1.getOrderId() + "\n" +
                                    "Product ID  : " + pid + "\n" +
                                    "Total Price : " + p1.getPrice()*quantity + "\n" +
                                    "Updated Quantity : " + p1.getQuantity() + "\n" + "\n";

                    output.write(text.getBytes());

                } catch (IOException e) {
                    System.out.println("Error writing file");
                    e.printStackTrace();
                }


            } else {
                try (FileOutputStream output = new FileOutputStream("Failure.txt", true)) {

                    Product p1 = ProductDao.getProduct(pid);

                    String text =
                                    "User Name   : " + u1.getUserName() + "\n" +
                                    "Product     : " + p1.getName() + "\n" +
                                    "Quantity    : " + quantity + "\n" +
                                    "Order ID    : " + o1.getOrderId() + "\n" +
                                    "Product ID  : " + pid + "\n" +
                                    "Total Price : " + p1.getPrice()*quantity + "\n" +
                                    "Original Quantity : " + p1.getQuantity() + "\n" + "\n";

                    output.write(text.getBytes());

                } catch (IOException e) {
                    System.out.println("Error writing file");
                    e.printStackTrace();
                }

                System.out.println("Transaction failed");
            }
        }else {
            System.out.println("Error: Product quantity changed");
        }

    }
}
