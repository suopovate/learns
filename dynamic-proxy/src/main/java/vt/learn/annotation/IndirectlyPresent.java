package vt.learn.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(DirectlyPresent.class)
//@Inherited
public @interface IndirectlyPresent {

}
