package DoAn.entity;

public class Customer extends Account {
    private Cart cart;

    public Customer(String username, String email, String password, Wallet wallet, int role, Cart cart) {
        super(username, email, password, wallet, role);
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role=" + getRole() +
                "wallet=" + getWallet() +
                '}';
    }
}
