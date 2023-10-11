package supermarket;

import supermarket.pricefactory.PriceRule;
import supermarket.shopingcartfactory.DefaultShoppingCartFactory;
import supermarket.shopingcartfactory.ShoppingCardBase;
import supermarket.shopingcartfactory.ShoppingCartFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;

public class MainClass {

    public static void main(String... args) throws IOException {

        ShoppingCartFactory cartFactory = new DefaultShoppingCartFactory();
        ShoppingCardBase yourCart = cartFactory.createShoppingCart();
        PriceRule priceRule = new DefaultPricingRule();
        Map<String, Map<Integer, BigDecimal>> priceMap = priceRule.getRuleMapInstance();

        System.out.println("Price Rule: ");
        System.out.println(priceMap);

        String productName = "";
        final String CHECK_OUT = "CHECK OUT";
        while(!productName.equals(CHECK_OUT)){
            System.out.println("1. Please enter product name you want to scan...");
            System.out.println("2. Type "+ CHECK_OUT+ " to process payment... ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            productName = br.readLine();

            CheckoutTillManager till = new CheckoutTillManager();
            if(till.scanItemOneByOne(priceMap, productName, yourCart)){
                System.out.println("Product " +productName+ " Scanned, Total Price: " + yourCart.getTotalPrice());
            }
        }

        System.out.println("Total Price is " + yourCart.getTotalPrice());
        yourCart.getProducts().stream()
                .forEach(product -> {
                    System.out.println(product.toString());
                });

        System.out.println("3. Payment process is coming soon... ");

        // call this method for test testHardDataOnMain(yourCart, priceMap);

    }

    private static void testHardDataOnMain(ShoppingCardBase yourCart, Map<String, Map<Integer, BigDecimal>> priceMap) {
        System.out.println(priceMap.toString());
        CheckoutTillManager till = new CheckoutTillManager();
        String testProductA = "A";
        till.scanItemOneByOne(priceMap, testProductA, yourCart);
        //
        till.scanItemOneByOne(priceMap, testProductA, yourCart);
        till.scanItemOneByOne(priceMap, testProductA, yourCart);

        String testProductB = "B";
        till.scanItemOneByOne(priceMap, testProductB, yourCart);
        till.scanItemOneByOne(priceMap, testProductB, yourCart);

        String testProductC = "C";
        till.scanItemOneByOne(priceMap, testProductC, yourCart);
        till.scanItemOneByOne(priceMap, testProductC, yourCart);
        till.scanItemOneByOne(priceMap, testProductC, yourCart);

        String testProductD = "D";
        till.scanItemOneByOne(priceMap, testProductD, yourCart);
        till.scanItemOneByOne(priceMap, testProductD, yourCart);
        till.scanItemOneByOne(priceMap, testProductD, yourCart);


        String testProductE = "E";
        till.scanItemOneByOne(priceMap, testProductE, yourCart);
        till.scanItemOneByOne(priceMap, testProductE, yourCart);
        till.scanItemOneByOne(priceMap, testProductE, yourCart);
        till.scanItemOneByOne(priceMap, testProductE, yourCart);


        String testProductF = "F";
        till.scanItemOneByOne(priceMap, testProductF, yourCart);
        till.scanItemOneByOne(priceMap, testProductF, yourCart);
        till.scanItemOneByOne(priceMap, testProductF, yourCart);
        till.scanItemOneByOne(priceMap, testProductF, yourCart);

        // Dummy item
        till.scanItemOneByOne(priceMap, "abfdjsaf", yourCart);

        yourCart.getProducts().stream()
                .forEach(product -> {
                    System.out.println(product.toString());
                });


        System.out.println("Total Price is " + yourCart.getTotalPrice());
    }


}
