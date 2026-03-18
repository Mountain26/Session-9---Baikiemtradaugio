import database.ProductDatabase;
import entity.Product;
import factory.ProductFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ProductDatabase DATABASE = ProductDatabase.getInstance();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Lua chon cua ban: ");

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    showProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    running = false;
                    System.out.println("Da thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon tu 1 den 5.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("---------------------- QUAN LY SAN PHAM ----------------------");
        System.out.println("1. Them moi san pham");
        System.out.println("2. Xem danh sach san pham");
        System.out.println("3. Cap nhat thong tin san pham");
        System.out.println("4. Xoa san pham");
        System.out.println("5. Thoat");
        System.out.println("--------------------------------------------------------------");
    }

    private static void addProduct() {
        int type = readType();
        String id = readNonEmpty("Nhap ID: ");

        if (DATABASE.findById(id) != null) {
            System.out.println("ID da ton tai. Khong the them moi.");
            return;
        }

        String name = readNonEmpty("Nhap ten san pham: ");
        double price = readPositiveDouble("Nhap gia: ");
        double extraValue = (type == 1)
                ? readPositiveDouble("Nhap trong luong (kg): ")
                : readPositiveDouble("Nhap dung luong (MB): ");

        Product product = ProductFactory.createProduct(type, id, name, price, extraValue);
        boolean added = DATABASE.addProduct(product);

        System.out.println(added ? "Them san pham thanh cong." : "Them san pham that bai.");
    }

    private static void showProducts() {
        List<Product> products = DATABASE.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Danh sach san pham dang rong.");
            return;
        }

        System.out.println("Danh sach san pham:");
        for (Product product : products) {
            product.displayInfo();
        }
    }

    private static void updateProduct() {
        String oldId = readNonEmpty("Nhap ID san pham can cap nhat: ");
        Product existing = DATABASE.findById(oldId);

        if (existing == null) {
            System.out.println("Khong tim thay san pham voi ID: " + oldId);
            return;
        }

        int type = readType();
        String newId = readNonEmpty("Nhap ID moi: ");

        Product duplicate = DATABASE.findById(newId);
        if (duplicate != null && !duplicate.getId().equalsIgnoreCase(oldId)) {
            System.out.println("ID moi da ton tai. Khong the cap nhat.");
            return;
        }

        String name = readNonEmpty("Nhap ten moi: ");
        double price = readPositiveDouble("Nhap gia moi: ");
        double extraValue = (type == 1)
                ? readPositiveDouble("Nhap trong luong moi (kg): ")
                : readPositiveDouble("Nhap dung luong moi (MB): ");

        Product updatedProduct = ProductFactory.createProduct(type, newId, name, price, extraValue);
        boolean updated = DATABASE.updateProduct(oldId, updatedProduct);

        System.out.println(updated ? "Cap nhat thanh cong." : "Cap nhat that bai.");
    }

    private static void deleteProduct() {
        String id = readNonEmpty("Nhap ID san pham can xoa: ");
        boolean deleted = DATABASE.deleteProduct(id);

        System.out.println(deleted ? "Xoa san pham thanh cong." : "Khong tim thay san pham de xoa.");
    }

    private static int readType() {
        while (true) {
            System.out.println("Chon loai san pham:");
            System.out.println("1. Physical entity.Product");
            System.out.println("2. Digital entity.Product");
            int type = readInt("Nhap loai (1 hoac 2): ");

            if (type == 1 || type == 2) {
                return type;
            }
            System.out.println("Loai khong hop le. Vui long nhap 1 hoac 2.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Gia tri khong hop le. Vui long nhap so nguyen.");
            }
        }
    }

    private static double readPositiveDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();

            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                }
                System.out.println("Gia tri phai lon hon 0.");
            } catch (NumberFormatException e) {
                System.out.println("Gia tri khong hop le. Vui long nhap so.");
            }
        }
    }

    private static String readNonEmpty(String message) {
        while (true) {
            System.out.print(message);
            String value = SCANNER.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Khong duoc de trong.");
        }
    }
}

