import java.util.ArrayList;

public class Cart {
    static int baseId = 0;
    private String owner;
    private String status;
    private int id;
    private ArrayList<CartItem> items = new ArrayList<CartItem>();

    public Cart(String owner) {
        this.owner = owner;
        this.id = getNextId();
        this.status = "waiting";
    }

    public String getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int getNextId() {
        baseId++;
        return baseId;
    }

    public void printCart(String user) {
        System.out.println("shopping cart id: " + this.id + " | cart owner: " + user + " | cart status: " + status
                + " | list of products: ");
        for (CartItem i : items) {
            System.out.print("       ");
            i.printProduct();
        }
    }

    public void addItem(String name, int quentity) {
        for (Product i : Admin.getProducts()) {
            if (i.getProductName().equals(name)) {
                if (i.checkStock(quentity)) {
                    int price = i.getProductPrice();
                    CartItem item = new CartItem(name, quentity, price);
                    items.add(item);

                    int oldStock = i.getProductStock();
                    i.changeStock(oldStock - quentity);
                    break;
                } else {
                    System.out.println("no enough stock");
                    return;
                }
            }
        }
        System.out.println("product not found");
    }

    public void deleteItem(String name) {
        for (CartItem i : items) {
            if (i.getName().equals(name)) {
                items.remove(i);
                break;
            }
        }
    }

    public void changeQuentity(String name, int quentity) {
        for (CartItem i : items) {
            if (i.getName().equals(name)) {
                i.setQuentity(quentity);
                break;
            }
        }
    }

    public int countCost() {
        int sum = 0;
        for (CartItem i : items) {
            sum += i.getCost();
        }
        return sum;
    }

}
