package dao;

import java.util.UUID;

public class ProductSelection {
    private UUID pid;

    private int quantity;

    public ProductSelection(UUID pid, int quantity) {
        this.pid = pid;
        this.quantity = quantity;
    }

    public UUID getPid() {
        return pid;
    }

    public void setPid(UUID pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
