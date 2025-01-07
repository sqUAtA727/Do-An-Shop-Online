package DoAn.view;

import DoAn.entity.*;
import DoAn.service.AccountService;
import DoAn.service.AdminService;
import DoAn.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {
    AdminService adminService = new AdminService();
    AccountService accountService = new AccountService();
    AccountMenu accountMenu = new AccountMenu();

    public void displayMenu(String username){
        System.out.println("Chào mừng Admin " + username + ", bạn có thể thực hiện các công việc sau:");
        System.out.println("""
                1 - Xem thông tin doanh thu cửa hàng và đưa tiền vào tài khoản
                2 - Xem thông tin các hóa đơn của cửa hàng
                3 - Thêm/Xóa/Chỉnh sửa thông tin nhân viên
                4 - Thêm/Xóa/Chỉnh sửa thông tin mặt hàng
                5 - Chỉnh sửa thông tin tài khoản của bạn (Sẽ vào menu chỉnh sửa thông tin riêng)
                6 - Đăng xuất (Sau khi đăng xuất quay về mục yêu cầu đăng nhập hoặc đăng ký)
                0 - Thoát chương trình""");
    }

    public void selectMenu(Scanner scanner, Admin admin, ArrayList<Product> products, ArrayList<Bill> bills, ArrayList<Account> accounts, ArrayList<Staff> staffs){
        int choose = 0;
        while (choose != 6){
            displayMenu(admin.getUsername());
            choose = Utils.inputInteger(scanner);
            switch (choose){
                case 1:
                    BigDecimal revenue = adminService.storeRevenue(bills);
                    System.out.println("Doanh thu cửa hàng: " + revenue);
                    admin.getWallet().setAccountBalance(revenue);
                    System.out.println("Đưa vào ví thành công");
                    break;
                case 2:
                    System.out.println(bills);
                    break;
                case 3:
                    modifyStaffListMenu(scanner, accounts, staffs);
                    break;
                case 4:
                    modifyProductListMenu(scanner, products);
                    break;
                case 5:
                    accountMenu.mainSelectMenu(scanner, admin, accounts);
                    break;
                case 6:
                    System.out.println("Đăng xuất thành công");
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Giá trị không tồn tại vui lòng nhập lại");
            }
        }
    }

    public void displayModifyStaffListMenu(){
        System.out.println("""
                1 - Hiển thị danh sách nhân viên
                2 - Thêm nhân viên
                3 - Xóa nhân viên
                4 - Chỉnh sửa thông tin nhân viên
                5 - Về menu chính""");
    }

    public void modifyStaffListMenu(Scanner scanner, ArrayList<Account> accounts, ArrayList<Staff> staffs){
        int choose = 0;
        while (choose!=5){
            displayModifyStaffListMenu();
            choose = Utils.inputInteger(scanner);
            switch (choose){
                case 1:
                    System.out.println(staffs);
                    break;
                case 2:
                    staffRegisterMenu(scanner, accounts, staffs);
                    break;
                case 3:
                    System.out.println("Nhập username của Staff cần xóa");
                    String username = scanner.nextLine();
                    adminService.removeStaff(username, staffs);
                    break;
                case 4:
                    System.out.println("Nhap username của Staff cần sửa thông tin");
                    username = scanner.nextLine();
                    Staff staff = adminService.findStaffbyID(username, staffs);
                    if (staff!=null){
                        modifyStaffInfoMenu(scanner, staff);
                    } else {
                        System.out.println("Staff không tồn tại");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Giá trị không tồn tại vui lòng nhập lại");
                    break;
            }
        }
    }

    public void staffRegisterMenu(Scanner scanner, ArrayList<Account> accounts, ArrayList<Staff> staffs){
        System.out.println("Nhap username: ");
        String username = scanner.nextLine();
        System.out.println("Nhap email: ");
        String email = scanner.nextLine();
        System.out.println("Nhap password");
        String password = scanner.nextLine();
        int role = 2;
        accountService.registerService(scanner ,username, email, password, role, accounts, staffs);
    }

    public void displayModifyStaffInfoMenu(){
        System.out.println("""
                1 - Sửa lương nhân viên
                2 - Sửa lịch làm việc của nhân viên
                3 - Về menu trước đó""");
    }

    public void modifyStaffInfoMenu(Scanner scanner, Staff staff){
        int choose = 0;
        while (choose!=3){
            displayModifyStaffInfoMenu();
            choose = Utils.inputInteger(scanner);
            switch (choose){
                case 1:
                    System.out.println("Nhập lương mới");
                    staff.setSalary(Utils.inputBigDecimal(scanner));
                    break;
                case 2:
                    System.out.println("Nhập lịch làm việc mới");
                    staff.setSchedule(scanner.nextLine());
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Giá trị không tồn tại vui lòng nhập lại");
                    break;
            }
        }
    }

    public void displayModifyProductListMenu(){
        System.out.println("""
                1 - Hiển thị danh sách các mặt hàng
                2 - Thêm hàng
                3 - Xóa hàng
                4 - Chỉnh sửa thông tin hàng
                5 - Về menu chính""");
    }

    public void modifyProductListMenu(Scanner scanner, ArrayList<Product> products){
        int choose = 0;
        while (choose!=5){
            displayModifyProductListMenu();
            choose = Utils.inputInteger(scanner);
            switch (choose){
                case 1:
                    System.out.println(products);
                    break;
                case 2:
                    System.out.println("Nhập tên hàng: ");
                    String productName = scanner.nextLine();
                    System.out.println("Nhập số hàng nhập vào: ");
                    int stock = Utils.inputInteger(scanner);
                    System.out.println("Nhập vào giá hàng: ");
                    BigDecimal price = Utils.inputBigDecimal(scanner);
                    products.add(new Product(productName, stock, price));
                    break;
                case 3:
                    System.out.println("Nhap id hang can xoa: ");
                    int id = Utils.inputInteger(scanner);
                    adminService.removeProduct(id, products);
                    break;
                case 4:
                    System.out.println("Nhập id hàng bạn muốn chỉnh sửa: ");
                    id = Utils.inputInteger(scanner);
                    Product product =adminService.findProductById(id, products);
                    if (product!=null){
                        selectModifyProductMenu(scanner, product);
                    } else {
                        System.out.println("Không tìm thấy hàng bạn cần");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Giá trị không tồn tại vui lòng nhập lại");
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
