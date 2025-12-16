package input;

import customException.InvalidFormatException;
import dao.ProductSelection;
import dao.UserOrderData;
import datastore.Data;
import entity.Product;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleInput {
    public static List<UserOrderData> takeconsoleInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of users : ");
        int n = sc.nextInt();


        List<UserOrderData> finalData = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            System.out.println("Enter name: ");
            Scanner sc1 = new Scanner(System.in);

            String name = sc1.nextLine();

            User u = new User(name);

            System.out.println("Choose Products from below:");
            for (Product p : Data.products) {
                System.out.println("Product ID: " + p.getProductId() +
                        ", Name: " + p.getName() +
                        ", Price: " + p.getPrice() +
                        ", Quantity: " + p.getQuantity());
            }

            List<ProductSelection> selections = new ArrayList<>();

            while (true) {

                System.out.println("\nEnter Product UUID or type 'exit' to stop: ");
                String input = sc1.nextLine();

                if (input.equals("exit")) {
                    break;
                }

                try{
                    InvalidFormatException.isValid(input);
                }catch (InvalidFormatException e){
                    System.out.println(e.getMessage());
                    continue;
                }

                UUID pid = UUID.fromString(input);
                try {
                    int originalQuantity = Data.mp.get(pid).getQuantity();

                }catch (NullPointerException e){
                    System.out.println("Please enter existing UUID");
                    continue;
                }


                System.out.println("Enter Product quantity: ");
                int quantity = Integer.parseInt(sc1.nextLine());

                selections.add(new ProductSelection(pid, quantity));
            }

            finalData.add(new UserOrderData(u, selections));
        }
        return finalData;
    }
}
