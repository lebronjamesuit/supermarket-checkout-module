package supermarket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import supermarket.factory.DefaultShoppingCartFactory;
import supermarket.factory.ShoppingCardBase;
import supermarket.factory.ShoppingCartFactory;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckoutTillManagerMultipleProductTest {
    Map<String, Map<Integer, BigDecimal>> priceMap;
    ShoppingCartFactory cartFactory;
    ShoppingCardBase yourCart;
    CheckoutTillManager till;

    @BeforeAll
    public void setup() {
        priceMap = PricingRule.getRuleMapInstance();
        cartFactory = new DefaultShoppingCartFactory();
        till = new CheckoutTillManager();
    }


    @BeforeEach
    public void beforeEach() {
        yourCart = cartFactory.createShoppingCart();
    }

    @Test
    public void shouldReturn175WhenAddMixProductA_B() {
        //  3 products A , Total price 130
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);


        // 3 products B, Total Price 45
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);


        // Expect Total Price is 130 + 45 = 175
        assertEquals(BigDecimal.valueOf(175), yourCart.getTotalPrice());
        assertEquals(2, yourCart.getProducts().size());
        assertEquals(ProductUtil.PRODUCT_A, yourCart.getProducts().get(0).getProductName());
        assertEquals(ProductUtil.PRODUCT_B, yourCart.getProducts().get(1).getProductName());

    }

    @Test
    public void shouldReturn342WhenScan_6Products() {

        //  Product{productName='A', quantity=3, price=130}
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);

        //   Product{productName='B', quantity=2, price=45}
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);

        // Product{productName='C', quantity=3, price=60}
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_C, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_C, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_C, yourCart);

        //  Product{productName='D', quantity=3, price=45}
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_D, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_D, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_D, yourCart);

        // Product{productName='E', quantity=4, price=33}
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_E, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_E, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_E, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_E, yourCart);

        // Product{productName='F', quantity=4, price=29}
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_F, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_F, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_F, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_F, yourCart);

        assertEquals(BigDecimal.valueOf(342), yourCart.getTotalPrice());

        assertEquals(6, yourCart.getProducts().size());

        assertEquals(ProductUtil.PRODUCT_A, yourCart.getProducts().get(0).getProductName());
        assertEquals(BigDecimal.valueOf(130), yourCart.getProducts().get(0).getPrice());

        assertEquals(ProductUtil.PRODUCT_B, yourCart.getProducts().get(1).getProductName());
        assertEquals(BigDecimal.valueOf(45), yourCart.getProducts().get(1).getPrice());

        assertEquals(ProductUtil.PRODUCT_C, yourCart.getProducts().get(2).getProductName());
        assertEquals(BigDecimal.valueOf(60), yourCart.getProducts().get(2).getPrice());

        assertEquals(ProductUtil.PRODUCT_D, yourCart.getProducts().get(3).getProductName());
        assertEquals(BigDecimal.valueOf(45), yourCart.getProducts().get(3).getPrice());

        assertEquals(ProductUtil.PRODUCT_E, yourCart.getProducts().get(4).getProductName());
        assertEquals(BigDecimal.valueOf(33), yourCart.getProducts().get(4).getPrice());

        assertEquals(ProductUtil.PRODUCT_F, yourCart.getProducts().get(5).getProductName());
        assertEquals(BigDecimal.valueOf(29), yourCart.getProducts().get(5).getPrice());
    }

      /*    {A={1=50, 3=130}, B={1=30, 2=45}, C={1=20}, D={1=15}, E={1=10, 4=33}, F={1=8, 4=29}}
            Product{productName='A', quantity=3, price=130}
            Product{productName='B', quantity=2, price=45}
            Product{productName='C', quantity=3, price=60}
            Product{productName='D', quantity=3, price=45}
            Product{productName='E', quantity=4, price=33}
            Product{productName='F', quantity=4, price=29}
            Total Price is 342
        */

}
