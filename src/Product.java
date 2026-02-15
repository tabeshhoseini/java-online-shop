public class Product {
    static int base_id = 0;

    private String name;
    private int stock;
    private int id;
    private int price;
    private String category;

    public Product(String name, int stock, int price, String category) {
        this.name = name;
        this.stock = stock;
        this.id = nextId();
        this.price = price;
        this.category = category;
    }

    private static int nextId() {
        base_id++;
        return base_id;
    }

    public void printProduct() {
        System.out
                .println("name: " + this.name + " | product id: " + this.id + " | price: " + this.price + "$ | stock: "
                        + this.stock + " | category: " + this.category);
    }

    public String getProductName() {
        return this.name;
    }

    public String getProductCategory() {
        return this.category;
    }

    public int getProductStock() {
        return this.stock;
    }

    public int getProductPrice() {
        return this.price;
    }

    public void changeStock(int newStock) {
        this.stock = newStock;
    }

    public void changePrice(int newPrice) {
        this.price = newPrice;
    }

    public boolean checkStock(int number) {
        if (number <= this.stock) {
            return true;
        } else {
            return false;
        }
    }
}
