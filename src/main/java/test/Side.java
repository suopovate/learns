package test;

public class Side {

    /**
     * 代表省市区关系中的省或者市
     */
    String preName;
    /**
     * 代表省市区关系中的市或者省(相对于preName的下级)
     */
    String nextName;

    public Side(String preName, String nextName) {
        this.preName = preName;
        this.nextName = nextName;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getNextName() {
        return nextName;
    }

    public void setNextName(String nextName) {
        this.nextName = nextName;
    }
}
