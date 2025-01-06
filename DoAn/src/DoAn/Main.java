package DoAn;

import DoAn.entity.*;
import DoAn.view.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        ArrayList<Wallet> wallets = new ArrayList<>();
        Account account = new Account("admin", "admin@gmail.com", "daylaadmin", new Wallet(0), 1);
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(account);
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Bill> bills = new ArrayList<>();
        ArrayList<Staff> staffs = new ArrayList<>();
        while (true) {
            menu.displayMenu();
            menu.selectMenu(scanner, accounts, products, bills, staffs);
        }
    }
}