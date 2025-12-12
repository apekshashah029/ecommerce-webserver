package entity;


public class OrderItem {
    private Order order;
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderItem(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
}
