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
    public static void performWrite(String file, String text) throws FileWriteException{
        try (FileOutputStream output = new FileOutputStream(file, true)) {

            output.write(text.getBytes());

        } catch (IOException e) {
            throw new FileWriteException("Error in Writing file");
        }
    }
}
