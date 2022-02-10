package vt.learn.proxy;

import com.alibaba.fastjson.JSON;

/**
 * @author vate
 */
public class Reflection {

    static class Parent{
        String pName;
        public void pHello(){
            System.out.println(pName + ":P Hello.");
        }

        public Parent(String pName) {
            this.pName = pName;
        }

        public void setpName(String pName) {
            this.pName = pName;
        }

        public String getpName() {
            return pName;
        }

        public String getFuck(){
            return "fuck";
        }
    }

    static class Child extends Parent{
        String cName;
        public void cHello(){
            System.out.println(cName + ":C Hello.");
        }

        public Child(String cName) {
            super("富名城");
            this.cName = cName;
        }

        public void setcName(String cName) {
            this.cName = cName;
        }

        public String getcName() {
            return cName;
        }
    }

    public static void main(String[] args) {
        Child.class.getFields();
        new Child("紫明春");

        System.out.println(JSON.toJSONString(new Child("紫明春")));;

        System.out.println(JSON.parseObject("{\"cName\":\"紫明春\",\"fuck\":\"fuck\",\"pName\":\"富名城\"}",Child.class));

        System.out.println();
    }

}
