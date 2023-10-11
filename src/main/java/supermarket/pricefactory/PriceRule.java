package supermarket.pricefactory;


import java.math.BigDecimal;
import java.util.Map;


/**
 * Open-closed Principle
 * It expects any  derived class are return PriceMap
 *
 * @author James
 * @Since 10.2023
 */
public interface PriceRule {
    Map<String, Map<Integer, BigDecimal>> getRuleMapInstance();

}
