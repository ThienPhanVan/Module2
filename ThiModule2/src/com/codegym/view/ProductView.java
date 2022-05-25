package com.codegym.view;

import com.codegym.motel.Product;
import com.codegym.service.ProductService;
import com.codegym.untils.CSVUtils;
import java.util.List;
import java.util.Scanner;


public class ProductView {
    static String path = "data/products.csv";
    private static ProductService productService;
    static Scanner sc;

    public ProductView() {
        productService = new ProductService();
        sc = new Scanner(System.in);
    }

    public static void option() {
        Menu.mainMenu();

        while(true) {
            Scanner input = new Scanner(System.in);

            int choice;
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("\t Chức năng phải nhập bằng 1 số!");
                Menu.mainMenu();
                continue;
            }

            switch(choice) {
                case 1:
                    show();
                    break;
                case 2:
                    add();
                    break;
                case 3:
//                    remove();
                    break;
                case 4:
//                    remove();
                    break;
                case 5:
//                    searchByName();
                    break;
                case 6:
                    CSVUtils.read(path);
                    break;
                case 7:
                    CSVUtils.write(path,productService.getItem());
                    break;
                case 8:
                    Menu.exit();
                    System.exit(0);
                default:
                    System.out.println("Không có chức năng này. Mời bạn nhập đúng chức năng!!");
                    Menu.mainMenu();
            }
        }
    }

    public static void show() {
        List<Product> productList = productService.getItem();
        System.out.println(" - - - - - * - - - - - - - - DANH SÁCH SẢN PHẨM - - - - - - - - - * - - - - - ");
        System.out.printf("%-15s%-15s%-10s%-20s%-10s%-10s%-15s", "ID", "TÊN", "GIÁ", "SỐ LƯỢNG", "MÔ TẢ");
       for (Product product: productList){
            System.out.printf("%-15s%-15s%-10s%-20s%-10s\n", product.getId(), product.getName(), product.getPrice(), product.getQuantity(), product.getDescription());
        }

        System.out.println("- - - - - - - - - - * * ** *** **** ***** **** *** * - - - - - - - - - - - - -");

    }

    public static void add() {
        productService.getItem();
        System.out.println("Nhập id sản phẩm: ");
        System.out.print(" --- >>");
        int id = sc.nextInt();
        sc.nextLine();
        if (productService.existById(id)) {
            System.out.println("Id sản phẩm đã có! Vui lòng nhập lại id!");
            add();

        } else {
            System.out.println("Nhập tên sản phẩm: ");
            System.out.print(" --->> ");
            String name = sc.nextLine();

            System.out.println("Nhập giá sản phẩm: ");
            System.out.print(" --->> ");
            int price = sc.nextInt();
            sc.nextLine();

            System.out.println("Nhập số lượng sản phẩm: ");
            System.out.print(" --->> ");
            int quantity = sc.nextInt();

            System.out.println("Mô tả sản phẩm: ");
            System.out.print("--->> ");
            String description = sc.nextLine();

            Product product = new Product(id, name, price, quantity, description);
            productService.add(product);
            System.out.println("Bạn đã thêm sản phẩm thành công\n");


        }
    }
}
