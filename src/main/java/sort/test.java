package sort;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import heap.Heap;
import mock.Person;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static java.time.temporal.ChronoField.NANO_OF_DAY;

public class test {

    public static void main(String[] args) {
        int[] datas = makeRandomIntArr(1000000);
//        validCurrent(datas);
//        testEffect(datas);
          testHeap();
    }

    public static void validCurrent(int[] datas){
        BaseSortor bubblingSortor = new BubblingSortor();
        BaseSortor insertSortor = new InsertSortor();
        BaseSortor selectSortor = new SelectSortor();
        BaseSortor mergeSortor = new MergeSortor();
        BaseSortor quickSortor = new QuickSortor();

        int[] datas0 = datas.clone();
        int[] datas1 = datas.clone();
        int[] datas2 = datas.clone();
        int[] datas3 = datas.clone();
        int[] datas4 = datas.clone();

        System.out.println(String.format("数组为：%s", orgnizeArr(datas)));

        System.out.println(String.format("冒泡排序结果：%s", orgnizeArr(bubblingSortor.sortIntAsc(datas0))));
        System.out.println(String.format("插入排序结果：%s", orgnizeArr(insertSortor.sortIntAsc(datas1))));
        System.out.println(String.format("选择排序结果：%s", orgnizeArr(selectSortor.sortIntAsc(datas2))));
        System.out.println(String.format("归并排序结果：%s", orgnizeArr(mergeSortor.sortIntAsc(datas3))));
        System.out.println(String.format("快速排序结果：%s", orgnizeArr(quickSortor.sortIntAsc(datas4))));
    }

    public static void testEffect(int[] datas){
        assert datas != null;
        BaseSortor bubblingSortor = new BubblingSortor();
        BaseSortor insertSortor = new InsertSortor();
        BaseSortor selectSortor = new SelectSortor();
        BaseSortor mergeSortor = new MergeSortor();
        BaseSortor quickSortor = new QuickSortor();


        System.out.println(String.format("数组长度为：%d", datas.length));
        int[] datas0 = datas.clone();
        int[] datas1 = datas.clone();
        int[] datas2 = datas.clone();
        int[] datas3 = datas.clone();
        int[] datas4 = datas.clone();
//        System.out.println(String.format("冒泡排序耗时：%dms", countCallingTime(datas0, bubblingSortor)));
//        System.out.println(String.format("插入排序耗时：%dms", countCallingTime(datas1, insertSortor)));
//        System.out.println(String.format("选择排序耗时：%dms", countCallingTime(datas2, selectSortor)));
        System.out.println(String.format("归并排序耗时：%dms", countCallingTime(datas3, mergeSortor)));
        System.out.println(String.format("快速排序耗时：%dms", countCallingTime(datas4, quickSortor)));
    }

    public static long countCallingTime(int[] datas,BaseSortor sortor){
        long start = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        sortor.sortIntAsc(datas);
        long end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return end - start;
    }

    public static String orgnizeArr(int[] datas){
        assert datas != null;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            str.append(datas[i] + ",");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    public static int[] makeRandomIntArr(int size){
        int size0 = size ==0? (int) (Math.random() * 100000) : size;
        int[] datas = new int[size];
        for (int i = 0; i < size; i++) {
            datas[i] = (int) (Math.random() * 1000);
        }
        return datas;
    }

    public static void testHeap(){
        Heap<Person> heap = new Heap(12);
        heap.add(Person.builder().age(15).build());
        heap.add(Person.builder().age(17).build());
        heap.add(Person.builder().age(13).build());
        heap.add(Person.builder().age(19).build());
        heap.del(Person.builder().age(13).build());
        heap.add(Person.builder().age(123).build());
        heap.add(Person.builder().age(162).build());
        heap.add(Person.builder().age(6).build());
        heap.add(Person.builder().age(6).build());
        System.out.println(heap);
        System.out.println(heap.printTreeDataAsArr());
        heap.sort();
        System.out.println(heap.printTreeDataAsArr());
    }
}
