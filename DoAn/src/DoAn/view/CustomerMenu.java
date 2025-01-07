package DoAn.view;

import DoAn.entity.Account;
import DoAn.entity.Bill;
import DoAn.entity.Customer;
import DoAn.entity.Product;
import DoAn.service.CustomerService;
import DoAn.utils.Utils;

import java.math.BigDecimal;
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
                4 - Mua hàng
                5 - Chỉnh sửa thông tin tài khoản của bạn (Sẽ vào menu chỉnh sửa thông tin riêng)
                6 - Đăng xuất (Sau khi đăng xuất quay về mục yêu cầu đăng nhập hoặc đăng ký)
                0 - Thoát chương trình""");
    }

    public void selectMenu(Scanner scanner, Customer customer, ArrayList<Product> products, ArrayList<Account> accounts, ArrayList<Bill> bills) {
        Bill bill = null;
        int choose = 0;
        while (choose != 6) {
            displayMenu(customer.getUsername());
            choose = Utils.inputInteger(scanner);
            switch (choose) {
                case 1:
                    System.out.println("Nhập số tiền bạn muốn nạp vào tài khoản");
                    BigDecimal addMoney = Utils.inputBigDecimal(scanner);
                    BigDecimal newAccountBalance = customer.getWallet().getAccountBalance().add(addMoney);
                    customer.getWallet().setAccountBalance(newAccountBalance);
                    break;
                case 2:
                    System.out.println(products);
                    break;
                case 3:
                    System.out.println("So tien con lai trong tai khoan: " + customer.getWallet().getAccountBalance());
                    break;
                case 4:
                    bill = getBillMenu(scanner, products, customer);
                    System.out.println(bill);
                    customerService.payment(customer, bill, bills);
                    break;
                case 5:
                    accountMenu.mainSelectMenu(scanner, customer, accounts);
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

    public Bill getBillMenu(Scanner scanner, ArrayList<Product> products, Customer customer) {
        int choose = 0;
        while (true) {
            System.out.println("""
                    1 - Xem giỏ hàng
                    2 - Thêm mặt hàng và số lượng
                    3 - Xóa mặt hàng và số lượng
                    4 - Đặt hàng và thanh toán""");
            choose = Utils.inputInteger(scanner);
            switch (choose) {
                case 1:
                    System.out.println(customer.getCart());
                    break;
                case 2:
                    System.out.println("Nhập id mặt hàng");
                    int id = Utils.inputInteger(scanner);
                    System.out.println("Nhập số lượng");
                    int amount = Utils.inputInteger(scanner);
                    customerService.getProduct(id, amount, products, customer);
                    break;
                case 3:
                    System.out.println("Nhập id mặt hàng muốn xóa khỏi giỏ hàng: ");
                    id = Utils.inputInteger(scanner);
                    customerService.removeProduct(id, products, customer);
                    break;
                case 4:
                    return new Bill(customer.getCart(), customer);
                default:
                    System.out.println("Gia trị không hợp lệ vui lòng nhập lại");
                    break;
            }
        }
    }
}