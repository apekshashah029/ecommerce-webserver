package service;

import customException.FileWriteException;
import dao.ProductDao;
import datastore.Data;
import entity.*;
import fileOperation.OutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    public static void performTransaction(UUID pid, int quantity, Order o1, OrderItem oi1, User u1){
        Data.PRODUCT_LOCK.lock();

        // If a thread holds a lock and calls another method then that method executes under the same lock
        // until the lock is not released so no need to synchronize updateQuantity explicitly

        try {
            boolean exist1 = ProductDao.checkProductQuantity(pid, quantity);
            if (exist1) {
                System.out.println("Let's proceed for transaction: ");

                Transaction tx1 = new Transaction(true, o1);

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

                    try {
                        OutputStream.performWrite("Success.file", pid, quantity, u1, o1);
                    } catch (FileWriteException e) {
                        System.out.println(e.getMessage());
                    }

                } else {
                    try {
                        OutputStream.performWrite("Failure.file", pid, quantity, u1, o1);
                    } catch (FileWriteException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Transaction failed");
                }
            } else {
                System.out.println("Error: Product quantity changed");
            }
        }finally {
            Data.PRODUCT_LOCK.unlock();
        }

    }
}
