package Entity;

import DataStore.Data;
import lombok.*;

import java.util.List;
import java.util.UUID;


public class User {
    private UUID userId;

    private String userName;

    private List<Order> orders;

    public User(String userName) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
