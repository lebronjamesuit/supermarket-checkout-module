package supermarket;

import supermarket.factory.ShoppingCardBase;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CheckoutTillManager {

    private static final int DEFAULT_QUANTITY = 1;

    public boolean scanItemOneByOne(Map<String, Map<Integer, BigDecimal>> priceMap, String itemName, ShoppingCardBase cart) {
        if (!priceMap.containsKey(itemName)) {
            return false;
        }

        Map<Integer, BigDecimal> priceByItem = priceMap.get(itemName);
        calculatePrice(itemName, priceByItem, cart);
        return true;
    }

    private void calculatePrice(String itemName, Map<Integer, BigDecimal> priceByItem, ShoppingCardBase cart) {
        Optional<Product> optionalProduct = findProductByName(itemName, cart);

        if (optionalProduct.isEmpty()) {
            Product newProduct = createNewProduct(itemName, priceByItem);
            cart.addProduct(newProduct);
        } else {
            Product existingProduct = optionalProduct.get();
            Product productUpdated =  createNewProductForUpdate(existingProduct, priceByItem);
            cart.updateProduct(existingProduct, productUpdated);
        }

    }

    private Product createNewProductForUpdate(Product product, Map<Integer, BigDecimal> priceByItem) {
        int currentQuantity = product.getQuantity();
        int addedOneItem = currentQuantity + 1;

        BigDecimal price = priceByItem.getOrDefault(addedOneItem,
                priceByItem.get(DEFAULT_QUANTITY).multiply(BigDecimal.valueOf(addedOneItem)));

        return new Product.Builder()
                .productName(product.getProductName())
                .price(price)
                .quantity(addedOneItem)
                .build();
    }

    private Optional<Product> findProductByName(String itemName, ShoppingCardBase cart) {
        return cart.getProducts().stream()
                .filter(product -> product.getProductName().equals(itemName))
                .findFirst();
    }

    private Product createNewProduct(String itemName, Map<Integer, BigDecimal> priceByItem) {
        BigDecimal price = priceByItem.getOrDefault(DEFAULT_QUANTITY, BigDecimal.ZERO);
        return new Product.Builder()
                .productName(itemName)
                .price(price)
                .quantity(DEFAULT_QUANTITY)
                .build();

    }

}
