package DoAn;

import DoAn.entity.*;
import DoAn.view.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
//        Tạo account admin
//        Chỉ có 1 admin và khi sửa thông tin sẽ sửa trực tiếp vào mã nguồn
        Admin admin = new Admin("admin", "admin@gmail.com", "daylaadmin", new Wallet(new BigDecimal(0)), 1);
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(admin);

        // Tạo các arraylist lưu thông tin khác
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Bill> bills = new ArrayList<>();
        ArrayList<Staff> staffs = new ArrayList<>();
        while (true) {
            menu.displayMenu();
            menu.selectMenu(scanner, accounts, products, bills, staffs);
        }
    }
}