package DoAn.view;

import DoAn.entity.Account;
import DoAn.entity.Bill;
import DoAn.entity.Cart;
import DoAn.entity.Product;
import DoAn.service.CustomerService;
import DoAn.utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMenu {
    AccountMenu accountMenu = new AccountMenu();
    CustomerService customerService = new CustomerService();

    public void displayMenu(String username) {
        System.out.println("Chào mừng " + username + ", bạn có thể thực hiện các công việc sau:");
        System.out.println("""
                1 - Nạp tiền vào ví điện tử
                2 - Xem thông tin về hàng
                3 - Xem thông tin về số tiền còn lại trong tài khoản
                4 - Mua hàng (Mỗi lần mua hàng sẽ là 1 giỏ hàng và hóa đơn mới)
                5 - Chỉnh sửa thông tin tài khoản của bạn (Sẽ vào menu chỉnh sửa thông tin riêng)
                6 - Đăng xuất (Sau khi đăng xuất quay về mục yêu cầu đăng nhập hoặc đăng ký)
                0 - Thoát chương trình""");
    }

    public void selectMenu(Scanner scanner, Account account, ArrayList<Product> products, ArrayList<Account> accounts, ArrayList<Bill> bills) {
        Bill bill = null;
        int choose = 0;
        while (choose != 6) {
            displayMenu(account.getUsername());
            choose = Utils.inputInteger(scanner);;
            switch (choose) {
                case 1:
                    System.out.println("Nhập số tiền bạn muốn nạp vào tài khoản");
                    int addMoney = Utils.inputInteger(scanner);
                    int newAccountBalance = account.getWallet().getAccountBalance() + addMoney;
                    account.getWallet().setAccountBalance(newAccountBalance);
                    break;
                case 2:
                    System.out.println(products);
                    break;
                case 3:
                    System.out.println("So tien con lai trong tai khoan: " + account.getWallet().getAccountBalance());
                    break;
                case 4:
                    Cart cart = new Cart();
                    bill = getBillMenu(scanner, products, cart);
                    System.out.println(bill);
                    customerService.payment(account, bill, bills);
                    break;
                case 5:
                    accountMenu.mainSelectMenu(scanner, account, accounts);
                    break;
                case 6:
                    System.out.println("Dang xuat thanh cong");
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Giá trị không tồn tại vui lòng nhập lại");
                    break;
            }
        }
    }

    public Bill getBillMenu(Scanner scanner, ArrayList<Product> products, Cart cart) {
        int choose = 0;
        while (true) {
            System.out.println("""
                    1 - Thêm mặt hàng và số lượng
                    2 - Xóa mặt hàng và số lượng
                    3 - Đặt hàng và thanh toán""");
            choose = Utils.inputInteger(scanner);
            switch (choose) {
                case 1:
                    System.out.println("Nhập id mặt hàng");
                    int id = Utils.inputInteger(scanner);;
                    System.out.println("Nhập số lượng");
                    int amount = Utils.inputInteger(scanner);;
                    customerService.getProduct(id, amount, products, cart);
                    break;
                case 2:
                    System.out.println("Nhập id mặt hàng muốn xóa khỏi giỏ hàng: ");
                    id = Utils.inputInteger(scanner);;
                    cart.removeProduct(id);
                    break;
                case 3:
                    return new Bill(cart.getProducts(), cart.getAmounts());
                default:
                    System.out.println("Gia trị không hợp lệ vui lòng nhập lại");
                    break;
            }
        }
    }
}