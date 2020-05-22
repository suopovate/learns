package sort;

import java.net.DatagramSocket;

public class MergeSortor implements BaseSortor{

    @Override
    public int[] sortIntAsc(int[] datas) {
        mergeSort(datas);
        return datas;
    }

    private void mergeSort(int[] datas){
        mergeSort_c(datas,0,datas.length-1);
    }

    private void mergeSort_c(int[] datas,int start,int end){
        //这个地方要注意，必须end > start(一开始没注意)
        if (end > start){
            int mid = (start+end)/2;
            mergeSort_c(datas, start,mid);
            mergeSort_c(datas, mid+1, end);
            merge(datas,mid,start,end);
        }
    }

    private void merge(int[] datas, int mid, int start, int end){
        int[] temp = new int[end-start+1];
        int i = start,j = mid+1,tp = 0;

        while (i <= mid && j <= end){
            int compare = datas[i] - datas[j];
           if (compare < 0){
               //i比较小
               temp[tp++] = datas[i++];
           }else if (compare > 0){
               //j比较小
               temp[tp++] = datas[j++];
           }else {
               //相等的话 将i先放进去(i在左边),再将j也放进去,保持排序的稳定性
               temp[tp++] = datas[i++];
               temp[tp++] = datas[j++];
           }
        }
        //找到左右两个数组中，剩余元素的数组，以及剩余元素的开始-结束索引
        int start0 = i,end0 = mid;
        if (j <= end){
            start0 = j;
            end0 = end;
        }
        //将剩余的数据填入临时数组
        for (; start0 <= end0 ;){
            temp[tp++] = datas[start0++];
        }
        //将临时数组中的数据 填充回源数组
        for (int k = 0; k <= end - start; k++) {
            datas[start+k] = temp[k];
        }
    }
}
