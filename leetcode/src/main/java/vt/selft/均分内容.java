package vt.selft;

import cn.hutool.core.lang.Tuple;

public class 均分内容 {

    public static void main(String[] args) {
        // 输入
        int[] datas = new int[]{ 100, 105, 205, 305, 75, 20, 35, 60 };
        // 从大到小排个序
        quickSort(datas);
        // 从头开始遍历
        int[] result = new int[2];
        int pl = 0, pr = datas.length - 1;
        while (true){
//            datas[pl]
        }
    }

    private static void quickSort(int[] datas) {
    }

}
