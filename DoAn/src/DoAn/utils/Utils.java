package DoAn.utils;

import java.util.Scanner;

public class Utils {
    public static Integer inputInteger(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Lỗi " + e);
                System.out.println("Vui long nhap lai");
            }
        }
    }
}
