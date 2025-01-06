package DoAn.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bill {
    private static int autoID;
    private int id;
    private ArrayList<Product> products;
    private ArrayList<Integer> amounts;
    private int totalPrice;
    private LocalDate buyDate;

    public Bill(ArrayList<Product> products, ArrayList<Integer> amounts) {
        this.id = ++autoID;
        this.products = products;
        this.amounts = amounts;
        this.buyDate = LocalDate.now();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }

    public int getTotalPrice() {
        totalPrice = 0;
        for (Product product : products){
            totalPrice = totalPrice + product.getPrice() * amounts.get(products.indexOf(product));
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
                ", products=" + products +
                ", amounts=" + amounts +
                ", totalPrice=" + getTotalPrice() +
                ", buyDate=" + buyDate +
                '}';
    }
}