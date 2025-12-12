package Entity;

import java.util.List;
import java.util.UUID;

public class Order {

    private UUID orderId;
    private User user;
    private List<OrderItem> orderItemList;
    private List<Transaction> transactionList;

    public Order(User user) {
        this.orderId = UUID.randomUUID();
        this.user = user;
    }

    public Order(User user, List<OrderItem> orderItemList, List<Transaction> transactionList) {
        this.orderId = UUID.randomUUID();
        this.user = user;
        this.orderItemList = orderItemList;
        this.transactionList = transactionList;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
