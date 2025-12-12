package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


public class Product {
    private UUID productId;

    private String name;

    private int price;

    private int quantity;

    private List<OrderItem> orderItemList;

    public Product(String name, int price, int quantity) {
        this.productId = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product( String name, int price, int quantity, List<OrderItem> orderItemList) {
        this.productId = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.orderItemList = orderItemList;
    }

    public UUID getProductId() {
        return productId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
