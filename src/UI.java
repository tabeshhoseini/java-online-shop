import java.util.Scanner;

public class UI {
    static Scanner inputReader = new Scanner(System.in);

    // here is your username and password as an admin but you can have other admin
    // in the program.
    static Admin admin = new Admin("admin", 12345);

    public static void runApplication() {

        int userChoice;
        do {
            showRuleMenu();
            userChoice = getUserInt("choose your rule.");
            switch (userChoice) {
                case 1:
                    adminLogin();
                    adminPanel();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    addNewUser();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("choose a valid number!");
                    break;
            }
        } while (userChoice != 0);
    }

    private static int getUserInt(String message) {
        System.out.println(message);
        int userNumber = inputReader.nextInt();
        inputReader.nextLine();
        return userNumber;
    }

    private static String getUserString(String message) {
        System.out.println(message);
        String userText = inputReader.nextLine();
        return userText;
    }

    private static void showRuleMenu() {
        System.out.println("________________________\n" +
                "1. Admin\n" +
                "2. User\n" +
                "3. Add user\n" +
                "0. Exit");
    }

    // Admin panel

    private static void adminLogin() {
        while (true) {
            String username = getUserString("enter your username");
            int password = getUserInt("enter your password");
            if (admin.getName().equals(username) && admin.getPassword() == password) {
                System.out.println("successful login!");
                break;
            } else {
                System.out.println("wrong username or password, try again.");
            }
        }
    }

    private static void adminPanel() {
        int choice;
        do {
            showAdminMenu();
            choice = getUserInt("enter your choice");
            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    Admin.showUsers();
                    break;
                case 5:
                    Admin.showAllOrders();
                    break;
                case 6:
                    filterProducts();
                    break;
                case 7:
                    Admin.sortByName();
                    break;
                case 8:
                    Admin.sortByPrice();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("choose a valid number!");
                    break;
            }
        } while (choice != 0);
    }

    private static void showAdminMenu() {
        System.out.println("________________________\n" +
                "Admin panel:\n" +
                "1. Add product\n" +
                "2. Remove product\n" +
                "3. Edit product\n" +
                "4. See all users\n" +
                "5. See all orders(shopping carts)\n" +
                "6. Filter products\n" +
                "7. Sort products by name\n" +
                "8. Sort products by price\n" +
                "0. Exit");
    }

    private static void addNewUser() {
        String username = getUserString("enter your username");
        int balance = getUserInt("enter your balance");
        Admin.addUser(username, balance);
        System.out.println("new user added successfully!");
    }

    private static void addNewProduct() {
        String name = getUserString("enter the name of the product");
        int stock = getUserInt("enter the stock");
        int price = getUserInt("enter the price of product");
        String category = getUserString("enter the category of product");
        Admin.addProduct(name, stock, price, category);
        System.out.println("product added successfully!");
    }

    private static void removeProduct() {
        String name = getUserString("enter the name of the product you want to remove");
        Admin.deleteProduct(name);
    }

    private static void editProduct() {
        int choice = 0;

        do {
            String name = getUserString("enter the name of the product");
            if (!Admin.checkAvailability(name)) {
                System.out.println("we dont have such a product");
                continue;
            }
            System.out.println("1. change product's stock");
            System.out.println("2. change product's price");
            System.out.println("0. exit");
            choice = getUserInt("");

            if (choice == 1) {
                int stock = getUserInt("enter the new stock");
                Admin.setStock(name, stock);
            } else if (choice == 2) {
                int price = getUserInt("enter the new price");
                Admin.setPrice(name, price);
            } else {
                System.out.println("enter a valid number");
            }
        } while (choice != 0);
    }

    private static void filterProducts() {
        int choice = 0;
        do {
            System.out.println("1. filter by category\n" +
                    "2. filter by stock\n" +
                    "3. filter by price\n" +
                    "0. exit");
            choice = getUserInt("");

            switch (choice) {
                case 1:
                    Admin.filterByCategory(getUserString("enter the name of the category"));
                    break;
                case 2:
                    System.out.println("available Items: ");
                    Admin.filterByStock();
                    break;
                case 3:
                    int lower = getUserInt("enter the lower bound");
                    int upper = getUserInt("enter the upper bound");
                    Admin.filterByPrice(lower, upper);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("enter a valid number");
                    break;
            }
        } while (choice != 0);
    }

    // User panel
    private static void userLogin() {
        String username = "";
        do {
            username = getUserString("enter your username");
            if (Admin.checkUserExist(username)) {
                userPanel(Admin.getOneUser(username));
                break;
            } else if (username.equals("exit")) {
                break;
            } else {
                System.out.println("user not found, try again or type 'exit' ");
            }
        } while (!username.equals("exit"));
    }

    private static void userPanel(User user) {
        int choice;
        do {
            showUserMenu();
            choice = getUserInt("enter your choice");
            switch (choice) {
                case 1:
                    Admin.showProducts();
                    break;
                case 2:
                    searchProductByName();
                    break;
                case 3:
                    System.out.println("your award points: " + user.getAward());
                    break;
                case 4:
                    int id = user.addShoppingCart();
                    System.out.println("the new shopping cart with the id of " + id + " is added");
                    break;
                case 5:
                    int cancelId = getUserInt("enter the id of the shopping cart you want to cancel");
                    user.cancelShoppingCart(cancelId);
                    break;
                case 6:
                    addItemsToCart(user);
                    break;
                case 7:
                    orderTheCart(user);
                    break;
                case 8:
                    user.showShoppingCarts();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("choose a valid number!");
                    break;
            }
        } while (choice != 0);
    }

    private static void showUserMenu() {
        System.out.println("__________________________________\n" +
                "User menu: \n" +
                "1. See all products\n" +
                "2. Search products\n" +
                "3. See award points\n" +
                "4. Add new shopping cart\n" +
                "5. Cancel a shopping cart\n" +
                "6. Add items to a shopping cart\n" +
                "7. Order a shopping cart\n" +
                "8. See all shopping carts\n" +
                "0. Exit");
    }

    private static void searchProductByName() {
        String name = getUserString("enter your product");
        Admin.searchProductByName(name);
    }

    private static void orderTheCart(User user) {
        int id = getUserInt("enter the id of the cart you want to order:");
        int useOffer = getUserInt("do you want to use your award points to get offer? 1.yes 2. no");
        if (useOffer == 1) {
            user.orderShoppingCart(id, true);
        } else {
            user.orderShoppingCart(id, false);
        }

    }

    private static void addItemsToCart(User user) {
        int id = getUserInt("enter the shopping cart id: ");
        String name = getUserString("what product do you want?");
        int quantity = getUserInt("how many do you want?");

        if (user.checkCartExist(id)) {
            Cart cart = user.getOneCart(id);
            cart.addItem(name, quantity);
        } else {
            System.out.println("shopping cart not found");
        }
    }

}