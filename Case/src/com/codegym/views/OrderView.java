package com.codegym.views;

import com.codegym.model.Product;
import com.codegym.services.*;
import com.codegym.utils.AppUtils;
import com.codegym.utils.ValidateUtils;
import com.codegym.model.Order;
import com.codegym.model.OrderItem;


import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final IProductService productService;
    private final IOrderService orderService;
    private final IOderItemService oderItemService;
    private final Scanner scanner = new Scanner(System.in);
    private String color;

    public OrderView() {
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
        oderItemService = OrderItemService.getInstance();
    }

    public OrderItem addOrderItems(long orderId) {
        oderItemService.getOrderItems();
        ProductView productView = new ProductView();
        productView.showProducts(InputOption.ADD);
        long id = System.currentTimeMillis() / 1000;
        System.out.println("Nhập ID sản phẩm: ");
        System.out.print("➾ ");
        int productId = Integer.parseInt(scanner.nextLine());
        while (!productService.existById(productId)) {
            System.out.println("ID sản phẩm không tồn tại");
            System.out.println("Nhập ID sản phẩm: ");
            System.out.print("➾ ");
            productId = Integer.parseInt(scanner.nextLine());
        }
        Product product = productService.getProductById(productId);
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.println("Nhập số lượng sản phẩm");
        System.out.print("➾ ");
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0)
                System.out.println("Số lượng phải lớn hơn 0 (giá > 0)");
        } while (quantity <= 0);
        while (!checkQualityMobile(product, quantity)) {
            System.out.println("Vượt quá số lượng! Vui lòng nhập lại");
            System.out.println("Nhập số lượng");
            System.out.print("➾ ");
            quantity = AppUtils.retryParseInt();
        }

        String productName = product.getTitle();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);

        productService.update(product);
        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, productId, productName, total);
        productService.updateQuantity(productId,quantity);
        return orderItem;
    }

    public boolean checkQualityMobile(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }

    public void addOder() {
        try {
            orderService.getOrders();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ và tên (vd: Phan Van Thien) ");
            System.out.print("➾ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên " + name + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập tên (vd: Phan Thien) ");
                System.out.print("➾ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số điên thoại");
            System.out.print("➾ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại (vd: 0123456789)");
                System.out.print("➾ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ");
            System.out.print("➾ ");
            String address = scanner.nextLine();
            OrderItem orderItem = addOrderItems(orderId);
            Order order = new Order(orderId, name, phone, address);
            oderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Tạo đơn hàng thành công");
            do {
                System.out.println("+ - + - + - + - + - + - + - + - + - + - + - +");
                System.out.println("+                                            +");
                System.out.println("+      Nhấn 'y' để tạo tiếp đơn hàng       +");
                System.out.println("+      Nhấn 'q' để trở lại                +");
                System.out.println("+      nhấp 'p' để in hoá đơn             +");
                System.out.println("+      Nhấn 't' để thoát                  +");
                System.out.println("+                                           +");
                System.out.println("+ - + - + - + - + - + - + - + - + - + - + - +");

                System.out.print("➾ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addOder();
                        break;
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "p":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nhập sai. vui lòng nhập lại!");
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("----------------------------------------------------------HOÁ ĐƠN----------------------------------------------------------------");
            System.out.printf("|%-15s %-20s %-15s %-15s %-15s %-15s %-15s\n|", "   Id", "Tên khách hàng", "   SĐT", "Địa chỉ", "Tên sản phẩm", "Số lượng", "Giá");
            System.out.printf("%-15s %-20s %-15s %-15s %-15s %-15d %-15f \n|", order.getId(), order.getName(), order.getPhone(),
                    order.getAddress(), orderItem.getProductName(), orderItem.getQuantity(), orderItem.getPrice());
            System.out.println(" ------------------------------------------------------------------------------------------------- Tổng tiền:" + orderItem.getTotal());
            System.out.println(" *****   -- * * -- * * -- ** -- ** -- THIEN STORE -- * * -- * * -- ** -- ** --   ***** ");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để tiếp tục chương trình");
                System.out.println("Nhấn    ");
                System.out.print("➾ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        addOder();

                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.getOrders();
        List<OrderItem> orderItems = oderItemService.getOrderItems();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println(" -- * ** -- * ** -- **** -- ****** -- ******  LIST PRODUCT  ****** -- ***** -- **** -- *** -- ** -- * -- ");
            System.out.println("|                                                                                                                                                      |");
            System.out.printf("|%-15s %-20s %-12s %-15s %-25s %-15s %-15s %-15s %-21s\n|", "   Id", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên Sản Phẩm", "Màu sắc", "Số lượng", "   Giá", "   Tổng" + "|");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        Product product = productService.getProductById(newOrderItem.getProductId());
                        System.out.printf("%-15s %-20s %-12s %-15s %-25s %-15s %-15s %-21s %-7s\n|",
                                order.getId(),
                                order.getName(),
                                order.getPhone(),
                                order.getAddress(),
                                newOrderItem.getProductName(),
                                product.getColor(),
                                newOrderItem.getQuantity(),
                                newOrderItem.getPrice(),
                                newOrderItem.getTotal());
                    }
                }

            }
            System.out.println("                                                                                                                                      |");
            System.out.println(" -- * * -- * * -- ** -- ** -- ** -- MOBILE STORE -- ** -- * * -- * * -- ** -- ** -- ");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print("➾ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
