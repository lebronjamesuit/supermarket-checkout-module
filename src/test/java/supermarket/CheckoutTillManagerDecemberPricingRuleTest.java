package supermarket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import supermarket.pricefactory.DecemberPricingRule;
import supermarket.pricefactory.PriceRule;
import supermarket.shopingcartfactory.DefaultShoppingCartFactory;
import supermarket.shopingcartfactory.ShoppingCardBase;
import supermarket.shopingcartfactory.ShoppingCartFactory;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class when we want to introduce a new set of Price for the future.
 *
 * @author James
 * @Since 12.2023
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckoutTillManagerDecemberPricingRuleTest {

    Map<String, Map<Integer, BigDecimal>> priceMap;
    ShoppingCartFactory cartFactory;
    ShoppingCardBase yourCart;
    CheckoutTillManager till;
    PriceRule priceRule;

    @BeforeAll
    public void setup() {
        // December Pricing Rule
        priceRule = new DecemberPricingRule();

        priceMap = priceRule.getRuleMapInstance();
        cartFactory = new DefaultShoppingCartFactory();
        till = new CheckoutTillManager();
    }


    @BeforeEach
    public void beforeEach() {
        yourCart = cartFactory.createShoppingCart();
    }

    @Test
    public void shouldReturn135When_4_ProductA() {
        //  4 products A , Total price 135 - December Price
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);


        // Expect Total Price is 135
        assertEquals(BigDecimal.valueOf(135), yourCart.getTotalPrice());
        assertEquals(1, yourCart.getProducts().size());
        assertEquals(ProductUtil.PRODUCT_A, yourCart.getProducts().get(0).getProductName());

    }

    @Test
    public void shouldReturn120When_3_ProductA() {
        //  3 products A , Total price 120 - December Price
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);


        // Total Price = 120, Product Price = 40
        assertEquals(BigDecimal.valueOf(120), yourCart.getTotalPrice());
        assertEquals(1, yourCart.getProducts().size());
        assertEquals(ProductUtil.PRODUCT_A, yourCart.getProducts().get(0).getProductName());

    }

}