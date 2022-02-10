package vt.learn.annotation;

import lombok.val;

import java.lang.annotation.Annotation;

public class Test {
    /**
     * isAnnotationPresent: Returns present
     * true: directly present
     * true: present
     * false: indirectly present
     * false: associated
     *
     * @param fatherClazz
     * @param sonClazz
     */
    private static void isAnnotationPresent(Class<?> fatherClazz, Class<?> sonClazz) {
        System.out.println(fatherClazz.isAnnotationPresent(DirectlyPresent.class));
        System.out.println(sonClazz.isAnnotationPresent(DirectlyPresent.class));
        System.out.println(fatherClazz.isAnnotationPresent(IndirectlyPresent.class));
        System.out.println(sonClazz.isAnnotationPresent(IndirectlyPresent.class));
    }

    public static void main(String[] args) {
        Class<Father> fatherClazz = Father.class;
        Class<Son> sonClazz = Son.class;
        isAnnotationPresent(fatherClazz, sonClazz);
        final Annotation[] annotations1 = fatherClazz.getAnnotationsByType(IndirectlyPresent.class);
        System.out.println();
    }
}
