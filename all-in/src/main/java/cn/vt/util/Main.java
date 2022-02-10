package cn.vt.util;

import java.lang.reflect.TypeVariable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Main<K extends String & Collection, V> {
    V value;
    public static void main(String[] args) throws Exception{
//        TypeVariable[] types = Main.class.getTypeParameters();
//        for(TypeVariable type : types){
//            System.out.println(type.getAnnotatedBounds());
//        }
    }
}
