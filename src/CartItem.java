public class CartItem {
    private String name;
    private int quantity;
    private int price;

    public CartItem(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getquantity() {
        return quantity;
    }

    public int getCost() {
        return this.price * this.quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public void printProduct() {
        System.out.println("name: " + this.name + " | price: " + this.price + " | quantity: " + this.quantity);
    }

}
