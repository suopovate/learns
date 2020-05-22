
public class test {
//    public static void main(String[] args) {
//        SingleLink link = new SingleLink();
//        link.add("1");
//        System.out.println(link.toString());
//        link.inverse();
//        System.out.println(link.toString());
//        ((SingleLink)link.getValue(0)).inverse();
//        System.out.println(link.toString());
//    }
public static void main(String[] args) {
    String str1 = "和谐";
    String str2 = "社会";
    String str3 = "和谐社会";
    String str4;
    str4 = str1 + str2;
    System.out.println(str3 == str4);
    str4 = "和谐" + "社会";
    System.out.println(str3 == str4);
    str4 = (str1 + str2).intern();
    System.out.println(str3 == str4);
}
}
