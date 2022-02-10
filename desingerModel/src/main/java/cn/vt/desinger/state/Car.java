package cn.vt.desinger.state;

/**
 * @author vate
 */
public class Car {
    State state;

    public void setState(State state) {
        this.state = state;
    }

    public void push(){
        state.push(this);
    }

    public void pull(){
        state.pull(this);
    }
}
