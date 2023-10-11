package supermarket;

import supermarket.pricefactory.PriceRule;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Implement a default Pricing Rule for the application
 * return static priceMap <productName, <quantity, price>> ;
 *
 * @author James
 * @Since 10.2023
 */
public class DefaultPricingRule implements PriceRule {

    private final Map<String, Map<Integer, BigDecimal>> priceMap = new HashMap<>();

    public DefaultPricingRule() {
        priceMap.put(ProductUtil.PRODUCT_A, makePriceRuleByPair(1, 50, 3, 130));
        priceMap.put(ProductUtil.PRODUCT_B, makePriceRuleByPair(1, 30, 2, 45));
        priceMap.put(ProductUtil.PRODUCT_C, makePriceRuleByPair(1, 20));
        priceMap.put(ProductUtil.PRODUCT_D, makePriceRuleByPair(1, 15));

        priceMap.put(ProductUtil.PRODUCT_E, makePriceRuleByPair(1, 10, 4, 33)); // Normal price: 10 * 4 = 40
        priceMap.put(ProductUtil.PRODUCT_F, makePriceRuleByPair(1, 8, 4, 29));  // Normal price: 4  * 4 = 32

        // Add more  product G H K M N ..... pair(quantity, price)
    }

    public Map<String, Map<Integer, BigDecimal>> getRuleMapInstance() {
        return Collections.unmodifiableMap(priceMap);
    }

    private Map<Integer, BigDecimal> makePriceRuleByPair(Integer... args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Only accept args in pair(quantity, price)");
        }
        Map<Integer, BigDecimal> pricingRule = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            Integer quantity = args[i];
            BigDecimal price = BigDecimal.valueOf(args[i + 1]);
            pricingRule.put(quantity, price);
        }
        return pricingRule;
    }

}
