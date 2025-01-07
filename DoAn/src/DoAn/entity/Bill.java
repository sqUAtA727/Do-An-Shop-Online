package DoAn.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Bill {
    private static int autoID;
    private int id;
    private Cart cart;
    private Account account;
    private BigDecimal totalPrice;
    private LocalDate buyDate;

    public Bill(Cart cart, Account account) {
        this.id = ++autoID;
        this.cart = cart;
        this.account = account;
        this.buyDate = LocalDate.now();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getTotalPrice() {
        totalPrice = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : cart.getCartProductListAndQuantity().entrySet()){
            totalPrice = totalPrice.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return totalPrice;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", cart=" + cart +
                ", account=" + account +
                ", totalPrice=" + getTotalPrice() +
                ", buyDate=" + buyDate +
                '}';
    }
}