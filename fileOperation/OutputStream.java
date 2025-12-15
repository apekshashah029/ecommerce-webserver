package fileOperation;

import customException.FileWriteException;
import dao.ProductDao;
import entity.Order;
import entity.Product;
import entity.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class OutputStream {
    public static void performWrite(String file, UUID pid, int quantity, User u1, Order o1) throws FileWriteException{
        try (FileOutputStream output = new FileOutputStream(file, true)) {

            Product p1 = ProductDao.getProduct(pid);

            String text =
                    "User Name   : " + u1.getUserName() + "\n" +
                            "Product     : " + p1.getName() + "\n" +
                            "Quantity    : " + quantity + "\n" +
                            "Order ID    : " + o1.getOrderId() + "\n" +
                            "Product ID  : " + pid + "\n" +
                            "Total Price : " + p1.getPrice()*quantity + "\n" +
                            "Updated Quantity : " + p1.getQuantity() + "\n" + "\n";

            output.write(text.getBytes());

        } catch (IOException e) {
            throw new FileWriteException("Error in Writing file");
        }
    }
}
