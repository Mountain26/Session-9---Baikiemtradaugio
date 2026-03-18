package factory;

import entity.DigitalProduct;
import entity.PhysicalProduct;
import entity.Product;

public class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double extraValue) {
        switch (type) {
            case 1:
                return new PhysicalProduct(id, name, price, extraValue);
            case 2:
                return new DigitalProduct(id, name, price, extraValue);
            default:
                throw new IllegalArgumentException("Loai san pham khong hop le. Chi nhan 1 hoac 2.");
        }
    }
}

