package DoAn.service;

import DoAn.entity.Product;

import java.util.ArrayList;

public class StaffService {
    public Product findProductById(int id, ArrayList<Product> products){
//        Tìm sản phẩm bằng id
        for (Product product : products){
            if (product.getId()==id){
                return product;
            }
        }
        return null;
    }
}
