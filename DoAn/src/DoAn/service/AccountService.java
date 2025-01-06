package DoAn.service;

import DoAn.entity.*;
import DoAn.utils.Utils;
import DoAn.view.AdminMenu;
import DoAn.view.CustomerMenu;
import DoAn.view.Menu;
import DoAn.view.StaffMenu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AccountService {
    public void loginService(String username, String password, ArrayList<Account> accounts, Scanner scanner, ArrayList<Product> products, ArrayList<Bill> bills, ArrayList<Staff> staffs) {
        AdminMenu adminMenu = new AdminMenu();
        StaffMenu staffMenu = new StaffMenu();
        CustomerMenu customerMenu = new CustomerMenu();
        Menu menu = new Menu();
        for (Account account : accounts) {
            if (Objects.equals(account.getUsername(), username) && Objects.equals(account.getPassword(), password)) {
                System.out.println("Dang nhap thanh cong");
                if (account.getRole()==2){
                    Staff staff = (Staff) account;
                    staffMenu.selectMenu(scanner, staff, products, bills, accounts);
                } else if (account.getRole()==3) {
                    customerMenu.selectMenu(scanner, account, products, accounts, bills);
                } else {
                    adminMenu.selectMenu(scanner, account, products, bills, accounts, staffs);
                }
            } else {
                menu.wrongPasswordMenu(scanner, accounts);
            }
        }
    }

    public Account findAccountByUsername(String username, ArrayList<Account> accounts) {
        for (Account account : accounts) {
            if (Objects.equals(account.getUsername(), username)) {
                return account;
            }
        }
        return null;
    }

    public Account findAccountByEmail(String email, ArrayList<Account> accounts) {
        for (Account account : accounts) {
            if (Objects.equals(account.getEmail(), email)) {
                return account;
            }
        }
        return null;
    }

    public void registerService(Scanner scanner, String username, String email, String password, int role, ArrayList<Account> accounts, ArrayList<Staff> staffs) {
        Account accountUsername = findAccountByUsername(username, accounts);
        Account accountEmail = findAccountByEmail(email, accounts);
        if (accountUsername == null && accountEmail == null) {
            System.out.println("Tao tai khoan moi thanh cong");
            if (role==2){
                System.out.println("Nhập lương nhân viên theo tháng (vnd): ");
                int salary = Utils.inputInteger(scanner);
                System.out.println("Nhap lich lam viec cua nhan vien: ");
                String schedule = scanner.nextLine();
                Staff staff = new Staff(username, email, password, role, salary, schedule);
                accounts.add(staff);
                staffs.add(staff);
            } else {
                accounts.add(new Account(username, email, password, new Wallet(0), role));
            }
        } else {
            System.out.println("Username hoac email da ton tai");
        }
    }

    public void resetPassword(Scanner scanner, ArrayList<Account> accounts) {
        System.out.println("Nhap email: ");
        String email = scanner.nextLine();
        Account account = findAccountByEmail(email, accounts);
        if (account == null) {
            System.out.println("Khong ton tai tai khoan");
        } else {
            System.out.println("Nhap mat khau moi: ");
            account.setPassword(scanner.nextLine());
            System.out.println("Doi mat khau thanh cong moi dang nhap lai");
        }
    }

    public void modifyAccount(Scanner scanner, ArrayList<Account> accounts, String type, Account account) {
        if (Objects.equals(type, "username")) {
            String username = scanner.nextLine();
            Account accountUsername = findAccountByUsername(username, accounts);
            if (accountUsername == null) {
                account.setUsername(username);
                System.out.println("Doi username thanh cong");
            } else {
                System.out.println("Username da ton tai");
            }
        } else if (Objects.equals(type, "email")) {
            String email = scanner.nextLine();
            Account accountEmail = findAccountByEmail(email, accounts);
            if (accountEmail == null) {
                account.setEmail(email);
                System.out.println("Doi email thanh cong");
            } else {
                System.out.println("Email da ton tai");
            }
        } else {
            account.setPassword(scanner.nextLine());
            System.out.println("Doi password thanh cong");
        }
    }
}