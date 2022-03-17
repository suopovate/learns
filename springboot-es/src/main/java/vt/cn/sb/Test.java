package vt.cn.sb;

import cn.hutool.core.lang.Console;

@TestA1(value = @TestA(id = 1))
@TestA(id = 0)
public class Test {

    public static void main(String[] args) {
        TestA[] declaredAnnotationsByType = Test.class.getDeclaredAnnotationsByType(TestA.class);
        Console.log();
    }
}
