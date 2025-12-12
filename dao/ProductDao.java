package dao;

import datastore.Data;
import entity.Product;

import java.util.UUID;

public class ProductDao {
    public static boolean checkProductQuantity(UUID pid, int quantity){
        boolean exist = Data.products.stream()
                .anyMatch(p -> p.getProductId().equals(pid) && p.getQuantity() >= quantity);

        if(exist){
            System.out.println("Product exist");
        }else{
            System.out.println("Product doesn't exist");
        }
        return exist;
    }

    synchronized public static void updateQuantity(UUID pid,int quantity){
        Product p = Data.products.stream()
                .filter(p1 -> p1.getProductId().equals(pid))
                .findFirst()
                .orElse(null);

        if(p!=null){
            p.setQuantity(p.getQuantity()-quantity);
            System.out.println("Product quantity updated : "+p.getQuantity());
        }else{
            System.out.println("product quantity not updated");
        }
    }

    public static Product getProduct(UUID pid){
        Product p = Data.products.stream()
                .filter(p1 -> p1.getProductId().equals(pid))
                .findFirst()
                .orElse(null);

        return p;
    }

}
