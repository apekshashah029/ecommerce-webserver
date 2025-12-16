package dao;

import datastore.Data;
import entity.Product;

import java.util.UUID;

public class ProductDao {
    public static boolean checkProductQuantity(UUID pid, int quantity){

        Product p = Data.mp.get(pid);
        if (p == null) {
            System.out.println("Product not found");
            return false;
        }
        boolean exist = p.getQuantity() >= quantity;

        if(exist){
            System.out.println("Product exist");
        }else{
            System.out.println("Insufficient Quantity");
        }
        return exist;
    }

    public static void updateQuantity(UUID pid,int quantity){
        Product p = Data.mp.get(pid);

        if(p!=null){
            p.setQuantity(p.getQuantity()-quantity);
            System.out.println("Product quantity updated : "+p.getQuantity());
        }else{
            System.out.println("product quantity not updated");
        }
    }

    public static Product getProduct(UUID pid){
        Product p = Data.mp.get(pid);
        return p;
    }

}
