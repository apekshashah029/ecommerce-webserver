package datastore;

import entity.*;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<User> users = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<OrderItem> orderItemList = new ArrayList<>();
    public static List<Transaction> transactionList = new ArrayList<>();

    public void createData() {

        User u = new User("Apeksha");
        User u1 = new User( "Ruchita");
        User u2 = new User("Arshi");
        User u3 = new User( "Helly");

        Product p1 = new Product("Laptop", 50000, 4);
        Product p2 = new Product("Oil", 120, 12);
        Product p3 = new Product("Shuttle", 40, 3);
        Product p4 = new Product("ball", 30, 12);

        Order o1 = new Order(u);
        Order o2 = new Order(u);
        Order o3 = new Order(u1);

        u.setOrders(new ArrayList<>(List.of(o1, o2)));
        u1.setOrders(new ArrayList<>(List.of(o3)));

        users.addAll(List.of(u, u1, u2, u3));

        OrderItem oi1 = new OrderItem(o1, p1);
        OrderItem oi2 = new OrderItem(o2, p3);
        OrderItem oi3 = new OrderItem(o3, p1);
        OrderItem oi4 = new OrderItem(o1, p2);

        orderItemList.addAll(List.of(oi1, oi2, oi3, oi4));

        p1.setOrderItemList(new ArrayList<>(List.of(oi1, oi3)));
        p2.setOrderItemList(new ArrayList<>(List.of(oi4)));
        p3.setOrderItemList(new ArrayList<>(List.of(oi2)));
        p4.setOrderItemList(new ArrayList<>());

        products.addAll(List.of(p1, p2, p3, p4));

        o1.setOrderItemList(new ArrayList<>(List.of(oi1, oi4)));
        o2.setOrderItemList(new ArrayList<>(List.of(oi2)));
        o3.setOrderItemList(new ArrayList<>(List.of(oi3)));

        Transaction tx1 = new Transaction(false, o1);
        Transaction tx2 = new Transaction(true, o1);
        Transaction tx3 = new Transaction(true, o2);
        Transaction tx4 = new Transaction(true, o3);

        transactionList.addAll(List.of(tx1, tx2, tx3, tx4));

        o1.setTransactionList(new ArrayList<>(List.of(tx1, tx2)));
        o2.setTransactionList(new ArrayList<>(List.of(tx3)));
        o3.setTransactionList(new ArrayList<>(List.of(tx4)));

        orders.addAll(List.of(o1, o2, o3));
    }


    public static void printAll() {

        System.out.println("========== USERS ==========");
        for (User u : Data.users) {
            System.out.println("User ID: " + u.getUserId() + ", Name: " + u.getUserName());

            if (u.getOrders() != null) {
                for (Order o : u.getOrders()) {
                    System.out.println("   Order ID: " + o.getOrderId());

                    if (o.getOrderItemList() != null) {
                        for (OrderItem oi : o.getOrderItemList()) {
                            System.out.println("      Product: " + oi.getProduct().getName() +
                                    " (ID " + oi.getProduct().getProductId() + ")");
                        }
                    }

                    if (o.getTransactionList() != null) {
                        for (Transaction t : o.getTransactionList()) {
                            System.out.println("      Transaction ID: " + t.getTransactionId() +
                                    ", Success: " + t.isStatus());
                        }
                    }
                }
            }
        }


        System.out.println("\n========== PRODUCTS ==========");
        for (Product p : Data.products) {
            System.out.println("Product ID: " + p.getProductId() +
                    ", Name: " + p.getName() +
                    ", Price: " + p.getPrice() +
                    ", Quantity: " + p.getQuantity());

            if (p.getOrderItemList() != null) {
                System.out.println("   Ordered in these orders:");
                for (OrderItem oi : p.getOrderItemList()) {
                    System.out.println("      Order ID: " + oi.getOrder().getOrderId());
                }
            }
        }


        System.out.println("\n========== ORDERS ==========");
        for (Order o : Data.orders) {
            System.out.println("Order ID: " + o.getOrderId() +
                    ", User: " + o.getUser().getUserName());

            if (o.getOrderItemList() != null) {
                for (OrderItem oi : o.getOrderItemList()) {
                    System.out.println("   Product: " + oi.getProduct().getName());
                }
            }

            if (o.getTransactionList() != null) {
                for (Transaction t : o.getTransactionList()) {
                    System.out.println("   Transaction ID: " + t.getTransactionId() +
                            ", Success: " + t.isStatus());
                }
            }
        }


        System.out.println("\n========== TRANSACTIONS ==========");
        for (Transaction t : Data.transactionList) {
            System.out.println("Transaction ID: " + t.getTransactionId() +
                    ", Order: " + t.getOrder().getOrderId() +
                    ", Success: " + t.isStatus());
        }


        System.out.println("\n========== ORDER ITEMS ==========");
        for (OrderItem oi : Data.orderItemList) {
            System.out.println("Order ID: " + oi.getOrder().getOrderId() +
                    ", Product: " + oi.getProduct().getName());
        }
    }

}
