import java.util.ArrayList;

public class Admin {

    private String name;
    private int password;

    // list of users
    private static ArrayList<User> users = new ArrayList<User>();
    // list of products
    private static ArrayList<Product> products = new ArrayList<Product>();

    public Admin(String name, int password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public int getPassword() {
        return this.password;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getOneUser(String username) {
        for (User i : users) {
            if (i.getUsername().equals(username)) {
                return i;
            }
        }
        return null;
    }

    public static void addUser(String username, int balance) {
        User newUser = new User(username, balance);
        users.add(newUser);
    }

    public static void addProduct(String name, int stock, int price, String category) {
        Product product = new Product(name, stock, price, category);
        products.add(product);
    }

    public static void showProducts() {
        for (Product i : products) {
            i.printProduct();
            System.out.println();
        }
    }

    public static void showUsers() {
        for (User i : users) {
            i.printUser();
            System.out.println();
        }
    }

    public static void showAllOrders() {
        for (User i : users) {
            i.printUser();
            System.err.print("  ");
            for (Cart j : i.getShoppingCarts()) {
                j.printCart(i.getUsername());
            }
            System.out.println();
        }
    }

    public static boolean checkAvailablity(String name) {
        for (Product i : products) {
            if (i.getProductName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUserExist(String name) {
        for (User i : users) {
            if (i.getUsername().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void searchProductByName(String name) {
        for (Product i : products) {
            if (i.getProductName().equals(name)) {
                i.printProduct();
                break;
            }
        }
        System.out.println("product not found");
    }

    public static void deleteProduct(String name) {
        for (Product i : products) {
            if (i.getProductName().equals(name)) {
                products.remove(i);
            }
        }
    }

    public static void changeStock(String name, int newStock) {
        for (Product i : products) {
            if (i.getProductName().equals(name)) {
                i.changeStock(newStock);
            }
        }
    }

    public static void changePrice(String name, int newPrice) {
        for (Product i : products) {
            if (i.getProductName().equals(name)) {
                i.changePrice(newPrice);
            }
        }
    }

    public static void filterByCategory(String name) {
        for (Product i : products) {
            if (i.getProductCategory().equals(name)) {
                i.printProduct();
                System.out.println();
            }
        }
    }

    public static void filterByStock() {
        for (Product i : products) {
            if (i.getProductStock() > 0) {
                i.printProduct();
                System.out.println();
            }
        }
    }

    public static void filterByPrice(int lowerBound, int upperBound) {
        for (Product i : products) {
            int price = i.getProductPrice();
            if (price > lowerBound && price < upperBound) {
                i.printProduct();
                System.out.println();
            }
        }
    }

    public static void sortByPrice() {
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).getProductPrice() < products.get(j).getProductPrice()) {
                    Product temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);
                }
            }
        }
        showProducts();
    }

    public static void sortByName() {
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).getProductName().compareTo(products.get(j).getProductName()) > 0) {
                    Product temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);
                }
            }
        }
        showProducts();
    }

}
