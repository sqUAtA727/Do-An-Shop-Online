package DoAn.view;

import DoAn.entity.Account;
import DoAn.entity.Bill;
import DoAn.entity.Product;
import DoAn.entity.Staff;
import DoAn.service.AccountService;
import DoAn.utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    AccountService service = new AccountService();

    public void displayMenu() {
        System.out.println("1 - Đăng nhập\n" +
                "2 - Đăng ký");
    }

    public void selectMenu(Scanner scanner, ArrayList<Account> accounts, ArrayList<Product> products, ArrayList<Bill> bills, ArrayList<Staff> staffs) {
        int choose = Utils.inputInteger(scanner);
        switch (choose) {
            case 1:
//                Đăng nhập
                loginMenu(scanner, accounts, products, bills, staffs);
                break;
            case 2:
//                Đăng ký
                registerMenu(scanner, accounts, staffs);
                break;
//                Lệnh ẩn để kiểm tra list tài khoản
//            case 3:
//                System.out.println(accounts);
//                break;
            default:
                System.out.println("Giá trị không hợp lệ vui lòng nhập lại");
                break;
        }
    }

    public void loginMenu(Scanner scanner, ArrayList<Account> accounts, ArrayList<Product> products, ArrayList<Bill> bills, ArrayList<Staff> staffs) {
//        Đăng nhập
        System.out.println("Nhap username");
        String username = scanner.nextLine();
        System.out.println("Nhap password: ");
        String password = scanner.nextLine();
        service.loginService(username, password, accounts, scanner, products, bills, staffs);
    }

    public void registerMenu(Scanner scanner, ArrayList<Account> accounts, ArrayList<Staff> staffs) {
//      Đăng ký tài khoản (Mặc định là Customer)
        System.out.println("Nhap username: ");
        String username = scanner.nextLine();
        System.out.println("Nhap email: ");
        String email = scanner.nextLine();
        System.out.println("Nhap password");
        String password = scanner.nextLine();
        int role = 3;
        service.registerService(scanner ,username, email, password, role, accounts, staffs);
    }

    public void wrongAccountMenu(Scanner scanner, ArrayList<Account> accounts) {
//        Menu hiện lên khi nhập sai thông tin
        System.out.println("Đăng nhập thất bại");
        System.out.println("1 - Đăng nhập lai\n" +
                "2 - Quen mat khau");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose) {
            case 1:
//                Break
                System.out.println("Về menu đăng nhập");
                break;
            case 2:
//                Reset password
                service.resetPassword(scanner, accounts);
                break;
            default:
                System.out.println("Giá trị không hợp lệ");
                break;
        }
    }
}