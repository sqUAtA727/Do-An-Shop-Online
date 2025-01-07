package DoAn.utils;

import java.math.BigDecimal;
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

    public static BigDecimal inputBigDecimal(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return new BigDecimal(input);
            } catch (Exception e) {
                System.out.println("Lỗi " +e);
                System.out.println("Vui lòng nhập lại");
            }
        }
    }
}
