package cn.vt.desinger.stratage;

/**
 * @author vate
 */
public interface CalculateStrategy<T, R> {
    public R calculate(T... args);
}
