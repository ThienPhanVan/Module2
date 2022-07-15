package com.codegym.views;

import com.codegym.utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.codegym.views.MainLauncher.menuOption;


public class ProductViewLauncher {
    public static void run() {
        int number;
        do {
            Scanner scanner = new Scanner(System.in);
            ProductView productView = new ProductView();
            menuMobile();
            try {
                System.out.println("\nChọn chức năng: ");
                System.out.print("➾ ");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        productView.add();
                        break;
                    case 2:
                        productView.update();
                        break;
                    case 3:
                        productView.remove();
                        break;
                    case 4:
                        productView.showProducts(InputOption.SHOW);
                        break;
                    case 5:
                        productView.sortByPriceOrderByASC();
                        return;
                    case 6:
                        menuOption();
                    case 7:
                        AppUtils.exit();
//                        System.exit(7);
                        break;
                    default:
                        System.err.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        run();
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    public static void menuMobile() {
        System.out.println();
        System.out.println("|------------------ MENU MOBILE ------------------|");
        System.out.println("|                                                 |");
        System.out.println("|  1. Thêm sản phẩm                               |");
        System.out.println("|  2. Sửa thông tin sản phẩm                      |");
        System.out.println("|  3. Xoá sản phẩm                                |");
        System.out.println("|  4. Hiển thị tất cả sản phẩm                    |");
        System.out.println("|  5. Hiển thị tất cả sản phẩm theo giá tăng dần  |");
        System.out.println("|  6. Trở lại                                     |");
        System.out.println("|  7. Thoát                                       |");
        System.out.println("|                                                 |");
        System.out.println("|-------------------------------------------------|");
    }
}
