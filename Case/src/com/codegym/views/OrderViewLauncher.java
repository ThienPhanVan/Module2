package com.codegym.views;

import java.util.Scanner;

import static com.codegym.views.MainLauncher.menuOption;


public class OrderViewLauncher {
    public static void run() {
        MainLauncher.orderMenu();
        Scanner scanner = new Scanner(System.in);
        OrderView orderView = new OrderView();
        System.out.println("\nChọn chức năng");
        System.out.print("➾ ");
        int choices = scanner.nextInt();
        switch (choices) {
            case 1:
                orderView.addOder();
                break;
            case 2:
                orderView.showAllOrder();
                break;
            case 3:
                menuOption();
                break;
            default:
                System.out.println("Chọn chức năng không đúng! vui lòng nhập lại");
                run();
        }
    }
}

