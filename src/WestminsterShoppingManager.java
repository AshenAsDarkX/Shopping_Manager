import java.util.HashMap;
import java.util.Scanner;

public class WestminsterShoppingManager {
    public static void main(String[] args) {

            Stock stock = new Stock();
            Users users = new Users();
        while(true){
            Scanner sc= new Scanner(System.in);
            System.out.println("Menu");
            System.out.println("""
                If you are a user press '1'
                If you are the manager press '2'
                If you like to exit press '3'""");

            String choice = sc.nextLine();

            if (choice.equals("1")){
                regLoop:
                while(true){
                    System.out.println("""
                            What do you wanna do?
                            Log in 1
                            Register 2""");
                    String regChoice = sc.nextLine();

                    switch (regChoice){
                        case "1":
                            logIn(users, stock);
                            break regLoop;
                        case "2":
                            register(users);
                            break regLoop;
                        default:
                            System.out.println("Please enter a valid input !");
                            break;
                    }
                }

            }
            if (choice.equals("2")){
                System.out.println("""
                     What do you wanna do?
                     add a new product press 1
                     remove a product press 2
                     print the list press 3
                     save to a file press 4
                     """);
                String proType = sc.nextLine();
                switch (proType.toUpperCase()){
                    case "1":
                        System.out.println("""
                               Please enter the type of your product
                                    if electronics press 1
                                    if clothes press 2
                               """);
                        String type = sc.nextLine();
                        if(type.equals("1")){
                            addEleProduct(stock);
                        }
                        else if(type.equals("2")){
                            addCloProduct(stock);
                        }
                        else {
                            System.out.println("Please enter a valid input !");
                        }
                        break;

                    case "2":
                        System.out.println("List of available products");
                        stock.viewProList();
                        System.out.println("Which item do you wanna delete ?");
                        System.out.println("Please enter the id of the product ");
                        String key = sc.nextLine();
                        stock.delProduct(key);
                        break;
                    case "3":
//                     exit = true;
                        break;

                    case "4":
                        viewProduct(stock);
                        break;


                    default:
                        System.out.println("Please enter a valid input (-_-)");
                        break;
                }
            }
            if (choice.equals("3")){

            }
        }
//         return exit;

    }
    public static void addEleProduct(Stock storeStock){
        Scanner scan = new Scanner(System.in);
        String proId;
        String proName;
        String proBrand;
        String proWarranty;
        double proPrice;
        int quantity = 0;

        while (true) {
            System.out.print("Enter product Id : ");
            proId = scan.nextLine();
            if (proId.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT ID cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("Enter product name : ");
            proName = scan.nextLine();
            if (proName.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT NAME cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("Enter product brand : ");
            proBrand = scan.nextLine();
            if (proBrand.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT BRAND cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("Enter products' warranty period : ");
            proWarranty = scan.nextLine();
            if (proWarranty.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! Electronic PRODUCT must have a warranty period !\n");
            }
        }
        while (true) {
            System.out.print("Enter products' price : ");
            proPrice = scan.nextDouble();
            if (proPrice != 0) {
                break;
            } else {
                System.out.println("WARNING!! product must have a price !\n");
            }
        }
        while (true) {
            System.out.print("Enter quantity : ");
            proPrice = scan.nextDouble();
            if (proPrice != 0) {
                break;
            } else {
                System.out.println("WARNING!! must have a quantity !\n");
            }
        }
        Electronics eleItem = new Electronics(proId,proName,proPrice,quantity,proBrand,proWarranty);
        storeStock.addProduct(proId, eleItem);
        System.out.println("Product has added !");
        }
    public static void addCloProduct(Stock storeStock){
        Scanner scan = new Scanner(System.in);
        String proId;
        String proName;
        String proSize;
        String proColor;
        double proPrice;
        int quantity = 0;
        while (true) {
            System.out.print("Enter product Id : ");
            proId = scan.nextLine();
            if (proId.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT ID cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("Enter product name : ");
            proName = scan.nextLine();
            if (proName.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT NAME cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("Enter clothes' size : ");
            proSize = scan.nextLine();
            if (proSize.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT SIZE cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("Enter cloths' color : ");
            proColor = scan.nextLine();
            if (proColor.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! Cloth must have a color !\n");
            }
        }
        while (true) {
            System.out.print("Enter products' price : ");
            proPrice = scan.nextDouble();
            if (proPrice != 0) {
                break;
            } else {

                System.out.println("WARNING!! product must have a price !\n");
            }
        }
        while (true) {
            System.out.print("Enter quantity : ");
            proPrice = scan.nextDouble();
            if (proPrice != 0) {
                break;
            } else {
                System.out.println("WARNING!! must have a quantity !\n");
            }
        }

        Clothes cloItem = new Clothes(proId,proName,proPrice,quantity,proSize,proColor);
        storeStock.addProduct(proId, cloItem);
        System.out.println("Product has added !");
    }
    public static void viewProduct(Stock cart){
        cart.viewProList();
    }
    public static void register(Users users){
        Scanner sc = new Scanner(System.in);
        String userName;
        String password;
        while (true) {
            System.out.print("Please enter a user name : ");
            userName = sc.nextLine();
            if (userName.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! must have a quantity !\n");
            }
        }
        while (true) {
            System.out.print("Please enter a password : ");
            password = sc.nextLine();
            if (password.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! must have a quantity !\n");
            }
        }
        User user = new User(userName,password);
        users.addUser(userName,user);
        users.viewUserList();
    }

    public static void logIn(Users users, Stock stock){
        Scanner sc = new Scanner(System.in);
        String userName;
        String password;
        while (true) {
            System.out.print("Please enter your user name : ");
            userName = sc.nextLine();
            System.out.print("Please enter your password : ");
            password = sc.nextLine();
            if(users.login(userName, password)){
                System.out.println("You're logged in !");
                loggedInUser(users, stock);
                break;
            }
            else {
                System.out.println("Incorrect username password combination !");
            }

        }

    }

    public static void loggedInUser(Users users, ProductList stock){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                What do u wanna do?
                add a product in to cart 1
                view cart 2
                remove product 3""");
        String choice = sc.nextLine();

        switch (choice){
            case "1":
                System.out.println("""
                        What kind of product u want?
                        1. Electronic
                        2. Clothes""");
                String proType = sc.nextLine();
                if(proType.equals("1")){
                    stock.getElectronics();
                    System.out.println("""
                            Please enter the product id of your chosen product :""");
                    String proId = sc.nextLine();
                    stock.viewProduct(proId);
                    
                }
                if(proType.equals("2")){
                    stock.getElectronics();
                }
            case "2":

        }
    }
}