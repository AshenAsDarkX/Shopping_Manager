import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    public static void main(String[] args) {
        
         Stock stock = new Stock();
            Users users = new Users();

        //Prints a welcome message to the console
        System.out.println("""
                    Welcome to Westminster Shopping Manager...
                ----------------------------------------------------------""");
        while(true){

            Scanner sc= new Scanner(System.in);// Initializing a scanner to scan inputs
//            Showing the instruction to the user
            System.out.println("""
                If you are a customer press      '1'
                If you are the manager press     '2'
                If you want to save data press   '3'
                If you want to load data press   '4'
                If you like to exit press        '5'""");
            System.out.println(" ");
            System.out.print("\nPlease enter your choice :");
            String choice = sc.nextLine();
            mainloop:
            if (choice.equals("1")){
                regLoop:
                while(true){
                    System.out.println("""
                            If you have already registered press '1' to log in!
                            Still not Registered press '2' to register!
                            Or else you want to go back to main menu press '3'""");
                    System.out.print("\nPlease enter your choice :");
                    String regChoice = sc.nextLine();

                    switch (regChoice){
                        case "1":
                            logIn(users, stock);//Method for login
                            break regLoop;
                        case "2":
                            register(users);//Method for Register
                            break;
                        case "3":
                            break mainloop;//Method for break the loop
                        default:
                            System.out.println("\nPlease enter a valid input !");
                            break regLoop;//Error handling
                    }
                }

            }
            if (choice.equals("2")){
                //Showing manager options
                System.out.print("""
                     What do you wanna do?
                       -add a new product press 1
                       -remove a product press  2
                       -print the list press    3
                     """);
                System.out.print("\nPlease enter your choice :");
                String proType = sc.nextLine();
                switch (proType.toUpperCase()) {
                    case "1" -> {
                        System.out.print("""
                                Product types
                                     -if electronics press 1
                                     -if clothes press     2
                                """);
                        System.out.print("\nPlease enter your choice :");//Getting inputs for manage the choices
                        String type = sc.nextLine();
                        if (type.equals("1")) {
                            addEleProduct(stock);
                        }else if(type.equals("2")) {
                            addCloProduct(stock);
                        } else {
                            System.out.print("Please enter a valid input !\n");//Error handling
                        }
                    }
                    case "2" -> {
                        System.out.println("List of available products");
                        stock.viewProList();
                        System.out.print("Which item do you wanna delete ?");
                        System.out.print("Please enter the id of the product :\n");
                        String key = sc.nextLine();
                        stock.delProduct(key);
                    }
                    case "3" -> {stock.viewProList();break;}
                    default -> {
                        System.out.println("Please enter a valid input (-_-)\n");
                        break;
                    }

                }
            }
            else if (choice.equals("3")){
                saveFile(stock,users);
            }
            else if(choice.equals("4")){
                loadFile(stock, users);
            }
            else if (choice.equals("5")){
                System.out.print("Are you sure you want to terminate the program !(Y/N)");  //Exiting the program
                String terminate = sc.nextLine();
                if (terminate.equalsIgnoreCase("Y")) {
                    System.out.println("Now the program will be terminated!");
                    System.exit(0);
                    //https://blog.gitnux.com/code/java-exit-program/#:~:text=To%20exit%20a%20program%20in,error%20or%20an%20exceptional%20condition.&text=In%20this%20example%2C%20after%20printing,the%20program%20will%20exit%20immediately.
                } else if (terminate.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("please enter a valid input!");
                    break;

                }
            }
            else {
                System.out.println("Please enter a valid input!\n");
            }
        }

    }

    //Method for add Electronic product
    public static void addEleProduct(Stock storeStock){
        Scanner scan = new Scanner(System.in);
        String proId;
        String proName;
        String proBrand;
        int proWarranty = 0;
        double proPrice = 0;
        int quantity=0;

        while (true) {
            System.out.print("\nEnter product Id : ");
            proId = scan.nextLine();
            if (proId.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT ID cannot be empty\n");
                break;
            }
        }
        while (true) {
            System.out.print("\nEnter product name : ");
            proName = scan.nextLine();
            if (proName.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT NAME cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("\nEnter product brand : ");
            proBrand = scan.nextLine();
            if (proBrand.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT BRAND cannot be empty\n");
            }
        }
        while (true) {
            Scanner sc =new Scanner(System.in);
            try {
                System.out.print("\nEnter products' warranty period in years : ");
                proWarranty = sc.nextInt();
                if (proWarranty != 0) {
                    break;
                } else {
                    System.out.println("WARNING!! Electronic PRODUCT must have a warranty period !\n");
                }
            }catch(Exception e){
                System.out.println("Please enter a valid input\n");
                break;
            }
        }
        while (true) {
            Scanner sc =new Scanner(System.in);
            try{
                System.out.print("\nEnter products' price : ");
                proPrice = sc.nextDouble();
                if (proPrice != 0) {
                    break;
                } else {
                    System.out.println("WARNING!! product must have a price !\n");
                }
            }catch(Exception e){
                System.out.println("Please enter a valid input!\n");
                break;
            }
        }
        while (true) {
            Scanner sc =new Scanner(System.in);
            try {
                System.out.print("\nEnter quantity : ");
                quantity = sc.nextInt();
                if (quantity != 0) {
                    break;
                } else {
                    System.out.println("WARNING!! must have a quantity !\n");
                }
            }catch (Exception e){
                System.out.println("Please enter a valid input!");
                break;
            }
        }

        if(storeStock.keyAvailability(proId)){
            Electronics eleItem = new Electronics(proId,proName,proPrice,quantity,proBrand,proWarranty);
            storeStock.addProduct(proId, eleItem);
            System.out.println("Product "+eleItem.getProductName()+" has added !\n");
        }
        else {
            System.out.println("Warning! ProductId is already used \n");
        }
        }

        //Method for add Clothing objects
    public static void addCloProduct(Stock storeStock){
        Scanner scan = new Scanner(System.in);
        String proId;
        String proName;
        String proSize;
        String proColor;
        double proPrice=0;
        int quantity=0;
        while (true) {
            System.out.print("\nEnter product Id : ");
            proId = scan.nextLine();
            if (proId.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT ID cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("\nEnter product name : ");
            proName = scan.nextLine();
            if (proName.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT NAME cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("\nEnter clothes' size : ");
            proSize = scan.nextLine();
            if (proSize.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! PRODUCT SIZE cannot be empty\n");
            }
        }
        while (true) {
            System.out.print("\nEnter cloths' color : ");
            proColor = scan.nextLine();
            if (proColor.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! Cloth must have a color !\n");
            }
        }
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("\nEnter products' price : ");
                proPrice = sc .nextDouble();
                if (proPrice != 0) {
                    break;
                } else {
                    System.out.println("WARNING!! product must have a price !\n");
                }
            }catch (Exception e){
                System.out.println("Please enter a valid input!");
                break;
            }
        }
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("\nEnter quantity : ");
                quantity = sc.nextInt();

                if (quantity != 0) {
                    break;
                } else {
                    System.out.println("WARNING!! must have a quantity !\n");
                }
            }catch (Exception e){
                System.out.println("Please enter a valid input!");
                break;
            }

        }
        if(storeStock.keyAvailability(proId)){
            Clothes cloItem = new Clothes(proId,proName,proPrice,quantity,proSize,proColor);
            storeStock.addProduct(proId, cloItem);
            System.out.println("Product "+cloItem.getProductName()+" has added !\n");
        }
        else {
            System.out.println("Warning! ProductId is already used \n");
        }

    }

    //Method for Register a user
    public static void register(Users users){
        Scanner sc = new Scanner(System.in);
        String userName;
        String password;
        while (true) {
            System.out.print("\nPlease enter a user name : ");
            userName = sc.nextLine();
            if (userName.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! You should have a username !\n");
                break;
            }
        }
        while (true) {
            System.out.print("\nPlease enter a password : ");
            password = sc.nextLine();
            if (password.length() != 0) {
                break;
            } else {
                System.out.println("WARNING!! You should have a password !\n");
            }
        }
        if(!users.keyAvailability(userName)){
            User user = new User(userName,password);
            users.addUser(userName,user);
            System.out.println("You have successfully registered !\n");
        }
        else{
            System.out.println("Warning! Username not available \n");
        }

    }

//    Method for Login for the user
    public static void logIn(Users users, Stock stock){
        Scanner sc = new Scanner(System.in);
        String userName;
        String password;
        while (true) {
            System.out.print("\nPlease enter your user name : ");
            userName = sc.nextLine();
            System.out.print("\nPlease enter your password : ");
            password = sc.nextLine();
            if(users.login(userName, password)){
                System.out.println("You're logged in !\n");
                loggedInUser(stock);
                break;
            }
            else {
                System.out.println("Incorrect username password combination !\n");
                break;
            }

        }
    }

    //Method for save the object
    public static void saveFile(Stock stock, Users users) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("shopping_data.txt"))) {
            outputStream.writeObject(stock);
            outputStream.writeObject(users);
            System.out.println("Data saved successfully!\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving data!\n");
        }
    }

//    Method for retreive data from saved file
    public static void loadFile(Stock stock, Users users) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("shopping_data.txt"))) {
            Stock savedStock = (Stock) inputStream.readObject();
            Users savedUsers = (Users) inputStream.readObject();

            // Clear existing data
            stock.clear();
            users.clear();

            // Copy data from the loaded objects
            stock.copyFrom(savedStock);
            users.copyFrom(savedUsers);

            System.out.println("Data loaded successfully!\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading data!\n");
        }
    }
    public static void loggedInUser(Stock stock){
        SwingUtilities.invokeLater(() -> new GUI(stock));
    }
}