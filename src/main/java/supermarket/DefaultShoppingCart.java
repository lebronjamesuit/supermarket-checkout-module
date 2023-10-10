package supermarket;

import supermarket.factory.ShoppingCardBase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default shopping cart in the application, centralize add, update and remove products
 * return immutable Collection of Products
 * @author James
 * @Since 10.2023
 */
public class DefaultShoppingCart implements ShoppingCardBase {

    private List<Product> products = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
        totalPrice = totalPrice.add(product.getPrice());
    }

    @Override
    public void removeProduct(Product product) {
        // to do
    }

    // Time complexity: O(n)
    public void updateProduct(Product oldProduct, Product updatedProduct){
        // remove the old one
        products.remove(oldProduct);
        totalPrice = totalPrice.subtract(oldProduct.getPrice());

        // add a new one
        products.add(updatedProduct);
        totalPrice = totalPrice.add(updatedProduct.getPrice());

    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
