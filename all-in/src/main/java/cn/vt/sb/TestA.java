package cn.vt.sb;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = TestA1.class)
public @interface TestA {
    int id();
}
