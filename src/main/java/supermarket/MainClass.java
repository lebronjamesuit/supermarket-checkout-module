package supermarket;

import supermarket.factory.DefaultShoppingCartFactory;
import supermarket.factory.ShoppingCardBase;
import supermarket.factory.ShoppingCartFactory;

import java.math.BigDecimal;
import java.util.Map;

public class MainClass {

    public static void main(String... args) {
        System.out.println("Hello");

        ShoppingCartFactory cartFactory =  new DefaultShoppingCartFactory();
        ShoppingCardBase yourCart = cartFactory.createShoppingCart();

        Map<String, Map<Integer, BigDecimal>> priceMap = PricingRule.getRuleMapInstance();
        System.out.println(priceMap.toString());

        CheckoutTillManager till = new CheckoutTillManager();

        String testProduct = "A";
        till.scanItemOneByOne(priceMap, testProduct, yourCart);

        //
        till.scanItemOneByOne(priceMap, testProduct, yourCart);
        till.scanItemOneByOne(priceMap, testProduct, yourCart);

        String testProductB = "B";
        till.scanItemOneByOne(priceMap, testProductB, yourCart);
        till.scanItemOneByOne(priceMap, testProductB, yourCart);

        String testProductC= "C";
        till.scanItemOneByOne(priceMap, testProductC, yourCart);
        till.scanItemOneByOne(priceMap, testProductC, yourCart);
        till.scanItemOneByOne(priceMap, testProductC, yourCart);

        String testProductD= "D";
        till.scanItemOneByOne(priceMap, testProductD, yourCart);
        till.scanItemOneByOne(priceMap, testProductD, yourCart);
        till.scanItemOneByOne(priceMap, testProductD, yourCart);


        String testProductE= "E";
        till.scanItemOneByOne(priceMap, testProductE, yourCart);
        till.scanItemOneByOne(priceMap, testProductE, yourCart);
        till.scanItemOneByOne(priceMap, testProductE, yourCart);
        till.scanItemOneByOne(priceMap, testProductE, yourCart);


        String testProductF= "F";
        till.scanItemOneByOne(priceMap, testProductF, yourCart);
        till.scanItemOneByOne(priceMap, testProductF, yourCart);
        till.scanItemOneByOne(priceMap, testProductF, yourCart);
        till.scanItemOneByOne(priceMap, testProductF, yourCart);

        // Dummy item
        till.scanItemOneByOne(priceMap, "abfdjsaf", yourCart);

        yourCart.getProducts().stream()
                .forEach( product -> { System.out.println(product.toString() ); });


        System.out.println("Total Price is " + yourCart.getTotalPrice());

    }



}
