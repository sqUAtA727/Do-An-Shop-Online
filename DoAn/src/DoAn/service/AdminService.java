package DoAn.service;

import DoAn.entity.Account;
import DoAn.entity.Bill;
import DoAn.entity.Product;
import DoAn.entity.Staff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class AdminService {
    public BigDecimal storeRevenue(ArrayList<Bill> bills) {
//        Tính tổng doanh thu
        BigDecimal revenue = BigDecimal.valueOf(0);
        for (Bill bill : bills) {
            revenue = revenue.add(bill.getTotalPrice());
        }
        return revenue;
    }

    public Staff findStaffbyID(String username, ArrayList<Staff> staffs) {
//        Tìm staff bằng id
        for (Staff staff : staffs) {
            if (Objects.equals(staff.getUsername(), username)) {
                return staff;
            }
        }
        return null;
    }

    public void removeStaff(String username, ArrayList<Staff> staffs, ArrayList<Account> accounts) {
//        Xóa staff ở cả 2 list account
        Staff staff = findStaffbyID(username, staffs);
        if (staff != null){
            staffs.remove(staff);
            accounts.remove(staff);
            System.out.println("Xóa nhân viên thành công");
        } else {
            System.out.println("Khong tim thay nhan vien can tim");
        }
    }

    public Product findProductById(int id, ArrayList<Product> products){
//        Tìm sản phẩm bằng ID
        for (Product product : products){
            if (product.getId()==id){
                return product;
            }
        }
        return null;
    }

    public void removeProduct(int id, ArrayList<Product> products){
//        Xóa sản phẩm
        Product product = findProductById(id, products);
        if (product!=null){
            System.out.println("Xoa thanh cong");
            products.remove(product);
        } else {
            System.out.println("Khong tim thay hang can xoa");
        }
    }
}
