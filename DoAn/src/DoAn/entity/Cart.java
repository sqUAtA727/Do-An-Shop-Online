package DoAn.entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cartProductListAndQuantity;

    public Cart(){
        cartProductListAndQuantity = new HashMap<>();
    }

    public void addProduct(Product product, int quantity){
        cartProductListAndQuantity.put(product, quantity);
    }

    public void removeProduct(Product product){
        cartProductListAndQuantity.remove(product);
    }

    public Map<Product, Integer> getCartProductListAndQuantity(){
        return cartProductListAndQuantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartProductListAndQuantity=" + cartProductListAndQuantity +
                '}';
    }
}