package cn.vt;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.*;
import java.util.List;

public class WildcardTypeTest<T extends String> {

    T[] ts;
    T t;

    // 使用通配符类型参数的方法
    public <T> void testWildcardType(
        List<? extends OutputStream> outputStreams, List<? super InputStream> inputStreams,
        List<Integer> list,List<T[]> listArr, InputStream inputStream) {
    }

    public static void main(String[] args) {
        // 获取WildcardTypeTest类的所有方法（本例中是testWildcardType方法）
        Method[] declaredMethods = WildcardTypeTest.class.getDeclaredMethods();
//        for (Method method : declaredMethods) {
//            System.out.println("method name: " + method.getName());
//            // 获取方法的所有参数类型
//            Type[] genericParameterTypes = method.getGenericParameterTypes();
//            for (Type type : genericParameterTypes) {
//                System.out.println("type: " + type.toString());
//                // 如果不是参数化类型则直接continue
//                if (!(type instanceof ParameterizedType)) {
//                    continue;
//                }
//                // 将当前类型强转为参数化类型并获取实际参数（即含有通配符的泛型类型）
//                Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
//                // 输出其是否为通配符类型
//                System.out.println("type instanceof WildcardType: "
//                        + (actualTypeArgument instanceof WildcardType));
//                if (actualTypeArgument instanceof WildcardType) {
//                    int lowIndex = ((WildcardType) actualTypeArgument).getLowerBounds().length - 1;
//                    int upperIndex = ((WildcardType) actualTypeArgument).getUpperBounds().length - 1;
//                    // 输出上边界与下边界
//                    System.out.println("getLowerBounds(): " +
//                            (lowIndex >= 0 ? ((WildcardType) actualTypeArgument).getLowerBounds()[lowIndex] : "String")
//                            + ";getUpperBounds(): " +
//                            (upperIndex >= 0 ? ((WildcardType) actualTypeArgument).getUpperBounds()[upperIndex] : "Object"));
//                }
//            }
//        }

        for (Field field : WildcardTypeTest.class.getDeclaredFields()) {
            System.out.println(field.getGenericType());
            System.out.println(field.getType());
        }
    }
}
