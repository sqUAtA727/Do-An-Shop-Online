package DoAn.view;

import DoAn.entity.Account;
import DoAn.entity.Bill;
import DoAn.entity.Product;
import DoAn.entity.Staff;
import DoAn.service.StaffService;
import DoAn.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffMenu {
    StaffService staffService = new StaffService();
    AccountMenu accountMenu = new AccountMenu();

    public void displayMenu(String username){
        System.out.println("Chào mừng Staff " + username + ", bạn có thể thực hiện các công việc sau:");
        System.out.println("""
                1 - Nạp tiền vào ví điện tử
                2 - Xem lịch làm việc
                3 - Xem thông tin về hàng hóa của shop
                4 - Chỉnh sửa thông tin hàng hóa
                5 - Kiểm tra các giỏ hàng đã thanh toán dưới dạng hóa đơn
                6 - Chỉnh sửa thông tin tài khoản của bạn (Sẽ vào menu chỉnh sửa thông tin riêng)
                7 - Đăng xuất (Sau khi đăng xuất quay về mục yêu cầu đăng nhập hoặc đăng ký)
                0 - Thoát chương trình""");
    }

    public void selectMenu(Scanner scanner, Staff staff, ArrayList<Product> products, ArrayList<Bill> bills, ArrayList<Account> accounts) {
        int choose = 0;
        while (choose != 7) {
            displayMenu(staff.getUsername());
            choose = Utils.inputInteger(scanner);
            switch (choose) {
                case 1:
                    System.out.println("Nhập số tiền bạn muốn nạp vào tài khoản");
                    BigDecimal addMoney = Utils.inputBigDecimal(scanner);
                    BigDecimal newAccountBalance = staff.getWallet().getAccountBalance().add(addMoney);
                    staff.getWallet().setAccountBalance(newAccountBalance);
                    break;
                case 2:
                    System.out.println(staff.getSchedule());
                    break;
                case 3:
                    System.out.println(products);
                    break;
                case 4:
                    System.out.println("Nhập id hàng bạn muốn chỉnh sửa: ");
                    int id = Utils.inputInteger(scanner);
                    Product product =staffService.findProductById(id, products);
                    if (product!=null){
                        selectModifyProductMenu(scanner, product);
                    } else {
                        System.out.println("Không tìm thấy hàng bạn cần");
                    }
                    break;
                case 5:
                    System.out.println(bills);
                    break;
                case 6:
                    accountMenu.mainSelectMenu(scanner, staff, accounts);
                    break;
                case 7:
                    System.out.println("Đăng xuất thành công");
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Gia trị không hợp lệ vui lòng nhập lại");
                    break;
            }
        }
    }

    public void displayModifyProductMenu(){
        System.out.println("""
                1 - Xem thông tin hàng hiện tại
                2 - Restock lại hàng
                3 - Chỉnh sửa giá hàng
                4 - Hoàn thành chỉnh sửa""");
    }

    public void selectModifyProductMenu (Scanner scanner, Product product){
        int choose = 0;
        while (choose!=4){
            displayModifyProductMenu();
            choose = Utils.inputInteger(scanner);
            switch (choose){
                case 1:
                    System.out.println(product);
                    break;
                case 2:
                    System.out.println("Nhập số hàng đã được nhập về: ");
                    int restock = Utils.inputInteger(scanner);
                    product.setStock(product.getStock()+restock);
                    break;
                case 3:
                    System.out.println("Nhập giá mới: ");
                    BigDecimal newPrice = Utils.inputBigDecimal(scanner);
                    product.setPrice(newPrice);
                    break;
                case 4:
                    System.out.println("Quay về menu của staff");
                    break;
                default:
                    System.out.println("Gia trị không hợp lệ vui lòng nhập lại");
                    break;
            }
        }
    }
}
