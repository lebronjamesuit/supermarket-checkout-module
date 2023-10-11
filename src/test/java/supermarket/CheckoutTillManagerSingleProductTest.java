package supermarket;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;
import supermarket.pricefactory.PriceRule;
import supermarket.shopingcartfactory.DefaultShoppingCartFactory;
import supermarket.shopingcartfactory.ShoppingCardBase;
import supermarket.shopingcartfactory.ShoppingCartFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

// Default LifeCycle is PER_METHOD
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckoutTillManagerSingleProductTest {

    Map<String, Map<Integer, BigDecimal>> priceMap;
    ShoppingCartFactory cartFactory;
    ShoppingCardBase yourCart;
    CheckoutTillManager till;
    PriceRule priceRule;

    @BeforeAll
    public void setup() {
        priceRule = new DefaultPricingRule();
        priceMap = priceRule.getRuleMapInstance();
        cartFactory = new DefaultShoppingCartFactory();
        till = new CheckoutTillManager();
    }

    @BeforeEach
    public void beforeEach() {
        yourCart = cartFactory.createShoppingCart();
    }

    @Test
    public void shouldReturnTrueWhenAddValidProductName() {
        boolean addOk = till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        assertTrue(addOk);
    }

    @Test
    public void shouldReturnFalseWhenAddWrongProductName() {
        boolean addFailed = till.scanItemOneByOne(priceMap, "Wrong Product name", yourCart);
        assertFalse(addFailed);
    }

    @Test
    public void shouldReturnPrice_50WhenAdd_1_ProductA() {
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);

        assertEquals(BigDecimal.valueOf(50), yourCart.getTotalPrice());
        assertEquals(BigDecimal.valueOf(50), yourCart.getProducts().get(0).getPrice());
        assertSizeOneAndProductNameMatch(ProductUtil.PRODUCT_A);
    }

    @Test
    public void shouldReturnNormalPrice_100WhenAdd_2_ProductA() {
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);

        assertEquals(BigDecimal.valueOf(100), yourCart.getTotalPrice());
        assertEquals(BigDecimal.valueOf(100), yourCart.getProducts().get(0).getPrice());
        assertSizeOneAndProductNameMatch(ProductUtil.PRODUCT_A);
    }

    @Test
    public void shouldReturnPriceMap_130WhenAdd_3_ProductA() {
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_A, yourCart);

        assertEquals(BigDecimal.valueOf(130), yourCart.getTotalPrice());
        assertEquals(BigDecimal.valueOf(130), yourCart.getProducts().get(0).getPrice());
        assertSizeOneAndProductNameMatch(ProductUtil.PRODUCT_A);
    }

    // Test for Product B

    @Test
    public void shouldReturnPriceMap_30WhenAddProductB() {
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);
        assertEquals(BigDecimal.valueOf(30), yourCart.getTotalPrice());
        assertEquals(BigDecimal.valueOf(30), yourCart.getProducts().get(0).getPrice());
        assertSizeOneAndProductNameMatch(ProductUtil.PRODUCT_B);
    }

    @Test
    public void shouldReturnPriceMap_45WhenAdd2_productB() {
        // 2 products B
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);

        // Expect total price 45
        assertEquals(BigDecimal.valueOf(45), yourCart.getTotalPrice());
        assertEquals(BigDecimal.valueOf(45), yourCart.getProducts().get(0).getPrice());
        assertSizeOneAndProductNameMatch(ProductUtil.PRODUCT_B);
    }

    @Test
    public void shouldReturnNormalPrice_90WhenAdd3_productB() {
        // 3 products B
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_B, yourCart);

        // Expect Total Price is 90
        assertEquals(BigDecimal.valueOf(90), yourCart.getTotalPrice());
        assertEquals(BigDecimal.valueOf(90), yourCart.getProducts().get(0).getPrice());
        assertSizeOneAndProductNameMatch(ProductUtil.PRODUCT_B);
    }

    @Test
    public void shouldReturnPriceMap_20WhenAdd_1_ProductC() {
        // 1 product C
        till.scanItemOneByOne(priceMap, ProductUtil.PRODUCT_C, yourCart);

        // Expect Total Price is 20
        assertEquals(BigDecimal.valueOf(20), yourCart.getTotalPrice());
        assertEquals(BigDecimal.valueOf(20), yourCart.getProducts().get(0).getPrice());
        assertSizeOneAndProductNameMatch(ProductUtil.PRODUCT_C);
    }

    @Test
    public void shouldThrowExceptionWhenModifyPriceMap() {
        Executable executable = () -> priceMap.put("M", new HashMap<>(1, 16));
        assertThrows(UnsupportedOperationException.class, executable);

    }

    // Expect size = 1 for a single Product
    private void assertSizeOneAndProductNameMatch(String productName) {
        assertEquals(1, yourCart.getProducts().size());
        assertEquals(productName, yourCart.getProducts().get(0).getProductName());
    }

}
