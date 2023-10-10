package supermarket.factory;

import supermarket.DefaultShoppingCart;


// Not use at the moment
public class SelfCheckoutShoppingCartFactory implements  ShoppingCartFactory {

    @Override
    public ShoppingCardBase createShoppingCart() {
        // This part is for future extension.
        return null;
    }
}
