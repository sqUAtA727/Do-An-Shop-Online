package DoAn.view;

import DoAn.entity.Account;
import DoAn.service.AccountService;
import DoAn.utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountMenu {
    AccountService service = new AccountService();

    public void displayMainMenu(String username) {
        System.out.println("Chào mừng " + username + ", bạn có thể thực hiện các công việc sau:");
        System.out.println("""
                1 - Thay đổi username
                2 - Thay đổi email
                3 - Thay đổi mật khẩu
                4 - Đăng xuất (Sau khi đăng xuất quay về mục yêu cầu đăng nhập hoặc đăng ký)
                0 - Thoát chương trình""");
    }

    public void mainSelectMenu(Scanner scanner, Account account, ArrayList<Account> accounts) {
        int choose = 0;
        while (choose != 4) {
//            Display phần menu
            displayMainMenu(account.getUsername());

//            Input
            choose = Utils.inputInteger(scanner);
            switch (choose) {
                case 1:
//                    Đổi username
                    System.out.println("Nhap username moi: ");
                    String type = "username";
                    service.modifyAccount(scanner, accounts, type, account);
                    break;
                case 2:
//                    Đổi email
                    System.out.println("Nhap email moi: ");
                    type = "email";
                    service.modifyAccount(scanner, accounts, type, account);
                    break;
                case 3:
//                    Đổi mật khẩu
                    System.out.println("Nhap password moi: ");
                    type = "password";
                    service.modifyAccount(scanner, accounts, type, account);
                    break;
                case 4:
//                    Đăng xuất
                    System.out.println("Dang xuat thanh cong");
                    break;
//              Lệnh ẩn dùng để check list accounts
//                case 5:
//                    System.out.println(accounts);
//                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Giá trị không hợp lệ");
            }
        }
    }
}