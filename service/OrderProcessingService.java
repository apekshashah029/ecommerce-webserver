package service;

import dao.ProductDao;
import datastore.Data;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderProcessingService {
    public void processOrder(User u1, UUID pid, int quantity) {
        boolean exist = ProductDao.checkProductQuantity(pid, quantity);
        if (exist) {

            Product p = ProductDao.getProduct(pid);

            Order o1 = new Order(u1);
            OrderItem oi1 = new OrderItem(o1, p);

            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(oi1);
            o1.setOrderItemList(orderItems);

            List<Order> orders = u1.getOrders();
            if (orders == null) orders = new ArrayList<>();
            orders.add(o1);
            u1.setOrders(orders);

            List<OrderItem> productOrderItems = p.getOrderItemList();
            if (productOrderItems == null) productOrderItems = new ArrayList<>();
            productOrderItems.add(oi1);
            p.setOrderItemList(productOrderItems);

            TransactionService.performTransaction(pid,quantity,o1,oi1,u1);

        }
    }
}
