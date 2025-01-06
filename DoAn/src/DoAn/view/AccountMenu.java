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
            displayMainMenu(account.getUsername());
            choose = Utils.inputInteger(scanner);
            switch (choose) {
                case 1:
                    System.out.println("Nhap username moi: ");
                    String type = "username";
                    service.modifyAccount(scanner, accounts, type, account);
                    break;
                case 2:
                    System.out.println("Nhap email moi: ");
                    type = "email";
                    service.modifyAccount(scanner, accounts, type, account);
                    break;
                case 3:
                    System.out.println("Nhap password moi: ");
                    type = "password";
                    service.modifyAccount(scanner, accounts, type, account);
                    break;
                case 4:
                    System.out.println("Dang xuat thanh cong");
                    break;
                case 5:
                    System.out.println(accounts);
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}