package cn.vt.desinger.state;

/**
 * @author vate
 * @see Car#setState(State)
 */
public interface State {
    public void push(Car car);

    public void pull(Car car);
}
