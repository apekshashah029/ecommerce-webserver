package service;

import dao.ProductDao;
import datastore.Data;
import entity.Order;
import entity.OrderItem;
import entity.Transaction;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    public synchronized static void performTransaction(UUID pid, int quantity, Order o1, OrderItem oi1, User u1){
        boolean exist1 = ProductDao.checkProductQuantity(pid,quantity);
        if(exist1) {
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
            } else {
                System.out.println("Transaction failed");
            }
        }else {
            System.out.println("Error: Product quantity changed");
        }

    }
}
