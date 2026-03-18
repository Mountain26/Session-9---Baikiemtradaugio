package database;

import entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDatabase {
    private static final ProductDatabase INSTANCE = new ProductDatabase();
    private final List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        return INSTANCE;
    }

    public boolean addProduct(Product product) {
        if (product == null || findById(product.getId()) != null) {
            return false;
        }
        return products.add(product);
    }

    public Product findById(String id) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }

    public boolean updateProduct(String id, Product newProduct) {
        if (newProduct == null) {
            return false;
        }

        for (int i = 0; i < products.size(); i++) {
            Product current = products.get(i);
            if (current.getId().equalsIgnoreCase(id)) {
                Product duplicate = findById(newProduct.getId());
                if (duplicate != null && duplicate != current) {
                    return false;
                }
                products.set(i, newProduct);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        Product product = findById(id);
        if (product == null) {
            return false;
        }
        return products.remove(product);
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }
}

