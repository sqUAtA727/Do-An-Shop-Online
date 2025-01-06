package DoAn.service;

import DoAn.entity.Bill;
import DoAn.entity.Product;
import DoAn.entity.Staff;

import java.util.ArrayList;
import java.util.Objects;

public class AdminService {
    public Integer storeRevenue(ArrayList<Bill> bills) {
        int revenue = 0;
        for (Bill bill : bills) {
            revenue = revenue + bill.getTotalPrice();
        }
        return revenue;
    }

    public Staff findStaffbyID(String username, ArrayList<Staff> staffs) {
        for (Staff staff : staffs) {
            if (Objects.equals(staff.getUsername(), username)) {
                return staff;
            }
        }
        return null;
    }

    public void removeStaff(String username, ArrayList<Staff> staffs) {
        Staff staff = findStaffbyID(username, staffs);
        if (staff != null){
            staff.setRole(3); //Trả về thành account customer bình thường
            staffs.remove(staff);
            System.out.println("Xóa nhân viên thành công");
        } else {
            System.out.println("Khong tim thay nhan vien can tim");
        }
    }

    public Product findProductById(int id, ArrayList<Product> products){
        for (Product product : products){
            if (product.getId()==id){
                return product;
            }
        }
        return null;
    }

    public void removeProduct(int id, ArrayList<Product> products){
        Product product = findProductById(id, products);
        if (product!=null){
            System.out.println("Xoa thanh cong");
            products.remove(product);
        } else {
            System.out.println("Khong tim thay hang can xoa");
        }
    }
}
