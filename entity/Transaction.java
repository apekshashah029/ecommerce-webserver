package entity;


import java.util.UUID;


public class Transaction {
    private UUID transactionId;

//    private Instant timestamp;

    private boolean status;

    private Order order;

    public Transaction(boolean status, Order order) {
        this.transactionId = UUID.randomUUID();
        this.status = status;
        this.order = order;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
