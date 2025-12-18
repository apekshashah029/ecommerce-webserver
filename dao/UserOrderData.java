package dao;


import entity.User;

import java.util.List;

public class UserOrderData {

    private User user;

    private List<ProductSelection> productSelectionList;

    public UserOrderData(User user, List<ProductSelection> selections) {
        this.user = user;
        this.productSelectionList = selections;
    }

    public User getUser(){
        return user;
    }

    public List<ProductSelection> getSelections() {
        return productSelectionList;
    }
}
