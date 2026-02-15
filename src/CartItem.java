public class CartItem {
    private String name;
    private int quentity;
    private int price;
    // private int cost = price * quentity;

    public CartItem(String name, int quentity, int price) {
        this.name = name;
        this.quentity = quentity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuentity() {
        return quentity;
    }

    public int getCost() {
        return this.price * this.quentity;
    }

    public void setQuentity(int quentity) {
        this.quentity = quentity;
        // this.cost = this.price * this.quentity;
    }

    public void printProduct() {
        System.out.println("name: " + this.name + " | price: " + this.price + " | quentity: " + this.quentity);
    }

}
