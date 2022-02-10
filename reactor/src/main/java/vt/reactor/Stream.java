package vt.reactor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

public class Stream<T> {

    public static class Parent{}
    public static class Child extends Parent{}

    public static void main(String[] args) {
        new ArrayList<String>().stream().map(s -> s)
    }
}
