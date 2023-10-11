package supermarket.shopingcartfactory;

import supermarket.DefaultShoppingCart;

/**
 * Simple factory that implements ShoppingCartFactory that support for flexibility
 *
 * @author James
 */
public class DefaultShoppingCartFactory implements ShoppingCartFactory {

    @Override
    public ShoppingCardBase createShoppingCart() {
        return new DefaultShoppingCart();
    }
}
