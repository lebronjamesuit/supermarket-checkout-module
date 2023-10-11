package supermarket.pricefactory;

import supermarket.ProductUtil;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Because the pricing changes frequently, we might have new December Price
 *
 * @author James
 * @Since 10.2023
 */

public class DecemberPricingRule implements PriceRule {

    private final Map<String, Map<Integer, BigDecimal>> priceMap = new HashMap<>();

    public DecemberPricingRule() {
        priceMap.put(ProductUtil.PRODUCT_A, makePriceRuleForDecember(1, 40, 4, 135));
        priceMap.put(ProductUtil.PRODUCT_B, makePriceRuleForDecember(1, 40, 4, 135));
        priceMap.put(ProductUtil.PRODUCT_C, makePriceRuleForDecember(1, 40, 4, 135));
        priceMap.put(ProductUtil.PRODUCT_D, makePriceRuleForDecember(1, 40, 4, 135));

        priceMap.put(ProductUtil.PRODUCT_E, makePriceRuleForDecember(1, 40, 4, 135));
        priceMap.put(ProductUtil.PRODUCT_F, makePriceRuleForDecember(1, 40, 4, 135));
    }

    @Override
    public Map<String, Map<Integer, BigDecimal>> getRuleMapInstance() {
        return Collections.unmodifiableMap(priceMap);
    }

    private Map<Integer, BigDecimal> makePriceRuleForDecember(Integer... args) {
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
