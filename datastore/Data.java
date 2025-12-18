package datastore;

import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Data {

    public static List<User> users = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<OrderItem> orderItemList = new ArrayList<>();
    public static List<Transaction> transactionList = new ArrayList<>();

    public static Map<UUID,Product>mp = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(Data.class);
    public static final ReentrantLock PRODUCT_LOCK = new ReentrantLock();         // reentrant lock

    static  {

        logger.info("Initializing in-memory data");

        User u = new User("Apeksha");
        User u1 = new User("Ruchita");
        User u2 = new User("Arshi");
        User u3 = new User("Helly");

        Product p1 = new Product("Laptop", 50000, 4);
        Product p2 = new Product("Oil", 120, 12);
        Product p3 = new Product("Shuttle", 40, 3);
        Product p4 = new Product("Ball", 30, 12);


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

        for(Product p:products){
            mp.put(p.getProductId(),p);
        }

        for (Map.Entry<UUID, Product> entry : Data.mp.entrySet()) {
            System.out.println("Key: " + entry.getKey());
        }

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

        logger.info("Data initialization completed successfully");
    }

    public static void printUsers(){
        logger.info("\n"+"========== USERS ==========");
        for (User u : users) {
            logger.info("User ID: {}, Name: {}", u.getUserId(), u.getUserName());

            if (u.getOrders() != null) {
                for (Order o : u.getOrders()) {
                    logger.info("   Order ID: {}", o.getOrderId());

                    if (o.getOrderItemList() != null) {
                        for (OrderItem oi : o.getOrderItemList()) {
                            logger.info(
                                    "      Product: {} (ID {})",
                                    oi.getProduct().getName(),
                                    oi.getProduct().getProductId()
                            );
                        }
                    }

                    if (o.getTransactionList() != null) {
                        for (Transaction t : o.getTransactionList()) {
                            logger.info(
                                    "      Transaction ID: {}, Success: {}",
                                    t.getTransactionId(),
                                    t.isStatus()
                            );
                        }
                    }
                }
            }
        }
    }

    public static void printProducts(){
        logger.info("\n"+"========== PRODUCTS ==========");
        for (Product p : products) {
            logger.info(
                    "Product ID: {}, Name: {}, Price: {}, Quantity: {}",
                    p.getProductId(),
                    p.getName(),
                    p.getPrice(),
                    p.getQuantity()
            );

            if (p.getOrderItemList() != null && !p.getOrderItemList().isEmpty()) {
                logger.info("   Ordered in these orders:");
                for (OrderItem oi : p.getOrderItemList()) {
                    logger.info("      Order ID: {}", oi.getOrder().getOrderId());
                }
            }
        }
    }

    public static void printOrders(){

        logger.info("\n"+"========== ORDERS ==========");
        for (Order o : orders) {
            logger.info("Order ID: {}, User: {}", o.getOrderId(), o.getUser().getUserName());

            if (o.getOrderItemList() != null) {
                for (OrderItem oi : o.getOrderItemList()) {
                    logger.info("   Product: {}", oi.getProduct().getName());
                }
            }

            if (o.getTransactionList() != null) {
                for (Transaction t : o.getTransactionList()) {
                    logger.info(
                            "   Transaction ID: {}, Success: {}",
                            t.getTransactionId(),
                            t.isStatus()
                    );
                }
            }
        }

    }

    public static void printTransaction(){
        logger.info("\n"+"========== TRANSACTIONS ==========");
        for (Transaction t : transactionList) {
            logger.info(
                    "Transaction ID: {}, Order ID: {}, Success: {}",
                    t.getTransactionId(),
                    t.getOrder().getOrderId(),
                    t.isStatus()
            );
        }
    }

    public static void printOrderItems(){
        logger.info("\n"+"========== ORDER ITEMS ==========");
        for (OrderItem oi : orderItemList) {
            logger.info(
                    "Order ID: {}, Product: {}",
                    oi.getOrder().getOrderId(),
                    oi.getProduct().getName()
            );
        }
    }


    public static void printAll() {

        printUsers();
        printProducts();
        printOrders();
        printOrderItems();
        printTransaction();

    }
}
