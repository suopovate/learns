package cn.vt.parameter;

import java.io.Console;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ParameterizedTest<A, B extends Collection, C extends Map> {

    static ParameterizedTest<String, List, Map> isP = new ParameterizedTest();

    A a;

    List<List<String>> listList;

    List<String>[] lArr;

    List<Map> [][] lArrArr;


    static ParameterizedTest notP = new ParameterizedTest();

    public static void main(String[] args) throws NoSuchFieldException {
        ParameterizedTest localP = new ParameterizedTest();
        Type a = ParameterizedTest.class.getDeclaredField("listList").getGenericType();
        System.out.println();
    }
}
