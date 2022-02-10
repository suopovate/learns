package cn.vt.desinger.stratage;

import cn.hutool.core.lang.Assert;

import java.math.BigDecimal;

/**
 * @author vate
 */
public class AddCalculateStrategy implements CalculateStrategy<Object, Object> {
    @Override
    public Object calculate(Object... args) {
        Assert.checkBetween(args.length,2,2);
        return new BigDecimal(String.valueOf(args[0])).add(new BigDecimal(String.valueOf(args[1]))).doubleValue();
    }
}
