package cn.vt.thread.safe;

/**
 * @author vate
 */
public class SameKey {

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
