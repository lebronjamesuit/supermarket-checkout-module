package supermarket;

import java.math.BigDecimal;

public class Product {

    private String productName;
    private int quantity;
    private BigDecimal price;

    public Product() {

    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public static class Builder {

        private final Product product = new Product();

        public Builder productName(String productName) {
            product.productName = productName;
            return this;
        }

        public Builder quantity(int quantity) {
            product.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            product.price = price;
            return this;
        }

        public Product build() {
            return product;
        }

    }
}
