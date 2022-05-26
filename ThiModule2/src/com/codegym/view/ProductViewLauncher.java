package com.codegym.view;

import com.codegym.untils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductViewLauncher {
    public static void run() {
        int number;
        do {
            Scanner scanner = new Scanner(System.in);
            ProductView productView = new ProductView();
            menuMain();
            try {
                System.out.println(" \n Chọn chức năng");
                System.out.print("⭆ ");
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
                        break;
                    case 6:
                        productView.showMaxPrice();
                        break;
                    case 7:
                        AppUtils.exit();
                    default:
                        System.err.println("Chọn chức năng không đúng, vui lòng nhập lại!");
                        run();
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Nhập sai, vui lòng nhập lại!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void menuMain() {
        System.out.println();
        System.out.println("* * * * * * * * * * *  * * * * * * * * ** * * * * * *");
        System.out.println("*                                                   *");
        System.out.println("*    1. Thêm sản phẩm                               *");
        System.out.println("*    2. Sửa thông tin sản phẩm                      *");
        System.out.println("*    3. Xoá sản phẩm                                *");
        System.out.println("*    4. Hiển thị danh sách sản phẩm                 *");
        System.out.println("*    5. Hiển thị tất cả sản phẩm theo giá tăng dần  *");
        System.out.println("*    6. Sản phẩm có giá cao nhất                    *");
        System.out.println("*    7. Thoát                                       *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }
}
