package cn.vt.desinger.stratage;

import cn.hutool.core.lang.Console;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vate
 */
public class Calculator {
    private static final Map<String, CalculateStrategy> calculatorStrategies = new HashMap() {{
        this.put("add",new AddCalculateStrategy());
//        this.put("minus",);
//        this.put("mul",);
//        this.put("",);
    }};

    public static void calculate(String strategyName, String... args) {
        Object result;
        try {
            result = calculatorStrategies.getOrDefault(strategyName, new EmptyCalculateStrategy()).calculate(args);
        } catch (Throwable t) {
            result = t;
        }
        if (result instanceof Throwable) {
            Console.error((Throwable) result, "计算异常.");
        } else {
            Console.log("结果: {}", result);
        }
    }

    public static void main(String[] args) {
        calculate("add", "-1111111.1231234", "2.123123");
    }
}
