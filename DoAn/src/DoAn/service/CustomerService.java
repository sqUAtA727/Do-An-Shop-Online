package DoAn.service;

import DoAn.entity.Account;
import DoAn.entity.Bill;
import DoAn.entity.Cart;
import DoAn.entity.Product;

import java.util.ArrayList;

public class CustomerService {
    public Product findProductById(int id, ArrayList<Product> products) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void getProduct(int id, int amount, ArrayList<Product> products, Cart cart) {
        Product product = findProductById(id, products);
        if (product != null) {
            cart.addProduct(product);
            cart.addAmount(amount);
        }
    }

    public void payment(Account account, Bill bill, ArrayList<Bill> bills) {
        if (account.getWallet().getAccountBalance() < bill.getTotalPrice()) {
            System.out.println("Số tiền phải trả: " + bill.getTotalPrice());
            System.out.println("Số tiền trong tài khoản: " + account.getWallet().getAccountBalance());
            System.out.println("Tài khoản không đủ tiền vui lòng đặt hàng lại");
        } else {
            String condition = "true";
            for (Product product : bill.getProducts()) {
                if (product.getStock() < bill.getAmounts().get(bill.getProducts().indexOf(product))) {
                    System.out.println("Shop đang không có đủ hàng, vui lòng đặt lại");
                    condition = "false";
                    break;
                }
            }
            if (condition.equals("true")) {
                System.out.println("Đặt hàng thành công");
                account.getWallet().setAccountBalance(account.getWallet().getAccountBalance() - bill.getTotalPrice());
                bills.add(bill);
            }
        }
    }
}