package DoAn.service;

import DoAn.entity.Bill;
import DoAn.entity.Customer;
import DoAn.entity.Product;

import java.util.ArrayList;
import java.util.Map;

public class CustomerService {
    public Product findProductById(int id, ArrayList<Product> products) {
//        Tìm sản phẩm bằng ID
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void getProduct(int id, int quantity, ArrayList<Product> products, Customer customer) {
//        Đưa product vào cart
        Product product = findProductById(id, products);
        if (product != null) {
            customer.getCart().addProduct(product, quantity);
        } else {
            System.out.println("Không tìm thấy mặt hàng muốn thêm vào");
        }
    }

    public void removeProduct(int id, ArrayList<Product> products, Customer customer){
//        Xóa khỏi cart
        Product product = findProductById(id, products);
        if (product!=null) {
            customer.getCart().removeProduct(product);
        } else {
            System.out.println("Không tìm thấy mặt hàng muốn xóa");
        }
    }

    public void payment(Customer customer, Bill bill, ArrayList<Bill> bills) {
//        Thanh toán

//        Tính số tiền trong tài khoản so sánh với hóa đơn
        if (customer.getWallet().getAccountBalance().compareTo(bill.getTotalPrice()) < 0) {
            System.out.println("Số tiền phải trả: " + bill.getTotalPrice());
            System.out.println("Số tiền trong tài khoản: " + customer.getWallet().getAccountBalance());
            System.out.println("Tài khoản không đủ tiền vui lòng đặt hàng lại");
        } else {
            String condition = "true";
//           So sánh số hàng tồn kho với số lượng khách đặt
            for (Map.Entry<Product, Integer> entry : bill.getCart().getCartProductListAndQuantity().entrySet()) {
                if (entry.getKey().getStock() < entry.getValue()) {
                    System.out.println("Shop đang không có đủ hàng, vui lòng đặt lại");
                    condition = "false";
                    break;
                }
            }
            if (condition.equals("true")) {
//                Thực hiện trừ tiền và đưa hóa đơn vào list đã thanh toán
                System.out.println("Đặt hàng thành công");
                customer.getWallet().setAccountBalance(customer.getWallet().getAccountBalance().subtract(bill.getTotalPrice()));
                bills.add(bill);
            }
        }
    }
}