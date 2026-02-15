import java.util.ArrayList;

public class User {
    private String username;
    private int balance;
    private int award;

    private ArrayList<Cart> shoppingCarts = new ArrayList<Cart>();

    public User(String username, int balance) {
        this.username = username;
        this.balance = balance;
    }

    public int getAward() {
        return award;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Cart> getShoppingCarts() {
        return shoppingCarts;
    }

    public Cart getOneCart(int id) {
        for (Cart i : shoppingCarts) {
            if (id == i.getId()) {
                return i;
            }
        }
        return null;
    }

    public boolean checkCartExist(int id) {
        for (Cart i : shoppingCarts) {
            if (id == i.getId()) {
                return true;
            }
        }
        return false;
    }

    public void printUser() {
        System.out.println(
                "username: " + this.username + " | balance: " + this.balance + "$ | award points: " + this.award);
    }

    public int addShoppingCart() {
        Cart cart = new Cart(this.username);
        shoppingCarts.add(cart);
        return cart.getId();
    }

    public void showShoppingCarts() {
        for (Cart i : shoppingCarts) {
            i.printCart(i.getOwner());
        }
    }

    public void cancelShoppingCart(int id) {
        for (Cart i : shoppingCarts) {
            if (i.getId() == id) {
                i.setStatus("cancelled");
            }
        }
    }

    public void orderShoppingCart(int id, boolean useAward) {
        for (Cart i : shoppingCarts) {
            if (i.getId() == id && i.getStatus().equals("waiting") && this.balance > i.countCost()) {
                i.setStatus("ordered");
                if (award != 0 && useAward) {
                    this.balance -= i.countCost() - (i.countCost() * award / 10);
                } else {
                    this.balance -= i.countCost();
                    if (award == 10) {
                        award = 0;
                    } else {
                        award += 1;
                    }
                }
                System.out.println("thanks for your purhcase, the final cost is : " + i.countCost() + "$");
                return;
            }
        }
        System.out.println("something is wrong, try again!");
    }
}