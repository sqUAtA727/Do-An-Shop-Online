package DoAn.entity;

import java.util.ArrayList;

public class Cart {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Integer> amounts = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }

    public void addAmount(int amount){
        amounts.add(amount);
    }

    public void removeProduct(int id){
        String found = "false";
        for (Product product : products){
            if (product.getId()==id){
                int index = products.indexOf(product);
                removeAmount(index);
                products.remove(index);
                found = "true";
                break;
            }
        }
        if (found.equals("false")){
            System.out.println("Khong co trong gio hang");
        }
    }

    public void removeAmount(int index){
        amounts.remove(index);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                ", amounts=" + amounts +
                '}';
    }
}