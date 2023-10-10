package supermarket.factory;

import supermarket.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCardBase {
        void addProduct(Product product);
        void removeProduct(Product product);
        void updateProduct(Product oldProduct, Product updatedProduct);
        List<Product> getProducts();
        BigDecimal getTotalPrice();
}
