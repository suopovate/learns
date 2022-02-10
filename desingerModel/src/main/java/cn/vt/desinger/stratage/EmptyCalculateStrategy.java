package cn.vt.desinger.stratage;

import cn.hutool.core.lang.Console;

/**
 * @author vate
 */
public class EmptyCalculateStrategy implements CalculateStrategy<Object, Object> {
    @Override
    public Object calculate(Object... args) {
        throw new UnsupportedOperationException();
    }
}
